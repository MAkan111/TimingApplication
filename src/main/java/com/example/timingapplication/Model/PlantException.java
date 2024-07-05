package com.example.timingapplication.Model;

public class PlantException extends RuntimeException {
    public PlantException() {
    }

    public PlantException(String message) {
        super(message);
    }

    public PlantException(String message, Throwable cause) {
        super(message, cause);
    }

    public PlantException(Throwable cause) {
        super(cause);
    }
}
