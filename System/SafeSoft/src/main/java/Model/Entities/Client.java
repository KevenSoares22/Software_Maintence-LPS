package Model.Entities;

public class Client {
    private static int counter = 1;
    private int id;
    private String name;
    private String identity;
    private String password;
    private String email;
    private String sex;

    public Client(String name, String identity, String password, String email, String sex) {
        this.id = counter++;
        this.name = name;
        this.identity = identity;
        this.password = password;
        this.email = email;
        this.sex = sex;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
