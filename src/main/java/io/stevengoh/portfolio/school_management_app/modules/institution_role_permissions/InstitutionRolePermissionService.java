package io.stevengoh.portfolio.school_management_app.modules.institution_role_permissions;

import io.stevengoh.portfolio.school_management_app.common.dtos.PaginatedResult;
import io.stevengoh.portfolio.school_management_app.modules.institution_role_permissions.dtos.request.CreateInstitutionRolePermissionDto;
import io.stevengoh.portfolio.school_management_app.modules.institution_role_permissions.dtos.request.DeleteInstitutionRolePermissionDto;
import io.stevengoh.portfolio.school_management_app.modules.institution_role_permissions.dtos.request.SearchInstitutionRolePermissionDto;
import io.stevengoh.portfolio.school_management_app.modules.institution_role_permissions.dtos.response.DetailedResInstitutionRolePermissionDto;
import io.stevengoh.portfolio.school_management_app.modules.institution_role_permissions.dtos.response.SimpleResInstitutionRolePermissionDto;
import io.stevengoh.portfolio.school_management_app.modules.institution_role_permissions.entities.InstitutionRolePermission;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface InstitutionRolePermissionService {
    InstitutionRolePermission findByUuidOrNull(UUID uuid);

    InstitutionRolePermission findByUuidOrThrow(UUID uuid);

    PaginatedResult<SimpleResInstitutionRolePermissionDto> getInstitutionRolePermissions(SearchInstitutionRolePermissionDto searchDto, Pageable pageable);

    DetailedResInstitutionRolePermissionDto getInstitutionRolePermissionByUuidOrNull(UUID uuid);

    DetailedResInstitutionRolePermissionDto getInstitutionRolePermissionByUuidOrThrow(UUID uuid);

    DetailedResInstitutionRolePermissionDto createInstitutionRolePermission(UUID institutionUuid, CreateInstitutionRolePermissionDto createInstitutionRolePermissionDto);

    List<DetailedResInstitutionRolePermissionDto> createBulkInstitutionRolePermission(UUID institutionUuid, List<CreateInstitutionRolePermissionDto> createInstitutionRolePermissionDtos);

    void deleteInstitutionRolePermission(UUID uuid);

    void deleteBulkInstitutionRolePermission(DeleteInstitutionRolePermissionDto request);
}