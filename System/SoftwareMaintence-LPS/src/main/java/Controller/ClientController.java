package Controller;

import Controller.TableModels.TMClient;
import Model.Entities.Client;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class ClientController {

    private List<Client> clients;

    public ClientController() {
        this.clients = new ArrayList<>();
    }

    public void refreshTable(JTable t) {
        TMClient model = new TMClient(clients);  // Passa a lista de clients para o TMClient
        t.setModel(model);
    }

    public void createClient(String name, String identity, String password, String email, String sex) {
        if (name == null || name.isEmpty()) {
            JOptionPane.showMessageDialog(null, "O nome não pode ser vazio.");
            return;
        }
        if (identity == null || identity.isEmpty()) {
            JOptionPane.showMessageDialog(null, "A identidade não pode ser vazia.");
            return;
        }
        if (email == null || email.isEmpty() || !email.contains("@")) {
            JOptionPane.showMessageDialog(null, "Email inválido.");
            return;
        }
        if (password == null || password.isEmpty()) {
            JOptionPane.showMessageDialog(null, "A senha não pode ser vazia.");
            return;
        }
        if (sex == null || sex.isEmpty()) {
            JOptionPane.showMessageDialog(null, "O sexo não pode ser vazio.");
            return;
        }

        Client newClient = new Client(name, identity, password, email, sex);
        clients.add(newClient);  // Adiciona o cliente na lista
        JOptionPane.showMessageDialog(null, "Cliente criado com sucesso!");
    }

    public void updateClient(int editingId, String name, String identity, String password, String email, String sex) {
        Client clientToUpdate = null;
        for (Client client : clients) {
            if (client.getId() == editingId) {
                clientToUpdate = client;
                break;
            }
        }

        if (clientToUpdate == null) {
            JOptionPane.showMessageDialog(null, "Cliente não encontrado.");
            return;
        }

        if (name != null && !name.isEmpty()) {
            clientToUpdate.setName(name);
        }
        if (identity != null && !identity.isEmpty()) {
            clientToUpdate.setIdentity(identity);
        }
        if (email != null && !email.isEmpty() && email.contains("@")) {
            clientToUpdate.setEmail(email);
        }
        if (password != null && !password.isEmpty()) {
            clientToUpdate.setPassword(password);
        }
        if (sex != null && !sex.isEmpty()) {
            clientToUpdate.setSex(sex);
        }

        JOptionPane.showMessageDialog(null, "Cliente atualizado com sucesso!");
    }

    public void deleteClient(int id) {
        boolean removed = false;
        for (Client client : clients) {
            if (client.getId() == id) {
                clients.remove(client);
                removed = true;
                break;
            }
        }

        if (removed) {
            JOptionPane.showMessageDialog(null, "Cliente deletado com sucesso!");
        } else {
            JOptionPane.showMessageDialog(null, "Cliente não encontrado.");
        }
    }
}
