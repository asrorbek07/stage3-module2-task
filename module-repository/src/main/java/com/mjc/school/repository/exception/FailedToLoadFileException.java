package com.mjc.school.repository.exception;

import java.io.Serial;

public class FailedToLoadFileException extends RuntimeException {
    //
    @Serial
    private static final long serialVersionUID = 3L;

    public FailedToLoadFileException(String message) {
        //
        super(message);
    }
}
