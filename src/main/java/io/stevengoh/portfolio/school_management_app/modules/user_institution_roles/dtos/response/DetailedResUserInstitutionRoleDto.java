package io.stevengoh.portfolio.school_management_app.modules.user_institution_roles.dtos.response;

import io.stevengoh.portfolio.school_management_app.common.dtos.WithAuditingBaseResponseMapperDto;
import io.stevengoh.portfolio.school_management_app.modules.institution_roles.dtos.response.SimpleResInstitutionRoleDto;
import io.stevengoh.portfolio.school_management_app.modules.institutions.dtos.response.SimpleResInstitutionDto;
import io.stevengoh.portfolio.school_management_app.modules.users.dtos.response.SimpleResUserDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DetailedResUserInstitutionRoleDto extends WithAuditingBaseResponseMapperDto {
    private SimpleResInstitutionRoleDto institutionRole;
    private SimpleResUserDto user;
    private SimpleResInstitutionDto institution;
}
