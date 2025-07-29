package io.stevengoh.portfolio.school_management_app.common.exceptions;

public class MissingBodyRequestException extends RuntimeException {
    public MissingBodyRequestException() {
        super();
    }

    public MissingBodyRequestException(String message) {
        super(message);
    }
}
