package ru.bstu.it51.hlopov.helpers.xml;

import java.io.File;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Scanner;

import ru.bstu.it51.hlopov.Models.Country;

public class ParserXml {
    protected File xml;
    protected Properties prop;

    public ParserXml(Properties properties) {
        try {
            prop = properties;
            xml = new File(prop.getProperty("PATH_FILE_XML"));
        } catch (Exception e) {
            System.out.println("Файл не найден");
        }
    }

    public void printAllDocument() {
        ArrayList<Country> countries = Sax.getData(xml);
        for(Country country : countries) {
            Convert.printObjectModel(country);
        }
    }

    public void addItemToDocument() {
        try {
            ArrayList<Country> countries = Sax.getData(xml);
            Country country = Convert.fillObjectModel();
            country.setId(countries.get(countries.size() - 1).getId());
            countries.add(country);
            Convert.writeToFile(Convert.modelsToDocument(countries),xml);
            System.out.println("Новая запись успешно создана");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void editItemDocument(int id) {
        ArrayList<Country> countries = Sax.getData(xml);
        for(Country country : countries) {
            if(country.getId() == id) {
                System.out.println("Данные на текущий момент: ");
                Convert.printObjectModel(country);
                Convert.fillObjectModel(country);
                try {
                    Convert.writeToFile(Convert.modelsToDocument(countries),xml);
                    System.out.println("Редактирование выполнено");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void removeItemDocument(int id) {
        ArrayList<Country> countries = Sax.getData(xml);
        boolean status = false;
        for(int i = 0; i < countries.size(); ++i) {
            if(countries.get(i).getId() == id) {
                countries.remove(i);
                status = true;
                try {
                    Convert.writeToFile(Convert.modelsToDocument(countries),xml);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        if(status) {
            System.out.println(prop.getProperty("MESSAGE_SUCCESS_REMOVE"));
        } else {
            System.out.println(prop.getProperty("MESSAGE_FAIL_REMOVE"));
        }
    }

    public void routingCommands(byte cmd, Scanner in) {
        switch (cmd) {
            case 0:
                printAllDocument();
                break;
            case 1:
                addItemToDocument();
                break;
            case 2:
                System.out.print("Введите Id: ");
                editItemDocument(in.nextInt());
                break;
            case 3:
                System.out.print("Введите Id: ");
                removeItemDocument(in.nextInt());
                break;
        }
    }
}
