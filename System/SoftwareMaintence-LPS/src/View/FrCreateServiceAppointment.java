package View;

import javax.swing.*;

public class FrCreateServiceAppointment extends JFrame {
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

    public JTextField getTextField4() {
        return textField4;
    }

    public JTextField getTextField5() {
        return textField5;
    }

    public static void main(String[] args) {
        FrCreateServiceAppointment syst = new FrCreateServiceAppointment();
        syst.setContentPane(syst.panelMain);
        syst.setTitle("Safe Software");
        syst.setSize(600, 400);
        syst.setVisible(true);
        syst.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        new Controller.FrCreateServiceAppointmentController(syst);
    }
}
