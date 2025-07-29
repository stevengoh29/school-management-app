package io.stevengoh.portfolio.school_management_app.modules.role_permissions.entities;

import io.stevengoh.portfolio.school_management_app.common.entities.BaseEntity;
import io.stevengoh.portfolio.school_management_app.modules.permissions.entities.Permission;
import io.stevengoh.portfolio.school_management_app.modules.roles.entities.Role;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "role_permissions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RolePermission extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "role_id")
    private Role role;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "permission_id")
    private Permission permission;
}
