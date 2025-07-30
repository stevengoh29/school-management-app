package io.stevengoh.portfolio.school_management_app.modules.user_institution_roles;

import io.stevengoh.portfolio.school_management_app.common.dtos.PaginatedResult;
import io.stevengoh.portfolio.school_management_app.modules.user_institution_roles.dtos.request.CreateUserInstitutionRoleDto;
import io.stevengoh.portfolio.school_management_app.modules.user_institution_roles.dtos.request.DeleteUserInstitutionRoleDto;
import io.stevengoh.portfolio.school_management_app.modules.user_institution_roles.dtos.request.SearchUserInstitutionRoleDto;
import io.stevengoh.portfolio.school_management_app.modules.user_institution_roles.dtos.response.DetailedResUserInstitutionRoleDto;
import io.stevengoh.portfolio.school_management_app.modules.user_institution_roles.dtos.response.SimpleResUserInstitutionRoleDto;
import io.stevengoh.portfolio.school_management_app.modules.user_institution_roles.entities.UserInstitutionRole;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface UserInstitutionRoleService {
    UserInstitutionRole findByUuidOrNull(UUID uuid);

    UserInstitutionRole findByUuidOrThrow(UUID uuid);

    PaginatedResult<SimpleResUserInstitutionRoleDto> getUserInstitutionRoles(SearchUserInstitutionRoleDto searchDto, Pageable pageable);

    DetailedResUserInstitutionRoleDto getUserInstitutionRoleByUuidOrNull(UUID uuid);

    DetailedResUserInstitutionRoleDto getUserInstitutionRoleByUuidOrThrow(UUID uuid);

    DetailedResUserInstitutionRoleDto createUserInstitutionRole(UUID uuid, CreateUserInstitutionRoleDto createUserInstitutionRoleDto);

    List<DetailedResUserInstitutionRoleDto> createBulkUserInstitutionRole(UUID uuid, List<CreateUserInstitutionRoleDto> createUserInstitutionRoleDtos);

    void deleteUserInstitutionRole(UUID uuid);

    void deleteBulkUserInstitutionRole(DeleteUserInstitutionRoleDto request);
}