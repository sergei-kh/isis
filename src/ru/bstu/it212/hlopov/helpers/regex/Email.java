package ru.bstu.it212.hlopov.helpers.regex;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.*;
import java.util.ArrayList;

public class Email {
    public static ArrayList<String> findEmailFromText(ArrayList<String> text) {
        String regex = "[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}";
        ArrayList<String> items = new ArrayList<String>();
        Pattern pattern = Pattern.compile(regex,Pattern.CASE_INSENSITIVE);
        for(String line : text) {
            Matcher matchObj = pattern.matcher(line);
            while(matchObj.find()) {
                items.add(matchObj.group());
            }
        }
        Set<String> tmpItems = new HashSet<String>(items);
        items.clear();
        items.addAll(tmpItems);
        return items;
    }
}
