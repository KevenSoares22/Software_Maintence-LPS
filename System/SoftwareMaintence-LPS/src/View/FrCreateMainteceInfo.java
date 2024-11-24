package View;

import javax.swing.*;

public class FrCreateMainteceInfo extends JFrame {
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

    public JButton getFecharButton() {
        return fecharButton;
    }

    public JButton getCriarButton() {
        return criarButton;
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

    public JTextField getTextField6() {
        return textField6;
    }

    public static void main(String[] args) {
        FrCreateMainteceInfo syst = new FrCreateMainteceInfo();
        syst.setContentPane(syst.panelMain);
        syst.setTitle("Safe Software");
        syst.setSize(600, 400);
        syst.setVisible(true);
        syst.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        new Controller.FrCreateMainteceInfoController(syst);
    }
}

