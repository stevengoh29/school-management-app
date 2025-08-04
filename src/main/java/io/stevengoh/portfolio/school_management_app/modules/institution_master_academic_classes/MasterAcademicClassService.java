package io.stevengoh.portfolio.school_management_app.modules.institution_master_academic_classes;

import io.stevengoh.portfolio.school_management_app.common.dtos.PaginatedResult;
import io.stevengoh.portfolio.school_management_app.modules.institution_master_academic_classes.dtos.request.CreateMasterAcademicClassDto;
import io.stevengoh.portfolio.school_management_app.modules.institution_master_academic_classes.dtos.request.SearchMasterAcademicClassDto;
import io.stevengoh.portfolio.school_management_app.modules.institution_master_academic_classes.dtos.request.UpdateMasterAcademicClassDto;
import io.stevengoh.portfolio.school_management_app.modules.institution_master_academic_classes.dtos.response.DetailedResMasterAcademicClassDto;
import io.stevengoh.portfolio.school_management_app.modules.institution_master_academic_classes.dtos.response.SimpleResMasterAcademicClassDto;
import io.stevengoh.portfolio.school_management_app.modules.institution_master_academic_classes.entities.MasterAcademicClass;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface MasterAcademicClassService {
    MasterAcademicClass findByUuidOrNull(UUID uuid);
    MasterAcademicClass findByUuidOrThrow(UUID uuid);
    PaginatedResult<SimpleResMasterAcademicClassDto> getMasterAcademicClasses(SearchMasterAcademicClassDto searchMasterAcademicClassDto, Pageable pageable);
    DetailedResMasterAcademicClassDto getMasterAcademicClassByUuidOrNull(UUID uuid);
    DetailedResMasterAcademicClassDto getMasterAcademicClassByUuidOrThrow(UUID uuid);
    DetailedResMasterAcademicClassDto createMasterAcademicClass(UUID institutionUuid, CreateMasterAcademicClassDto request);
    DetailedResMasterAcademicClassDto updateMasterAcademicClass(UUID uuid, UpdateMasterAcademicClassDto request);
    void deleteMasterAcademicClass(UUID uuid);
}