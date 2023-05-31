package com.grupo4.error;

public class NullReadableValuesToWriteException extends Exception {
    public static final String MESSAGE = "Valores passados para a escrita tem retorno null";

    public NullReadableValuesToWriteException() {
        super(MESSAGE);
    }
}
