package ru.bstu.it212.hlopov.helpers.xml;

import java.io.File;
import java.util.*;

import ru.bstu.it212.hlopov.Models.Country;

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

    public void findMaxOrMin(byte direction, byte typeField) {
        ArrayList<Country> countries = Sax.getData(xml);
        Country country;
        if(direction == 0) {
            if(typeField == 0) {
                country = countries.stream().max(Comparator.comparing(Country::getArea)).get();
            } else {
                country = countries.stream().max(Comparator.comparing(Country::getPopulation)).get();
            }
        } else {
            if(typeField == 0) {
                country = countries.stream().min(Comparator.comparing(Country::getArea)).get();
            } else {
                country = countries.stream().min(Comparator.comparing(Country::getPopulation)).get();
            }
        }
        Convert.printObjectModel(country);
    }

    public void findToFields(String str, String fields) {
        ArrayList<Integer> ids = new ArrayList<Integer>();
        HashMap<Integer, Integer> resultIds = new HashMap<Integer, Integer>();
        ArrayList<Country> countries = Sax.getData(xml);
        String[] fieldsArr = fields.split(",");
        for(String name : fieldsArr) {
            for(Country country : countries) {
                switch (name) {
                    case "continent":
                        if (country.getContinent().contains(str)) {
                            ids.add(country.getId());
                        }
                        break;
                    case "name":
                        if (country.getName().contains(str)) {
                            ids.add(country.getId());
                        }
                        break;
                    case "minerals":
                        if (country.getMinerals().contains(str)) {
                            ids.add(country.getId());
                        }
                        break;
                }
            }
        }
        Integer count;
        for (Integer i : ids) {
            count = resultIds.get(i);
            resultIds.put(i, count == null ? 1 : count + 1);
        }
        for (Map.Entry<Integer, Integer> entry : resultIds.entrySet()) {
            if(entry.getValue() == fieldsArr.length) {
                for(Country country : countries) {
                    if(country.getId() == entry.getKey()) {
                        Convert.printObjectModel(country);
                    }
                }
            }
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
            case 4:
                System.out.print("0 - Максимальное значение; 1 - Минимальное значение: ");
                byte direction = in.nextByte();
                System.out.print("0 - По площади; 1 - По населению ");
                byte typeField = in.nextByte();
                findMaxOrMin(direction, typeField);
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
