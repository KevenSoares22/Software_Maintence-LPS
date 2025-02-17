package com.ifcolab.safesoft.controller.tablemodel;

import com.ifcolab.safesoft.model.Suporte;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class TMViewSuporte extends AbstractTableModel {

    private List<Suporte> lista;
    private final int COL_NOME = 0;
    private final int COL_cod = 1;
    private final int COL_EMAIL = 2;
    private final int COL_TELEFONE = 3;

    public TMViewSuporte(List<Suporte> lst) {
        this.lista = lst;
    }

    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Suporte aux = new Suporte();
        if (lista.isEmpty()) {
            return aux;
        } else {
            aux = lista.get(rowIndex);
            switch (columnIndex) {
                case COL_NOME:
                    return aux.getNome();
                case COL_cod:
                    return aux.getCod();
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
            case COL_cod:
                return "cod";
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
