package com.example.redcollar1.exception;

public class NotFoundEntityException extends IllegalArgumentException{
    public NotFoundEntityException(Long id) {
        super("Not found entity by id = " + id);
    }
}
