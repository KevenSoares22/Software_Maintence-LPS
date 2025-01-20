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
        String nome = view.getTextField1().getText();
        String sobrenome = view.getTextField2().getText();
        String cpf = view.getTextField3().getText();
        String telefone = view.getTextField4().getText();
        String endereco = view.getTextField5().getText();
        String especializacao = view.getTextField6().getText();
        String genero = view.getMasculinoRadioButton().isSelected() ? "Masculino" :
                view.getFemininoRadioButton().isSelected() ? "Feminino" : "";

        if (nome.isEmpty() || sobrenome.isEmpty() || cpf.isEmpty() || telefone.isEmpty() ||
                endereco.isEmpty() || especializacao.isEmpty() || genero.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Todos os campos devem ser preenchidos!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        System.out.println("Nome: " + nome);
        System.out.println("Sobrenome: " + sobrenome);
        System.out.println("CPF: " + cpf);
        System.out.println("Telefone: " + telefone);
        System.out.println("Endereço: " + endereco);
        System.out.println("Especialização: " + especializacao);
        System.out.println("Gênero: " + genero);

        JOptionPane.showMessageDialog(view, "Técnico(a) adicionado(a) com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
    }
}
