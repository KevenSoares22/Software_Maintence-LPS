package View;

import javax.swing.*;

public class FrLogin extends JFrame {
    private JButton loginBtn;
    private JTextField emailField;
    private JPasswordField passwordField;
    private JLabel title;
    private JLabel emailLabel;
    private JLabel passwordLabel;
    private JCheckBox clientCheckBox;
    private JPanel panelMain;

    public FrLogin() {
    }

    public JButton getLoginBtn() {
        return loginBtn;
    }

    public JTextField getEmailField() {
        return emailField;
    }

    public JPasswordField getPasswordField() {
        return passwordField;
    }

    public JCheckBox getClientCheckBox() {
        return clientCheckBox;
    }

    public static void main(String[] args) {
        FrLogin syst = new FrLogin();
        syst.setContentPane(syst.panelMain);
        syst.setTitle("Safe Software");
        syst.setSize(600, 400);
        syst.setVisible(true);
        syst.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        new Controller.FrLoginController(syst);
    }
}
