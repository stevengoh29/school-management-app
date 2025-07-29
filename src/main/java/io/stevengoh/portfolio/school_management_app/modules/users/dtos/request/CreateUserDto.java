package io.stevengoh.portfolio.school_management_app.modules.users.dtos.request;

import io.stevengoh.portfolio.school_management_app.common.enums.ActorType;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CreateUserDto {
    private String username;
    private String password;
    private String email;
    private ActorType type;
    private UUID institutionUuid;
}
