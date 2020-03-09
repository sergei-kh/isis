package ru.bstu.it51.hlopov.helpers.db;

import ru.bstu.it51.hlopov.Models.Country;
import ru.bstu.it51.hlopov.helpers.db.ResultDb;
import ru.bstu.it51.hlopov.helpers.xml.Convert;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class ParserDb {
    protected Connection con;

    public ParserDb(Connection conNew) {
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

    public void routingCommands(byte cmd, int param) {
        switch (cmd) {
            case 0:
                printAllItems();
                break;
            case 1:
                addItem();
                break;
            case 2:
                editItem(param);
                break;
        }
    }
}
