package Controller.TableModels;

import Model.Entities.Manager;
import javax.swing.table.AbstractTableModel;
import java.util.List;

public class TMManager extends AbstractTableModel {
    private List<Manager> managers;
    private String[] columnNames = {"ID", "Nome", "E-mail", "Identidade", "Senha", "Salário", "Filial"};

    public TMManager(List<Manager> managers) {
        this.managers = managers;
    }

    @Override
    public int getRowCount() {
        return managers.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Manager manager = managers.get(rowIndex);
        switch (columnIndex) {
            case 0: return manager.getId();
            case 1: return manager.getName();
            case 2: return manager.getEmail();
            case 3: return manager.getIdentity();
            case 4: return manager.getPassword();
            case 5: return manager.getSalary();
            case 6: return manager.getFilial().getName();  // Exibindo o nome da filial associada
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
