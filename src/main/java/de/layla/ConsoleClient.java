package de.layla;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class ConsoleClient {
    
    private final Scanner sc = new Scanner(System.in);
    private DatabaseConnection databaseConnection = new DatabaseConnection();

    public void login() {
        while (true) {
            System.out.print("Username: ");
            String username = sc.nextLine();
            System.out.print("Password: ");
            String password = sc.nextLine();

            if (isUser(username, password)) {
                databaseConnection.connect(username, password);
                System.out.println("Hello " + username + "!");
                break;
            } else {
                System.out.println("No user found. Would you like to create one with the given information? (y/n)");
                if (sc.nextLine().equals("y")) {
                    String[] newUser = createNewUser(username, password);
                    databaseConnection.connect(newUser[0], newUser[1]);
                    break;
                }
            }
            System.out.println("Login failed. Try again.");
        }
    }

    private boolean isUser(String username, String password) {
        return (databaseConnection.connect(username, password) != null);
    }

    private String[] createNewUser(String username, String password) {
        try {
            Connection con = databaseConnection.connectAsRoot();
            String createUser = "CREATE USER ?@localhost IDENTIFIED BY ?;";
            PreparedStatement createUserStatement = con.prepareStatement(createUser);
            createUserStatement.setString(1, username);
            createUserStatement.setString(2, password);
            createUserStatement.execute();
            String grantPermissions = "GRANT ALL PRIVILEGES ON versand.* To ?@'localhost' IDENTIFIED BY ?;";
            PreparedStatement grantPermissionsStatement = con.prepareStatement(grantPermissions);
            grantPermissionsStatement.setString(1, username);
            grantPermissionsStatement.setString(2, password);
            grantPermissionsStatement.execute();
            con.close();
            return new String[]{username, password};
        } catch (SQLException e) {
            return null;
        }
    }

}
