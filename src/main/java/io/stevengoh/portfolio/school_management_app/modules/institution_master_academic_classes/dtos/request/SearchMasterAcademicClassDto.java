package io.stevengoh.portfolio.school_management_app.modules.institution_master_academic_classes.dtos.request;

import io.stevengoh.portfolio.school_management_app.common.annotations.FilterField;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class SearchMasterAcademicClassDto {
    private String name; // e.g., "1A", "2B", "TK B1"
    private String code; // e.g., "SD1A", "TKB1"
    private Integer orderIndex;
    @FilterField(path = "gradeLevel.uuid")
    private UUID gradeLevelUuid;
}
