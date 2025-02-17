package Controller;

import View.FrCreateFilial;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrCreateFilialController {
    private FrCreateFilial view;

    public FrCreateFilialController(FrCreateFilial view) {
        this.view = view;
        initController();
    }

    private void initController() {
        view.getFecharButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleFecharButton();
            }
        });

        view.getCriarButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleCriarButton();
            }
        });
    }

    private void handleFecharButton() {
        System.exit(0);
    }

    private void handleCriarButton() {
        String textField1Value = view.getTextField1().getText();
        String textField2Value = view.getTextField2().getText();
        char[] passwordValue = view.getPasswordField1().getPassword();

        if (textField1Value.isEmpty() || textField2Value.isEmpty() || passwordValue.length == 0) {
            JOptionPane.showMessageDialog(view, "Todos os campos devem ser preenchidos!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        System.out.println("Campo 1: " + textField1Value);
        System.out.println("Campo 2: " + textField2Value);
        System.out.println("Senha: " + String.valueOf(passwordValue));

        JOptionPane.showMessageDialog(view, "Dados enviados com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
    }
}
