package io.stevengoh.portfolio.school_management_app.modules.institution_role_permissions.dtos.request;

import io.stevengoh.portfolio.school_management_app.common.interfaces.InstitutionAware;
import io.stevengoh.portfolio.school_management_app.modules.institutions.entities.Institution;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CreateInstitutionRolePermissionDto implements InstitutionAware {
    private Institution institution;
    private UUID institutionRoleUuid;
    private UUID permissionUuid;
}
