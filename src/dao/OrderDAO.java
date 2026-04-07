package dao;

import config.DBConnection;
import model.Order;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO {

    // ADD ORDER
    public boolean addOrder(Order order) {
        try {
            Connection con = DBConnection.getConnection();

            String query = "INSERT INTO orders(user_id, medicine_id, quantity, total_price) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(query);

            ps.setInt(1, order.getUserId());
            ps.setInt(2, order.getMedicineId());
            ps.setInt(3, order.getQuantity());
            ps.setDouble(4, order.getTotalPrice());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // GET ALL ORDERS
    public List<Order> getAllOrders() {
        List<Order> list = new ArrayList<>();

        try {
            Connection con = DBConnection.getConnection();

            String query = "SELECT * FROM orders";
            PreparedStatement ps = con.prepareStatement(query);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Order o = new Order(
                        rs.getInt("id"),
                        rs.getInt("user_id"),
                        rs.getInt("medicine_id"),
                        rs.getInt("quantity"),
                        rs.getDouble("total_price")
                );
                list.add(o);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}

