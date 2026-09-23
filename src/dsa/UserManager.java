package dsa;

import model.User;

import java.util.ArrayList;

public class UserManager {

    private ArrayList<User> users = new ArrayList<>();

    public void addUser(User user) {
        users.add(user);
    }

    public void displayUsers() {

        if (users.isEmpty()) {
            System.out.println("No users found.");
            return;
        }

        System.out.println("Registered Users:");

        for (User user : users) {
            System.out.println(user);
        }
    }

    public ArrayList<User> getUsers() {
        return users;
    }
}