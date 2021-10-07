package com.example.redcollar1.exception;


public class NotFoundEntityException extends RuntimeException {
    public NotFoundEntityException(Long id) {
        super("Not found entity by id = " + id);
    }
}
