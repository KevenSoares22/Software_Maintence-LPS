package Controller;

import View.FrCreatePiece;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrCreatePieceController {
    private FrCreatePiece view;

    public FrCreatePieceController(FrCreatePiece view) {
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
        // Usando os getters corretos para acessar os campos da view
        String pieceName = view.getPieceNameField().getText();
        String quantity = view.getQuantityField().getText();
        String height = view.getHeightField().getText();
        String length = view.getLengthField().getText();
        String width = view.getWidthField().getText();
        String price = view.getPriceField().getText();
        String cost = view.getCostField().getText();

        // Verificando se algum campo está vazio
        if (pieceName.isEmpty() || quantity.isEmpty() || height.isEmpty() || length.isEmpty() ||
                width.isEmpty() || price.isEmpty() || cost.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Todos os campos devem ser preenchidos!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Exibindo os dados no console (ou implementando lógica adicional)
        System.out.println("Nome da peça: " + pieceName);
        System.out.println("Quantidade: " + quantity);
        System.out.println("Altura: " + height);
        System.out.println("Comprimento: " + length);
        System.out.println("Largura: " + width);
        System.out.println("Preço: " + price);
        System.out.println("Custo: " + cost);

        // Exibindo uma mensagem de sucesso
        JOptionPane.showMessageDialog(view, "Peça criada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
    }
}
