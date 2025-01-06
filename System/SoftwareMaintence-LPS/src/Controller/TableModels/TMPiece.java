package Controller.TableModels;

import Model.Entities.Piece;
import javax.swing.table.AbstractTableModel;
import java.util.List;

public class TMPiece extends AbstractTableModel {
    private List<Piece> pieces;
    private String[] columnNames = {"ID", "Nome", "Quantidade", "Preço", "Custo", "Altura", "Comprimento", "Largura"};

    public TMPiece(List<Piece> pieces) {
        this.pieces = pieces;
    }

    @Override
    public int getRowCount() {
        return pieces.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Piece piece = pieces.get(rowIndex);
        switch (columnIndex) {
            case 0: return piece.getId();
            case 1: return piece.getName();
            case 2: return piece.getQuantity();
            case 3: return piece.getPrice();
            case 4: return piece.getCost();
            case 5: return piece.getHeight();
            case 6: return piece.getLength();
            case 7: return piece.getWidth();
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
