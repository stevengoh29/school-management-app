package io.stevengoh.portfolio.school_management_app.modules.permissions.mappers;

import io.stevengoh.portfolio.school_management_app.modules.permissions.PermissionController;
import io.stevengoh.portfolio.school_management_app.modules.permissions.dtos.request.CreatePermissionDto;
import io.stevengoh.portfolio.school_management_app.modules.permissions.dtos.request.UpdatePermissionDto;
import io.stevengoh.portfolio.school_management_app.modules.permissions.entities.Permission;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface PermissionRequestMapper {
    Permission createDtoToEntity(CreatePermissionDto request);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateDtoToEntity(UpdatePermissionDto request, @MappingTarget Permission entity);
}