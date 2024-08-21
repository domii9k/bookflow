package edu.api.bookflow.Exceptions;

public class NotFoundObject extends RuntimeException{
    private static final Long id = null;

    public NotFoundObject(Long id) {
        super("Registro de ID "+id+" não encontrado!");
    }
}
