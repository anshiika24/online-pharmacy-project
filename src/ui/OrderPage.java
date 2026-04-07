//package ui;
//
//import model.User;
//import util.DatabaseConnection;
//
//import javax.swing.*;
//import javax.swing.table.DefaultTableModel;
//import java.awt.*;
//import java.sql.*;
//
//public class OrderPage extends JFrame {
//
//    private User loggedUser;
//
//    JTable medicineTable;
//    DefaultTableModel medModel;
//
//    JTextField quantityField;
//    JButton orderBtn;
//
//    public OrderPage(User user) {
//        this.loggedUser = user;
//
//        setTitle("Place Order");
//        setSize(700, 500);
//        setLocationRelativeTo(null);
//        setLayout(new BorderLayout());
//
//        // ================= TABLE =================
//        medModel = new DefaultTableModel(new String[]{"ID", "Name", "Price", "Stock"}, 0);
//        medicineTable = new JTable(medModel);
//
//        loadMedicines();
//
//        add(new JScrollPane(medicineTable), BorderLayout.CENTER);
//
//        // ================= INPUT PANEL =================
//        JPanel inputPanel = new JPanel(new GridLayout(2, 2, 10, 10));
//        inputPanel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));
//
//        inputPanel.add(new JLabel("Enter Quantity:"));
//        quantityField = new JTextField();
//        inputPanel.add(quantityField);
//
//        orderBtn = new JButton("Place Order");
//        inputPanel.add(orderBtn);
//
//        add(inputPanel, BorderLayout.SOUTH);
//
//        // Action
//        orderBtn.addActionListener(e -> placeOrder());
//
//        setVisible(true);
//    }
//
//    // ---------------------------------------------------
//    // LOAD MEDICINES
//    // ---------------------------------------------------
//    private void loadMedicines() {
//        try (Connection conn = DatabaseConnection.getConnection()) {
//            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM medicines");
//            ResultSet rs = stmt.executeQuery();
//            medModel.setRowCount(0);
//
//            while (rs.next()) {
//                medModel.addRow(new Object[]{
//                        rs.getInt("id"),
//                        rs.getString("name"),
//                        rs.getDouble("price"),
//                        rs.getInt("stock")
//                });
//            }
//
//        } catch (Exception ex) {
//            JOptionPane.showMessageDialog(this, "Error loading medicines: " + ex.getMessage());
//        }
//    }
//
//    // ---------------------------------------------------
//    // PLACE ORDER
//    // ---------------------------------------------------
//    private void placeOrder() {
//        int row = medicineTable.getSelectedRow();
//
//        if (row == -1) {
//            JOptionPane.showMessageDialog(this, "Select a medicine first!");
//            return;
//        }
//
//        int medId = (int) medModel.getValueAt(row, 0);
//        double price = (double) medModel.getValueAt(row, 2);
//        int availableStock = (int) medModel.getValueAt(row, 3);
//
//        String qtyStr = quantityField.getText();
//
//        if (qtyStr.isEmpty()) {
//            JOptionPane.showMessageDialog(this, "Enter quantity!");
//            return;
//        }
//
//        int qty = Integer.parseInt(qtyStr);
//
//        if (qty <= 0) {
//            JOptionPane.showMessageDialog(this, "Quantity must be positive!");
//            return;
//        }
//
//        if (qty > availableStock) {
//            JOptionPane.showMessageDialog(this, "Not enough stock!");
//            return;
//        }
//
//        double total = qty * price;
//
//        try (Connection conn = DatabaseConnection.getConnection()) {
//            conn.setAutoCommit(false);  // Transaction start
//
//            // 1️⃣ Insert order
//            PreparedStatement orderStmt = conn.prepareStatement(
//                    "INSERT INTO orders (user_id, medicine_id, quantity, total_price) VALUES (?, ?, ?, ?)"
//            );
//
//            orderStmt.setInt(1, loggedUser.getId());
//            orderStmt.setInt(2, medId);
//            orderStmt.setInt(3, qty);
//            orderStmt.setDouble(4, total);
//            orderStmt.executeUpdate();
//
//            // 2️⃣ Reduce stock
//            PreparedStatement stockStmt = conn.prepareStatement(
//                    "UPDATE medicines SET stock = stock - ? WHERE id = ?"
//            );
//            stockStmt.setInt(1, qty);
//            stockStmt.setInt(2, medId);
//            stockStmt.executeUpdate();
//
//            conn.commit(); // Transaction end
//
//            JOptionPane.showMessageDialog(this, "Order Placed Successfully!\nTotal = ₹" + total);
//
//            loadMedicines();
//
//        } catch (Exception ex) {
//            JOptionPane.showMessageDialog(this, "Order Error: " + ex.getMessage());
//        }
//    }
//}
//



package ui;

import model.User;
import util.DatabaseConnection;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class OrderPage extends JFrame {

    private User loggedUser;

    JTable medicineTable;
    DefaultTableModel medModel;

    JTextField quantityField;
    JButton orderBtn;

    public OrderPage(User user) {

        this.loggedUser = user;

        setTitle("Place Order");
        setSize(850, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        // -------------------------------------------------------
        // MAIN BACKGROUND PANEL WITH GRADIENT
        // -------------------------------------------------------
        JPanel bgPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                Graphics2D g2d = (Graphics2D) g;
                GradientPaint gp = new GradientPaint(
                        0, 0, new Color(25, 118, 210),
                        0, getHeight(), new Color(144, 202, 249)
                );
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };

        bgPanel.setLayout(new BorderLayout());
        bgPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        // -------------------------------------------------------
        // ROUNDED WHITE CARD PANEL
        // -------------------------------------------------------
        JPanel card = new JPanel();
        card.setBackground(Color.WHITE);
        card.setLayout(new BorderLayout(15, 15));
        card.setBorder(new EmptyBorder(20, 20, 20, 20));

        // -------------------------------------------------------
        // TABLE TITLE
        // -------------------------------------------------------
        JLabel title = new JLabel("Available Medicines");
        title.setFont(new Font("Segoe UI", Font.BOLD, 20));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        card.add(title, BorderLayout.NORTH);

        // -------------------------------------------------------
        // TABLE
        // -------------------------------------------------------
        medModel = new DefaultTableModel(new String[]{"ID", "Name", "Price", "Stock"}, 0);
        medicineTable = new JTable(medModel);
        medicineTable.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        medicineTable.setRowHeight(25);
        medicineTable.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 15));

        loadMedicines();

        JScrollPane scroll = new JScrollPane(medicineTable);
        scroll.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1));
        card.add(scroll, BorderLayout.CENTER);

        // -------------------------------------------------------
        // INPUT AREA
        // -------------------------------------------------------
        JPanel inputPanel = new JPanel();
        inputPanel.setBackground(Color.WHITE);
        inputPanel.setLayout(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(10, 10, 10, 10);

        JLabel qtyLabel = new JLabel("Enter Quantity:");
        qtyLabel.setFont(new Font("Segoe UI", Font.PLAIN, 15));

        quantityField = new JTextField(10);
        quantityField.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        quantityField.setBorder(BorderFactory.createLineBorder(new Color(180, 180, 180)));

        orderBtn = new JButton("Place Order");
        orderBtn.setFont(new Font("Segoe UI", Font.BOLD, 16));
        orderBtn.setBackground(new Color(25, 118, 210));
        orderBtn.setForeground(Color.WHITE);
        orderBtn.setFocusPainted(false);
        orderBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        c.gridx = 0;
        c.gridy = 0;
        inputPanel.add(qtyLabel, c);

        c.gridx = 1;
        inputPanel.add(quantityField, c);

        c.gridx = 1;
        c.gridy = 1;
        inputPanel.add(orderBtn, c);

        card.add(inputPanel, BorderLayout.SOUTH);

        // Add card to background
        bgPanel.add(card, BorderLayout.CENTER);

        add(bgPanel);

        // -------------------------------------------------------
        // BUTTON ACTION
        // -------------------------------------------------------
        orderBtn.addActionListener(e -> placeOrder());

        setVisible(true);
    }

    // ---------------------------------------------------
    // LOAD MEDICINES
    // ---------------------------------------------------
    private void loadMedicines() {
        try (Connection conn = DatabaseConnection.getConnection()) {

            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM medicines");
            ResultSet rs = stmt.executeQuery();

            medModel.setRowCount(0);

            while (rs.next()) {
                medModel.addRow(new Object[]{
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getDouble("price"),
                        rs.getInt("stock")
                });
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error loading medicines:\n" + ex.getMessage());
        }
    }

    // ---------------------------------------------------
    // PLACE ORDER
    // ---------------------------------------------------
    private void placeOrder() {

        int row = medicineTable.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "❗ Please select a medicine first!");
            return;
        }

        int medId = (int) medModel.getValueAt(row, 0);
        double price = (double) medModel.getValueAt(row, 2);
        int availableStock = (int) medModel.getValueAt(row, 3);

        String qtyStr = quantityField.getText().trim();
        if (qtyStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "❗ Please enter a quantity.");
            return;
        }

        int qty = Integer.parseInt(qtyStr);

        if (qty <= 0) {
            JOptionPane.showMessageDialog(this, "❗ Quantity must be greater than 0.");
            return;
        }

        if (qty > availableStock) {
            JOptionPane.showMessageDialog(this, "❗ Not enough stock available!");
            return;
        }

        double total = qty * price;

        try (Connection conn = DatabaseConnection.getConnection()) {

            conn.setAutoCommit(false);

            // Insert order
            PreparedStatement orderStmt = conn.prepareStatement(
                    "INSERT INTO orders (user_id, medicine_id, quantity, total_price) VALUES (?, ?, ?, ?)"
            );

            orderStmt.setInt(1, loggedUser.getId());
            orderStmt.setInt(2, medId);
            orderStmt.setInt(3, qty);
            orderStmt.setDouble(4, total);
            orderStmt.executeUpdate();

            // Update stock
            PreparedStatement stockStmt = conn.prepareStatement(
                    "UPDATE medicines SET stock = stock - ? WHERE id = ?"
            );

            stockStmt.setInt(1, qty);
            stockStmt.setInt(2, medId);
            stockStmt.executeUpdate();

            conn.commit();

            JOptionPane.showMessageDialog(this,
                    "🛒 Order placed successfully!\nTotal Price: ₹" + total);

            loadMedicines();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Order Error:\n" + ex.getMessage());
        }
    }
}
