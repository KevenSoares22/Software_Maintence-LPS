package View;

import javax.swing.*;

public class FrCreateServiceAppointment {
    private JPanel panelMain;
    private JButton criarButton;
    private JButton fecharButton;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;















    public FrCreateServiceAppointment() {

    }


    public static void main(String[] args) {
        FrCreateServiceAppointment syst = new FrCreateServiceAppointment();
        syst.setContentPane(syst.panelMain);
        syst.setTitle("Safe Software");
        syst.setSize(600, 400);
        syst.setVisible(true);
        syst.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    }
}
