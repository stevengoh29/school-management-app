package io.stevengoh.portfolio.school_management_app.modules.users.dtos.response;

import io.stevengoh.portfolio.school_management_app.common.dtos.WithAuditingBaseResponseMapperDto;
import io.stevengoh.portfolio.school_management_app.modules.institutions.dtos.response.SimpleResInstitutionDto;
import io.stevengoh.portfolio.school_management_app.modules.user_roles.dtos.response.SimpleResUserRoleDto;
import io.stevengoh.portfolio.school_management_app.modules.users.enums.UserStatus;
import io.stevengoh.portfolio.school_management_app.common.enums.ActorType;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class DetailedResUserDto extends WithAuditingBaseResponseMapperDto {
    private String username;
    private String email;
    private UserStatus status;
    private ActorType type;
    private SimpleResInstitutionDto institution;
    private List<SimpleResUserRoleDto> userRoles;
}
