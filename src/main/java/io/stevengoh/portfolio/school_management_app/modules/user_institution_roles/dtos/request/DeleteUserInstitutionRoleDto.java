package io.stevengoh.portfolio.school_management_app.modules.user_institution_roles.dtos.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class DeleteUserInstitutionRoleDto {
    private List<UUID> userInstitutionRoleUuids;
}
