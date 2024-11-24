package Model.Entities;

public class Manager {
    private static int counter = 1;  // Usado para gerar IDs únicos
    private int id;
    private String name;
    private String email;
    private String identity;
    private String password;
    private double salary;
    private Filial filial;  // A filial à qual o gerente está vinculado

    public Manager(String name, String email, String identity, String password, double salary, Filial filial) {
        this.id = counter++;
        this.name = name;
        this.email = email;
        this.identity = identity;
        this.password = password;
        this.salary = salary;
        this.filial = filial;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Filial getFilial() {
        return filial;
    }

    public void setFilial(Filial filial) {
        this.filial = filial;
    }
}
