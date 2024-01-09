package ru.efomenko.exception;

public class TaskValidFailException extends RuntimeException {
    public TaskValidFailException(String message) {
        super(message);
    }
}
