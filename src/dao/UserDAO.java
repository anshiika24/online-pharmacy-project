// package dao;

// import config.DBConnection;
// import model.User;

// import java.sql.Connection;
// import java.sql.PreparedStatement;
// import java.sql.ResultSet;

// public class UserDAO {

//     // LOGIN FUNCTION
//     public User login(String email, String passwordHash) {
//         User user = null;

//         try {
//             Connection con = DBConnection.getConnection();

//             String query = "SELECT * FROM users WHERE email = ? AND password_hash = ?";
//             PreparedStatement ps = con.prepareStatement(query);
//             ps.setString(1, email);
//             ps.setString(2, passwordHash);

//             ResultSet rs = ps.executeQuery();

//             if (rs.next()) {
//                 user = new User(
//                         rs.getInt("id"),
//                         rs.getString("name"),
//                         rs.getString("email"),
//                         rs.getString("password_hash"),
//                         rs.getString("role")
//                 );
//             }

//         } catch (Exception e) {
//             e.printStackTrace();
//         }

//         return user;
//     }
// }



// package dao;

// import config.DBConnection;
// import model.User;

// import java.sql.Connection;
// import java.sql.PreparedStatement;
// import java.sql.ResultSet;

// public class UserDAO {

//     // LOGIN FUNCTION
//     public User login(String email, String password) {
//         User user = null;

//         try {
//             Connection con = DBConnection.getConnection();

//             String query = "SELECT * FROM users WHERE email = ? AND password = ?";
//             PreparedStatement ps = con.prepareStatement(query);
//             ps.setString(1, email);
//             ps.setString(2, password);

//             ResultSet rs = ps.executeQuery();

//             if (rs.next()) {
//                 user = new User(
//                         rs.getInt("id"),
//                         rs.getString("name"),
//                         rs.getString("email"),
//                         rs.getString("password"),
//                         rs.getString("role")
//                 );
//             }

//         } catch (Exception e) {
//             e.printStackTrace();
//         }

//         return user;
//     }
// }





package dao;

import config.DBConnection;
import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAO {

    // LOGIN FUNCTION
    public User login(String email, String password) {
        User user = null;

        try {
            Connection con = DBConnection.getConnection();

            String query = "SELECT * FROM users WHERE email = ? AND password = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, email);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                user = new User(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("password"),  // FIXED
                        rs.getString("role")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return user;
    }
    public User getUserById(int id) {
        User user = null;
        try {
            Connection con = DBConnection.getConnection();
            String q = "SELECT * FROM users WHERE id = ?";
            PreparedStatement ps = con.prepareStatement(q);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                user = new User(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("role")
                );
            }
        } catch (Exception e) { e.printStackTrace(); }
        return user;
    }

}
