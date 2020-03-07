package ru.bstu.it51.hlopov.helpers.xml;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import ru.bstu.it51.hlopov.Models.Country;
import ru.bstu.it51.hlopov.helpers.xml.Convert;

public class Elements {
    public static Node getCountry(Document doc, Country country) {
        Element countryEl = doc.createElement("country");
        countryEl.setAttribute("id",Integer.toString(country.getId()));
        countryEl.appendChild(Elements.getCountryElement(doc,"continent",country.getContinent()));
        countryEl.appendChild(Elements.getCountryElement(doc,"name",country.getName()));
        countryEl.appendChild(Elements.getCountryElement(doc,"area",country.getStringArea()));
        countryEl.appendChild(Elements.getCountryElement(doc,"population",country.getStringPopulation()));
        countryEl.appendChild(Elements.getCountryElement(doc,"minerals",country.getMinerals()));
        return  countryEl;
    }
    private static Node getCountryElement(Document doc, String name, String value) {
        Element node = doc.createElement(name);
        node.appendChild(doc.createTextNode(value));
        return node;
    }
}
