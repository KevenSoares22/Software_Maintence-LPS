package Model.Entities;

public class StockPlace {
    private static int counter = 1;  // Usado para gerar IDs únicos
    private int id;
    private String place;
    private int filialId;
    private double totalSpace;

    public StockPlace(String place, int filialId, double totalSpace) {
        this.id = counter++;
        this.place = place;
        this.filialId = filialId;
        this.totalSpace = totalSpace;
    }

    public int getId() {
        return id;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public int getFilialId() {
        return filialId;
    }

    public void setFilialId(int filialId) {
        this.filialId = filialId;
    }

    public double getTotalSpace() {
        return totalSpace;
    }

    public void setTotalSpace(double totalSpace) {
        this.totalSpace = totalSpace;
    }
}
