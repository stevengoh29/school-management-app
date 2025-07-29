package io.stevengoh.portfolio.school_management_app.modules.role_permissions.dtos.request;

import lombok.Getter;

import java.util.List;
import java.util.UUID;

@Getter
public class DeleteBulkRolePermissionDto {
    private List<UUID> rolePermissionUuids;
}
