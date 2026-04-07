//package ui;
//
//import dao.OrderDAO;
//import dao.UserDAO;
//import dao.MedicineDAO;
//import model.Order;
//import model.User;
//import model.Medicine;
//
//import javax.swing.*;
//import javax.swing.table.DefaultTableModel;
//import java.awt.*;
//import java.util.List;
//
//public class ViewOrdersPage extends JFrame {
//
//    private OrderDAO orderDAO = new OrderDAO();
//    private UserDAO userDAO = new UserDAO();
//    private MedicineDAO medicineDAO = new MedicineDAO();
//
//    public ViewOrdersPage() {
//
//        setTitle("All Orders");
//        setSize(800, 500);
//        setLocationRelativeTo(null);
//        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
//
//        // Table Model
//        DefaultTableModel model = new DefaultTableModel();
//        model.setColumnIdentifiers(new String[]{"Order ID", "User", "Medicine", "Quantity", "Total Price"});
//
//        JTable table = new JTable(model);
//        JScrollPane scroll = new JScrollPane(table);
//
//        add(scroll);
//
//        // Load orders from DB
//        loadOrders(model);
//
//        setVisible(true);
//    }
//
//    private void loadOrders(DefaultTableModel model) {
//
//        List<Order> orders = orderDAO.getAllOrders();
//
//        for (Order o : orders) {
//
//            User user = userDAO.getUserById(o.getUserId());
//            Medicine med = medicineDAO.getMedicineById(o.getMedicineId());
//
//            model.addRow(new Object[]{
//                    o.getId(),
//                    user != null ? user.getName() : "Unknown",
//                    med != null ? med.getName() : "Unknown",
//                    o.getQuantity(),
//                    o.getTotalPrice()
//            });
//        }
//    }
//}
//



package ui;

import dao.OrderDAO;
import dao.UserDAO;
import dao.MedicineDAO;
import model.Order;
import model.User;
import model.Medicine;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import javax.swing.table.JTableHeader;


public class ViewOrdersPage extends JFrame {

    private OrderDAO orderDAO = new OrderDAO();
    private UserDAO userDAO = new UserDAO();
    private MedicineDAO medicineDAO = new MedicineDAO();

    public ViewOrdersPage() {

        setTitle("All Orders");
        setSize(900, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // ---------------- HEADER ----------------
        JPanel header = new JPanel();
        header.setBackground(new Color(30, 144, 255));
        header.setPreferredSize(new Dimension(900, 70));

        JLabel title = new JLabel("📄 All Orders");
        title.setFont(new Font("Segoe UI", Font.BOLD, 28));
        title.setForeground(Color.WHITE);
        header.add(title);

        add(header, BorderLayout.NORTH);

        // ---------------- TABLE ----------------
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(new String[]{
                "Order ID", "User", "Medicine", "Quantity", "Total Price (₹)"
        });

        JTable table = new JTable(model);
        table.setRowHeight(28);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 15));

        JTableHeader headerTable = table.getTableHeader();
        headerTable.setFont(new Font("Segoe UI", Font.BOLD, 16));
        headerTable.setBackground(new Color(0, 120, 215));
        headerTable.setForeground(Color.WHITE);

        JScrollPane scroll = new JScrollPane(table);
        add(scroll, BorderLayout.CENTER);

        // Load data
        loadOrders(model);

        setVisible(true);
    }

    private void loadOrders(DefaultTableModel model) {

        List<Order> orders = orderDAO.getAllOrders();

        for (Order o : orders) {
            User user = userDAO.getUserById(o.getUserId());
            Medicine med = medicineDAO.getMedicineById(o.getMedicineId());

            model.addRow(new Object[]{
                    o.getId(),
                    user != null ? user.getName() : "Unknown",
                    med != null ? med.getName() : "Unknown",
                    o.getQuantity(),
                    "₹ " + o.getTotalPrice()
            });
        }
    }
}
