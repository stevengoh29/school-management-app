package io.stevengoh.portfolio.school_management_app.modules.roles.dtos.request;

import io.stevengoh.portfolio.school_management_app.common.enums.ActorType;
import io.stevengoh.portfolio.school_management_app.modules.roles.enums.RoleStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchRoleDto {
    private String name;
    private String description;
    private ActorType actorType;
    private RoleStatus status;
}
