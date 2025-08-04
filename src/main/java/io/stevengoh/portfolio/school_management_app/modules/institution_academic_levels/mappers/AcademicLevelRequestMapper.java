package io.stevengoh.portfolio.school_management_app.modules.institution_academic_levels.mappers;

import io.stevengoh.portfolio.school_management_app.modules.institution_academic_levels.dtos.request.CreateAcademicLevelDto;
import io.stevengoh.portfolio.school_management_app.modules.institution_academic_levels.dtos.request.UpdateAcademicLevelDto;
import io.stevengoh.portfolio.school_management_app.modules.institution_academic_levels.entities.AcademicLevel;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface AcademicLevelRequestMapper {
    AcademicLevel createDtoToEntity(CreateAcademicLevelDto request);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateDtoToEntity(UpdateAcademicLevelDto request, @MappingTarget AcademicLevel academicLevel);
}