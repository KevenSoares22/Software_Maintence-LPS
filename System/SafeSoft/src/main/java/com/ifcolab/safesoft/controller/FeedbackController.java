package com.ifcolab.safesoft.controller;

import com.ifcolab.safesoft.controller.tablemodel.TMViewFeedback;
import com.ifcolab.safesoft.model.Feedback;
import com.ifcolab.safesoft.model.Servico;
import com.ifcolab.safesoft.model.Cliente;
import com.ifcolab.safesoft.model.dao.FeedbackDAO;
import com.ifcolab.safesoft.model.exceptions.FeedbackException;
import com.ifcolab.safesoft.model.valid.ValidateFeedback;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.JTable;
import java.util.Optional;


public class FeedbackController {
    
    private final FeedbackDAO repositorio;
    private final ValidateFeedback validate;
    private final ServicoController servicoController;
    private final AutenticacaoController autenticacaoController;
    
    public FeedbackController() {
        repositorio = new FeedbackDAO();
        validate = new ValidateFeedback();
        servicoController = new ServicoController();
        autenticacaoController = new AutenticacaoController();
    }
    
    public void cadastrar(String titulo, String descricao, int avaliacao, LocalDateTime dataAvaliacao, Servico servico) {
        Feedback feedback = validate.validaCamposEntrada(titulo, descricao, avaliacao, servico, dataAvaliacao);
        repositorio.save(feedback);
    }
    
    public void atualizar(String titulo, String descricao, int avaliacao, LocalDateTime dataAvaliacao, Servico servico) {
        Feedback feedback = validate.validaCamposEntrada(titulo, descricao, avaliacao, servico, dataAvaliacao);
        repositorio.update(feedback);
    }
    
    public void excluir(Feedback feedback) {
        if (feedback != null) {
            repositorio.delete(feedback.getId());
        } else {
            throw new FeedbackException("Erro - Feedback inexistente.");
        }
    }
    
    public List<Feedback> findAll() {
        return repositorio.findAll();
    }
    
    public void atualizarTabela(JTable grd) {
        TMViewFeedback tmFeedback = new TMViewFeedback(repositorio.findAll());
        grd.setModel(tmFeedback);
    }
    
    public List<Servico> buscarServicosDisponiveis() {
        return servicoController.findAll().stream()
                               .filter(Servico::isConcluida)
                               .collect(Collectors.toList());
    }
    
    public Optional<Feedback> buscarFeedbackPorServico(Servico servico) {
        return findAll().stream()
                       .filter(f -> f.getServico().getId() == servico.getId())
                       .findFirst();
    }
    
    public void atualizarTabelaTodosFeedbacks(JTable grd) {
        TMViewFeedback tmFeedback = new TMViewFeedback(findAll());
        grd.setModel(tmFeedback);
    }
    
    public void atualizarTabelaFeedbacksUsuario(JTable grd) {
        Cliente clienteLogado = autenticacaoController.getClienteLogado();
        if (clienteLogado != null) {
            List<Feedback> feedbacksUsuario = findAll().stream()
                .filter(f -> f.getServico().getCliente().getId() == clienteLogado.getId())
                .collect(Collectors.toList());
            
            TMViewFeedback tmFeedback = new TMViewFeedback(feedbacksUsuario);
            grd.setModel(tmFeedback);
        }
    }
    
    public boolean isUserAdmin() {
        return autenticacaoController.isAdmin();
    }
    
    public void excluirFeedbackSelecionado(JTable grd, int selectedRow) {
        TMViewFeedback model = (TMViewFeedback) grd.getModel();
        Feedback feedback = model.getFeedback(selectedRow);
        
        if (feedback != null) {
            excluir(feedback);
        } else {
            throw new FeedbackException("Não foi possível excluir o feedback selecionado.");
        }
    }
}
