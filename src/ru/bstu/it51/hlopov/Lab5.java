package ru.bstu.it51.hlopov;
import ru.bstu.it51.hlopov.helpers.ConvertXmlDb;
import ru.bstu.it51.hlopov.helpers.db.Connect;
import ru.bstu.it51.hlopov.helpers.xml.ParserXml;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;
import java.util.Scanner;

public class Lab5 {
    protected Properties prop = new Properties();

    public void run() {
        Scanner in = new Scanner(System.in);
        byte format = 0;
        try {
            prop.load(new FileInputStream("configLab5.properties"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.print("Выберите формал представления (1 - XML, 2 - БД) либо 3 - для конвертации: ");
        format = in.nextByte();
        byte cmd = 0;
        int id = 0;
        if(format > 0) {
            switch (format) {
                case 1:
                    ParserXml parser = new ParserXml(prop);
                    System.out.println("Просмотреть всё = 0; Добавление = 1; Редактирование = 2; Удаление = 3");
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
                    Connect connect = new Connect(prop);
                    System.out.println("Просмотреть всё = 0; Добавление = 1; Редактирование = 2; Удаление = 3");
                    System.out.println("Выход = -1");
                    while (cmd != -1) {
                        System.out.print(">> ");
                        cmd = in.nextByte();
                        if(cmd >= 2 && cmd <= 3) {
                            System.out.print("Введите ID записи: ");
                        }
                    }
                    connect.close();
                    break;
                case 3:
                    Connect connect2 = new Connect(prop);
                    ConvertXmlDb.xmlToDB(connect2,new File(prop.getProperty("PATH_FILE_XML")));
                    connect2.close();
                    break;
            }
        } else {
            System.out.println("Ошибка, неверный ввод");
        }
    }
}
