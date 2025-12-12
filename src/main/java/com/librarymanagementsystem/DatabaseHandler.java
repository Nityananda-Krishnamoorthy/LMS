package com.librarymanagementsystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DatabaseHandler {

    private final String url = System.getenv("mysqlUrl");
    private final String user = System.getenv("mysqlUser");
    private final String password = System.getenv("mysqlPassword");

public ObservableList<Books> getBooksAsObservableList() throws SQLException {
    ObservableList<Books> booksList = FXCollections.observableArrayList();
    String query =
    "SELECT ROW_NUMBER() OVER (ORDER BY ISBN) AS SNo, " +
    "`ISBN`, Title, Author, `Author Gender`, `Publication Year`, Genre, " +
   "`Book Price`, `Book Availability`, `Book Cover`, `Condition` " +
    "FROM LibraryMangementSystem.`Books table`";

    try (Connection conn = getDatabaseConnection(url, user, password);
         Statement stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery(query)) {

        while (rs.next()) {
            Books book = new Books(
                rs.getString("ISBN"),
                rs.getString("Title"),
                rs.getString("Author"),
                rs.getString("Author Gender"),
                rs.getInt("Publication Year"),
                rs.getString("Genre"),
                rs.getDouble("Book Price"),
                rs.getBoolean("Book Availability"),
                rs.getString("Book Cover"),
                rs.getString("Condition")
            );

            booksList.add(book);
        }
    } catch (SQLException e) {
        e.printStackTrace(System.err);
    }

    return booksList;
}

// public void readBooks() {
    
    
// }
public boolean isbnExists(String isbn) {
    String query = "SELECT 1 FROM `Books table` WHERE ISBN = ? LIMIT 1";

    try (Connection conn = getDatabaseConnection(url, user, password);
         PreparedStatement stmt = conn.prepareStatement(query)) {

        stmt.setString(1, isbn);
        ResultSet rs = stmt.executeQuery();

        return rs.next();  // true if record exists

    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}

public boolean insertBook(Books b) {
    String sql = "INSERT INTO `Books table` " +
            "(`ISBN`, `Title`, `Author`, `Author Gender`, `Publication Year`, " +
            "`Genre`, `Book Price`, `Book Availability`, `Book Cover`, `Condition`) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    try (Connection conn = getDatabaseConnection(url, user, password);
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setString(1, b.getIsbn());
        stmt.setString(2, b.getTitle());
        stmt.setString(3, b.getAuthor());
        stmt.setString(4, b.getAuthorGender());
        stmt.setInt(5, b.getPublicationYear());
        stmt.setString(6, b.getGenre());
        stmt.setDouble(7, b.getBookPrice());
        stmt.setBoolean(8, b.isBookAvailability());
        stmt.setString(9, b.getBookCover());
        stmt.setString(10, b.getCondition());

        return stmt.executeUpdate() > 0;

    } catch (Exception e) {
        e.printStackTrace();
        return false;
    }
}


public boolean updateBooks(Books b) {
    String sql = "UPDATE `Books table` SET " +
            "`Title` = ?, `Author` = ?, `Author Gender` = ?, `Publication Year` = ?, " +
            "`Genre` = ?, `Book Price` = ?, `Book Availability` = ?, " +
            "`Book Cover` = ?, `Condition` = ? " +
            "WHERE `ISBN` = ?";
    try (Connection conn = getDatabaseConnection(url, user, password);
            PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setString(1, b.getTitle());
        stmt.setString(2, b.getAuthor());
        stmt.setString(3, b.getAuthorGender());
        stmt.setInt(4, b.getPublicationYear());
        stmt.setString(5, b.getGenre());
        stmt.setDouble(6, b.getBookPrice());
        stmt.setBoolean(7, b.isBookAvailability());
        stmt.setString(8, b.getBookCover());
        stmt.setString(9, b.getCondition());
        stmt.setString(10, b.getIsbn());
        return stmt.executeUpdate() > 0;
    } catch (Exception e) {
        e.printStackTrace();
        return false;
    }


}
public boolean  deleteBooks(Books b) {
    String sql = "DELETE FROM `Books table` WHERE ISBN = ?";
    try (Connection conn = getDatabaseConnection(url, user, password);
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setString(1, b.getIsbn());

        return stmt.executeUpdate() > 0;

    } catch (Exception e) {
        e.printStackTrace();
        return false;
    }
}

    
    private Connection getDatabaseConnection(String url, String user, String password) throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
}

