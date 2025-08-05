package io.stevengoh.portfolio.school_management_app.modules.institution_grade_levels.entities;

import io.stevengoh.portfolio.school_management_app.common.entities.WithSoftDeleteBaseEntity;
import io.stevengoh.portfolio.school_management_app.modules.institution_academic_levels.entities.AcademicLevel;
import io.stevengoh.portfolio.school_management_app.modules.institutions.entities.Institution;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLRestriction;

@Entity(name = "grade_levels")
@Getter
@Setter
@SQLRestriction("deleted_at IS NULL")
public class GradeLevel extends WithSoftDeleteBaseEntity {
    private String name;        // e.g., "TK A", "SD 1", "SD 2"

    private String code;        // e.g., "1", "A"

    private Integer orderIndex;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "academic_level_id")
    private AcademicLevel academicLevel;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "institution_id")
    private Institution institution;
}