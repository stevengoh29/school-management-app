package io.stevengoh.portfolio.school_management_app.modules.institution_academic_terms.dtos.response;

import io.stevengoh.portfolio.school_management_app.common.dtos.BaseResponseMapperDto;
import io.stevengoh.portfolio.school_management_app.modules.institution_academic_terms.enums.TermStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
public class SimpleResAcademicTermDto extends BaseResponseMapperDto {
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
    private TermStatus status;
    private Boolean isCurrent;
}