package controller;

import database.UserDAO;
import model.User;

public class LoginController {

    private final UserDAO userDAO;

    public LoginController() {
        userDAO = new UserDAO();
    }

    public User login(String email, String password) {

        if (email == null || email.isBlank()) {
            return null;
        }

        if (password == null || password.isBlank()) {
            return null;
        }

        return userDAO.login(email, password);
    }
}