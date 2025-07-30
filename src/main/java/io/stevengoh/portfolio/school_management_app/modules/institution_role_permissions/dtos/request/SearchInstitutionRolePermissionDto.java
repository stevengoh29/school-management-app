package io.stevengoh.portfolio.school_management_app.modules.institution_role_permissions.dtos.request;

import io.stevengoh.portfolio.school_management_app.common.annotations.FilterField;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class SearchInstitutionRolePermissionDto {
    @FilterField(path = "institution.uuid")
    private UUID institutionUuid;

    @FilterField(path = "institutionRole.uuid")
    private UUID institutionRoleUuid;
}