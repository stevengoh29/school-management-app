package io.stevengoh.portfolio.school_management_app.modules.permissions.entities;

import io.stevengoh.portfolio.school_management_app.common.entities.BaseEntity;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "permissions")
@Getter
@Setter
public class Permission extends BaseEntity {
    private String name;
    private String description;
}
