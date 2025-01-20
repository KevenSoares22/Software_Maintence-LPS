package Controller;

import Controller.TableModels.TMManager;
import Model.Entities.Manager;
import Model.Entities.Filial;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class ManagerController {

    private List<Manager> managers;

    public ManagerController() {
        this.managers = new ArrayList<>();
    }

    public void refreshTable(JTable t) {
        TMManager model = new TMManager(managers);  // Passa a lista de managers para o TMManager
        t.setModel(model);
    }

    public void createManager(String name, String email, String identity, String password, double salary, Filial filial) {
        if (name == null || name.isEmpty()) {
            JOptionPane.showMessageDialog(null, "O nome do gerente não pode ser vazio.");
            return;
        }
        if (email == null || email.isEmpty()) {
            JOptionPane.showMessageDialog(null, "O e-mail não pode ser vazio.");
            return;
        }
        if (identity == null || identity.isEmpty()) {
            JOptionPane.showMessageDialog(null, "A identidade não pode ser vazia.");
            return;
        }
        if (password == null || password.isEmpty()) {
            JOptionPane.showMessageDialog(null, "A senha não pode ser vazia.");
            return;
        }
        if (salary <= 0) {
            JOptionPane.showMessageDialog(null, "O salário deve ser maior que 0.");
            return;
        }
        if (filial == null) {
            JOptionPane.showMessageDialog(null, "A filial não pode ser nula.");
            return;
        }

        Manager newManager = new Manager(name, email, identity, password, salary, filial);
        managers.add(newManager);  // Adiciona o gerente à lista
        JOptionPane.showMessageDialog(null, "Gerente criado com sucesso!");
    }

    public void updateManager(int editingId, String name, String email, String identity, String password, double salary, Filial filial) {
        Manager managerToUpdate = null;
        for (Manager manager : managers) {
            if (manager.getId() == editingId) {
                managerToUpdate = manager;
                break;
            }
        }

        if (managerToUpdate == null) {
            JOptionPane.showMessageDialog(null, "Gerente não encontrado.");
            return;
        }

        if (name != null && !name.isEmpty()) {
            managerToUpdate.setName(name);
        }
        if (email != null && !email.isEmpty()) {
            managerToUpdate.setEmail(email);
        }
        if (identity != null && !identity.isEmpty()) {
            managerToUpdate.setIdentity(identity);
        }
        if (password != null && !password.isEmpty()) {
            managerToUpdate.setPassword(password);
        }
        if (salary > 0) {
            managerToUpdate.setSalary(salary);
        }
        if (filial != null) {
            managerToUpdate.setFilial(filial);
        }

        JOptionPane.showMessageDialog(null, "Gerente atualizado com sucesso!");
    }

    public void deleteManager(int id) {
        boolean removed = false;
        for (Manager manager : managers) {
            if (manager.getId() == id) {
                managers.remove(manager);
                removed = true;
                break;
            }
        }

        if (removed) {
            JOptionPane.showMessageDialog(null, "Gerente deletado com sucesso!");
        } else {
            JOptionPane.showMessageDialog(null, "Gerente não encontrado.");
        }
    }
}
