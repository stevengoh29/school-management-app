package io.stevengoh.portfolio.school_management_app.modules.users.mappers;

import io.stevengoh.portfolio.school_management_app.modules.users.dtos.request.CreateUserDto;
import io.stevengoh.portfolio.school_management_app.modules.users.dtos.request.UpdateUserDto;
import io.stevengoh.portfolio.school_management_app.modules.users.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserRequestMapper {
    User createEntityToDto(CreateUserDto request);

    void updateEntityToDto(UpdateUserDto request, @MappingTarget User entity);
}
