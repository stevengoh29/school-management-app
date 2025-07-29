package io.stevengoh.portfolio.school_management_app.common.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BaseResponse<T> {
    private String statusCode = "SUCCESS";
    private String message = "success";
    private T data;

    public BaseResponse(T data) {
        this.data = data;
    }
}
