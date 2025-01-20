package Controller;

import Controller.TableModels.TMMaintenceInfo;
import Model.Entities.MaintenceInfo;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class MaintenanceInfoController {

    private List<MaintenceInfo> maintenceInfos;

    public MaintenanceInfoController() {
        this.maintenceInfos = new ArrayList<>();
    }

    public void refreshTable(JTable t) {
        TMMaintenceInfo model = new TMMaintenceInfo(maintenceInfos);  // Passa a lista de MaintenanceInfo para o TMMaintenanceInfo
        t.setModel(model);
    }

    public void createMaintenceInfo(String title, int clientId, String description, int time, double price) {
        if (title == null || title.isEmpty()) {
            JOptionPane.showMessageDialog(null, "O título não pode ser vazio.");
            return;
        }
        if (description == null || description.isEmpty()) {
            JOptionPane.showMessageDialog(null, "A descrição não pode ser vazia.");
            return;
        }
        if (time <= 0) {
            JOptionPane.showMessageDialog(null, "O tempo de manutenção deve ser maior que 0.");
            return;
        }
        if (price <= 0) {
            JOptionPane.showMessageDialog(null, "O preço deve ser maior que 0.");
            return;
        }

        MaintenceInfo newMaintenceInfo = new MaintenceInfo(title, clientId, description, time, price);
        maintenceInfos.add(newMaintenceInfo);  // Adiciona as informações de manutenção à lista
        JOptionPane.showMessageDialog(null, "Informações de manutenção criadas com sucesso!");
    }

    public void updateMaintenceInfo(int editingId, String title, int clientId, String description, int time, double price) {
        MaintenceInfo maintenceToUpdate = null;
        for (MaintenceInfo maintenceInfo : maintenceInfos) {
            if (maintenceInfo.getId() == editingId) {
                maintenceToUpdate = maintenceInfo;
                break;
            }
        }

        if (maintenceToUpdate == null) {
            JOptionPane.showMessageDialog(null, "Informações de manutenção não encontradas.");
            return;
        }

        if (title != null && !title.isEmpty()) {
            maintenceToUpdate.setTitle(title);
        }
        if (description != null && !description.isEmpty()) {
            maintenceToUpdate.setDescription(description);
        }
        if (time > 0) {
            maintenceToUpdate.setTime(time);
        }
        if (price > 0) {
            maintenceToUpdate.setPrice(price);
        }

        JOptionPane.showMessageDialog(null, "Informações de manutenção atualizadas com sucesso!");
    }

    public void deleteMaintenceInfo(int id) {
        boolean removed = false;
        for (MaintenceInfo maintenceInfo : maintenceInfos) {
            if (maintenceInfo.getId() == id) {
                maintenceInfos.remove(maintenceInfo);
                removed = true;
                break;
            }
        }

        if (removed) {
            JOptionPane.showMessageDialog(null, "Informações de manutenção deletadas com sucesso!");
        } else {
            JOptionPane.showMessageDialog(null, "Informações de manutenção não encontradas.");
        }
    }
}
