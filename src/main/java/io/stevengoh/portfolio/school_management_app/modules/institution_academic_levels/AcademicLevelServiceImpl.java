package io.stevengoh.portfolio.school_management_app.modules.institution_academic_levels;

import io.stevengoh.portfolio.school_management_app.common.annotations.AutoAssignInstitution;
import io.stevengoh.portfolio.school_management_app.common.dtos.PaginatedResult;
import io.stevengoh.portfolio.school_management_app.common.specifications.SpecificationBuilder;
import io.stevengoh.portfolio.school_management_app.common.utils.AuthUtils;
import io.stevengoh.portfolio.school_management_app.modules.institution_academic_levels.dtos.request.CreateAcademicLevelDto;
import io.stevengoh.portfolio.school_management_app.modules.institution_academic_levels.dtos.request.SearchAcademicLevelDto;
import io.stevengoh.portfolio.school_management_app.modules.institution_academic_levels.dtos.response.DetailedResAcademicLevelDto;
import io.stevengoh.portfolio.school_management_app.modules.institution_academic_levels.dtos.response.SimpleResAcademicLevelDto;
import io.stevengoh.portfolio.school_management_app.modules.institution_academic_levels.entities.AcademicLevel;
import io.stevengoh.portfolio.school_management_app.modules.institution_academic_levels.dtos.request.UpdateAcademicLevelDto;
import io.stevengoh.portfolio.school_management_app.modules.institution_academic_levels.mappers.AcademicLevelRequestMapper;
import io.stevengoh.portfolio.school_management_app.modules.institution_academic_levels.mappers.AcademicLevelResponseMapper;
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
public class AcademicLevelServiceImpl implements AcademicLevelService {
    private final InstitutionService institutionService;

    private final AcademicLevelRepository academicLevelRepository;
    private final AcademicLevelRequestMapper requestMapper;
    private final AcademicLevelResponseMapper responseMapper;

    @Override
    public AcademicLevel findByUuidOrNull(UUID uuid) {
        return academicLevelRepository.findByUuid(uuid)
                .orElse(null);
    }

    @Override
    public AcademicLevel findByUuidOrThrow(UUID uuid) {
        return academicLevelRepository.findByUuid(uuid)
                .orElseThrow(() -> new EntityNotFoundException("Class level not found."));
    }

    @Override
    public PaginatedResult<SimpleResAcademicLevelDto> getAcademicLevels(SearchAcademicLevelDto searchAcademicLevelDto, Pageable pageable) {
        SpecificationBuilder<AcademicLevel, SearchAcademicLevelDto> builder = new SpecificationBuilder<>();
        Specification<AcademicLevel> spec = builder.build(searchAcademicLevelDto);

        Page<AcademicLevel> pageResult = academicLevelRepository.findAll(spec, pageable);
        List<SimpleResAcademicLevelDto> dtoList = responseMapper.toSimpleResDtoList(pageResult.getContent());

        return new PaginatedResult<>(dtoList, pageResult.getTotalElements(), pageable.getPageNumber(), pageable.getPageSize());
    }

    @Override
    public DetailedResAcademicLevelDto getAcademicLevelByUuidOrNull(UUID uuid) {
        AcademicLevel academicLevel = findByUuidOrNull(uuid);
        return responseMapper.toDetailedResDto(academicLevel);
    }

    @Override
    public DetailedResAcademicLevelDto getAcademicLevelByUuidOrThrow(UUID uuid) {
        AcademicLevel academicLevel = findByUuidOrThrow(uuid);
        return responseMapper.toDetailedResDto(academicLevel);
    }

    @Override
    @AutoAssignInstitution
    public DetailedResAcademicLevelDto createAcademicLevel(UUID institutionUuid, CreateAcademicLevelDto request) {
        AcademicLevel entity = requestMapper.createDtoToEntity(request);

        if (request.getInstitution() != null) {
            entity.setInstitution(request.getInstitution());
        } else {
            Institution institution = institutionService.findByUuidOrThrow(institutionUuid);
            entity.setInstitution(institution);
        }

        AcademicLevel saved = academicLevelRepository.save(entity);
        return responseMapper.toDetailedResDto(saved);
    }

    @Override
    public DetailedResAcademicLevelDto updateAcademicLevel(UUID academicLevelUuid, UpdateAcademicLevelDto request) {
        AcademicLevel academicLevel = findByUuidOrThrow(academicLevelUuid);
        requestMapper.updateDtoToEntity(request, academicLevel);

        AcademicLevel saved = academicLevelRepository.save(academicLevel);
        return responseMapper.toDetailedResDto(saved);
    }

    @Override
    public void deleteAcademicLevel(UUID academicLevelUuid) {
        AcademicLevel academicLevel = findByUuidOrThrow(academicLevelUuid);

        String username = AuthUtils.getUsername();

        academicLevel.setDeletedAt(Instant.now());
        academicLevel.setDeletedBy(username);

        academicLevelRepository.save(academicLevel);
    }
}
