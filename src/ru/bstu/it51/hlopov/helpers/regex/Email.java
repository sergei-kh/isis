package ru.bstu.it51.hlopov.helpers.regex;
import java.util.regex.*;

public class Email {
    public void findEmailFromText(String text) {
        String regex = "([a-zа-яё0-9._-]+)(@{1})([a-zа-яё0-9._-]+)\\.([a-zа-я]{2})";
        Pattern pattern = Pattern.compile(regex,Pattern.CASE_INSENSITIVE);
        Matcher matchObj = pattern.matcher(text);
        while (matchObj.find()) {
            System.out.println(matchObj.group());
        }
    }
}
