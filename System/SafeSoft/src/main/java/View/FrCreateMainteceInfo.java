package View;

import javax.swing.*;

public class FrCreateMainteceInfo extends JFrame {
    private JPanel panelMain;
    private JTextField titleField;
    private JTextField clientIdField;
    private JTextField descriptionField;
    private JTextField timeField;
    private JTextField priceField;
    private JButton fecharButton;
    private JButton criarButton;

    public FrCreateMainteceInfo() {
        setContentPane(panelMain);
        setTitle("Safe Software");
        setSize(600, 400);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    // Métodos para acessar os campos e botões
    public JTextField getTitleField() {
        return titleField;
    }

    public JTextField getClientIdField() {
        return clientIdField;
    }

    public JTextField getDescriptionField() {
        return descriptionField;
    }

    public JTextField getTimeField() {
        return timeField;
    }

    public JTextField getPriceField() {
        return priceField;
    }

    public JButton getFecharButton() {
        return fecharButton;
    }

    public JButton getCriarButton() {
        return criarButton;
    }

    // Métodos adicionais para acessar os campos, se necessário
    public JTextField getTextField1() {
        return titleField;  // Pode ser alterado para outro campo, se necessário
    }

    public JTextField getTextField2() {
        return clientIdField;  // Pode ser alterado para outro campo, se necessário
    }

    public JTextField getTextField3() {
        return descriptionField;  // Pode ser alterado para outro campo, se necessário
    }

    public JTextField getTextField4() {
        return timeField;  // Pode ser alterado para outro campo, se necessário
    }

    public JTextField getTextField6() {
        return priceField;  // Pode ser alterado para outro campo, se necessário
    }

    public static void main(String[] args) {
        new FrCreateMainteceInfo();
    }
}
