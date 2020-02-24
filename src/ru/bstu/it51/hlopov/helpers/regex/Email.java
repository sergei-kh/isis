package ru.bstu.it51.hlopov.helpers.regex;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.*;
import java.util.ArrayList;

public class Email {
    public static ArrayList<String> findEmailFromText(ArrayList<String> text) {
        String regex = "([a-zа-яё0-9._-]+)(@{1})([a-zа-яё0-9._-]+)\\.([a-zа-я]{2,3})";
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
