package io.stevengoh.portfolio.school_management_app.modules.institution_academic_classes;

import io.stevengoh.portfolio.school_management_app.common.annotations.AutoAssignInstitution;
import io.stevengoh.portfolio.school_management_app.common.dtos.PaginatedResult;
import io.stevengoh.portfolio.school_management_app.common.specifications.SpecificationBuilder;
import io.stevengoh.portfolio.school_management_app.modules.institution_academic_classes.dtos.request.CreateAcademicClassDto;
import io.stevengoh.portfolio.school_management_app.modules.institution_academic_classes.dtos.request.SearchAcademicClassDto;
import io.stevengoh.portfolio.school_management_app.modules.institution_academic_classes.dtos.request.UpdateAcademicClassDto;
import io.stevengoh.portfolio.school_management_app.modules.institution_academic_classes.dtos.response.DetailedResAcademicClassDto;
import io.stevengoh.portfolio.school_management_app.modules.institution_academic_classes.dtos.response.SimpleResAcademicClassDto;
import io.stevengoh.portfolio.school_management_app.modules.institution_academic_classes.entities.AcademicClass;
import io.stevengoh.portfolio.school_management_app.modules.institution_academic_classes.mappers.AcademicClassRequestMapper;
import io.stevengoh.portfolio.school_management_app.modules.institution_academic_classes.mappers.AcademicClassResponseMapper;
import io.stevengoh.portfolio.school_management_app.modules.institution_academic_terms.AcademicTermService;
import io.stevengoh.portfolio.school_management_app.modules.institution_academic_terms.entities.AcademicTerm;
import io.stevengoh.portfolio.school_management_app.modules.institution_grade_levels.GradeLevelService;
import io.stevengoh.portfolio.school_management_app.modules.institution_grade_levels.entities.GradeLevel;
import io.stevengoh.portfolio.school_management_app.modules.institutions.InstitutionService;
import io.stevengoh.portfolio.school_management_app.modules.institutions.entities.Institution;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AcademicClassServiceImpl implements AcademicClassService {
    private final InstitutionService institutionService;
    private final AcademicTermService academicTermService;
    private final GradeLevelService gradeLevelService;

    private final AcademicClassRepository academicClassRepository;
    private final AcademicClassRequestMapper requestMapper;
    private final AcademicClassResponseMapper responseMapper;

    @Override
    public AcademicClass findByUuidOrNull(UUID uuid) {
        return academicClassRepository.findByUuid(uuid)
                .orElse(null);
    }

    @Override
    public AcademicClass findByUuidOrThrow(UUID uuid) {
        return academicClassRepository.findByUuid(uuid)
                .orElseThrow(() -> new EntityNotFoundException("Class level not found."));
    }

    @Override
    public PaginatedResult<SimpleResAcademicClassDto> getAcademicClasses(SearchAcademicClassDto searchAcademicClassDto, Pageable pageable) {
        SpecificationBuilder<AcademicClass, SearchAcademicClassDto> builder = new SpecificationBuilder<>();
        Specification<AcademicClass> spec = builder.build(searchAcademicClassDto);

        Page<AcademicClass> pageResult = academicClassRepository.findAll(spec, pageable);
        List<SimpleResAcademicClassDto> dtoList = responseMapper.toSimpleResDtoList(pageResult.getContent());

        return new PaginatedResult<>(dtoList, pageResult.getTotalElements(), pageable.getPageNumber(), pageable.getPageSize());
    }

    @Override
    public DetailedResAcademicClassDto getAcademicClassByUuidOrNull(UUID uuid) {
        AcademicClass academicClass = findByUuidOrNull(uuid);
        return responseMapper.toDetailedResDto(academicClass);
    }

    @Override
    public DetailedResAcademicClassDto getAcademicClassByUuidOrThrow(UUID uuid) {
        AcademicClass academicClass = findByUuidOrThrow(uuid);
        return responseMapper.toDetailedResDto(academicClass);
    }

    @Override
    @AutoAssignInstitution
    public DetailedResAcademicClassDto createAcademicClass(UUID institutionUuid, CreateAcademicClassDto request) {
        AcademicClass entity = requestMapper.createDtoToEntity(request);
        AcademicTerm academicTerm = academicTermService.findByUuidOrThrow(request.getAcademicTermUuid());
        GradeLevel gradeLevel = gradeLevelService.findByUuidOrThrow(request.getGradeLevelUuid());

        if (request.getInstitution() != null) {
            entity.setInstitution(request.getInstitution());
        } else {
            Institution institution = institutionService.findByUuidOrThrow(institutionUuid);
            entity.setInstitution(institution);
        }

        entity.setAcademicTerm(academicTerm);
        entity.setGradeLevel(gradeLevel);

        AcademicClass saved = academicClassRepository.save(entity);
        return responseMapper.toDetailedResDto(saved);
    }

    @Override
    public DetailedResAcademicClassDto updateAcademicClass(UUID academicClassUuid, UpdateAcademicClassDto request) {
        AcademicClass academicClass = findByUuidOrThrow(academicClassUuid);
        requestMapper.updateDtoToEntity(request, academicClass);

        AcademicClass saved = academicClassRepository.save(academicClass);
        return responseMapper.toDetailedResDto(saved);
    }

    @Override
    public void deleteAcademicClass(UUID academicClassUuid) {
        AcademicClass academicClass = findByUuidOrThrow(academicClassUuid);
        academicClassRepository.delete(academicClass);
    }
}