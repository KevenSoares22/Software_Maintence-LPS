package Controller;

import View.FrCreateTechnician;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrCreateTechnicianController {
    private FrCreateTechnician view;

    public FrCreateTechnicianController(FrCreateTechnician view) {
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
        // Usando os getters para acessar os campos
        String nome = view.getNameField().getText();
        String email = view.getEmailField().getText();
        String senha = view.getPasswordField().getText();
        String identidade = view.getIdentityField().getText();
        String areaId = view.getAreaIdField().getText();
        String comissao = view.getComissionValueField().getText();

        String genero = view.getMasculinoRadioButton().isSelected() ? "Masculino" :
                view.getFemininoRadioButton().isSelected() ? "Feminino" : "";

        // Verificando se algum campo está vazio
        if (nome.isEmpty() || email.isEmpty() || senha.isEmpty() || identidade.isEmpty() ||
                areaId.isEmpty() || comissao.isEmpty() || genero.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Todos os campos devem ser preenchidos!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Exibindo os dados no console
        System.out.println("Nome: " + nome);
        System.out.println("Email: " + email);
        System.out.println("Senha: " + senha);
        System.out.println("Identidade: " + identidade);
        System.out.println("ID da Área: " + areaId);
        System.out.println("Comissão: " + comissao);
        System.out.println("Gênero: " + genero);

        // Exibindo uma mensagem de sucesso
        JOptionPane.showMessageDialog(view, "Técnico(a) adicionado(a) com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
    }
}
