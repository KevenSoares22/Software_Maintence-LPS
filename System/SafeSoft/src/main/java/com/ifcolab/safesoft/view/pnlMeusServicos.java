package com.ifcolab.safesoft.view;

import com.ifcolab.safesoft.controller.AutenticacaoController;
import com.ifcolab.safesoft.controller.ServicoController;
import com.ifcolab.safesoft.model.Cliente;
import com.ifcolab.safesoft.model.Pessoa;

public class pnlMeusServicos extends javax.swing.JPanel {

    private final AutenticacaoController autenticacaoController;
    private final ServicoController servicoController;
     
    public pnlMeusServicos() {
        initComponents();
        
        autenticacaoController = new AutenticacaoController();
        servicoController = new ServicoController();
        
        configurarComponentes();
        carregarServicos();
    }
    
    private void configurarComponentes() {
        lblSubtituloGerenciaTecnicos.setText("Visualize todas as suas consultas agendadas.");
        
        grdMeusServicos.setShowGrid(false);
        grdMeusServicos.setShowHorizontalLines(true);
        grdMeusServicos.setGridColor(new java.awt.Color(230, 230, 230));
        grdMeusServicos.setRowHeight(50);
        
    }

    
    private void carregarServicos() {
        Pessoa usuarioLogado = autenticacaoController.getUsuarioLogado();
        if (usuarioLogado instanceof Cliente) {
            Cliente cliente = (Cliente) usuarioLogado;
            servicoController.atualizarTabelaMeusServicos(grdMeusServicos, cliente.getId());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblAvatar = new javax.swing.JLabel();
        lblTitleGerenciaTecnicos = new javax.swing.JLabel();
        lblSubtituloGerenciaTecnicos = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        grdMeusServicos = new com.ifcolab.safesoft.components.CustomTable();

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(null);

        lblAvatar.setForeground(new java.awt.Color(51, 51, 51));
        add(lblAvatar);
        lblAvatar.setBounds(30, 450, 170, 0);

        lblTitleGerenciaTecnicos.setFont(new java.awt.Font("Segoe UI SemiBold", 0, 18)); // NOI18N
        lblTitleGerenciaTecnicos.setForeground(new java.awt.Color(51, 51, 51));
        lblTitleGerenciaTecnicos.setText("Minhas Servicos");
        add(lblTitleGerenciaTecnicos);
        lblTitleGerenciaTecnicos.setBounds(30, 20, 210, 22);

        lblSubtituloGerenciaTecnicos.setFont(new java.awt.Font("Segoe UI Medium", 0, 13)); // NOI18N
        lblSubtituloGerenciaTecnicos.setForeground(new java.awt.Color(102, 102, 102));
        lblSubtituloGerenciaTecnicos.setText("Personalize seu perfil ao seu gosto.");
        add(lblSubtituloGerenciaTecnicos);
        lblSubtituloGerenciaTecnicos.setBounds(30, 40, 550, 17);

        grdMeusServicos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(grdMeusServicos);

        add(jScrollPane1);
        jScrollPane1.setBounds(10, 70, 980, 620);
    }// </editor-fold>//GEN-END:initComponents



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.ifcolab.safesoft.components.CustomTable grdMeusServicos;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblAvatar;
    private javax.swing.JLabel lblSubtituloGerenciaTecnicos;
    private javax.swing.JLabel lblTitleGerenciaTecnicos;
    // End of variables declaration//GEN-END:variables
}
