package com.ifcolab.safesoft.view;

import com.ifcolab.safesoft.controller.AutenticacaoController;
import javax.swing.JOptionPane;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class DlgEsqueceuSenha extends javax.swing.JDialog {

    private final AutenticacaoController autenticacaoController;

    public DlgEsqueceuSenha(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        this.setLocationRelativeTo(null);
        this.setTitle("Recuperar Senha");
        autenticacaoController = new AutenticacaoController();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        edtEmail = new com.ifcolab.safesoft.components.CustomTextField();
        btnRecuperar = new com.ifcolab.safesoft.components.PrimaryCustomButton();
        btnCancelar = new com.ifcolab.safesoft.components.SecondaryCustomButton();
        jLabel6 = new javax.swing.JLabel();
        lblTitutoSidebar = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1370, 870));
        setPreferredSize(new java.awt.Dimension(1350, 870));
        getContentPane().setLayout(null);

        edtEmail.setBounds(490, 340, 360, 40);
        getContentPane().add(edtEmail);

        btnRecuperar.setText("Recuperar Conta");
        btnRecuperar.setBounds(570, 420, 200, 30);
        btnRecuperar.addActionListener(this::btnRecuperarActionPerformed);
        getContentPane().add(btnRecuperar);

        btnCancelar.setText("Cancelar");
        btnCancelar.setBounds(570, 470, 200, 30);
        btnCancelar.addActionListener(this::btnCancelarActionPerformed);
        getContentPane().add(btnCancelar);

        jLabel6.setFont(new java.awt.Font("Fira Sans", 0, 12));
        jLabel6.setForeground(new java.awt.Color(51, 51, 51));
        jLabel6.setText("E-mail");
        jLabel6.setBounds(490, 320, 110, 16);
        getContentPane().add(jLabel6);

        lblTitutoSidebar.setFont(new java.awt.Font("Fira Sans SemiBold", 0, 18));
        lblTitutoSidebar.setForeground(new java.awt.Color(0, 0, 0));
        lblTitutoSidebar.setText("Recupere sua senha fornecendo seu e-mail");
        lblTitutoSidebar.setBounds(490, 270, 380, 20);
        getContentPane().add(lblTitutoSidebar);

        // Removed background image components
        // lblBackgroundSidebar and lblBackground have been removed

        pack();
    }

    private void btnRecuperarActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            String email = edtEmail.getText().trim();

            if (email.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor, digite seu email!");
                return;
            }

            autenticacaoController.recuperarSenha(email);

            JOptionPane.showMessageDialog(this,
                    "Uma nova senha temporária foi enviada para seu email!\n" +
                            "Por favor, verifique sua caixa de entrada.");
            this.dispose();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                    "Erro ao recuperar senha: " + ex.getMessage());
        }
    }

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {
        this.dispose();
    }

    private com.ifcolab.safesoft.components.SecondaryCustomButton btnCancelar;
    private com.ifcolab.safesoft.components.PrimaryCustomButton btnRecuperar;
    private com.ifcolab.safesoft.components.CustomTextField edtEmail;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel lblTitutoSidebar;
}
