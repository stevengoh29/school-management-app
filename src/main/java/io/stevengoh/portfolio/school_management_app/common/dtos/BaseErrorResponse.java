package io.stevengoh.portfolio.school_management_app.common.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BaseErrorResponse<T> {
    private String errorCode;
    private String message;
    private T data;

    public BaseErrorResponse(String errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }

    public BaseErrorResponse(String message) {
        this.message = message;
    }
}
