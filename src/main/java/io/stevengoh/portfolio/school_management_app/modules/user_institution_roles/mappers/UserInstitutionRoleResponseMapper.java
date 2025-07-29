package io.stevengoh.portfolio.school_management_app.modules.user_institution_roles.mappers;

import io.stevengoh.portfolio.school_management_app.modules.roles.dtos.response.DetailedResRoleDto;
import io.stevengoh.portfolio.school_management_app.modules.roles.entities.Role;
import io.stevengoh.portfolio.school_management_app.modules.user_institution_roles.dtos.response.DetailedResUserInstitutionRoleDto;
import io.stevengoh.portfolio.school_management_app.modules.user_institution_roles.dtos.response.SimpleResUserInstitutionRoleDto;
import io.stevengoh.portfolio.school_management_app.modules.user_institution_roles.entities.UserInstitutionRole;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserInstitutionRoleResponseMapper {
    List<SimpleResUserInstitutionRoleDto> toDtoList(List<UserInstitutionRole> userRoles);
    SimpleResUserInstitutionRoleDto toDto(UserInstitutionRole userRole);

    List<DetailedResRoleDto> toDetailedDtoList(List<Role> institutions);
    DetailedResUserInstitutionRoleDto toDetailedDto(UserInstitutionRole userRole);
}
