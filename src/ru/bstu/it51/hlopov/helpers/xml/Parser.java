package ru.bstu.it51.hlopov.helpers.xml;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Properties;
import ru.bstu.it51.hlopov.Models.Country;

public class Parser {
    protected File xml;
    protected Properties prop;

    public Parser(String config) {
        try {
            prop = new Properties();
            prop.load(new FileInputStream(config));
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
        boolean successFind = false;
        for(Country country : countries) {
            if(country.getId() == id) {
                successFind = true;
                System.out.println("Данные на текущий момент: ");
                Convert.printObjectModel(country);
                Convert.fillObjectModel(country);
            }
        }
        try {
            if(successFind) {
                Convert.writeToFile(Convert.modelsToDocument(countries),xml);
                System.out.println("Редактирование выполнено");
            } else {
                System.out.println("Объект не найден");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void removeItemDocument(int id) {
        ArrayList<Country> countries = Sax.getData(xml);
        boolean successFind = false;
        for(int i = 0; i < countries.size(); ++i) {
            if(countries.get(i).getId() == id) {
                successFind = true;
                countries.remove(i);
            }
        }
        try {
            if(successFind) {
                Convert.writeToFile(Convert.modelsToDocument(countries),xml);
                System.out.println("Удаление выполнено успешно");
            } else {
                System.out.println("Объект не найден");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void routingCommands(byte cmd, byte min, byte max, int param) {
        if(cmd <= max && cmd >= min) {
            switch (cmd) {
                case 0:
                    printAllDocument();
                    break;
                case 1:
                    addItemToDocument();
                    break;
                case 2:
                    editItemDocument(param);
                    break;
                case 3:
                    removeItemDocument(param);
                    break;
            }
        } else {
            System.out.println("Команда не определена");
        }
    }
}
