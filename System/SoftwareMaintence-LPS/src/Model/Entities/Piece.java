package Model.Entities;

public class Piece {
    private static int counter = 1;  // Usado para gerar IDs únicos
    private int id;
    private String name;
    private int quantity;
    private double price;
    private double cost;
    private double height;
    private double length;
    private double width;

    public Piece(String name, int quantity, double price, double cost, double height, double length, double width) {
        this.id = counter++;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.cost = cost;
        this.height = height;
        this.length = length;
        this.width = width;
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }
}
