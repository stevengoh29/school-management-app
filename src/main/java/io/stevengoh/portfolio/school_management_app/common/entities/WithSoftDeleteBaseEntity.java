package io.stevengoh.portfolio.school_management_app.common.entities;

import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@MappedSuperclass
@Getter
@Setter
public abstract class WithSoftDeleteBaseEntity extends BaseEntity {
    private Instant deletedAt;
    private String deletedBy;
}