package io.stevengoh.portfolio.school_management_app.modules.institution_academic_terms.mappers;

import io.stevengoh.portfolio.school_management_app.modules.institution_academic_terms.dtos.request.CreateAcademicTermDto;
import io.stevengoh.portfolio.school_management_app.modules.institution_academic_terms.dtos.request.UpdateAcademicTermDto;
import io.stevengoh.portfolio.school_management_app.modules.institution_academic_terms.entities.AcademicTerm;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.context.annotation.Bean;

@Mapper(componentModel = "spring")
public interface AcademicTermRequestMapper {
    AcademicTerm createToEntity(CreateAcademicTermDto dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateToEntity(UpdateAcademicTermDto dto, @MappingTarget AcademicTerm entity);
}
