package com.ifcolab.safesoft.view;

import com.ifcolab.safesoft.controller.RecepcionistaController;
import com.ifcolab.safesoft.model.Recepcionista;
import com.ifcolab.safesoft.model.enums.TipoSexo;
import com.ifcolab.safesoft.model.exceptions.RecepcionistaException;
import com.ifcolab.safesoft.model.exceptions.ValidateException;
import java.text.ParseException;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.text.MaskFormatter;

public class DlgGerenciaRecepcionista extends javax.swing.JDialog {

    private RecepcionistaController controller;
    private int idRecepcionistaEditando;

    public DlgGerenciaRecepcionista(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        
        controller = new RecepcionistaController();
        idRecepcionistaEditando = -1;
 
        this.configurarComboBoxes();
        this.adicionarMascaraNosCampos();
        this.habilitarFormulario(false);
        this.limparFormulario();
        
        // Adicionar listener de duplo clique
        grdRecepcionistas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                grdclientesMouseClicked(evt);
            }
        });
        
        controller.atualizarTabela(grdRecepcionistas);
    }
    
    private void configurarComboBoxes() {
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
            maskData.install(fEdtDataContratacao);
            maskData.install(fEdtDataNascimento);
            
        } catch (ParseException ex) {
            Logger.getLogger(DlgGerenciaRecepcionista.class.getName()).log(Level.SEVERE, null, ex);
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
        fEdtDataContratacao.setEnabled(habilitar);
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
        fEdtDataContratacao.setText("");
    }

    private void preencherFormulario(Recepcionista recepcionista) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        
        edtNome.setText(recepcionista.getNome());
        edtEmail.setText(recepcionista.getEmail());
        fEdtCPF.setText(recepcionista.getCpf());
        cboSexo.setSelectedItem(recepcionista.getSexo());
        fEdtDataNascimento.setText(recepcionista.getDataNascimento().format(formatter));
        fEdtTelefone.setText(recepcionista.getTelefone());
        edtEndereco.setText(recepcionista.getEndereco());
        fEdtDataContratacao.setText(recepcionista.getDataContratacao().format(formatter));
    }

    private Object getObjetoSelecionadoNaGrid() {
        int rowCliked = grdRecepcionistas.getSelectedRow();
        Object obj = null;
        if (rowCliked >= 0) {
            obj = grdRecepcionistas.getModel().getValueAt(rowCliked, -1);
        }
        return obj;
    }

    private void grdclientesMouseClicked(java.awt.event.MouseEvent evt) {
        if (evt.getClickCount() == 2) {
            btnEditarActionPerformed(null);
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
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
        fEdtTelefone = new com.ifcolab.safesoft.components.CustomFormattedTextField();
        fEdtDataNascimento = new com.ifcolab.safesoft.components.CustomFormattedTextField();
        fEdtCPF = new com.ifcolab.safesoft.components.CustomFormattedTextField();
        edtEmail = new com.ifcolab.safesoft.components.CustomTextField();
        edtNome = new com.ifcolab.safesoft.components.CustomTextField();
        fEdtDataContratacao = new com.ifcolab.safesoft.components.CustomFormattedTextField();
        edtEndereco = new com.ifcolab.safesoft.components.CustomTextField();
        btnAdicionar = new com.ifcolab.safesoft.components.PrimaryCustomButton();
        btnSalvar = new com.ifcolab.safesoft.components.SecondaryCustomButton();
        btnEditar = new com.ifcolab.safesoft.components.SecondaryCustomButton();
        btnRemover = new com.ifcolab.safesoft.components.SecondaryCustomButton();
        lblHistoricoTecnico = new javax.swing.JLabel();
        cboSexo = new com.ifcolab.safesoft.components.CustomComboBox();
        tmRecepcionistas = new javax.swing.JScrollPane();
        grdRecepcionistas = new com.ifcolab.safesoft.components.CustomTable();
        lblSubtituloGerenciaTecnicos = new javax.swing.JLabel();
        lblTitleGerenciaTecnicos = new javax.swing.JLabel();
        lblBackgroundTabela = new javax.swing.JLabel();
        lblBackgroundCadastro = new javax.swing.JLabel();
        lblBackground = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1350, 870));
        getContentPane().setLayout(null);

        lblCPF.setForeground(new java.awt.Color(51, 51, 51));
        lblCPF.setText("CPF");
        getContentPane().add(lblCPF);
        lblCPF.setBounds(890, 140, 50, 17);

        lblNome.setForeground(new java.awt.Color(51, 51, 51));
        lblNome.setText("Nome");
        getContentPane().add(lblNome);
        lblNome.setBounds(70, 140, 160, 17);

        lblEmail.setForeground(new java.awt.Color(51, 51, 51));
        lblEmail.setText("Email");
        getContentPane().add(lblEmail);
        lblEmail.setBounds(560, 140, 170, 17);

        lblSexo.setForeground(new java.awt.Color(51, 51, 51));
        lblSexo.setText("Sexo");
        getContentPane().add(lblSexo);
        lblSexo.setBounds(1140, 140, 50, 17);

        lblDataNascimento.setForeground(new java.awt.Color(51, 51, 51));
        lblDataNascimento.setText("Data de Nascimento");
        getContentPane().add(lblDataNascimento);
        lblDataNascimento.setBounds(70, 210, 170, 17);

        lblTelefone.setForeground(new java.awt.Color(51, 51, 51));
        lblTelefone.setText("Telefone");
        getContentPane().add(lblTelefone);
        lblTelefone.setBounds(730, 210, 140, 17);

        lblEndereco.setForeground(new java.awt.Color(51, 51, 51));
        lblEndereco.setText("Endereço");
        getContentPane().add(lblEndereco);
        lblEndereco.setBounds(290, 210, 140, 17);

        fEdtTelefone.setText("Telefone");
        getContentPane().add(fEdtTelefone);
        fEdtTelefone.setBounds(720, 230, 270, 38);

        fEdtDataNascimento.setText("Data de Nascimento");
        getContentPane().add(fEdtDataNascimento);
        fEdtDataNascimento.setBounds(60, 230, 190, 38);

        fEdtCPF.setText("CPF");
        getContentPane().add(fEdtCPF);
        fEdtCPF.setBounds(880, 160, 230, 38);

        edtEmail.setText("E-mail");
        edtEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                edtEmailActionPerformed(evt);
            }
        });
        getContentPane().add(edtEmail);
        edtEmail.setBounds(550, 160, 300, 40);

        edtNome.setText("Nome");
        edtNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                edtNomeActionPerformed(evt);
            }
        });
        getContentPane().add(edtNome);
        edtNome.setBounds(60, 160, 460, 40);

        fEdtDataContratacao.setText("Data de Contratação");
        getContentPane().add(fEdtDataContratacao);
        fEdtDataContratacao.setBounds(1020, 230, 260, 38);

        edtEndereco.setText("Endereço");
        edtEndereco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                edtEnderecoActionPerformed(evt);
            }
        });
        getContentPane().add(edtEndereco);
        edtEndereco.setBounds(280, 230, 410, 40);

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

        lblHistoricoTecnico.setForeground(new java.awt.Color(51, 51, 51));
        lblHistoricoTecnico.setText("Data de Contratação");
        getContentPane().add(lblHistoricoTecnico);
        lblHistoricoTecnico.setBounds(1030, 210, 140, 17);
        getContentPane().add(cboSexo);
        cboSexo.setBounds(1130, 160, 160, 44);

        grdRecepcionistas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tmRecepcionistas.setViewportView(grdRecepcionistas);

        getContentPane().add(tmRecepcionistas);
        tmRecepcionistas.setBounds(40, 366, 1260, 420);

        lblSubtituloGerenciaTecnicos.setFont(new java.awt.Font("Segoe UI Medium", 0, 13)); // NOI18N
        lblSubtituloGerenciaTecnicos.setForeground(new java.awt.Color(102, 102, 102));
        lblSubtituloGerenciaTecnicos.setText("Organize as informações das recepcionistas, com opções para cadastro, edição e data de contratação");
        getContentPane().add(lblSubtituloGerenciaTecnicos);
        lblSubtituloGerenciaTecnicos.setBounds(30, 40, 650, 17);

        lblTitleGerenciaTecnicos.setFont(new java.awt.Font("Segoe UI SemiBold", 0, 18)); // NOI18N
        lblTitleGerenciaTecnicos.setForeground(new java.awt.Color(51, 51, 51));
        lblTitleGerenciaTecnicos.setText("Gerenciar Recepcionista");
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

    private void edtEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_edtEmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_edtEmailActionPerformed

    private void edtNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_edtNomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_edtNomeActionPerformed

    private void edtEnderecoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_edtEnderecoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_edtEnderecoActionPerformed

    private void btnAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionarActionPerformed
        this.limparFormulario();
        this.habilitarFormulario(true);
    }//GEN-LAST:event_btnAdicionarActionPerformed

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        try {
            if (idRecepcionistaEditando > 0) {
                Recepcionista recepcionistaAtual = controller.find(idRecepcionistaEditando);
                controller.atualizar(
                    idRecepcionistaEditando,
                    edtNome.getText(),
                    edtEmail.getText(),
                    recepcionistaAtual.getSenha(),
                    fEdtCPF.getText(),
                    (TipoSexo) cboSexo.getSelectedItem(),
                    fEdtDataNascimento.getText(),
                    fEdtTelefone.getText(),
                    edtEndereco.getText(),
                    fEdtDataContratacao.getText(),
                    recepcionistaAtual.getAvatar()
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
                    fEdtDataContratacao.getText(),
                    1
                );
            }

            this.idRecepcionistaEditando = -1;
            controller.atualizarTabela(grdRecepcionistas);
            this.habilitarFormulario(false);
            this.limparFormulario();

        } catch (ValidateException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        Recepcionista recepcionistaEditando = (Recepcionista) this.getObjetoSelecionadoNaGrid();

        if (recepcionistaEditando == null)
        JOptionPane.showMessageDialog(this, "Primeiro selecione um registro na tabela.");
        else {
            this.limparFormulario();
            this.habilitarFormulario(true);
            this.preencherFormulario(recepcionistaEditando);
            this.idRecepcionistaEditando = recepcionistaEditando.getId();
        }
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnRemoverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoverActionPerformed
        Recepcionista recepcionistaExcluido = (Recepcionista) this.getObjetoSelecionadoNaGrid();

        if (recepcionistaExcluido == null)
        JOptionPane.showMessageDialog(this, "Primeiro selecione um registro na tabela.");
        else {
            int response = JOptionPane.showConfirmDialog(null,
                "Deseja excluir o cliente \n("
                + recepcionistaExcluido.getNome() + ", "
                + recepcionistaExcluido.getCpf() + ") ?",
                "Confirmar exclusão",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE);
            if (response == JOptionPane.OK_OPTION) {
                try {
                    controller.excluir(recepcionistaExcluido);
                    controller.atualizarTabela(grdRecepcionistas);
                    JOptionPane.showMessageDialog(this, "Exclusão feita com sucesso!");
                } catch (RecepcionistaException ex) {
                    JOptionPane.showMessageDialog(this, ex.getMessage());
                }
            }
        }
    }//GEN-LAST:event_btnRemoverActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.ifcolab.safesoft.components.PrimaryCustomButton btnAdicionar;
    private com.ifcolab.safesoft.components.SecondaryCustomButton btnEditar;
    private com.ifcolab.safesoft.components.SecondaryCustomButton btnRemover;
    private com.ifcolab.safesoft.components.SecondaryCustomButton btnSalvar;
    private com.ifcolab.safesoft.components.CustomComboBox cboSexo;
    private com.ifcolab.safesoft.components.CustomTextField edtEmail;
    private com.ifcolab.safesoft.components.CustomTextField edtEndereco;
    private com.ifcolab.safesoft.components.CustomTextField edtNome;
    private com.ifcolab.safesoft.components.CustomFormattedTextField fEdtCPF;
    private com.ifcolab.safesoft.components.CustomFormattedTextField fEdtDataContratacao;
    private com.ifcolab.safesoft.components.CustomFormattedTextField fEdtDataNascimento;
    private com.ifcolab.safesoft.components.CustomFormattedTextField fEdtTelefone;
    private com.ifcolab.safesoft.components.CustomTable grdRecepcionistas;
    private javax.swing.JLabel lblBackground;
    private javax.swing.JLabel lblBackgroundCadastro;
    private javax.swing.JLabel lblBackgroundTabela;
    private javax.swing.JLabel lblCPF;
    private javax.swing.JLabel lblDataNascimento;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblEndereco;
    private javax.swing.JLabel lblHistoricoTecnico;
    private javax.swing.JLabel lblNome;
    private javax.swing.JLabel lblSexo;
    private javax.swing.JLabel lblSubtituloGerenciaTecnicos;
    private javax.swing.JLabel lblTelefone;
    private javax.swing.JLabel lblTitleGerenciaTecnicos;
    private javax.swing.JScrollPane tmRecepcionistas;
    // End of variables declaration//GEN-END:variables
}
