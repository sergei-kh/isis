package ru.bstu.it51.hlopov.helpers.human;
import ru.bstu.it51.hlopov.helpers.abstr.Human;

import java.util.Scanner;

public class Student extends Human {
    protected String gradebookNumber; // Номер зачётки

    public Student(Scanner scanner) {
        super(scanner);
    }

    public void init(Scanner scanner) {
        System.out.print("Номер зачетки: ");
        this.gradebookNumber = scanner.next();
    }
    @Override
    public String toString() {
        return "Возраст: " + this.age + "\nИмя: " + this.name + "\nНомер зачетки: " + this.gradebookNumber;
    }
}
