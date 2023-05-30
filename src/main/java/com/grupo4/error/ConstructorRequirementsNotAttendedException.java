package com.grupo4.error;

public class ConstructorRequirementsNotAttendedException extends Exception {
    private final static String ERROR_MESSAGE = "%s levou o construtor a não ser criado";

    public ConstructorRequirementsNotAttendedException(String message) {
        super(String.format(ERROR_MESSAGE, message));
    }
}
