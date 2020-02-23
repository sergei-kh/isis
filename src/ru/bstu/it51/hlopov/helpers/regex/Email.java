package ru.bstu.it51.hlopov.helpers.regex;
import java.util.regex.*;

public class Email {
    public Email() {
        String str = "Давно выяснено, что при оценке дизайна и композиции читаемый текст мешает referent@vlgr.ranepa.ru" +
                " Давно выяснено,@ lolol@ololo. .qewqe@qwe,ry 123@mail,ru rlro@mm,.ry что при оценке дизайна и композиции читаемый текст мешает prorector.nauka@vlgr.ranepa.ru Давно выяснено, " +
                "что при оценке дизайна и композиции читаемый текст мешает grajd@vlgr.ranepa.ru что при оценке дизайна и композиции читаемый блабла@lolwqe skdjf @@@" +
                "lol @ kwwj. ин фо@по чта.рф Fsk ueqwe rot ";
        String regex = "([a-zа-яё0-9._-]+)(@{1})([a-zа-яё0-9._-]+)\\.([a-zа-я]{2})";
        Pattern pattern = Pattern.compile(regex,Pattern.CASE_INSENSITIVE);
        Matcher matchObj = pattern.matcher(str);
        String match = "";
        while (matchObj.find()) {
            match = matchObj.group();
            System.out.println(match);
        }
    }
}
