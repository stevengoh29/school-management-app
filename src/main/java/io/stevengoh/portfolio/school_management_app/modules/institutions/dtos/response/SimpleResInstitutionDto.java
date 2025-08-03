package io.stevengoh.portfolio.school_management_app.modules.institutions.dtos.response;

import io.stevengoh.portfolio.school_management_app.common.dtos.WithAuditingBaseResponseMapperDto;
import io.stevengoh.portfolio.school_management_app.modules.institutions.enums.InstitutionStatus;
import io.stevengoh.portfolio.school_management_app.modules.institutions.enums.InstitutionType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SimpleResInstitutionDto extends WithAuditingBaseResponseMapperDto {
    private String name;
    private String code;
    private InstitutionType type;
    private String email;
    private String phoneNumber;
    private String address;
    private InstitutionStatus status;
}
