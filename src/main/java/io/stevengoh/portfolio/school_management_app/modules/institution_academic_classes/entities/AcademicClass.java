package io.stevengoh.portfolio.school_management_app.modules.institution_academic_classes.entities;

import io.stevengoh.portfolio.school_management_app.common.entities.WithSoftDeleteBaseEntity;
import io.stevengoh.portfolio.school_management_app.modules.institution_academic_terms.entities.AcademicTerm;
import io.stevengoh.portfolio.school_management_app.modules.institution_grade_levels.entities.GradeLevel;
import io.stevengoh.portfolio.school_management_app.modules.institution_master_academic_classes.entities.MasterAcademicClass;
import io.stevengoh.portfolio.school_management_app.modules.institutions.entities.Institution;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "academic_classes")
@Getter
@Setter
public class AcademicClass extends WithSoftDeleteBaseEntity {
    private String name;        // e.g., "1A"

    private String code;        // e.g., "SD1A"

    private Integer orderIndex;

    @ManyToOne(optional = false)
    private AcademicTerm academicTerm;

    @ManyToOne(optional = false)
    private GradeLevel gradeLevel;

    @ManyToOne(optional = false)
    private Institution institution;

    private Boolean isActive = Boolean.TRUE;
}