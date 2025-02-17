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
        // Pegando os valores dos campos com base nos getters definidos
        String email = view.getEmailField().getText();
        String name = view.getNameField().getText();
        String idetity = view.getIdetityField().getText();
        String salary = view.getSalaryField().getText();
        String filial = String.valueOf(view.getFilialField()); // Para pegar o texto do JPasswordField
        String password = String.valueOf(view.getPasswordField().getPassword()); // Para pegar a senha do JPasswordField

        // Verificando se algum campo está vazio
        if (email.isEmpty() || name.isEmpty() || idetity.isEmpty() || salary.isEmpty() || filial.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Todos os campos devem ser preenchidos!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Exibindo os dados no console (ou podendo implementar lógica adicional aqui)
        System.out.println("Email: " + email);
        System.out.println("Nome: " + name);
        System.out.println("Identidade: " + idetity);
        System.out.println("Salário: " + salary);
        System.out.println("Filial: " + filial);
        System.out.println("Senha: " + password);

        // Exibindo uma mensagem de sucesso
        JOptionPane.showMessageDialog(view, "Gerente registrado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
    }
}
