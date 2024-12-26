package View;

import javax.swing.*;

public class FrCreateTechnician {
    private JPanel panelMain;
    private JButton fecharButton;
    private JButton criarButton;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JRadioButton masculinoRadioButton;
    private JRadioButton femininoRadioButton;
    private JTextField textField5;
    private JTextField textField6;










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
