package io.stevengoh.portfolio.school_management_app.modules.user_roles.dtos.request;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CreateUserRoleDto {
    private UUID userUuid;
    private UUID roleUuid;
    private UUID institutionUuid;
}
