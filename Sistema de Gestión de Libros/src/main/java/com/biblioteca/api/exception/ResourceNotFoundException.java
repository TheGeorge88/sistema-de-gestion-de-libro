package com.biblioteca.api.exception;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String mensaje) {
        super(mensaje);
    }

    public ResourceNotFoundException(String recurso, Long id) {
        super(String.format("%s con ID %d no encontrado", recurso, id));
    }
}
