package io.stevengoh.portfolio.school_management_app.modules.institution_role_permissions.dtos.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class DeleteInstitutionRolePermissionDto {
    private List<UUID> uuids;
}
