package View;

import javax.swing.*;

public class FrCreateMainteceInfo {
    private JPanel panelMain;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField6;
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
