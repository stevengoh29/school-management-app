package io.stevengoh.portfolio.school_management_app.modules.institution_role_permissions.entities;

import io.stevengoh.portfolio.school_management_app.common.entities.BaseEntity;
import io.stevengoh.portfolio.school_management_app.modules.institution_roles.entities.InstitutionRole;
import io.stevengoh.portfolio.school_management_app.modules.institutions.entities.Institution;
import io.stevengoh.portfolio.school_management_app.modules.permissions.entities.Permission;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "institution_role_permissions")
@Getter
@Setter
@AllArgsConstructor
public class InstitutionRolePermission extends BaseEntity {
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private InstitutionRole institutionRole;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Permission permission;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Institution institution;

    public InstitutionRolePermission(InstitutionRole institutionRole, Permission permission) {
        this.institutionRole = institutionRole;
        this.permission = permission;
    }
}