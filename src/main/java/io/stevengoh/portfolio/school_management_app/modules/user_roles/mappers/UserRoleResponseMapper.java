package io.stevengoh.portfolio.school_management_app.modules.user_roles.mappers;

import io.stevengoh.portfolio.school_management_app.modules.roles.dtos.response.DetailedResRoleDto;
import io.stevengoh.portfolio.school_management_app.modules.roles.entities.Role;
import io.stevengoh.portfolio.school_management_app.modules.user_roles.dtos.response.DetailedResUserRoleDto;
import io.stevengoh.portfolio.school_management_app.modules.user_roles.dtos.response.SimpleResUserRoleDto;
import io.stevengoh.portfolio.school_management_app.modules.user_roles.entities.UserRole;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserRoleResponseMapper {
    List<SimpleResUserRoleDto> toDtoList(List<UserRole> userRoles);
    SimpleResUserRoleDto toDto(UserRole userRole);

    List<DetailedResRoleDto> toDetailedDtoList(List<Role> institutions);
    DetailedResUserRoleDto toDetailedDto(UserRole userRole);
}