package io.stevengoh.portfolio.school_management_app.modules.role_permissions.dtos.response;

import io.stevengoh.portfolio.school_management_app.common.dtos.WithAuditingBaseResponseMapperDto;
import io.stevengoh.portfolio.school_management_app.modules.permissions.dtos.response.SimpleResPermissionDto;
import io.stevengoh.portfolio.school_management_app.modules.roles.dtos.response.SimpleResRoleDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SimpleResRolePermissionDto extends WithAuditingBaseResponseMapperDto {
    private SimpleResRoleDto role;
    private SimpleResPermissionDto permission;
}
