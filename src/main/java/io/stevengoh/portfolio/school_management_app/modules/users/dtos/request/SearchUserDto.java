package io.stevengoh.portfolio.school_management_app.modules.users.dtos.request;

import io.stevengoh.portfolio.school_management_app.modules.users.enums.UserStatus;
import io.stevengoh.portfolio.school_management_app.common.enums.ActorType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchUserDto {
    private String username;
    private String email;
    private UserStatus status;
    private ActorType type;
}
