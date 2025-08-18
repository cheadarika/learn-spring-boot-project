package org.springclass.springclassproject.exception;

public class ResourceNotFoundException extends RuntimeException {
    private final String error;
    private final String message;

    public ResourceNotFoundException(final String error, final String message) {
        super(message);
        this.error = error;
        this.message = message;
    }

    public String getError() { return error; }

    @Override
    public String getMessage() { return message; }
}
