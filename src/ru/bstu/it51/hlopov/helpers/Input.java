package ru.bstu.it51.hlopov.helpers;

import java.util.Scanner;
import org.apache.logging.log4j.Logger;

public class Input {
    /**
     * <p>Метод определяет откуда будут считаны данные из файла либо из консоли</p>
     * @param in объект потока ввода
     * @param log объект логера
     * @throws java.util.InputMismatchException когда введена строка не true и не false
     * @see ru.bstu.it51.hlopov.helpers.Input
     * @return true - считываем из файла, false - считываем из консоли
     */
    public static boolean formFile(Scanner in, Logger log) {
        boolean result;
        System.out.print("Считывать параметры (true - файл; false - консоль): ");
        try {
            result = in.nextBoolean();
        } catch (java.util.InputMismatchException ex) {
            log.fatal("fatal",ex);
            result = true;
        }
        return result;
    }
}
