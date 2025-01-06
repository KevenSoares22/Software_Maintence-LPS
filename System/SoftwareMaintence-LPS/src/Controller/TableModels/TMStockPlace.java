package Controller.TableModels;

import Model.Entities.StockPlace;
import javax.swing.table.AbstractTableModel;
import java.util.List;

public class TMStockPlace extends AbstractTableModel {
    private List<StockPlace> stockPlaces;
    private String[] columnNames = {"ID", "Local", "Filial ID", "Espaço Total"};

    public TMStockPlace(List<StockPlace> stockPlaces) {
        this.stockPlaces = stockPlaces;
    }

    @Override
    public int getRowCount() {
        return stockPlaces.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        StockPlace stockPlace = stockPlaces.get(rowIndex);
        switch (columnIndex) {
            case 0: return stockPlace.getId();
            case 1: return stockPlace.getPlace();
            case 2: return stockPlace.getFilialId();
            case 3: return stockPlace.getTotalSpace();
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
