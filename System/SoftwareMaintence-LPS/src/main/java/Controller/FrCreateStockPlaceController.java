package Controller;

import View.FrCreateStockPlace;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrCreateStockPlaceController {
    private FrCreateStockPlace view;

    public FrCreateStockPlaceController(FrCreateStockPlace view) {
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
        // Usando os getters corretos para acessar os campos
        String place = view.getPlaceField().getText();
        String space = view.getSpaceField().getText();
        String filial = view.getFilialField().getText();

        // Verificando se algum campo está vazio
        if (place.isEmpty() || space.isEmpty() || filial.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Todos os campos devem ser preenchidos!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Exibindo os dados no console
        System.out.println("Local: " + place);
        System.out.println("Espaço: " + space);
        System.out.println("Filial: " + filial);

        // Exibindo uma mensagem de sucesso
        JOptionPane.showMessageDialog(view, "Local de estoque criado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
    }
}
