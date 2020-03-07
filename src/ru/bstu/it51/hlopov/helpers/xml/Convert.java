package ru.bstu.it51.hlopov.helpers.xml;

import org.w3c.dom.Document;
import ru.bstu.it51.hlopov.Models.Country;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
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
        DOMSource source = new DOMSource(doc);
        StreamResult file = new StreamResult(xml);
        transformer.transform(source, file);
    }

    /**
     * <p>Метод считывает данные с консоли и записывает эти данные в объект Country<p/>
     * @return Country
     */
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
}
