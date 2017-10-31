package model.service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBService {
    private static final String DATABASE_URL = "jdbc:postgresql://localhost:5432/my_library";
    private static final String USERNAME = "madpa";
    private static final String PASSWORD = "edhunter";

    private Connection connection = null;
    private Statement statement = null;
    private ResultSet resultSet = null;

    public DBService() throws SQLException {
        connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
    }

    private void disconnect() throws SQLException {
        resultSet.close();
        statement.close();
        connection.close();
    }

    public List<String> readFromDB(final String statement) throws SQLException {
        List<String> list = new ArrayList<>();
        this.statement = connection.createStatement();
        resultSet = this.statement.executeQuery(statement);

        if (resultSet.next()) {
            ResultSetMetaData meta = resultSet.getMetaData();
            for (int i = 1; i <= meta.getColumnCount(); i++) {
                list.add(meta.getColumnName(i));
            }
            do {
                for (int i = 1; i <= meta.getColumnCount(); i++) {
                    list.add(resultSet.getObject(i).toString());
                }
            } while (resultSet.next());
        }
        disconnect();
        return list;
    }

    public void executeStatement(final String statement) throws SQLException {
        this.statement = connection.createStatement();
        this.statement.executeUpdate(statement);
    }
}
