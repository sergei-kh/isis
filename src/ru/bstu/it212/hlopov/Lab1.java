package ru.bstu.it212.hlopov;

import java.util.Arrays;
import java.util.Scanner;
import java.io.*;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Logger;
import ru.bstu.it212.hlopov.helpers.Input;
import ru.bstu.it212.hlopov.helpers.Date;
import ru.bstu.it212.hlopov.helpers.Table;

public class Lab1 {

    private Logger log = null;

    public Lab1(Logger loggerArg) {
        this.log = loggerArg;
    }

    public Lab1() {
    }

    public void run() {
        if (this.log != null) {
            this.log.info("Запуск первой лабораторной работы");
        }
        this.isBrickSuitable();
        this.showNameMonth();
        this.showTablesMultiplicationAndSum();
        this.arrayCountElements();
        if (this.log != null) {
            this.log.info("Лабораторная работа закончена");
        }
    }

    /**
     * <p>Проверяет, проходит ли кирпич в отверстие.</p>
     */
    public void isBrickSuitable() {
        Scanner in = new Scanner(System.in);
        if (this.log != null) {
            this.log.info("Начинаю выполнять первое задание (условные выражения)");
        }
        float a = 0;
        float b = 0;
        float x = 0;
        float y = 0;
        float z = 0;
        boolean fileNotFound = false;
        if (!Input.formFile(in, this.log)) {
            System.out.println("Введите (A, B) отверстия");
            System.out.print("A = ");
            a = in.nextFloat();
            System.out.print("B = ");
            b = in.nextFloat();
            System.out.println("Введите (X, Y, Z) кирпича");
            System.out.print("X = ");
            x = in.nextFloat();
            System.out.print("Y = ");
            y = in.nextFloat();
            System.out.print("Z = ");
            z = in.nextFloat();
        } else {
            File file = new File("files/z1.txt");
            if (file.length() == 0 && this.log != null) {
                this.log.error("Файл с параметрами пуст (первое задание)");
            }
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                byte lineCount = 0;
                while ((line = reader.readLine()) != null) {
                    if (!line.isEmpty()) {
                        String[] tmpArray = line.split(",", 0);
                        ++lineCount;
                        if (lineCount == 1) {
                            a = Float.parseFloat(tmpArray[0]);
                            b = Float.parseFloat(tmpArray[1]);
                        } else if (lineCount == 2) {
                            x = Float.parseFloat(tmpArray[0]);
                            y = Float.parseFloat(tmpArray[1]);
                            z = Float.parseFloat(tmpArray[2]);
                        }
                    }
                }
            } catch (IOException ex) {
                if (this.log != null) {
                    fileNotFound = true;
                    this.log.fatal("Упс.. Не удалось найти файл с параметрами (первое задание)", ex);
                }
            }
        }
        if (x != 0 && y != 0 && z != 0) {
            if (!fileNotFound) {
                if (this.log != null) {
                    this.log.printf(Level.DEBUG, "Размеры отверстия (A и B) %f, %f", a, b);
                    this.log.printf(Level.DEBUG, "Размеры кирпича (X, Y, Z) %f, %f, %f", x, y, z);
                } else {
                    System.out.printf("Размеры отверстия (A и B) %f, %f\n", a, b);
                    System.out.printf("Размеры кирпича (X, Y, Z) %f, %f, %f\n", x, y, z);
                }
                if (b >= y && a >= z || b >= z && a >= y || b >= x && a >= y || b >= y && a >= x
                        || a >= x && b >= z || a >= z && b >= x) {
                    if (this.log != null) {
                        this.log.debug("Кирпич проходит");
                    } else {
                        System.out.println("Кирпич проходит");
                    }
                } else {
                    if (this.log != null) {
                        this.log.debug("Кирпич не проходит");
                    } else {
                        System.out.println("Кирпич не проходит");
                    }
                }
                if (this.log != null) {
                    this.log.info("Задача - 'условные выражения' выполнена!");
                } else {
                    System.out.println("Задача - 'условные выражения' выполнена!");
                }
            }
        } else if (this.log != null) {
            this.log.warn("Внимание размеры кирпича были равны нулю или не заданы");
        }
    }

    /**
     * <p>Выводит название месяца по номеру</p>
     */
    public void showNameMonth() {
        if (this.log != null) {
            this.log.info("Начинаю выполнять второе задание (оператор выбора)");
        } else {
            System.out.println("Начинаю выполнять второе задание (оператор выбора)");
        }
        Scanner in = new Scanner(System.in);
        byte numberMonth = 0;
        if (!Input.formFile(in, this.log)) {
            System.out.print("Введите номер месяца: ");
            numberMonth = in.nextByte();
            if (this.log != null) {
                this.log.printf(Level.DEBUG, "Номер месяца: %d", numberMonth);
            }
        } else {
            File file = new File("files/z2.txt");
            if (file.length() == 0 && this.log != null) {
                this.log.error("Файл с параметрами пуст (второе задание)");
            }
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    numberMonth = Byte.parseByte(line);
                }
            } catch (IOException ex) {
                if (this.log != null) {
                    this.log.fatal("Упс.. Не удалось найти файл с параметрами (второе задание)", ex);
                }
            }
        }
        if (numberMonth < 1 || numberMonth > 12) {
            if (this.log != null) {
                this.log.warn("Внимание не известный номер месяца");
            } else {
                System.out.println("Внимание не известный номер месяца");
            }
        } else {
            Date.monthByNumber(numberMonth);
            if (this.log != null) {
                this.log.info("Задача - 'оператор выбора' выполнена!");
            } else {
                System.out.println("Задача - 'оператор выбора' выполнена!");
            }
        }
    }

    /**
     * <p>Печатает таблицу умножения и сложения натуральных чисел в десятичной системе счисления</p>
     */
    public void showTablesMultiplicationAndSum() {
        if (this.log != null) {
            this.log.info("Начинаю выполнять третье задание (циклы)");
        } else {
            System.out.println("Начинаю выполнять третье задание (циклы)");
        }
        Scanner in = new Scanner(System.in);
        int n = 4;
        if (!Input.formFile(in, this.log)) {
            System.out.print("Введите размер таблицы: ");
            n = in.nextInt();
            if (this.log != null) {
                this.log.printf(Level.DEBUG, "Размер таблицы: %d", n);
            }
        } else {
            File file = new File("files/z3.txt");
            if (file.length() == 0 && this.log != null) {
                this.log.error("Файл с параметрами пуст (третье задание)");
            }
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    n = Integer.parseInt(line);
                }
            } catch (IOException ex) {
                if (this.log != null) {
                    this.log.fatal("Упс.. Не удалось найти файл с параметрами (третье задание)");
                }
            }
        }
        if (n > 0) {
            Table.showTableWhile(n, true);
            System.out.println();
            Table.showTableWhile(n, false);
            if (this.log != null) {
                this.log.info("Задача - 'циклы' выполнена!");
            } else {
                System.out.println("Задача - 'циклы' выполнена!");
            }
        } else {
            if (this.log != null) {
                this.log.warn("Размер таблицы равен, либо меньше 0");
            } else {
                System.out.println("Размер таблицы равен, либо меньше 0");
            }
        }
    }

    /**
     * <p>Подсчитывает сколько в массиве отрицательных, положительных и нулевых элементов.</p>
     */
    public void arrayCountElements() {
        if (this.log != null) {
            this.log.info("Начинаю выполнять четвертое задание (массивы)");
        } else {
            System.out.println("Начинаю выполнять четвертое задание (массивы)");
        }
        Scanner in = new Scanner(System.in);
        float[] arrElements = new float[0];
        int countNegative = 0;
        int countPositive = 0;
        int countNull = 0;
        int n = 5;
        if (!Input.formFile(in, this.log)) {
            System.out.print("Введите размер массива: ");
            n = in.nextInt();
            arrElements = new float[n];
            for (int i = 0; i < n; ++i) {
                System.out.printf("Элемент [%d]: ", i);
                arrElements[i] = in.nextFloat();
            }
            if (this.log != null) {
                this.log.printf(Level.DEBUG, "Размер массива: %d", n);
                this.log.debug(Arrays.toString(arrElements));
            } else {
                System.out.printf("Размер массива: %d", n);
            }
        } else {
            File file = new File("files/z4.txt");
            if (file.length() == 0 && this.log != null) {
                this.log.error("Файл с параметрами пуст (четвертое задание)");
            }
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                String[] tmpArray = new String[1];
                while ((line = reader.readLine()) != null) {
                    if (!line.isEmpty()) {
                        tmpArray = line.split(",", 0);
                    }
                }
                arrElements = new float[tmpArray.length];
                for (int i = 0; i < tmpArray.length; ++i) {
                    arrElements[i] = Float.parseFloat(tmpArray[i]);
                }
                if (this.log != null) {
                    this.log.printf(Level.DEBUG, "Размер массива: %d", tmpArray.length);
                    this.log.debug(Arrays.toString(arrElements));
                }
            } catch (IOException ex) {
                if (this.log != null) {
                    this.log.fatal("Упс.. Не удалось найти файл с параметрами (четвертое задание)");
                }
            }
        }
        for (float el : arrElements) {
            if (el > 0) {
                ++countPositive;
            } else if (el < 0) {
                ++countNegative;
            } else {
                ++countNull;
            }
        }
        if (this.log != null) {
            this.log.printf(Level.DEBUG, "Положительных элементов в массиве %d", countPositive);
            this.log.printf(Level.DEBUG, "Отрицательных элементов в массиве: %d", countNegative);
            this.log.printf(Level.DEBUG, "Нулевых элементов в массиве: %d", countNull);
            this.log.info("Задача - 'массив' выполнена!");
        } else {
            System.out.printf("Положительных элементов в массиве %d\n", countPositive);
            System.out.printf("Отрицательных элементов в массиве: %d\n", countNegative);
            System.out.printf("Нулевых элементов в массиве: %d\n", countNull);
            System.out.println("Задача - 'массив' выполнена!");
        }
    }
}