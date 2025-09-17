package ru.bstu.it212.hlopov.helpers.abstr;

import java.util.regex.*;
import java.util.Scanner;

public abstract class Human {
    protected byte age; // Возвраст
    protected String name; // Имя

    public Human(Scanner scanner) {
        System.out.print("Введите имя: ");
        this.name = scanner.next();
        System.out.print("Введите возраст: ");
        this.age = scanner.nextByte();
    }

    public abstract void init(Scanner scanner);

    public String toString() {
        return this.age + " " + this.name;
    }

    public int getAge() { return this.age; }

    public static int getCountHumansFormStr(String str) {
        Pattern p = Pattern.compile("(\\d+)");
        Matcher m = p.matcher(str);
        while(m.find()) {
            return Integer.parseInt(m.group(1));
        }
        return 0;
    }
}
