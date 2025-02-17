package com.ifcolab.safesoft.controller.tablemodel;

import com.ifcolab.safesoft.model.Servico;

import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class TMServicoDia extends AbstractTableModel {
    
    private List<Servico> servicos;
    private final int COL_HORARIO = 0;
    private final int COL_Cliente = 1;
    private final int COL_TECNICO = 2;
    private final int COL_CLIENTE = 3;
    private final int COL_itens = 4;
    private final int COL_STATUS = 5;
    private final int COL_ACAO = 6;
    
    private final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
    
    public TMServicoDia(List<Servico> servicos) {
        this.servicos = servicos;
    }
    
    @Override
    public int getRowCount() {
        return servicos.size();
    }
    
    @Override
    public int getColumnCount() {
        return 7;
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (servicos.isEmpty()) {
            return null;
        }
        
        Servico servico = servicos.get(rowIndex);
        
        return switch (columnIndex) {
            case COL_HORARIO -> servico.getDataHora().format(timeFormatter);
            case COL_Cliente -> servico.getCliente().getNome();
            case COL_TECNICO -> servico.getTecnico().getNome();
            case COL_CLIENTE -> servico.getSuporte().getNome();
            case COL_itens -> servico.getItens().stream()
                    .map(proc -> proc.getDescricao())
                    .reduce((a, b) -> a + ", " + b)
                    .orElse("");
            case COL_STATUS -> servico.getStatus();
            case COL_ACAO -> "[*]";
            default -> "";
        };
    }
    
    @Override
    public String getColumnName(int column) {
        return switch (column) {
            case COL_HORARIO -> "Horário";
            case COL_Cliente -> "Cliente";
            case COL_TECNICO -> "Tecnico";
            case COL_CLIENTE -> "Suporte";
            case COL_itens -> "Itens";
            case COL_STATUS -> "Status";
            case COL_ACAO -> "Ação";
            default -> "";
        };
    }
    
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }
    
    public Servico getServico(int row) {
        if (row >= 0 && row < servicos.size()) {
            return servicos.get(row);
        }
        return null;
    }
    
    public void atualizarServicos(List<Servico> novasServicos) {
        this.servicos = novasServicos;
        fireTableDataChanged();
    }
}