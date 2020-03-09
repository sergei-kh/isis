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
            while (resultSet.next()) {
                System.out.printf("id: %d\n",resultSet.getInt("attr_id"));
                System.out.printf("Континент: %s\n",resultSet.getString("continent"));
                System.out.printf("Название: %s\n",resultSet.getString("name"));
                System.out.printf("Площадь: %d\n",resultSet.getInt("area"));
                System.out.printf("Население: %d\n",resultSet.getInt("population"));
                System.out.printf("Полезные ископаемые: %s\n\n",resultSet.getString("minerals"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addItem() {
        Country country = Convert.fillObjectModel();
        try {
            PreparedStatement statement = con.prepareStatement("insert into countries(continent," +
                    "name,area,population,minerals,attr_id) values(?,?,?,?,?,?)");
            ResultSet resultSet = statement.executeQuery("SELECT * FROM `countries` ORDER BY `countries`.`attr_id` DESC");
            statement.setString(1,country.getContinent());
            statement.setString(2,country.getName());
            statement.setInt(3,country.getArea());
            statement.setInt(4,country.getPopulation());
            statement.setString(5,country.getMinerals());
            statement.setInt(6,ResultDb.getMaxId(resultSet) + 1);
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void editItem(int id) {

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
