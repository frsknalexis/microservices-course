package com.dev.microservices.core.users.exception;

public class NotFoundException extends Exception {

    private String mensaje;

    public NotFoundException() {
        super();
    }

    public NotFoundException(String mensaje) {
        super(mensaje);
        this.mensaje = mensaje;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}