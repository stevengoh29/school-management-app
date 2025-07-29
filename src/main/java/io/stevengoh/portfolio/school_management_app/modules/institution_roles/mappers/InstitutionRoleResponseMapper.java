package io.stevengoh.portfolio.school_management_app.modules.institution_roles.mappers;

import io.stevengoh.portfolio.school_management_app.modules.institution_roles.dtos.response.DetailedResInstitutionRoleDto;
import io.stevengoh.portfolio.school_management_app.modules.institution_roles.dtos.response.SimpleResInstitutionRoleDto;
import io.stevengoh.portfolio.school_management_app.modules.institution_roles.entities.InstitutionRole;
import io.stevengoh.portfolio.school_management_app.modules.roles.dtos.response.DetailedResRoleDto;
import io.stevengoh.portfolio.school_management_app.modules.roles.dtos.response.SimpleResRoleDto;
import io.stevengoh.portfolio.school_management_app.modules.roles.entities.Role;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface InstitutionRoleResponseMapper {
    List<SimpleResInstitutionRoleDto> toDtoList(List<InstitutionRole> institutions);

    SimpleResInstitutionRoleDto toDto(InstitutionRole institution);

    List<DetailedResInstitutionRoleDto> toDetailedDtoList(List<InstitutionRole> institutions);

    DetailedResInstitutionRoleDto toDetailedDto(InstitutionRole institution);
}
