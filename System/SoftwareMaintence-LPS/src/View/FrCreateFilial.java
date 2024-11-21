package View;

import javax.swing.*;

public class FrCreateFilial extends JFrame {
    private JButton fecharButton;
    private JButton criarButton;
    private JTextField textField1;
    private JTextField textField2;
    private JPanel panelMain;
    private JPasswordField passwordField1;

    public FrCreateFilial() {

    }
    public static void main(String[] args) {
        FrCreateFilial syst = new FrCreateFilial();
        syst.setContentPane(syst.panelMain);
        syst.setTitle("Safe Software");
        syst.setSize(600, 400);
        syst.setVisible(true);
        syst.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
