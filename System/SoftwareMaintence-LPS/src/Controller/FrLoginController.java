package Controller;

import View.FrLogin;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrLoginController {
    private FrLogin view;

    public FrLoginController(FrLogin view) {
        this.view = view;
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
        boolean isCliente = view.getClienteCheckBox().isSelected();

        if (email.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Email e senha devem ser preenchidos!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (email.equals("admin@example.com") && password.equals("1234") && !isCliente) {
            JOptionPane.showMessageDialog(view, "Bem-vindo, Administrador!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        } else if (email.equals("cliente@example.com") && password.equals("abcd") && isCliente) {
            JOptionPane.showMessageDialog(view, "Login realizado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(view, "Credenciais inválidas!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
