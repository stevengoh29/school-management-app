package io.stevengoh.portfolio.school_management_app.modules.institution_roles.dtos.response;

import io.stevengoh.portfolio.school_management_app.common.dtos.WithAuditingBaseResponseMapperDto;
import io.stevengoh.portfolio.school_management_app.common.enums.ActorType;
import io.stevengoh.portfolio.school_management_app.modules.institutions.dtos.response.SimpleResInstitutionDto;
import io.stevengoh.portfolio.school_management_app.modules.roles.dtos.response.SimpleResRoleDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DetailedResInstitutionRoleDto extends WithAuditingBaseResponseMapperDto {
    private String name;
    private String description;
    private ActorType actorType;
    private SimpleResInstitutionDto institution;
    private SimpleResRoleDto baseRole;
}
