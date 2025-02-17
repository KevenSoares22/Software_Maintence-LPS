package com.ifcolab.safesoft.controller;

import com.ifcolab.safesoft.model.Pagamento;
import com.ifcolab.safesoft.model.Servico;
import com.ifcolab.safesoft.model.Pessoa;
import com.ifcolab.safesoft.model.dao.PagamentoDAO;
import com.ifcolab.safesoft.model.enums.StatusPagamento;
import com.ifcolab.safesoft.model.enums.MetodoPagamento;
import com.ifcolab.safesoft.model.exceptions.PagamentoException;
import com.ifcolab.safesoft.model.valid.ValidatePagamento;
import com.ifcolab.safesoft.controller.tablemodel.TMViewPagamento;
import com.ifcolab.safesoft.utils.GeradorPdf;
import com.ifcolab.safesoft.utils.NotificadorEmail;
import java.util.List;
import java.time.format.DateTimeFormatter;
import java.io.File;
import java.awt.Desktop;
import javax.swing.JTable;

public class PagamentoController {
    private final PagamentoDAO repositorio;
    private final ValidatePagamento validador;
    private final NotificadorEmail notificadorEmail;
    private final Servico servico;
    
    public PagamentoController(Servico servico) {
        this.repositorio = new PagamentoDAO();
        this.validador = new ValidatePagamento();
        this.notificadorEmail = new NotificadorEmail();
        this.servico = servico;
    }
    
    public PagamentoController() {
        this(null);
    }
    
    public void realizarPagamento(double valor, MetodoPagamento metodoPagamento, String detalhes) {
        if (metodoPagamento == null) {
            throw new PagamentoException("Selecione um método de pagamento.");
        }
        
        cadastrar(valor, StatusPagamento.PAGO, metodoPagamento, detalhes, 
                 this.servico, this.servico.getTecnico());
    }
    
    private void cadastrar(double valor, StatusPagamento status, MetodoPagamento metodoPagamento,
                           String detalhes, Servico servico, Pessoa registrador) {
        if (metodoPagamento == null) {
            throw new PagamentoException("Selecione um método de pagamento.");
        }
        
        Pagamento pagamento = validador.validaCamposEntrada(valor, status, metodoPagamento, 
                                                          detalhes, servico, registrador);
        
        repositorio.save(pagamento);
        enviarConfirmacaoPagamento(valor, metodoPagamento);
    }
    
    private void enviarConfirmacaoPagamento(double valor, MetodoPagamento metodoPagamento) {
        String mensagem = String.format(
            "Olá %s,\n\n" +
            "O pagamento da sua servico foi confirmado:\n\n" +
            "Data: %s\n" +
            "Tecnico: %s\n" +
            "Valor: R$ %.2f\n" +
            "Forma de Pagamento: %s\n\n" +
            "Agradecemos a preferência!\n\n" +
            "Atenciosamente,\n" +
            "Equipe SafeSoft",
            servico.getCliente().getNome(),
            servico.getDataHora().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")),
            servico.getTecnico().getNome(),
            valor,
            metodoPagamento.toString()
        );

        notificadorEmail.notificar(
            servico.getCliente(),
            "Confirmação de Pagamento - SafeSoft", 
            mensagem
        );
    }
    
    public String getNomeCliente() {
        return servico.getCliente().getNome();
    }
    
    public String getDataHoraFormatada() {
        return servico.getDataHora().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
    }
    
    public String getNomeTecnico() {
        return servico.getTecnico().getNome();
    }
    
    public double getValorTotal() {
        return servico.getValorTotal();
    }
    
    public List<String> getItensFormatados() {
        return servico.getItens().stream()
            .map(proc -> String.format("• %s - R$ %.2f", 
                proc.getNome(), proc.getValor()))
            .toList();
    }
    
    public MetodoPagamento[] getMetodosPagamento() {
        return MetodoPagamento.values();
    }
    
    public List<Pagamento> buscarPorServico(int servicoId) {
        return repositorio.buscarPorServico(servicoId);
    }
    
    public void excluir(Pagamento pagamento) throws Exception {
        if (pagamento == null) {
            throw new PagamentoException("Pagamento não encontrado.");
        }
        
        repositorio.delete(pagamento.getId());
    }
    
    public Pagamento buscar(int id) {
        return repositorio.find(id);
    }
    
    public List<Pagamento> buscarTodos() {
        return repositorio.findAll();
    }
    
    public void atualizar(int id, double valor, MetodoPagamento metodoPagamento, 
        StatusPagamento status, String detalhes) {
        Pagamento pagamento = buscar(id);
        if (pagamento == null) {
            throw new PagamentoException("Pagamento não encontrado.");
        }
        
        pagamento = validador.validaCamposEntrada(
            valor, 
            status, 
            metodoPagamento, 
            detalhes, 
            pagamento.getServico(),
            pagamento.getServico().getTecnico()
        );
        
        pagamento.setId(id);
        repositorio.update(pagamento);
    }
    
    public void atualizarTabela(JTable tabela) {
        List<Pagamento> pagamentos = buscarTodos();
        TMViewPagamento modelo = new TMViewPagamento(pagamentos);
        tabela.setModel(modelo);
    }
    
    public void emitirRecibo(Pagamento pagamento) throws Exception {
        if (pagamento == null) {
            throw new PagamentoException("Pagamento não encontrado.");
        }
        
        // Criar caminho base
        String caminhoPdf = System.getProperty("user.home") + "/SafeSoft/recibos/" + 
            String.format("%d_%s",
                pagamento.getId(),
                pagamento.getDataPagamento().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"))
            );
        
        // O GeradorPdf já cuida da criação do diretório e uso correto do separador
        GeradorPdf geradorPdf = new GeradorPdf();
        geradorPdf.gerarReciboPagamento(caminhoPdf, pagamento.getServico(), pagamento);
        
        // Abre o arquivo PDF gerado
        String caminhoCompletoPdf = caminhoPdf + "/recibo_pagamento.pdf";
        Desktop.getDesktop().open(new File(caminhoCompletoPdf));
    }
}
