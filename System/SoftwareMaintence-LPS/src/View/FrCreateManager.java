package View;

import javax.swing.*;

public class FrCreateManager extends JFrame {
    private JPanel panelMain;
    private JButton criarButton;
    private JButton fecharButton;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField6;
    private JTextField textField7;
    private JPasswordField passwordField1;

    public FrCreateManager() {
    }

    public JButton getCriarButton() {
        return criarButton;
    }

    public JButton getFecharButton() {
        return fecharButton;
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

    public JTextField getTextField6() {
        return textField6;
    }

    public JTextField getTextField7() {
        return textField7;
    }

    public JPasswordField getPasswordField1() {
        return passwordField1;
    }

    public static void main(String[] args) {
        FrCreateManager syst = new FrCreateManager();
        syst.setContentPane(syst.panelMain);
        syst.setTitle("Safe Software");
        syst.setSize(600, 400);
        syst.setVisible(true);
        syst.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        new Controller.FrCreateManagerController(syst);
    }
}
