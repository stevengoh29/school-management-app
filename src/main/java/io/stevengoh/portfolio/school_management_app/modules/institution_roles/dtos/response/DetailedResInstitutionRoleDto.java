package io.stevengoh.portfolio.school_management_app.modules.institution_roles.dtos.response;

import io.stevengoh.portfolio.school_management_app.common.enums.ActorType;
import io.stevengoh.portfolio.school_management_app.modules.institutions.dtos.response.SimpleResInstitutionDto;
import io.stevengoh.portfolio.school_management_app.modules.roles.dtos.response.SimpleResRoleDto;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class DetailedResInstitutionRoleDto {
    private UUID uuid;
    private String name;
    private String description;
    private ActorType actorType;
    private String createdBy;
    private String updatedBy;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private SimpleResInstitutionDto institution;
    private SimpleResRoleDto baseRole;
}
