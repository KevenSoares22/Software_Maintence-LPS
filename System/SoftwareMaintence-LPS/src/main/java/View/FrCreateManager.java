package View;

import javax.swing.*;

public class FrCreateManager extends JFrame {
    private JPanel panelMain;
    private JButton criarButton;
    private JButton fecharButton;
    private JTextField emailField;
    private JTextField nameField;
    private JTextField idetityField;
    private JPasswordField passwordField;
    private JTextField salaryField;
    private JTextField filialField;

    public FrCreateManager() {
        // Inicialize seus componentes e layout, se necessário
    }

    // Getters para cada componente
    public JPanel getPanelMain() {
        return panelMain;
    }

    public JButton getCriarButton() {
        return criarButton;
    }

    public JButton getFecharButton() {
        return fecharButton;
    }

    public JTextField getEmailField() {
        return emailField;
    }

    public JTextField getNameField() {
        return nameField;
    }

    public JTextField getIdetityField() {
        return idetityField;
    }

    public JPasswordField getPasswordField() {
        return passwordField;
    }

    public JTextField getSalaryField() {
        return salaryField;
    }

    public JTextField getFilialField() {
        return filialField;
    }

    public static void main(String[] args) {
        FrCreateManager syst = new FrCreateManager();
        syst.setContentPane(syst.panelMain);
        syst.setTitle("Safe Software");
        syst.setSize(600, 400);
        syst.setVisible(true);
        syst.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
