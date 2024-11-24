package Controller;

import Controller.TableModels.TMPiece;
import Model.Entities.Piece;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class PieceController {

    private List<Piece> pieces;

    public PieceController() {
        this.pieces = new ArrayList<>();
    }

    public void refreshTable(JTable t) {
        TMPiece model = new TMPiece(pieces);  // Passa a lista de peças para o TMPiece
        t.setModel(model);
    }

    public void createPiece(String name, int quantity, double price, double cost, double height, double length, double width) {
        if (name == null || name.isEmpty()) {
            JOptionPane.showMessageDialog(null, "O nome da peça não pode ser vazio.");
            return;
        }
        if (quantity <= 0) {
            JOptionPane.showMessageDialog(null, "A quantidade deve ser maior que 0.");
            return;
        }
        if (price <= 0) {
            JOptionPane.showMessageDialog(null, "O preço deve ser maior que 0.");
            return;
        }
        if (cost <= 0) {
            JOptionPane.showMessageDialog(null, "O custo deve ser maior que 0.");
            return;
        }
        if (height <= 0 || length <= 0 || width <= 0) {
            JOptionPane.showMessageDialog(null, "As dimensões (altura, comprimento e largura) devem ser maiores que 0.");
            return;
        }

        Piece newPiece = new Piece(name, quantity, price, cost, height, length, width);
        pieces.add(newPiece);  // Adiciona a peça à lista
        JOptionPane.showMessageDialog(null, "Peça criada com sucesso!");
    }

    public void updatePiece(int editingId, String name, int quantity, double price, double cost, double height, double length, double width) {
        Piece pieceToUpdate = null;
        for (Piece piece : pieces) {
            if (piece.getId() == editingId) {
                pieceToUpdate = piece;
                break;
            }
        }

        if (pieceToUpdate == null) {
            JOptionPane.showMessageDialog(null, "Peça não encontrada.");
            return;
        }

        if (name != null && !name.isEmpty()) {
            pieceToUpdate.setName(name);
        }
        if (quantity > 0) {
            pieceToUpdate.setQuantity(quantity);
        }
        if (price > 0) {
            pieceToUpdate.setPrice(price);
        }
        if (cost > 0) {
            pieceToUpdate.setCost(cost);
        }
        if (height > 0) {
            pieceToUpdate.setHeight(height);
        }
        if (length > 0) {
            pieceToUpdate.setLength(length);
        }
        if (width > 0) {
            pieceToUpdate.setWidth(width);
        }

        JOptionPane.showMessageDialog(null, "Peça atualizada com sucesso!");
    }

    public void deletePiece(int id) {
        boolean removed = false;
        for (Piece piece : pieces) {
            if (piece.getId() == id) {
                pieces.remove(piece);
                removed = true;
                break;
            }
        }

        if (removed) {
            JOptionPane.showMessageDialog(null, "Peça deletada com sucesso!");
        } else {
            JOptionPane.showMessageDialog(null, "Peça não encontrada.");
        }
    }
}
