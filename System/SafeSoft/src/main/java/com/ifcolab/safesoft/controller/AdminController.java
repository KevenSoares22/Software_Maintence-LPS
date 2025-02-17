package com.ifcolab.safesoft.controller;

import com.ifcolab.safesoft.controller.tablemodel.TMViewAdmin;
import com.ifcolab.safesoft.model.Admin;
import com.ifcolab.safesoft.model.dao.AdminDAO;
import com.ifcolab.safesoft.model.enums.TipoSexo;
import com.ifcolab.safesoft.model.exceptions.AdminException;
import com.ifcolab.safesoft.model.valid.ValidateAdmin;
import com.ifcolab.safesoft.utils.GeradorSenha;
import com.ifcolab.safesoft.utils.GerenciadorCriptografia;
import com.ifcolab.safesoft.utils.NotificadorEmail;
import javax.swing.JTable;
import java.util.List;

public class AdminController {
    
    private final AdminDAO repositorio;
    private final GerenciadorCriptografia gerenciadorCriptografia;
    private NotificadorEmail notificadorEmail;
    private ValidateAdmin valid;
    
    public AdminController() {
        this.repositorio = new AdminDAO();
        this.gerenciadorCriptografia = new GerenciadorCriptografia();
        valid = new ValidateAdmin();
        notificadorEmail = new NotificadorEmail();
    }
    
    public void criarAdminPadrao() {
        if (!repositorio.findAll().isEmpty()) {
            return;
        }

        String nome = "Administrador";
        String email = "admin@safesoft.com";
        String senha = "admin";
        String cpf = "000.000.000-00";
        String telefone = "(00) 00000-0000";
        String dataNascimento = "01/01/2000";
        String endereco = "Endereço Padrão";
        
        try {
            String senhaHash = gerenciadorCriptografia.criptografarSenha(senha);
            
            ValidateAdmin valid = new ValidateAdmin();
            Admin admin = valid.validaCamposEntrada(nome, email, senhaHash, cpf, 
                    TipoSexo.MASCULINO, dataNascimento, telefone, endereco, 1);
            
            repositorio.save(admin);
        } catch (Exception e) {
            throw new AdminException("Erro ao criar administrador padrão");
        }
    }
    
    public void cadastrar(String nome, String email, String cpf, TipoSexo sexo, 
        String dataNascimento, String telefone, String endereco, int avatar) {
            
        String senhaTemporaria = GeradorSenha.gerarSenha(8);
        String senhaHash = gerenciadorCriptografia.criptografarSenha(senhaTemporaria);
        
        Admin admin = valid.validaCamposEntrada(nome, email, senhaHash, cpf, sexo, 
                dataNascimento, telefone, endereco, avatar);
        
        Admin adminExistenteCPF = repositorio.findByCPF(cpf);
        if (adminExistenteCPF != null) {
            throw new AdminException("CPF já cadastrado");
        }
        
        Admin adminExistenteEmail = repositorio.findByEmail(email);
        if (adminExistenteEmail != null) {
            throw new AdminException("Email já cadastrado");
        }
        
        repositorio.save(admin);
        enviarCredenciaisAcesso(admin, senhaTemporaria);
    }
    
    private void enviarCredenciaisAcesso(Admin admin, String senhaTemporaria) {
        String mensagem = String.format(
            "Olá %s,\n\n" +
            "Suas credenciais foram criadas:\n\n" +
            "Email: %s\n" +
            "Senha: %s\n\n" +
            "Por favor, altere sua senha no primeiro acesso.\n\n" +
            "Atenciosamente,\n" +
            "Equipe SafeSoft",
            admin.getNome(),
            admin.getEmail(),
            senhaTemporaria
        );

        notificadorEmail.notificar(admin, "Credenciais de Acesso", mensagem);
    }
    
    public void atualizar(int id, String nome, String email, String senha, String cpf, 
            TipoSexo sexo, String dataNascimento, String telefone, String endereco, int avatar) {
            
        Admin admin = valid.validaCamposEntrada(nome, email, senha, cpf, sexo, 
                dataNascimento, telefone, endereco, avatar);
        admin.setId(id);
        
        Admin adminExistenteCPF = repositorio.findByCPF(cpf);
        if (adminExistenteCPF != null && adminExistenteCPF.getId() != id) {
            throw new AdminException("CPF já cadastrado para outro administrador");
        }
        
        Admin adminExistenteEmail = repositorio.findByEmail(email);
        if (adminExistenteEmail != null && adminExistenteEmail.getId() != id) {
            throw new AdminException("Email já cadastrado para outro administrador");
        }
        
        repositorio.update(admin);
    }
    
    public void excluir(Admin admin) {
        if (admin != null) {
            repositorio.delete(admin.getId());
        } else {
            throw new AdminException("Erro - Administrador inexistente.");
        }
    }
    
    public Admin buscarPorCPF(String cpf) {
        return repositorio.findByCPF(cpf);
    }
    
    public List<Admin> findAll() {
        return repositorio.findAll();
    }
    
    public Admin find(int id) {
        return repositorio.find(id);
    }
    
    public void atualizarTabela(JTable grd) {
        TMViewAdmin tmAdmin = new TMViewAdmin(repositorio.findAll());
        grd.setModel(tmAdmin);
    }
    
    public void filtrarTabela(JTable grd, String nome) {
        TMViewAdmin tmAdmin = new TMViewAdmin(repositorio.filterByName(nome));
        grd.setModel(tmAdmin);
    }
} 