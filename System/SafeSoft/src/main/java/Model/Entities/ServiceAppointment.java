package Model.Entities;

public class ServiceAppointment {
    private static int counter = 1;  // Usado para gerar IDs únicos
    private int id;
    private int areaId;
    private double price;
    private String time;
    private int clientId;
    private String description;

    public ServiceAppointment(int areaId, double price, String time, int clientId, String description) {
        this.id = counter++;
        this.areaId = areaId;
        this.price = price;
        this.time = time;
        this.clientId = clientId;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public int getAreaId() {
        return areaId;
    }

    public void setAreaId(int areaId) {
        this.areaId = areaId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
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
}
