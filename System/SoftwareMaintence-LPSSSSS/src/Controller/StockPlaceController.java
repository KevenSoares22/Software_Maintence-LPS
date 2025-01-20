package Controller;

import Controller.TableModels.TMStockPlace;
import Model.Entities.StockPlace;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class StockPlaceController {

    private List<StockPlace> stockPlaces;

    public StockPlaceController() {
        this.stockPlaces = new ArrayList<>();
    }

    public void refreshTable(JTable t) {
        TMStockPlace model = new TMStockPlace(stockPlaces);  // Passa a lista de locais de estoque para o TMStockPlace
        t.setModel(model);
    }

    public void createStockPlace(String place, int filialId, double totalSpace) {
        if (place == null || place.isEmpty()) {
            JOptionPane.showMessageDialog(null, "O nome do local não pode ser vazio.");
            return;
        }
        if (filialId <= 0) {
            JOptionPane.showMessageDialog(null, "A filial ID deve ser maior que 0.");
            return;
        }
        if (totalSpace <= 0) {
            JOptionPane.showMessageDialog(null, "O espaço total deve ser maior que 0.");
            return;
        }

        StockPlace newStockPlace = new StockPlace(place, filialId, totalSpace);
        stockPlaces.add(newStockPlace);  // Adiciona o local de estoque à lista
        JOptionPane.showMessageDialog(null, "Local de estoque criado com sucesso!");
    }

    public void updateStockPlace(int editingId, String place, int filialId, double totalSpace) {
        StockPlace stockPlaceToUpdate = null;
        for (StockPlace stockPlace : stockPlaces) {
            if (stockPlace.getId() == editingId) {
                stockPlaceToUpdate = stockPlace;
                break;
            }
        }

        if (stockPlaceToUpdate == null) {
            JOptionPane.showMessageDialog(null, "Local de estoque não encontrado.");
            return;
        }

        if (place != null && !place.isEmpty()) {
            stockPlaceToUpdate.setPlace(place);
        }
        if (filialId > 0) {
            stockPlaceToUpdate.setFilialId(filialId);
        }
        if (totalSpace > 0) {
            stockPlaceToUpdate.setTotalSpace(totalSpace);
        }

        JOptionPane.showMessageDialog(null, "Local de estoque atualizado com sucesso!");
    }

    public void deleteStockPlace(int id) {
        boolean removed = false;
        for (StockPlace stockPlace : stockPlaces) {
            if (stockPlace.getId() == id) {
                stockPlaces.remove(stockPlace);
                removed = true;
                break;
            }
        }

        if (removed) {
            JOptionPane.showMessageDialog(null, "Local de estoque deletado com sucesso!");
        } else {
            JOptionPane.showMessageDialog(null, "Local de estoque não encontrado.");
        }
    }
}
