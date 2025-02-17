package com.ifcolab.safesoft.controller;

import com.ifcolab.safesoft.model.Servico;
import com.ifcolab.safesoft.model.Pagamento;
import com.ifcolab.safesoft.model.Item;
import com.ifcolab.safesoft.model.enums.StatusServico;
import com.ifcolab.safesoft.model.enums.TipoUsuario;
import com.ifcolab.safesoft.utils.GeradorPdf;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class OpcoesServicoController {
    private final ServicoController servicoController;
    private final AutenticacaoController autenticacaoController;
    private final Servico servico;
    private final PagamentoController pagamentoController;
    private final GeradorPdf geradorPdf;
    
    public OpcoesServicoController(Servico servico) {
        this.servicoController = new ServicoController();
        this.autenticacaoController = new AutenticacaoController();
        this.pagamentoController = new PagamentoController(servico);
        this.geradorPdf = new GeradorPdf();
        this.servico = servico;
    }
    
    public String getStatusFormatado() {
        return servico.getStatus().toString();
    }
    
    public String getDataHoraFormatada() {
        return servico.getDataHora().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
    }
    
    public String getNomeCliente() {
        return servico.getCliente().getNome();
    }
    
    public String getNomeTecnico() {
        return servico.getTecnico().getNome();
    }
    
    public String getNomeSuporte() {
        return servico.getSuporte().getNome();
    }
    
    public List<String> getItensFormatados() {
        return servico.getItens().stream()
            .map(p -> p.getNome() + " - R$ " + String.format("%.2f", p.getValor()))
            .toList();
    }
    
    public double getValorTotal() {
        return servico.getValorTotal();
    }
    
    public StatusServico getStatus() {
        return servico.getStatus();
    }
    
    public boolean podeConfirmarServico() {
        return servicoController.podeConfirmarServico(servico);
    }
    
    public boolean podeRealizarServico() {
        return servicoController.podeRealizarServico(servico);
    }
    
    public boolean podeFazerPagamento() {
        return servico.isConcluida() && !temPagamentoRegistrado();
    }
    
    public boolean podeEmitirRecibo() {
        return servico.isConcluida() && temPagamentoRegistrado();
    }
    
    private boolean temPagamentoRegistrado() {
        return !buscarPagamentosServico().isEmpty();
    }
    
    public boolean podeVerRelatorios() {
        TipoUsuario tipoUsuario = autenticacaoController.getUsuarioLogado().getTipoUsuario();
        return tipoUsuario == TipoUsuario.ADMIN || 
               tipoUsuario == TipoUsuario.TECNICO ||
               tipoUsuario == TipoUsuario.CLIENTE;
    }
    
    public boolean podeGerenciarPagamentos() {
        TipoUsuario tipoUsuario = autenticacaoController.getUsuarioLogado().getTipoUsuario();
        return tipoUsuario == TipoUsuario.ADMIN|| 
               tipoUsuario == TipoUsuario.RECEPCIONISTA;
    }
    
    public void confirmarServico() {
        servicoController.confirmarServico(servico.getId());
    }
    
    public void cancelarServico() {
        servicoController.cancelarServico(servico.getId());
    }
    
    public void realizarServico() {
        servicoController.realizarServico(servico.getId());
    }
    
    public List<Item> getItens() {
        return servico.getItens();
    }
    
    public Servico getServico() {
        return servico;
    }
    
    public List<Pagamento> buscarPagamentosServico() {
        return pagamentoController.buscarPorServico(servico.getId());
    }
    
    public void gerarReciboPagamento(Pagamento pagamento) throws Exception {
        pagamentoController.emitirRecibo(pagamento);
    }
} 