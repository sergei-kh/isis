package ru.bstu.it212.hlopov.helpers.human;
import ru.bstu.it212.hlopov.helpers.abstr.Employee;

import java.util.Scanner;

public class Director extends Employee {

    protected byte school; // Школа

    public Director(Scanner scanner) {
        super(scanner);
    }
    public void init(Scanner scanner) {
        System.out.print("Школа: ");
        this.school = scanner.nextByte();
    }
    @Override
    public String toString() {
        return "Возраст: " + this.age + "\nИмя: " + this.name + "\nЗарплата: " + this.wages + "\nСтаж работы: ";
    }
}