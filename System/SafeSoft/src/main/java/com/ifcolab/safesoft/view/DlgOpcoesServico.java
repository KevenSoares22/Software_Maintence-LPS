package com.ifcolab.safesoft.view;

import com.ifcolab.safesoft.components.PrimaryCustomButton;
import com.ifcolab.safesoft.components.SecondaryCustomButton;
import com.ifcolab.safesoft.model.Servico;
import com.ifcolab.safesoft.model.Pagamento;
import com.ifcolab.safesoft.model.Item;
import com.ifcolab.safesoft.model.enums.StatusServico;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.Component;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.util.List;

import com.ifcolab.safesoft.controller.OpcoesServicoController;

public class DlgOpcoesServico extends javax.swing.JDialog {
    private final OpcoesServicoController controller;
    private boolean alteracaoRealizada = false;
    
    public DlgOpcoesServico(JFrame parent, Servico servico) {
        super(parent, "Opções da Servico", true);
        this.controller = new OpcoesServicoController(servico);
        
        initComponents();
        configurarJanela();
    }
    
    private void initComponents() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainPanel.setBackground(Color.WHITE);
        
        JLabel lblTitulo = new JLabel("Detalhes da Servico");
        lblTitulo.setFont(new Font("Segoe UI SemiBold", Font.PLAIN, 18));
        lblTitulo.setForeground(new Color(51, 51, 51));
        lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(lblTitulo);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        
        JPanel cardPanel = criarPainelInformacoes();
        cardPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(cardPanel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        

        JPanel botoesPanel = criarPainelBotoes();
        botoesPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(botoesPanel);
        
        setContentPane(mainPanel);
    }
    
    private JPanel criarPainelInformacoes() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(new Color(240, 247, 255));
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 220, 255), 1),
            BorderFactory.createEmptyBorder(20, 25, 20, 25)
        ));
        
        JLabel lblStatus = new JLabel("Status: " + controller.getStatusFormatado());
        lblStatus.setFont(new Font("Segoe UI", Font.BOLD, 14));
        definirCorStatus(lblStatus, controller.getStatus());
        panel.add(lblStatus);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        
        adicionarLabel(panel, "Data/Hora: " + controller.getDataHoraFormatada());
        adicionarLabel(panel, "Cliente: " + controller.getNomeCliente());
        adicionarLabel(panel, "Tecnico: " + controller.getNomeTecnico());
        adicionarLabel(panel, "Suporte: " + controller.getNomeSuporte());
        
        List<String> items = controller.getItensFormatados();
        if (!items.isEmpty()) {
            panel.add(Box.createRigidArea(new Dimension(0, 10)));
            JLabel lblItens = new JLabel("<html><b>Itens:</b><br/>" +
                String.join("<br/>• ", items) + "</html>");
            lblItens.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            panel.add(lblItens);
        }
        
        panel.add(Box.createRigidArea(new Dimension(0, 15)));
        JLabel lblValorTotal = new JLabel("Valor Total: R$ " + 
            String.format("%.2f", controller.getValorTotal()));
        lblValorTotal.setFont(new Font("Segoe UI", Font.BOLD, 14));
        panel.add(lblValorTotal);
        
        return panel;
    }
    
    private void definirCorStatus(JLabel label, StatusServico status) {
        switch (status) {
            case AGENDADA:
                label.setForeground(new Color(51, 102, 204));  // Azul
                break;
            case CONFIRMADA:
                label.setForeground(new Color(46, 125, 50));   // Verde
                break;
            case CONCLUIDA:
                label.setForeground(new Color(88, 88, 88));    // Cinza
                break;
            case CANCELADA:
                label.setForeground(new Color(211, 47, 47));   // Vermelho
                break;
        }
    }
    
    private void adicionarLabel(JPanel panel, String texto) {
        panel.add(Box.createRigidArea(new Dimension(0, 5)));
        JLabel label = new JLabel(texto);
        label.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        panel.add(label);
    }
    
    private JPanel criarPainelBotoes() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBackground(Color.WHITE);
        
        JPanel innerPanel = new JPanel();
        innerPanel.setLayout(new BoxLayout(innerPanel, BoxLayout.Y_AXIS));
        innerPanel.setBackground(Color.WHITE);
        
        if (controller.podeConfirmarServico()) {
            adicionarBotao(innerPanel, new PrimaryCustomButton("Confirmar Servico"),
                          e -> confirmarServico());
            adicionarBotao(innerPanel, new SecondaryCustomButton("Cancelar Servico"),
                          e -> cancelarServico());
        }
        
        if (controller.podeRealizarServico()) {
            adicionarBotao(innerPanel, new PrimaryCustomButton("Realizar Servico"),
                          e -> realizarServico());
        }
        
        if (controller.podeFazerPagamento() && controller.podeGerenciarPagamentos()) {
            adicionarBotao(innerPanel, new PrimaryCustomButton("Realizar Pagamento"), 
                          e -> realizarPagamento());
        }
        
        if (controller.podeEmitirRecibo() && controller.podeGerenciarPagamentos()) {
            adicionarBotao(innerPanel, new SecondaryCustomButton("Emitir Recibo"), 
                          e -> emitirRecibo());
        }
        
        if (controller.podeVerRelatorios()) {
            adicionarBotao(innerPanel, new SecondaryCustomButton("Relatório"), 
                          e -> gerarRelatorio());
        }
        
        adicionarBotao(innerPanel, new SecondaryCustomButton("Editar Servico"),
                      e -> editarServico());
        
        panel.add(innerPanel);
        return panel;
    }
    
    private void adicionarBotao(JPanel panel, JButton botao, ActionListener listener) {
        botao.setAlignmentX(Component.CENTER_ALIGNMENT);
        botao.setMaximumSize(new Dimension(200, 30));
        botao.addActionListener(listener);
        panel.add(botao);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
    }
    
    private void configurarJanela() {
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setSize(550, 650);
        setLocationRelativeTo(getParent());
        setResizable(false);
    }
    
    private void confirmarServico() {
        int opcao = JOptionPane.showConfirmDialog(this,
            "Deseja realmente confirmar esta servico?",
            "Confirmar Servico",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE);
        
        if (opcao == JOptionPane.YES_OPTION) {
            try {
                controller.confirmarServico();
                alteracaoRealizada = true;
                JOptionPane.showMessageDialog(this,
                    "Servico confirmada com sucesso!",
                    "Sucesso",
                    JOptionPane.INFORMATION_MESSAGE);
                dispose();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this,
                    "Erro ao confirmar servico: " + e.getMessage(),
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private void cancelarServico() {
        int opcao = JOptionPane.showConfirmDialog(this,
            "Deseja realmente cancelar esta servico?\n\n" +
            "Esta ação não poderá ser desfeita.",
            "Cancelar Servico",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.WARNING_MESSAGE);
        
        if (opcao == JOptionPane.YES_OPTION) {
            try {
                controller.cancelarServico();
                alteracaoRealizada = true;
                JOptionPane.showMessageDialog(this,
                    "Servico cancelada com sucesso!",
                    "Sucesso",
                    JOptionPane.INFORMATION_MESSAGE);
                dispose();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this,
                    "Erro ao cancelar servico: " + e.getMessage(),
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private void realizarServico() {
        int opcao = JOptionPane.showConfirmDialog(this,
            "Deseja marcar esta servico como realizada?",
            "Realizar Servico",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE);
        
        if (opcao == JOptionPane.YES_OPTION) {
            try {
                controller.realizarServico();
                alteracaoRealizada = true;
                JOptionPane.showMessageDialog(this,
                    "Servico realizada com sucesso!\n" +
                    "Não se esqueça de registrar o pagamento.",
                    "Sucesso",
                    JOptionPane.INFORMATION_MESSAGE);
                dispose();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this,
                    "Erro ao realizar servico: " + e.getMessage(),
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private void realizarPagamento() {
       DlgPagamento dialog = new DlgPagamento(null, true, controller.getServico());
        dialog.setVisible(true);
        if (dialog.isPagamentoRealizado()) {
            alteracaoRealizada = true;
            dispose();
        } 
    }
    
    private void emitirRecibo() {
        try {
            List<Pagamento> pagamentos = controller.buscarPagamentosServico();

            if (pagamentos.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                    "Não há pagamento registrado para esta servico.",
                    "Aviso",
                    JOptionPane.WARNING_MESSAGE);
                return;
            }

            Pagamento pagamento = pagamentos.get(0);
            
            try {
                controller.gerarReciboPagamento(pagamento);
                
                JOptionPane.showMessageDialog(this,
                    "Recibo gerado com sucesso!",
                    "Sucesso",
                    JOptionPane.INFORMATION_MESSAGE);
                    
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this,
                    "Erro ao gerar recibo: " + e.getMessage(),
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                "Erro ao buscar pagamentos: " + e.getMessage(),
                "Erro",
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void editarServico() {
        DlgNovoServico dialog = new DlgNovoServico(null, true);
        dialog.preencherFormulario(controller.getServico());
        dialog.setVisible(true);
        if (dialog.isServicoAlterada()) {
            alteracaoRealizada = true;
            dispose();
        }
    }
    
    private void gerarRelatorio() {
    if (controller.getServico().getItens().isEmpty()) {
        JOptionPane.showMessageDialog(this,
            "Não há items registrados nesta servico.",
            "Aviso",
            JOptionPane.WARNING_MESSAGE);
        return;
    }
    
    if (controller.getServico().getItens().size() == 1) {
        Item item = controller.getServico().getItens().get(0);
        DlgRelatorio dialog = new DlgRelatorio(null, true, controller.getServico(), item);
        dialog.setVisible(true);
        if (dialog.isRelatorioSalvo()) {
            alteracaoRealizada = true;
            dispose();
        }
    } else {

        Item item = (Item) JOptionPane.showInputDialog(
            this,
            "Selecione o item:",
            "Gerar Relatório",
            JOptionPane.QUESTION_MESSAGE,
            null,
            controller.getServico().getItens().toArray(),
            controller.getServico().getItens().get(0)
        );
        
        if (item != null) {
            DlgRelatorio dialog = new DlgRelatorio(null, true, controller.getServico(), item);
            dialog.setVisible(true);
            if (dialog.isRelatorioSalvo()) {
                alteracaoRealizada = true;
                dispose();
            }
        }
    }
    }
    
    public boolean isAlteracaoRealizada() {
        return alteracaoRealizada;
    }
} 