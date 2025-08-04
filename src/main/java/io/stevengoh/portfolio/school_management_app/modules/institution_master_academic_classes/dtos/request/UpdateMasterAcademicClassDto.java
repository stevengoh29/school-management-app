package io.stevengoh.portfolio.school_management_app.modules.institution_master_academic_classes.dtos.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateMasterAcademicClassDto {
    private String name; // e.g., "1A", "2B", "TK B1"
    private String code; // e.g., "SD1A", "TKB1"
    private Integer orderIndex;
}