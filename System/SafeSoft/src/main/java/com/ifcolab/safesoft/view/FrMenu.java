package com.ifcolab.safesoft.view;

import com.ifcolab.safesoft.controller.AutenticacaoController;
import com.ifcolab.safesoft.model.Cliente;

public class FrMenu extends javax.swing.JFrame {

    private final AutenticacaoController autenticacaoController;

    public FrMenu() {
        initComponents();
        this.setLocationRelativeTo(null);

        autenticacaoController = new AutenticacaoController();
        agendaPanel = new PnlAgenda();

        pnlConfiguracaoSistema.setParentFrame(this);
        // Configurar a sidebar
        pnlSidebar.setParentFrame(this);

        if (autenticacaoController.getUsuarioLogado() instanceof Cliente) {
            mostrarMeusServicos();
        } else {
            mostrarAgenda();
        }

    }

    public final void mostrarMeusServicos() {
        // Limpar o conteúdo atual
        getContentPane().removeAll();

        // Adicionar componentes na ordem correta
        getContentPane().add(pnlAppBar);
        getContentPane().add(pnlSidebar);
        getContentPane().add(pnlMeusServicos);


        // Adicionar o background por último
        getContentPane().add(lblBackground);

        // Atualizar a tela
        revalidate();
        repaint();
    }

    public final void mostrarAgenda() {

        if (autenticacaoController.getUsuarioLogado() instanceof Cliente) {
            mostrarMeusServicos();
            return;
        }
        // Limpar o conteúdo atual
        getContentPane().removeAll();

        // Adicionar componentes na ordem correta
        getContentPane().add(pnlAppBar);
        getContentPane().add(pnlSidebar);
        getContentPane().add(agendaPanel);

        // Definir posições (mesmas posições dos outros painéis)
        agendaPanel.setBounds(280, 80, 1040, 730);

        // Adicionar o background por último
        getContentPane().add(lblBackground);

        // Atualizar a tela
        revalidate();
        repaint();
    }

    public void mostrarPerfil() {
        // Limpar o conteúdo atual
        getContentPane().removeAll();

        // Adicionar componentes
        getContentPane().add(pnlAppBar);
        getContentPane().add(pnlSidebar);
        getContentPane().add(pnlPerfil);

        getContentPane().add(lblBackground);

        // Atualizar a tela
        revalidate();
        repaint();
    }


    public void mostrarConfiguracoes() {
        // Limpar o conteúdo atual
        getContentPane().removeAll();

        // Adicionar componentes na ordem correta
        getContentPane().add(pnlAppBar);
        getContentPane().add(pnlSidebar);
        getContentPane().add(pnlConfiguracaoSistema);

        // Adicionar o background por último
        getContentPane().add(lblBackground);

        // Atualizar a tela
        revalidate();
        repaint();
    }


    public void atualizarAppBar() {
        if (pnlAppBar != null) {
            pnlAppBar.atualizarInterface();
        }
    }

    public void atualizarAgenda() {
        if (agendaPanel != null) {
            agendaPanel.atualizarVisualizacao();
            revalidate();
            repaint();
        }
    }



    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel4 = new javax.swing.JLabel();
        pnlPerfil = new com.ifcolab.safesoft.view.pnlPerfil();
        pnlConfiguracaoSistema = new com.ifcolab.safesoft.view.pnlConfiguracaoSistema();
        pnlAppBar = new com.ifcolab.safesoft.view.PnlAppBar();
        pnlSidebar = new com.ifcolab.safesoft.view.PnlSidebar();
        agendaPanel = new com.ifcolab.safesoft.view.PnlAgenda();
        pnlMeusServicos = new com.ifcolab.safesoft.view.pnlMeusServicos();
        lblBackground = new javax.swing.JLabel();
        jLabel4.setText("jLabel4");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1350, 870));
        setResizable(false);
        getContentPane().setLayout(null);
        getContentPane().add(pnlPerfil);
        pnlPerfil.setBounds(280, 80, 1040, 730);
        getContentPane().add(pnlConfiguracaoSistema);
        pnlConfiguracaoSistema.setBounds(280, 80, 1040, 730);
        getContentPane().add(pnlMeusServicos);
        pnlMeusServicos.setBounds(280, 80, 1040, 730);
        getContentPane().add(pnlAppBar);
        pnlAppBar.setBounds(250, 0, 1100, 50);
        getContentPane().add(pnlSidebar);
        pnlSidebar.setBounds(0, 0, 250, 850);
        getContentPane().add(agendaPanel);
        agendaPanel.setBounds(280, 80, 1040, 730);

        lblBackground.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/background.png"))); // NOI18N
        lblBackground.setText("jLabel3");
        getContentPane().add(lblBackground);
        lblBackground.setBounds(0, 0, 1350, 850);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel lblBackground;
    private com.ifcolab.safesoft.view.PnlAppBar pnlAppBar;
    private com.ifcolab.safesoft.view.pnlPerfil pnlPerfil;
    private com.ifcolab.safesoft.view.pnlConfiguracaoSistema pnlConfiguracaoSistema;
    private com.ifcolab.safesoft.view.pnlMeusServicos pnlMeusServicos;
    private com.ifcolab.safesoft.view.PnlAgenda agendaPanel;
    private com.ifcolab.safesoft.view.PnlSidebar pnlSidebar;
    // End of variables declaration//GEN-END:variables
}
