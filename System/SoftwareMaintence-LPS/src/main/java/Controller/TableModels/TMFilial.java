package Controller.TableModels;

import Model.Entities.Filial;
import javax.swing.table.AbstractTableModel;
import java.util.List;

public class TMFilial extends AbstractTableModel {
    private List<Filial> filiais;
    private String[] columnNames = {"ID", "Nome", "Local", "Senha Administrador"};

    public TMFilial(List<Filial> filiais) {
        this.filiais = filiais;
    }

    @Override
    public int getRowCount() {
        return filiais.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Filial filial = filiais.get(rowIndex);
        switch (columnIndex) {
            case 0: return filial.getId();
            case 1: return filial.getName();
            case 2: return filial.getPlace();
            case 3: return filial.getAdminPassword();
            default: return null;
        }
    }

    @Override
    public String getColumnName(int columnIndex) {
        return columnNames[columnIndex];
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }
}
