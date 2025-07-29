package io.stevengoh.portfolio.school_management_app.modules.users.dtos.request;

import io.stevengoh.portfolio.school_management_app.modules.users.enums.UserStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateUserDto {
    private String username;
    private String password;
    private UserStatus status;
    private String email;
}
