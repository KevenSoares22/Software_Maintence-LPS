package com.ifcolab.safesoft.controller;

import com.ifcolab.safesoft.controller.tablemodel.TMViewSuporte;
import com.ifcolab.safesoft.model.Suporte;
import com.ifcolab.safesoft.model.dao.SuporteDAO;
import com.ifcolab.safesoft.model.enums.TipoSexo;
import com.ifcolab.safesoft.model.exceptions.SuporteException;
import com.ifcolab.safesoft.model.valid.ValidateSuporte;
import com.ifcolab.safesoft.utils.GeradorSenha;
import com.ifcolab.safesoft.utils.GerenciadorCriptografia;
import com.ifcolab.safesoft.utils.NotificadorEmail;
import javax.swing.JTable;
import java.util.List;

public class SuporteController {

    private SuporteDAO repositorio;
    private GerenciadorCriptografia gerenciadorCriptografia;
    private NotificadorEmail notificadorEmail;
    private ValidateSuporte valid;

    public SuporteController() {
        repositorio = new SuporteDAO();
        valid = new ValidateSuporte();
        gerenciadorCriptografia = new GerenciadorCriptografia();
        notificadorEmail = new NotificadorEmail();
    }

    public void cadastrar(String nome, String email, String cpf, TipoSexo sexo,
                          String dataNascimento, String telefone, String endereco, String cod, int avatar) {

        String senhaTemporaria = GeradorSenha.gerarSenha(8);
        String senhaHash = gerenciadorCriptografia.criptografarSenha(senhaTemporaria);

        Suporte cliente = valid.validaCamposEntrada(nome, email, senhaHash, cpf,
                sexo, dataNascimento, telefone, endereco, cod, avatar);

        Suporte clienteExistenteCPF = repositorio.findByCPF(cpf);
        if (clienteExistenteCPF != null) {
            throw new SuporteException("CPF já cadastrado");
        }

        Suporte clienteExistenteEmail = repositorio.findByEmail(email);
        if (clienteExistenteEmail != null) {
            throw new SuporteException("Email já cadastrado");
        }

        repositorio.save(cliente);
        enviarCredenciaisAcesso(cliente, senhaTemporaria);
    }

    private void enviarCredenciaisAcesso(Suporte cliente, String senhaTemporaria) {
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


    public void atualizar(int id, String nome, String email, String senha, String cpf,
                          TipoSexo sexo, String dataNascimento, String telefone, String endereco,
                          String cod, int avatar) {

        Suporte cliente = valid.validaCamposEntrada(nome, email, senha, cpf,
                sexo, dataNascimento, telefone, endereco, cod, avatar);
        cliente.setId(id);

        Suporte clienteExistenteCPF = repositorio.findByCPF(cpf);
        if (clienteExistenteCPF != null && clienteExistenteCPF.getId() != id) {
            throw new SuporteException("CPF já cadastrado para outro cliente");
        }

        Suporte clienteExistenteEmail = repositorio.findByEmail(email);
        if (clienteExistenteEmail != null && clienteExistenteEmail.getId() != id) {
            throw new SuporteException("Email já cadastrado para outro cliente");
        }



        repositorio.update(cliente);
    }

    public void excluir(Suporte cliente) {
        if (cliente != null) {
            repositorio.delete(cliente.getId());
        } else {
            throw new SuporteException("Erro - Suporte inexistente.");
        }
    }



    public void atualizarTabela(JTable grd) {
        TMViewSuporte tmSuporte = new TMViewSuporte(repositorio.findAll());
        grd.setModel(tmSuporte);
    }

    public void filtrarTabela(JTable grd, String nome) {
        TMViewSuporte tmSuporte = new TMViewSuporte(repositorio.filterByName(nome));
        grd.setModel(tmSuporte);
    }

    public Suporte find(int id) {
        return repositorio.find(id);
    }

    public List<Suporte> findAll() {
        return repositorio.findAll();
    }
}
