package service;

import dao.UserDAO;
import model.User;

public class AuthService {

    private UserDAO userDAO = new UserDAO();

    public User login(String email, String password) {

        // Normally we hash the password, but here we use plain text
        String passwordHash = password; // You can add hashing later

        User user = userDAO.login(email, passwordHash);

        if (user != null) {
            System.out.println("✔ Login Successful!");
        } else {
            System.out.println("❌ Invalid Email or Password!");
        }

        return user;
    }
}
