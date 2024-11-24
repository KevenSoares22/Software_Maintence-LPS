package View;

import javax.swing.*;

public class FrCreatePiece {
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


    public static void main(String[] args) {
        FrCreatePiece syst = new FrCreatePiece();
        syst.setContentPane(syst.panelMain);
        syst.setTitle("Safe Software");
        syst.setSize(600, 400);
        syst.setVisible(true);
        syst.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    }
}
