package Model.Entities;

public class MaintenceInfo {
    private static int counter = 1;  // Usado para gerar IDs únicos
    private int id;
    private String title;
    private int clientId;
    private String description;
    private int time;  // Tempo em minutos
    private double price;

    public MaintenceInfo(String title, int clientId, String description, int time, double price) {
        this.id = counter++;
        this.title = title;
        this.clientId = clientId;
        this.description = description;
        this.time = time;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
