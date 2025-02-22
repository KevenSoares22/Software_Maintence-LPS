package com.ifcolab.safesoft.view;

import com.ifcolab.safesoft.controller.AutenticacaoController;
import com.ifcolab.safesoft.model.Pessoa;
import javax.swing.JOptionPane;

public class DlgTrocarSenha extends javax.swing.JDialog {

    
    private final AutenticacaoController controller;
    
    public DlgTrocarSenha(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        
        this.setLocationRelativeTo(null);
        this.setTitle("Trocar Senha");
        controller = new AutenticacaoController();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlBackground = new javax.swing.JPanel();
        lblSubtituloGerenciaTecnicos = new javax.swing.JLabel();
        lblTitleGerenciaTecnicos = new javax.swing.JLabel();
        lblNome = new javax.swing.JLabel();
        lblTelefone = new javax.swing.JLabel();
        lblTelefone1 = new javax.swing.JLabel();
        btnTrocarSenha = new com.ifcolab.safesoft.components.PrimaryCustomButton();
        btnCancelar = new com.ifcolab.safesoft.components.SecondaryCustomButton();
        edtConfirmarSenha = new com.ifcolab.safesoft.components.CustomPasswordField();
        edtNovaSenha = new com.ifcolab.safesoft.components.CustomPasswordField();
        edtSenhaAtual = new com.ifcolab.safesoft.components.CustomPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setMinimumSize(new java.awt.Dimension(800, 550));
        getContentPane().setLayout(null);

        pnlBackground.setBackground(new java.awt.Color(255, 255, 255));
        pnlBackground.setLayout(null);

        lblSubtituloGerenciaTecnicos.setFont(new java.awt.Font("Segoe UI Medium", 0, 13)); // NOI18N
        lblSubtituloGerenciaTecnicos.setForeground(new java.awt.Color(102, 102, 102));
        lblSubtituloGerenciaTecnicos.setText("Troque sua senha e fique ainda mais seguro.");
        pnlBackground.add(lblSubtituloGerenciaTecnicos);
        lblSubtituloGerenciaTecnicos.setBounds(30, 40, 550, 17);

        lblTitleGerenciaTecnicos.setFont(new java.awt.Font("Segoe UI SemiBold", 0, 18)); // NOI18N
        lblTitleGerenciaTecnicos.setForeground(new java.awt.Color(51, 51, 51));
        lblTitleGerenciaTecnicos.setText("Modifique sua senha");
        pnlBackground.add(lblTitleGerenciaTecnicos);
        lblTitleGerenciaTecnicos.setBounds(30, 20, 210, 22);

        lblNome.setForeground(new java.awt.Color(51, 51, 51));
        lblNome.setText("Senha Atual");
        pnlBackground.add(lblNome);
        lblNome.setBounds(170, 120, 160, 17);

        lblTelefone.setForeground(new java.awt.Color(51, 51, 51));
        lblTelefone.setText("Nova Senha");
        pnlBackground.add(lblTelefone);
        lblTelefone.setBounds(170, 190, 260, 17);

        lblTelefone1.setForeground(new java.awt.Color(51, 51, 51));
        lblTelefone1.setText("Confirme sua Senha");
        pnlBackground.add(lblTelefone1);
        lblTelefone1.setBounds(170, 260, 260, 17);

        btnTrocarSenha.setText("Trocar Senha");
        btnTrocarSenha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTrocarSenhaActionPerformed(evt);
            }
        });
        pnlBackground.add(btnTrocarSenha);
        btnTrocarSenha.setBounds(420, 370, 200, 38);

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        pnlBackground.add(btnCancelar);
        btnCancelar.setBounds(160, 370, 200, 38);
        pnlBackground.add(edtConfirmarSenha);
        edtConfirmarSenha.setBounds(160, 280, 460, 40);
        pnlBackground.add(edtNovaSenha);
        edtNovaSenha.setBounds(160, 210, 460, 40);
        pnlBackground.add(edtSenhaAtual);
        edtSenhaAtual.setBounds(160, 140, 460, 40);

        getContentPane().add(pnlBackground);
        pnlBackground.setBounds(0, 0, 800, 550);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnTrocarSenhaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTrocarSenhaActionPerformed
        try {
            validarTrocaSenha();
            JOptionPane.showMessageDialog(this, "Senha alterada com sucesso!");
            this.dispose();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }//GEN-LAST:event_btnTrocarSenhaActionPerformed

    private void validarTrocaSenha() throws Exception {
        String senhaAtual = new String(edtSenhaAtual.getPassword());
        String novaSenha = new String(edtNovaSenha.getPassword());
        String confirmarSenha = new String(edtConfirmarSenha.getPassword());

        if (senhaAtual.isEmpty() || novaSenha.isEmpty() || confirmarSenha.isEmpty()) {
            throw new Exception("Todos os campos são obrigatórios!");
        }

        if (!novaSenha.equals(confirmarSenha)) {
            throw new Exception("A nova senha e a confirmação não coincidem!");
        }
        
        Pessoa usuario = controller.getUsuarioLogado();
        controller.atualizarSenhaUsuario(usuario.getId(), novaSenha);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.ifcolab.safesoft.components.SecondaryCustomButton btnCancelar;
    private com.ifcolab.safesoft.components.PrimaryCustomButton btnTrocarSenha;
    private com.ifcolab.safesoft.components.CustomPasswordField edtConfirmarSenha;
    private com.ifcolab.safesoft.components.CustomPasswordField edtNovaSenha;
    private com.ifcolab.safesoft.components.CustomPasswordField edtSenhaAtual;
    private javax.swing.JLabel lblNome;
    private javax.swing.JLabel lblSubtituloGerenciaTecnicos;
    private javax.swing.JLabel lblTelefone;
    private javax.swing.JLabel lblTelefone1;
    private javax.swing.JLabel lblTitleGerenciaTecnicos;
    private javax.swing.JPanel pnlBackground;
    // End of variables declaration//GEN-END:variables
}
