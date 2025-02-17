package com.ifcolab.safesoft.controller;

import com.ifcolab.safesoft.controller.tablemodel.TMViewCliente;
import com.ifcolab.safesoft.model.Cliente;
import com.ifcolab.safesoft.model.Servico;
import com.ifcolab.safesoft.model.dao.ClienteDAO;
import com.ifcolab.safesoft.model.dao.ServicoDAO;
import com.ifcolab.safesoft.model.enums.TipoSexo;
import com.ifcolab.safesoft.model.exceptions.ClienteException;
import com.ifcolab.safesoft.model.valid.ValidateCliente;
import com.ifcolab.safesoft.utils.GeradorSenha;
import com.ifcolab.safesoft.utils.GerenciadorCriptografia;
import com.ifcolab.safesoft.utils.NotificadorEmail;
import javax.swing.JTable;
import java.util.List;


public class ClienteController {
    
    private ClienteDAO repositorio;
    private ServicoDAO servicoDAO;
    private ValidateCliente valid;
    private GerenciadorCriptografia gerenciadorCriptografia;
    private NotificadorEmail notificadorEmail;
    
    public ClienteController() {
        this.repositorio = new ClienteDAO();
        this.servicoDAO = new ServicoDAO();
        valid = new ValidateCliente();
        gerenciadorCriptografia = new GerenciadorCriptografia();
        notificadorEmail = new NotificadorEmail();
    }
    
    public void cadastrar(String nome, String email, String cpf, TipoSexo sexo, String dataNascimento, String telefone, String endereco, String historicoTecnico, int avatar) {
        
        String senhaTemporaria = GeradorSenha.gerarSenha(8);
        String senhaHash = gerenciadorCriptografia.criptografarSenha(senhaTemporaria);
        
        Cliente cliente = valid.validaCamposEntrada(nome, email, senhaHash, cpf, sexo, dataNascimento, telefone, endereco, historicoTecnico, avatar);
        
        Cliente clienteExistenteCPF = repositorio.findByCPF(cpf);
        if (clienteExistenteCPF != null) {
            throw new ClienteException("CPF já cadastrado");
        }
        
        Cliente clienteExistenteEmail = repositorio.findByEmail(email);
        if (clienteExistenteEmail != null) {
            throw new ClienteException("Email já cadastrado");
        }

        repositorio.save(cliente);
        enviarCredenciaisAcesso(cliente, senhaTemporaria);
    }
    
    private void enviarCredenciaisAcesso(Cliente cliente, String senhaTemporaria) {
        String mensagem = String.format(
            "Olá %s,\n\n" +
            "Suas credenciais de acesso ao sistema Estetify foram criadas:\n\n" +
            "Email: %s\n" +
            "Senha: %s\n\n" +
            "Por favor, altere sua senha no primeiro acesso.\n\n" +
            "Atenciosamente,\n" +
            "Equipe Estetify",
            cliente.getNome(),
            cliente.getEmail(),
            senhaTemporaria
        );

        notificadorEmail.notificar(cliente, "Credenciais de Acesso - Estetify", mensagem);
    }
    
    public void atualizar(int id, String nome, String email, String senha, String cpf, TipoSexo sexo, String dataNascimento, String telefone, String endereco, String historicoTecnico, int avatar) {
        
        Cliente cliente = valid.validaCamposEntrada(nome, email, senha, cpf, sexo, dataNascimento, telefone, endereco, historicoTecnico, avatar);
        cliente.setId(id);
        
        Cliente clienteExistenteCPF = repositorio.findByCPF(cpf);
        if (clienteExistenteCPF != null && clienteExistenteCPF.getId() != id) {
            throw new ClienteException("CPF já cadastrado para outro cliente");
        }
        
        Cliente clienteExistenteEmail = repositorio.findByEmail(email);
        if (clienteExistenteEmail != null && clienteExistenteEmail.getId() != id) {
            throw new ClienteException("Email já cadastrado para outro cliente");
        }
        
        repositorio.update(cliente);
    }
    
    public void excluir(Cliente cliente) {
        if (cliente == null) {
            throw new ClienteException("Erro - Cliente inexistente.");
        }
        
        List<Servico> servicos = servicoDAO.buscarServicosPorCliente(cliente.getId());
        if (!servicos.isEmpty()) {
            throw new ClienteException("Não é possível excluir o cliente pois existem servicos associados a ele.");
        }
        
        boolean deletado = repositorio.delete(cliente.getId());
        if (!deletado) {
            throw new ClienteException("Erro ao excluir o cliente.");
        }
    }
    
    public Cliente buscarPorCPF(String cpf) {
        return repositorio.findByCPF(cpf);
    }
    
    public List<Cliente> findAll() {
        return repositorio.findAll();
    }
    
    public Cliente find(int id) {
        return repositorio.find(id);
    }
    
    public void atualizarTabela(JTable grd) {
        TMViewCliente tmCliente = new TMViewCliente(repositorio.findAll());
        grd.setModel(tmCliente);
    }
    
    public void filtrarTabela(JTable grd, String nome) {
        TMViewCliente tmCliente = new TMViewCliente(repositorio.filterByName(nome));
        grd.setModel(tmCliente);
    }
}
