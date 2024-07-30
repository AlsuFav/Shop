package ru.itis.shop.users.models;


public class User {
    private final Integer id;
    private final String name;
    private final String email;
    private final String password;

    private static Integer available_id = 1;

    public User(int id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public User(String name, String email, String password) {
        this.id = available_id;
        this.name = name;
        this.email = email;
        this.password = password;

        available_id += 1;
    }

    public Integer getId() {
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

    @Override
    public String toString() {
        return "User { " +
                "\nid=" + id +
                "\nname=" + name +
                "\nemail=" + email +
                "\npassword=" + password +
                "\n}";
    }
}
