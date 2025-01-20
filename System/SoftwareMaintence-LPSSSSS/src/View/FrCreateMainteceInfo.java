package View;

import javax.swing.*;

public class FrCreateMainteceInfo {
    private JPanel panelMain;
    private JTextField titleField;
    private JTextField clientIdField;
    private JTextField descriptionField;
    private JTextField timeField;
    private JTextField priceField;
    private JButton fecharButton;
    private JButton criarButton;














    public FrCreateMainteceInfo() {

    }


    public static void main(String[] args) {
        FrCreateMainteceInfo syst = new FrCreateMainteceInfo();
        syst.setContentPane(syst.panelMain);
        syst.setTitle("Safe Software");
        syst.setSize(600, 400);
        syst.setVisible(true);
        syst.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    }
}
