package ru.bstu.it51.hlopov.helpers.xml;

import org.w3c.dom.Document;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

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
}
