package com.ifcolab.safesoft.controller.tablemodel;

import com.ifcolab.safesoft.model.Tecnico;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class TMViewTecnico extends AbstractTableModel {

    private List<Tecnico> lista;
    private final int COL_NOME = 0;
    private final int COL_COD = 1;
    private final int COL_AREAGESTAO = 2;
    private final int COL_EMAIL = 3;
    private final int COL_TELEFONE = 4;

    public TMViewTecnico(List<Tecnico> lst) {
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
        Tecnico aux = new Tecnico();
        if (lista.isEmpty()) {
            return aux;
        } else {
            aux = lista.get(rowIndex);
            switch (columnIndex) {
                case COL_NOME:
                    return aux.getNome();
                case COL_COD:
                    return aux.getCod();
                case COL_AREAGESTAO:
                    return aux.getAreagestao();
                case COL_EMAIL:
                    return aux.getEmail();
                case COL_TELEFONE:
                    return aux.getTelefone();
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
            case COL_NOME:
                return "Nome";
            case COL_COD:
                return "Cod";
            case COL_AREAGESTAO:
                return "AREAEGESTAO";
            case COL_EMAIL:
                return "Email";
            case COL_TELEFONE:
                return "Telefone";
            default:
                break;
        }
        return "";
    }
}
