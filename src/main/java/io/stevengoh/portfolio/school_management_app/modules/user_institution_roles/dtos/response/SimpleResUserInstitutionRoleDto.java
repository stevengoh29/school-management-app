package io.stevengoh.portfolio.school_management_app.modules.user_institution_roles.dtos.response;

import io.stevengoh.portfolio.school_management_app.modules.institution_roles.dtos.response.SimpleResInstitutionRoleDto;
import io.stevengoh.portfolio.school_management_app.modules.institutions.dtos.response.SimpleResInstitutionDto;
import io.stevengoh.portfolio.school_management_app.modules.users.dtos.response.SimpleResUserDto;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class SimpleResUserInstitutionRoleDto {
    private UUID uuid;
    private String createdBy;
    private String updatedBy;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private SimpleResInstitutionRoleDto institutionRole;
    private SimpleResUserDto user;
    private SimpleResInstitutionDto institution;
}
