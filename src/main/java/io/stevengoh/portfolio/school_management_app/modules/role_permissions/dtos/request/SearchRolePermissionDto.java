package io.stevengoh.portfolio.school_management_app.modules.role_permissions.dtos.request;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class SearchRolePermissionDto {
    private UUID roleUuid;
}