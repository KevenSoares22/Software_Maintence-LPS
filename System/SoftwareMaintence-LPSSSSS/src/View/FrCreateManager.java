package View;

import javax.swing.*;

public class FrCreateManager {
    private JPanel panelMain;
    private JButton criarButton;
    private JButton fecharButton;
    private JTextField emailField;
    private JTextField nameField;
    private JTextField idetityField;
    private JTextField passwordField;
    private JTextField salaryField;
    private JPasswordField filialField;














    public FrCreateManager() {

    }


    public static void main(String[] args) {
        FrCreateManager syst = new FrCreateManager();
        syst.setContentPane(syst.panelMain);
        syst.setTitle("Safe Software");
        syst.setSize(600, 400);
        syst.setVisible(true);
        syst.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    }
}
