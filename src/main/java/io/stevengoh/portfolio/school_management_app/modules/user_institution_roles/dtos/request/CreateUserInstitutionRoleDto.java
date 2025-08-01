package io.stevengoh.portfolio.school_management_app.modules.user_institution_roles.dtos.request;

import io.stevengoh.portfolio.school_management_app.common.interfaces.InstitutionAware;
import io.stevengoh.portfolio.school_management_app.modules.institutions.entities.Institution;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CreateUserInstitutionRoleDto implements InstitutionAware {
    private UUID userUuid;
    private UUID institutionRoleUuid;
    private Institution institution;
}
