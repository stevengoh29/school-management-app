package io.stevengoh.portfolio.school_management_app.modules.roles.dtos.request;

import io.stevengoh.portfolio.school_management_app.common.enums.ActorType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateRoleDto {
    private String name;
    private String description;
    private ActorType actorType;
    private Boolean isSuperAdmin;
    private Boolean isDefaultRole;
}
