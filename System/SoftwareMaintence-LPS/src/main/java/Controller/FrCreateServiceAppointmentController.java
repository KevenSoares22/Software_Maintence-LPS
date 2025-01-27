package Controller;

import View.FrCreateServiceAppointment;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrCreateServiceAppointmentController {
    private FrCreateServiceAppointment view;

    public FrCreateServiceAppointmentController(FrCreateServiceAppointment view) {
        this.view = view;
        initController();
    }

    private void initController() {
        view.getFecharButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleFecharButton();
            }
        });

        view.getCriarButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleCriarButton();
            }
        });
    }

    private void handleFecharButton() {
        System.exit(0);
    }

    private void handleCriarButton() {
        // Pegando os valores dos campos com base nos getters definidos
        String client = view.getClientField().getText();
        String description = view.getDescriptionField().getText();
        String time = view.getTimeField().getText();
        String price = view.getPriceField().getText();
        String areaId = view.getAreaIdField().getText();

        // Verificando se algum campo está vazio
        if (client.isEmpty() || description.isEmpty() || time.isEmpty() || price.isEmpty() || areaId.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Todos os campos devem ser preenchidos!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Exibindo os dados no console
        System.out.println("Cliente: " + client);
        System.out.println("Descrição: " + description);
        System.out.println("Horário: " + time);
        System.out.println("Preço: " + price);
        System.out.println("ID da área: " + areaId);

        // Exibindo uma mensagem de sucesso
        JOptionPane.showMessageDialog(view, "Agendamento de serviço criado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
    }
}
