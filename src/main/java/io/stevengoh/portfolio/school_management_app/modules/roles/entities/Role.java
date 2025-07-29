package io.stevengoh.portfolio.school_management_app.modules.roles.entities;

import io.stevengoh.portfolio.school_management_app.common.entities.BaseEntity;
import io.stevengoh.portfolio.school_management_app.common.entities.WithSoftDeleteBaseEntity;
import io.stevengoh.portfolio.school_management_app.common.enums.ActorType;
import io.stevengoh.portfolio.school_management_app.modules.roles.enums.RoleStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLRestriction;

@Entity(name = "roles")
@Getter
@Setter
@SQLRestriction("deleted_at IS NULL")
public class Role extends WithSoftDeleteBaseEntity {
    @Column(nullable = false, unique = true)
    private String name;

    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ActorType actorType;

    @Enumerated(EnumType.STRING)
    private RoleStatus status = RoleStatus.ACTIVE;

    private Boolean isSuperAdmin = Boolean.FALSE;

    // When an institution has been created, all the isDefaultRole = true AND isSuperAdmin = false
    // will be cloned to it
    private Boolean isDefaultRole = Boolean.FALSE;
}
