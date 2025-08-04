package io.stevengoh.portfolio.school_management_app.modules.institution_master_academic_classes.mappers;

import io.stevengoh.portfolio.school_management_app.modules.institution_master_academic_classes.dtos.request.CreateMasterAcademicClassDto;
import io.stevengoh.portfolio.school_management_app.modules.institution_master_academic_classes.dtos.request.UpdateMasterAcademicClassDto;
import io.stevengoh.portfolio.school_management_app.modules.institution_master_academic_classes.entities.MasterAcademicClass;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface MasterAcademicClassRequestMapper {
    MasterAcademicClass createDtoToEntity(CreateMasterAcademicClassDto request);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateDtoToEntity(UpdateMasterAcademicClassDto request, @MappingTarget MasterAcademicClass entity);
}