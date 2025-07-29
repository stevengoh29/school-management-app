package io.stevengoh.portfolio.school_management_app.modules.role_permissions;

import io.stevengoh.portfolio.school_management_app.common.dtos.PaginatedResult;
import io.stevengoh.portfolio.school_management_app.modules.role_permissions.dtos.request.CreateRolePermissionDto;
import io.stevengoh.portfolio.school_management_app.modules.role_permissions.dtos.request.DeleteBulkRolePermissionDto;
import io.stevengoh.portfolio.school_management_app.modules.role_permissions.dtos.request.SearchRolePermissionDto;
import io.stevengoh.portfolio.school_management_app.modules.role_permissions.dtos.response.DetailedResRolePermissionDto;
import io.stevengoh.portfolio.school_management_app.modules.role_permissions.dtos.response.SimpleResRolePermissionDto;
import io.stevengoh.portfolio.school_management_app.modules.role_permissions.entities.RolePermission;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface RolePermissionService {
    RolePermission findByUuidOrNull(UUID uuid);

    RolePermission findByUuidOrThrow(UUID uuid);

    PaginatedResult<SimpleResRolePermissionDto> getRolePermissions(SearchRolePermissionDto searchRolePermissionDto, Pageable pageable);

    DetailedResRolePermissionDto getRolePermissionByUuidOrNull(UUID uuid);

    DetailedResRolePermissionDto getRolePermissionByUuidOrThrow(UUID uuid);

    DetailedResRolePermissionDto createRolePermission(CreateRolePermissionDto request);

    List<DetailedResRolePermissionDto> createBulkRolePermissions(List<CreateRolePermissionDto> request);

    void deleteRolePermission(UUID uuid);

    void deleteBulkRolePermissions(DeleteBulkRolePermissionDto deleteBulkRolePermissionDto);
}