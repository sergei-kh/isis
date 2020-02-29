package ru.bstu.it51.hlopov;
import java.util.Scanner;
/**
 * Главный класс программы
 */
public class app {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Введите номер лабораторной работы: ");
        byte number = in.nextByte();
        if(number > 0 && number <= 5) {
            switch (number) {
                case 1:
                    Lab1 lr1 = new Lab1();
                    lr1.run();
                    break;
                case 2:
                    System.setProperty("log4j.configurationFile", "log4j2.xml");
                    Lab2 lr2 = new Lab2();
                    lr2.run();
                    break;
                case 3:
                    Lab3 lr3 = new Lab3();
                    lr3.run();
                    break;
                case 4:
                    Lab4 lr4 = new Lab4();
                    lr4.run();
                    break;
                case 5:
                    Lab5 lr5 = new Lab5();
                    lr5.run();
                    break;
                default:
                    break;
            }
        } else {
            System.out.printf("Не корректный ввод");
        }
    }
}
