package Model.Entities;

public class Technician {
    private static int counter = 1;  // Usado para gerar IDs únicos
    private int id;
    private String name;
    private String email;
    private String password;
    private String identity;
    private String sex;  // "M" para masculino, "F" para feminino, ou "O" para outro
    private int areaId;
    private double commissionValue;

    public Technician(String name, String email, String password, String identity, String sex, int areaId, double commissionValue) {
        this.id = counter++;
        this.name = name;
        this.email = email;
        this.password = password;
        this.identity = identity;
        this.sex = sex;
        this.areaId = areaId;
        this.commissionValue = commissionValue;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getAreaId() {
        return areaId;
    }

    public void setAreaId(int areaId) {
        this.areaId = areaId;
    }

    public double getCommissionValue() {
        return commissionValue;
    }

    public void setCommissionValue(double commissionValue) {
        this.commissionValue = commissionValue;
    }
}
