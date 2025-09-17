package ru.bstu.it212.hlopov.helpers.db;

import ru.bstu.it212.hlopov.Models.Country;
import ru.bstu.it212.hlopov.helpers.xml.Convert;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;
import java.util.Scanner;

public class ParserDb {
    protected Connection con;
    protected Properties prop;

    public ParserDb(Connection conNew, Properties properties) {
        prop = properties;
        con = conNew;
    }

    public void printAllItems() {
        try {
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM countries");
            ResultDb.printAll(resultSet);
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addItem() {
        Country country = Convert.fillObjectModel();
        try {
            PreparedStatement statement = con.prepareStatement("INSERT INTO countries(continent," +
                    "name,area,population,minerals,attr_id) VALUES(?,?,?,?,?,?)");
            ResultSet resultSet = statement.executeQuery("SELECT * FROM `countries` ORDER BY `countries`.`attr_id` DESC");
            statement.setString(1,country.getContinent());
            statement.setString(2,country.getName());
            statement.setInt(3,country.getArea());
            statement.setInt(4,country.getPopulation());
            statement.setString(5,country.getMinerals());
            statement.setInt(6,ResultDb.getMaxId(resultSet) + 1);
            statement.executeUpdate();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void editItem(int id) {
        try {
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM countries WHERE attr_id = "+id);
            System.out.println("Данные на текущий момент: ");
            ResultDb.printAll(resultSet);
            Country country = Convert.fillObjectModel();
            PreparedStatement preparedStatement = con.prepareStatement("UPDATE countries SET continent = ?, " +
                    "name = ?, area = ?, population = ?, minerals = ? WHERE attr_id = ?");
            preparedStatement.setString(1,country.getContinent());
            preparedStatement.setString(2,country.getName());
            preparedStatement.setInt(3,country.getArea());
            preparedStatement.setInt(4,country.getPopulation());
            preparedStatement.setString(5,country.getMinerals());
            preparedStatement.setInt(6,id);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            System.out.println("Редактирование выполнено успешно");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void removeItem(int id) {
        try {
            Statement statement = con.createStatement();
            int status = statement.executeUpdate("DELETE FROM countries WHERE attr_id = "+id);
            if(status != 0) {
                System.out.println(prop.getProperty("MESSAGE_SUCCESS_REMOVE"));
            } else {
                System.out.println(prop.getProperty("MESSAGE_FAIL_REMOVE"));
            }
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void findMaxOrMin(byte direction, String nameField) {
        try {
            Statement statement = con.createStatement();
            ResultSet resultSet;
            if(direction == 0) {
                resultSet = statement.executeQuery("SELECT * FROM countries WHERE "+nameField+" = (SELECT MAX("+nameField+") FROM countries)");
            } else {
                resultSet = statement.executeQuery("SELECT * FROM countries WHERE "+nameField+" = (SELECT MIN("+nameField+") FROM countries)");
            }
            ResultDb.printAll(resultSet);
            statement.close();
        } catch (Exception e) {
            System.out.println(prop.getProperty("MESSAGES_FAIL_QUERY_DB"));
        }
    }

    public void findToFields(String str, String fields) {
        try {
            String query = "SELECT * FROM countries WHERE ";
            Statement statement = con.createStatement();
            String[] names = fields.split(",");
            for(int i = 0; i < names.length; ++i) {
                if(i == 0) {
                    query += names[i]+" LIKE '%"+str+"%'";
                } else {
                    query += " AND "+names[i]+" LIKE '%"+str+"%'";
                }
            }
            ResultSet resultSet = statement.executeQuery(query);
            ResultDb.printAll(resultSet);
        } catch (Exception e) {
            System.out.println(prop.getProperty("MESSAGES_FAIL_QUERY_DB"));
        }
    }

    public void routingCommands(byte cmd, Scanner in) {
        switch (cmd) {
            case 0:
                printAllItems();
                break;
            case 1:
                addItem();
                break;
            case 2:
                System.out.print("Введите Id: ");
                editItem(in.nextInt());
                break;
            case 3:
                System.out.print("Введите Id: ");
                removeItem(in.nextInt());
                break;
            case 4:
                System.out.print("0 - Максимальное значение; 1 - Минимальное значение: ");
                byte direction = in.nextByte();
                System.out.print("Введите название поля: ");
                String nameField = in.next();
                findMaxOrMin(direction, nameField);
                break;
            case 5:
                System.out.print("Введите поисковый запрос: ");
                String query = in.next();
                System.out.print("Введите поля по которым будет происходит поиск через запятую: ");
                String fields = in.next();
                findToFields(query, fields);
                break;
        }
    }
}
