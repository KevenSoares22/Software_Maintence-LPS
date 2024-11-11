package View;

import javax.swing.*;

public class FrCreateStockPlace {
    private JButton fecharButton;
    private JButton criarButton;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JPanel panelMain;


















    public FrCreateStockPlace() {

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
