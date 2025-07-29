package io.stevengoh.portfolio.school_management_app.modules.institution_roles.dtos.request;

import io.stevengoh.portfolio.school_management_app.common.enums.ActorType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchInstitutionRoleDto {
    private String name;
    private String description;
    private ActorType actorType;
}
