//package ui;
//
//import model.User;
//import service.AuthService;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//
//
//public class LoginPage extends JFrame {
//
//    private AuthService authService = new AuthService();
//
//    public LoginPage() {
//        setTitle("Online Pharmacy - Login");
//        setSize(400, 300);
//        setDefaultCloseOperation(EXIT_ON_CLOSE);
//        setLocationRelativeTo(null);
//        setLayout(new BorderLayout());
//
//        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
//        panel.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));
//
//        JLabel emailLabel = new JLabel("Email:");
//        JTextField emailField = new JTextField();
//
//        JLabel passwordLabel = new JLabel("Password:");
//        JPasswordField passwordField = new JPasswordField();
//
//        JButton loginButton = new JButton("Login");
//
//        panel.add(emailLabel);
//        panel.add(emailField);
//        panel.add(passwordLabel);
//        panel.add(passwordField);
//
//        add(panel, BorderLayout.CENTER);
//        add(loginButton, BorderLayout.SOUTH);
//
//        // On Login Button Click
//        loginButton.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//
//                String email = emailField.getText();
//                String password = new String(passwordField.getPassword());
//
//                User user = authService.login(email, password);
//
//                if (user != null) {
//                    JOptionPane.showMessageDialog(null, "Login Successful!");
//
//                    // Close login window
//                    dispose();
//
//                    // Open Dashboard
//                    new Dashboard(user);
//                } else {
//                    JOptionPane.showMessageDialog(null, "Invalid Credentials!");
//                }
//            }
//        });
//
//        setVisible(true);
//    }
//}



//package ui;
//
//import model.User;
//import service.AuthService;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.*;
//
//public class LoginPage extends JFrame {
//
//    private AuthService authService = new AuthService();
//
//    public LoginPage() {
//        setTitle("Online Pharmacy - Login");
//        setSize(500, 400);
//        setDefaultCloseOperation(EXIT_ON_CLOSE);
//        setLocationRelativeTo(null);
//
//        // ===== Main Background (Soft Color) =====
//        JPanel background = new JPanel();
//        background.setBackground(new Color(245, 247, 250));
//        background.setLayout(new GridBagLayout());
//
//        // ===== Glass Card UI =====
//        JPanel card = new JPanel();
//        card.setPreferredSize(new Dimension(350, 240));
//        card.setBackground(new Color(255, 255, 255));
//        card.setBorder(BorderFactory.createLineBorder(new Color(220, 220, 220), 2));
//        card.setLayout(new GridLayout(4, 1, 10, 10));
//
//        // ===== Fields =====
//        JTextField emailField = new JTextField();
//        emailField.setBorder(BorderFactory.createTitledBorder("Email"));
//        emailField.setFont(new Font("SansSerif", Font.PLAIN, 16));
//
//        JPasswordField passwordField = new JPasswordField();
//        passwordField.setBorder(BorderFactory.createTitledBorder("Password"));
//        passwordField.setFont(new Font("SansSerif", Font.PLAIN, 16));
//
//        JButton loginButton = new JButton("Login");
//        loginButton.setFocusPainted(false);
//        loginButton.setForeground(Color.WHITE);
//        loginButton.setFont(new Font("SansSerif", Font.BOLD, 16));
//        loginButton.setBackground(new Color(0, 120, 255));
//        loginButton.setOpaque(true);
//        loginButton.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
//
//        // Hover Effect
//        loginButton.addMouseListener(new MouseAdapter() {
//            public void mouseEntered(MouseEvent e) {
//                loginButton.setBackground(new Color(0, 100, 230));
//            }
//            public void mouseExited(MouseEvent e) {
//                loginButton.setBackground(new Color(0, 120, 255));
//            }
//        });
//
//        // ===== Add components to card =====
//        card.add(emailField);
//        card.add(passwordField);
//        card.add(loginButton);
//
//        // Add spacing at bottom
//        card.add(new JLabel(""));
//
//        // ===== Add card to background =====
//        background.add(card);
//
//        add(background);
//
//        // ===== Login Logic =====
//        loginButton.addActionListener(e -> {
//            String email = emailField.getText();
//            String password = new String(passwordField.getPassword());
//
//            User user = authService.login(email, password);
//
//            if (user != null) {
//                JOptionPane.showMessageDialog(null, "Login Successful!");
//                dispose();
//                new Dashboard(user);
//            } else {
//                JOptionPane.showMessageDialog(null, "Invalid Credentials!");
//            }
//        });
//
//        setVisible(true);
//    }
//}
package ui;

import model.User;
import service.AuthService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginPage extends JFrame {

    private AuthService authService = new AuthService();

    public LoginPage() {
        setTitle("Online Pharmacy - Login");
        setSize(500, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // ===== Main Background =====
        JPanel background = new JPanel();
        background.setBackground(new Color(245, 247, 250));
        background.setLayout(new GridBagLayout());

        // ===== Glass Card =====
        JPanel card = new JPanel();
        card.setPreferredSize(new Dimension(350, 260));
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createLineBorder(new Color(220, 220, 220), 2));
        card.setLayout(new GridLayout(4, 1, 10, 10));

        // ===== Email Field =====
        JTextField emailField = new JTextField();
        emailField.setBorder(BorderFactory.createTitledBorder("Email"));
        emailField.setFont(new Font("SansSerif", Font.PLAIN, 16));

        // ===== Password Panel (Field + Eye Icon) =====
        JPanel passwordPanel = new JPanel(new BorderLayout());
        passwordPanel.setBorder(BorderFactory.createTitledBorder("Password"));
        passwordPanel.setBackground(Color.WHITE);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setFont(new Font("SansSerif", Font.PLAIN, 16));
        passwordField.setEchoChar('•');

        JButton toggleButton = new JButton("\uD83D\uDC41"); // Eye icon
        toggleButton.setBorder(null);
        toggleButton.setFocusable(false);
        toggleButton.setContentAreaFilled(false);
        toggleButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        toggleButton.setFont(new Font("SansSerif", Font.PLAIN, 18));

        toggleButton.addActionListener(e -> {
            if (passwordField.getEchoChar() == 0) {
                passwordField.setEchoChar('•');
                toggleButton.setText("\uD83D\uDC41");  // Eye icon
            } else {
                passwordField.setEchoChar((char) 0);
                toggleButton.setText("\uD83D\uDC41\u200D\uD83D\uDD12"); // Eye-locked
            }
        });

        passwordPanel.add(passwordField, BorderLayout.CENTER);
        passwordPanel.add(toggleButton, BorderLayout.EAST);

        // ===== Login Button =====
        JButton loginButton = new JButton("Login");
        loginButton.setFocusPainted(false);
        loginButton.setForeground(Color.WHITE);
        loginButton.setFont(new Font("SansSerif", Font.BOLD, 16));
        loginButton.setBackground(new Color(0, 120, 255));
        loginButton.setOpaque(true);
        loginButton.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        loginButton.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                loginButton.setBackground(new Color(0, 100, 230));
            }
            public void mouseExited(MouseEvent e) {
                loginButton.setBackground(new Color(0, 120, 255));
            }
        });

        // ===== Add components =====
        card.add(emailField);
        card.add(passwordPanel);
        card.add(loginButton);
        card.add(new JLabel("")); // spacing

        background.add(card);
        add(background);

        // ===== Login Logic =====
        loginButton.addActionListener(e -> {
            String email = emailField.getText();
            String password = new String(passwordField.getPassword());

            User user = authService.login(email, password);

            if (user != null) {
                JOptionPane.showMessageDialog(null, "Login Successful!");
                dispose();
                new Dashboard(user);
            } else {
                JOptionPane.showMessageDialog(null, "Invalid Credentials!");
            }
        });

        setVisible(true);
    }
}



