package Controller;

import Controller.TableModels.TMServiceAppointment;
import Model.Entities.ServiceAppointment;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class ServiceAppointmentController {

    private List<ServiceAppointment> serviceAppointments;

    public ServiceAppointmentController() {
        this.serviceAppointments = new ArrayList<>();
    }

    public void refreshTable(JTable t) {
        TMServiceAppointment model = new TMServiceAppointment(serviceAppointments);  // Passa a lista de agendamentos para o TMServiceAppointment
        t.setModel(model);
    }

    public void createServiceAppointment(int areaId, double price, String time, int clientId, String description) {
        if (areaId <= 0) {
            JOptionPane.showMessageDialog(null, "A área ID deve ser maior que 0.");
            return;
        }
        if (price <= 0) {
            JOptionPane.showMessageDialog(null, "O preço deve ser maior que 0.");
            return;
        }
        if (time == null || time.isEmpty()) {
            JOptionPane.showMessageDialog(null, "O tempo deve ser informado.");
            return;
        }
        if (description == null || description.isEmpty()) {
            JOptionPane.showMessageDialog(null, "A descrição do serviço deve ser informada.");
            return;
        }

        ServiceAppointment newAppointment = new ServiceAppointment(areaId, price, time, clientId, description);
        serviceAppointments.add(newAppointment);  // Adiciona o agendamento à lista
        JOptionPane.showMessageDialog(null, "Agendamento de serviço criado com sucesso!");
    }

    public void updateServiceAppointment(int editingId, int areaId, double price, String time, int clientId, String description) {
        ServiceAppointment appointmentToUpdate = null;
        for (ServiceAppointment appointment : serviceAppointments) {
            if (appointment.getId() == editingId) {
                appointmentToUpdate = appointment;
                break;
            }
        }

        if (appointmentToUpdate == null) {
            JOptionPane.showMessageDialog(null, "Agendamento de serviço não encontrado.");
            return;
        }

        if (areaId > 0) {
            appointmentToUpdate.setAreaId(areaId);
        }
        if (price > 0) {
            appointmentToUpdate.setPrice(price);
        }
        if (time != null && !time.isEmpty()) {
            appointmentToUpdate.setTime(time);
        }
        if (clientId > 0) {
            appointmentToUpdate.setClientId(clientId);
        }
        if (description != null && !description.isEmpty()) {
            appointmentToUpdate.setDescription(description);
        }

        JOptionPane.showMessageDialog(null, "Agendamento de serviço atualizado com sucesso!");
    }

    public void deleteServiceAppointment(int id) {
        boolean removed = false;
        for (ServiceAppointment appointment : serviceAppointments) {
            if (appointment.getId() == id) {
                serviceAppointments.remove(appointment);
                removed = true;
                break;
            }
        }

        if (removed) {
            JOptionPane.showMessageDialog(null, "Agendamento de serviço deletado com sucesso!");
        } else {
            JOptionPane.showMessageDialog(null, "Agendamento de serviço não encontrado.");
        }
    }
}
