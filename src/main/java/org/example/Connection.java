package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

class DatabaseConnection {
    private String URL = "jdbc:postgresql://localhost:5432/StudentsManagement";
    private String USERNAME = "postgres";
    private String PASSWORD = "MichelangE12!@";

    public Connection connect() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }
}