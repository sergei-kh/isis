package ru.bstu.it51.hlopov.helpers.xml;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.io.File;
import java.util.ArrayList;
import ru.bstu.it51.hlopov.Models.Country;

public class Sax {

    public static ArrayList<Country> getData(File xml) {
        ArrayList<Country> countries = new ArrayList<Country>();
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            DefaultHandler handler = new DefaultHandler() {
                Country country;
                String tag = "";
                @Override
                public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
                    if(qName.equalsIgnoreCase("country")) {
                        country = new Country(Integer.parseInt(attributes.getValue(0)));
                    }
                    tag = qName;
                }
                @Override
                public void characters(char[] ch, int start, int length) throws SAXException {
                    if(tag.equalsIgnoreCase("continent")) {
                        country.setContinent(new String(ch,start,length));
                    }
                    else if(tag.equalsIgnoreCase("name")) {
                        country.setName(new String(ch,start,length));
                    }
                    else if(tag.equalsIgnoreCase("area")) {
                        country.setArea(Integer.parseInt(new String(ch,start,length)));
                    }
                    else if(tag.equalsIgnoreCase("population")) {
                        country.setPopulation(Integer.parseInt(new String(ch,start,length)));
                    }
                    else if(tag.equalsIgnoreCase("minerals")) {
                        country.setMinerals(new String(ch,start,length));
                    }
                }
                @Override
                public void endElement(String uri,String localName,String qName) throws SAXException {
                    if(tag.equalsIgnoreCase("continent")) {
                        countries.add(country);
                    }
                    tag = "";
                }
            };
            saxParser.parse(xml.getPath(),handler);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return countries;
    }
    public static int getNextId(File xml) {
        ArrayList<Country> countries = Sax.getData(xml);
        return countries.get(countries.size() - 1).getId() + 1;
    }
}
