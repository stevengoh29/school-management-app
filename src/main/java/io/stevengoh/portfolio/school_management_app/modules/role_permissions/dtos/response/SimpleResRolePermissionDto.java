package io.stevengoh.portfolio.school_management_app.modules.role_permissions.dtos.response;

import io.stevengoh.portfolio.school_management_app.modules.permissions.dtos.response.SimpleResPermissionDto;
import io.stevengoh.portfolio.school_management_app.modules.roles.dtos.response.SimpleResRoleDto;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class SimpleResRolePermissionDto {
    private UUID uuid;
    private SimpleResRoleDto role;
    private SimpleResPermissionDto permission;
    private String createdBy;
    private String updatedBy;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
