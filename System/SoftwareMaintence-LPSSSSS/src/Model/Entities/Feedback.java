package Model.Entities;

import java.util.Date;

public class Feedback {
    private int clientId;
    private String description;
    private Date date;

    public Feedback(int clientId, String description, Date date) {
        this.clientId = clientId;
        this.description = description;
        this.date = date;
    }

    public int getClientId() {
        return clientId;
    }

    public String getDescription() {
        return description;
    }

    public Date getDate() {
        return date;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
