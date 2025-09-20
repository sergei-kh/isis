package ru.bstu.it212.hlopov;
import ru.bstu.it212.hlopov.helpers.regex.Email;
import java.io.FileReader;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Lab4 {
    public void run() {
        ArrayList<String> content = new ArrayList<String>();
        Scanner in = new Scanner(System.in);
        try {
            System.out.print("Введите путь до файла: ");
            Scanner inFile = new Scanner(new FileReader(in.nextLine()));
            while (inFile.hasNextLine()) {
                content.add(inFile.nextLine());
            }
            inFile.close();
        } catch (IOException ex) {
            System.out.println("Не могу открыть файл");
        }
        if(!content.isEmpty()) {
            ArrayList<String> result;
            result = Email.findEmailFromText(content);
            System.out.printf("Найдено уникальных e-mail адресов: %d\n",result.size());
            try {
                PrintWriter out = new PrintWriter("files/out.txt");
                for(String email : result) {
                    out.println(email);
                }
                out.close();
            } catch(IOException ignored) { }
        } else {
            System.out.println("Файл пустой");
        }
    }
}
