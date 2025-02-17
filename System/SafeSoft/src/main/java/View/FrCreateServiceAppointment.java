package View;

import javax.swing.*;

public class FrCreateServiceAppointment extends JFrame {
    private JPanel panelMain;
    private JButton criarButton;
    private JButton fecharButton;
    private JTextField clientField;
    private JTextField descriptionField;
    private JTextField timeField;
    private JTextField priceField;
    private JTextField areaIdField;

    public FrCreateServiceAppointment() {
        // Inicialização dos componentes (se necessário)
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

    public JTextField getClientField() {
        return clientField;
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

    public JTextField getAreaIdField() {
        return areaIdField;
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
