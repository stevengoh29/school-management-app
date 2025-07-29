package io.stevengoh.portfolio.school_management_app.modules.user_roles.dtos.response;

import io.stevengoh.portfolio.school_management_app.modules.institutions.dtos.response.SimpleResInstitutionDto;
import io.stevengoh.portfolio.school_management_app.modules.institutions.entities.Institution;
import io.stevengoh.portfolio.school_management_app.modules.roles.dtos.response.SimpleResRoleDto;
import io.stevengoh.portfolio.school_management_app.modules.roles.entities.Role;
import io.stevengoh.portfolio.school_management_app.modules.users.dtos.response.SimpleResUserDto;
import io.stevengoh.portfolio.school_management_app.modules.users.entities.User;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class SimpleResUserRoleDto {
    private UUID uuid;
    private SimpleResUserDto user;
    private SimpleResRoleDto role;
    private SimpleResInstitutionDto institution;
    private String createdBy;
    private String updatedBy;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
