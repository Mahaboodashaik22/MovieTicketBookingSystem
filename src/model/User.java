package model;

public class User extends Person {

    private String password;

    public User(int userId, String name,
                String email, String password) {

        super(userId, name, email);
        this.password = password;
    }

    public int getUserId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getRole() {
        return "Customer";
    }

    @Override
    public String toString() {
        return id + " - " + name + " - " + email;
    }
}