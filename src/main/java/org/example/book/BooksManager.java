package org.example.book;

import org.example.DatabaseManager;

import java.sql.*;

public class BooksManager {

    public static void selectAllBook() {
        String sql = "SELECT * FROM books";

        try(Connection conn = DatabaseManager.connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println("ID | Title | Author | Genre | Publication Year | ISBN | Available");

            while(rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String author = rs.getString("author");
                String genre = rs.getString("genre");
                int publicationYear = rs.getInt("publication_year");
                String isbn = rs.getString("isbn");
                boolean available = rs.getBoolean("available");
                String availableString = "";
                if(available) {
                    availableString = "YES";
                } else {
                    availableString = "NO";
                }

                System.out.printf("%d | %s | %s | %s | %s | %s | %s%n",
                        id, title, author, genre, publicationYear, isbn, availableString);
            }

        } catch (SQLException e) {
            System.out.println("Error displaying books: " + e.getMessage());
        }

    }

    public static void addBook(String title, String author, String genre,
                               int publicationYear, String isbn, boolean available) {
        String sql = "INSERT INTO books (title, author, genre, publication_year, isbn, available) VALUES (?, ?, ?, ?, ?, ?)";

        try(Connection conn = DatabaseManager.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, title);
            pstmt.setString(2, author);
            pstmt.setString(3, genre);
            pstmt.setInt(4, publicationYear);
            pstmt.setString(5, isbn);
            pstmt.setBoolean(6, available);

            int affectedRows = pstmt.executeUpdate();

            if(affectedRows > 0) {
                System.out.println("Book add successfully!");
            }

        } catch (SQLException e) {
            System.out.println("Error adding book: " + e.getMessage());
        }
    }

    public static void deleteBook(int id) {
        String sql = "DELETE FROM books WHERE id = ?";

        try(Connection conn = DatabaseManager.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            int affectedRows = pstmt.executeUpdate();

            if(affectedRows > 0) {
                System.out.println("Book deleted successfully!");
            } else {
                System.out.println("Book not found!");
            }

        }catch (SQLException e) {
            System.out.println("Error deleting books: " + e.getMessage());
        }
    }

    public static void bookFindById(int id) {
        String sql = "SELECT * FROM books WHERE id = ?";

        try(Connection conn = DatabaseManager.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {

            System.out.println("ID | Title | Author | Genre | Publication Year | ISBN | Available");

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if(rs.next()) {
                int rsId = rs.getInt("id");
                String title = rs.getString("title");
                String author = rs.getString("author");
                String genre = rs.getString("genre");
                int publicationYear = rs.getInt("publication_year");
                String isbn = rs.getString("isbn");
                boolean available = rs.getBoolean("available");
                String availableString = "";
                if(available) {
                    availableString = "YES";
                } else {
                    availableString = "NO";
                }

                System.out.printf("%d | %s | %s | %s | %s | %s | %s%n",
                        rsId, title, author, genre, publicationYear, isbn, availableString);
            } else {
                System.out.println("Book not found!");
            }

        } catch (SQLException e) {
            System.out.println("Error finding books: " + e.getMessage());
        }
    }

    public static void bookFindByTitle(String title) {
        String sql = "SELECT * FROM books WHERE title = ?";

        try(Connection conn = DatabaseManager.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {

            System.out.println("ID | Title | Author | Genre | Publication Year | ISBN | Available");

            pstmt.setString(1, title);
            ResultSet rs = pstmt.executeQuery();

            boolean found = false;
            while(rs.next()) {
                found = true;
                int id = rs.getInt("id");
                String rsTitle = rs.getString("title");
                String author = rs.getString("author");
                String genre = rs.getString("genre");
                int publicationYear = rs.getInt("publication_year");
                String isbn = rs.getString("isbn");
                boolean available = rs.getBoolean("available");
                String availableString = "";
                if(available) {
                    availableString = "YES";
                } else {
                    availableString = "NO";
                }

                System.out.printf("%d | %s | %s | %s | %s | %s | %s%n",
                        id, rsTitle, author, genre, publicationYear, isbn, availableString);
            }

            if (!found) {
                System.out.println("Book not found!");
            }


        } catch (SQLException e) {
            System.out.println("Error finding books: " + e.getMessage());
        }

    }

    public static void bookFindByAuthor(String author) {
        String sql = "SELECT * FROM books WHERE author = ?";

        try(Connection conn = DatabaseManager.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {

            System.out.println("ID | Title | Author | Genre | Publication Year | ISBN | Available");

            pstmt.setString(1, author);
            ResultSet rs = pstmt.executeQuery();

            boolean found = false;
            while(rs.next()) {
                found = true;
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String rsAuthor = rs.getString("author");
                String genre = rs.getString("genre");
                int publicationYear = rs.getInt("publication_year");
                String isbn = rs.getString("isbn");
                boolean available = rs.getBoolean("available");
                String availableString = "";
                if(available) {
                    availableString = "YES";
                } else {
                    availableString = "NO";
                }

                System.out.printf("%d | %s | %s | %s | %s | %s | %s%n",
                        id, title, rsAuthor, genre, publicationYear, isbn, availableString);
            }

            if (!found) {
                System.out.println("Book not found!");
            }

        } catch (SQLException e) {
            System.out.println("Error finding books: " + e.getMessage());
        }
    }

    public static void bookFindByGenre(String genre) {
        String sql = "SELECT * FROM books WHERE genre = ?";

        try(Connection conn = DatabaseManager.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {

            System.out.println("ID | Title | Author | Genre | Publication Year | ISBN | Available");

            pstmt.setString(1, genre);
            ResultSet rs = pstmt.executeQuery();

            boolean found = false;
            while(rs.next()) {
                found = true;
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String author = rs.getString("author");
                String rsGenre = rs.getString("genre");
                int publicationYear = rs.getInt("publication_year");
                String isbn = rs.getString("isbn");
                boolean available = rs.getBoolean("available");
                String availableString = "";
                if(available) {
                    availableString = "YES";
                } else {
                    availableString = "NO";
                }

                System.out.printf("%d | %s | %s | %s | %s | %s | %s%n",
                        id, title, author, rsGenre, publicationYear, isbn, availableString);
            }

            if (!found) {
                System.out.println("Book not found!");
            }

        } catch (SQLException e) {
            System.out.println("Error finding books: " + e.getMessage());
        }
    }

    public static void bookFindByPublicationYear(int publicationYear) {
        String sql = "SELECT * FROM books WHERE publication_year = ?";

        try(Connection conn = DatabaseManager.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {

            System.out.println("ID | Title | Author | Genre | Publication Year | ISBN | Available");

            pstmt.setInt(1, publicationYear);
            ResultSet rs = pstmt.executeQuery();

            boolean found = false;
            while(rs.next()) {
                found = true;
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String author = rs.getString("author");
                String genre = rs.getString("genre");
                int rsPublicationYear = rs.getInt("publication_year");
                String isbn = rs.getString("isbn");
                boolean available = rs.getBoolean("available");
                String availableString = "";
                if(available) {
                    availableString = "YES";
                } else {
                    availableString = "NO";
                }

                System.out.printf("%d | %s | %s | %s | %s | %s | %s%n",
                        id, title, author, genre, rsPublicationYear, isbn, availableString);
            }
            if (!found) {
                System.out.println("Book not found!");
            }

        } catch (SQLException e) {
            System.out.println("Error finding books: " + e.getMessage());
        }
    }

    public static void bookFindByIsbn(String isbn) {
        String sql = "SELECT * FROM books WHERE isbn = ?";

        try(Connection conn = DatabaseManager.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {

            System.out.println("ID | Title | Author | Genre | Publication Year | ISBN | Available");

            pstmt.setString(1, isbn);
            ResultSet rs = pstmt.executeQuery();

            if(rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String author = rs.getString("author");
                String genre = rs.getString("genre");
                int publicationYear = rs.getInt("publication_year");
                String rsIsbn = rs.getString("isbn");
                boolean available = rs.getBoolean("available");
                String availableString = "";
                if(available) {
                    availableString = "YES";
                } else {
                    availableString = "NO";
                }

                System.out.printf("%d | %s | %s | %s | %s | %s | %s%n",
                        id, title, author, genre, publicationYear, rsIsbn, availableString);
            } else {
                System.out.println("Book not found!");
            }

        } catch (SQLException e) {
            System.out.println("Error finding books: " + e.getMessage());
        }
    }

    public static void bookFindByAvailable(boolean available) {
        String sql = "SELECT * FROM books WHERE available = ?";

        try(Connection conn = DatabaseManager.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {

            System.out.println("ID | Title | Author | Genre | Publication Year | ISBN | Available");

            pstmt.setBoolean(1, available);
            ResultSet rs = pstmt.executeQuery();

            boolean found = false;
            while(rs.next()) {
                found = true;
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String author = rs.getString("author");
                String genre = rs.getString("genre");
                int publicationYear = rs.getInt("publication_year");
                String isbn = rs.getString("isbn");
                boolean rsAvailable = rs.getBoolean("available");
                String availableString = "";
                if(rsAvailable) {
                    availableString = "YES";
                } else {
                    availableString = "NO";
                }


                System.out.printf("%d | %s | %s | %s | %s | %s | %s%n",
                        id, title, author, genre, publicationYear, isbn, availableString);
            }
            if (!found) {
                System.out.println("Book not found!");
            }

        } catch (SQLException e) {
            System.out.println("Error finding books: " + e.getMessage());
        }
    }

}
