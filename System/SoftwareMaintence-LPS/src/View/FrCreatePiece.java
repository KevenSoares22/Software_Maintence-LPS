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

    }

    public JButton getFecharButton() {
        return fecharButton;
    }

    public JButton getCriarButton() {
        return criarButton;
    }

    public JTextField getTextField1() {
        return textField1;
    }

    public JTextField getTextField2() {
        return textField2;
    }

    public JTextField getTextField3() {
        return textField3;
    }

    public JTextField getTextField4() {
        return textField4;
    }

    public JTextField getTextField5() {
        return textField5;
    }

    public JTextField getTextField6() {
        return textField6;
    }

    public JTextField getTextField7() {
        return textField7;
    }

    public static void main(String[] args) {
        FrCreatePiece syst = new FrCreatePiece();
        syst.setContentPane(syst.panelMain);
        syst.setTitle("Safe Software");
        syst.setSize(600, 400);
        syst.setVisible(true);
        syst.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        new Controller.FrCreatePieceController(syst);
    }
}
