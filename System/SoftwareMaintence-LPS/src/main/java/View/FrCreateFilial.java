package View;

import javax.swing.*;

public class FrCreateFilial extends JFrame {
    private JButton fecharButton;
    private JButton criarButton;
    private JTextField nameField;
    private JTextField placeField;
    private JPasswordField passwordField;  // Correção do tipo
    private JPanel panelMain;

    public FrCreateFilial() {
        fecharButton = new JButton("Fechar");
        criarButton = new JButton("Criar");

        // Configuração inicial da interface
        setContentPane(panelMain);
        setTitle("Safe Software");
        setSize(600, 400);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    // Métodos de acesso aos botões
    public JButton getFecharButton() {
        return fecharButton;
    }

    public JButton getCriarButton() {
        return criarButton;
    }

    public JTextField getTextField1() {
        return nameField;
    }

    public JTextField getTextField2() {
        return placeField;
    }

    public JPasswordField getPasswordField1() {
        return passwordField;
    }

    public static void main(String[] args) {
        new FrCreateFilial();
    }
}
