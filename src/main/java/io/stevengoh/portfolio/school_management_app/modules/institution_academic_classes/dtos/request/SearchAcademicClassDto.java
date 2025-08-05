package io.stevengoh.portfolio.school_management_app.modules.institution_academic_classes.dtos.request;

import io.stevengoh.portfolio.school_management_app.common.annotations.FilterField;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class SearchAcademicClassDto {
    private String name;
    private String code;
    private Integer orderIndex;

    @FilterField(path = "gradeLevel.uuid")
    private UUID gradeLevelUuid;

    @FilterField(path = "academicTerm.uuid")
    private UUID academicTermUuid;

    @FilterField(path = "institution.uuid")
    private UUID institutionUuid;
}