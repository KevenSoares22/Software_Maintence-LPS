package com.ifcolab.safesoft.controller.tablemodel;

import com.ifcolab.safesoft.model.Servico;
import com.ifcolab.safesoft.model.Item;

import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class TMViewHistoricoItem extends AbstractTableModel {
    private List<Servico> lista;
    private final int COL_DATA = 0;
    private final int COL_HORA = 1;
    private final int COL_TECNICO = 2;
    private final int COL_itens = 3;
    private final int COL_STATUS = 4;
    
    public TMViewHistoricoItem(List<Servico> lst) {
        this.lista = lst;
    }
    
    @Override
    public int getRowCount() {
        return lista.size();
    }
    
    @Override
    public int getColumnCount() {
        return 5;
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (lista.isEmpty()) {
            return null;
        }
        
        Servico servico = lista.get(rowIndex);
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        
        switch (columnIndex) {
            case COL_DATA:
                return servico.getDataHora().format(dateFormatter);
            case COL_HORA:
                return servico.getDataHora().format(timeFormatter);
            case COL_TECNICO:
                return servico.getTecnico().getNome();
            case COL_itens:
                return servico.getItens().stream()
                    .map(Item::getNome)
                    .reduce((a, b) -> a + ", " + b)
                    .orElse("");
            case COL_STATUS:
                return servico.getStatus().toString();
            default:
                return null;
        }
    }
    
    @Override
    public String getColumnName(int columnIndex) {
        switch (columnIndex) {
            case COL_DATA:
                return "Data";
            case COL_HORA:
                return "Hora";
            case COL_TECNICO:
                return "Tecnico";
            case COL_itens:
                return "Itens";
            case COL_STATUS:
                return "Status";
            default:
                return "";
        }
    }
    
    public Servico getServico(int rowIndex) {
        if (rowIndex >= 0 && rowIndex < lista.size()) {
            return lista.get(rowIndex);
        }
        return null;
    }
    
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }
} 