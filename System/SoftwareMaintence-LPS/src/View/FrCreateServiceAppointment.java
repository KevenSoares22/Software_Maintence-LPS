package View;

import javax.swing.*;

public class FrCreateServiceAppointment {
    private JPanel panelMain;
    private JButton criarButton;
    private JButton fecharButton;
    private JTextField clientField;
    private JTextField descriptionField;
    private JTextField timeField;
    private JTextField priceField;
    private JTextField areaIdField;















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
