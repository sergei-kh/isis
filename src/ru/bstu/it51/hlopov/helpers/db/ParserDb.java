package ru.bstu.it51.hlopov.helpers.db;

import java.io.FileInputStream;
import java.sql.*;
import java.util.Properties;

public class ParserDb {
    private static Connection con;
    private static Statement stmt;
    private static Properties prop;

    public ParserDb(String config) {
        try {
            prop = new Properties();
            prop.load(new FileInputStream(config));
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/lr5?autoReconnect=true&useSSL=false",
                    prop.getProperty("USER_DATABASE"), prop.getProperty("PASSWORD_DATABASE"));
            stmt = con.createStatement();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void printAllDocument() {
        
    }

    public void routingCommands(byte cmd, byte min, byte max, int param) {
        if(cmd <= max && cmd >= min) {
            switch (cmd) {
                case 0:
                    printAllDocument();
                    break;
            }
        } else {
            System.out.println("Команда не определена");
        }
    }

    public void close() {
        try {
            con.close();
            stmt.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
