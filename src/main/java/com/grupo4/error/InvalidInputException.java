package com.grupo4.error;

public class InvalidInputException extends Exception {
    private static final String MESSAGE = "Formato informado inválido!";

    public InvalidInputException() {
        super(MESSAGE);
    }
}
