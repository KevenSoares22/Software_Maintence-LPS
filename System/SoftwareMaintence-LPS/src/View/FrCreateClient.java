package View;

import javax.swing.*;

public class FrCreateClient {
    private JPanel panelMain;
    private JButton fecharButton;
    private JButton cadastrarButton1;
    private JTextField textField1;
    private JTextField textField2;
    private JPasswordField passwordField1;
    private JTextField textField3;
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
