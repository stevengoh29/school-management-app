package io.stevengoh.portfolio.school_management_app.modules.institution_roles.dtos.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateInstitutionRoleDto {
    private String name;
    private String description;
}
