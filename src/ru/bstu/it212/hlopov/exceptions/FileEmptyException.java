package ru.bstu.it212.hlopov.exceptions;

public class FileEmptyException extends Exception {
    // Конструктор с сообщением
    public FileEmptyException(String message) {
        super(message);
    }

    // Конструктор с сообщением и причиной
    public FileEmptyException(String message, Throwable cause) {
        super(message, cause);
    }
}
