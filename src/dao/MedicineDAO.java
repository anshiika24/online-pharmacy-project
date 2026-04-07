//package dao;
//
//import config.DBConnection;
//import model.Medicine;
//
//import java.sql.*;
//import java.util.ArrayList;
//import java.util.List;
//
//public class MedicineDAO {
//
//    // ADD MEDICINE
//    public boolean addMedicine(Medicine medicine) {
//        try {
//            Connection con = DBConnection.getConnection();
//
//            String query = "INSERT INTO medicines(name, description, price, stock) VALUES (?, ?, ?, ?)";
//            PreparedStatement ps = con.prepareStatement(query);
//
//            ps.setString(1, medicine.getName());
//            ps.setString(2, medicine.getDescription());
//            ps.setDouble(3, medicine.getPrice());
//            ps.setInt(4, medicine.getStock());
//
//            return ps.executeUpdate() > 0;
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            return false;
//        }
//    }
//
//    // GET ALL MEDICINES
//    public List<Medicine> getAllMedicines() {
//        List<Medicine> list = new ArrayList<>();
//
//        try {
//            Connection con = DBConnection.getConnection();
//
//            String query = "SELECT * FROM medicines";
//            PreparedStatement ps = con.prepareStatement(query);
//
//            ResultSet rs = ps.executeQuery();
//
//            while (rs.next()) {
//                Medicine m = new Medicine(
//                        rs.getInt("id"),
//                        rs.getString("name"),
//                        rs.getString("description"),
//                        rs.getDouble("price"),
//                        rs.getInt("stock")
//                );
//                list.add(m);
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return list;
//    }
//
//    // UPDATE STOCK
//    public boolean updateStock(int medicineId, int newStock) {
//        try {
//            Connection con = DBConnection.getConnection();
//
//            String query = "UPDATE medicines SET stock = ? WHERE id = ?";
//            PreparedStatement ps = con.prepareStatement(query);
//
//            ps.setInt(1, newStock);
//            ps.setInt(2, medicineId);
//
//            return ps.executeUpdate() > 0;
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            return false;
//        }
//    }
//
//}


//package dao;
//
//import config.DBConnection;
//import model.Medicine;
//
//import java.sql.*;
//import java.util.ArrayList;
//import java.util.List;
//
//public class MedicineDAO {
//
//    // ADD MEDICINE
//    public boolean addMedicine(Medicine medicine) {
//        try {
//            Connection con = DBConnection.getConnection();
//
//            String query = "INSERT INTO medicines(name, description, price, stock) VALUES (?, ?, ?, ?)";
//            PreparedStatement ps = con.prepareStatement(query);
//
//            ps.setString(1, medicine.getName());
//            ps.setString(2, medicine.getDescription());
//            ps.setDouble(3, medicine.getPrice());
//            ps.setInt(4, medicine.getStock());
//
//            return ps.executeUpdate() > 0;
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            return false;
//        }
//    }
//
//    // GET ALL MEDICINES
//    public List<Medicine> getAllMedicines() {
//        List<Medicine> list = new ArrayList<>();
//
//        try {
//            Connection con = DBConnection.getConnection();
//
//            String query = "SELECT * FROM medicines";
//            PreparedStatement ps = con.prepareStatement(query);
//
//            ResultSet rs = ps.executeQuery();
//
//            while (rs.next()) {
//                Medicine m = new Medicine(
//                        rs.getInt("id"),
//                        rs.getString("name"),
//                        rs.getString("description"),
//                        rs.getDouble("price"),
//                        rs.getInt("stock")
//                );
//                list.add(m);
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return list;
//    }
//
//    // UPDATE STOCK
//    public boolean updateStock(int medicineId, int newStock) {
//        try {
//            Connection con = DBConnection.getConnection();
//
//            String query = "UPDATE medicines SET stock = ? WHERE id = ?";
//            PreparedStatement ps = con.prepareStatement(query);
//
//            ps.setInt(1, newStock);
//            ps.setInt(2, medicineId);
//
//            return ps.executeUpdate() > 0;
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            return false;
//        }
//    }
//
//    // ✅ GET MEDICINE BY ID (useful for View Orders page)
//    public Medicine getMedicineById(int id) {
//        Medicine m = null;
//        try {
//            Connection con = DBConnection.getConnection();
//            String q = "SELECT * FROM medicines WHERE id = ?";
//            PreparedStatement ps = con.prepareStatement(q);
//            ps.setInt(1, id);
//            ResultSet rs = ps.executeQuery();
//            if (rs.next()) {
//                m = new Medicine(
//                        rs.getInt("id"),
//                        rs.getString("name"),
//                        rs.getString("description"),
//                        rs.getDouble("price"),
//                        rs.getInt("stock")
//                );
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return m;
//    }
//
//}


//package dao;
//
//import config.DBConnection;
//import model.Medicine;
//
//import java.sql.*;
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.List;
//
//public class MedicineDAO {
//
//    // ADD MEDICINE (now with expiry_date)
//    public boolean addMedicine(Medicine medicine) {
//        String sql = "INSERT INTO medicines(name, description, price, stock, expiry_date) VALUES (?, ?, ?, ?, ?)";
//        try (Connection con = DBConnection.getConnection();
//             PreparedStatement ps = con.prepareStatement(sql)) {
//
//            ps.setString(1, medicine.getName());
//            ps.setString(2, medicine.getDescription());
//            ps.setDouble(3, medicine.getPrice());
//            ps.setInt(4, medicine.getStock());
//            if (medicine.getExpiryDate() != null) {
//                ps.setDate(5, Date.valueOf(medicine.getExpiryDate()));
//            } else {
//                ps.setDate(5, null);
//            }
//
//            return ps.executeUpdate() > 0;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return false;
//        }
//    }
//
//    // GET ALL MEDICINES (with expiry_date)
//    public List<Medicine> getAllMedicines() {
//        List<Medicine> list = new ArrayList<>();
//        String sql = "SELECT * FROM medicines";
//        try (Connection con = DBConnection.getConnection();
//             PreparedStatement ps = con.prepareStatement(sql);
//             ResultSet rs = ps.executeQuery()) {
//
//            while (rs.next()) {
//                LocalDate expiry = null;
//                Date d = rs.getDate("expiry_date");
//                if (d != null) expiry = d.toLocalDate();
//
//                Medicine m = new Medicine(
//                        rs.getInt("id"),
//                        rs.getString("name"),
//                        rs.getString("description"),
//                        rs.getDouble("price"),
//                        rs.getInt("stock"),
//                        expiry
//                );
//                list.add(m);
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return list;
//    }
//
//    // GET BY ID
//    public Medicine getMedicineById(int id) {
//        Medicine m = null;
//        String sql = "SELECT * FROM medicines WHERE id = ?";
//        try (Connection con = DBConnection.getConnection();
//             PreparedStatement ps = con.prepareStatement(sql)) {
//
//            ps.setInt(1, id);
//            try (ResultSet rs = ps.executeQuery()) {
//                if (rs.next()) {
//                    LocalDate expiry = null;
//                    Date d = rs.getDate("expiry_date");
//                    if (d != null) expiry = d.toLocalDate();
//
//                    m = new Medicine(
//                            rs.getInt("id"),
//                            rs.getString("name"),
//                            rs.getString("description"),
//                            rs.getDouble("price"),
//                            rs.getInt("stock"),
//                            expiry
//                    );
//                }
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return m;
//    }
//
//    // UPDATE MEDICINE (with expiry_date)
//    public boolean updateMedicine(Medicine medicine) {
//        String sql = "UPDATE medicines SET name=?, description=?, price=?, stock=?, expiry_date=? WHERE id=?";
//        try (Connection con = DBConnection.getConnection();
//             PreparedStatement ps = con.prepareStatement(sql)) {
//
//            ps.setString(1, medicine.getName());
//            ps.setString(2, medicine.getDescription());
//            ps.setDouble(3, medicine.getPrice());
//            ps.setInt(4, medicine.getStock());
//            if (medicine.getExpiryDate() != null) {
//                ps.setDate(5, Date.valueOf(medicine.getExpiryDate()));
//            } else {
//                ps.setDate(5, null);
//            }
//            ps.setInt(6, medicine.getId());
//
//            return ps.executeUpdate() > 0;
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            return false;
//        }
//    }
//
//    // DELETE MEDICINE
//    public boolean deleteMedicine(int id) {
//        String sql = "DELETE FROM medicines WHERE id = ?";
//        try (Connection con = DBConnection.getConnection();
//             PreparedStatement ps = con.prepareStatement(sql)) {
//            ps.setInt(1, id);
//            return ps.executeUpdate() > 0;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return false;
//        }
//    }
//
//    public boolean updateStock(int id, int stock) {
//        String sql = "UPDATE medicines SET stock = ? WHERE id = ?";
//        try (Connection con = DBConnection.getConnection();
//             PreparedStatement ps = con.prepareStatement(sql)) {
//            ps.setInt(1, stock);
//            ps.setInt(2, id);
//            return ps.executeUpdate() > 0;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return false;
//        }
//    }
//
//
//    // Helper: get medicines expiring within 'days' days
//    public List<Medicine> getExpiringWithinDays(int days) {
//        List<Medicine> list = new ArrayList<>();
//        String sql = "SELECT * FROM medicines WHERE expiry_date IS NOT NULL AND expiry_date <= DATE_ADD(CURDATE(), INTERVAL ? DAY)";
//        try (Connection con = DBConnection.getConnection();
//             PreparedStatement ps = con.prepareStatement(sql)) {
//
//            ps.setInt(1, days);
//            try (ResultSet rs = ps.executeQuery()) {
//                while (rs.next()) {
//                    Date d = rs.getDate("expiry_date");
//                    LocalDate expiry = (d != null) ? d.toLocalDate() : null;
//                    Medicine m = new Medicine(
//                            rs.getInt("id"),
//                            rs.getString("name"),
//                            rs.getString("description"),
//                            rs.getDouble("price"),
//                            rs.getInt("stock"),
//                            expiry
//                    );
//                    list.add(m);
//                }
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return list;
//    }
//
//    // Helper: get low stock medicines (threshold)
//    public List<Medicine> getLowStock(int threshold) {
//        List<Medicine> list = new ArrayList<>();
//        String sql = "SELECT * FROM medicines WHERE stock <= ?";
//        try (Connection con = DBConnection.getConnection();
//             PreparedStatement ps = con.prepareStatement(sql)) {
//
//            ps.setInt(1, threshold);
//            try (ResultSet rs = ps.executeQuery()) {
//                while (rs.next()) {
//                    Date d = rs.getDate("expiry_date");
//                    LocalDate expiry = (d != null) ? d.toLocalDate() : null;
//                    Medicine m = new Medicine(
//                            rs.getInt("id"),
//                            rs.getString("name"),
//                            rs.getString("description"),
//                            rs.getDouble("price"),
//                            rs.getInt("stock"),
//                            expiry
//                    );
//                    list.add(m);
//                }
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return list;
//    }
//}



package dao;

import model.Medicine;
import util.DatabaseConnection;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MedicineDAO {

    // ADD Medicine
    public boolean addMedicine(Medicine m) {
        String sql = "INSERT INTO medicines (name, description, price, stock, category, expiry_date, image_path) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, m.getName());
            ps.setString(2, m.getDescription());
            ps.setDouble(3, m.getPrice());
            ps.setInt(4, m.getStock());
            ps.setString(5, m.getCategory());

            if (m.getExpiryDate() != null) {
                ps.setDate(6, Date.valueOf(m.getExpiryDate()));
            } else {
                ps.setNull(6, Types.DATE);
            }

            if (m.getImagePath() != null) {
                ps.setString(7, m.getImagePath());
            } else {
                ps.setNull(7, Types.VARCHAR);
            }

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // GET ALL Medicines
    public List<Medicine> getAllMedicines() {
        List<Medicine> list = new ArrayList<>();
        String sql = "SELECT * FROM medicines ORDER BY name ASC";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(extractMedicine(rs));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    // GET Medicine by ID
    public Medicine getMedicineById(int id) {
        String sql = "SELECT * FROM medicines WHERE id=?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return extractMedicine(rs);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    // UPDATE Medicine
    public boolean updateMedicine(Medicine m) {
        String sql = "UPDATE medicines SET name=?, description=?, price=?, stock=?, category=?, expiry_date=?, image_path=? WHERE id=?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, m.getName());
            ps.setString(2, m.getDescription());
            ps.setDouble(3, m.getPrice());
            ps.setInt(4, m.getStock());
            ps.setString(5, m.getCategory());

            if (m.getExpiryDate() != null) {
                ps.setDate(6, Date.valueOf(m.getExpiryDate()));
            } else {
                ps.setNull(6, Types.DATE);
            }

            if (m.getImagePath() != null) {
                ps.setString(7, m.getImagePath());
            } else {
                ps.setNull(7, Types.VARCHAR);
            }

            ps.setInt(8, m.getId());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // UPDATE Stock Only
    public boolean updateStock(int id, int stock) {
        String sql = "UPDATE medicines SET stock = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, stock);
            ps.setInt(2, id);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // DELETE Medicine
    public boolean deleteMedicine(int id) throws SQLIntegrityConstraintViolationException {
        String sql = "DELETE FROM medicines WHERE id=?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;

        } catch (SQLIntegrityConstraintViolationException fkEx) {
            throw fkEx; // send to UI to handle
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }




    // SEARCH by name (default)
    public List<Medicine> searchMedicines(String keyword) {
        List<Medicine> list = new ArrayList<>();
        String sql = "SELECT * FROM medicines WHERE name LIKE ? OR category LIKE ? ORDER BY name ASC";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            String searchKey = "%" + keyword + "%";
            ps.setString(1, searchKey);
            ps.setString(2, searchKey);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(extractMedicine(rs));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    // SEARCH by Name + Category exact category
    public List<Medicine> searchMedicinesByCategory(String keyword, String category) {
        List<Medicine> list = new ArrayList<>();
        String sql = "SELECT * FROM medicines WHERE name LIKE ? AND category = ? ORDER BY name ASC";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, "%" + keyword + "%");
            ps.setString(2, category);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(extractMedicine(rs));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    // LOW STOCK MEDICINES
    public List<Medicine> getLowStock(int threshold) {
        List<Medicine> list = new ArrayList<>();
        String sql = "SELECT * FROM medicines WHERE stock <= ? ORDER BY stock ASC";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, threshold);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(extractMedicine(rs));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    // EXPIRING SOON
    public List<Medicine> getExpiringWithinDays(int days) {
        List<Medicine> list = new ArrayList<>();
        String sql = "SELECT * FROM medicines WHERE expiry_date IS NOT NULL AND expiry_date <= CURRENT_DATE + INTERVAL ? DAY";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, days);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(extractMedicine(rs));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    // Helper method: convert SQL row to Medicine object (handles nulls)
    private Medicine extractMedicine(ResultSet rs) throws SQLException {
        LocalDate expiry = null;
        Date d = rs.getDate("expiry_date");
        if (d != null) expiry = d.toLocalDate();

        String img = null;
        try {
            img = rs.getString("image_path");
        } catch (SQLException ignore) {}

        return new Medicine(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getString("description"),
                rs.getDouble("price"),
                rs.getInt("stock"),
                rs.getString("category"),
                expiry,
                img
        );
    }
}
