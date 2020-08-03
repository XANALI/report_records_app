package kz.xan.report_records_app.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseHandler extends DatabaseConfig {
    private Connection connection;

    public DatabaseHandler() {
        String dbURL = "jdbc:postgresql://" + dbHost + ":" + dbPort + "/" + dbDatabase;

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            connection = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }
}
