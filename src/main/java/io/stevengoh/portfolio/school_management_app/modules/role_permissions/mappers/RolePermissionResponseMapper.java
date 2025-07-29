package io.stevengoh.portfolio.school_management_app.modules.role_permissions.mappers;

import io.stevengoh.portfolio.school_management_app.modules.role_permissions.dtos.response.DetailedResRolePermissionDto;
import io.stevengoh.portfolio.school_management_app.modules.role_permissions.dtos.response.SimpleResRolePermissionDto;
import io.stevengoh.portfolio.school_management_app.modules.role_permissions.entities.RolePermission;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RolePermissionResponseMapper {
    SimpleResRolePermissionDto toSimpleResDto(RolePermission rolePermission);
    List<SimpleResRolePermissionDto> toSimpleResDtoList(List<RolePermission> rolePermission);

    DetailedResRolePermissionDto toDetailedResDto(RolePermission rolePermission);
    List<DetailedResRolePermissionDto> toDetailedResDtoList(List<RolePermission> rolePermission);
}