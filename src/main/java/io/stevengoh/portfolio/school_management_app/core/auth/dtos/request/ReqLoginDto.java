package io.stevengoh.portfolio.school_management_app.core.auth.dtos.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReqLoginDto {
    private String username;
    private String password;
}
