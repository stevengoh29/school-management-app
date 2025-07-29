package io.stevengoh.portfolio.school_management_app.modules.institution_roles.mappers;

import io.stevengoh.portfolio.school_management_app.modules.institution_roles.dtos.request.CreateInstitutionRoleDto;
import io.stevengoh.portfolio.school_management_app.modules.institution_roles.dtos.request.UpdateInstitutionRoleDto;
import io.stevengoh.portfolio.school_management_app.modules.institution_roles.entities.InstitutionRole;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface InstitutionRoleRequestMapper {
    InstitutionRole createEntityToDto(CreateInstitutionRoleDto request);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityToDto(UpdateInstitutionRoleDto request, @MappingTarget InstitutionRole entity);
}
