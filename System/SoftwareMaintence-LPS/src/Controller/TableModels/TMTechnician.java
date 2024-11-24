package Controller.TableModels;

import Model.Entities.Technician;
import javax.swing.table.AbstractTableModel;
import java.util.List;

public class TMTechnician extends AbstractTableModel {
    private List<Technician> technicians;
    private String[] columnNames = {"ID", "Nome", "Email", "Identidade", "Sexo", "Área ID", "Comissão"};

    public TMTechnician(List<Technician> technicians) {
        this.technicians = technicians;
    }

    @Override
    public int getRowCount() {
        return technicians.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Technician technician = technicians.get(rowIndex);
        switch (columnIndex) {
            case 0: return technician.getId();
            case 1: return technician.getName();
            case 2: return technician.getEmail();
            case 3: return technician.getIdentity();
            case 4: return technician.getSex();
            case 5: return technician.getAreaId();
            case 6: return technician.getCommissionValue();
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
