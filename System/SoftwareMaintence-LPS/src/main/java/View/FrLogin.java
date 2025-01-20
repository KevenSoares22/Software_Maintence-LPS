package View;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrLogin extends JFrame{
    private JButton loginBtn;
    private JTextField emailField;
    private JPasswordField passwordField;
    private JLabel title;
    private JLabel emailLabel;
    private JLabel passwordLabel;
    private JCheckBox clienteCheckBox;
    private JPanel panelMain;
    private JCheckBox cliientCheckBox;

    public FrLogin() {
        loginBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(loginBtn, "Hello ");
            }
        });

    }


    public static void main(String[] args) {
        FrLogin syst = new FrLogin();
        syst.setContentPane(syst.panelMain);
        syst.setTitle("Safe Software");
        syst.setSize(600, 400);
        syst.setVisible(true);
        syst.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    }
}
