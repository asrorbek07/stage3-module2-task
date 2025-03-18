package com.mjc.school.repository.exception;

import java.io.Serial;

public class ResourceFileNotFoundException extends RuntimeException {
    //
    @Serial
    private static final long serialVersionUID = 4L;

    public ResourceFileNotFoundException(String message) {
        //
        super(message);
    }
}
