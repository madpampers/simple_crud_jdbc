package model;

import model.service.DBService;

import java.sql.SQLException;
import java.util.List;

public class Model {
    private static DBService dbService;

    public void connect() throws SQLException {
        dbService = new DBService();
    }

    public void addEntry(String bookName, String bookAuthor, int pages) throws SQLException {
        connect();
        dbService.executeStatement("INSERT INTO books (name, author, pages) VALUES ('"
                + bookName + "' , '"
                + bookAuthor + "' , '"
                + pages + "')");
    }

    public void updateEntry(int bookID, int bookRating, int pages) throws SQLException {
        connect();
        dbService.executeStatement("UPDATE books SET rating = "
                + bookRating + " , pages = "
                + pages +
                " WHERE id = " + bookID);
    }

    public void deleteEntry(int bookID) throws SQLException {
        connect();
        dbService.executeStatement("DELETE FROM books WHERE id = " + bookID);

    }

    public List<String> showEntry(String bookName) throws SQLException {
        return dbService.readFromDB("SELECT '" + bookName + "' FROM books");
    }

    public List<String> showAllEntries() throws SQLException {
        return dbService.readFromDB("SELECT * FROM books");
    }
}
