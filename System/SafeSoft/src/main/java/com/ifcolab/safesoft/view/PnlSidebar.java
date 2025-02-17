package com.ifcolab.safesoft.view;

import com.ifcolab.safesoft.controller.AutenticacaoController;
import javax.swing.JFrame;

public class PnlSidebar extends javax.swing.JPanel {

    private final AutenticacaoController authController;
    private JFrame parentFrame;

    public PnlSidebar() {
        initComponents();

        authController = new AutenticacaoController();
    }

    public void setParentFrame(JFrame parent) {
        this.parentFrame = parent;
        configurarVisualizacao();
    }

     private void configurarVisualizacao() {
        // Esconde todos os botões inicialmente
        btnGerenciarTecnicos.setVisible(false);
        btnGerenciarSuportes.setVisible(false);
        btnGerenciaClientes.setVisible(false);
        btnGerenciarRecepcionistas.setVisible(false);
        btnGerenciarItens.setVisible(false);
        btnHistoricoItem.setVisible(false);
        btnFeedbackCliente.setVisible(false);
        btnSair.setVisible(true);
        btnConfiguracoes.setVisible(false);
        btnMeusServicos.setVisible(false);
        btnFeedbacks.setVisible(false);
        btnFeedbacksAdmin.setVisible(false);
        btnGerenciarAdmins.setVisible(false);
        btnAgenda.setVisible(false);
        btnPerfil.setVisible(false);
        btnGerenciarPagamentos.setVisible(false);
        lblItens.setVisible(false);


        // Mostra botões baseado no tipo de usuário
        if (authController.isAdmin()) {
            btnAgenda.setVisible(true);
            btnPerfil.setVisible(true);
            btnGerenciarTecnicos.setVisible(true);
            btnGerenciarSuportes.setVisible(true);
            btnGerenciaClientes.setVisible(true);
            btnGerenciarRecepcionistas.setVisible(true);
            btnGerenciarItens.setVisible(true);
            btnHistoricoItem.setVisible(true);
            btnGerenciarPagamentos.setVisible(true);
            btnGerenciarAdmins.setVisible(true);
            btnConfiguracoes.setVisible(true);
            btnFeedbacksAdmin.setVisible(true);
            btnGerenciarPagamentos.setVisible(true);
        }
        else if (authController.isCliente()) {
            btnMeusServicos.setVisible(true);
            btnFeedbackCliente.setVisible(true);
            btnPerfil.setVisible(true);
        }
        else if (authController.isTecnico() || authController.isSuporte()) {
            btnAgenda.setVisible(true);
            btnPerfil.setVisible(true);
            btnFeedbacks.setVisible(true);
            btnGerenciarItens.setVisible(true);
            btnHistoricoItem.setVisible(true);
            lblItens.setVisible(true);
        }
        else if (authController.isRecepcionista()) {
            btnAgenda.setVisible(true);
            btnGerenciaClientes.setVisible(true);
            btnPerfil.setVisible(true);
            btnFeedbacks.setVisible(true);
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLayeredPane1 = new javax.swing.JLayeredPane();
        lblLogo = new javax.swing.JLabel();
        lblSafeSoft = new javax.swing.JLabel();
        btnAgenda = new com.ifcolab.safesoft.components.CustomButton();
        btnConfiguracoes = new com.ifcolab.safesoft.components.CustomButton();
        btnGerenciaClientes = new com.ifcolab.safesoft.components.CustomButton();
        btnGerenciarTecnicos = new com.ifcolab.safesoft.components.CustomButton();
        btnPerfil = new com.ifcolab.safesoft.components.CustomButton();
        btnGerenciarSuportes = new com.ifcolab.safesoft.components.CustomButton();
        btnMeusServicos = new com.ifcolab.safesoft.components.CustomButton();
        btnFeedbackCliente = new com.ifcolab.safesoft.components.CustomButton();
        btnFeedbacks = new com.ifcolab.safesoft.components.CustomButton();
        btnSair = new com.ifcolab.safesoft.components.CustomButton();
        btnFeedbacksAdmin = new com.ifcolab.safesoft.components.CustomButton();
        btnGerenciarItens = new com.ifcolab.safesoft.components.CustomButton();
        btnGerenciarRecepcionistas = new com.ifcolab.safesoft.components.CustomButton();
        btnGerenciarPagamentos = new com.ifcolab.safesoft.components.CustomButton();
        btnGerenciarAdmins = new com.ifcolab.safesoft.components.CustomButton();
        btnHistoricoItem = new com.ifcolab.safesoft.components.CustomButton();
        lblItens = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(null);

        add(lblLogo);
        lblLogo.setBounds(20, 10, 50, 50);

        lblSafeSoft.setFont(new java.awt.Font("Segoe UI SemiBold", 0, 18)); // NOI18N
        lblSafeSoft.setForeground(new java.awt.Color(0, 0, 0));
        lblSafeSoft.setText("SafeSoft");
        add(lblSafeSoft);
        lblSafeSoft.setBounds(70, 30, 130, 20);

        btnAgenda.setText("Agenda");
        btnAgenda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgendaActionPerformed(evt);
            }
        });
        add(btnAgenda);
        btnAgenda.setBounds(10, 90, 230, 40);

        btnConfiguracoes.setText("Configurações");
        btnConfiguracoes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfiguracoesActionPerformed(evt);
            }
        });
        add(btnConfiguracoes);
        btnConfiguracoes.setBounds(10, 190, 230, 40);

        btnGerenciaClientes.setText("GerenciarClientes");
        btnGerenciaClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGerenciaClientesActionPerformed(evt);
            }
        });
        add(btnGerenciaClientes);
        btnGerenciaClientes.setBounds(10, 240, 230, 40);

        btnGerenciarTecnicos.setText("Gerenciar Tecnicos");
        btnGerenciarTecnicos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGerenciarTecnicosActionPerformed(evt);
            }
        });
        add(btnGerenciarTecnicos);
        btnGerenciarTecnicos.setBounds(10, 390, 230, 40);

        btnPerfil.setText("Perfil");
        btnPerfil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPerfilActionPerformed(evt);
            }
        });
        add(btnPerfil);
        btnPerfil.setBounds(10, 140, 230, 40);

        btnGerenciarSuportes.setText("Gerenciar Suportes");
        btnGerenciarSuportes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGerenciarSuportesActionPerformed(evt);
            }
        });
        add(btnGerenciarSuportes);
        btnGerenciarSuportes.setBounds(10, 440, 230, 40);

        btnMeusServicos.setText("Minhas Servicos");
        btnMeusServicos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMeusServicosActionPerformed(evt);
            }
        });
        add(btnMeusServicos);
        btnMeusServicos.setBounds(10, 90, 230, 40);

        btnFeedbackCliente.setText("Feedbacks");
        btnFeedbackCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFeedbackClienteActionPerformed(evt);
            }
        });
        add(btnFeedbackCliente);
        btnFeedbackCliente.setBounds(10, 190, 230, 40);

        btnFeedbacks.setText("Feedbacks");
        btnFeedbacks.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFeedbacksActionPerformed(evt);
            }
        });
        add(btnFeedbacks);
        btnFeedbacks.setBounds(10, 190, 230, 40);

        btnSair.setText(" Sair");
        btnSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSairActionPerformed(evt);
            }
        });
        add(btnSair);
        btnSair.setBounds(10, 780, 230, 50);

        btnFeedbacksAdmin.setText("Feedbacks");
        btnFeedbacksAdmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFeedbacksAdminActionPerformed(evt);
            }
        });
        add(btnFeedbacksAdmin);
        btnFeedbacksAdmin.setBounds(10, 640, 230, 40);

        btnGerenciarItens.setText("Gerenciar Itens");
        btnGerenciarItens.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGerenciarItensActionPerformed(evt);
            }
        });
        add(btnGerenciarItens);
        btnGerenciarItens.setBounds(10, 340, 230, 40);



        btnGerenciarPagamentos.setText("Gerenciar Pagamentos");
        btnGerenciarPagamentos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGerenciarPagamentosActionPerformed(evt);
            }
        });
        add(btnGerenciarPagamentos);
        btnGerenciarPagamentos.setBounds(10, 590, 230, 40);



        btnHistoricoItem.setText("Historico de Itens");
        btnHistoricoItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHistoricoItemActionPerformed(evt);
            }
        });
        add(btnHistoricoItem);
        btnHistoricoItem.setBounds(10, 290, 230, 40);

        lblItens.setText("Itens");
        add(lblItens);
        lblItens.setBounds(20, 237, 170, 40);
    }// </editor-fold>//GEN-END:initComponents

    private void btnConfiguracoesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfiguracoesActionPerformed
        if (parentFrame instanceof FrMenu) {
            ((FrMenu) parentFrame).mostrarConfiguracoes();
        }
    }//GEN-LAST:event_btnConfiguracoesActionPerformed

    private void btnPerfilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPerfilActionPerformed
        if (parentFrame instanceof FrMenu) {
            ((FrMenu) parentFrame).mostrarPerfil();
        }
    }//GEN-LAST:event_btnPerfilActionPerformed

    private void btnFeedbacksActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFeedbacksActionPerformed
        DlgFeedback dialog = new DlgFeedback(null, true);
        dialog.setLocationRelativeTo(parentFrame);
        dialog.setVisible(true);
    }//GEN-LAST:event_btnFeedbacksActionPerformed

    private void btnGerenciarTecnicosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGerenciarTecnicosActionPerformed
        DlgGerenciaTecnico dialog = new DlgGerenciaTecnico(null, true);
        dialog.setLocationRelativeTo(parentFrame);
        dialog.setVisible(true);
    }//GEN-LAST:event_btnGerenciarTecnicosActionPerformed

    private void btnGerenciaClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGerenciaClientesActionPerformed
        DlgGerenciaCliente dialog = new DlgGerenciaCliente(null, true);
        dialog.setLocationRelativeTo(parentFrame);
        dialog.setVisible(true);
    }//GEN-LAST:event_btnGerenciaClientesActionPerformed

    private void btnGerenciarSuportesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGerenciarSuportesActionPerformed
        DlgGerenciaSuporte dialog = new DlgGerenciaSuporte(null, true);
        dialog.setLocationRelativeTo(parentFrame);
        dialog.setVisible(true);
    }//GEN-LAST:event_btnGerenciarSuportesActionPerformed

    private void btnAgendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgendaActionPerformed

        if (parentFrame instanceof FrMenu) {
            ((FrMenu) parentFrame).mostrarAgenda();
        }
    }//GEN-LAST:event_btnAgendaActionPerformed

    private void btnFeedbacksAdminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFeedbacksAdminActionPerformed
        DlgFeedback dialog = new DlgFeedback(null, true);
        dialog.setLocationRelativeTo(parentFrame);
        dialog.setVisible(true);
    }//GEN-LAST:event_btnFeedbacksAdminActionPerformed

    private void btnGerenciarItensActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGerenciarItensActionPerformed
        DlgGerenciaItem dialog = new DlgGerenciaItem(null, true);
        dialog.setLocationRelativeTo(parentFrame);
        dialog.setVisible(true);
    }//GEN-LAST:event_btnGerenciarItensActionPerformed

    private void btnMeusServicosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMeusServicosActionPerformed
        if (parentFrame instanceof FrMenu) {
            ((FrMenu) parentFrame).mostrarMeusServicos();
        }
    }//GEN-LAST:event_btnMeusServicosActionPerformed

    private void btnGerenciarRecepcionistasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGerenciarRecepcionistasActionPerformed
        DlgGerenciaRecepcionista dialog = new DlgGerenciaRecepcionista(null, true);
        dialog.setLocationRelativeTo(parentFrame);
        dialog.setVisible(true);
    }//GEN-LAST:event_btnGerenciarRecepcionistasActionPerformed

    private void btnGerenciarPagamentosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGerenciarPagamentosActionPerformed
        DlgGerenciaPagamento dialog = new DlgGerenciaPagamento(null, true);
        dialog.setLocationRelativeTo(parentFrame);
        dialog.setVisible(true);
    }//GEN-LAST:event_btnGerenciarPagamentosActionPerformed

    private void btnGerenciarAdminsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGerenciarAdminsActionPerformed
        DlgGerenciaAdmin dialog = new DlgGerenciaAdmin(null, true);
        dialog.setLocationRelativeTo(parentFrame);
        dialog.setVisible(true);
    }//GEN-LAST:event_btnGerenciarAdminsActionPerformed

    private void btnSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSairActionPerformed
            // Realiza o logout
        authController.logout();

        // Fecha a janela atual (parentFrame) e redireciona para a tela de login
        if (parentFrame != null) {
            parentFrame.dispose();
        }

        // Abre a tela de login
        FrLogin telaLogin = new FrLogin();
        telaLogin.setLocationRelativeTo(null);
        telaLogin.setVisible(true);
    }//GEN-LAST:event_btnSairActionPerformed

    private void btnFeedbackClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFeedbackClienteActionPerformed
        DlgFeedbackCliente dialog = new DlgFeedbackCliente(null, true);dialog.setLocationRelativeTo(parentFrame);
        dialog.setVisible(true);
    }//GEN-LAST:event_btnFeedbackClienteActionPerformed

    private void btnHistoricoItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHistoricoItemActionPerformed
        DlgHistoricoItem dialog = new DlgHistoricoItem(null, true);
        dialog.setLocationRelativeTo(parentFrame);
        dialog.setVisible(true);
    }//GEN-LAST:event_btnHistoricoItemActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.ifcolab.safesoft.components.CustomButton btnAgenda;
    private com.ifcolab.safesoft.components.CustomButton btnConfiguracoes;
    private com.ifcolab.safesoft.components.CustomButton btnFeedbacks;
    private com.ifcolab.safesoft.components.CustomButton btnFeedbacksAdmin;
    private com.ifcolab.safesoft.components.CustomButton btnFeedbackCliente;
    private com.ifcolab.safesoft.components.CustomButton btnGerenciarAdmins;
    private com.ifcolab.safesoft.components.CustomButton btnGerenciarSuportes;
    private com.ifcolab.safesoft.components.CustomButton btnGerenciarTecnicos;
    private com.ifcolab.safesoft.components.CustomButton btnGerenciaClientes;
    private com.ifcolab.safesoft.components.CustomButton btnGerenciarPagamentos;
    private com.ifcolab.safesoft.components.CustomButton btnGerenciarItens;
    private com.ifcolab.safesoft.components.CustomButton btnGerenciarRecepcionistas;
    private com.ifcolab.safesoft.components.CustomButton btnHistoricoItem;
    private com.ifcolab.safesoft.components.CustomButton btnMeusServicos;
    private com.ifcolab.safesoft.components.CustomButton btnPerfil;
    private com.ifcolab.safesoft.components.CustomButton btnSair;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JLabel lblSafeSoft;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JLabel lblItens;
    // End of variables declaration//GEN-END:variables
}
