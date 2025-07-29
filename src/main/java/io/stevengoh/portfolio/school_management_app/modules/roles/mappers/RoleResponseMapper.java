package io.stevengoh.portfolio.school_management_app.modules.roles.mappers;

import io.stevengoh.portfolio.school_management_app.modules.roles.dtos.response.DetailedResRoleDto;
import io.stevengoh.portfolio.school_management_app.modules.roles.dtos.response.SimpleResRoleDto;
import io.stevengoh.portfolio.school_management_app.modules.roles.entities.Role;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RoleResponseMapper {
    List<SimpleResRoleDto> toDtoList(List<Role> institutions);

    SimpleResRoleDto toDto(Role institution);

    List<DetailedResRoleDto> toDetailedDtoList(List<Role> institutions);

    DetailedResRoleDto toDetailedDto(Role institution);
}
