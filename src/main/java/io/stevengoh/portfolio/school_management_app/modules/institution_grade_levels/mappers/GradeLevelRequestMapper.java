package io.stevengoh.portfolio.school_management_app.modules.institution_grade_levels.mappers;

import io.stevengoh.portfolio.school_management_app.modules.institution_grade_levels.dtos.request.CreateGradeLevelDto;
import io.stevengoh.portfolio.school_management_app.modules.institution_grade_levels.dtos.request.UpdateGradeLevelDto;
import io.stevengoh.portfolio.school_management_app.modules.institution_grade_levels.entities.GradeLevel;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
public interface GradeLevelRequestMapper {
    GradeLevel createDtoToEntity(CreateGradeLevelDto request);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateDtoToEntity(UpdateGradeLevelDto request, @MappingTarget GradeLevel entity);
}