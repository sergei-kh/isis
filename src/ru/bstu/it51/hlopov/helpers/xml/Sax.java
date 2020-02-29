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
    protected File xml;
    public Sax(File xml) {
        this.xml = xml;
    }
    public ArrayList<Country> getData() {
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
                        country = new Country();
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
                    else if(tag.equalsIgnoreCase("country")) {
                        System.out.print("\n");
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
}
