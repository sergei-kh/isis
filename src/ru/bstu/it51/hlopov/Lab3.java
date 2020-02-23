package ru.bstu.it51.hlopov;

import ru.bstu.it51.hlopov.helpers.human.*;
import ru.bstu.it51.hlopov.helpers.abstr.Human;

import java.util.Comparator;
import java.util.Scanner;

import java.util.ArrayList;

public class Lab3 {
    protected ArrayList<Human> Humans = new ArrayList<Human>();
    public void run() {
        byte typePerson = -1;
        Scanner in = new Scanner(System.in);
        System.out.print("Введите кол-во человек: ");
        System.out.printf("Кол-во человек: %d\n",Student.getCountHumansFormStr(in.next()));
        while (typePerson != 0) {
            System.out.print("Тип персоны: (0 - выход; 1 - школьник; 2 - студент; 3 - преподаватель; 4 - директор): ");
            typePerson = in.nextByte();
            this.addPerson(typePerson, in);
        }
        var human = this.Humans.stream().min(Comparator.comparing(Human::getAge)).get();
        System.out.print(human.toString());
    }
    private void addPerson(byte typePerson, Scanner in) {
        switch (typePerson) {
            case  1:
                Schoolboy schoolboy = new Schoolboy(in);
                schoolboy.init(in);
                this.Humans.add(schoolboy);
                break;
            case 2:
                Student student = new Student(in);
                student.init(in);
                this.Humans.add(student);
                break;
            case 3:
                Teacher teacher = new Teacher(in);
                teacher.init(in);
                this.Humans.add(teacher);
                break;
            case 4:
                Director director = new Director(in);
                director.init(in);
                this.Humans.add(director);
                break;
        }
    }
}