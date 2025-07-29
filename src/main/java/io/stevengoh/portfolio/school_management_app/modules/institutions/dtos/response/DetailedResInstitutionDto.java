package io.stevengoh.portfolio.school_management_app.modules.institutions.dtos.response;

import io.stevengoh.portfolio.school_management_app.common.entities.BaseEntity;
import io.stevengoh.portfolio.school_management_app.modules.institutions.enums.InstitutionStatus;
import io.stevengoh.portfolio.school_management_app.modules.institutions.enums.InstitutionType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class DetailedResInstitutionDto {
    private UUID uuid;
    private String name;
    private String code;
    private InstitutionType type;
    private String email;
    private String phoneNumber;
    private String address;
    private InstitutionStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String createdBy;
    private String updatedBy;
}
