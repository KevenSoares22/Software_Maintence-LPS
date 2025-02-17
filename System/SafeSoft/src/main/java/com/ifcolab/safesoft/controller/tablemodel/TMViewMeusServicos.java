package com.ifcolab.safesoft.controller.tablemodel;

import com.ifcolab.safesoft.model.Servico;
import com.ifcolab.safesoft.model.Item;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class TMViewMeusServicos extends AbstractTableModel {
    private List<Servico> lista;
    private final int COL_DATA = 0;
    private final int COL_HORA = 1;
    private final int COL_TECNICO = 2;
    private final int COL_Suporte = 3;
    private final int COL_itens = 4;
    private final int COL_STATUS = 5;
    
    public TMViewMeusServicos(List<Servico> lst) {
        this.lista = lst;
    }
    
    @Override
    public int getRowCount() {
        return lista.size();
    }
    
    @Override
    public int getColumnCount() {
        return 6;
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
            case COL_Suporte:
                return servico.getSuporte().getNome();
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
            case COL_Suporte:
                return "Suporte";
            case COL_itens:
                return "Itens";
            case COL_STATUS:
                return "Status";
            default:
                return "";
        }
    }
    
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }
    
} 