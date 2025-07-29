package io.stevengoh.portfolio.school_management_app.modules.permissions.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UpdatePermissionDto {
    private String name;
    private String description;
}
