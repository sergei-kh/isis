package ru.bstu.it212.hlopov.helpers.abstr;

import java.util.Scanner;

public abstract class Employee extends Human {
    protected float wages; // Зарплата
    protected float workExperience; // Стаж работы

    public Employee(Scanner scanner) {
        super(scanner);
        System.out.print("Зарплата: ");
        this.wages = scanner.nextFloat();
        System.out.print("Стаж работы: ");
        this.workExperience = scanner.nextFloat();
    }
}
