package io.stevengoh.portfolio.school_management_app.modules.institution_roles.dtos.request;

import io.stevengoh.portfolio.school_management_app.common.enums.ActorType;
import io.stevengoh.portfolio.school_management_app.common.interfaces.InstitutionAware;
import io.stevengoh.portfolio.school_management_app.modules.institutions.entities.Institution;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CreateInstitutionRoleDto implements InstitutionAware {
    private String name;
    private String description;
    private ActorType actorType;
    private UUID roleUuid;

    // Should only inbound
    private Institution institution;
}