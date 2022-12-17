package ru.itmo.cloudtechonlogies.exception;

public class NotFoundElementException extends RuntimeException {
    public NotFoundElementException(String message) {
        super(message);
    }
}