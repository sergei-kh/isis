package ru.bstu.it51.hlopov.helpers;

import ru.bstu.it51.hlopov.Models.Country;
import ru.bstu.it51.hlopov.helpers.db.Connect;
import ru.bstu.it51.hlopov.helpers.xml.Sax;

import java.io.File;
import java.sql.PreparedStatement;
import java.util.ArrayList;

public class ConvertXmlDb {

    public static void xmlToDB(Connect connect, File xml) {
        ArrayList<Country> countries = Sax.getData(xml);
        try {
            PreparedStatement objInsert = connect.getConnect().prepareStatement("insert into countries(continent,name,area,population,minerals) values(?,?,?,?,?)");
            for(Country country : countries) {
                objInsert.setString(1,country.getContinent());
                objInsert.setString(2,country.getName());
                objInsert.setInt(3,country.getArea());
                objInsert.setInt(4,country.getPopulation());
                objInsert.setString(5,country.getMinerals());
                objInsert.addBatch();
            }
            objInsert.executeBatch();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
