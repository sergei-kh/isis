package ru.bstu.it51.hlopov.helpers.regex;
import java.util.regex.*;

public class Email {
    public Email() {
        String str = "Давно выяснено, что при оценке дизайна и композиции читаемый текст мешает сосредоточиться. Lorem " +
                "Ipsum используют потому, что тот обеспечивает более или менее стандартное заполнение шаблона" +
                "sergey@webstyle.top123" +
                "сосредоточиться. Lorem Ipsum используют потому, что тот обеспечивает более или менее стандартное " +
                "заполнение шаблона 123@mail.ru 123@";
        String pattern = "([a-z0-9._-]+)(@{1})([a-z0-9._-]+).([a-z]+)";
        Pattern r = Pattern.compile(pattern,Pattern.CASE_INSENSITIVE);
        Matcher m = r.matcher(str);
        String match = "";
        while (m.find()) {
            match = m.group();
            System.out.println(match);
        }
    }
}
