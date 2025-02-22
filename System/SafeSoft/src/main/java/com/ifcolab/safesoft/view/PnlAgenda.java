package com.ifcolab.safesoft.view;

import com.ifcolab.safesoft.components.PrimaryCustomButton;
import com.ifcolab.safesoft.components.SecondaryCustomButton;
import com.ifcolab.safesoft.controller.ServicoController;
import com.ifcolab.safesoft.model.Servico;
import com.ifcolab.safesoft.model.ConfiguracaoSistema;
import com.ifcolab.safesoft.model.Item;
import com.ifcolab.safesoft.model.dao.ConfiguracaoSistemaDAO;
import java.awt.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.swing.*;
import java.util.Map;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PnlAgenda extends javax.swing.JPanel {
    private final ServicoController servicoController;
    private ConfiguracaoSistema config;
    private LocalDate dataAtual;
    private JPanel pnlHeader;
    private JPanel pnlAgenda;
    private JLabel lblMes;
    private SecondaryCustomButton btnAnterior;
    private SecondaryCustomButton btnProximo;
    
    public PnlAgenda() {
        servicoController = new ServicoController();
        ConfiguracaoSistemaDAO configDAO = new ConfiguracaoSistemaDAO();
        config = configDAO.getConfiguracao();
        dataAtual = LocalDate.now();
        
        initComponents();
    }
    
    private void initComponents() {
        
        setLayout(null);
        setBackground(Color.WHITE);
        setBounds(0, 0, 1040, 730);
        
        JLabel lblTitle = new JLabel("Serviços");
        lblTitle.setFont(new Font("Segoe UI SemiBold", Font.PLAIN, 18));
        lblTitle.setForeground(new Color(51, 51, 51));
        lblTitle.setBounds(30, 20, 210, 22);
        add(lblTitle);
        
        JLabel lblSubtitle = new JLabel("Visualize e gerencie as servico agendadas.");
        lblSubtitle.setFont(new Font("Segoe UI Medium", Font.PLAIN, 13));
        lblSubtitle.setForeground(new Color(102, 102, 102));
        lblSubtitle.setBounds(30, 40, 720, 17);
        add(lblSubtitle);
        
        PrimaryCustomButton btnNovoServico = new PrimaryCustomButton("Novo Serviço");
        btnNovoServico.setBounds(810, 30, 200, 30);
        btnNovoServico.addActionListener(e -> abrirDialogNovoServico());
        add(btnNovoServico);
        
        pnlHeader = new JPanel();
        pnlHeader.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
        pnlHeader.setBounds(30, 80, 980, 50); // Ajustado para ficar abaixo do título
        pnlHeader.setBackground(Color.WHITE);
        pnlHeader.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(230, 230, 230)));
        
        btnAnterior = new SecondaryCustomButton("←");
        btnProximo = new SecondaryCustomButton("→");
        lblMes = new JLabel();
        lblMes.setFont(new Font("Segoe UI", Font.BOLD, 16));
        
        btnAnterior.addActionListener(e -> {
            dataAtual = dataAtual.minusDays(1);
            atualizarAgenda();
        });
        
        btnProximo.addActionListener(e -> {
            dataAtual = dataAtual.plusDays(1);
            atualizarAgenda();
        });
        
        pnlHeader.add(btnAnterior);
        pnlHeader.add(lblMes);
        pnlHeader.add(btnProximo);
        
        pnlAgenda = new JPanel();
        pnlAgenda.setLayout(new BoxLayout(pnlAgenda, BoxLayout.Y_AXIS));
        pnlAgenda.setBackground(Color.WHITE);
        
        JScrollPane scrollPane = new JScrollPane(pnlAgenda);
        scrollPane.setBounds(30, 150, 980, 550); 
        scrollPane.setBorder(null);
        scrollPane.getViewport().setBackground(Color.WHITE);
        
        add(pnlHeader);
        add(scrollPane);
        
        atualizarAgenda();
    }
    
    private void atualizarAgenda() {
        pnlAgenda.removeAll();
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd 'de' MMMM 'de' yyyy");
        lblMes.setText(dataAtual.format(formatter));
        
        Map<LocalTime, List<Servico>> servicoPorHorario =
            servicoController.getServicosOrganizadasPorHorario(dataAtual);
            
        servicoPorHorario.forEach((horario, servicoDoHorario) -> {
            JPanel pnlHorario = criarPainelHorario(horario);
            JPanel pnlServicos = (JPanel) ((BorderLayout) pnlHorario.getLayout())
                                          .getLayoutComponent(BorderLayout.CENTER);
            
            for (Servico servico : servicoDoHorario) {
                pnlServicos.add(criarPainelServico(servico));
            }
            
            pnlAgenda.add(pnlHorario);
        });
        
        pnlAgenda.revalidate();
        pnlAgenda.repaint();
    }
    
    private JPanel criarPainelHorario(LocalTime hora) {
        JPanel panel = new JPanel(new BorderLayout(10, 0));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(230, 230, 230)));
        

        JPanel pnlServicos = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        pnlServicos.setBackground(Color.WHITE);
        
        panel.setMinimumSize(new Dimension(980, 100));
        panel.setPreferredSize(new Dimension(980, 100));
        panel.add(pnlServicos, BorderLayout.CENTER);
        
        return panel;
    }
    
    private JPanel criarPainelServico(Servico servico) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        
        Color backgroundColor;
        Color borderColor;
        
        switch (servico.getStatus()) {
            case AGENDADA:
                backgroundColor = new Color(240, 247, 255); // Azul claro
                borderColor = new Color(200, 220, 255);     // Azul médio
                break;
            case CONFIRMADA:
                backgroundColor = new Color(236, 252, 241); // Verde claro
                borderColor = new Color(183, 235, 193);     // Verde médio
                break;
            case CONCLUIDA:
                backgroundColor = new Color(241, 241, 241); // Cinza claro
                borderColor = new Color(220, 220, 220);     // Cinza médio
                break;
            case CANCELADA:
                backgroundColor = new Color(255, 235, 235); // Vermelho claro
                borderColor = new Color(255, 200, 200);     // Vermelho médio
                break;
            default:
                backgroundColor = new Color(240, 247, 255);
                borderColor = new Color(200, 220, 255);
        }
        
        panel.setBackground(backgroundColor);
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(borderColor, 1),
            BorderFactory.createEmptyBorder(8, 12, 8, 12)
        ));
        
        panel.setPreferredSize(new Dimension(250, 90));
        panel.setMaximumSize(new Dimension(250, 90));
        panel.setMinimumSize(new Dimension(250, 90));
        
        JLabel lblStatus = new JLabel(servico.getStatus().toString());
        JLabel lblCliente = new JLabel("Cliente: " + servico.getCliente().getNome());
        JLabel lblTecnico = new JLabel("Tecnico: " + servico.getTecnico().getNome());
        JLabel lblSuporte = new JLabel("Suporte: " + servico.getSuporte().getNome());
        
        Font labelFont = new Font("Segoe UI", Font.PLAIN, 11);
        Font statusFont = new Font("Segoe UI", Font.BOLD, 11);
        
        lblStatus.setFont(statusFont);
        lblCliente.setFont(labelFont);
        lblTecnico.setFont(labelFont);
        lblSuporte.setFont(labelFont);
        
        switch (servico.getStatus()) {
            case AGENDADA:
                lblStatus.setForeground(new Color(51, 102, 204));  // Azul escuro
                break;
            case CONFIRMADA:
                lblStatus.setForeground(new Color(46, 125, 50));   // Verde escuro
                break;
            case CONCLUIDA:
                lblStatus.setForeground(new Color(88, 88, 88));    // Cinza escuro
                break;
            case CANCELADA:
                lblStatus.setForeground(new Color(211, 47, 47));   // Vermelho escuro
                break;
        }
        
        panel.add(lblStatus);
        panel.add(Box.createRigidArea(new Dimension(0, 2)));
        panel.add(lblCliente);
        panel.add(Box.createRigidArea(new Dimension(0, 2)));
        panel.add(lblTecnico);
        panel.add(Box.createRigidArea(new Dimension(0, 2)));
        panel.add(lblSuporte);
        
        if (!servico.getItens().isEmpty()) {
            panel.add(Box.createRigidArea(new Dimension(0, 2)));
            JLabel lblItens = new JLabel("<html>Itens: " +
                String.join(", ", servico.getItens().stream()
                    .map(Item::getNome)
                    .toArray(String[]::new)) + "</html>");
            lblItens.setFont(labelFont);
            panel.add(lblItens);
        }
        
        panel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                abrirDetalhesServico(servico);
            }
        });
        
        return panel;
    }

    
    private void abrirDetalhesServico(Servico servico) {
        Window window = SwingUtilities.getWindowAncestor(this);
        if (window instanceof JFrame) {
            DlgOpcoesServico dialog = new DlgOpcoesServico((JFrame) window, servico);
            dialog.setVisible(true);
            
            if (dialog.isAlteracaoRealizada()) {
                atualizarVisualizacao();
            }
        }
    }
    
    public void atualizarVisualizacao() {
        // Recarregar a configuração do sistema
        ConfiguracaoSistemaDAO configDAO = new ConfiguracaoSistemaDAO();
        config = configDAO.getConfiguracao();
        
      
        atualizarAgenda();
    }
    
    private void abrirDialogNovoServico() {
        Window window = SwingUtilities.getWindowAncestor(this);
        if (window instanceof JFrame) {
            DlgNovoServico dialog = new DlgNovoServico((JFrame) window, true);
            dialog.setLocationRelativeTo(window);
            dialog.setVisible(true);
            
            // Atualizar a agenda após fechar o diálogo
            if (dialog.isServicoCadastrada() || dialog.isServicoAlterada()) {
                atualizarVisualizacao();
            }
        }
    }
}
