//package ui;
//
//import util.DatabaseConnection;
//
//import javax.swing.*;
//import javax.swing.table.DefaultTableModel;
//import java.awt.*;
//import java.sql.*;
//
//public class MedicineManagementPage extends JFrame {
//
//    JTable table;
//    DefaultTableModel model;
//
//    JTextField nameField, priceField, stockField;
//    JButton addBtn, updateBtn, deleteBtn;
//
//    public MedicineManagementPage() {
//
//        setTitle("Manage Medicines");
//        setSize(700, 500);
//        setLocationRelativeTo(null);
//        setLayout(new BorderLayout());
//
//        // ========== TABLE ==========
//        model = new DefaultTableModel(new String[]{"ID", "Name", "Price", "Stock"}, 0);
//        table = new JTable(model);
//
//        loadMedicines();
//
//        add(new JScrollPane(table), BorderLayout.CENTER);
//
//        // ========== INPUT PANEL ==========
//        JPanel inputPanel = new JPanel(new GridLayout(4, 2, 10, 10));
//        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
//
//        inputPanel.add(new JLabel("Medicine Name:"));
//        nameField = new JTextField();
//        inputPanel.add(nameField);
//
//        inputPanel.add(new JLabel("Price:"));
//        priceField = new JTextField();
//        inputPanel.add(priceField);
//
//        inputPanel.add(new JLabel("Stock:"));
//        stockField = new JTextField();
//        inputPanel.add(stockField);
//
//        // Buttons
//        addBtn = new JButton("Add");
//        updateBtn = new JButton("Update");
//        deleteBtn = new JButton("Delete");
//
//        inputPanel.add(addBtn);
//        inputPanel.add(updateBtn);
//        inputPanel.add(deleteBtn);
//
//        add(inputPanel, BorderLayout.SOUTH);
//
//        // ========== BUTTON ACTIONS ==========
//
//        addBtn.addActionListener(e -> addMedicine());
//        updateBtn.addActionListener(e -> updateMedicine());
//        deleteBtn.addActionListener(e -> deleteMedicine());
//
//        setVisible(true);
//    }
//
//    // ---------------------------------------------------
//    // LOAD DATA INTO TABLE
//    // ---------------------------------------------------
//    private void loadMedicines() {
//        try (Connection conn = DatabaseConnection.getConnection()) {
//            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM medicines");
//            ResultSet rs = stmt.executeQuery();
//            model.setRowCount(0);
//
//            while (rs.next()) {
//                model.addRow(new Object[]{
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
//    // ADD MEDICINE
//    // ---------------------------------------------------
//    private void addMedicine() {
//        String name = nameField.getText();
//        String price = priceField.getText();
//        String stock = stockField.getText();
//
//        if (name.isEmpty() || price.isEmpty() || stock.isEmpty()) {
//            JOptionPane.showMessageDialog(this, "All fields required!");
//            return;
//        }
//
//        try (Connection conn = DatabaseConnection.getConnection()) {
//
//            PreparedStatement stmt = conn.prepareStatement(
//                    "INSERT INTO medicines (name, price, stock) VALUES (?, ?, ?)"
//            );
//
//            stmt.setString(1, name);
//            stmt.setDouble(2, Double.parseDouble(price));
//            stmt.setInt(3, Integer.parseInt(stock));
//
//            stmt.executeUpdate();
//
//            JOptionPane.showMessageDialog(this, "Medicine Added!");
//
//            loadMedicines();
//
//        } catch (Exception ex) {
//            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
//        }
//    }
//
//    // ---------------------------------------------------
//    // UPDATE MEDICINE
//    // ---------------------------------------------------
//    private void updateMedicine() {
//
//        int row = table.getSelectedRow();
//        if (row == -1) {
//            JOptionPane.showMessageDialog(this, "Select a row first!");
//            return;
//        }
//
//        int id = (int) model.getValueAt(row, 0);
//
//        String name = nameField.getText();
//        String price = priceField.getText();
//        String stock = stockField.getText();
//
//        try (Connection conn = DatabaseConnection.getConnection()) {
//
//            PreparedStatement stmt = conn.prepareStatement(
//                    "UPDATE medicines SET name=?, price=?, stock=? WHERE id=?"
//            );
//
//            stmt.setString(1, name);
//            stmt.setDouble(2, Double.parseDouble(price));
//            stmt.setInt(3, Integer.parseInt(stock));
//            stmt.setInt(4, id);
//
//            stmt.executeUpdate();
//
//            JOptionPane.showMessageDialog(this, "Medicine Updated!");
//
//            loadMedicines();
//
//        } catch (Exception ex) {
//            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
//        }
//    }
//
//    // ---------------------------------------------------
//    // DELETE MEDICINE
//    // ---------------------------------------------------
//    private void deleteMedicine() {
//
//        int row = table.getSelectedRow();
//        if (row == -1) {
//            JOptionPane.showMessageDialog(this, "Select a row first!");
//            return;
//        }
//
//        int id = (int) model.getValueAt(row, 0);
//
//        try (Connection conn = DatabaseConnection.getConnection()) {
//
//            PreparedStatement stmt = conn.prepareStatement(
//                    "DELETE FROM medicines WHERE id = ?"
//            );
//
//            stmt.setInt(1, id);
//            stmt.executeUpdate();
//
//            JOptionPane.showMessageDialog(this, "Medicine Deleted!");
//
//            loadMedicines();
//
//        } catch (Exception ex) {
//            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
//        }
//    }
//}





//package ui;
//
//import dao.MedicineDAO;
//import model.Medicine;
//
//import javax.swing.*;
//import javax.swing.table.DefaultTableCellRenderer;
//import javax.swing.table.DefaultTableModel;
//import java.awt.*;
//import java.time.LocalDate;
//import java.time.format.DateTimeFormatter;
//import java.util.List;
//import java.util.Date;
//import java.time.ZoneId;
//import java.util.Calendar;
//import java.awt.event.*;
//
//public class MedicineManagementPage extends JFrame {
//
//    JTable table;
//    DefaultTableModel model;
//
//    JTextField nameField, priceField, stockField, descField;
//    JButton addBtn, updateBtn, deleteBtn;
//    JSpinner expirySpinner;
//
//    MedicineDAO medDAO = new MedicineDAO();
//    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//
//    public MedicineManagementPage() {
//        setTitle("Manage Medicines");
//        setSize(900, 600);
//        setLocationRelativeTo(null);
//        setLayout(new BorderLayout());
//
//        // Header
//        JPanel header = new JPanel(new BorderLayout());
//        header.setBackground(new Color(52, 152, 219));
//        header.setPreferredSize(new Dimension(900, 60));
//        JLabel title = new JLabel("📦 Manage Medicines", SwingConstants.LEFT);
//        title.setForeground(Color.WHITE);
//        title.setFont(new Font("Segoe UI", Font.BOLD, 22));
//        title.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 10));
//        header.add(title, BorderLayout.WEST);
//        add(header, BorderLayout.NORTH);
//
//        // Table
//        model = new DefaultTableModel(new String[]{"ID", "Name", "Desc", "Price", "Stock", "Expiry Date"}, 0) {
//            @Override
//            public boolean isCellEditable(int row, int column) {
//                return false; // table read-only
//            }
//        };
//        table = new JTable(model);
//        table.setRowHeight(30);
//        table.getTableHeader().setBackground(new Color(41, 128, 185));
//        table.getTableHeader().setForeground(Color.WHITE);
//        table.setFont(new Font("Segoe UI", Font.PLAIN, 14));
//        loadMedicines();
//
//        // Render expiry column and color rows
//        table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
//            @Override
//            public Component getTableCellRendererComponent(JTable t, Object value,
//                                                           boolean isSelected, boolean hasFocus,
//                                                           int row, int column) {
//                Component c = super.getTableCellRendererComponent(t, value, isSelected, hasFocus, row, column);
//                // default colors
//                c.setBackground(Color.WHITE);
//                c.setForeground(Color.BLACK);
//
//                // expiry col index = 5
//                Object expiryObj = t.getValueAt(row, 5);
//                if (expiryObj != null) {
//                    try {
//                        LocalDate expiry = LocalDate.parse(expiryObj.toString());
//                        LocalDate today = LocalDate.now();
//                        if (expiry.isBefore(today) || expiry.isEqual(today)) {
//                            // expired
//                            c.setBackground(new Color(255, 200, 200)); // red/pinkish
//                        } else if (!expiry.isAfter(today.plusDays(30))) {
//                            // expiring within 30 days
//                            c.setBackground(new Color(255, 245, 200)); // yellowish
//                        }
//                    } catch (Exception ex) {
//                        // ignore parse errors
//                    }
//                }
//
//                if (isSelected) {
//                    c.setBackground(new Color(200, 230, 255)); // selection color
//                }
//
//                return c;
//            }
//        });
//
//        JScrollPane scroll = new JScrollPane(table);
//        scroll.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
//        add(scroll, BorderLayout.CENTER);
//
//        // Input panel
//        JPanel inputPanel = new JPanel(new GridBagLayout());
//        inputPanel.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));
//        inputPanel.setBackground(new Color(245, 245, 245));
//        GridBagConstraints gbc = new GridBagConstraints();
//        gbc.insets = new Insets(8, 8, 8, 8);
//        gbc.fill = GridBagConstraints.HORIZONTAL;
//
//        gbc.gridx = 0; gbc.gridy = 0;
//        inputPanel.add(new JLabel("Name:"), gbc);
//        gbc.gridx = 1;
//        nameField = new JTextField();
//        inputPanel.add(nameField, gbc);
//
//        gbc.gridx = 0; gbc.gridy = 1;
//        inputPanel.add(new JLabel("Description:"), gbc);
//        gbc.gridx = 1;
//        descField = new JTextField();
//        inputPanel.add(descField, gbc);
//
//        gbc.gridx = 0; gbc.gridy = 2;
//        inputPanel.add(new JLabel("Price:"), gbc);
//        gbc.gridx = 1;
//        priceField = new JTextField();
//        inputPanel.add(priceField, gbc);
//
//        gbc.gridx = 0; gbc.gridy = 3;
//        inputPanel.add(new JLabel("Stock:"), gbc);
//        gbc.gridx = 1;
//        stockField = new JTextField();
//        inputPanel.add(stockField, gbc);
//
//        // Expiry date spinner
//        gbc.gridx = 0; gbc.gridy = 4;
//        inputPanel.add(new JLabel("Expiry Date (yyyy-MM-dd):"), gbc);
//        gbc.gridx = 1;
//        // spinner default today
//        SpinnerDateModel dateModel = new SpinnerDateModel(new Date(), null, null, Calendar.DAY_OF_MONTH);
//        expirySpinner = new JSpinner(dateModel);
//        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(expirySpinner, "yyyy-MM-dd");
//        expirySpinner.setEditor(dateEditor);
//        inputPanel.add(expirySpinner, gbc);
//
//        // Buttons row
//        gbc.gridx = 0; gbc.gridy = 5; gbc.gridwidth = 2;
//        JPanel btnRow = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
//        btnRow.setOpaque(false);
//
//        addBtn = createStyledButton("Add", new Color(46, 204, 113));
//        updateBtn = createStyledButton("Update", new Color(241, 196, 15));
//        deleteBtn = createStyledButton("Delete", new Color(231, 76, 60));
//
//        btnRow.add(addBtn);
//        btnRow.add(updateBtn);
//        btnRow.add(deleteBtn);
//
//        inputPanel.add(btnRow, gbc);
//
//        add(inputPanel, BorderLayout.EAST);
//
//        // Row selection populates fields
//        table.addMouseListener(new java.awt.event.MouseAdapter() {
//            public void mouseClicked(java.awt.event.MouseEvent evt) {
//                int r = table.getSelectedRow();
//                if (r != -1) {
//                    nameField.setText(model.getValueAt(r, 1).toString());
//                    descField.setText(model.getValueAt(r, 2).toString());
//                    priceField.setText(model.getValueAt(r, 3).toString());
//                    stockField.setText(model.getValueAt(r, 4).toString());
//                    Object ex = model.getValueAt(r, 5);
//                    if (ex != null) {
//                        try {
//                            LocalDate ld = LocalDate.parse(ex.toString());
//                            Date d = Date.from(ld.atStartOfDay(ZoneId.systemDefault()).toInstant());
//                            expirySpinner.setValue(d);
//                        } catch (Exception ignore) {}
//                    }
//                }
//            }
//        });
//
//        // Button actions
//        addBtn.addActionListener(e -> addMedicine());
//        updateBtn.addActionListener(e -> updateMedicine());
//        deleteBtn.addActionListener(e -> deleteMedicine());
//
//        // After UI ready, show alerts if any
//        SwingUtilities.invokeLater(() -> {
//            checkAlerts(); // low stock & expiring soon
//        });
//
//        setVisible(true);
//    }
//
//    private JButton createStyledButton(String text, Color color) {
//        JButton btn = new JButton(text);
//        btn.setBackground(color);
//        btn.setForeground(Color.WHITE);
//        btn.setFont(new Font("Segoe UI", Font.BOLD, 13));
//        btn.setFocusPainted(false);
//        btn.setBorder(BorderFactory.createEmptyBorder(8, 16, 8, 16));
//        return btn;
//    }
//
//    // ---------------------------------------------------
//    // load data
//    private void loadMedicines() {
//        model.setRowCount(0);
//        List<Medicine> list = medDAO.getAllMedicines();
//        for (Medicine m : list) {
//            String expiryStr = (m.getExpiryDate() != null) ? m.getExpiryDate().toString() : "";
//            model.addRow(new Object[]{
//                    m.getId(),
//                    m.getName(),
//                    m.getDescription(),
//                    m.getPrice(),
//                    m.getStock(),
//                    expiryStr
//            });
//        }
//    }
//
//    // add
//    private void addMedicine() {
//        String name = nameField.getText().trim();
//        String desc = descField.getText().trim();
//        String priceTxt = priceField.getText().trim();
//        String stockTxt = stockField.getText().trim();
//
//        if (name.isEmpty() || priceTxt.isEmpty() || stockTxt.isEmpty()) {
//            JOptionPane.showMessageDialog(this, "Name, Price and Stock required!");
//            return;
//        }
//
//        try {
//            double price = Double.parseDouble(priceTxt);
//            int stock = Integer.parseInt(stockTxt);
//
//            Date spinnerDate = (Date) expirySpinner.getValue();
//            LocalDate expiry = spinnerDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
//
//            Medicine m = new Medicine();
//            m.setName(name);
//            m.setDescription(desc);
//            m.setPrice(price);
//            m.setStock(stock);
//            m.setExpiryDate(expiry);
//
//            boolean ok = medDAO.addMedicine(m);
//            if (ok) {
//                JOptionPane.showMessageDialog(this, "Medicine added!");
//                loadMedicines();
//            } else {
//                JOptionPane.showMessageDialog(this, "Failed to add medicine.");
//            }
//
//        } catch (NumberFormatException ex) {
//            JOptionPane.showMessageDialog(this, "Invalid number format.");
//        } catch (Exception ex) {
//            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
//        }
//    }
//
//    // update
//    private void updateMedicine() {
//        int row = table.getSelectedRow();
//        if (row == -1) {
//            JOptionPane.showMessageDialog(this, "Select a row first!");
//            return;
//        }
//
//        try {
//            int id = (int) model.getValueAt(row, 0);
//            String name = nameField.getText().trim();
//            String desc = descField.getText().trim();
//            double price = Double.parseDouble(priceField.getText().trim());
//            int stock = Integer.parseInt(stockField.getText().trim());
//
//            Date spinnerDate = (Date) expirySpinner.getValue();
//            LocalDate expiry = spinnerDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
//
//            Medicine m = new Medicine(id, name, desc, price, stock, expiry);
//            boolean ok = medDAO.updateMedicine(m);
//            if (ok) {
//                JOptionPane.showMessageDialog(this, "Medicine updated!");
//                loadMedicines();
//            } else {
//                JOptionPane.showMessageDialog(this, "Failed to update medicine.");
//            }
//        } catch (Exception ex) {
//            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
//        }
//    }
//
//    // delete
//    private void deleteMedicine() {
//        int row = table.getSelectedRow();
//        if (row == -1) {
//            JOptionPane.showMessageDialog(this, "Select a row first!");
//            return;
//        }
//
//        int id = (int) model.getValueAt(row, 0);
//        int confirm = JOptionPane.showConfirmDialog(this, "Delete selected medicine?", "Confirm", JOptionPane.YES_NO_OPTION);
//        if (confirm != JOptionPane.YES_OPTION) return;
//
//        boolean ok = medDAO.deleteMedicine(id);
//        if (ok) {
//            JOptionPane.showMessageDialog(this, "Medicine deleted!");
//            loadMedicines();
//        } else {
//            JOptionPane.showMessageDialog(this, "Failed to delete medicine.");
//        }
//    }
//
//    // check alerts
//    private void checkAlerts() {
//        // low stock threshold
//        int threshold = 10;
//        List<Medicine> lowStock = medDAO.getLowStock(threshold);
//        List<Medicine> expiring = medDAO.getExpiringWithinDays(30);
//
//        StringBuilder sb = new StringBuilder();
//        if (!lowStock.isEmpty()) {
//            sb.append("Low stock medicines (<= ").append(threshold).append("):\n");
//            for (Medicine m : lowStock) {
//                sb.append(" • ").append(m.getName()).append(" (").append(m.getStock()).append(")\n");
//            }
//        }
//
//        if (!expiring.isEmpty()) {
//            if (sb.length() > 0) sb.append("\n");
//            sb.append("Medicines expiring within 30 days:\n");
//            for (Medicine m : expiring) {
//                sb.append(" • ").append(m.getName()).append(" (expires ").append(m.getExpiryDate()).append(")\n");
//            }
//        }
//
//        if (sb.length() > 0) {
//            JTextArea area = new JTextArea(sb.toString());
//            area.setEditable(false);
//            area.setBackground(null);
//            area.setFont(new Font("Segoe UI", Font.PLAIN, 13));
//            JOptionPane.showMessageDialog(this, new JScrollPane(area), "Alerts", JOptionPane.WARNING_MESSAGE);
//        }
//    }
//}



//package ui;
//
//import dao.MedicineDAO;
//import model.Medicine;
//
//import javax.swing.*;
//import javax.swing.table.DefaultTableModel;
//import java.awt.*;
//import java.io.File;
//import java.sql.SQLIntegrityConstraintViolationException;
//import java.time.LocalDate;
//import java.time.format.DateTimeParseException;
//import java.util.List;
//
//public class MedicineManagementPage extends JFrame {
//
//    JTable table;
//    DefaultTableModel model;
//
//    JTextField nameField, priceField, stockField, searchField, expiryField;
//    JTextArea descArea;
//    JComboBox<String> categoryBox;
//    JLabel imagePreview;
//    String imagePath = null; // relative path to be stored in DB
//
//    JButton uploadImageBtn;
//    JButton addBtn, updateBtn, deleteBtn, searchBtn;
//
//    MedicineDAO dao = new MedicineDAO();
//
//    public MedicineManagementPage() {
//
//        setTitle("Manage Medicines");
//        setSize(1100, 720);
//        setLocationRelativeTo(null);
//        setLayout(new BorderLayout());
//
//        // TOP - SEARCH PANEL
//        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
//        searchField = new JTextField(20);
//
//        JComboBox<String> searchCategoryBox = new JComboBox<>(new String[]{
//                "All", "Tablet", "Capsule", "Syrup", "Injection", "Drops",
//                "Cream", "Vitamin", "Ayurvedic", "Other"
//        });
//        searchBtn = new JButton("Search");
//
//        searchPanel.add(new JLabel("Search: "));
//        searchPanel.add(searchField);
//        searchPanel.add(new JLabel("Category: "));
//        searchPanel.add(searchCategoryBox);
//        searchPanel.add(searchBtn);
//
//        add(searchPanel, BorderLayout.NORTH);
//
//        // CENTER - TABLE
//        model = new DefaultTableModel(
//                new String[]{"ID", "Name", "Description", "Category", "Price", "Stock", "Expiry", "ImagePath"},
//                0
//        ) {
//            // make table non-editable
//            @Override
//            public boolean isCellEditable(int row, int column) {
//                return false;
//            }
//        };
//
//        table = new JTable(model);
//        table.setRowHeight(28);
//        loadMedicines();
//
//        // Properly hide ImagePath column from VIEW (not from model)
//        // removeColumn must be done after the table has columns
//        if (table.getColumnModel().getColumnCount() > 7) {
//            table.removeColumn(table.getColumnModel().getColumn(7));
//        }
//
//        add(new JScrollPane(table), BorderLayout.CENTER);
//
//        // RIGHT - Form Panel
//        JPanel form = new JPanel(new GridBagLayout());
//        form.setBorder(BorderFactory.createTitledBorder("Medicine Details"));
//        GridBagConstraints gbc = new GridBagConstraints();
//        gbc.insets = new Insets(6, 6, 6, 6);
//        gbc.fill = GridBagConstraints.HORIZONTAL;
//
//        int row = 0;
//        gbc.gridx = 0;
//        gbc.gridy = row;
//        form.add(new JLabel("Name:"), gbc);
//        gbc.gridx = 1;
//        nameField = new JTextField(18);
//        form.add(nameField, gbc);
//
//        row++;
//        gbc.gridx = 0;
//        gbc.gridy = row;
//        form.add(new JLabel("Description:"), gbc);
//        gbc.gridx = 1;
//        descArea = new JTextArea(3, 18);
//        form.add(new JScrollPane(descArea), gbc);
//
//        row++;
//        gbc.gridx = 0;
//        gbc.gridy = row;
//        form.add(new JLabel("Category:"), gbc);
//        gbc.gridx = 1;
//        categoryBox = new JComboBox<>(new String[]{
//                "Tablet", "Capsule", "Syrup", "Injection", "Drops", "Cream", "Vitamin", "Ayurvedic", "Other"
//        });
//        form.add(categoryBox, gbc);
//
//        row++;
//        gbc.gridx = 0;
//        gbc.gridy = row;
//        form.add(new JLabel("Price:"), gbc);
//        gbc.gridx = 1;
//        priceField = new JTextField();
//        form.add(priceField, gbc);
//
//        row++;
//        gbc.gridx = 0;
//        gbc.gridy = row;
//        form.add(new JLabel("Stock:"), gbc);
//        gbc.gridx = 1;
//        stockField = new JTextField();
//        form.add(stockField, gbc);
//
//        row++;
//        gbc.gridx = 0;
//        gbc.gridy = row;
//        form.add(new JLabel("Expiry (YYYY-MM-DD):"), gbc);
//        gbc.gridx = 1;
//        expiryField = new JTextField();
//        form.add(expiryField, gbc);
//
//        row++;
//        gbc.gridx = 0;
//        gbc.gridy = row;
//        form.add(new JLabel("Image:"), gbc);
//        gbc.gridx = 1;
//        JPanel imgRow = new JPanel(new FlowLayout(FlowLayout.LEFT));
//        uploadImageBtn = new JButton("Upload Image");
//        imagePreview = new JLabel();
//        imagePreview.setPreferredSize(new Dimension(120, 120));
//        imagePreview.setBorder(BorderFactory.createLineBorder(Color.GRAY));
//        imgRow.add(uploadImageBtn);
//        imgRow.add(imagePreview);
//        form.add(imgRow, gbc);
//
//        row++;
//        gbc.gridx = 0;
//        gbc.gridy = row;
//        addBtn = new JButton("Add");
//        form.add(addBtn, gbc);
//        gbc.gridx = 1;
//        updateBtn = new JButton("Update");
//        form.add(updateBtn, gbc);
//
//        row++;
//        gbc.gridx = 0;
//        gbc.gridy = row;
//        deleteBtn = new JButton("Delete");
//        form.add(deleteBtn, gbc);
//
//        add(form, BorderLayout.EAST);
//
//        // EVENTS
//        uploadImageBtn.addActionListener(e -> chooseImage()); // copies file into medicine_images and sets imagePath
//        addBtn.addActionListener(e -> addMedicine());
//        updateBtn.addActionListener(e -> updateMedicine());
//        deleteBtn.addActionListener(e -> deleteMedicine());
//
//        searchBtn.addActionListener(e -> {
//            String keyword = searchField.getText().trim();
//            String selCat = searchCategoryBox.getSelectedItem().toString();
//            List<Medicine> list;
//            if (selCat.equals("All")) {
//                list = dao.searchMedicines(keyword);
//            } else {
//                list = dao.searchMedicinesByCategory(keyword, selCat);
//            }
//            model.setRowCount(0);
//            for (Medicine m : list) {
//                model.addRow(new Object[]{
//                        m.getId(), m.getName(), m.getDescription(),
//                        m.getCategory(), m.getPrice(), m.getStock(),
//                        m.getExpiryDate(), m.getImagePath()
//                });
//            }
//        });
//
//        table.getSelectionModel().addListSelectionListener(e -> fillFormFromTable());
//
//        setVisible(true);
//    }
//
//    // Choose image, copy to ./medicine_images/, set imagePath (relative), preview
//    private void chooseImage() {
//        JFileChooser fileChooser = new JFileChooser();
//        fileChooser.setDialogTitle("Select Medicine Image");
//        fileChooser.setAcceptAllFileFilterUsed(false);
//        fileChooser.addChoosableFileFilter(new javax.swing.filechooser.FileNameExtensionFilter(
//                "Image files", "png", "jpg", "jpeg"
//        ));
//
//        int result = fileChooser.showOpenDialog(this);
//        if (result != JFileChooser.APPROVE_OPTION) return;
//
//        File selectedFile = fileChooser.getSelectedFile();
//        try {
//            File imagesDir = new File("medicine_images");
//            if (!imagesDir.exists()) imagesDir.mkdirs();
//
//            String name = selectedFile.getName();
//            String ext = "";
//            int i = name.lastIndexOf('.');
//            if (i > 0) ext = name.substring(i);
//
//            String newFileName = "med_" + System.currentTimeMillis() + ext;
//            File dest = new File(imagesDir, newFileName);
//
//            java.nio.file.Files.copy(selectedFile.toPath(), dest.toPath(), java.nio.file.StandardCopyOption.REPLACE_EXISTING);
//
//            // store RELATIVE path (works when packaging app)
//            imagePath = "medicine_images/" + newFileName;
//
//            // Preview
//            ImageIcon icon = new ImageIcon(new ImageIcon(imagePath).getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH));
//            imagePreview.setIcon(icon);
//
//        } catch (Exception ex) {
//            ex.printStackTrace();
//            JOptionPane.showMessageDialog(this, "Failed to upload image: " + ex.getMessage());
//        }
//    }
//
//    private void loadMedicines() {
//        model.setRowCount(0);
//        List<Medicine> list = dao.getAllMedicines();
//        for (Medicine m : list) {
//            model.addRow(new Object[]{
//                    m.getId(), m.getName(), m.getDescription(),
//                    m.getCategory(), m.getPrice(), m.getStock(),
//                    m.getExpiryDate(), m.getImagePath()
//
//            });
//
//
//        }
//        checkLowStockAlert();
//    }
//
//    private void addMedicine() {
//        try {
//            String name = nameField.getText().trim();
//            String desc = descArea.getText().trim();
//            String category = categoryBox.getSelectedItem().toString();
//            double price = Double.parseDouble(priceField.getText().trim());
//            int stock = Integer.parseInt(stockField.getText().trim());
//            LocalDate expiry = null;
//            if (!expiryField.getText().trim().isEmpty()) {
//                expiry = LocalDate.parse(expiryField.getText().trim());
//            }
//
//            Medicine m = new Medicine(0, name, desc, price, stock, category, expiry, imagePath);
//            if (dao.addMedicine(m)) {
//                JOptionPane.showMessageDialog(this, "Medicine added!");
//                clearForm();
//                loadMedicines();
//            } else {
//                JOptionPane.showMessageDialog(this, "Failed to add medicine.");
//            }
//        } catch (NumberFormatException ex) {
//            JOptionPane.showMessageDialog(this, "Price and stock must be numbers.");
//        } catch (DateTimeParseException ex) {
//            JOptionPane.showMessageDialog(this, "Expiry date format should be YYYY-MM-DD or leave empty.");
//        } catch (Exception ex) {
//            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
//        }
//    }
//
//    private void updateMedicine() {
//        int row = table.getSelectedRow();
//        if (row == -1) { JOptionPane.showMessageDialog(this, "Select a medicine to update."); return; }
//
//        int modelRow = table.convertRowIndexToModel(row);
//
//        try {
//            int id = (int) model.getValueAt(modelRow, 0);
//            String name = nameField.getText().trim();
//            String desc = descArea.getText().trim();
//            String category = categoryBox.getSelectedItem().toString();
//            double price = Double.parseDouble(priceField.getText().trim());
//            int stock = Integer.parseInt(stockField.getText().trim());
//            LocalDate expiry = null;
//            if (!expiryField.getText().trim().isEmpty()) {
//                expiry = LocalDate.parse(expiryField.getText().trim());
//            }
//
//            // Keep old image if user didn't upload a new one
//            if (imagePath == null || imagePath.trim().isEmpty()) {
//                Object old = model.getValueAt(modelRow, 7);
//                imagePath = (old == null) ? null : String.valueOf(old);
//            }
//
//            Medicine m = new Medicine(id, name, desc, price, stock, category, expiry, imagePath);
//            if (dao.updateMedicine(m)) {
//                JOptionPane.showMessageDialog(this, "Medicine updated!");
//                clearForm();
//                loadMedicines();
//            } else {
//                JOptionPane.showMessageDialog(this, "Update failed.");
//            }
//        } catch (NumberFormatException ex) {
//            JOptionPane.showMessageDialog(this, "Price and stock must be numbers.");
//        } catch (DateTimeParseException ex) {
//            JOptionPane.showMessageDialog(this, "Expiry date format should be YYYY-MM-DD or leave empty.");
//        } catch (Exception ex) {
//            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
//        }
//    }
//
//    private void deleteMedicine() {
//        int row = table.getSelectedRow();
//        if (row == -1) {
//            JOptionPane.showMessageDialog(this, "Select a row to delete.");
//            return;
//        }
//
//        int modelRow = table.convertRowIndexToModel(row);
//        int id = (int) model.getValueAt(modelRow, 0);
//
//        int confirm = JOptionPane.showConfirmDialog(
//                this, "Delete selected medicine?", "Confirm", JOptionPane.YES_NO_OPTION);
//
//        if (confirm != JOptionPane.YES_OPTION) return;
//
//        try {
//            if (dao.deleteMedicine(id)) {
//                JOptionPane.showMessageDialog(this, "Deleted.");
//                clearForm();
//                loadMedicines();
//            } else {
//                JOptionPane.showMessageDialog(this, "Delete failed.");
//            }
//        } catch (SQLIntegrityConstraintViolationException ex) {
//            JOptionPane.showMessageDialog(
//                    this,
//                    "You cannot delete this medicine because it is used in orders.\n"
//                            + "Delete the related orders first.",
//                    "Cannot Delete",
//                    JOptionPane.ERROR_MESSAGE
//            );
//        }
//    }
//
//    private void checkLowStockAlert() {
//        int threshold = 5; // you can change
//
//        List<Medicine> lowList = dao.getLowStock(threshold);
//
//        if (!lowList.isEmpty()) {
//            StringBuilder msg = new StringBuilder("⚠ Low Stock Medicines:\n\n");
//
//            for (Medicine m : lowList) {
//                msg.append(m.getName())
//                        .append(" (Stock: ").append(m.getStock()).append(")\n");
//            }
//
//            JOptionPane.showMessageDialog(
//                    this,
//                    msg.toString(),
//                    "Low Stock Alert",
//                    JOptionPane.WARNING_MESSAGE
//            );
//        }
//    }
//
//
//
//
//    // fill form and show preview on table select
//    private void fillFormFromTable() {
//        int row = table.getSelectedRow();
//        if (row == -1) return;
//
//        int modelRow = table.convertRowIndexToModel(row);
//
//        nameField.setText(String.valueOf(model.getValueAt(modelRow, 1)));
//        descArea.setText(String.valueOf(model.getValueAt(modelRow, 2)));
//        categoryBox.setSelectedItem(String.valueOf(model.getValueAt(modelRow, 3)));
//        priceField.setText(String.valueOf(model.getValueAt(modelRow, 4)));
//        stockField.setText(String.valueOf(model.getValueAt(modelRow, 5)));
//        Object exp = model.getValueAt(modelRow, 6);
//        expiryField.setText(exp == null ? "" : String.valueOf(exp));
//        Object ip = model.getValueAt(modelRow, 7);
//        imagePath = (ip == null) ? null : String.valueOf(ip);
//
//        if (imagePath != null && !imagePath.trim().isEmpty()) {
//            File f = new File(imagePath);
//            if (f.exists()) {
//                ImageIcon icon = new ImageIcon(new ImageIcon(imagePath).getImage().getScaledInstance(120,120,Image.SCALE_SMOOTH));
//                imagePreview.setIcon(icon);
//            } else {
//                imagePreview.setIcon(null);
//            }
//        } else {
//            imagePreview.setIcon(null);
//        }
//    }
//
//    private void clearForm() {
//        nameField.setText("");
//        descArea.setText("");
//        priceField.setText("");
//        stockField.setText("");
//        expiryField.setText("");
//        categoryBox.setSelectedIndex(0);
//        imagePreview.setIcon(null);
//        imagePath = null;
//    }
//}




package ui;

import dao.MedicineDAO;
import model.Medicine;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.File;
import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

public class MedicineManagementPage extends JFrame {

    JTable table;
    DefaultTableModel model;

    JTextField nameField, priceField, stockField, searchField, expiryField;
    JTextArea descArea;
    JComboBox<String> categoryBox;
    JLabel imagePreview;
    String imagePath = null; // relative path to be stored in DB

    JButton uploadImageBtn;
    JButton addBtn, updateBtn, deleteBtn, searchBtn;

    MedicineDAO dao = new MedicineDAO();

    public MedicineManagementPage() {

        // --- START: Apply Nimbus Look and Feel for a modern look (Built-in Swing) ---
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            // Fallback to default L&F if Nimbus fails
            System.err.println("Nimbus L&F not found. Using default.");
        }
        // --- END: Apply Nimbus Look and Feel ---


        setTitle("Manage Medicines");
        setSize(1100, 720);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10)); // Added outer spacing

        // --- TOP - SEARCH PANEL ---
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 10)); // Added spacing
        searchField = new JTextField(20);

        JComboBox<String> searchCategoryBox = new JComboBox<>(new String[]{
                "All", "Tablet", "Capsule", "Syrup", "Injection", "Drops",
                "Cream", "Vitamin", "Ayurvedic", "Other"
        });
        searchBtn = new JButton("Search");

        // STYLE Search Button
        searchBtn.setBackground(new Color(108, 117, 125)); // Secondary Gray
        searchBtn.setForeground(Color.WHITE);
        searchBtn.setFocusPainted(false);

        searchPanel.add(new JLabel("Search: "));
        searchPanel.add(searchField);
        searchPanel.add(new JLabel("Category: "));
        searchPanel.add(searchCategoryBox);
        searchPanel.add(searchBtn);

        add(searchPanel, BorderLayout.NORTH);

        // --- CENTER - TABLE ---
        model = new DefaultTableModel(
                new String[]{"ID", "Name", "Description", "Category", "Price", "Stock", "Expiry", "ImagePath"},
                0
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        table = new JTable(model);
        table.setRowHeight(28);

        // --- TABLE VISUAL IMPROVEMENTS ---
        table.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 14));
        table.getTableHeader().setBackground(new Color(230, 230, 230));
        // Ensure minimal spacing for a cleaner grid look
        table.setShowGrid(false);
        table.setIntercellSpacing(new Dimension(0, 0));
        // --- END OF TABLE VISUAL IMPROVEMENTS ---

        loadMedicines();

        // Properly hide ImagePath column
        if (table.getColumnModel().getColumnCount() > 7) {
            table.removeColumn(table.getColumnModel().getColumn(7));
        }

        add(new JScrollPane(table), BorderLayout.CENTER);

        // --- RIGHT - Form Panel ---
        JPanel form = new JPanel(new GridBagLayout());
        form.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(150, 150, 150)), // Border color
                "Medicine Details",
                javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
                javax.swing.border.TitledBorder.DEFAULT_POSITION,
                new Font("SansSerif", Font.BOLD, 14), // Title Font
                new Color(50, 50, 50) // Title Color
        ));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 8, 10, 8); // Increased vertical and horizontal padding
        gbc.fill = GridBagConstraints.HORIZONTAL;

        int row = 0;

        // Name Field
        gbc.gridx = 0;
        gbc.gridy = row;
        form.add(new JLabel("Name:"), gbc);
        gbc.gridx = 1;
        nameField = new JTextField(18);
        form.add(nameField, gbc);

        // Description Area
        row++;
        gbc.gridx = 0;
        gbc.gridy = row;
        form.add(new JLabel("Description:"), gbc);
        gbc.gridx = 1;
        descArea = new JTextArea(3, 18);
        descArea.setLineWrap(true); // Ensure text wraps nicely
        form.add(new JScrollPane(descArea), gbc);

        // Category Box
        row++;
        gbc.gridx = 0;
        gbc.gridy = row;
        form.add(new JLabel("Category:"), gbc);
        gbc.gridx = 1;
        categoryBox = new JComboBox<>(new String[]{
                "Tablet", "Capsule", "Syrup", "Injection", "Drops", "Cream", "Vitamin", "Ayurvedic", "Other"
        });
        form.add(categoryBox, gbc);

        // Price Field
        row++;
        gbc.gridx = 0;
        gbc.gridy = row;
        form.add(new JLabel("Price:"), gbc);
        gbc.gridx = 1;
        priceField = new JTextField();
        form.add(priceField, gbc);

        // Stock Field
        row++;
        gbc.gridx = 0;
        gbc.gridy = row;
        form.add(new JLabel("Stock:"), gbc);
        gbc.gridx = 1;
        stockField = new JTextField();
        form.add(stockField, gbc);

        // Expiry Field
        row++;
        gbc.gridx = 0;
        gbc.gridy = row;
        form.add(new JLabel("Expiry (YYYY-MM-DD):"), gbc);
        gbc.gridx = 1;
        expiryField = new JTextField();
        form.add(expiryField, gbc);

        // Image Row
        row++;
        gbc.gridx = 0;
        gbc.gridy = row;
        form.add(new JLabel("Image:"), gbc);
        gbc.gridx = 1;
        JPanel imgRow = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        uploadImageBtn = new JButton("Upload Image");
        uploadImageBtn.setFocusPainted(false); // Clean up button look
        imagePreview = new JLabel();
        imagePreview.setPreferredSize(new Dimension(120, 120));
        imagePreview.setHorizontalAlignment(SwingConstants.CENTER);
        imagePreview.setVerticalAlignment(SwingConstants.CENTER);
        imagePreview.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        imgRow.add(uploadImageBtn);
        imgRow.add(imagePreview);
        form.add(imgRow, gbc);

        // Add Button
        row++;
        gbc.gridx = 0;
        gbc.gridy = row;
        addBtn = new JButton("Add");
        addBtn.setBackground(new Color(40, 167, 69)); // Success Green
        addBtn.setForeground(Color.WHITE);
        addBtn.setFocusPainted(false);
        form.add(addBtn, gbc);

        // Update Button
        gbc.gridx = 1;
        updateBtn = new JButton("Update");
        updateBtn.setBackground(new Color(0, 123, 255)); // Primary Blue
        updateBtn.setForeground(Color.WHITE);
        updateBtn.setFocusPainted(false);
        form.add(updateBtn, gbc);

        // Delete Button
        row++;
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.gridwidth = 2; // Span across two columns
        deleteBtn = new JButton("Delete");
        deleteBtn.setBackground(new Color(220, 53, 69)); // Danger Red
        deleteBtn.setForeground(Color.WHITE);
        deleteBtn.setFocusPainted(false);
        form.add(deleteBtn, gbc);
        gbc.gridwidth = 1; // Reset width

        add(form, BorderLayout.EAST);

        // --- EVENTS ---
        uploadImageBtn.addActionListener(e -> chooseImage());
        addBtn.addActionListener(e -> addMedicine());
        updateBtn.addActionListener(e -> updateMedicine());
        deleteBtn.addActionListener(e -> deleteMedicine());

        searchBtn.addActionListener(e -> {
            String keyword = searchField.getText().trim();
            String selCat = searchCategoryBox.getSelectedItem().toString();
            List<Medicine> list;
            if (selCat.equals("All")) {
                list = dao.searchMedicines(keyword);
            } else {
                list = dao.searchMedicinesByCategory(keyword, selCat);
            }
            model.setRowCount(0);
            for (Medicine m : list) {
                model.addRow(new Object[]{
                        m.getId(), m.getName(), m.getDescription(),
                        m.getCategory(), m.getPrice(), m.getStock(),
                        m.getExpiryDate(), m.getImagePath()
                });
            }
        });

        table.getSelectionModel().addListSelectionListener(e -> fillFormFromTable());

        setVisible(true);
    }

    // --- Utility Methods (Unchanged, but included for completeness) ---

    // Choose image, copy to ./medicine_images/, set imagePath (relative), preview
    private void chooseImage() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Select Medicine Image");
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.addChoosableFileFilter(new javax.swing.filechooser.FileNameExtensionFilter(
                "Image files", "png", "jpg", "jpeg"
        ));

        int result = fileChooser.showOpenDialog(this);
        if (result != JFileChooser.APPROVE_OPTION) return;

        File selectedFile = fileChooser.getSelectedFile();
        try {
            File imagesDir = new File("medicine_images");
            if (!imagesDir.exists()) imagesDir.mkdirs();

            String name = selectedFile.getName();
            String ext = "";
            int i = name.lastIndexOf('.');
            if (i > 0) ext = name.substring(i);

            String newFileName = "med_" + System.currentTimeMillis() + ext;
            File dest = new File(imagesDir, newFileName);

            java.nio.file.Files.copy(selectedFile.toPath(), dest.toPath(), java.nio.file.StandardCopyOption.REPLACE_EXISTING);

            // store RELATIVE path (works when packaging app)
            imagePath = "medicine_images/" + newFileName;

            // Preview
            ImageIcon icon = new ImageIcon(new ImageIcon(imagePath).getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH));
            imagePreview.setIcon(icon);

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to upload image: " + ex.getMessage());
        }
    }

    private void loadMedicines() {
        model.setRowCount(0);
        List<Medicine> list = dao.getAllMedicines();
        for (Medicine m : list) {
            model.addRow(new Object[]{
                    m.getId(), m.getName(), m.getDescription(),
                    m.getCategory(), m.getPrice(), m.getStock(),
                    m.getExpiryDate(), m.getImagePath()

            });


        }
        checkLowStockAlert();
    }

    private void addMedicine() {
        try {
            String name = nameField.getText().trim();
            String desc = descArea.getText().trim();
            String category = categoryBox.getSelectedItem().toString();
            double price = Double.parseDouble(priceField.getText().trim());
            int stock = Integer.parseInt(stockField.getText().trim());
            LocalDate expiry = null;
            if (!expiryField.getText().trim().isEmpty()) {
                expiry = LocalDate.parse(expiryField.getText().trim());
            }

            Medicine m = new Medicine(0, name, desc, price, stock, category, expiry, imagePath);
            if (dao.addMedicine(m)) {
                JOptionPane.showMessageDialog(this, "Medicine added!");
                clearForm();
                loadMedicines();
            } else {
                JOptionPane.showMessageDialog(this, "Failed to add medicine.");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Price and stock must be numbers.");
        } catch (DateTimeParseException ex) {
            JOptionPane.showMessageDialog(this, "Expiry date format should be YYYY-MM-DD or leave empty.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }

    private void updateMedicine() {
        int row = table.getSelectedRow();
        if (row == -1) { JOptionPane.showMessageDialog(this, "Select a medicine to update."); return; }

        int modelRow = table.convertRowIndexToModel(row);

        try {
            int id = (int) model.getValueAt(modelRow, 0);
            String name = nameField.getText().trim();
            String desc = descArea.getText().trim();
            String category = categoryBox.getSelectedItem().toString();
            double price = Double.parseDouble(priceField.getText().trim());
            int stock = Integer.parseInt(stockField.getText().trim());
            LocalDate expiry = null;
            if (!expiryField.getText().trim().isEmpty()) {
                expiry = LocalDate.parse(expiryField.getText().trim());
            }

            // Keep old image if user didn't upload a new one
            if (imagePath == null || imagePath.trim().isEmpty()) {
                Object old = model.getValueAt(modelRow, 7);
                imagePath = (old == null) ? null : String.valueOf(old);
            }

            Medicine m = new Medicine(id, name, desc, price, stock, category, expiry, imagePath);
            if (dao.updateMedicine(m)) {
                JOptionPane.showMessageDialog(this, "Medicine updated!");
                clearForm();
                loadMedicines();
            } else {
                JOptionPane.showMessageDialog(this, "Update failed.");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Price and stock must be numbers.");
        } catch (DateTimeParseException ex) {
            JOptionPane.showMessageDialog(this, "Expiry date format should be YYYY-MM-DD or leave empty.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }

    private void deleteMedicine() {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Select a row to delete.");
            return;
        }

        int modelRow = table.convertRowIndexToModel(row);
        int id = (int) model.getValueAt(modelRow, 0);

        int confirm = JOptionPane.showConfirmDialog(
                this, "Delete selected medicine?", "Confirm", JOptionPane.YES_NO_OPTION);

        if (confirm != JOptionPane.YES_OPTION) return;

        try {
            if (dao.deleteMedicine(id)) {
                JOptionPane.showMessageDialog(this, "Deleted.");
                clearForm();
                loadMedicines();
            } else {
                JOptionPane.showMessageDialog(this, "Delete failed.");
            }
        } catch (SQLIntegrityConstraintViolationException ex) {
            JOptionPane.showMessageDialog(
                    this,
                    "You cannot delete this medicine because it is used in orders.\n"
                            + "Delete the related orders first.",
                    "Cannot Delete",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void checkLowStockAlert() {
        int threshold = 5; // you can change

        List<Medicine> lowList = dao.getLowStock(threshold);

        if (!lowList.isEmpty()) {
            StringBuilder msg = new StringBuilder("⚠ Low Stock Medicines:\n\n");

            for (Medicine m : lowList) {
                msg.append(m.getName())
                        .append(" (Stock: ").append(m.getStock()).append(")\n");
            }

            JOptionPane.showMessageDialog(
                    this,
                    msg.toString(),
                    "Low Stock Alert",
                    JOptionPane.WARNING_MESSAGE
            );
        }
    }

    // fill form and show preview on table select
    private void fillFormFromTable() {
        int row = table.getSelectedRow();
        if (row == -1) return;

        int modelRow = table.convertRowIndexToModel(row);

        nameField.setText(String.valueOf(model.getValueAt(modelRow, 1)));
        descArea.setText(String.valueOf(model.getValueAt(modelRow, 2)));
        categoryBox.setSelectedItem(String.valueOf(model.getValueAt(modelRow, 3)));
        priceField.setText(String.valueOf(model.getValueAt(modelRow, 4)));
        stockField.setText(String.valueOf(model.getValueAt(modelRow, 5)));
        Object exp = model.getValueAt(modelRow, 6);
        expiryField.setText(exp == null ? "" : String.valueOf(exp));
        Object ip = model.getValueAt(modelRow, 7);
        imagePath = (ip == null) ? null : String.valueOf(ip);

        if (imagePath != null && !imagePath.trim().isEmpty()) {
            File f = new File(imagePath);
            if (f.exists()) {
                ImageIcon icon = new ImageIcon(new ImageIcon(imagePath).getImage().getScaledInstance(120,120,Image.SCALE_SMOOTH));
                imagePreview.setIcon(icon);
            } else {
                imagePreview.setIcon(null);
            }
        } else {
            imagePreview.setIcon(null);
        }
    }

    private void clearForm() {
        nameField.setText("");
        descArea.setText("");
        priceField.setText("");
        stockField.setText("");
        expiryField.setText("");
        categoryBox.setSelectedIndex(0);
        imagePreview.setIcon(null);
        imagePath = null;
    }
}