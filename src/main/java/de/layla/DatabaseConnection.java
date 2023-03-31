package de.layla;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DatabaseConnection {

    private final String url = "jdbc:mysql://localhost/versand?";
    
    public Connection connect(String user, String password) {
        try {
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            return null;
        }
    }

    public Connection connectAsRoot() {
        try {
            String user = "root";
            String password = "";
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            return null;
        }
    }
}
