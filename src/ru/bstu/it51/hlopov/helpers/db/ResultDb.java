package ru.bstu.it51.hlopov.helpers.db;

import ru.bstu.it51.hlopov.Models.Country;

import java.sql.ResultSet;

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
}
