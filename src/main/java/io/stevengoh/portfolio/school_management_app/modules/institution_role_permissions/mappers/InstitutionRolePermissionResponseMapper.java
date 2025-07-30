package io.stevengoh.portfolio.school_management_app.modules.institution_role_permissions.mappers;

import io.stevengoh.portfolio.school_management_app.modules.institution_role_permissions.dtos.response.DetailedResInstitutionRolePermissionDto;
import io.stevengoh.portfolio.school_management_app.modules.institution_role_permissions.dtos.response.SimpleResInstitutionRolePermissionDto;
import io.stevengoh.portfolio.school_management_app.modules.institution_role_permissions.entities.InstitutionRolePermission;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface InstitutionRolePermissionResponseMapper {
    SimpleResInstitutionRolePermissionDto toSimpleResDto(InstitutionRolePermission entity);
    List<SimpleResInstitutionRolePermissionDto> toSimpleResDtoList(List<InstitutionRolePermission> entity);

    DetailedResInstitutionRolePermissionDto toDetailedResDto(InstitutionRolePermission entity);
    List<DetailedResInstitutionRolePermissionDto> toDetailedResDtoList(List<InstitutionRolePermission> entity);
}