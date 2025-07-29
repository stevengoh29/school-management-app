package io.stevengoh.portfolio.school_management_app.modules.permissions.mappers;

import io.stevengoh.portfolio.school_management_app.modules.permissions.PermissionController;
import io.stevengoh.portfolio.school_management_app.modules.permissions.dtos.response.DetailedResPermissionDto;
import io.stevengoh.portfolio.school_management_app.modules.permissions.dtos.response.SimpleResPermissionDto;
import io.stevengoh.portfolio.school_management_app.modules.permissions.entities.Permission;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PermissionResponseMapper {
    SimpleResPermissionDto toSimpleResDto(Permission permission);
    List<SimpleResPermissionDto> toSimpleResDtoList(List<Permission> permissions);

    DetailedResPermissionDto toDetailedResDto(Permission permission);
    List<DetailedResPermissionDto> toDetailedResDtolist(List<Permission> permissions);
}