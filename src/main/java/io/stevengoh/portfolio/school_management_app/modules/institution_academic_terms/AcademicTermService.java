package io.stevengoh.portfolio.school_management_app.modules.institution_academic_terms;

import io.stevengoh.portfolio.school_management_app.common.dtos.PaginatedResult;
import io.stevengoh.portfolio.school_management_app.modules.institution_academic_terms.dtos.request.CreateAcademicTermDto;
import io.stevengoh.portfolio.school_management_app.modules.institution_academic_terms.dtos.request.SearchAcademicTermDto;
import io.stevengoh.portfolio.school_management_app.modules.institution_academic_terms.dtos.request.UpdateAcademicTermDto;
import io.stevengoh.portfolio.school_management_app.modules.institution_academic_terms.dtos.response.DetailedResAcademicTermDto;
import io.stevengoh.portfolio.school_management_app.modules.institution_academic_terms.dtos.response.SimpleResAcademicTermDto;
import io.stevengoh.portfolio.school_management_app.modules.institution_academic_terms.entities.AcademicTerm;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface AcademicTermService {
    AcademicTerm findByUuidOrNull(UUID uuid);
    AcademicTerm findByUuidOrThrow(UUID uuid);
    PaginatedResult<SimpleResAcademicTermDto> getTerms(SearchAcademicTermDto searchAcademicTermDto, Pageable pageable);
    DetailedResAcademicTermDto getTermByUuidOrNull(UUID uuid);
    DetailedResAcademicTermDto getTermByUuidOrThrow(UUID uuid);
    DetailedResAcademicTermDto createTerm(UUID institutionUuid, CreateAcademicTermDto request);
    DetailedResAcademicTermDto updateTerm(UUID termUuid, UpdateAcademicTermDto request);
    void resetTermIsCurrentFlagByInstitutionUuid(UUID institutionUuid);
    void deleteTerm(UUID uuid);
}