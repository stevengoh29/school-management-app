package io.stevengoh.portfolio.school_management_app.modules.institution_grade_levels;

import io.stevengoh.portfolio.school_management_app.common.dtos.PaginatedResult;
import io.stevengoh.portfolio.school_management_app.modules.institution_grade_levels.dtos.request.CreateGradeLevelDto;
import io.stevengoh.portfolio.school_management_app.modules.institution_grade_levels.dtos.request.SearchGradeLevelDto;
import io.stevengoh.portfolio.school_management_app.modules.institution_grade_levels.dtos.request.UpdateGradeLevelDto;
import io.stevengoh.portfolio.school_management_app.modules.institution_grade_levels.dtos.response.DetailedResGradeLevelDto;
import io.stevengoh.portfolio.school_management_app.modules.institution_grade_levels.dtos.response.SimpleResGradeLevelDto;
import io.stevengoh.portfolio.school_management_app.modules.institution_grade_levels.entities.GradeLevel;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface GradeLevelService {
    GradeLevel findByUuidOrNull(UUID uuid);
    GradeLevel findByUuidOrThrow(UUID uuid);
    PaginatedResult<SimpleResGradeLevelDto> getGradeLevels(SearchGradeLevelDto searchGradeLevelDto, Pageable pageable);
    DetailedResGradeLevelDto getGradeLevelByUuidOrNull(UUID uuid);
    DetailedResGradeLevelDto getGradeLevelByUuidOrThrow(UUID uuid);
    DetailedResGradeLevelDto createGradeLevel(UUID institutionUuid, CreateGradeLevelDto request);
    DetailedResGradeLevelDto updateGradeLevel(UUID gradeLevelUuid, UpdateGradeLevelDto request);
    void deleteGradeLevel(UUID gradeLevelUuid);
}