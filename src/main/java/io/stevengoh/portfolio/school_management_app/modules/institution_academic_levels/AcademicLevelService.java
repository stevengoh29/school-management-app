package io.stevengoh.portfolio.school_management_app.modules.institution_academic_levels;

import io.stevengoh.portfolio.school_management_app.common.dtos.PaginatedResult;
import io.stevengoh.portfolio.school_management_app.modules.institution_academic_levels.dtos.request.CreateAcademicLevelDto;
import io.stevengoh.portfolio.school_management_app.modules.institution_academic_levels.dtos.request.SearchAcademicLevelDto;
import io.stevengoh.portfolio.school_management_app.modules.institution_academic_levels.dtos.request.UpdateAcademicLevelDto;
import io.stevengoh.portfolio.school_management_app.modules.institution_academic_levels.dtos.response.DetailedResAcademicLevelDto;
import io.stevengoh.portfolio.school_management_app.modules.institution_academic_levels.dtos.response.SimpleResAcademicLevelDto;
import io.stevengoh.portfolio.school_management_app.modules.institution_academic_levels.entities.AcademicLevel;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface AcademicLevelService {
    AcademicLevel findByUuidOrNull(UUID uuid);
    AcademicLevel findByUuidOrThrow(UUID uuid);
    PaginatedResult<SimpleResAcademicLevelDto> getAcademicLevels(SearchAcademicLevelDto searchAcademicLevelDto, Pageable pageable);
    DetailedResAcademicLevelDto getAcademicLevelByUuidOrNull(UUID uuid);
    DetailedResAcademicLevelDto getAcademicLevelByUuidOrThrow(UUID uuid);
    DetailedResAcademicLevelDto createAcademicLevel(UUID institutionUuid, CreateAcademicLevelDto request);
    DetailedResAcademicLevelDto updateAcademicLevel(UUID academicLevelUuid, UpdateAcademicLevelDto request);
    void deleteAcademicLevel(UUID academicLevelUuid);
}