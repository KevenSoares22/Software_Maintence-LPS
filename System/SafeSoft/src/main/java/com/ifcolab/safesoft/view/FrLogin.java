package com.ifcolab.safesoft.view;

import com.ifcolab.safesoft.controller.AutenticacaoController;
import com.ifcolab.safesoft.model.exceptions.AutenticacaoException;

import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.Color;

public class FrLogin extends javax.swing.JFrame {

    private final AutenticacaoController controller;

    public FrLogin() {
        initComponents();
        this.setLocationRelativeTo(null);
        controller = new AutenticacaoController();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        edtSenha = new com.ifcolab.safesoft.components.CustomPasswordField();
        edtEmail = new com.ifcolab.safesoft.components.CustomTextField();
        btnEntrar = new com.ifcolab.safesoft.components.PrimaryCustomButton();
        lblEsqueceuSenha = new javax.swing.JLabel();
        lblLine = new javax.swing.JLabel();
        lblSafeSoft = new javax.swing.JLabel();
        lblBackground = new javax.swing.JLabel();

        jLabel5.setFont(new java.awt.Font("Fira Sans", 0, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(51, 51, 51));
        jLabel5.setText("Senha");

        jLabel6.setFont(new java.awt.Font("Fira Sans", 0, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(51, 51, 51));
        jLabel6.setText("E-mail");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1350, 870));
        setResizable(false);
        getContentPane().setLayout(null);

        // Centralizando o conteúdo na tela
        int frameWidth = getWidth();
        int frameHeight = getHeight();
        int formWidth = 400;  // Largura do formulário (pode ajustar conforme necessário)
        int formHeight = 350; // Altura do formulário (pode ajustar conforme necessário)

        // Definindo a posição central
        int x = (frameWidth - formWidth) / 2;
        int y = (frameHeight - formHeight) / 2;

        // Definindo os campos de email e senha
        edtEmail.setBounds(x + 20, y + 80, 360, 40);
        getContentPane().add(edtEmail);

        jLabel6.setBounds(x + 20, y + 60, 110, 16);
        getContentPane().add(jLabel6);

        edtSenha.setBounds(x + 20, y + 160, 360, 38);
        getContentPane().add(edtSenha);

        jLabel5.setBounds(x + 20, y + 140, 70, 16);
        getContentPane().add(jLabel5);

        btnEntrar.setText("Entrar");
        btnEntrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEntrarActionPerformed(evt);
            }
        });
        btnEntrar.setBounds(x + 120, y + 220, 140, 30);
        getContentPane().add(btnEntrar);

        // Movendo o "Esqueceu sua senha?" ligeiramente para a direita
        lblEsqueceuSenha.setForeground(new java.awt.Color(51, 51, 51));
        lblEsqueceuSenha.setText("Esqueceu sua senha?");
        lblEsqueceuSenha.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblEsqueceuSenhaMouseClicked(evt);
            }
        });
        lblEsqueceuSenha.setBounds(x + 140, y + 260, 200, 20); // Movido ligeiramente para a direita
        getContentPane().add(lblEsqueceuSenha);

        lblLine.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Line.png"))); // NOI18N
        lblLine.setBounds(x - 20, y + 300, 390, 40);
        getContentPane().add(lblLine);

        // Adicionando o nome "SafeSoft" em cima, com fonte bonita (Lobster)
        lblSafeSoft.setText("SafeSoft");
        try {
            // Tentando usar a fonte Lobster
            Font lobsterFont = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("/fonts/Lobster-Regular.ttf"));
            lobsterFont = lobsterFont.deriveFont(60f);  // Ajuste o tamanho
            lblSafeSoft.setFont(lobsterFont);
        } catch (Exception e) {
            // Se não conseguir carregar a fonte, usa uma fonte padrão
            lblSafeSoft.setFont(new Font("Fira Sans", Font.BOLD, 60));
        }

        lblSafeSoft.setForeground(new Color(0, 51, 102)); // Cor azul escuro
        lblSafeSoft.setBounds(x + 100, y - 60, 350, 80); // Ajuste de posição para ficar um pouco mais para baixo
        getContentPane().add(lblSafeSoft);

        lblBackground.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/background.png"))); // NOI18N
        lblBackground.setText("jLabel3");
        lblBackground.setBounds(0, 0, 1350, 870);
        getContentPane().add(lblBackground);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void edtEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_edtEmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_edtEmailActionPerformed

    private void btnEntrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEntrarActionPerformed
        try {
            String email = edtEmail.getText().trim();
            String senha = new String(edtSenha.getPassword());

            // Validações básicas
            if (email.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "Por favor, informe o email",
                        "Campo obrigatório",
                        JOptionPane.WARNING_MESSAGE);
                edtEmail.requestFocus();
                return;
            }

            if (senha.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "Por favor, informe a senha",
                        "Campo obrigatório",
                        JOptionPane.WARNING_MESSAGE);
                edtSenha.requestFocus();
                return;
            }

            // Tenta realizar o login
            controller.login(email, senha);

            // Se chegou aqui, login foi bem sucedido
            // Abre o menu principal
            FrMenu telaMenu = new FrMenu();
            telaMenu.setVisible(true);
            this.dispose(); // Fecha a tela de login

            // Limpa os campos
            edtEmail.setText("");
            edtSenha.setText("");

        } catch (AutenticacaoException ex) {
            JOptionPane.showMessageDialog(this,
                    ex.getMessage(),
                    "Erro de autenticação",
                    JOptionPane.ERROR_MESSAGE);
            edtSenha.setText("");
            edtSenha.requestFocus();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                    "Erro ao realizar login: " + ex.getMessage(),
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_btnEntrarActionPerformed

    private void lblEsqueceuSenhaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblEsqueceuSenhaMouseClicked
        DlgEsqueceuSenha dlg = new DlgEsqueceuSenha(null, true);
        dlg.setVisible(true);
    }//GEN-LAST:event_lblEsqueceuSenhaMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.ifcolab.safesoft.components.PrimaryCustomButton btnEntrar;
    private com.ifcolab.safesoft.components.CustomTextField edtEmail;
    private com.ifcolab.safesoft.components.CustomPasswordField edtSenha;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel lblBackground;
    private javax.swing.JLabel lblEsqueceuSenha;
    private javax.swing.JLabel lblLine;
    private javax.swing.JLabel lblSafeSoft;
    // End of variables declaration//GEN-END:variables
}
