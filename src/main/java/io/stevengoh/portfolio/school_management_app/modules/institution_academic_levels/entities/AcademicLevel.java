package io.stevengoh.portfolio.school_management_app.modules.institution_academic_levels.entities;

import io.stevengoh.portfolio.school_management_app.common.entities.WithSoftDeleteBaseEntity;
import io.stevengoh.portfolio.school_management_app.modules.institutions.entities.Institution;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLRestriction;

@Entity(name = "academic_levels")
@Getter
@Setter
@SQLRestriction("deleted_at IS NULL")
public class AcademicLevel extends WithSoftDeleteBaseEntity {
    private String name; // e.g. SD, SMP, SMA

    private Integer orderIndex; // for sorting

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "institution_id")
    private Institution institution;
}