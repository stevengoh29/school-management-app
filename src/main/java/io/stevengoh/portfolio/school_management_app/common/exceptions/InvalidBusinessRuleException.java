package io.stevengoh.portfolio.school_management_app.common.exceptions;

public class InvalidBusinessRuleException extends RuntimeException {
    public InvalidBusinessRuleException(String message) {
        super(message);
    }
}
