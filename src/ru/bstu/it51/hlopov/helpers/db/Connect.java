package ru.bstu.it51.hlopov.helpers.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class Connect {
    private static Connection con;

    public Connect(Properties prop) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(prop.getProperty("URL_CONNECTION"),
                    prop.getProperty("USER_DATABASE"), prop.getProperty("PASSWORD_DATABASE"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            con.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public Connection getConnect() {
        return con;
    }
}
