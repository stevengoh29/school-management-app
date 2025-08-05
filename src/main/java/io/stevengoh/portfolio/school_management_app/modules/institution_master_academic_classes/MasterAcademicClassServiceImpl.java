package io.stevengoh.portfolio.school_management_app.modules.institution_master_academic_classes;

import io.stevengoh.portfolio.school_management_app.common.annotations.AutoAssignInstitution;
import io.stevengoh.portfolio.school_management_app.common.dtos.PaginatedResult;
import io.stevengoh.portfolio.school_management_app.common.specifications.SpecificationBuilder;
import io.stevengoh.portfolio.school_management_app.common.utils.AuthUtils;
import io.stevengoh.portfolio.school_management_app.modules.institution_grade_levels.GradeLevelService;
import io.stevengoh.portfolio.school_management_app.modules.institution_grade_levels.entities.GradeLevel;
import io.stevengoh.portfolio.school_management_app.modules.institution_master_academic_classes.MasterAcademicClassRepository;
import io.stevengoh.portfolio.school_management_app.modules.institution_master_academic_classes.dtos.request.CreateMasterAcademicClassDto;
import io.stevengoh.portfolio.school_management_app.modules.institution_master_academic_classes.dtos.request.SearchMasterAcademicClassDto;
import io.stevengoh.portfolio.school_management_app.modules.institution_master_academic_classes.dtos.request.UpdateMasterAcademicClassDto;
import io.stevengoh.portfolio.school_management_app.modules.institution_master_academic_classes.dtos.response.DetailedResMasterAcademicClassDto;
import io.stevengoh.portfolio.school_management_app.modules.institution_master_academic_classes.dtos.response.SimpleResMasterAcademicClassDto;
import io.stevengoh.portfolio.school_management_app.modules.institution_master_academic_classes.entities.MasterAcademicClass;
import io.stevengoh.portfolio.school_management_app.modules.institution_master_academic_classes.mappers.MasterAcademicClassRequestMapper;
import io.stevengoh.portfolio.school_management_app.modules.institution_master_academic_classes.mappers.MasterAcademicClassResponseMapper;
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
public class MasterAcademicClassServiceImpl implements MasterAcademicClassService {
    private final InstitutionService institutionService;
    private final GradeLevelService gradeLevelService;

    private final MasterAcademicClassRepository masterAcademicClassRepository;
    private final MasterAcademicClassRequestMapper requestMapper;
    private final MasterAcademicClassResponseMapper responseMapper;

    @Override
    public MasterAcademicClass findByUuidOrNull(UUID uuid) {
        return masterAcademicClassRepository.findByUuid(uuid)
                .orElse(null);
    }

    @Override
    public MasterAcademicClass findByUuidOrThrow(UUID uuid) {
        return masterAcademicClassRepository.findByUuid(uuid)
                .orElseThrow(() -> new EntityNotFoundException("Class level not found."));
    }

    @Override
    public PaginatedResult<SimpleResMasterAcademicClassDto> getMasterAcademicClasses(SearchMasterAcademicClassDto searchMasterAcademicClassDto, Pageable pageable) {
        SpecificationBuilder<MasterAcademicClass, SearchMasterAcademicClassDto> builder = new SpecificationBuilder<>();
        Specification<MasterAcademicClass> spec = builder.build(searchMasterAcademicClassDto);

        Page<MasterAcademicClass> pageResult = masterAcademicClassRepository.findAll(spec, pageable);
        List<SimpleResMasterAcademicClassDto> dtoList = responseMapper.toSimpleResDtoList(pageResult.getContent());

        return new PaginatedResult<>(dtoList, pageResult.getTotalElements(), pageable.getPageNumber(), pageable.getPageSize());
    }

    @Override
    public DetailedResMasterAcademicClassDto getMasterAcademicClassByUuidOrNull(UUID uuid) {
        MasterAcademicClass academicLevel = findByUuidOrNull(uuid);
        return responseMapper.toDetailedResDto(academicLevel);
    }

    @Override
    public DetailedResMasterAcademicClassDto getMasterAcademicClassByUuidOrThrow(UUID uuid) {
        MasterAcademicClass academicLevel = findByUuidOrThrow(uuid);
        return responseMapper.toDetailedResDto(academicLevel);
    }

    @Override
    @AutoAssignInstitution
    public DetailedResMasterAcademicClassDto createMasterAcademicClass(UUID institutionUuid, CreateMasterAcademicClassDto request) {
        MasterAcademicClass entity = requestMapper.createDtoToEntity(request);

        if (request.getInstitution() != null) {
            entity.setInstitution(request.getInstitution());
        } else {
            Institution institution = institutionService.findByUuidOrThrow(institutionUuid);
            entity.setInstitution(institution);
        }

        GradeLevel gradeLevel = gradeLevelService.findByUuidOrThrow(request.getGradeLevelUuid());
        entity.setGradeLevel(gradeLevel);

        MasterAcademicClass saved = masterAcademicClassRepository.save(entity);
        return responseMapper.toDetailedResDto(saved);
    }

    @Override
    public DetailedResMasterAcademicClassDto updateMasterAcademicClass(UUID academicLevelUuid, UpdateMasterAcademicClassDto request) {
        MasterAcademicClass academicLevel = findByUuidOrThrow(academicLevelUuid);
        requestMapper.updateDtoToEntity(request, academicLevel);

        MasterAcademicClass saved = masterAcademicClassRepository.save(academicLevel);
        return responseMapper.toDetailedResDto(saved);
    }

    @Override
    public void deleteMasterAcademicClass(UUID academicLevelUuid) {
        MasterAcademicClass academicLevel = findByUuidOrThrow(academicLevelUuid);
        masterAcademicClassRepository.delete(academicLevel);
    }
}