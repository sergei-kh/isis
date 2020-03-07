package ru.bstu.it51.hlopov;
import ru.bstu.it51.hlopov.helpers.db.ParserDb;
import ru.bstu.it51.hlopov.helpers.xml.ParserXml;

import java.util.Scanner;

public class Lab5 {

    public void run() {
        Scanner in = new Scanner(System.in);
        byte format = 0;
        System.out.print("Выберите формал представления (1 - XML, 2 - БД): ");
        format = in.nextByte();
        byte cmd = 0;
        int id = 0;
        if(format > 0) {
            switch (format) {
                case 1:
                    System.out.print("Просмотреть всё = 0\nДобавление = 1\nРедактирование = 2\nУдаление = 3\n");
                    ParserXml parser = new ParserXml("configLab5.properties");
                    System.out.println("Выход = -1");
                    while (cmd != -1) {
                        System.out.print(">> ");
                        cmd = in.nextByte();
                        if(cmd >= 2 && cmd <= 3) {
                            System.out.print("Введите ID записи: ");
                            id = in.nextInt();
                        }
                        parser.routingCommands(cmd,(byte) 0,(byte) 3, id);
                    }
                    break;
                case 2:
                    ParserDb parserDb = new ParserDb("configLab5.properties");
                    System.out.print("Просмотреть всё = 0\nДобавление = 1\nРедактирование = 2\nУдаление = 3\n");
                    System.out.println("Выход = -1");
                    while (cmd != -1) {
                        System.out.print(">> ");
                        cmd = in.nextByte();
                        if(cmd >= 2 && cmd <= 3) {
                            System.out.print("Введите ID записи: ");
                            id = in.nextInt();
                        }
                        parserDb.routingCommands(cmd,(byte) 0,(byte) 3, id);
                    }
                    break;
            }
        } else {
            System.out.println("Ошибка, неверный ввод");
        }
    }
}
