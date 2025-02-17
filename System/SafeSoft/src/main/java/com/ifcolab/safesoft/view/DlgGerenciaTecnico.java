package com.ifcolab.safesoft.view;

import com.ifcolab.safesoft.controller.suporteController;
import com.ifcolab.safesoft.model.suporte;
import com.ifcolab.safesoft.model.enums.TipoSexo;
import com.ifcolab.safesoft.model.exceptions.ClienteException;
import com.ifcolab.safesoft.model.exceptions.ValidateException;
import java.text.ParseException;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.text.MaskFormatter;

public class DlgGerenciasuporte extends javax.swing.JDialog {

    private suporteController controller;
    private int idsuporteEditando;


    public DlgGerenciasuporte(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        
        controller = new suporteController();
        idsuporteEditando = -1;
 
        this.configurarComboBoxes();
        this.adicionarMascaraNosCampos();
        this.habilitarFormulario(false);
        this.limparFormulario();
        
        grdsuportes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                grdsuportesMouseClicked(evt);
            }
        });
        
        controller.atualizarTabela(grdsuportes);
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
    }

    private void preencherFormulario(suporte suporte) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        
        edtNome.setText(suporte.getNome());
        edtEmail.setText(suporte.getEmail());
        fEdtCPF.setText(suporte.getCpf());
        cboSexo.setSelectedItem(suporte.getSexo());
        fEdtDataNascimento.setText(suporte.getDataNascimento().format(formatter));
        fEdtTelefone.setText(suporte.getTelefone());
        edtEndereco.setText(suporte.getEndereco());
        edtcod.setText(suporte.getCod());
    }

    private Object getObjetoSelecionadoNaGrid() {
        int rowCliked = grdsuportes.getSelectedRow();
        Object obj = null;
        if (rowCliked >= 0) {
            obj = grdsuportes.getModel().getValueAt(rowCliked, -1);
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
        edtEmail = new com.ifcolab.safesoft.components.CustomTextField();
        edtNome = new com.ifcolab.safesoft.components.CustomTextField();
        fEdtDataNascimento = new com.ifcolab.safesoft.components.CustomFormattedTextField();
        fEdtCPF = new com.ifcolab.safesoft.components.CustomFormattedTextField();
        fEdtTelefone = new com.ifcolab.safesoft.components.CustomFormattedTextField();
        edtcod = new com.ifcolab.safesoft.components.CustomTextField();
        edtEndereco = new com.ifcolab.safesoft.components.CustomTextField();
        btnAdicionar = new com.ifcolab.safesoft.components.PrimaryCustomButton();
        btnSalvar = new com.ifcolab.safesoft.components.SecondaryCustomButton();
        btnEditar = new com.ifcolab.safesoft.components.SecondaryCustomButton();
        btnRemover = new com.ifcolab.safesoft.components.SecondaryCustomButton();
        cboSexo = new com.ifcolab.safesoft.components.CustomComboBox();
        tmsuportes = new javax.swing.JScrollPane();
        grdsuportes = new com.ifcolab.safesoft.components.CustomTable();
        lblSubtituloGerenciaTecnicoes = new javax.swing.JLabel();
        lblTitleGerenciaTecnicoes = new javax.swing.JLabel();
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
        lblNome.setBounds(70, 140, 130, 17);

        lblEmail.setForeground(new java.awt.Color(51, 51, 51));
        lblEmail.setText("Email");
        getContentPane().add(lblEmail);
        lblEmail.setBounds(480, 140, 130, 17);

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
        lblTelefone.setBounds(720, 210, 140, 17);

        lblEndereco.setForeground(new java.awt.Color(51, 51, 51));
        lblEndereco.setText("Endereço");
        getContentPane().add(lblEndereco);
        lblEndereco.setBounds(300, 210, 240, 17);

        lblcod.setForeground(new java.awt.Color(51, 51, 51));
        lblcod.setText("cod");
        getContentPane().add(lblcod);
        lblcod.setBounds(1030, 210, 140, 17);

        edtEmail.setText("E-mail");
        edtEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                edtEmailActionPerformed(evt);
            }
        });
        getContentPane().add(edtEmail);
        edtEmail.setBounds(470, 160, 380, 40);

        edtNome.setText("Nome");
        edtNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                edtNomeActionPerformed(evt);
            }
        });
        getContentPane().add(edtNome);
        edtNome.setBounds(60, 160, 380, 40);

        fEdtDataNascimento.setText("Data de Nascimento");
        getContentPane().add(fEdtDataNascimento);
        fEdtDataNascimento.setBounds(60, 230, 200, 38);

        fEdtCPF.setText("CPF");
        getContentPane().add(fEdtCPF);
        fEdtCPF.setBounds(880, 160, 230, 38);

        fEdtTelefone.setText("Telefone");
        getContentPane().add(fEdtTelefone);
        fEdtTelefone.setBounds(710, 230, 280, 38);

        edtcod.setText("cod");
        edtcod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                edtcodActionPerformed(evt);
            }
        });
        getContentPane().add(edtcod);
        edtcod.setBounds(1020, 230, 210, 40);

        edtEndereco.setText("Endereço");
        edtEndereco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                edtEnderecoActionPerformed(evt);
            }
        });
        getContentPane().add(edtEndereco);
        edtEndereco.setBounds(290, 230, 390, 40);

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
        getContentPane().add(cboSexo);
        cboSexo.setBounds(1130, 160, 160, 44);

        grdsuportes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        grdsuportes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                grdsuportesMouseClicked(evt);
            }
        });
        tmsuportes.setViewportView(grdsuportes);

        getContentPane().add(tmsuportes);
        tmsuportes.setBounds(40, 370, 1260, 420);

        lblSubtituloGerenciaTecnicoes.setFont(new java.awt.Font("Fira Sans Medium", 0, 13)); // NOI18N
        lblSubtituloGerenciaTecnicoes.setForeground(new java.awt.Color(102, 102, 102));
        lblSubtituloGerenciaTecnicoes.setText("Controle as informações das suportes e dados pessoais.");
        getContentPane().add(lblSubtituloGerenciaTecnicoes);
        lblSubtituloGerenciaTecnicoes.setBounds(30, 40, 720, 17);

        lblTitleGerenciaTecnicoes.setFont(new java.awt.Font("Fira Sans SemiBold", 0, 18)); // NOI18N
        lblTitleGerenciaTecnicoes.setForeground(new java.awt.Color(51, 51, 51));
        lblTitleGerenciaTecnicoes.setText("Gerenciar suportes");
        getContentPane().add(lblTitleGerenciaTecnicoes);
        lblTitleGerenciaTecnicoes.setBounds(30, 20, 210, 22);

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

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        suporte suporteEditando = (suporte) this.getObjetoSelecionadoNaGrid();

        if (suporteEditando == null)
        JOptionPane.showMessageDialog(this, "Primeiro selecione um registro na tabela.");
        else {
            this.limparFormulario();
            this.habilitarFormulario(true);
            this.preencherFormulario(suporteEditando);
            this.idsuporteEditando = suporteEditando.getId();
        }      
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnRemoverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoverActionPerformed
         suporte suporteExcluido = (suporte) this.getObjetoSelecionadoNaGrid();

        if (suporteExcluido == null)
        JOptionPane.showMessageDialog(this, "Primeiro selecione um registro na tabela.");
        else {
            int response = JOptionPane.showConfirmDialog(null,
                "Deseja excluir a suporte \n("
                + suporteExcluido.getNome() + ", "
                + suporteExcluido.getCpf() + ") ?",
                "Confirmar exclusão",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE);
            if (response == JOptionPane.OK_OPTION) {
                try {
                    controller.excluir(suporteExcluido);
                    controller.atualizarTabela(grdsuportes);
                    JOptionPane.showMessageDialog(this, "Exclusão feita com sucesso!");
                } catch (ClienteException ex) {
                    JOptionPane.showMessageDialog(this, ex.getMessage());
                }
            }
        }
    }//GEN-LAST:event_btnRemoverActionPerformed

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        try {
            if (idsuporteEditando > 0) {
                suporte suporteAtual = controller.find(idsuporteEditando);
                controller.atualizar(
                    idsuporteEditando,
                    edtNome.getText(),
                    edtEmail.getText(),
                    suporteAtual.getSenha(),
                    fEdtCPF.getText(),
                    (TipoSexo) cboSexo.getSelectedItem(),
                    fEdtDataNascimento.getText(),
                    fEdtTelefone.getText(),
                    edtEndereco.getText(),
                    edtcod.getText(),
                    suporteAtual.getAvatar()
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
                    1
                );
            }

            this.idsuporteEditando = -1;
            controller.atualizarTabela(grdsuportes);
            this.habilitarFormulario(false);
            this.limparFormulario();

        } catch (ValidateException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void grdsuportesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_grdsuportesMouseClicked
        if (evt.getClickCount() == 2) {
            btnEditarActionPerformed(null);
        }
    }//GEN-LAST:event_grdsuportesMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.ifcolab.safesoft.components.PrimaryCustomButton btnAdicionar;
    private com.ifcolab.safesoft.components.SecondaryCustomButton btnEditar;
    private com.ifcolab.safesoft.components.SecondaryCustomButton btnRemover;
    private com.ifcolab.safesoft.components.SecondaryCustomButton btnSalvar;
    private com.ifcolab.safesoft.components.CustomComboBox cboSexo;
    private com.ifcolab.safesoft.components.CustomTextField edtcod;
    private com.ifcolab.safesoft.components.CustomTextField edtEmail;
    private com.ifcolab.safesoft.components.CustomTextField edtEndereco;
    private com.ifcolab.safesoft.components.CustomTextField edtNome;
    private com.ifcolab.safesoft.components.CustomFormattedTextField fEdtCPF;
    private com.ifcolab.safesoft.components.CustomFormattedTextField fEdtDataNascimento;
    private com.ifcolab.safesoft.components.CustomFormattedTextField fEdtTelefone;
    private com.ifcolab.safesoft.components.CustomTable grdsuportes;
    private javax.swing.JLabel lblBackground;
    private javax.swing.JLabel lblBackgroundCadastro;
    private javax.swing.JLabel lblBackgroundTabela;
    private javax.swing.JLabel lblcod;
    private javax.swing.JLabel lblCPF;
    private javax.swing.JLabel lblDataNascimento;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblEndereco;
    private javax.swing.JLabel lblNome;
    private javax.swing.JLabel lblSexo;
    private javax.swing.JLabel lblSubtituloGerenciaTecnicoes;
    private javax.swing.JLabel lblTelefone;
    private javax.swing.JLabel lblTitleGerenciaTecnicoes;
    private javax.swing.JScrollPane tmsuportes;
    // End of variables declaration//GEN-END:variables
}
