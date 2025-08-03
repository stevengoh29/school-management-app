package io.stevengoh.portfolio.school_management_app.common.dtos;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class WithAuditingBaseResponseMapperDto extends BaseResponseMapperDto {
    private Instant createdAt;
    private Instant updatedAt;
    private String createdBy;
    private String updatedBy;
}
