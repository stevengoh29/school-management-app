package io.stevengoh.portfolio.school_management_app.modules.institution_master_academic_classes.dtos.request;

import io.stevengoh.portfolio.school_management_app.common.interfaces.InstitutionAware;
import io.stevengoh.portfolio.school_management_app.modules.institution_grade_levels.entities.GradeLevel;
import io.stevengoh.portfolio.school_management_app.modules.institutions.entities.Institution;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CreateMasterAcademicClassDto implements InstitutionAware {
    private String name; // e.g., "1A", "2B", "TK B1"
    private String code; // e.g., "SD1A", "TKB1"
    private Integer orderIndex;
    private UUID gradeLevelUuid;
    private Institution institution;
}