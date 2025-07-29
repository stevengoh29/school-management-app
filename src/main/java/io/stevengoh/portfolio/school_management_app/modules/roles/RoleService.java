package io.stevengoh.portfolio.school_management_app.modules.roles;

import io.stevengoh.portfolio.school_management_app.common.dtos.PaginatedResult;
import io.stevengoh.portfolio.school_management_app.modules.roles.dtos.request.CreateRoleDto;
import io.stevengoh.portfolio.school_management_app.modules.roles.dtos.request.SearchRoleDto;
import io.stevengoh.portfolio.school_management_app.modules.roles.dtos.request.UpdateRoleDto;
import io.stevengoh.portfolio.school_management_app.modules.roles.dtos.response.DetailedResRoleDto;
import io.stevengoh.portfolio.school_management_app.modules.roles.dtos.response.SimpleResRoleDto;
import io.stevengoh.portfolio.school_management_app.modules.roles.entities.Role;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface RoleService {
    Role findByUuidOrNull(UUID uuid);

    Role findByUuidOrThrow(UUID uuid);

    PaginatedResult<SimpleResRoleDto> getRoles(SearchRoleDto searchDto, Pageable pageable);

    DetailedResRoleDto getRoleByUuidOrNull(UUID uuid);

    DetailedResRoleDto getRoleByUuidOrThrow(UUID uuid);

    DetailedResRoleDto createRole(CreateRoleDto createRoleDto);

    DetailedResRoleDto updateRole(UUID uuid, UpdateRoleDto updateRoleDto);

    void deleteRole(UUID uuid);
}
