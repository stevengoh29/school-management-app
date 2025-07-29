package io.stevengoh.portfolio.school_management_app.modules.users.mappers;

import io.stevengoh.portfolio.school_management_app.modules.institutions.dtos.response.DetailedResInstitutionDto;
import io.stevengoh.portfolio.school_management_app.modules.institutions.dtos.response.SimpleResInstitutionDto;
import io.stevengoh.portfolio.school_management_app.modules.institutions.entities.Institution;
import io.stevengoh.portfolio.school_management_app.modules.users.dtos.response.DetailedResUserDto;
import io.stevengoh.portfolio.school_management_app.modules.users.dtos.response.SimpleResUserDto;
import io.stevengoh.portfolio.school_management_app.modules.users.entities.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserResponseMapper {
    List<SimpleResUserDto> toDtoList(List<User> institutions);
    SimpleResUserDto toDto(User institution);

    List<DetailedResUserDto> toDetailedDtoList(List<User> institutions);
    DetailedResUserDto toDetailedDto(User institution);

    SimpleResInstitutionDto toDto(Institution institution);
}
