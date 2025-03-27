package org.example.user;

import org.example.DatabaseManager;

import java.sql.*;

public class UserManager {

    public static void selectAllUser() {
        String sql = "SELECT * FROM users";

        try(Connection conn = DatabaseManager.connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println("ID | Name | Email | Phone");

            while(rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String phone = rs.getString("phone");

                System.out.printf("%d | %s | %s | %s%n", id, name, email, phone);
            }

        } catch (SQLException e) {
            System.out.println("Error displaying users: " + e.getMessage());
        }
    }

    public static void addUser(String name, String email, String phone) {
        String sql = "INSERT INTO users(name, email, phone) VALUES(?, ?, ?)";

        try(Connection conn = DatabaseManager.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setString(2, email);
            pstmt.setString(3, phone);

            int affectedRows = pstmt.executeUpdate();

            if(affectedRows > 0) {
                System.out.println("User add successfully!");
            }

        } catch(SQLException e) {
            System.out.println("Error adding user: " + e.getMessage());
        }
    }

    public static void deleteUser(int id) {
        String sql = "DELETE FROM users WHERE id = ?";

        try(Connection conn = DatabaseManager.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            int affectedRows = pstmt.executeUpdate();

            if(affectedRows > 0) {
                System.out.println("User deleted successfully!");
            } else {
                System.out.println("User not found!");
            }

        } catch (SQLException e) {
            System.out.println("Error deleting user: " + e.getMessage());
        }

    }

    public static void userFindById(int id) {
        String sql = "SELECT * FROM users WHERE id = ?";

        try(Connection conn = DatabaseManager.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {

            System.out.println("ID | Name | Email | Phone");

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if(rs.next()) {
                int rsId = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String phone = rs.getString("phone");

                System.out.printf("%d | %s | %s | %s%n", rsId, name, email, phone);
            } else {
                System.out.println("User not found!");
            }

        } catch (SQLException e) {
            System.out.println("Error finding users: " + e.getMessage());
        }
    }

    public static void userFindByName(String name) {
        String sql = "SELECT * FROM users WHERE name = ?";

        try(Connection conn = DatabaseManager.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            System.out.println("ID | Name | Email | Phone");

            pstmt.setString(1, name);
            ResultSet rs = pstmt.executeQuery();

            boolean found = false;
            while(rs.next()) {
                found = true;
                int id = rs.getInt("id");
                String rsName = rs.getString("name");
                String email = rs.getString("email");
                String phone = rs.getString("phone");

                System.out.printf("%d | %s | %s | %s%n", id, rsName, email, phone);
            }
            if (!found) {
                System.out.println("User not found!");
            }

        } catch (SQLException e) {
            System.out.println("Error finding users: " + e.getMessage());
        }
    }

    public static void userFindByEmail(String email) {
        String sql = "SELECT * FROM users WHERE email = ?";

        try(Connection conn = DatabaseManager.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            System.out.println("ID | Name | Email | Phone");

            pstmt.setString(1, email);
            ResultSet rs = pstmt.executeQuery();

            if(rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String rsEmail = rs.getString("email");
                String phone = rs.getString("phone");

                System.out.printf("%d | %s | %s | %s%n", id, name, rsEmail, phone);
            } else {
                System.out.println("User not found!");
            }

        } catch (SQLException e) {
            System.out.println("Error finding users: " + e.getMessage());
        }
    }

    public static void userFindByPhone(String phone) {
        String sql = "SELECT * FROM users WHERE phone = ?";

        try(Connection conn = DatabaseManager.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            System.out.println("ID | Name | Email | Phone");

            pstmt.setString(1, phone);
            ResultSet rs = pstmt.executeQuery();

            if(rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String rsPhone = rs.getString("phone");

                System.out.printf("%d | %s | %s | %s%n", id, name, email, rsPhone);
            } else {
                System.out.println("User not found!");
            }

        } catch (SQLException e) {
            System.out.println("Error finding users: " + e.getMessage());
        }
    }

}
