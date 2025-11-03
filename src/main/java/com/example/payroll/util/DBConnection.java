package com.example.payroll.util;
import java.sql.*;

public class DBConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/emp_pay";
    private static final String USER = "root";
    private static final String PASSWORD = "Jyothi@2004";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}