package io.stevengoh.portfolio.school_management_app.modules.roles.mappers;

import io.stevengoh.portfolio.school_management_app.modules.roles.dtos.request.CreateRoleDto;
import io.stevengoh.portfolio.school_management_app.modules.roles.dtos.request.UpdateRoleDto;
import io.stevengoh.portfolio.school_management_app.modules.roles.entities.Role;
import io.stevengoh.portfolio.school_management_app.modules.users.dtos.request.CreateUserDto;
import io.stevengoh.portfolio.school_management_app.modules.users.dtos.request.UpdateUserDto;
import io.stevengoh.portfolio.school_management_app.modules.users.entities.User;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface RoleRequestMapper {
    Role createEntityToDto(CreateRoleDto request);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityToDto(UpdateRoleDto request, @MappingTarget Role entity);
}