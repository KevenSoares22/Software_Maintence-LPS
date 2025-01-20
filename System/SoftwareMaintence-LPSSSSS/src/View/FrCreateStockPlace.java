package View;

import javax.swing.*;

public class FrCreateStockPlace extends JFrame {
    private JButton fecharButton;
    private JButton criarButton;
    private JTextField placeField;
    private JTextField spaceField;
    private JPanel panelMain;


    public FrCreateStockPlace() {
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

    public static void main(String[] args) {
        FrCreateStockPlace syst = new FrCreateStockPlace();
        syst.setContentPane(syst.panelMain);
        syst.setTitle("Safe Software");
        syst.setSize(600, 400);
        syst.setVisible(true);
        syst.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        new Controller.FrCreateStockPlaceController(syst);
    }
}
