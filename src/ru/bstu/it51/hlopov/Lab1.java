package ru.bstu.it51.hlopov;

import java.util.Arrays;
import java.util.Scanner;
import java.io.*;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Logger;
import ru.bstu.it51.hlopov.helpers.Input;
import ru.bstu.it51.hlopov.helpers.Date;
import ru.bstu.it51.hlopov.helpers.Table;

public class Lab1 {

    private Logger log = null;

    public Lab1(Logger loggerArg) {
        this.log = loggerArg;
    }

    public Lab1() {}

    public void run() {
        if(this.log != null) {
            this.log.info("Запуск первой лабораторной работы");
        }
        this.isBrickSuitable();
        this.showNameMonth();
        this.showTablesMultiplicationAndSum();
        this.arrayCountElements();
        if(this.log != null) {
            this.log.info("Лабораторная работа закончена");
        }
    }

    public void isBrickSuitable() {
        Scanner in = new Scanner(System.in);
        if(this.log != null) {
            this.log.info("Начинаю выполнять первое задание (условные выражения)");
        }
        float a = 0;
        float b = 0;
        float x = 0;
        float y = 0;
        float z = 0;
        boolean fileNotFound = false;
        if(!Input.formFile(in, this.log)) {
            System.out.println("Введите (A, B) отверстия");
            System.out.print("A = ");
            a = in.nextFloat();
            System.out.print("B = ");
            b = in.nextFloat();
            System.out.println("Введите (X, Y, Z) керпича");
            System.out.print("X = ");
            x = in.nextFloat();
            System.out.print("Y = ");
            y = in.nextFloat();
            System.out.print("Z = ");
            z = in.nextFloat();
        } else {
            try(BufferedReader reader = new BufferedReader(new FileReader("files/z1.txt"))) {
                String line;
                byte lineCount = 0;
                while ((line = reader.readLine()) != null) {
                    if(!line.isEmpty()) {
                        String[] tmpArray = line.split(",",0);
                        ++lineCount;
                        if(lineCount == 1) {
                            a = Float.parseFloat(tmpArray[0]);
                            b = Float.parseFloat(tmpArray[1]);
                        } else if(lineCount == 2) {
                            x = Float.parseFloat(tmpArray[0]);
                            y = Float.parseFloat(tmpArray[1]);
                            z = Float.parseFloat(tmpArray[2]);
                        }
                    }
                }
            } catch (IOException ex) {
                if(this.log != null) {
                    fileNotFound = true;
                    this.log.error("File not found",ex);
                }
            }
        }
        if(x != 0 && y != 0 && z != 0) {
            if(!fileNotFound) {
                this.log.printf(Level.DEBUG,"Размеры отверсия (A и B) %f, %f", a, b);
                this.log.printf(Level.DEBUG,"Размеры керпича (X, Y, Z) %f, %f, %f", x, y, z);
                if(b >= y && a >= z || b >= z && a >= y || b >= x && a >= y || b >= y && a >= x
                        || a >= x && b >= z || a >= z && b >= x) {
                    this.log.debug("Керпич проходит");
                } else {
                    this.log.debug("Керпич не проходит");
                }
                if(this.log != null) {
                    this.log.info("Задача - 'условные выражения' выполнена!");
                }
            }
        } else {
            this.log.warn("Внимание размеры керпича были равны нулю");
        }
    }

    public void showNameMonth() {
        if(this.log != null) {
            this.log.info("Начинаю выполнять второе задание (оператор выбора)");
        }
        Scanner in = new Scanner(System.in);
        byte numberMonth = 0;
        if(!Input.formFile(in, this.log)) {
            System.out.print("Введите номер месяца: ");
            numberMonth = in.nextByte();
            this.log.printf(Level.DEBUG,"Номер месяца: %d",numberMonth);
        } else {
            try(BufferedReader reader = new BufferedReader(new FileReader("files/z2.txt"))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    numberMonth = Byte.parseByte(line);
                }
            } catch (IOException ex) {
                this.log.error("File not found",ex);
            }
        }
        if(numberMonth < 1 || numberMonth > 12) {
            this.log.warn("Внимание не известный номер месяца");
        } else {
            Date.monthByNumber(numberMonth);
            if(this.log != null) {
                this.log.info("Задача - 'оператор выбора' выполнена!");
            }
        }
    }

    public void showTablesMultiplicationAndSum() {
        if(this.log != null) {
            this.log.info("Начинаю выполнять третье задание (циклы)");
        }
        Scanner in = new Scanner(System.in);
        int n = 4;
        if(!Input.formFile(in, this.log)) {
            System.out.print("Введите размер таблицы: ");
            n = in.nextInt();
            this.log.printf(Level.DEBUG,"Размер таблицы: %d",n);
        } else {
            try(BufferedReader reader = new BufferedReader(new FileReader("files/z3.txt"))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    n = Integer.parseInt(line);
                }
            } catch (IOException ex) {
                if(this.log != null) {
                    this.log.error("File not found",ex);
                }
            }
        }
        if(n > 0) {
            Table.showTableWhile(n,true);
            System.out.println();
            Table.showTableWhile(n,false);
            if(this.log != null) {
                this.log.info("Задача - 'циклы' выполнена!");
            }
        } else {
            this.log.warn("Размер таблицы равен, либо меньше 0");
        }
    }

    public void arrayCountElements() {
        if(this.log != null) {
            this.log.info("Начинаю выполнять четвертое задание (массивы)");
        }
        Scanner in = new Scanner(System.in);
        float[] arrElements = new float[0];
        int countNegative = 0;
        int countPositive = 0;
        int countNull = 0;
        int n = 5;
        if(!Input.formFile(in, this.log)) {
            System.out.print("Введите размер массива: ");
            n = in.nextInt();
            arrElements = new float[n];
            for(int i = 0; i < n; ++i) {
                System.out.printf("Элемент [%d]: ", i);
                arrElements[i] = in.nextFloat();
            }
            this.log.printf(Level.DEBUG,"Размер массива: %d", n);
            this.log.debug(Arrays.toString(arrElements));
        } else {
            try(BufferedReader reader = new BufferedReader(new FileReader("files/z4.txt"))) {
                String line;
                String[] tmpArray = new String[1];
                while ((line = reader.readLine()) != null) {
                    if(!line.isEmpty()) {
                        tmpArray = line.split(",",0);
                    }
                }
                arrElements = new float[tmpArray.length];
                for(int i = 0; i < tmpArray.length; ++i) {
                    arrElements[i] = Float.parseFloat(tmpArray[i]);
                }
                this.log.printf(Level.DEBUG,"Размер массива: %d",tmpArray.length);
                this.log.debug(Arrays.toString(arrElements));
            } catch (IOException ex) {
                if(this.log != null) {
                    this.log.error("File not found",ex);
                }
            }
        }
        for(float el : arrElements) {
            if(el > 0) {
                ++countPositive;
            } else if(el < 0) {
                ++countNegative;
            } else {
                ++countNull;
            }
        }
        this.log.printf(Level.DEBUG,"Положительных элементов в массиве %d",countPositive);
        this.log.printf(Level.DEBUG,"Отрицательных элементов в массиве: %d",countNegative);
        this.log.printf(Level.DEBUG,"Нулевых элементов в массиве: %d",countNull);
        if(this.log != null) {
            this.log.info("Задача - 'массив' выполнена!");
        }
    }
}