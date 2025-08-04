package io.stevengoh.portfolio.school_management_app.modules.institution_master_academic_classes.entities;

import io.stevengoh.portfolio.school_management_app.common.entities.BaseEntity;
import io.stevengoh.portfolio.school_management_app.common.entities.WithSoftDeleteBaseEntity;
import io.stevengoh.portfolio.school_management_app.modules.institution_grade_levels.entities.GradeLevel;
import io.stevengoh.portfolio.school_management_app.modules.institutions.entities.Institution;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "master_academic_classes")
@Getter
@Setter
public class MasterAcademicClass extends BaseEntity {
    private String name; // e.g., "1A", "2B", "TK B1"

    private String code; // e.g., "SD1A", "TKB1"

    private Integer orderIndex;

    @ManyToOne
    private GradeLevel gradeLevel;

    @ManyToOne(optional = true)
    private Institution institution;
}