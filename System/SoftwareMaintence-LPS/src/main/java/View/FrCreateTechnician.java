package View;

import javax.swing.*;

public class FrCreateTechnician extends JFrame {
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
        // Inicialização dos componentes (se necessário)
    }

    // Getters para cada componente
    public JPanel getPanelMain() {
        return panelMain;
    }

    public JButton getFecharButton() {
        return fecharButton;
    }

    public JButton getCriarButton() {
        return criarButton;
    }

    public JTextField getNameField() {
        return nameField;
    }

    public JTextField getEmailField() {
        return emailField;
    }

    public JTextField getPasswordField() {
        return passwordField;
    }

    public JTextField getIdentityField() {
        return identityField;
    }

    public JRadioButton getMasculinoRadioButton() {
        return masculinoRadioButton;
    }

    public JRadioButton getFemininoRadioButton() {
        return femininoRadioButton;
    }

    public JTextField getAreaIdField() {
        return areaIdField;
    }

    public JTextField getComissionValueField() {
        return comissionValueField;
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
