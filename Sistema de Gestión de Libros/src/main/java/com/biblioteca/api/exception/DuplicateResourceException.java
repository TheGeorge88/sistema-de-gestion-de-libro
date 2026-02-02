package com.biblioteca.api.exception;

public class DuplicateResourceException extends RuntimeException {

    public DuplicateResourceException(String mensaje) {
        super(mensaje);
    }

    public DuplicateResourceException(String campo, String valor) {
        super(String.format("Ya existe un registro con %s: %s", campo, valor));
    }
}
