package io.stevengoh.portfolio.school_management_app.common.entities;

import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLRestriction;

import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@Setter
public abstract class WithSoftDeleteBaseEntity extends BaseEntity {
    private LocalDateTime deletedAt;
    private String deletedBy;
}