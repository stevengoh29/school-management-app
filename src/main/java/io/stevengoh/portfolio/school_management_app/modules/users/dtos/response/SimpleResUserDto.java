package io.stevengoh.portfolio.school_management_app.modules.users.dtos.response;

import io.stevengoh.portfolio.school_management_app.common.dtos.BaseResponseMapperDto;
import io.stevengoh.portfolio.school_management_app.modules.users.enums.UserStatus;
import io.stevengoh.portfolio.school_management_app.common.enums.ActorType;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class SimpleResUserDto extends BaseResponseMapperDto {
    private String username;
    private String email;
    private UserStatus status;
    private ActorType type;
}
