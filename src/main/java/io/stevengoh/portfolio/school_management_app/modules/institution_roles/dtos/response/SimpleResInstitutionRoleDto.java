package io.stevengoh.portfolio.school_management_app.modules.institution_roles.dtos.response;

import io.stevengoh.portfolio.school_management_app.common.enums.ActorType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class SimpleResInstitutionRoleDto {
    private UUID uuid;
    private String name;
    private String description;
    private ActorType actorType;
    private String createdBy;
    private String updatedBy;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
