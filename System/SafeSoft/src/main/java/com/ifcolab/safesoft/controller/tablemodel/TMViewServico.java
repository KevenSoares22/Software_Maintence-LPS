package com.ifcolab.safesoft.controller.tablemodel;

import com.ifcolab.safesoft.model.Servico;
import com.ifcolab.safesoft.model.Item;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import java.time.format.DateTimeFormatter;
import java.text.DecimalFormat;
import java.util.stream.Collectors;

public class TMViewServico extends AbstractTableModel {

    private List<Servico> lista;
    private final int COL_DATA_HORA = 0;
    private final int COL_CLIENTE = 1;
    private final int COL_TECNICO = 2;
    private final int COL_Suporte = 3;
    private final int COL_itens = 4;
    private final int COL_VALOR_TOTAL = 5;
    private final int COL_STATUS = 6;
    private final int COL_OBSERVACOES = 7;

    public TMViewServico(List<Servico> lst) {
        this.lista = lst;
    }

    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        return 8;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Servico aux = new Servico();
        if (lista.isEmpty()) {
            return aux;
        } else {
            aux = lista.get(rowIndex);
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
            DecimalFormat decimalFormat = new DecimalFormat("R$ #,##0.00");
            
            switch (columnIndex) {
                case COL_DATA_HORA:
                    return aux.getDataHora().format(dateFormatter);
                    
                case COL_CLIENTE:
                    return aux.getCliente().getNome();
                    
                case COL_TECNICO:
                    return aux.getTecnico().getNome();
                    
                case COL_Suporte:
                    return aux.getSuporte().getNome();
                    
                case COL_itens:
                    return aux.getItens().stream()
                            .map(Item::getDescricao)
                            .collect(Collectors.joining(", "));
                    
                case COL_VALOR_TOTAL:
                    return decimalFormat.format(aux.getValorTotal());
                    
                case COL_STATUS:
                    return aux.getStatus();
                    
                case COL_OBSERVACOES:
                    return aux.getObservacoes();
                    
                default:
                    break;
            }
            return aux;
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public String getColumnName(int columnIndex) {
        switch (columnIndex) {
            case COL_DATA_HORA:
                return "Data/Hora";
                
            case COL_CLIENTE:
                return "Cliente";
                
            case COL_TECNICO:
                return "Tecnico";
                
            case COL_Suporte:
                return "Suporte";
                
            case COL_itens:
                return "Itens";
                
            case COL_VALOR_TOTAL:
                return "Valor Total";
                
            case COL_STATUS:
                return "Status";
                
            case COL_OBSERVACOES:
                return "Observações";
                
            default:
                break;
        }
        return "";
    }
    
    public Servico getServico(int row) {
        if (row >= 0 && row < lista.size()) {
            return lista.get(row);
        }
        return null;
    }
}