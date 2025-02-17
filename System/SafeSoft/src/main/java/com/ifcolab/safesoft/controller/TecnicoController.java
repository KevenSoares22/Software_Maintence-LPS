package com.ifcolab.safesoft.controller;

import com.ifcolab.safesoft.controller.tablemodel.TMViewTecnico;
import com.ifcolab.safesoft.model.Tecnico;
import com.ifcolab.safesoft.model.dao.TecnicoDAO;
import com.ifcolab.safesoft.model.exceptions.TecnicoException;
import com.ifcolab.safesoft.model.valid.ValidateTecnico;
import javax.swing.JTable;
import java.util.List;
import com.ifcolab.safesoft.model.enums.AreaGestao;
import com.ifcolab.safesoft.model.enums.TipoSexo;
import com.ifcolab.safesoft.utils.GeradorSenha;
import com.ifcolab.safesoft.utils.GerenciadorCriptografia;
import com.ifcolab.safesoft.utils.NotificadorEmail;

public class TecnicoController {
    
    private TecnicoDAO repositorio;
    private GerenciadorCriptografia gerenciadorCriptografia;
    private NotificadorEmail notificadorEmail;
    private ValidateTecnico valid;
    
    public TecnicoController() {
        repositorio = new TecnicoDAO();
        valid = new ValidateTecnico();
        gerenciadorCriptografia = new GerenciadorCriptografia();
        notificadorEmail = new NotificadorEmail();
    }
    
    public void cadastrar(String nome, String email, String cpf, TipoSexo sexo, String dataNascimento, String telefone, String endereco, String cod, AreaGestao especializacao, int avatar) {
        String senhaTemporaria = GeradorSenha.gerarSenha(8);
        String senhaHash = gerenciadorCriptografia.criptografarSenha(senhaTemporaria);
        
        Tecnico tecnico = valid.validaCamposEntrada(nome, email, senhaHash, cpf, sexo, dataNascimento, telefone, endereco, cod, especializacao, avatar);
        
    if (repositorio.findByCPF(cpf) != null) {
        throw new TecnicoException("CPF já cadastrado no sistema");
    }
    
    if (repositorio.findByEmail(email) != null) {
        throw new TecnicoException("Email já cadastrado no sistema");
    }
        
        repositorio.save(tecnico);
        enviarCredenciaisAcesso(tecnico, senhaTemporaria);
    }
    
    private void enviarCredenciaisAcesso(Tecnico tecnico, String senhaTemporaria) {
        String mensagem = String.format(
            "Olá %s,\n\n" +
            "Suas credenciais de acesso ao sistema Estetify foram criadas:\n\n" +
            "Email: %s\n" +
            "Senha: %s\n\n" +
            "Por favor, altere sua senha no primeiro acesso.\n\n" +
            "Atenciosamente,\n" +
            "Equipe Estetify",
            tecnico.getNome(),
            tecnico.getEmail(),
            senhaTemporaria
        );

        notificadorEmail.notificar(tecnico, "Credenciais de Acesso - Estetify", mensagem);
    }
    
    
    public void atualizar(int id, String nome, String email, String senha, String cpf, TipoSexo sexo, String dataNascimento, String telefone, String endereco, String cod, AreaGestao especializacao, int avatar) {
        
        Tecnico tecnico = valid.validaCamposEntrada(nome, email, senha, cpf, sexo, dataNascimento, telefone, endereco, cod, especializacao, avatar);
        tecnico.setId(id);
        
        Tecnico tecnicoExistenteCPF = repositorio.findByCPF(cpf);
        if (tecnicoExistenteCPF != null && tecnicoExistenteCPF.getId() != id) {
            throw new TecnicoException("CPF já cadastrado para outro tecnico");
        }
        
        Tecnico tecnicoExistenteEmail = repositorio.findByEmail(email);
        if (tecnicoExistenteEmail != null && tecnicoExistenteEmail.getId() != id) {
            throw new TecnicoException("Email já cadastrado para outro tecnico");
        }

        
        repositorio.update(tecnico);
    }
    
    public void excluir(Tecnico tecnico) {
        if (tecnico != null) {
            repositorio.delete(tecnico.getId());
        } else {
            throw new TecnicoException("Erro - Tecnico inexistente.");
        }
    }
    

    
    public void atualizarTabela(JTable grd) {
        TMViewTecnico tmTecnico = new TMViewTecnico(repositorio.findAll());
        grd.setModel(tmTecnico);
    }
    
    public void filtrarTabela(JTable grd, String nome) {
        TMViewTecnico tmTecnico = new TMViewTecnico(repositorio.filterByName(nome));
        grd.setModel(tmTecnico);
    }
    
    public Tecnico find(int id) {
        return repositorio.find(id);
    }
    
    public List<Tecnico> findAll() {
        return repositorio.findAll();
    }
}
