package ru.bstu.it51.hlopov.helpers.xml;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class Elements {
    public static Node getCountry(Document doc, int id) {
        Element country = doc.createElement("country");
        country.setAttribute("id",Integer.toString(id));
//        country.appendChild(Elements.getCountryElement(doc,"continent",continent));
//        country.appendChild(Elements.getCountryElement(doc,"name",name));
//        country.appendChild(Elements.getCountryElement(doc,"area",area));
//        country.appendChild(Elements.getCountryElement(doc,"population",population));
//        country.appendChild(Elements.getCountryElement(doc,"minerals",minerals));
        return  country;
    }
    private static Node getCountryElement(Document doc, String name, String value) {
        Element node = doc.createElement(name);
        node.appendChild(doc.createTextNode(value));
        return node;
    }
}
