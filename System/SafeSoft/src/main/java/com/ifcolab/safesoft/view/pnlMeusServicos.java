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
        lblSubtituloGerenciaTecnicoes.setText("Visualize todas as suas consultas agendadas.");
        
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
        lblTitleGerenciaTecnicoes = new javax.swing.JLabel();
        lblSubtituloGerenciaTecnicoes = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        grdMeusServicos = new com.ifcolab.safesoft.components.CustomTable();

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(null);

        lblAvatar.setForeground(new java.awt.Color(51, 51, 51));
        add(lblAvatar);
        lblAvatar.setBounds(30, 450, 170, 0);

        lblTitleGerenciaTecnicoes.setFont(new java.awt.Font("Fira Sans SemiBold", 0, 18)); // NOI18N
        lblTitleGerenciaTecnicoes.setForeground(new java.awt.Color(51, 51, 51));
        lblTitleGerenciaTecnicoes.setText("Minhas Servicos");
        add(lblTitleGerenciaTecnicoes);
        lblTitleGerenciaTecnicoes.setBounds(30, 20, 210, 22);

        lblSubtituloGerenciaTecnicoes.setFont(new java.awt.Font("Fira Sans Medium", 0, 13)); // NOI18N
        lblSubtituloGerenciaTecnicoes.setForeground(new java.awt.Color(102, 102, 102));
        lblSubtituloGerenciaTecnicoes.setText("Personalize seu perfil ao seu gosto.");
        add(lblSubtituloGerenciaTecnicoes);
        lblSubtituloGerenciaTecnicoes.setBounds(30, 40, 550, 17);

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
    private javax.swing.JLabel lblSubtituloGerenciaTecnicoes;
    private javax.swing.JLabel lblTitleGerenciaTecnicoes;
    // End of variables declaration//GEN-END:variables
}
