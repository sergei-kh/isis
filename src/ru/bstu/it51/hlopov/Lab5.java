package ru.bstu.it51.hlopov;
import ru.bstu.it51.hlopov.helpers.xml.Parser;

import java.util.Scanner;

public class Lab5 {

    public void run() {
        Scanner in = new Scanner(System.in);
        byte format = 0;
        System.out.print("Выберите формал представления (1 - XML, 2 - БД): ");
        format = in.nextByte();
        if(format > 0) {
            switch (format) {
                case 1:
                    byte cmd = 0;
                    System.out.print("Просмотреть всё = 0\nДобавление = 1\nРедактирование = 2\nУдаление = 3\n");
                    Parser parser = new Parser("configLab5.properties");
                    System.out.println("Выход = -1");
                    while (cmd != -1) {
                        System.out.print(">> ");
                        cmd = in.nextByte();
                        parser.routingCommands(cmd,(byte) 0,(byte) 3);
                    }
                    break;
                case 2:
                    break;
            }
        } else {
            System.out.println("Ошибка, неверный ввод");
        }
    }
}
