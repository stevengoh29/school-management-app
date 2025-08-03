package io.stevengoh.portfolio.school_management_app.modules.roles.dtos.response;

import io.stevengoh.portfolio.school_management_app.common.dtos.BaseResponseMapperDto;
import io.stevengoh.portfolio.school_management_app.common.enums.ActorType;
import io.stevengoh.portfolio.school_management_app.modules.roles.enums.RoleStatus;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class SimpleResRoleDto extends BaseResponseMapperDto {
    private String name;
    private String description;
    private ActorType actorType;
    private RoleStatus status;
    private Boolean isSuperAdmin;
    private Boolean isDefaultRole;
}
