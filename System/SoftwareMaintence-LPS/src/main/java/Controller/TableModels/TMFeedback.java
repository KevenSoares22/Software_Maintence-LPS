package Controller.TableModels;

import Model.Entities.Feedback;
import javax.swing.table.AbstractTableModel;
import java.util.List;

public class TMFeedback extends AbstractTableModel {
    private List<Feedback> feedbacks;
    private String[] columnNames = {"Client ID", "Description", "Date"};

    public TMFeedback(List<Feedback> feedbacks) {
        this.feedbacks = feedbacks;
    }

    @Override
    public int getRowCount() {
        return feedbacks.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Feedback feedback = feedbacks.get(rowIndex);
        switch (columnIndex) {
            case 0: return feedback.getClientId();
            case 1: return feedback.getDescription();
            case 2: return feedback.getDate();
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
