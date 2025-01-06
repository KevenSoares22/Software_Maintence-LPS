package Controller;

import Controller.TableModels.TMTechnician;
import Model.Entities.Technician;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class TechnicianController {

    private List<Technician> technicians;

    public TechnicianController() {
        this.technicians = new ArrayList<>();
    }

    public void refreshTable(JTable t) {
        TMTechnician model = new TMTechnician(technicians);  // Passa a lista de técnicos para o TMTechnician
        t.setModel(model);
    }

    public void createTechnician(String name, String email, String password, String identity, String sex, int areaId, double commissionValue) {
        if (name == null || name.isEmpty()) {
            JOptionPane.showMessageDialog(null, "O nome não pode ser vazio.");
            return;
        }
        if (email == null || email.isEmpty()) {
            JOptionPane.showMessageDialog(null, "O e-mail não pode ser vazio.");
            return;
        }
        if (password == null || password.isEmpty()) {
            JOptionPane.showMessageDialog(null, "A senha não pode ser vazia.");
            return;
        }
        if (identity == null || identity.isEmpty()) {
            JOptionPane.showMessageDialog(null, "A identidade não pode ser vazia.");
            return;
        }
        if (sex == null || sex.isEmpty()) {
            JOptionPane.showMessageDialog(null, "O sexo não pode ser vazio.");
            return;
        }
        if (areaId <= 0) {
            JOptionPane.showMessageDialog(null, "A área ID deve ser maior que 0.");
            return;
        }
        if (commissionValue < 0) {
            JOptionPane.showMessageDialog(null, "O valor da comissão não pode ser negativo.");
            return;
        }

        Technician newTechnician = new Technician(name, email, password, identity, sex, areaId, commissionValue);
        technicians.add(newTechnician);  // Adiciona o técnico à lista
        JOptionPane.showMessageDialog(null, "Técnico criado com sucesso!");
    }

    public void updateTechnician(int editingId, String name, String email, String password, String identity, String sex, int areaId, double commissionValue) {
        Technician technicianToUpdate = null;
        for (Technician technician : technicians) {
            if (technician.getId() == editingId) {
                technicianToUpdate = technician;
                break;
            }
        }

        if (technicianToUpdate == null) {
            JOptionPane.showMessageDialog(null, "Técnico não encontrado.");
            return;
        }

        if (name != null && !name.isEmpty()) {
            technicianToUpdate.setName(name);
        }
        if (email != null && !email.isEmpty()) {
            technicianToUpdate.setEmail(email);
        }
        if (password != null && !password.isEmpty()) {
            technicianToUpdate.setPassword(password);
        }
        if (identity != null && !identity.isEmpty()) {
            technicianToUpdate.setIdentity(identity);
        }
        if (sex != null && !sex.isEmpty()) {
            technicianToUpdate.setSex(sex);
        }
        if (areaId > 0) {
            technicianToUpdate.setAreaId(areaId);
        }
        if (commissionValue >= 0) {
            technicianToUpdate.setCommissionValue(commissionValue);
        }

        JOptionPane.showMessageDialog(null, "Técnico atualizado com sucesso!");
    }

    public void deleteTechnician(int id) {
        boolean removed = false;
        for (Technician technician : technicians) {
            if (technician.getId() == id) {
                technicians.remove(technician);
                removed = true;
                break;
            }
        }

        if (removed) {
            JOptionPane.showMessageDialog(null, "Técnico deletado com sucesso!");
        } else {
            JOptionPane.showMessageDialog(null, "Técnico não encontrado.");
        }
    }
}
