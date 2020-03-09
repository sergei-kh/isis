package ru.bstu.it51.hlopov.helpers.db;

import ru.bstu.it51.hlopov.Models.Country;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ResultDb {

    public static int getMaxId(ResultSet resultSet) {
        int id = 0;
        try {
            while (resultSet.next()) {
                if(resultSet.getRow() == 1) {
                    id = resultSet.getInt("attr_id");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return id;
    }

    public static void printAll(ResultSet resultSet) throws SQLException {
        while (resultSet.next()) {
            System.out.printf("id: %d\n",resultSet.getInt("attr_id"));
            System.out.printf("Континент: %s\n",resultSet.getString("continent"));
            System.out.printf("Название: %s\n",resultSet.getString("name"));
            System.out.printf("Площадь: %d\n",resultSet.getInt("area"));
            System.out.printf("Население: %d\n",resultSet.getInt("population"));
            System.out.printf("Полезные ископаемые: %s\n\n",resultSet.getString("minerals"));
        }
    }
}
