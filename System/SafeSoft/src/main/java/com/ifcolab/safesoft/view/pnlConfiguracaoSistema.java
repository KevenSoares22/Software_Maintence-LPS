package com.ifcolab.safesoft.view;

import com.ifcolab.safesoft.controller.ConfiguracaoSistemaController;
import com.ifcolab.safesoft.model.ConfiguracaoSistema;
import java.text.ParseException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;
import javax.swing.text.MaskFormatter;


public class pnlConfiguracaoSistema extends javax.swing.JPanel {

    private final ConfiguracaoSistemaController controller;
    private FrMenu parentFrame;
    
    public pnlConfiguracaoSistema() {
        initComponents();
        controller = new ConfiguracaoSistemaController();
        
        configurarInterface();
        carregarConfiguracao();
    }

    private void configurarInterface() {
        configurarMascaras();
        configurarSpinners();
    }
    
    private void configurarMascaras() {
        try {
            MaskFormatter maskHora = controller.getHoraMaskFormatter();
            maskHora.install(fEdtHorarioAbertura);
            maskHora.install(fEdtHorarioFechamento);
        } catch (ParseException ex) {
            exibirErro("Erro ao configurar máscaras", ex);
        }
    }
    
    private void configurarSpinners() {
        spnIntervaloServico.setModel(controller.getIntervaloServicoModel());
        spnTempoAntecedencia.setModel(controller.getTempoAntecedenciaModel());
        spnTempoMaxAgendamento.setModel(controller.getTempoMaxAgendamentoModel());
    }

    private void carregarConfiguracao() {
        try {
            ConfiguracaoSistema config = controller.getConfiguracao();
            preencherFormulario(config);
        } catch (Exception ex) {
            exibirErro("Erro ao carregar configurações", ex);
        }
    }
    
    private void preencherFormulario(ConfiguracaoSistema config) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        fEdtHorarioAbertura.setText(config.getHorarioAbertura().format(formatter));
        fEdtHorarioFechamento.setText(config.getHorarioFechamento().format(formatter));
        spnIntervaloServico.setValue(config.getIntervaloServicoMinutos());
        
        chkSegunda.setSelected(config.isFuncionaSegunda());
        chkTerca.setSelected(config.isFuncionaTerca());
        chkQuarta.setSelected(config.isFuncionaQuarta());
        chkQuinta.setSelected(config.isFuncionaQuinta());
        chkSexta.setSelected(config.isFuncionaSexta());
        chkSabado.setSelected(config.isFuncionaSabado());
        chkDomingo.setSelected(config.isFuncionaDomingo());
        
        spnTempoAntecedencia.setValue(config.getTempoMinimoAntecedenciaMinutos());
        spnTempoMaxAgendamento.setValue(config.getTempoMaximoAgendamentoDias());
    }

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            salvarConfiguracoes();
            exibirSucesso("Configurações salvas com sucesso!");
            atualizarInterface();
        } catch (Exception ex) {
            exibirErro("Erro ao salvar configurações", ex);
        }
    }
    
    private void salvarConfiguracoes() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        controller.atualizar(
            LocalTime.parse(fEdtHorarioAbertura.getText(), formatter),
            LocalTime.parse(fEdtHorarioFechamento.getText(), formatter),
            (int) spnIntervaloServico.getValue(),
            chkSegunda.isSelected(),
            chkTerca.isSelected(),
            chkQuarta.isSelected(),
            chkQuinta.isSelected(),
            chkSexta.isSelected(),
            chkSabado.isSelected(),
            chkDomingo.isSelected(),
            (int) spnTempoAntecedencia.getValue(),
            (int) spnTempoMaxAgendamento.getValue()
        );
    }
    
    private void atualizarInterface() {
        if (parentFrame != null) {
            parentFrame.atualizarAgenda();
            parentFrame.mostrarAgenda();
        }
    }
    
    private void exibirSucesso(String mensagem) {
        JOptionPane.showMessageDialog(this, mensagem, "Sucesso", 
            JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void exibirErro(String mensagem, Exception ex) {
        JOptionPane.showMessageDialog(this, 
            mensagem + ": " + ex.getMessage(),
            "Erro",
            JOptionPane.ERROR_MESSAGE);
    }

    public void setParentFrame(FrMenu parent) {
        this.parentFrame = parent;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTitleGerenciaTecnicos = new javax.swing.JLabel();
        lblSubtituloGerenciaTecnicos = new javax.swing.JLabel();
        lblHorarioFuncionamento1 = new javax.swing.JLabel();
        lblAbertura = new javax.swing.JLabel();
        fEdtHorarioAbertura = new com.ifcolab.safesoft.components.CustomFormattedTextField();
        fEdtHorarioFechamento = new com.ifcolab.safesoft.components.CustomFormattedTextField();
        lblFechamento = new javax.swing.JLabel();
        spnIntervaloServico = new javax.swing.JSpinner();
        lblDias = new javax.swing.JLabel();
        lblIntervaloServico1 = new javax.swing.JLabel();
        lblHorarioFuncionamento2 = new javax.swing.JLabel();
        chkSegunda = new javax.swing.JCheckBox();
        lblMin2 = new javax.swing.JLabel();
        lblMin1 = new javax.swing.JLabel();
        chkTerca = new javax.swing.JCheckBox();
        chkQuarta = new javax.swing.JCheckBox();
        chkQuinta = new javax.swing.JCheckBox();
        chkSexta = new javax.swing.JCheckBox();
        chkSabado = new javax.swing.JCheckBox();
        chkDomingo = new javax.swing.JCheckBox();
        lblHorarioFuncionamento = new javax.swing.JLabel();
        lblIntervaloServico2 = new javax.swing.JLabel();
        spnTempoAntecedencia = new javax.swing.JSpinner();
        lblIntervaloServico = new javax.swing.JLabel();
        spnTempoMaxAgendamento = new javax.swing.JSpinner();
        btnSalvar = new com.ifcolab.safesoft.components.PrimaryCustomButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(null);

        lblTitleGerenciaTecnicos.setFont(new java.awt.Font("Fira Sans SemiBold", 0, 18)); // NOI18N
        lblTitleGerenciaTecnicos.setForeground(new java.awt.Color(51, 51, 51));
        lblTitleGerenciaTecnicos.setText("Configurações");
        add(lblTitleGerenciaTecnicos);
        lblTitleGerenciaTecnicos.setBounds(30, 20, 210, 22);

        lblSubtituloGerenciaTecnicos.setFont(new java.awt.Font("Fira Sans Medium", 0, 13)); // NOI18N
        lblSubtituloGerenciaTecnicos.setForeground(new java.awt.Color(102, 102, 102));
        lblSubtituloGerenciaTecnicos.setText("Controle as informações do Sistemas.");
        add(lblSubtituloGerenciaTecnicos);
        lblSubtituloGerenciaTecnicos.setBounds(30, 40, 720, 17);

        lblHorarioFuncionamento1.setFont(new java.awt.Font("Fira Sans SemiBold", 0, 14)); // NOI18N
        lblHorarioFuncionamento1.setForeground(new java.awt.Color(51, 51, 51));
        lblHorarioFuncionamento1.setText("Horário de Funcionamento");
        add(lblHorarioFuncionamento1);
        lblHorarioFuncionamento1.setBounds(30, 90, 270, 18);

        lblAbertura.setForeground(new java.awt.Color(51, 51, 51));
        lblAbertura.setText("Abertura");
        add(lblAbertura);
        lblAbertura.setBounds(40, 120, 130, 17);

        fEdtHorarioAbertura.setText("Abertura");
        add(fEdtHorarioAbertura);
        fEdtHorarioAbertura.setBounds(30, 140, 450, 38);

        fEdtHorarioFechamento.setText("Fechamento");
        add(fEdtHorarioFechamento);
        fEdtHorarioFechamento.setBounds(510, 140, 460, 38);

        lblFechamento.setForeground(new java.awt.Color(51, 51, 51));
        lblFechamento.setText("Fechamento");
        add(lblFechamento);
        lblFechamento.setBounds(520, 120, 170, 17);
        add(spnIntervaloServico);
        spnIntervaloServico.setBounds(40, 210, 160, 27);

        lblDias.setForeground(new java.awt.Color(51, 51, 51));
        lblDias.setText("dias");
        add(lblDias);
        lblDias.setBounds(340, 500, 190, 17);

        lblIntervaloServico1.setForeground(new java.awt.Color(51, 51, 51));
        lblIntervaloServico1.setText("Intervalo entre consultas");
        add(lblIntervaloServico1);
        lblIntervaloServico1.setBounds(40, 190, 190, 17);

        lblHorarioFuncionamento2.setFont(new java.awt.Font("Fira Sans SemiBold", 0, 14)); // NOI18N
        lblHorarioFuncionamento2.setForeground(new java.awt.Color(51, 51, 51));
        lblHorarioFuncionamento2.setText("Dias de Funcionamento");
        add(lblHorarioFuncionamento2);
        lblHorarioFuncionamento2.setBounds(40, 270, 270, 18);

        chkSegunda.setText("Segunda");
        add(chkSegunda);
        chkSegunda.setBounds(40, 310, 90, 21);

        lblMin2.setForeground(new java.awt.Color(51, 51, 51));
        lblMin2.setText("min");
        add(lblMin2);
        lblMin2.setBounds(340, 440, 190, 17);

        lblMin1.setForeground(new java.awt.Color(51, 51, 51));
        lblMin1.setText("min");
        add(lblMin1);
        lblMin1.setBounds(210, 220, 190, 17);

        chkTerca.setText("Terça");
        add(chkTerca);
        chkTerca.setBounds(180, 310, 80, 21);

        chkQuarta.setText("Quarta");
        add(chkQuarta);
        chkQuarta.setBounds(310, 310, 100, 21);

        chkQuinta.setText("Quinta");
        add(chkQuinta);
        chkQuinta.setBounds(460, 310, 100, 21);

        chkSexta.setText("Sexta");
        add(chkSexta);
        chkSexta.setBounds(600, 310, 100, 21);

        chkSabado.setText("Sabado");
        add(chkSabado);
        chkSabado.setBounds(720, 310, 100, 21);

        chkDomingo.setText("Domingo");
        add(chkDomingo);
        chkDomingo.setBounds(850, 310, 100, 21);

        lblHorarioFuncionamento.setFont(new java.awt.Font("Fira Sans SemiBold", 0, 14)); // NOI18N
        lblHorarioFuncionamento.setForeground(new java.awt.Color(51, 51, 51));
        lblHorarioFuncionamento.setText("Outras Configurações");
        add(lblHorarioFuncionamento);
        lblHorarioFuncionamento.setBounds(40, 380, 270, 18);

        lblIntervaloServico2.setForeground(new java.awt.Color(51, 51, 51));
        lblIntervaloServico2.setText("Antecedência Mínima de Agendamento de Servicos");
        add(lblIntervaloServico2);
        lblIntervaloServico2.setBounds(40, 410, 440, 17);
        add(spnTempoAntecedencia);
        spnTempoAntecedencia.setBounds(40, 430, 290, 27);

        lblIntervaloServico.setForeground(new java.awt.Color(51, 51, 51));
        lblIntervaloServico.setText("Agendamento Máximo");
        add(lblIntervaloServico);
        lblIntervaloServico.setBounds(40, 470, 230, 17);
        add(spnTempoMaxAgendamento);
        spnTempoMaxAgendamento.setBounds(40, 490, 290, 27);

        btnSalvar.setText("Salvar Alterações");
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });
        add(btnSalvar);
        btnSalvar.setBounds(760, 650, 200, 30);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.ifcolab.safesoft.components.PrimaryCustomButton btnSalvar;
    private javax.swing.JCheckBox chkDomingo;
    private javax.swing.JCheckBox chkQuarta;
    private javax.swing.JCheckBox chkQuinta;
    private javax.swing.JCheckBox chkSabado;
    private javax.swing.JCheckBox chkSegunda;
    private javax.swing.JCheckBox chkSexta;
    private javax.swing.JCheckBox chkTerca;
    private com.ifcolab.safesoft.components.CustomFormattedTextField fEdtHorarioAbertura;
    private com.ifcolab.safesoft.components.CustomFormattedTextField fEdtHorarioFechamento;
    private javax.swing.JLabel lblAbertura;
    private javax.swing.JLabel lblDias;
    private javax.swing.JLabel lblFechamento;
    private javax.swing.JLabel lblHorarioFuncionamento;
    private javax.swing.JLabel lblHorarioFuncionamento1;
    private javax.swing.JLabel lblHorarioFuncionamento2;
    private javax.swing.JLabel lblIntervaloServico;
    private javax.swing.JLabel lblIntervaloServico1;
    private javax.swing.JLabel lblIntervaloServico2;
    private javax.swing.JLabel lblMin1;
    private javax.swing.JLabel lblMin2;
    private javax.swing.JLabel lblSubtituloGerenciaTecnicos;
    private javax.swing.JLabel lblTitleGerenciaTecnicos;
    private javax.swing.JSpinner spnIntervaloServico;
    private javax.swing.JSpinner spnTempoAntecedencia;
    private javax.swing.JSpinner spnTempoMaxAgendamento;
    // End of variables declaration//GEN-END:variables
}
