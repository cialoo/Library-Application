package org.example.loan;

import org.example.DatabaseManager;

import java.sql.*;

public class LoanManager {

    public static void selectAllLoan() {
        String sql = "SELECT * FROM loans";

        try(Connection conn = DatabaseManager.connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println("ID | Book ID | User ID | Loan Date | Return Date");

            while(rs.next()) {
                int id = rs.getInt("id");
                int idBook = rs.getInt("book_id");
                int idUser = rs.getInt("user_id");
                String loanDate = rs.getString("loan_date");
                String returnDate = rs.getString("return_date");

                System.out.printf("%d | %s | %s | %s | %s%n",
                        id, idBook, idUser, loanDate, returnDate);
            }

        } catch (SQLException e) {
            System.out.println("Error displaying loans: " + e.getMessage());
        }

    }

    public static void selectAllLoanForUser(String userN) {
        String sql = "SELECT u.id as userId, u.name as userName, l.book_id as bookId, b.title as bookTitle, l.loan_date as loanDate, l.return_date as returnDate FROM users u INNER JOIN loans l ON l.user_id = u.id INNER JOIN books b ON b.id = l.book_id WHERE u.name = ?";

        try(Connection conn = DatabaseManager.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {

            System.out.println("User ID | User Name | Book ID | Book Title | Loan Date | Return Date");

            pstmt.setString(1, userN);
            ResultSet rs = pstmt.executeQuery();

            boolean found = false;
            while(rs.next()) {
                found = true;
                int userId = rs.getInt("userId");
                String userName = rs.getString("userName");
                int bookId = rs.getInt("bookId");
                String bookTitle = rs.getString("bookTitle");
                String loanDate = rs.getString("loanDate");
                String returnDate = rs.getString("returnDate");

                System.out.printf("%d | %s | %s | %s | %s |%s%n",
                        userId, userName, bookId, bookTitle, loanDate, returnDate);
            }

            if (!found) {
                System.out.println("Loans for user not found!");
            }

        } catch (SQLException e) {
            System.out.println("Error displaying loans for user: " + e.getMessage());
        }
    }

    static boolean isBookAvailable(int bookId) {
        String sql = "SELECT available FROM books where id = ?";
        boolean isAvailable = false;
        try (Connection conn = DatabaseManager.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, bookId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                isAvailable = rs.getBoolean("available");
            }

        } catch (SQLException e) {
            System.out.println("Lending error: " + e.getMessage());
        }
        return isAvailable;
    }

    private static void setBookAvailable(Connection conn, int bookId)throws SQLException {
        String sql = "UPDATE books SET available = 1 WHERE id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, bookId);
            pstmt.executeUpdate();
        }
    }

    private static void setBookUnavailable(int bookId) {
        String sql = "UPDATE books SET available = 0 WHERE id = ?";

        try (Connection conn = DatabaseManager.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, bookId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error when changing book availability: " + e.getMessage());
        }
    }

    public static void rentBook(int bookId, int userId) {
        String sql = "INSERT INTO loans (book_id, user_id, loan_date, return_date) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseManager.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, bookId);
            pstmt.setInt(2, userId);

            String today = java.time.LocalDate.now().toString();
            pstmt.setString(3, today);

            pstmt.setNull(4, java.sql.Types.VARCHAR);

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                setBookUnavailable(bookId);
                System.out.println("The book was lent!");
            }


        } catch (SQLException e) {
            System.out.println("Lending error: " + e.getMessage());
        }
    }


    public static void returnBook(int loanId) {
        String selectSql = "SELECT book_id FROM loans WHERE id = ? AND return_date IS NULL";

        String updateLoanSql = "UPDATE loans SET return_date = ? WHERE id = ?";

        try (Connection conn = DatabaseManager.connect();
             PreparedStatement selectStmt = conn.prepareStatement(selectSql);
             PreparedStatement updateStmt = conn.prepareStatement(updateLoanSql)) {

            selectStmt.setInt(1, loanId);
            ResultSet rs = selectStmt.executeQuery();

            if (rs.next()) {
                int bookId = rs.getInt("book_id");
                String today = java.time.LocalDate.now().toString();

                updateStmt.setString(1, today);
                updateStmt.setInt(2, loanId);
                updateStmt.executeUpdate();

                setBookAvailable(conn, bookId);

                System.out.println("The book was returned!");
            } else {
                System.out.println("No active loans found with ID = " + loanId);
            }

        } catch (SQLException e) {
            System.out.println("Error when returning a book: " + e.getMessage());
        }
    }

}
