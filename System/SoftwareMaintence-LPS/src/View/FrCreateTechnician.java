package View;

import javax.swing.*;

public class FrCreateTechnician extends JFrame {
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
    private ButtonGroup genderGroup;

    public FrCreateTechnician() {
        genderGroup = new ButtonGroup();
        genderGroup.add(masculinoRadioButton);
        genderGroup.add(femininoRadioButton);
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

    public JTextField getTextField5() {
        return textField5;
    }

    public JTextField getTextField6() {
        return textField6;
    }

    public JRadioButton getMasculinoRadioButton() {
        return masculinoRadioButton;
    }

    public JRadioButton getFemininoRadioButton() {
        return femininoRadioButton;
    }

    public static void main(String[] args) {
        FrCreateTechnician syst = new FrCreateTechnician();
        syst.setContentPane(syst.panelMain);
        syst.setTitle("Safe Software");
        syst.setSize(600, 400);
        syst.setVisible(true);
        syst.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        new Controller.FrCreateTechnicianController(syst);
    }
}
