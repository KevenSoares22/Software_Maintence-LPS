package View;

import javax.swing.*;

public class FrCreatePiece {
    private JPanel panelMain;
    private JButton fecharButton;
    private JButton criarButton;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JTextField textField6;
    private JTextField textField7;













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
