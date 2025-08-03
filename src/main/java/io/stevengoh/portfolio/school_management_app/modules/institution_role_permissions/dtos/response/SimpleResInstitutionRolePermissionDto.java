package io.stevengoh.portfolio.school_management_app.modules.institution_role_permissions.dtos.response;

import io.stevengoh.portfolio.school_management_app.common.dtos.WithAuditingBaseResponseMapperDto;
import io.stevengoh.portfolio.school_management_app.modules.institution_roles.dtos.response.SimpleResInstitutionRoleDto;
import io.stevengoh.portfolio.school_management_app.modules.permissions.dtos.response.SimpleResPermissionDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SimpleResInstitutionRolePermissionDto extends WithAuditingBaseResponseMapperDto {
    private SimpleResInstitutionRoleDto institutionRole;
    private SimpleResPermissionDto permission;
}