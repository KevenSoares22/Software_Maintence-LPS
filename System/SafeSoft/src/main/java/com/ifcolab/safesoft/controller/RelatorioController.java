package com.ifcolab.safesoft.controller;

import com.ifcolab.safesoft.controller.tablemodel.TMViewRelatorio;
import com.ifcolab.safesoft.model.Relatorio;
import com.ifcolab.safesoft.model.Servico;
import com.ifcolab.safesoft.model.Item;
import com.ifcolab.safesoft.model.dao.RelatorioDAO;
import com.ifcolab.safesoft.model.enums.TipoItem;
import com.ifcolab.safesoft.model.exceptions.RelatorioException;
import com.ifcolab.safesoft.model.valid.ValidateRelatorio;
import com.ifcolab.safesoft.utils.GeradorPdf;
import java.time.LocalDateTime;
import java.util.List;
import javax.swing.JTable;
import java.io.File;
import java.awt.Desktop;
import java.time.format.DateTimeFormatter;
import javax.swing.JFileChooser;

public class RelatorioController {
    
    private final RelatorioDAO repositorio;
    private final ValidateRelatorio validate;
    private final GeradorPdf geradorPdf;
    private Servico servico;
    private Item item;
    
    public RelatorioController(Servico servico, Item item) {
        this.repositorio = new RelatorioDAO();
        this.validate = new ValidateRelatorio();
        this.geradorPdf = new GeradorPdf();
        this.servico = servico;
        this.item = item;
    }
    
    public void cadastrar(String resultado, String observacoes, Servico servico, Item item) throws RelatorioException {
        String caminhoPdf = System.getProperty("user.home") + "/Estetify/relatorios/" + 
            String.format("%d_%d_%s",
                servico.getId(),
                item.getId(),
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"))
            );
        
        geradorPdf.gerarRelatorioItem(caminhoPdf, servico, item, resultado, observacoes);
        
        String caminhoCompletoPdf = caminhoPdf + "/relatorio_item.pdf";
        
        Relatorio relatorio = validate.validaCamposEntrada(
            LocalDateTime.now(),
            resultado,
            observacoes,
            caminhoCompletoPdf,
                servico
        );
        
        repositorio.save(relatorio);
    }
    
    public void atualizar(int id, String resultado, String observacoes, Servico servico, Item item) throws RelatorioException {
        String caminhoPdf = System.getProperty("user.home") + "/Estetify/relatorios/" + 
            String.format("%d_%d_%s",
                servico.getId(),
                item.getId(),
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"))
            );
        
        geradorPdf.gerarRelatorioItem(caminhoPdf, servico, item, resultado, observacoes);
        
        String caminhoCompletoPdf = caminhoPdf + "/relatorio_item.pdf";
        
        Relatorio relatorio = validate.validaCamposEntrada(
            LocalDateTime.now(),
            resultado,
            observacoes,
            caminhoCompletoPdf,
                servico
        );
        
        relatorio.setId(id);
        repositorio.update(relatorio);
    }
    
    public void validarESalvarRelatorio(String resultado, String observacoes) throws RelatorioException {
        if (resultado.isEmpty()) {
            throw new RelatorioException("O campo Resultado é obrigatório!");
        }
        cadastrar(resultado, observacoes, this.servico, this.item);
    }
    
    
    public List<Relatorio> findAll() {
        return repositorio.findAll();
    }

    public void atualizarTabela(JTable grd) {
        List<Relatorio> lst = repositorio.findAll();
        TMViewRelatorio tableModel = new TMViewRelatorio(lst);
        grd.setModel(tableModel);
    }
    
    public void abrirPdf(Relatorio relatorio) throws RelatorioException {
        try {
            java.io.File arquivo = new java.io.File(relatorio.getCaminhoPdf());
            if (arquivo.exists()) {
                java.awt.Desktop.getDesktop().open(arquivo);
            } else {
                throw new RelatorioException("Arquivo PDF não encontrado.");
            }
        } catch (Exception e) {
            throw new RelatorioException("Erro ao abrir PDF: " + e.getMessage());
        }
    }
    
    public void excluir(Relatorio relatorio) throws RelatorioException {
        if (relatorio == null) {
            throw new RelatorioException("Erro - Relatório inexistente.");
        }
        
        boolean deletado = repositorio.delete(relatorio.getId());
        if (!deletado) {
            throw new RelatorioException("Erro - Relatório inexistente.");
        }
    }
    
    public Relatorio buscarPorId(int id) throws RelatorioException {
        Relatorio relatorio = repositorio.find(id);
        if (relatorio == null) {
            throw new RelatorioException("Relatório não encontrado.");
        }
        return relatorio;
    }
    
    public List<Relatorio> buscarTodos() {
        return repositorio.findAll();
    }
    
    public TipoItem getTipoItem() {
        return item.getTipo();
    }
    
    public String getNomeCliente() {
        return servico.getCliente().getNome();
    }
    
    public String getNomeTecnico() {
        return servico.getTecnico().getNome();
    }
    
    public String getDataHoraFormatada() {
        return servico.getDataHora().format(
            java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
    }
    
    public Relatorio buscarRelatorioDaServico() {
        List<Relatorio> relatorios = repositorio.findAll();
        return relatorios.stream()
            .filter(r -> r.getServico().getId() == servico.getId())
            .findFirst()
            .orElse(null);
    }
    
    public void gerarRelatorio(String resultado, String observacoes) throws RelatorioException {
        if (resultado.isEmpty()) {
            throw new RelatorioException("O campo Resultado é obrigatório!");
        }
        
        try {
            // Abre diálogo para escolher onde salvar
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Salvar Relatório");
            
            // Define nome sugerido do arquivo
            String nomeArquivo = String.format("relatorio_%d_%d_%s.pdf", 
                servico.getId(),
                item.getId(),
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("ddMMyyyy"))
            );
            fileChooser.setSelectedFile(new File(nomeArquivo));
            
            // Mostra diálogo de salvar
            if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                String caminhoCompleto = fileChooser.getSelectedFile().getAbsolutePath();
                
                // Adiciona extensão .pdf se não houver
                if (!caminhoCompleto.toLowerCase().endsWith(".pdf")) {
                    caminhoCompleto += ".pdf";
                }
                
                // Gera o PDF
                geradorPdf.gerarRelatorioItem(caminhoCompleto, servico, item, resultado, observacoes);
                
                // Abre o arquivo PDF
                Desktop.getDesktop().open(new File(caminhoCompleto));
            }
            
        } catch (Exception e) {
            throw new RelatorioException("Erro ao gerar relatório: " + e.getMessage());
        }
    }
    
}