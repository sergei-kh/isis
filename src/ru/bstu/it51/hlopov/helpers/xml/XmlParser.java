package ru.bstu.it51.hlopov.helpers.xml;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.Properties;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class XmlParser {
    protected File xml;
    protected Properties prop;

    public XmlParser(String config) {
        try {
            prop = new Properties();
            FileInputStream file = new FileInputStream("configLab5.properties");
            prop.load(file);
            xml = new File(prop.getProperty("PATH_FILE_XML"));
        } catch (IOException e) {
            System.out.println("Файл не найден");
        }
    }

    public void printAllDocument() {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            NumberFormat f = NumberFormat.getInstance();
            DefaultHandler handler = new DefaultHandler() {
                String tag = "";
                @Override
                public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
                    tag = qName;
                }
                @Override
                public void characters(char[] ch, int start, int length) throws SAXException {
                    if(tag.equalsIgnoreCase("continent")) {
                        System.out.println("Континент: " + new String(ch,start,length));
                    }
                    else if(tag.equalsIgnoreCase("name")) {
                        System.out.println("Название страны: " + new String(ch,start,length));
                    }
                    else if(tag.equalsIgnoreCase("area")) {
                        System.out.println("Площадь: " + f.format(Integer.parseInt(new String(ch,start,length))));
                    }
                    else if(tag.equalsIgnoreCase("population")) {
                        System.out.println("Население: " + f.format(Integer.parseInt(new String(ch,start,length))));
                    }
                    else if(tag.equalsIgnoreCase("minerals")) {
                        System.out.println("Полезные ископаемые: " + new String(ch,start,length));
                    }
                    else if(tag.equalsIgnoreCase("country")) {
                        System.out.print("\n");
                    }
                }
                @Override
                public void endElement(String uri,String localName,String qName) throws SAXException {
                    tag = "";
                }
            };
            saxParser.parse(xml.getPath(),handler);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean addItemToDocument() {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(xml);
            Element root = document.getDocumentElement();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    public void routingCommands(byte cmd, byte min, byte max) {
        if(cmd <= max && cmd >= min) {
            switch (cmd) {
                case 0:
                    printAllDocument();
                    break;
                case 1:
                    addItemToDocument();
                    break;
            }
        } else {
            System.out.println("Команда не определена");
        }
    }
}
