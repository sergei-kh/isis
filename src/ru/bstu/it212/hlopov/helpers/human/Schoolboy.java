package ru.bstu.it212.hlopov.helpers.human;
import ru.bstu.it212.hlopov.helpers.abstr.Human;

import java.util.Scanner;

public class Schoolboy extends Human {
    protected byte school; // Школа

    public Schoolboy(Scanner scanner) {
        super(scanner);
    }

    public void init(Scanner scanner) {
        System.out.print("Школа: ");
        this.school = scanner.nextByte();
    }

    @Override
    public String toString() {
        return "Возраст: " + this.age + "\nИмя: " + this.name + "\nШкола: " + this.school;
    }
}