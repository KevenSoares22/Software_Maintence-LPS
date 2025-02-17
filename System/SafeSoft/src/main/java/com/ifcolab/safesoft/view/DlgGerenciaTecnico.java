package com.ifcolab.safesoft.view;

import com.ifcolab.safesoft.controller.TecnicoController;
import com.ifcolab.safesoft.model.Tecnico;
import com.ifcolab.safesoft.model.enums.AreaGestao;
import com.ifcolab.safesoft.model.enums.TipoSexo;
import com.ifcolab.safesoft.model.exceptions.ClienteException;
import com.ifcolab.safesoft.model.exceptions.ValidateException;
import java.text.ParseException;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.text.MaskFormatter;


public class DlgGerenciaTecnico extends javax.swing.JDialog {

    private TecnicoController controller;
    private int idTecnicoEditando;

    
    public DlgGerenciaTecnico(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        
        controller = new TecnicoController();
        idTecnicoEditando = -1;
        
        this.configurarComboBoxes();
        this.adicionarMascaraNosCampos();
        this.habilitarFormulario(false);
        this.limparFormulario();
        
        // Adicionar listener de duplo clique
        grdTecnicos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                grdTecnicosMouseClicked(evt);
            }
        });
        
        controller.atualizarTabela(grdTecnicos);
    }
    
    private void configurarComboBoxes() {
        // Adiciona todas as especializações ao ComboBox
        cboAreaGestao.removeAllItems();
        for (AreaGestao esp : AreaGestao.values()) {
            cboAreaGestao.addItem(esp);
        }
        
        // Configurar ComboBox de Sexo
        cboSexo.removeAllItems();
        for (TipoSexo s : TipoSexo.values()) {
            cboSexo.addItem(s);
        }
    }
    
    private void adicionarMascaraNosCampos() {
        try {
            // Máscara para CPF: 999.999.999-99
            MaskFormatter maskCPF = new MaskFormatter("###.###.###-##");
            maskCPF.setPlaceholderCharacter('_');
            maskCPF.install(fEdtCPF);
            
            // Máscara para telefone: (99) 99999-9999
            MaskFormatter maskTelefone = new MaskFormatter("(##) #####-####");
            maskTelefone.setPlaceholderCharacter('_');
            maskTelefone.install(fEdtTelefone);
            
            // Máscara para data: dd/mm/yyyy
            MaskFormatter maskData = new MaskFormatter("##/##/####");
            maskData.setPlaceholderCharacter('_');
            maskData.install(fEdtDataNascimento);
            
        } catch (ParseException ex) {
            Logger.getLogger(DlgGerenciaCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void habilitarFormulario(boolean habilitar) {
        edtNome.setEnabled(habilitar);
        edtEmail.setEnabled(habilitar);
        fEdtCPF.setEnabled(habilitar);
        cboSexo.setEnabled(habilitar);
        fEdtDataNascimento.setEnabled(habilitar);
        fEdtTelefone.setEnabled(habilitar);
        edtEndereco.setEnabled(habilitar);
        edtcod.setEnabled(habilitar);
        cboAreaGestao.setEnabled(habilitar);
        btnSalvar.setEnabled(habilitar);
    }

    private void limparFormulario() {
        edtNome.setText("");
        edtEmail.setText("");
        fEdtCPF.setText("");
        cboSexo.setSelectedItem(null);
        fEdtDataNascimento.setText("");
        fEdtTelefone.setText("");
        edtEndereco.setText("");
        edtcod.setText("");
        cboAreaGestao.setSelectedItem(null);
    }

    private void preencherFormulario(Tecnico tecnico) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        edtNome.setText(tecnico.getNome());
        edtEmail.setText(tecnico.getEmail());
        fEdtCPF.setText(tecnico.getCpf());
        cboSexo.setSelectedItem(tecnico.getSexo());
        fEdtDataNascimento.setText(tecnico.getDataNascimento().format(formatter));
        fEdtTelefone.setText(tecnico.getTelefone());
        edtEndereco.setText(tecnico.getEndereco());
        edtcod.setText(tecnico.getCod());
        cboAreaGestao.setSelectedItem(tecnico.getAreagestao());
    }

    private Object getObjetoSelecionadoNaGrid() {
        int rowCliked = grdTecnicos.getSelectedRow();
        Object obj = null;
        if (rowCliked >= 0) {
            obj = grdTecnicos.getModel().getValueAt(rowCliked, -1);
        }
        return obj;
    }




    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblCPF = new javax.swing.JLabel();
        lblNome = new javax.swing.JLabel();
        lblEmail = new javax.swing.JLabel();
        lblSexo = new javax.swing.JLabel();
        lblDataNascimento = new javax.swing.JLabel();
        lblTelefone = new javax.swing.JLabel();
        lblEndereco = new javax.swing.JLabel();
        lblcod = new javax.swing.JLabel();
        lblAreaGestao = new javax.swing.JLabel();
        fEdtCPF = new com.ifcolab.safesoft.components.CustomFormattedTextField();
        fEdtDataNascimento = new com.ifcolab.safesoft.components.CustomFormattedTextField();
        fEdtTelefone = new com.ifcolab.safesoft.components.CustomFormattedTextField();
        edtEmail = new com.ifcolab.safesoft.components.CustomTextField();
        edtNome = new com.ifcolab.safesoft.components.CustomTextField();
        edtcod = new com.ifcolab.safesoft.components.CustomTextField();
        edtEndereco = new com.ifcolab.safesoft.components.CustomTextField();
        btnAdicionar = new com.ifcolab.safesoft.components.PrimaryCustomButton();
        btnSalvar = new com.ifcolab.safesoft.components.SecondaryCustomButton();
        btnEditar = new com.ifcolab.safesoft.components.SecondaryCustomButton();
        btnRemover = new com.ifcolab.safesoft.components.SecondaryCustomButton();
        cboAreaGestao = new com.ifcolab.safesoft.components.CustomComboBox();
        cboSexo = new com.ifcolab.safesoft.components.CustomComboBox();
        tmTecnicos = new javax.swing.JScrollPane();
        grdTecnicos = new com.ifcolab.safesoft.components.CustomTable();
        lblSubtituloGerenciaTecnicos = new javax.swing.JLabel();
        lblTitleGerenciaTecnicos = new javax.swing.JLabel();
        lblBackgroundCadastro = new javax.swing.JLabel();
        lblBackgroundTabela = new javax.swing.JLabel();
        lblBackground = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1350, 870));
        getContentPane().setLayout(null);

        lblCPF.setForeground(new java.awt.Color(51, 51, 51));
        lblCPF.setText("CPF");
        getContentPane().add(lblCPF);
        lblCPF.setBounds(890, 120, 50, 17);

        lblNome.setForeground(new java.awt.Color(51, 51, 51));
        lblNome.setText("Nome");
        getContentPane().add(lblNome);
        lblNome.setBounds(70, 120, 170, 17);

        lblEmail.setForeground(new java.awt.Color(51, 51, 51));
        lblEmail.setText("Email");
        getContentPane().add(lblEmail);
        lblEmail.setBounds(530, 120, 240, 17);

        lblSexo.setForeground(new java.awt.Color(51, 51, 51));
        lblSexo.setText("Sexo");
        getContentPane().add(lblSexo);
        lblSexo.setBounds(1140, 120, 50, 17);

        lblDataNascimento.setForeground(new java.awt.Color(51, 51, 51));
        lblDataNascimento.setText("Data de Nascimento");
        getContentPane().add(lblDataNascimento);
        lblDataNascimento.setBounds(70, 190, 140, 17);

        lblTelefone.setForeground(new java.awt.Color(51, 51, 51));
        lblTelefone.setText("Telefone");
        getContentPane().add(lblTelefone);
        lblTelefone.setBounds(720, 190, 140, 17);

        lblEndereco.setForeground(new java.awt.Color(51, 51, 51));
        lblEndereco.setText("Endereço");
        getContentPane().add(lblEndereco);
        lblEndereco.setBounds(310, 190, 140, 17);

        lblcod.setForeground(new java.awt.Color(51, 51, 51));
        lblcod.setText("cod");
        getContentPane().add(lblcod);
        lblcod.setBounds(1030, 190, 140, 17);

        lblAreaGestao.setForeground(new java.awt.Color(51, 51, 51));
        lblAreaGestao.setText("Especialização");
        getContentPane().add(lblAreaGestao);
        lblAreaGestao.setBounds(70, 260, 140, 17);

        fEdtCPF.setText("CPF");
        getContentPane().add(fEdtCPF);
        fEdtCPF.setBounds(880, 140, 230, 38);

        fEdtDataNascimento.setText("Data de Nascimento");
        getContentPane().add(fEdtDataNascimento);
        fEdtDataNascimento.setBounds(60, 210, 210, 38);

        fEdtTelefone.setText("Telefone");
        getContentPane().add(fEdtTelefone);
        fEdtTelefone.setBounds(710, 210, 280, 38);

        edtEmail.setText("E-mail");
        edtEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                edtEmailActionPerformed(evt);
            }
        });
        getContentPane().add(edtEmail);
        edtEmail.setBounds(520, 140, 330, 40);

        edtNome.setText("Nome");
        edtNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                edtNomeActionPerformed(evt);
            }
        });
        getContentPane().add(edtNome);
        edtNome.setBounds(60, 140, 430, 40);

        edtcod.setText("cod");
        edtcod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                edtcodActionPerformed(evt);
            }
        });
        getContentPane().add(edtcod);
        edtcod.setBounds(1020, 210, 210, 40);

        edtEndereco.setText("Endereço");
        edtEndereco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                edtEnderecoActionPerformed(evt);
            }
        });
        getContentPane().add(edtEndereco);
        edtEndereco.setBounds(300, 210, 380, 40);

        btnAdicionar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/addsquare.png"))); // NOI18N
        btnAdicionar.setText(" Adicionar");
        btnAdicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionarActionPerformed(evt);
            }
        });
        getContentPane().add(btnAdicionar);
        btnAdicionar.setBounds(60, 80, 170, 30);

        btnSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/save.png"))); // NOI18N
        btnSalvar.setText(" Salvar");
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });
        getContentPane().add(btnSalvar);
        btnSalvar.setBounds(690, 80, 170, 30);

        btnEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/editsquare.png"))); // NOI18N
        btnEditar.setText(" Editar");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });
        getContentPane().add(btnEditar);
        btnEditar.setBounds(270, 80, 170, 30);

        btnRemover.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/trash.png"))); // NOI18N
        btnRemover.setText(" Remover");
        btnRemover.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoverActionPerformed(evt);
            }
        });
        getContentPane().add(btnRemover);
        btnRemover.setBounds(480, 80, 170, 30);
        getContentPane().add(cboAreaGestao);
        cboAreaGestao.setBounds(60, 280, 330, 44);
        getContentPane().add(cboSexo);
        cboSexo.setBounds(1130, 140, 160, 44);

        grdTecnicos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        grdTecnicos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                grdTecnicosMouseClicked(evt);
            }
        });
        tmTecnicos.setViewportView(grdTecnicos);

        getContentPane().add(tmTecnicos);
        tmTecnicos.setBounds(40, 380, 1260, 420);

        lblSubtituloGerenciaTecnicos.setFont(new java.awt.Font("Fira Sans Medium", 0, 13)); // NOI18N
        lblSubtituloGerenciaTecnicos.setForeground(new java.awt.Color(102, 102, 102));
        lblSubtituloGerenciaTecnicos.setText("Adicione, edite ou remova informações dos tecnicos, incluindo especialidades e dados de contato.");
        getContentPane().add(lblSubtituloGerenciaTecnicos);
        lblSubtituloGerenciaTecnicos.setBounds(30, 40, 780, 17);

        lblTitleGerenciaTecnicos.setFont(new java.awt.Font("Fira Sans SemiBold", 0, 18)); // NOI18N
        lblTitleGerenciaTecnicos.setForeground(new java.awt.Color(51, 51, 51));
        lblTitleGerenciaTecnicos.setText("Gerenciar Tecnicos");
        getContentPane().add(lblTitleGerenciaTecnicos);
        lblTitleGerenciaTecnicos.setBounds(30, 20, 210, 22);

        lblBackgroundCadastro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/backgroundCrud.png"))); // NOI18N
        getContentPane().add(lblBackgroundCadastro);
        lblBackgroundCadastro.setBounds(-10, 60, 1330, 290);

        lblBackgroundTabela.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/backgroundTableModel.png"))); // NOI18N
        getContentPane().add(lblBackgroundTabela);
        lblBackgroundTabela.setBounds(-10, 340, 1390, 500);

        lblBackground.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/background.png"))); // NOI18N
        lblBackground.setText("jLabel3");
        getContentPane().add(lblBackground);
        lblBackground.setBounds(0, 0, 1350, 850);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void edtEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_edtEmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_edtEmailActionPerformed

    private void edtNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_edtNomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_edtNomeActionPerformed

    private void edtcodActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_edtcodActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_edtcodActionPerformed

    private void edtEnderecoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_edtEnderecoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_edtEnderecoActionPerformed

    private void btnAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionarActionPerformed
        this.limparFormulario();
        this.habilitarFormulario(true);
    }//GEN-LAST:event_btnAdicionarActionPerformed

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        try {
            if (idTecnicoEditando > 0) {
                Tecnico tecnicoAtual = controller.find(idTecnicoEditando);
                controller.atualizar(
                    idTecnicoEditando,
                    edtNome.getText(),
                    edtEmail.getText(),
                    tecnicoAtual.getSenha(),
                    fEdtCPF.getText(),
                    (TipoSexo) cboSexo.getSelectedItem(),
                    fEdtDataNascimento.getText(),
                    fEdtTelefone.getText(),
                    edtEndereco.getText(),
                    edtcod.getText(),
                    (AreaGestao) cboAreaGestao.getSelectedItem(),
                    tecnicoAtual.getAvatar()
                );
            } else {
                controller.cadastrar(
                    edtNome.getText(),
                    edtEmail.getText(),
                    fEdtCPF.getText(),
                    (TipoSexo) cboSexo.getSelectedItem(),
                    fEdtDataNascimento.getText(),
                    fEdtTelefone.getText(),
                    edtEndereco.getText(),
                    edtcod.getText(),
                    (AreaGestao) cboAreaGestao.getSelectedItem(),
                    1
                );
            }

            this.idTecnicoEditando = -1;
            controller.atualizarTabela(grdTecnicos);
            this.habilitarFormulario(false);
            this.limparFormulario();

        } catch (ValidateException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        Tecnico tecnicoEditando = (Tecnico) this.getObjetoSelecionadoNaGrid();

        if (tecnicoEditando == null)
        JOptionPane.showMessageDialog(this, "Primeiro selecione um registro na tabela.");
        else {
            this.limparFormulario();
            this.habilitarFormulario(true);
            this.preencherFormulario(tecnicoEditando);
            this.idTecnicoEditando = tecnicoEditando.getId();
        }        
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnRemoverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoverActionPerformed
        Tecnico tecnicoExcluido = (Tecnico) this.getObjetoSelecionadoNaGrid();

        if (tecnicoExcluido == null)
        JOptionPane.showMessageDialog(this, "Primeiro selecione um registro na tabela.");
        else {
            int response = JOptionPane.showConfirmDialog(null,
                "Deseja excluir o Tecnico \n("
                + tecnicoExcluido.getNome() + ", "
                + tecnicoExcluido.getCpf() + ") ?",
                "Confirmar exclusão",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE);
            if (response == JOptionPane.OK_OPTION) {
                try {
                    controller.excluir(tecnicoExcluido);
                    controller.atualizarTabela(grdTecnicos);
                    JOptionPane.showMessageDialog(this, "Exclusão feita com sucesso!");
                } catch (ClienteException ex) {
                    JOptionPane.showMessageDialog(this, ex.getMessage());
                }
            }
        }
    }//GEN-LAST:event_btnRemoverActionPerformed

    private void grdTecnicosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_grdTecnicosMouseClicked
        if (evt.getClickCount() == 2) {
            btnEditarActionPerformed(null);
        }
    }//GEN-LAST:event_grdTecnicosMouseClicked



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.ifcolab.safesoft.components.PrimaryCustomButton btnAdicionar;
    private com.ifcolab.safesoft.components.SecondaryCustomButton btnEditar;
    private com.ifcolab.safesoft.components.SecondaryCustomButton btnRemover;
    private com.ifcolab.safesoft.components.SecondaryCustomButton btnSalvar;
    private com.ifcolab.safesoft.components.CustomComboBox cboAreaGestao;
    private com.ifcolab.safesoft.components.CustomComboBox cboSexo;
    private com.ifcolab.safesoft.components.CustomTextField edtcod;
    private com.ifcolab.safesoft.components.CustomTextField edtEmail;
    private com.ifcolab.safesoft.components.CustomTextField edtEndereco;
    private com.ifcolab.safesoft.components.CustomTextField edtNome;
    private com.ifcolab.safesoft.components.CustomFormattedTextField fEdtCPF;
    private com.ifcolab.safesoft.components.CustomFormattedTextField fEdtDataNascimento;
    private com.ifcolab.safesoft.components.CustomFormattedTextField fEdtTelefone;
    private com.ifcolab.safesoft.components.CustomTable grdTecnicos;
    private javax.swing.JLabel lblBackground;
    private javax.swing.JLabel lblBackgroundCadastro;
    private javax.swing.JLabel lblBackgroundTabela;
    private javax.swing.JLabel lblCPF;
    private javax.swing.JLabel lblcod;
    private javax.swing.JLabel lblDataNascimento;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblEndereco;
    private javax.swing.JLabel lblAreaGestao;
    private javax.swing.JLabel lblNome;
    private javax.swing.JLabel lblSexo;
    private javax.swing.JLabel lblSubtituloGerenciaTecnicos;
    private javax.swing.JLabel lblTelefone;
    private javax.swing.JLabel lblTitleGerenciaTecnicos;
    private javax.swing.JScrollPane tmTecnicos;
    // End of variables declaration//GEN-END:variables
}
