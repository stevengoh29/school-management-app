package io.stevengoh.portfolio.school_management_app.common.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@MappedSuperclass
@Getter
@Setter
public abstract class NoAuditingBaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // or another strategy as needed
    @JsonIgnore
    private Long id;

    @Column(
            nullable = false,
            unique = true,
            updatable = false,
            columnDefinition = "UUID DEFAULT gen_random_uuid()"
    )
    private UUID uuid;

    @PrePersist
    public void prePersist() {
        if (uuid == null) {
            uuid = UUID.randomUUID();
        }
    }
}
