package io.stevengoh.portfolio.school_management_app.modules.institution_academic_classes.mappers;

import io.stevengoh.portfolio.school_management_app.modules.institution_academic_classes.dtos.request.CreateAcademicClassDto;
import io.stevengoh.portfolio.school_management_app.modules.institution_academic_classes.dtos.request.UpdateAcademicClassDto;
import io.stevengoh.portfolio.school_management_app.modules.institution_academic_classes.entities.AcademicClass;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface AcademicClassRequestMapper {
    AcademicClass createDtoToEntity(CreateAcademicClassDto request);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateDtoToEntity(UpdateAcademicClassDto request, @MappingTarget AcademicClass entity);
}