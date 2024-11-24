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
        String campo1 = view.getTextField1().getText();
        String campo2 = view.getTextField2().getText();
        String campo3 = view.getTextField3().getText();

        if (campo1.isEmpty() || campo2.isEmpty() || campo3.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Todos os campos devem ser preenchidos!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        System.out.println("Campo 1: " + campo1);
        System.out.println("Campo 2: " + campo2);
        System.out.println("Campo 3: " + campo3);

        JOptionPane.showMessageDialog(view, "Local de estoque criado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
    }
}
