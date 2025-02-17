package com.ifcolab.safesoft.controller;

import com.ifcolab.safesoft.model.*;
import com.ifcolab.safesoft.model.dao.*;
import com.ifcolab.safesoft.model.enums.TipoUsuario;
import com.ifcolab.safesoft.model.exceptions.AutenticacaoException;
import com.ifcolab.safesoft.utils.Autenticacao;
import com.ifcolab.safesoft.utils.GeradorSenha;
import com.ifcolab.safesoft.utils.GerenciadorCriptografia;
import com.ifcolab.safesoft.utils.NotificadorEmail;
import java.time.LocalDateTime;

public class AutenticacaoController {
    private final TecnicoDAO tecnicoDAO;
    private final SuporteDAO SuporteDAO;
    private final ClienteDAO clienteDAO;
    private final AdminDAO adminDAO;
    private final Autenticacao autenticacao;
    private final GerenciadorCriptografia gerenciadorCriptografia;
    private final NotificadorEmail notificadorEmail;

    public AutenticacaoController() {
        this.tecnicoDAO = new TecnicoDAO();
        this.SuporteDAO = new SuporteDAO();
        this.clienteDAO = new ClienteDAO();
        this.adminDAO = new AdminDAO();
        this.autenticacao = Autenticacao.getInstance();
        this.gerenciadorCriptografia = new GerenciadorCriptografia();
        this.notificadorEmail = new NotificadorEmail();
    }

    private void realizarAutenticacao(Pessoa usuario, String senha) {
        if (usuario.getCodigoRecuperacao() != null && usuario.getValidadeCodigoRecuperacao() != null) {
            if (usuario.getCodigoRecuperacao().equals(senha)
                    && usuario.getValidadeCodigoRecuperacao().isAfter(LocalDateTime.now())) {
                autenticacao.setUsuario(usuario);
                return;
            }
        }

        if (!gerenciadorCriptografia.compararSenha(senha, usuario.getSenha())) {
            throw new AutenticacaoException("Senha incorreta");
        }

        autenticacao.setUsuario(usuario);
    }

    public void login(String email, String senha) {

        Pessoa usuario = tecnicoDAO.findByEmail(email);
        if (usuario == null) {
            usuario = SuporteDAO.findByEmail(email);
        }
        if (usuario == null) {
            usuario = clienteDAO.findByEmail(email);
        }

        if (usuario == null) {
            usuario = adminDAO.findByEmail(email);
        }

        if (usuario == null) {
            throw new AutenticacaoException("Usuário não encontrado");
        }

        realizarAutenticacao(usuario, senha);
    }

    public void logout() {
        autenticacao.setUsuario(null);
    }

    public Pessoa getUsuarioLogado() {
        return autenticacao.getUsuario();
    }

    public boolean isUsuarioLogado() {
        return autenticacao.getUsuario() != null;
    }

    public boolean isAdmin() {
        return isUsuarioLogado() && autenticacao.getUsuario().getTipoUsuario() == TipoUsuario.ADMIN;
    }

    public boolean isTecnico() {
        return isUsuarioLogado() && autenticacao.getUsuario().getTipoUsuario() == TipoUsuario.TECNICO;
    }

    public boolean isSuporte() {
        return isUsuarioLogado() && autenticacao.getUsuario().getTipoUsuario() == TipoUsuario.Suporte;
    }

    public boolean isRecepcionista() {
        return isUsuarioLogado() && autenticacao.getUsuario().getTipoUsuario() == TipoUsuario.RECEPCIONISTA;
    }

    public boolean isCliente() {
        return isUsuarioLogado() && autenticacao.getUsuario().getTipoUsuario() == TipoUsuario.CLIENTE ;
    }

    public Tecnico getTecnicoLogado() {
        return isTecnico() ? (Tecnico) autenticacao.getUsuario() : null;
    }

    public Suporte getSuporteLogada() {
        return isSuporte() ? (Suporte) autenticacao.getUsuario() : null;
    }

    public Cliente getClienteLogado() {
        return isCliente() ? (Cliente) autenticacao.getUsuario() : null;
    }

    public void atualizarDadosUsuario(Pessoa usuario, int novoAvatar) {
        try {
            if (novoAvatar > 0) {
                usuario.setAvatar(novoAvatar);
            }

            if (usuario instanceof Tecnico) {
                tecnicoDAO.update((Tecnico) usuario);
            }
            else if (usuario instanceof Suporte) {
                SuporteDAO.update((Suporte) usuario);
            }
            else if (usuario instanceof Cliente) {
                clienteDAO.update((Cliente) usuario);
            }

            else if (usuario instanceof Admin) {
                adminDAO.update((Admin) usuario);
            }
            else {
                throw new RuntimeException("Tipo de usuário não suportado");
            }

            autenticacao.setUsuario(usuario);

        } catch (Exception ex) {
            throw new RuntimeException("Erro ao atualizar dados: " + ex.getMessage());
        }
    }

    public void atualizarSenhaUsuario(int idUsuario, String novaSenhaHash) {
        try {
            Pessoa usuario = autenticacao.getUsuario();

            if (usuario == null || usuario.getId() != idUsuario) {
                throw new AutenticacaoException("Usuário não autorizado para esta operação");
            }

            usuario.setSenha(novaSenhaHash);

            usuario.setCodigoRecuperacao(null);
            usuario.setValidadeCodigoRecuperacao(null);

            if (usuario instanceof Tecnico) {
                tecnicoDAO.update((Tecnico) usuario);
            }
            else if (usuario instanceof Suporte) {
                SuporteDAO.update((Suporte) usuario);
            }
            else if (usuario instanceof Cliente) {
                clienteDAO.update((Cliente) usuario);}


            else if (usuario instanceof Admin) {
                adminDAO.update((Admin) usuario);
            }
            else {
                throw new RuntimeException("Tipo de usuário não suportado");
            }

            autenticacao.setUsuario(usuario);

        } catch (Exception ex) {
            throw new RuntimeException("Erro ao atualizar senha: " + ex.getMessage());
        }
    }

    public Pessoa buscarUsuarioPorEmail(String email) {

        Pessoa usuario = tecnicoDAO.findByEmail(email);
        if (usuario == null) {
            usuario = SuporteDAO.findByEmail(email);
        }
        if (usuario == null) {
            usuario = clienteDAO.findByEmail(email);
        }

        if (usuario == null) {
            usuario = adminDAO.findByEmail(email);
        }


        return usuario;
    }

    public void recuperarSenha(String email) {
        Pessoa usuario = buscarUsuarioPorEmail(email);

        if (usuario == null) {
            throw new AutenticacaoException("Email não encontrado no sistema!");
        }

        String senhaTemporaria = GeradorSenha.gerarSenha(8);
        String senhaHash = gerenciadorCriptografia.criptografarSenha(senhaTemporaria);

        usuario.setSenha(senhaHash);
        atualizarDadosUsuario(usuario, usuario.getAvatar());

        String mensagem = String.format(
                "Olá %s,\n\n" +
                        "Você solicitou a recuperação de sua senha do sistema Estetify.\n" +
                        "Uma nova senha temporária foi gerada:\n\n" +
                        "Email: %s\n" +
                        "Nova Senha: %s\n\n" +
                        "Por favor, altere esta senha após realizar o login.\n" +
                        "Caso não tenha solicitado esta recuperação, entre em contato com o Suporte.\n\n" +
                        "Atenciosamente,\n" +
                        "Equipe Estetify",
                usuario.getNome(),
                usuario.getEmail(),
                senhaTemporaria
        );

        if (!notificadorEmail.notificar(usuario, "Nova Senha Temporária - Estetify", mensagem)) {
            throw new AutenticacaoException("Erro ao enviar email de recuperação");
        }
    }
}