package Controller.TableModels;

import Model.Entities.Client;
import javax.swing.table.AbstractTableModel;
import java.util.List;

public class TMClient extends AbstractTableModel {
    private List<Client> clients;
    private String[] columnNames = {"ID", "Nome", "Identidade", "Email", "Sexo"};

    public TMClient(List<Client> clients) {
        this.clients = clients;
    }

    @Override
    public int getRowCount() {
        return clients.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Client client = clients.get(rowIndex);
        switch (columnIndex) {
            case 0: return client.getId();
            case 1: return client.getName();
            case 2: return client.getIdentity();
            case 3: return client.getEmail();
            case 4: return client.getSex();
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
