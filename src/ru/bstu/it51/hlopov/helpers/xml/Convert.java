package ru.bstu.it51.hlopov.helpers.xml;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import ru.bstu.it51.hlopov.Models.Country;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Convert {
    /**
     * <p>Метод конвертирует переданный документ в xml файл<p/>
     * @param doc документ
     * @param xml xml файл куда будем записывать
     * @throws TransformerException
     * @return void
     */
    public static void writeToFile(Document doc, File xml) throws TransformerException {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
        DOMSource source = new DOMSource(doc);
        StreamResult file = new StreamResult(xml);
        transformer.transform(source, file);
    }

    /**
     * <p>Метод считывает данные с консоли и записывает эти данные в объект Country<p/>
     * @return Country
     */
    public static Country fillObjectModel(Country country) {
        Scanner in = new Scanner(System.in);
        System.out.print("Введите континет: ");
        country.setContinent(in.next());
        System.out.print("Введите название: ");
        country.setName(in.next());
        System.out.print("Введите площадь: ");
        country.setArea(in.nextInt());
        System.out.print("Введите население: ");
        country.setPopulation(in.nextInt());
        System.out.print("Полезные ископаемые через запятую: ");
        country.setMinerals(in.next());
        return country;
    }
    public static Country fillObjectModel() {
        Country country = new Country();
        Scanner in = new Scanner(System.in);
        System.out.print("Введите континет: ");
        country.setContinent(in.next());
        System.out.print("Введите название: ");
        country.setName(in.next());
        System.out.print("Введите площадь: ");
        country.setArea(in.nextInt());
        System.out.print("Введите население: ");
        country.setPopulation(in.nextInt());
        System.out.print("Полезные ископаемые через запятую: ");
        country.setMinerals(in.next());
        return country;
    }

    /**
     * <p>Метод выводит в консоль объект модели<p/>
     * @return void
     */
    public static void printObjectModel(Country country) {
        System.out.printf("id: %d\n",country.getId());
        System.out.printf("Континент: %s\n",country.getContinent());
        System.out.printf("Название: %s\n",country.getName());
        System.out.printf("Площадь: %d\n",country.getArea());
        System.out.printf("Население: %d\n",country.getPopulation());
        System.out.printf("Полезные ископаемые: %s\n\n",country.getMinerals());
    }

    /**
     * Метод конвертирует список моделей в документ
     * @param countries список моделей
     * @throws ParserConfigurationException
     * @return Document
     */
    public static Document modelsToDocument(ArrayList<Country> countries) throws ParserConfigurationException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.newDocument();
        Element root = document.createElement("countries");
        document.appendChild(root);
        for(Country country : countries) {
            root.appendChild(Elements.getCountry(document,country));
        }
        return document;
    }
}
