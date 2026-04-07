//package ui;
//
//import model.User;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.*;
//
//public class Dashboard extends JFrame {
//
//    private User loggedUser;
//
//    public Dashboard(User user) {
//        this.loggedUser = user;
//
//        setTitle("Online Pharmacy - Dashboard");
//        setSize(500, 400);
//        setDefaultCloseOperation(EXIT_ON_CLOSE);
//        setLocationRelativeTo(null);
//        setLayout(new BorderLayout());
//
//        // Title
//        JLabel title = new JLabel("Welcome, " + user.getName(), SwingConstants.CENTER);
//        title.setFont(new Font("Arial", Font.BOLD, 20));
//        add(title, BorderLayout.NORTH);
//
//        // Buttons Panel
//        JPanel panel = new JPanel(new GridLayout(4, 1, 15, 15));
//        panel.setBorder(BorderFactory.createEmptyBorder(30, 70, 30, 70));
//
//        JButton manageMedicinesBtn = new JButton("Manage Medicines");
//        JButton placeOrderBtn = new JButton("Place Order");
//        JButton viewOrdersBtn = new JButton("View All Orders");
//        JButton logoutBtn = new JButton("Logout");
//        JButton aboutBtn = new JButton("About");
//
//
//
//
//
//        panel.add(manageMedicinesBtn);
//        panel.add(placeOrderBtn);
//        panel.add(viewOrdersBtn);
//        panel.add(logoutBtn);
//        panel.add(aboutBtn);
//        add(panel, BorderLayout.CENTER);
//
//        // Button Actions
//
//        manageMedicinesBtn.addActionListener(e -> {
//            new MedicineManagementPage();
//        });
//
//        placeOrderBtn.addActionListener(e -> {
//            new OrderPage(user);
//        });
//
//         viewOrdersBtn.addActionListener(e -> {
//             new ViewOrdersPage();
//         });
//
//        logoutBtn.addActionListener(e -> {
//            dispose();
//            new LoginPage();
//        });
//        aboutBtn.addActionListener(e -> new AboutPage());
//
//        setVisible(true);
//    }
//}
//
//
//
//

//package ui;
//
//import model.User;
//
//import javax.swing.*;
//import java.awt.*;
//
//public class Dashboard extends JFrame {
//
//    private User loggedUser;
//
//    public Dashboard(User user) {
//        this.loggedUser = user;
//
//        setTitle("Online Pharmacy - Dashboard");
//        setSize(600, 450);
//        setDefaultCloseOperation(EXIT_ON_CLOSE);
//        setLocationRelativeTo(null);
//
//        // ---- MAIN CONTAINER ----
//        JPanel mainPanel = new JPanel();
//        mainPanel.setBackground(new Color(245, 247, 250));
//        mainPanel.setLayout(new BorderLayout(20, 20));
//        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
//
//        // ---- TITLE ----
//        JLabel title = new JLabel("Welcome, " + user.getName(), SwingConstants.CENTER);
//        title.setFont(new Font("Segoe UI", Font.BOLD, 30));
//        title.setForeground(new Color(40, 40, 40));
//        mainPanel.add(title, BorderLayout.NORTH);
//
//        // ---- CENTER BUTTON PANEL ----
//        JPanel buttonPanel = new JPanel(new GridLayout(5, 1, 15, 15));
//        buttonPanel.setOpaque(false);
//
//        // Buttons
//        JButton manageMedicinesBtn = createButton("Manage Medicines");
//        JButton placeOrderBtn = createButton("Place Order");
//        JButton viewOrdersBtn = createButton("View All Orders");
//        JButton aboutBtn = createButton("About");
//        JButton logoutBtn = createButton("Logout");
//
//        // Add buttons
//        buttonPanel.add(manageMedicinesBtn);
//        buttonPanel.add(placeOrderBtn);
//        buttonPanel.add(viewOrdersBtn);
//        buttonPanel.add(aboutBtn);
//        buttonPanel.add(logoutBtn);
//
//        mainPanel.add(buttonPanel, BorderLayout.CENTER);
//
//        // ---- ADD MAIN PANEL ----
//        add(mainPanel);
//
//        // ---- BUTTON EVENTS ----
//        manageMedicinesBtn.addActionListener(e -> new MedicineManagementPage());
//        placeOrderBtn.addActionListener(e -> new OrderPage(user));
//        viewOrdersBtn.addActionListener(e -> new ViewOrdersPage());
//        aboutBtn.addActionListener(e -> new AboutPage());
//
//        logoutBtn.addActionListener(e -> {
//            dispose();
//            new LoginPage();
//        });
//
//        setVisible(true);
//    }
//
//    // ---- BEAUTIFUL BUTTON STYLE ----
//    private JButton createButton(String text) {
//        JButton btn = new JButton(text);
//        btn.setFocusPainted(false);
//        btn.setFont(new Font("Segoe UI", Font.BOLD, 16));
//        btn.setBackground(new Color(70, 130, 180));
//        btn.setForeground(Color.WHITE);
//        btn.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));
//        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
//        return btn;
//    }
//}



package ui;

import model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Dashboard extends JFrame {

    private User loggedUser;

    public Dashboard(User user) {
        this.loggedUser = user;

        setTitle("Online Pharmacy - Dashboard");
        setSize(760, 520);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(240, 242, 245));

        // ===== TOP GRADIENT HEADER =====
        JPanel header = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                GradientPaint gp = new GradientPaint(
                        0, 0, new Color(0, 153, 255),
                        getWidth(), getHeight(), new Color(0, 102, 204)
                );
                g2.setPaint(gp);
                g2.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        header.setPreferredSize(new Dimension(800, 90));
        header.setLayout(new GridBagLayout());

        JLabel welcome = new JLabel("Welcome, " + user.getName());
        welcome.setForeground(Color.WHITE);
        welcome.setFont(new Font("Segoe UI", Font.BOLD, 32));
        header.add(welcome);

        add(header, BorderLayout.NORTH);

        // ===== GLASS CARD PANEL =====
        JPanel card = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;

                // Glass effect
                Color glass = new Color(255, 255, 255, 140);
                g2.setColor(glass);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);

                // Soft shadow effect
                g2.setColor(new Color(200, 200, 200, 80));
                g2.drawRoundRect(1, 1, getWidth() - 2, getHeight() - 2, 30, 30);
            }
        };

        card.setOpaque(false);
        card.setPreferredSize(new Dimension(450, 380));
        card.setLayout(new GridLayout(5, 1, 25, 25));
        card.setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));

        // ==== Buttons ====
        JButton manageMedicinesBtn = createGradientButton("Manage Medicines");
        JButton placeOrderBtn = createGradientButton("Place Order");
        JButton viewOrdersBtn = createGradientButton("View All Orders");
        JButton aboutBtn = createGradientButton("About");
        JButton logoutBtn = createRedButton("Logout");

        card.add(manageMedicinesBtn);
        card.add(placeOrderBtn);
        card.add(viewOrdersBtn);
        card.add(aboutBtn);
        card.add(logoutBtn);

        JPanel centerWrapper = new JPanel(new GridBagLayout());
        centerWrapper.setOpaque(false);
        centerWrapper.add(card);

        add(centerWrapper, BorderLayout.CENTER);

        // ===== Button Events =====
        manageMedicinesBtn.addActionListener(e -> new MedicineManagementPage());
        placeOrderBtn.addActionListener(e -> new OrderPage(user));
        viewOrdersBtn.addActionListener(e -> new ViewOrdersPage());
        aboutBtn.addActionListener(e -> new AboutPage());

        logoutBtn.addActionListener(e -> {
            dispose();
            new LoginPage();
        });

        setVisible(true);
    }

    // -------------------------------------------------
    // BEAUTIFUL MODERN GRADIENT BUTTON
    // -------------------------------------------------
    private JButton createGradientButton(String text) {
        JButton btn = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g;
                GradientPaint gp = new GradientPaint(
                        0, 0, new Color(0, 123, 255),
                        getWidth(), getHeight(), new Color(0, 90, 200)
                );
                g2.setPaint(gp);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
                super.paintComponent(g);
            }
        };

        btn.setOpaque(false);
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 20));
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setContentAreaFilled(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        addHoverGlow(btn);

        return btn;
    }

    // RED LOGOUT BUTTON
    private JButton createRedButton(String text) {
        JButton btn = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g;
                GradientPaint gp = new GradientPaint(
                        0, 0, new Color(255, 87, 87),
                        getWidth(), getHeight(), new Color(220, 0, 0)
                );
                g2.setPaint(gp);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
                super.paintComponent(g);
            }
        };

        btn.setOpaque(false);
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 20));
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setContentAreaFilled(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        addHoverGlow(btn);

        return btn;
    }

    // ===== Hover Glow Effect =====
    private void addHoverGlow(JButton btn) {
        btn.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseEntered(MouseEvent e) {
                btn.setBorder(BorderFactory.createLineBorder(new Color(255, 255, 255, 180), 2));
                btn.setFont(new Font("Segoe UI", Font.BOLD, 22));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btn.setBorder(null);
                btn.setFont(new Font("Segoe UI", Font.BOLD, 20));
            }
        });
    }
}
