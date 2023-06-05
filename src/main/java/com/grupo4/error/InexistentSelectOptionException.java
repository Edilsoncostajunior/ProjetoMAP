package com.grupo4.error;

public class InexistentSelectOptionException extends Exception {
    private static final String MESSAGE = "Opção selecionada não existe";

    public InexistentSelectOptionException() {
        super(MESSAGE);
    }
}
