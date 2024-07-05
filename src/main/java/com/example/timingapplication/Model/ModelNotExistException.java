package com.example.timingapplication.Model;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ModelNotExistException extends RuntimeException {
    public ModelNotExistException(String message) {
        super(message);
    }

    public ModelNotExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public ModelNotExistException(Throwable cause) {
        super(cause);
    }
}
