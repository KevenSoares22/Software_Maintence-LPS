package View;

import javax.swing.*;

public class FrCreatePiece extends JFrame {
    private JPanel panelMain;
    private JButton fecharButton;
    private JButton criarButton;
    private JTextField pieceNameField;
    private JTextField quantityField;
    private JTextField heightField;
    private JTextField lengthField;
    private JTextField widthField;
    private JTextField priceField;
    private JTextField costField;

    public FrCreatePiece() {
        // Inicialização dos componentes (se necessário)
    }

    // Getters para cada componente
    public JPanel getPanelMain() {
        return panelMain;
    }

    public JButton getFecharButton() {
        return fecharButton;
    }

    public JButton getCriarButton() {
        return criarButton;
    }

    public JTextField getPieceNameField() {
        return pieceNameField;
    }

    public JTextField getQuantityField() {
        return quantityField;
    }

    public JTextField getHeightField() {
        return heightField;
    }

    public JTextField getLengthField() {
        return lengthField;
    }

    public JTextField getWidthField() {
        return widthField;
    }

    public JTextField getPriceField() {
        return priceField;
    }

    public JTextField getCostField() {
        return costField;
    }

    public static void main(String[] args) {
        FrCreatePiece syst = new FrCreatePiece();
        syst.setContentPane(syst.panelMain);
        syst.setTitle("Safe Software");
        syst.setSize(600, 400);
        syst.setVisible(true);
        syst.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
