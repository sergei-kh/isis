package ru.bstu.it51.hlopov.helpers.xml;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Properties;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import ru.bstu.it51.hlopov.Models.Country;
import ru.bstu.it51.hlopov.helpers.xml.Convert;

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
            System.out.printf("id: %d\n",country.getId());
            System.out.printf("Континент: %s\n",country.getContinent());
            System.out.printf("Название: %s\n",country.getName());
            System.out.printf("Площадь: %d\n",country.getArea());
            System.out.printf("Население: %d\n",country.getPopulation());
            System.out.printf("Полезные ископаемые: %s\n\n",country.getMinerals());
        }
    }

    public void addItemToDocument() {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(xml);
            document.getDocumentElement().normalize();
            Element root = document.getDocumentElement();
            root.appendChild(Elements.getCountry(document, Sax.getNextId(xml)));
            Convert.writeToFile(document,xml);
            System.out.println("Новая запись успешно создана");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void editItemDocument(int id) {
        ArrayList<Country> countries = Sax.getData(xml);
        for(Country country : countries) {
            if(country.getId() == id) {
                System.out.println(country.getName());
            }
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
            }
        } else {
            System.out.println("Команда не определена");
        }
    }
}
