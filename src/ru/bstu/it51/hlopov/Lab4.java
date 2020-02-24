package ru.bstu.it51.hlopov;
import ru.bstu.it51.hlopov.helpers.regex.Email;

import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Lab4 {
    public void run() {
        try {
            Scanner inFile = new Scanner(new FileReader("files/main.html"));
            Email email = new Email();
            while (inFile.hasNextLine()) {
                email.findEmailFromText(inFile.nextLine());
            }
            inFile.close();
        } catch (IOException ex) {
            System.out.println("Не могу открыть файл");
        }
    }
}
