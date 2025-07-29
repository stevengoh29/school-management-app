package io.stevengoh.portfolio.school_management_app.modules.users.dtos.response;

import io.stevengoh.portfolio.school_management_app.modules.users.enums.UserStatus;
import io.stevengoh.portfolio.school_management_app.common.enums.ActorType;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class SimpleResUserDto {
    private UUID uuid;
    private String username;
    private String email;
    private UserStatus status;
    private ActorType type;
//    private Institution institution;
}
