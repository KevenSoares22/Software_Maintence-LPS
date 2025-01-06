package Controller;

import View.FrCreateManager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrCreateManagerController {
    private FrCreateManager view;

    public FrCreateManagerController(FrCreateManager view) {
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
        String campo1 = view.getTextField1().getText();
        String campo2 = view.getTextField2().getText();
        String campo3 = view.getTextField3().getText();
        String campo6 = view.getTextField6().getText();
        String campo7 = view.getTextField7().getText();
        char[] password = view.getPasswordField1().getPassword();

        if (campo1.isEmpty() || campo2.isEmpty() || campo3.isEmpty() || campo6.isEmpty() || campo7.isEmpty() || password.length == 0) {
            JOptionPane.showMessageDialog(view, "Todos os campos devem ser preenchidos!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        System.out.println("Campo 1: " + campo1);
        System.out.println("Campo 2: " + campo2);
        System.out.println("Campo 3: " + campo3);
        System.out.println("Campo 6: " + campo6);
        System.out.println("Campo 7: " + campo7);
        System.out.println("Senha: " + String.valueOf(password));

        JOptionPane.showMessageDialog(view, "Gerente registrado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
    }
}
