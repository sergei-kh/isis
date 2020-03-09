package ru.bstu.it51.hlopov;
import ru.bstu.it51.hlopov.helpers.ConvertXmlDb;
import ru.bstu.it51.hlopov.helpers.db.Connect;
import ru.bstu.it51.hlopov.helpers.db.ParserDb;
import ru.bstu.it51.hlopov.helpers.xml.ParserXml;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;
import java.util.Scanner;

public class Lab5 {
    protected Properties prop = new Properties();

    public void run() {
        Scanner in = new Scanner(System.in);
        try {
            prop.load(new FileInputStream("configLab5.properties"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.print("Выберите формал представления (1 - XML, 2 - БД, 3 - Конвертировать): ");
        byte cmd = 0;
        int id = 0;
        switch (in.nextByte()) {
            case 1:
                ParserXml parser = new ParserXml(prop);
                System.out.println("Просмотреть всё = 0; Добавление = 1; Редактирование = 2; Удаление = 3; Выход = -1");
                while (cmd != -1) {
                    System.out.print(">> ");
                    cmd = in.nextByte();
                    if(cmd >= 2 && cmd <= 3) {
                        System.out.print("Введите ID записи: ");
                        id = in.nextInt();
                    }
                    parser.routingCommands(cmd, id);
                }
                break;
            case 2:
                Connect connect = new Connect(prop);
                System.out.println("Просмотреть всё = 0; Добавление = 1; Редактирование = 2; Удаление = 3; Выход = -1");
                ParserDb parserDb = new ParserDb(connect.getConnect());
                while (cmd != -1) {
                    System.out.print(">> ");
                    cmd = in.nextByte();
                    if(cmd >= 2 && cmd <= 3) {
                        System.out.print("Введите ID записи: ");
                    }
                    parserDb.routingCommands(cmd, in.nextInt());
                }
                connect.close();
                break;
            case 3:
                Connect connect2 = new Connect(prop);
                System.out.println("Конвертировать из xml в БД = 1; конвертировать из БД в xml = 2");
                byte tmp = in.nextByte();
                if(tmp == 1) {
                    ConvertXmlDb.xmlToDB(connect2,new File(prop.getProperty("PATH_FILE_XML")));
                } else if(tmp == 2) {
                    ConvertXmlDb.DbToXml(connect2,new File(prop.getProperty("PATH_FILE_XML")));
                }
                connect2.close();
                break;
        }
    }
}
