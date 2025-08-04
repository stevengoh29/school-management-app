package io.stevengoh.portfolio.school_management_app.modules.institution_academic_classes.dtos.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateAcademicClassDto {
    private String name;
    private String code;
    private Integer orderIndex;
}
