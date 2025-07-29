package io.stevengoh.portfolio.school_management_app.modules.institution_roles.entities;

import io.stevengoh.portfolio.school_management_app.common.entities.BaseEntity;
import io.stevengoh.portfolio.school_management_app.common.entities.WithSoftDeleteBaseEntity;
import io.stevengoh.portfolio.school_management_app.common.enums.ActorType;
import io.stevengoh.portfolio.school_management_app.common.interfaces.InstitutionAware;
import io.stevengoh.portfolio.school_management_app.modules.institutions.entities.Institution;
import io.stevengoh.portfolio.school_management_app.modules.roles.entities.Role;
import io.stevengoh.portfolio.school_management_app.modules.roles.enums.RoleStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLRestriction;

@Entity(name = "institution_roles")
@Getter
@Setter
@SQLRestriction("deleted_at IS NULL")
public class InstitutionRole extends WithSoftDeleteBaseEntity implements InstitutionAware {
    @Column(nullable = false)
    private String name;

    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ActorType actorType; // e.g. STUDENT, STAFF, TUTOR, etc.

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "institution_id")
    private Institution institution;

    // Optional reference to global Role (used as template)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "base_role_id")
    private Role baseRole;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RoleStatus status = RoleStatus.ACTIVE;
}
