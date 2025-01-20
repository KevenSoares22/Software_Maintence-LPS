package Controller;
import Controller.TableModels.TMFeedback;
import Model.Entities.Feedback;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public class FeedbackController {
    private List<Feedback> feedbacks;

    public FeedbackController() {
        this.feedbacks = new ArrayList<>();
    }

    // Método para adicionar um novo feedback
    public void addFeedback(int clientId, String description, Date date) {
        Feedback newFeedback = new Feedback(clientId, description, date);
        feedbacks.add(newFeedback);
        JOptionPane.showMessageDialog(null, "Feedback adicionado com sucesso!");
    }

    // Método para atualizar um feedback
    public void updateFeedback(int clientId, String description, Date date) {
        for (Feedback feedback : feedbacks) {
            if (feedback.getClientId() == clientId) {
                feedback.setDescription(description);
                feedback.setDate(date);
                JOptionPane.showMessageDialog(null, "Feedback atualizado com sucesso!");
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "Feedback não encontrado.");
    }

    // Método para excluir um feedback
    public void deleteFeedback(int clientId) {
        feedbacks.removeIf(feedback -> feedback.getClientId() == clientId);
        JOptionPane.showMessageDialog(null, "Feedback excluído com sucesso!");
    }

    // Método para atualizar a tabela de feedbacks
    public void refreshTable(JTable table) {
        TMFeedback model = new TMFeedback(feedbacks);
        table.setModel(model);
    }
}
