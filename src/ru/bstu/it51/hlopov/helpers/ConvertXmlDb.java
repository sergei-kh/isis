package ru.bstu.it51.hlopov.helpers;

import ru.bstu.it51.hlopov.Models.Country;
import ru.bstu.it51.hlopov.helpers.db.Connect;
import ru.bstu.it51.hlopov.helpers.xml.Sax;
import ru.bstu.it51.hlopov.Models.Country;
import ru.bstu.it51.hlopov.helpers.xml.Convert;

import java.io.File;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class ConvertXmlDb {

    public static void xmlToDB(Connect connect, File xml) {
        ArrayList<Country> countries = Sax.getData(xml);
        try {
            Statement statement = connect.getConnect().createStatement();
            statement.executeUpdate("DELETE FROM countries");
            statement.close();
            PreparedStatement objInsert = connect.getConnect().prepareStatement("insert into countries(continent,name,area,population,minerals,attr_id) values(?,?,?,?,?,?)");
            for(Country country : countries) {
                objInsert.setString(1,country.getContinent());
                objInsert.setString(2,country.getName());
                objInsert.setInt(3,country.getArea());
                objInsert.setInt(4,country.getPopulation());
                objInsert.setString(5,country.getMinerals());
                objInsert.setInt(6,country.getId());
                objInsert.addBatch();
            }
            objInsert.executeBatch();
            objInsert.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void DbToXml(Connect connect, File xml) {
        try {
            Statement statement = connect.getConnect().createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM countries");
            ArrayList<Country> countries = new ArrayList<Country>();
            while (resultSet.next()) {
                Country country = new Country();
                country.setId(resultSet.getInt("attr_id"));
                country.setContinent(resultSet.getString("continent"));
                country.setName(resultSet.getString("name"));
                country.setArea(resultSet.getInt("area"));
                country.setPopulation(resultSet.getInt("population"));
                country.setMinerals(resultSet.getString("minerals"));
                countries.add(country);
            }
            statement.close();
            Convert.writeToFile(Convert.modelsToDocument(countries),xml);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
