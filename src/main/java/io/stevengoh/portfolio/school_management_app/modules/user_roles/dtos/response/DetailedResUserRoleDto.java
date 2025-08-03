package io.stevengoh.portfolio.school_management_app.modules.user_roles.dtos.response;

import io.stevengoh.portfolio.school_management_app.common.dtos.WithAuditingBaseResponseMapperDto;
import io.stevengoh.portfolio.school_management_app.modules.institutions.dtos.response.SimpleResInstitutionDto;
import io.stevengoh.portfolio.school_management_app.modules.roles.dtos.response.SimpleResRoleDto;
import io.stevengoh.portfolio.school_management_app.modules.users.dtos.response.SimpleResUserDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DetailedResUserRoleDto extends WithAuditingBaseResponseMapperDto {
    private SimpleResUserDto user;
    private SimpleResRoleDto role;
    private SimpleResInstitutionDto institution;
}
