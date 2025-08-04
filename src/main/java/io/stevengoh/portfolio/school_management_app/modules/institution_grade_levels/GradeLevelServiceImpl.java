package io.stevengoh.portfolio.school_management_app.modules.institution_grade_levels;

import io.stevengoh.portfolio.school_management_app.common.annotations.AutoAssignInstitution;
import io.stevengoh.portfolio.school_management_app.common.dtos.PaginatedResult;
import io.stevengoh.portfolio.school_management_app.common.specifications.SpecificationBuilder;
import io.stevengoh.portfolio.school_management_app.common.utils.AuthUtils;
import io.stevengoh.portfolio.school_management_app.modules.institution_academic_levels.AcademicLevelService;
import io.stevengoh.portfolio.school_management_app.modules.institution_academic_levels.entities.AcademicLevel;
import io.stevengoh.portfolio.school_management_app.modules.institution_grade_levels.dtos.request.CreateGradeLevelDto;
import io.stevengoh.portfolio.school_management_app.modules.institution_grade_levels.dtos.request.SearchGradeLevelDto;
import io.stevengoh.portfolio.school_management_app.modules.institution_grade_levels.dtos.request.UpdateGradeLevelDto;
import io.stevengoh.portfolio.school_management_app.modules.institution_grade_levels.dtos.response.DetailedResGradeLevelDto;
import io.stevengoh.portfolio.school_management_app.modules.institution_grade_levels.dtos.response.SimpleResGradeLevelDto;
import io.stevengoh.portfolio.school_management_app.modules.institution_grade_levels.entities.GradeLevel;
import io.stevengoh.portfolio.school_management_app.modules.institution_grade_levels.mappers.GradeLevelRequestMapper;
import io.stevengoh.portfolio.school_management_app.modules.institution_grade_levels.mappers.GradeLevelResponseMapper;
import io.stevengoh.portfolio.school_management_app.modules.institutions.InstitutionService;
import io.stevengoh.portfolio.school_management_app.modules.institutions.entities.Institution;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GradeLevelServiceImpl implements GradeLevelService {
    private final InstitutionService institutionService;
    private final AcademicLevelService academicLevelService;

    private final GradeLevelRepository gradeLevelRepository;
    private final GradeLevelRequestMapper requestMapper;
    private final GradeLevelResponseMapper responseMapper;

    @Override
    public GradeLevel findByUuidOrNull(UUID uuid) {
        return gradeLevelRepository.findByUuid(uuid)
                .orElse(null);
    }

    @Override
    public GradeLevel findByUuidOrThrow(UUID uuid) {
        return gradeLevelRepository.findByUuid(uuid)
                .orElseThrow(() -> new EntityNotFoundException("Grade level not found."));
    }

    @Override
    public PaginatedResult<SimpleResGradeLevelDto> getGradeLevels(SearchGradeLevelDto searchGradeLevelDto, Pageable pageable) {
        SpecificationBuilder<GradeLevel, SearchGradeLevelDto> builder = new SpecificationBuilder<>();
        Specification<GradeLevel> spec = builder.build(searchGradeLevelDto);

        Page<GradeLevel> pageResult = gradeLevelRepository.findAll(spec, pageable);
        List<SimpleResGradeLevelDto> dtoList = responseMapper.toSimpleResDtoList(pageResult.getContent());

        return new PaginatedResult<>(dtoList, pageResult.getTotalElements(), pageable.getPageNumber(), pageable.getPageSize());
    }

    @Override
    public DetailedResGradeLevelDto getGradeLevelByUuidOrNull(UUID uuid) {
        GradeLevel gradeLevel = findByUuidOrNull(uuid);
        return responseMapper.toDetailedResDto(gradeLevel);
    }

    @Override
    public DetailedResGradeLevelDto getGradeLevelByUuidOrThrow(UUID uuid) {
        GradeLevel gradeLevel = findByUuidOrThrow(uuid);
        return responseMapper.toDetailedResDto(gradeLevel);
    }

    @Override
    @AutoAssignInstitution
    public DetailedResGradeLevelDto createGradeLevel(UUID institutionUuid, CreateGradeLevelDto request) {
        AcademicLevel academicLevel = academicLevelService.findByUuidOrThrow(request.getAcademicLevelUuid());

        GradeLevel entity = requestMapper.createDtoToEntity(request);
        entity.setAcademicLevel(academicLevel);

        if (request.getInstitution() != null) {
            entity.setInstitution(request.getInstitution());
        } else {
            Institution institution = institutionService.findByUuidOrThrow(institutionUuid);
            entity.setInstitution(institution);
        }

        GradeLevel saved = gradeLevelRepository.save(entity);
        return responseMapper.toDetailedResDto(saved);
    }

    @Override
    public DetailedResGradeLevelDto updateGradeLevel(UUID gradeLevelUuid, UpdateGradeLevelDto request) {
        GradeLevel gradeLevel = findByUuidOrThrow(gradeLevelUuid);
        requestMapper.updateDtoToEntity(request, gradeLevel);

        GradeLevel saved = gradeLevelRepository.save(gradeLevel);
        return responseMapper.toDetailedResDto(saved);
    }

    @Override
    public void deleteGradeLevel(UUID gradeLevelUuid) {
        GradeLevel gradeLevel = findByUuidOrThrow(gradeLevelUuid);

        String username = AuthUtils.getUsername();

        gradeLevel.setDeletedAt(Instant.now());
        gradeLevel.setDeletedBy(username);

        gradeLevelRepository.save(gradeLevel);
    }
}