package io.stevengoh.portfolio.school_management_app.modules.institution_roles;

import io.stevengoh.portfolio.school_management_app.common.dtos.PaginatedResult;
import io.stevengoh.portfolio.school_management_app.modules.institution_roles.dtos.request.CreateInstitutionRoleDto;
import io.stevengoh.portfolio.school_management_app.modules.institution_roles.dtos.request.SearchInstitutionRoleDto;
import io.stevengoh.portfolio.school_management_app.modules.institution_roles.dtos.request.UpdateInstitutionRoleDto;
import io.stevengoh.portfolio.school_management_app.modules.institution_roles.dtos.response.DetailedResInstitutionRoleDto;
import io.stevengoh.portfolio.school_management_app.modules.institution_roles.dtos.response.SimpleResInstitutionRoleDto;
import io.stevengoh.portfolio.school_management_app.modules.institution_roles.entities.InstitutionRole;
import io.stevengoh.portfolio.school_management_app.modules.roles.dtos.request.CreateRoleDto;
import io.stevengoh.portfolio.school_management_app.modules.roles.dtos.request.SearchRoleDto;
import io.stevengoh.portfolio.school_management_app.modules.roles.dtos.request.UpdateRoleDto;
import io.stevengoh.portfolio.school_management_app.modules.roles.dtos.response.DetailedResRoleDto;
import io.stevengoh.portfolio.school_management_app.modules.roles.dtos.response.SimpleResRoleDto;
import io.stevengoh.portfolio.school_management_app.modules.roles.entities.Role;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface InstitutionRoleService {
    InstitutionRole findByUuidOrNull(UUID uuid);

    InstitutionRole findByUuidOrThrow(UUID uuid);

    PaginatedResult<SimpleResInstitutionRoleDto> getInstitutionRoles(SearchInstitutionRoleDto searchDto, Pageable pageable);

    DetailedResInstitutionRoleDto getInstitutionRoleByUuidOrNull(UUID uuid);

    DetailedResInstitutionRoleDto getInstitutionRoleByUuidOrThrow(UUID uuid);

    DetailedResInstitutionRoleDto createInstitutionRole(UUID institutionUuid, CreateInstitutionRoleDto createInstitutionRoleDto);

    DetailedResInstitutionRoleDto updateInstitutionRole(UUID uuid, UpdateInstitutionRoleDto updateInstitutionRoleDto);

    void deleteInstitutionRole(UUID uuid);
}
