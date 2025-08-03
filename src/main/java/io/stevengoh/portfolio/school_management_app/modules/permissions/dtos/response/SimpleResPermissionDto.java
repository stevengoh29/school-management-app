package io.stevengoh.portfolio.school_management_app.modules.permissions.dtos.response;

import io.stevengoh.portfolio.school_management_app.common.dtos.WithAuditingBaseResponseMapperDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SimpleResPermissionDto extends WithAuditingBaseResponseMapperDto {
    private String name;
    private String description;
}
