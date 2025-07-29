package io.stevengoh.portfolio.school_management_app.modules.institutions.entities;

import io.stevengoh.portfolio.school_management_app.common.entities.BaseEntity;
import io.stevengoh.portfolio.school_management_app.modules.institutions.enums.InstitutionStatus;
import io.stevengoh.portfolio.school_management_app.modules.institutions.enums.InstitutionType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLRestriction;

@Entity(name = "institutions")
@Getter
@Setter
@SQLRestriction("status <> 'DELETED'")
public class Institution extends BaseEntity {
    private String name;

    private String code;

    @Enumerated(EnumType.STRING)
    private InstitutionType type;

    private String email;

    private String phoneNumber;

    private String address;

    @Enumerated(EnumType.STRING)
    private InstitutionStatus status = InstitutionStatus.ACTIVE;
}
