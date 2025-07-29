package io.stevengoh.portfolio.school_management_app.modules.institutions.dtos.request;

import io.stevengoh.portfolio.school_management_app.modules.institutions.enums.InstitutionStatus;
import io.stevengoh.portfolio.school_management_app.modules.institutions.enums.InstitutionType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateInstitutionDto {
    private String name;
    private String code;
    private InstitutionType type;
    private String email;
    private String phoneNumber;
    private String address;
}
