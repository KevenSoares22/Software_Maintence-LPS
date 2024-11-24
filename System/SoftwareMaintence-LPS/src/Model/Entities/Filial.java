package Model.Entities;

public class Filial {
    private static int counter = 1;  // Usado para gerar IDs únicos
    private int id;
    private String name;
    private String place;
    private String adminPassword;

    public Filial(String name, String place, String adminPassword) {
        this.id = counter++;
        this.name = name;
        this.place = place;
        this.adminPassword = adminPassword;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getAdminPassword() {
        return adminPassword;
    }

    public void setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword;
    }
}
