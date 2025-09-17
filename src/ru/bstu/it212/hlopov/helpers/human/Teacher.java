package ru.bstu.it212.hlopov.helpers.human;
import ru.bstu.it212.hlopov.helpers.abstr.Employee;

import java.util.Scanner;

public class Teacher extends Employee {
    protected String certificateNumber; // Номер сертификата

    public Teacher(Scanner scanner) {
        super(scanner);
    }

    public void init(Scanner scanner) {
        System.out.print("Номер сертификата: ");
        this.certificateNumber = scanner.next();
    }
    @Override
    public String toString() {
        return "Возраст: " + this.age + "\nИмя: " + this.name + "\nЗарплата: " + this.wages + "\nСтаж работы: "
                + this.workExperience + "\nНомер сертификата: " + this.certificateNumber;
    }
}
