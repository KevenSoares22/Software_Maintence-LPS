package Controller.TableModels;

import Model.Entities.MaintenceInfo;
import javax.swing.table.AbstractTableModel;
import java.util.List;

public class TMMaintenceInfo extends AbstractTableModel {
    private List<MaintenceInfo> maintenceInfos;
    private String[] columnNames = {"ID", "Título", "ID Cliente", "Descrição", "Tempo (minutos)", "Preço"};

    public TMMaintenceInfo(List<MaintenceInfo> maintenceInfos) {
        this.maintenceInfos = maintenceInfos;
    }

    @Override
    public int getRowCount() {
        return maintenceInfos.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        MaintenceInfo maintenceInfo = maintenceInfos.get(rowIndex);
        switch (columnIndex) {
            case 0: return maintenceInfo.getId();
            case 1: return maintenceInfo.getTitle();
            case 2: return maintenceInfo.getClientId();
            case 3: return maintenceInfo.getDescription();
            case 4: return maintenceInfo.getTime();
            case 5: return maintenceInfo.getPrice();
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
