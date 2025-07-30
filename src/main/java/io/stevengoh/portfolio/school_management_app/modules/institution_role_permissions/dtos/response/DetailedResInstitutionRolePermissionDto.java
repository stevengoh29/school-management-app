package io.stevengoh.portfolio.school_management_app.modules.institution_role_permissions.dtos.response;

import io.stevengoh.portfolio.school_management_app.modules.institution_roles.dtos.response.SimpleResInstitutionRoleDto;
import io.stevengoh.portfolio.school_management_app.modules.institutions.dtos.response.SimpleResInstitutionDto;
import io.stevengoh.portfolio.school_management_app.modules.permissions.dtos.response.SimpleResPermissionDto;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class DetailedResInstitutionRolePermissionDto {
    private UUID uuid;
    private SimpleResInstitutionRoleDto institutionRole;
    private SimpleResPermissionDto permission;
    private SimpleResInstitutionDto institution;
    private String createdBy;
    private String updatedBy;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}