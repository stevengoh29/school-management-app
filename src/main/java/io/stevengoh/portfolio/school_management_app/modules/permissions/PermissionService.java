package io.stevengoh.portfolio.school_management_app.modules.permissions;

import io.stevengoh.portfolio.school_management_app.common.dtos.PaginatedResult;
import io.stevengoh.portfolio.school_management_app.modules.permissions.dtos.response.SimpleResPermissionDto;
import io.stevengoh.portfolio.school_management_app.modules.permissions.dtos.request.CreatePermissionDto;
import io.stevengoh.portfolio.school_management_app.modules.permissions.dtos.request.SearchPermissionDto;
import io.stevengoh.portfolio.school_management_app.modules.permissions.dtos.request.UpdatePermissionDto;
import io.stevengoh.portfolio.school_management_app.modules.permissions.dtos.response.DetailedResPermissionDto;
import io.stevengoh.portfolio.school_management_app.modules.permissions.entities.Permission;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface PermissionService {
    Permission findByUuidOrNull(UUID uuid);

    Permission findByUuidOrThrow(UUID uuid);

    PaginatedResult<SimpleResPermissionDto> getPermissions(SearchPermissionDto searchDto, Pageable pageable);

    DetailedResPermissionDto getPermissionByUuidOrNull(UUID uuid);

    DetailedResPermissionDto getPermissionByUuidOrThrow(UUID uuid);

    DetailedResPermissionDto createPermission(CreatePermissionDto createPermissionDto);

    DetailedResPermissionDto updatePermission(UUID uuid, UpdatePermissionDto updatePermissionDto);

    void deletePermission(UUID uuid);
}