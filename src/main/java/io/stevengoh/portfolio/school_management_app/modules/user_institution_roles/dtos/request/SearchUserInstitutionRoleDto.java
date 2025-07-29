package io.stevengoh.portfolio.school_management_app.modules.user_institution_roles.dtos.request;

import io.stevengoh.portfolio.school_management_app.common.annotations.FilterField;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class SearchUserInstitutionRoleDto {
    @FilterField(path = "user.uuid")
    private UUID userUuid;

    @FilterField(path = "institutionRole.uuid")
    private UUID institutionRoleUuid;

    @FilterField(path = "institution.uuid")
    private UUID institutionUuid;
}