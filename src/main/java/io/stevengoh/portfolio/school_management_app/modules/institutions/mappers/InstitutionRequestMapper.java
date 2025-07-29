package io.stevengoh.portfolio.school_management_app.modules.institutions.mappers;

import io.stevengoh.portfolio.school_management_app.modules.institutions.dtos.request.CreateInstitutionDto;
import io.stevengoh.portfolio.school_management_app.modules.institutions.dtos.request.UpdateInstitutionDto;
import io.stevengoh.portfolio.school_management_app.modules.institutions.entities.Institution;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface InstitutionRequestMapper {
    Institution createEntityToDto(CreateInstitutionDto request);
    
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityToDto(UpdateInstitutionDto request, @MappingTarget Institution entity);
}
