package View;

import javax.swing.*;

public class FrCreateStockPlace extends JFrame {
    private JButton fecharButton;
    private JButton criarButton;
    private JTextField placeField;
    private JTextField spaceField;
    private JPanel panelMain;
    private JTextField filialField;

    public FrCreateStockPlace() {
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

    public JTextField getPlaceField() {
        return placeField;
    }

    public JTextField getSpaceField() {
        return spaceField;
    }

    public JTextField getFilialField() {
        return filialField;
    }

    public static void main(String[] args) {
        FrCreateStockPlace syst = new FrCreateStockPlace();
        syst.setContentPane(syst.panelMain);
        syst.setTitle("Safe Software");
        syst.setSize(600, 400);
        syst.setVisible(true);
        syst.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
