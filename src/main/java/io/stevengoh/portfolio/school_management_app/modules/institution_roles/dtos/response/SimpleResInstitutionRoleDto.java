package io.stevengoh.portfolio.school_management_app.modules.institution_roles.dtos.response;

import io.stevengoh.portfolio.school_management_app.common.dtos.WithAuditingBaseResponseMapperDto;
import io.stevengoh.portfolio.school_management_app.common.enums.ActorType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SimpleResInstitutionRoleDto extends WithAuditingBaseResponseMapperDto {
    private String name;
    private String description;
    private ActorType actorType;
}
