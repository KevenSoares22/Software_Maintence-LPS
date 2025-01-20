package Controller.TableModels;

import Model.Entities.ServiceAppointment;
import javax.swing.table.AbstractTableModel;
import java.util.List;

public class TMServiceAppointment extends AbstractTableModel {
    private List<ServiceAppointment> serviceAppointments;
    private String[] columnNames = {"ID", "Área ID", "Preço", "Tempo", "Cliente ID", "Descrição"};

    public TMServiceAppointment(List<ServiceAppointment> serviceAppointments) {
        this.serviceAppointments = serviceAppointments;
    }

    @Override
    public int getRowCount() {
        return serviceAppointments.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        ServiceAppointment appointment = serviceAppointments.get(rowIndex);
        switch (columnIndex) {
            case 0: return appointment.getId();
            case 1: return appointment.getAreaId();
            case 2: return appointment.getPrice();
            case 3: return appointment.getTime();
            case 4: return appointment.getClientId();
            case 5: return appointment.getDescription();
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
