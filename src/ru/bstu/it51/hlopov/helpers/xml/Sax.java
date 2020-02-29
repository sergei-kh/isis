package ru.bstu.it51.hlopov.helpers.xml;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.io.File;
import java.text.NumberFormat;
import java.util.ArrayList;
import ru.bstu.it51.hlopov.Models.Country;

public class Sax {
    protected File xml;
    public Sax(File xml) {
        this.xml = xml;
    }
    public void getData() {
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
}
