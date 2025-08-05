package io.stevengoh.portfolio.school_management_app.common.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class ForbiddenException extends RuntimeException {
    public ForbiddenException() {
        super("You are forbidden to access this resource.");
    }

    public ForbiddenException(String message) {
        super(message);
    }
}
