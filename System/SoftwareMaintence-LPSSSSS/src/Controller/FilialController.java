package Controller;

import Controller.TableModels.TMFilial;
import Model.Entities.Filial;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class FilialController {

    private List<Filial> filiais;

    public FilialController() {
        this.filiais = new ArrayList<>();
    }

    public void refreshTable(JTable t) {
        TMFilial model = new TMFilial(filiais);  // Passa a lista de filiais para o TMFilial
        t.setModel(model);
    }

    public void createFilial(String name, String place, String adminPassword) {
        if (name == null || name.isEmpty()) {
            JOptionPane.showMessageDialog(null, "O nome da filial não pode ser vazio.");
            return;
        }
        if (place == null || place.isEmpty()) {
            JOptionPane.showMessageDialog(null, "O local da filial não pode ser vazio.");
            return;
        }
        if (adminPassword == null || adminPassword.isEmpty()) {
            JOptionPane.showMessageDialog(null, "A senha do administrador não pode ser vazia.");
            return;
        }

        Filial newFilial = new Filial(name, place, adminPassword);
        filiais.add(newFilial);  // Adiciona a filial na lista
        JOptionPane.showMessageDialog(null, "Filial criada com sucesso!");
    }

    public void updateFilial(int editingId, String name, String place, String adminPassword) {
        Filial filialToUpdate = null;
        for (Filial filial : filiais) {
            if (filial.getId() == editingId) {
                filialToUpdate = filial;
                break;
            }
        }

        if (filialToUpdate == null) {
            JOptionPane.showMessageDialog(null, "Filial não encontrada.");
            return;
        }

        if (name != null && !name.isEmpty()) {
            filialToUpdate.setName(name);
        }
        if (place != null && !place.isEmpty()) {
            filialToUpdate.setPlace(place);
        }
        if (adminPassword != null && !adminPassword.isEmpty()) {
            filialToUpdate.setAdminPassword(adminPassword);
        }

        JOptionPane.showMessageDialog(null, "Filial atualizada com sucesso!");
    }

    public void deleteFilial(int id) {
        boolean removed = false;
        for (Filial filial : filiais) {
            if (filial.getId() == id) {
                filiais.remove(filial);
                removed = true;
                break;
            }
        }

        if (removed) {
            JOptionPane.showMessageDialog(null, "Filial deletada com sucesso!");
        } else {
            JOptionPane.showMessageDialog(null, "Filial não encontrada.");
        }
    }
}
