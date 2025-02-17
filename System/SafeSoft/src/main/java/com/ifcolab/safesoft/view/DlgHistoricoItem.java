package com.ifcolab.safesoft.view;

import com.ifcolab.safesoft.controller.ServicoController;
import com.ifcolab.safesoft.controller.ClienteController;
import com.ifcolab.safesoft.controller.tablemodel.TMViewHistoricoItem;
import com.ifcolab.safesoft.model.Servico;
import com.ifcolab.safesoft.model.Cliente;
import com.ifcolab.safesoft.model.Item;

import javax.swing.DefaultComboBoxModel;
import java.util.List;
import javax.swing.JOptionPane;
public class DlgHistoricoItem extends javax.swing.JDialog {
    private final ServicoController servicoController;
    private final ClienteController clienteController;

    public DlgHistoricoItem(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        
        this.servicoController = new ServicoController();
        this.clienteController = new ClienteController();
        
        configurarComponentes();
        configurarEventos();
    }
    
    private void configurarComponentes() {
        configurarComboBoxClientes();
        configurarTabela();
    }
    
    private void configurarComboBoxClientes() {
        List<Cliente> clientes = clienteController.findAll();
        DefaultComboBoxModel<Cliente> model = new DefaultComboBoxModel<>();
        
        for (Cliente cliente : clientes) {
            model.addElement(cliente);
        }
        
        cboClientes.setModel(model);
    }
    
    private void configurarTabela() {
        grdHistorico.getSelectionModel().addListSelectionListener((e) -> {
            btnVisualizarRelatorio.setEnabled(grdHistorico.getSelectedRow() != -1);
        });
    }
    
    private void configurarEventos() {
        cboClientes.addActionListener((evt) -> {
            atualizarHistorico();
        });
        
        btnBuscarCliente.addActionListener((evt) -> {
            atualizarHistorico();
        });
        
        btnVisualizarRelatorio.addActionListener((evt) -> {
            visualizarRelatorio();
        });
    }
    
    private void atualizarHistorico() {
        Cliente clienteSelecionado = (Cliente) cboClientes.getSelectedItem();
        
        if (clienteSelecionado != null) {
            List<Servico> servicos = servicoController.buscarServicosPorCliente(
                clienteSelecionado.getId()
            );
            
            TMViewHistoricoItem tmHistorico = new TMViewHistoricoItem(servicos);
            grdHistorico.setModel(tmHistorico);
        }
    }
    
    private void visualizarRelatorio() {
        try {
            int linhaSelecionada = grdHistorico.getSelectedRow();
            if (linhaSelecionada == -1) {
                exibirAviso("Selecione uma servico para visualizar o relatório.");
                return;
            }

            TMViewHistoricoItem modelo = (TMViewHistoricoItem) grdHistorico.getModel();
            Servico servico = modelo.getServico(linhaSelecionada);
            
            if (servico.getItens().isEmpty()) {
                exibirAviso("Não há servicos registrados nesta servico.");
                return;
            }

            if (servico.getItens().size() == 1) {
                abrirTelaRelatorio(servico, servico.getItens().get(0));
            } else {
                selecionarItemEAbrirRelatorio(servico);
            }
            
        } catch (Exception e) {
            exibirErro("Erro ao visualizar relatório", e);
        }
    }
    
    private void selecionarItemEAbrirRelatorio(Servico servico) {
        Item item = (Item) JOptionPane.showInputDialog(
            this,
            "Selecione o item:",
            "Gerar Relatório",
            JOptionPane.QUESTION_MESSAGE,
            null,
            servico.getItens().toArray(),
            servico.getItens().get(0)
        );
        
        if (item != null) {
            abrirTelaRelatorio(servico, item);
        }
    }
    
    private void abrirTelaRelatorio(Servico servico, Item item) {
        DlgRelatorio dialog = new DlgRelatorio(null, true, servico, item);
        dialog.setVisible(true);
        if (dialog.isRelatorioSalvo()) {
            atualizarHistorico();
        }
    }
    
    private void exibirAviso(String mensagem) {
        JOptionPane.showMessageDialog(this, mensagem, "Aviso", 
            JOptionPane.WARNING_MESSAGE);
    }
    
    private void exibirErro(String mensagem, Exception e) {
        JOptionPane.showMessageDialog(this, 
            mensagem + ": " + e.getMessage(),
            "Erro",
            JOptionPane.ERROR_MESSAGE);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblNome = new javax.swing.JLabel();
        cboClientes = new com.ifcolab.safesoft.components.CustomComboBox();
        btnBuscarCliente = new com.ifcolab.safesoft.components.PrimaryCustomButton();
        btnVisualizarRelatorio = new com.ifcolab.safesoft.components.SecondaryCustomButton();
        tmRecepcionistas = new javax.swing.JScrollPane();
        grdHistorico = new com.ifcolab.safesoft.components.CustomTable();
        pnlHistoricos = new javax.swing.JPanel();
        lblSubtituloGerenciaTecnicos = new javax.swing.JLabel();
        lblTitleGerenciaTecnicos = new javax.swing.JLabel();
        lblBackgroundCadastro = new javax.swing.JLabel();
        lblBackground = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1350, 870));
        getContentPane().setLayout(null);

        lblNome.setForeground(new java.awt.Color(51, 51, 51));
        lblNome.setText("Selecionar Cliente");
        getContentPane().add(lblNome);
        lblNome.setBounds(60, 120, 160, 17);
        getContentPane().add(cboClientes);
        cboClientes.setBounds(50, 140, 650, 44);

        btnBuscarCliente.setText("Buscar Cliente");
        getContentPane().add(btnBuscarCliente);
        btnBuscarCliente.setBounds(50, 70, 190, 30);

        btnVisualizarRelatorio.setText("Visualizar Relatorio");
        getContentPane().add(btnVisualizarRelatorio);
        btnVisualizarRelatorio.setBounds(260, 70, 230, 30);

        grdHistorico.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tmRecepcionistas.setViewportView(grdHistorico);

        getContentPane().add(tmRecepcionistas);
        tmRecepcionistas.setBounds(40, 236, 1260, 570);

        pnlHistoricos.setBackground(new java.awt.Color(255, 255, 255));
        getContentPane().add(pnlHistoricos);
        pnlHistoricos.setBounds(30, 220, 1280, 600);

        lblSubtituloGerenciaTecnicos.setFont(new java.awt.Font("Segoe UI Medium", 0, 13)); // NOI18N
        lblSubtituloGerenciaTecnicos.setForeground(new java.awt.Color(102, 102, 102));
        lblSubtituloGerenciaTecnicos.setText("Visualize os servicos por cliente");
        getContentPane().add(lblSubtituloGerenciaTecnicos);
        lblSubtituloGerenciaTecnicos.setBounds(30, 40, 650, 17);

        lblTitleGerenciaTecnicos.setFont(new java.awt.Font("Segoe UI SemiBold", 0, 18)); // NOI18N
        lblTitleGerenciaTecnicos.setForeground(new java.awt.Color(51, 51, 51));
        lblTitleGerenciaTecnicos.setText("Historico de Itens");
        getContentPane().add(lblTitleGerenciaTecnicos);
        lblTitleGerenciaTecnicos.setBounds(30, 20, 360, 22);

        lblBackgroundCadastro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/backgroundCrud.png"))); // NOI18N
        getContentPane().add(lblBackgroundCadastro);
        lblBackgroundCadastro.setBounds(-10, 60, 1330, 140);

        lblBackground.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/background.png"))); // NOI18N
        lblBackground.setText("jLabel3");
        getContentPane().add(lblBackground);
        lblBackground.setBounds(0, 0, 1350, 850);

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.ifcolab.safesoft.components.PrimaryCustomButton btnBuscarCliente;
    private com.ifcolab.safesoft.components.SecondaryCustomButton btnVisualizarRelatorio;
    private com.ifcolab.safesoft.components.CustomComboBox cboClientes;
    private com.ifcolab.safesoft.components.CustomTable grdHistorico;
    private javax.swing.JLabel lblBackground;
    private javax.swing.JLabel lblBackgroundCadastro;
    private javax.swing.JLabel lblNome;
    private javax.swing.JLabel lblSubtituloGerenciaTecnicos;
    private javax.swing.JLabel lblTitleGerenciaTecnicos;
    private javax.swing.JPanel pnlHistoricos;
    private javax.swing.JScrollPane tmRecepcionistas;
    // End of variables declaration//GEN-END:variables
}
