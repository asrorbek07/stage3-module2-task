package com.mjc.school.service.exception;

import java.io.Serial;

public class ResourceNotFoundException extends RuntimeException {
    //
    @Serial
    private static final long serialVersionUID = 2L;

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
