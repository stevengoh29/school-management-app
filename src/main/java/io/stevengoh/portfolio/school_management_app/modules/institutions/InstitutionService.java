package io.stevengoh.portfolio.school_management_app.modules.institutions;

import io.stevengoh.portfolio.school_management_app.common.dtos.PaginatedResult;
import io.stevengoh.portfolio.school_management_app.modules.institutions.dtos.request.CreateInstitutionDto;
import io.stevengoh.portfolio.school_management_app.modules.institutions.dtos.request.SearchInstitutionDto;
import io.stevengoh.portfolio.school_management_app.modules.institutions.dtos.request.UpdateInstitutionDto;
import io.stevengoh.portfolio.school_management_app.modules.institutions.dtos.response.DetailedResInstitutionDto;
import io.stevengoh.portfolio.school_management_app.modules.institutions.dtos.response.SimpleResInstitutionDto;
import io.stevengoh.portfolio.school_management_app.modules.institutions.entities.Institution;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface InstitutionService {
    // Reusable service method
    Institution findByUuidOrNull(UUID uuid);

    Institution findByUuidOrThrow(UUID uuid);

    PaginatedResult<SimpleResInstitutionDto> getInstitutions(SearchInstitutionDto searchInstitutionDto, Pageable pageable);

    DetailedResInstitutionDto getInstitutionByUuidOrNull(UUID uuid);

    DetailedResInstitutionDto getInstitutionByUuidOrThrow(UUID uuid);

    DetailedResInstitutionDto createInstitution(CreateInstitutionDto createInstitutionDto);

    DetailedResInstitutionDto updateInstitution(UUID uuid, UpdateInstitutionDto updateInstitutionDto);

    void deleteInstitution(UUID uuid);
}
