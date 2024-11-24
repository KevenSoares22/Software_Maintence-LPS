package View;

import javax.swing.*;

public class FrCreateTechnician {
    private JPanel panelMain;
    private JButton fecharButton;
    private JButton criarButton;
    private JTextField nameField;
    private JTextField emailField;
    private JTextField passwordField;
    private JTextField identityField;
    private JRadioButton masculinoRadioButton;
    private JRadioButton femininoRadioButton;
    private JTextField areaIdField;
    private JTextField comissionValueField;










    public FrCreateTechnician() {

    }


    public static void main(String[] args) {
        FrCreateTechnician syst = new FrCreateTechnician();
        syst.setContentPane(syst.panelMain);
        syst.setTitle("Safe Software");
        syst.setSize(600, 400);
        syst.setVisible(true);
        syst.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    }
}
