package View;

import javax.swing.*;

public class FrCreateClient {
    private JPanel panelMain;
    private JButton fecharButton;
    private JButton cadastrarButton1;
    private JTextField nameField;
    private JTextField identityField;
    private JPasswordField passwordField;
    private JTextField emailField;
    private JRadioButton masculinoRadioButton;
    private JRadioButton femininoRadioButton;














    public FrCreateClient() {

    }


    public static void main(String[] args) {
        FrCreateClient syst = new FrCreateClient();
        syst.setContentPane(syst.panelMain);
        syst.setTitle("Safe Software");
        syst.setSize(600, 400);
        syst.setVisible(true);
        syst.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    }
}
