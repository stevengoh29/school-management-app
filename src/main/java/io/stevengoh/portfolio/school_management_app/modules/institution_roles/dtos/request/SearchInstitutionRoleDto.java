package io.stevengoh.portfolio.school_management_app.modules.institution_roles.dtos.request;

import io.stevengoh.portfolio.school_management_app.common.annotations.FilterField;
import io.stevengoh.portfolio.school_management_app.common.enums.ActorType;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class SearchInstitutionRoleDto {
    private String name;
    private String description;
    private ActorType actorType;
    @FilterField(path = "baseRole.uuid")
    private UUID roleUuid;
}
