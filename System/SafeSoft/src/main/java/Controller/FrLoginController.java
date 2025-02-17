package Controller;

import View.FrLogin;
import DAO.UserDAO;
import Model.Entities.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrLoginController {
    private FrLogin view;
    private UserDAO userDAO;

    public FrLoginController(FrLogin view) {
        this.view = view;
        this.userDAO = new UserDAO(); // Inicializando o DAO
        initController();
    }

    private void initController() {
        view.getLoginBtn().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleLogin();
            }
        });
    }

    private void handleLogin() {
        String email = view.getEmailField().getText();
        String password = new String(view.getPasswordField().getPassword());
        boolean isCliente = view.getClientCheckBox().isSelected();

        if (email.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Email e senha devem ser preenchidos!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Verificar no banco de dados
        User user = userDAO.findByEmail(email);

        if (user == null) {
            JOptionPane.showMessageDialog(view, "Credenciais inválidas!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Verificar se a senha está correta
        if (user.getPassword().equals(password) && user.getIsClient() == isCliente) {
            if (isCliente) {
                JOptionPane.showMessageDialog(view, "Login realizado com sucesso, Cliente!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(view, "Bem-vindo, Administrador!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(view, "Credenciais inválidas!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
