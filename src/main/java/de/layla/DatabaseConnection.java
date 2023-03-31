package de.layla;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DatabaseConnection {
    
    public Connection connect(String user, String password) {
        try {
            String url = "jdbc:mysql://localhost/versand?";
            Connection connection = DriverManager.getConnection(url, user, password);
            return connection;
        } catch (SQLException e) {
            return null;
        }
    }

    public Connection connectAsRoot() {
        try {
            String user = "root";
            String password = "";
            String url = "jdbc:mysql://localhost/versand?";
            Connection connection = DriverManager.getConnection(url, user, password);
            return connection;
        } catch (SQLException e) {
            return null;
        }
    }
}
