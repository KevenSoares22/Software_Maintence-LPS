package com.ifcolab.safesoft.view;

import com.ifcolab.safesoft.controller.ServicoController;
import com.ifcolab.safesoft.controller.SuporteController;
import com.ifcolab.safesoft.controller.TecnicoController;
import com.ifcolab.safesoft.controller.ClienteController;
import com.ifcolab.safesoft.controller.ItemController;
import com.ifcolab.safesoft.controller.tablemodel.TMViewServico;
import com.ifcolab.safesoft.model.*;
import com.ifcolab.safesoft.model.Cliente;
import com.ifcolab.safesoft.model.exceptions.ServicoException;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.text.MaskFormatter;

public class DlgNovoServico extends javax.swing.JDialog {

    private final ServicoController controller;
    private final ClienteController clienteController;
    private final TecnicoController tecnicoController;
    private final SuporteController suporteController;
    private final ItemController itemController;
    private int idServicoEditando;
    private boolean servicoCadastrada;
    private boolean servicoAlterada;
    private List<Item> itensSelecionados;
    
    public DlgNovoServico(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);

        controller = new ServicoController();
        clienteController = new ClienteController();
        tecnicoController = new TecnicoController();
        suporteController = new SuporteController();
        itemController = new ItemController();
        
        try {
            controller.getConfiguracao();
            
            controller.atualizarTabela(grdServicos);
            idServicoEditando = -1;
            servicoCadastrada = false;
            servicoAlterada = false;
            itensSelecionados = new ArrayList<>();
            
            this.configurarComponentes();
            this.carregarCombos();
            
            
            
            this.habilitarFormulario(false);
            
        } catch (ServicoException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            this.dispose();
        }
    }
    
    public boolean isServicoCadastrada() {
        return servicoCadastrada;
    }
    
    public boolean isServicoAlterada() {
        return servicoAlterada;
    }

    private void configurarComponentes() {
        try {
            MaskFormatter maskData = new MaskFormatter("##/##/####");
            maskData.setPlaceholderCharacter('_');
            maskData.install(fEdtData);

            MaskFormatter maskHora = new MaskFormatter("##:##");
            maskHora.setPlaceholderCharacter('_');
            maskHora.install(fEdtHora);

            ConfiguracaoSistema config = controller.getConfiguracao();
            String tooltip = String.format("Horário de funcionamento: %s às %s", 
                config.getHorarioAbertura().format(DateTimeFormatter.ofPattern("HH:mm")),
                config.getHorarioFechamento().format(DateTimeFormatter.ofPattern("HH:mm")));
            fEdtHora.setToolTipText(tooltip);
            
        } catch (ParseException ex) {
            Logger.getLogger(DlgNovoServico.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    private void carregarCombos() {

        cbxSelecionarTecnico.removeAllItems();
        tecnicoController.findAll().forEach(Tecnico -> {
            cbxSelecionarTecnico.addItem(Tecnico);
        });
        

        cbxSelecionarSuporte.removeAllItems();
        suporteController.findAll().forEach(suporte -> {
            cbxSelecionarSuporte.addItem(suporte);
        });
        
        cbxSelecionarCliente.removeAllItems();
        clienteController.findAll().forEach(cliente -> {
            cbxSelecionarCliente.addItem(cliente);
        });
        
        cbxSelecionarItem.removeAllItems();
        itemController.findAll().forEach(item -> {
            cbxSelecionarItem.addItem(item);
        });
    }

    private void habilitarFormulario(boolean habilitar) {
        cbxSelecionarCliente.setEnabled(habilitar);
        cbxSelecionarTecnico.setEnabled(habilitar);
        cbxSelecionarSuporte.setEnabled(habilitar);
        cbxSelecionarItem.setEnabled(habilitar);
        fEdtData.setEnabled(habilitar);
        fEdtHora.setEnabled(habilitar);
        txtObeservacoes.setEnabled(habilitar);
        btnAdicionarItem.setEnabled(habilitar);
        lstItens.setEnabled(habilitar);
        btnSalvar.setEnabled(habilitar);
    }
    
    private void limparFormulario() {
        cbxSelecionarCliente.setSelectedIndex(-1);
        cbxSelecionarTecnico.setSelectedIndex(-1);
        cbxSelecionarSuporte.setSelectedIndex(-1);
        cbxSelecionarItem.setSelectedIndex(-1);
        fEdtData.setText("");
        fEdtHora.setText("");
        txtObeservacoes.setText("");
        itensSelecionados.clear();
        atualizarListaItens();
    }
    
    public void preencherFormulario(Servico servico) {
        if (servico == null) return;
        
        this.idServicoEditando = servico.getId();
        cbxSelecionarCliente.setSelectedItem(servico.getCliente());
        cbxSelecionarTecnico.setSelectedItem(servico.getTecnico());
        cbxSelecionarSuporte.setSelectedItem(servico.getSuporte());
        
        // Formata data e hora
        fEdtData.setText(servico.getDataHora().format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        fEdtHora.setText(servico.getDataHora().format(java.time.format.DateTimeFormatter.ofPattern("HH:mm")));
        
        txtObeservacoes.setText(servico.getObservacoes());
        itensSelecionados = new ArrayList<>(servico.getItens());
        atualizarListaItens();
        
        this.habilitarFormulario(true);
    }
    
    private Servico getObjetoSelecionadoNaGrid() {
        int rowSelected = grdServicos.getSelectedRow();
        if (rowSelected >= 0) {
            TMViewServico model = (TMViewServico) grdServicos.getModel();
            return model.getServico(rowSelected);
        }
        return null;
    }  
    
    private void atualizarListaItens() {
        DefaultListModel<Item> model = new DefaultListModel<>();
        for (Item proc : itensSelecionados) {
            model.addElement(proc);
        }
        lstItens.setModel(model);
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblCPF = new javax.swing.JLabel();
        lblNome = new javax.swing.JLabel();
        lblEmail = new javax.swing.JLabel();
        lblItensSelecionados = new javax.swing.JLabel();
        lblData = new javax.swing.JLabel();
        lblDataNascimento = new javax.swing.JLabel();
        lblHora = new javax.swing.JLabel();
        txtObservacoesScrollPane = new javax.swing.JScrollPane();
        txtObeservacoes = new com.ifcolab.safesoft.components.CustomTextArea();
        fEdtHora = new com.ifcolab.safesoft.components.CustomFormattedTextField();
        fEdtData = new com.ifcolab.safesoft.components.CustomFormattedTextField();
        cbxSelecionarItem = new com.ifcolab.safesoft.components.CustomComboBox();
        cbxSelecionarSuporte = new com.ifcolab.safesoft.components.CustomComboBox();
        cbxSelecionarTecnico = new com.ifcolab.safesoft.components.CustomComboBox();
        cbxSelecionarCliente = new com.ifcolab.safesoft.components.CustomComboBox();
        btnAdicionarItem = new com.ifcolab.safesoft.components.PrimaryCustomButton();
        btnAdicionar = new com.ifcolab.safesoft.components.PrimaryCustomButton();
        btnSalvar = new com.ifcolab.safesoft.components.SecondaryCustomButton();
        btnEditar = new com.ifcolab.safesoft.components.SecondaryCustomButton();
        btnRemover = new com.ifcolab.safesoft.components.SecondaryCustomButton();
        lblObservacoes = new javax.swing.JLabel();
        scrollItens = new javax.swing.JScrollPane();
        lstItens = new javax.swing.JList<>();
        tmServicos = new javax.swing.JScrollPane();
        grdServicos = new com.ifcolab.safesoft.components.CustomTable();
        lblSubtituloGerenciaTecnicos = new javax.swing.JLabel();
        lblTitleGerenciaTecnicos = new javax.swing.JLabel();
        lblBackgroundTabela = new javax.swing.JLabel();
        lblBackgroundCadastro = new javax.swing.JLabel();
        lblBackground = new javax.swing.JLabel();
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1350, 870));
        getContentPane().setLayout(null);

        lblCPF.setForeground(new java.awt.Color(51, 51, 51));
        lblCPF.setText("Selecionar Suporte");
        getContentPane().add(lblCPF);
        lblCPF.setBounds(960, 120, 190, 17);

        lblNome.setForeground(new java.awt.Color(51, 51, 51));
        lblNome.setText("Selecionar Cliente");
        getContentPane().add(lblNome);
        lblNome.setBounds(60, 120, 220, 17);

        lblEmail.setForeground(new java.awt.Color(51, 51, 51));
        lblEmail.setText("Selecionar Tecnico");
        getContentPane().add(lblEmail);
        lblEmail.setBounds(400, 120, 170, 17);

        lblItensSelecionados.setForeground(new java.awt.Color(51, 51, 51));
        lblItensSelecionados.setText("Itens Selecionados");
        getContentPane().add(lblItensSelecionados);
        lblItensSelecionados.setBounds(1020, 200, 290, 17);

        lblData.setForeground(new java.awt.Color(51, 51, 51));
        lblData.setText("Hora");
        getContentPane().add(lblData);
        lblData.setBounds(80, 260, 110, 17);

        lblDataNascimento.setForeground(new java.awt.Color(51, 51, 51));
        lblDataNascimento.setText("Selecionar Item");
        getContentPane().add(lblDataNascimento);
        lblDataNascimento.setBounds(1020, 120, 230, 17);

        lblHora.setForeground(new java.awt.Color(51, 51, 51));
        lblHora.setText("Data");
        getContentPane().add(lblHora);
        lblHora.setBounds(80, 190, 130, 17);

        txtObeservacoes.setColumns(20);
        txtObeservacoes.setRows(5);
        txtObservacoesScrollPane.setViewportView(txtObeservacoes);

        getContentPane().add(txtObservacoesScrollPane);
        txtObservacoesScrollPane.setBounds(250, 200, 720, 130);

        fEdtHora.setText("Hora");
        fEdtHora.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                fEdtHoraPropertyChange(evt);
            }
        });
        getContentPane().add(fEdtHora);
        fEdtHora.setBounds(70, 280, 160, 38);

        fEdtData.setText("Data");
        fEdtData.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                fEdtDataPropertyChange(evt);
            }
        });
        getContentPane().add(fEdtData);
        fEdtData.setBounds(70, 210, 160, 38);
        getContentPane().add(cbxSelecionarItem);
        cbxSelecionarItem.setBounds(1000, 140, 250, 44);

        cbxSelecionarSuporte.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                cbxSelecionarSuportePropertyChange(evt);
            }
        });
        getContentPane().add(cbxSelecionarSuporte);
        cbxSelecionarSuporte.setBounds(730, 140, 250, 44);

        cbxSelecionarTecnico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxSelecionarTecnicoActionPerformed(evt);
            }
        });
        cbxSelecionarTecnico.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                cbxSelecionarTecnicoPropertyChange(evt);
            }
        });
        getContentPane().add(cbxSelecionarTecnico);
        cbxSelecionarTecnico.setBounds(380, 140, 290, 44);

        getContentPane().add(cbxSelecionarCliente);
        cbxSelecionarCliente.setBounds(80, 140, 290, 44);

        btnAdicionarItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/addsquare.png"))); // NOI18N
        btnAdicionarItem.setText("");
        btnAdicionarItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionarItemActionPerformed(evt);
            }
        });
        getContentPane().add(btnAdicionarItem);
        btnAdicionarItem.setBounds(1240, 140, 40, 40);

        btnAdicionar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/addsquare.png"))); // NOI18N
        btnAdicionar.setText(" Agendar");
        btnAdicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionarActionPerformed(evt);
            }
        });
        getContentPane().add(btnAdicionar);
        btnAdicionar.setBounds(60, 80, 170, 30);

        btnSalvar.setText(" Salvar");
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });
        getContentPane().add(btnSalvar);
        btnSalvar.setBounds(720, 80, 170, 30);

        btnEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/editsquare.png"))); // NOI18N
        btnEditar.setText(" Editar");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });
        getContentPane().add(btnEditar);
        btnEditar.setBounds(280, 80, 170, 30);

        btnRemover.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/trash.png"))); // NOI18N
        btnRemover.setText(" Remover");
        btnRemover.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoverActionPerformed(evt);
            }
        });
        getContentPane().add(btnRemover);
        btnRemover.setBounds(500, 80, 170, 30);

        lblObservacoes.setForeground(new java.awt.Color(51, 51, 51));
        lblObservacoes.setText("Observacoes");
        getContentPane().add(lblObservacoes);
        lblObservacoes.setBounds(240, 190, 110, 17);

        lstItens.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        lstItens.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                lstItensKeyPressed(evt);
            }
        });
        scrollItens.setViewportView(lstItens);

        getContentPane().add(scrollItens);
        scrollItens.setBounds(980, 210, 300, 120);

        grdServicos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        grdServicos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                grdServicosMouseClicked(evt);
            }
        });
        tmServicos.setViewportView(grdServicos);

        getContentPane().add(tmServicos);
        tmServicos.setBounds(40, 366, 1260, 430);

        lblSubtituloGerenciaTecnicos.setFont(new java.awt.Font("Segoe UI Medium", 0, 13)); // NOI18N
        lblSubtituloGerenciaTecnicos.setForeground(new java.awt.Color(102, 102, 102));
        lblSubtituloGerenciaTecnicos.setText("Agende novas servicos vinculando clientes, profissionais e horários disponíveis.");
        getContentPane().add(lblSubtituloGerenciaTecnicos);
        lblSubtituloGerenciaTecnicos.setBounds(30, 40, 550, 17);

        lblTitleGerenciaTecnicos.setFont(new java.awt.Font("Segoe UI SemiBold", 0, 18)); // NOI18N
        lblTitleGerenciaTecnicos.setForeground(new java.awt.Color(51, 51, 51));
        lblTitleGerenciaTecnicos.setText("Nova Servico");
        getContentPane().add(lblTitleGerenciaTecnicos);
        lblTitleGerenciaTecnicos.setBounds(30, 20, 210, 22);

        lblBackgroundTabela.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/backgroundTableModel.png"))); // NOI18N
        getContentPane().add(lblBackgroundTabela);
        lblBackgroundTabela.setBounds(-10, 330, 1350, 500);

        lblBackgroundCadastro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/backgroundCrud.png"))); // NOI18N
        getContentPane().add(lblBackgroundCadastro);
        lblBackgroundCadastro.setBounds(-10, 60, 1330, 290);

        lblBackground.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/background.png"))); // NOI18N
        lblBackground.setText("jLabel3");
        getContentPane().add(lblBackground);
        lblBackground.setBounds(0, 0, 1350, 850);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cbxSelecionarTecnicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxSelecionarTecnicoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxSelecionarTecnicoActionPerformed

    private void btnAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionarActionPerformed
        this.limparFormulario();
        this.habilitarFormulario(true);
    }//GEN-LAST:event_btnAdicionarActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        Servico servicoEditando = (Servico) this.getObjetoSelecionadoNaGrid();

        if (servicoEditando == null)
        JOptionPane.showMessageDialog(this, "Primeiro selecione um registro na tabela.");
        else {
            this.limparFormulario();
            this.habilitarFormulario(true);
            this.preencherFormulario(servicoEditando);
            this.idServicoEditando = servicoEditando.getId();
        }
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnRemoverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoverActionPerformed
        Servico servicoSelecionada = getObjetoSelecionadoNaGrid();
        if (servicoSelecionada == null) {
            JOptionPane.showMessageDialog(this, "Selecione uma servico para remover.");
            return;
        }

        int opcao = JOptionPane.showConfirmDialog(this,
            "Deseja realmente remover esta servico?",
            "Confirmação",
            JOptionPane.YES_NO_OPTION);

        if (opcao == JOptionPane.YES_OPTION) {
            try {
                controller.excluir(servicoSelecionada.getId());
                controller.atualizarTabela(grdServicos);
                servicoAlterada = true;
                
                // Limpar e desabilitar o formulário após remover
                this.limparFormulario();
                this.habilitarFormulario(false);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this,
                    "Erro ao remover servico: " + e.getMessage(),
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnRemoverActionPerformed

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        try {
            // Converte data e hora para LocalDateTime
            String dataHoraStr = fEdtData.getText() + " " + fEdtHora.getText();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
            LocalDateTime dataHora = LocalDateTime.parse(dataHoraStr, formatter);
            
            controller.validarHorarioServico(dataHora);

            ConfiguracaoSistema config = controller.getConfiguracao();
            if (!config.isDiaFuncionamento(dataHora.getDayOfWeek())) {
                throw new ServicoException("A clínica não funciona neste dia da semana");
            }
            
            LocalTime horario = dataHora.toLocalTime();
            if (horario.isBefore(config.getHorarioAbertura()) || 
                horario.isAfter(config.getHorarioFechamento())) {
                throw new ServicoException("Horário fora do período de funcionamento (" +
                    config.getHorarioAbertura().format(DateTimeFormatter.ofPattern("HH:mm")) + " às " + 
                    config.getHorarioFechamento().format(DateTimeFormatter.ofPattern("HH:mm")) + ")");
            }
            
            LocalDateTime agora = LocalDateTime.now();
            long minutosAteServico = java.time.Duration.between(agora, dataHora).toMinutes();
            if (minutosAteServico < config.getTempoMinimoAntecedenciaMinutos()) {
                throw new ServicoException("É necessário agendar com no mínimo " +
                    config.getTempoMinimoAntecedenciaMinutos() + " minutos de antecedência");
            }
            
            Cliente cliente = (Cliente) cbxSelecionarCliente.getSelectedItem();
            Tecnico tecnico = (Tecnico) cbxSelecionarTecnico.getSelectedItem();
            Suporte suporte = (Suporte) cbxSelecionarSuporte.getSelectedItem();
            String observacoes = txtObeservacoes.getText();
            
            if (idServicoEditando > 0) {
                controller.atualizar(
                    idServicoEditando,
                    dataHora,
                    observacoes,
                        cliente,
                        tecnico,
                        suporte,
                    itensSelecionados
                );
                servicoAlterada = true;
            } else {
                controller.cadastrar(
                    dataHora,
                    observacoes,
                        cliente,
                        tecnico,
                        suporte,
                    itensSelecionados
                );
                servicoCadastrada = true;
            }

            controller.atualizarTabela(grdServicos);
            
            this.idServicoEditando = -1;
            this.habilitarFormulario(false);
            this.limparFormulario();

            dispose();

        } catch (ServicoException e) {
            System.err.println(e.getMessage());
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void btnAdicionarItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionarItemActionPerformed
        Item item = (Item) cbxSelecionarItem.getSelectedItem();
        if (item != null) {
            if (!itensSelecionados.contains(item)) {
                itensSelecionados.add(item);
                atualizarListaItens();
            } else {
                JOptionPane.showMessageDialog(this, 
                    "Este item já foi adicionado",
                    "Aviso",
                    JOptionPane.WARNING_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnAdicionarItemActionPerformed

    private void lstItensKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lstItensKeyPressed
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_DELETE) {
            int index = lstItens.getSelectedIndex();
            if (index != -1) {
                itensSelecionados.remove(index);
                atualizarListaItens();
            }
        }
    }//GEN-LAST:event_lstItensKeyPressed

    private void fEdtDataPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_fEdtDataPropertyChange

    }//GEN-LAST:event_fEdtDataPropertyChange

    private void fEdtHoraPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_fEdtHoraPropertyChange

    }//GEN-LAST:event_fEdtHoraPropertyChange

    private void cbxSelecionarTecnicoPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_cbxSelecionarTecnicoPropertyChange

    }//GEN-LAST:event_cbxSelecionarTecnicoPropertyChange

    private void cbxSelecionarSuportePropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_cbxSelecionarSuportePropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxSelecionarSuportePropertyChange

    private void grdServicosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_grdServicosMouseClicked
        if (evt.getClickCount() == 2) {
            Servico servicoSelecionada = getObjetoSelecionadoNaGrid();
            if (servicoSelecionada != null) {
                this.limparFormulario();
                this.habilitarFormulario(true);
                this.preencherFormulario(servicoSelecionada);
                this.idServicoEditando = servicoSelecionada.getId();
            }
        }
    }//GEN-LAST:event_grdServicosMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.ifcolab.safesoft.components.PrimaryCustomButton btnAdicionar;
    private com.ifcolab.safesoft.components.PrimaryCustomButton btnAdicionarItem;
    private com.ifcolab.safesoft.components.SecondaryCustomButton btnEditar;
    private com.ifcolab.safesoft.components.SecondaryCustomButton btnRemover;
    private com.ifcolab.safesoft.components.SecondaryCustomButton btnSalvar;
    private com.ifcolab.safesoft.components.CustomComboBox cbxSelecionarSuporte;
    private com.ifcolab.safesoft.components.CustomComboBox cbxSelecionarTecnico;
    private com.ifcolab.safesoft.components.CustomComboBox cbxSelecionarCliente;
    private com.ifcolab.safesoft.components.CustomComboBox cbxSelecionarItem;
    private com.ifcolab.safesoft.components.CustomFormattedTextField fEdtData;
    private com.ifcolab.safesoft.components.CustomFormattedTextField fEdtHora;
    private com.ifcolab.safesoft.components.CustomTable grdServicos;
    private javax.swing.JLabel lblBackground;
    private javax.swing.JLabel lblBackgroundCadastro;
    private javax.swing.JLabel lblBackgroundTabela;
    private javax.swing.JLabel lblCPF;
    private javax.swing.JLabel lblData;
    private javax.swing.JLabel lblDataNascimento;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblHora;
    private javax.swing.JLabel lblNome;
    private javax.swing.JLabel lblObservacoes;
    private javax.swing.JLabel lblItensSelecionados;
    private javax.swing.JLabel lblSubtituloGerenciaTecnicos;
    private javax.swing.JLabel lblTitleGerenciaTecnicos;
    private javax.swing.JList<Item> lstItens;
    private javax.swing.JScrollPane scrollItens;
    private javax.swing.JScrollPane tmServicos;
    private com.ifcolab.safesoft.components.CustomTextArea txtObeservacoes;
    private javax.swing.JScrollPane txtObservacoesScrollPane;
    // End of variables declaration//GEN-END:variables
}
