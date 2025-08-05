package io.stevengoh.portfolio.school_management_app.modules.institution_academic_classes;

import io.stevengoh.portfolio.school_management_app.common.dtos.PaginatedResult;
import io.stevengoh.portfolio.school_management_app.modules.institution_academic_classes.dtos.request.CreateAcademicClassDto;
import io.stevengoh.portfolio.school_management_app.modules.institution_academic_classes.dtos.request.SearchAcademicClassDto;
import io.stevengoh.portfolio.school_management_app.modules.institution_academic_classes.dtos.request.UpdateAcademicClassDto;
import io.stevengoh.portfolio.school_management_app.modules.institution_academic_classes.dtos.response.DetailedResAcademicClassDto;
import io.stevengoh.portfolio.school_management_app.modules.institution_academic_classes.dtos.response.SimpleResAcademicClassDto;
import io.stevengoh.portfolio.school_management_app.modules.institution_academic_classes.entities.AcademicClass;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface AcademicClassService {
    AcademicClass findByUuidOrNull(UUID uuid);
    AcademicClass findByUuidOrThrow(UUID uuid);
    PaginatedResult<SimpleResAcademicClassDto> getAcademicClasses(SearchAcademicClassDto searchAcademicClassDto, Pageable pageable);
    DetailedResAcademicClassDto getAcademicClassByUuidOrNull(UUID uuid);
    DetailedResAcademicClassDto getAcademicClassByUuidOrThrow(UUID uuid);
    DetailedResAcademicClassDto createAcademicClass(UUID institutionUuid, CreateAcademicClassDto request);
    DetailedResAcademicClassDto updateAcademicClass(UUID uuid, UpdateAcademicClassDto request);
    void deleteAcademicClass(UUID uuid);
}