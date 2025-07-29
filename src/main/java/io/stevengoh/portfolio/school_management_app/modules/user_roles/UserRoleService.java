package io.stevengoh.portfolio.school_management_app.modules.user_roles;

import io.stevengoh.portfolio.school_management_app.common.dtos.PaginatedResult;
import io.stevengoh.portfolio.school_management_app.modules.user_roles.dtos.request.CreateUserRoleDto;
import io.stevengoh.portfolio.school_management_app.modules.user_roles.dtos.request.DeleteUserRoleDto;
import io.stevengoh.portfolio.school_management_app.modules.user_roles.dtos.request.SearchUserRoleDto;
import io.stevengoh.portfolio.school_management_app.modules.user_roles.dtos.response.DetailedResUserRoleDto;
import io.stevengoh.portfolio.school_management_app.modules.user_roles.dtos.response.SimpleResUserRoleDto;
import io.stevengoh.portfolio.school_management_app.modules.user_roles.entities.UserRole;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface UserRoleService {
    UserRole findByUuidOrNull(UUID uuid);

    UserRole findByUuidOrThrow(UUID uuid);

    PaginatedResult<SimpleResUserRoleDto> getUserRoles(SearchUserRoleDto searchDto, Pageable pageable);

    DetailedResUserRoleDto getUserRoleByUuidOrNull(UUID uuid);

    DetailedResUserRoleDto getUserRoleByUuidOrThrow(UUID uuid);

    DetailedResUserRoleDto createUserRole(CreateUserRoleDto createUserRoleDto);

    List<DetailedResUserRoleDto> createBulkUserRole(List<CreateUserRoleDto> createUserRoleDtos);

    void deleteUserRole(UUID uuid);

    void deleteBulkUserRole(DeleteUserRoleDto request);
}