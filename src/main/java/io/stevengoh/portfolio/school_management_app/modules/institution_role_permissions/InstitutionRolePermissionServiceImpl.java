package io.stevengoh.portfolio.school_management_app.modules.institution_role_permissions;

import io.stevengoh.portfolio.school_management_app.common.annotations.AutoAssignInstitution;
import io.stevengoh.portfolio.school_management_app.common.dtos.PaginatedResult;
import io.stevengoh.portfolio.school_management_app.common.specifications.SpecificationBuilder;
import io.stevengoh.portfolio.school_management_app.modules.institution_role_permissions.dtos.request.CreateInstitutionRolePermissionDto;
import io.stevengoh.portfolio.school_management_app.modules.institution_role_permissions.dtos.request.DeleteInstitutionRolePermissionDto;
import io.stevengoh.portfolio.school_management_app.modules.institution_role_permissions.dtos.request.SearchInstitutionRolePermissionDto;
import io.stevengoh.portfolio.school_management_app.modules.institution_role_permissions.dtos.response.DetailedResInstitutionRolePermissionDto;
import io.stevengoh.portfolio.school_management_app.modules.institution_role_permissions.dtos.response.SimpleResInstitutionRolePermissionDto;
import io.stevengoh.portfolio.school_management_app.modules.institution_role_permissions.entities.InstitutionRolePermission;
import io.stevengoh.portfolio.school_management_app.modules.institution_role_permissions.mappers.InstitutionRolePermissionResponseMapper;
import io.stevengoh.portfolio.school_management_app.modules.institution_roles.InstitutionRoleService;
import io.stevengoh.portfolio.school_management_app.modules.institution_roles.entities.InstitutionRole;
import io.stevengoh.portfolio.school_management_app.modules.institutions.InstitutionService;
import io.stevengoh.portfolio.school_management_app.modules.institutions.entities.Institution;
import io.stevengoh.portfolio.school_management_app.modules.permissions.PermissionService;
import io.stevengoh.portfolio.school_management_app.modules.permissions.entities.Permission;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class InstitutionRolePermissionServiceImpl implements InstitutionRolePermissionService {
    private final InstitutionService institutionService;
    private final InstitutionRoleService institutionRoleService;
    private final PermissionService permissionService;

    private final InstitutionRolePermissionRepository institutionRolePermissionRepository;
    private final InstitutionRolePermissionResponseMapper responseMapper;

    @Override
    public InstitutionRolePermission findByUuidOrNull(UUID uuid) {
        return institutionRolePermissionRepository.findByUuid(uuid)
                .orElse(null);
    }

    @Override
    public InstitutionRolePermission findByUuidOrThrow(UUID uuid) {
        return institutionRolePermissionRepository.findByUuid(uuid)
                .orElseThrow(() -> new EntityNotFoundException("Institution role permission not found"));
    }

    @Override
    public PaginatedResult<SimpleResInstitutionRolePermissionDto> getInstitutionRolePermissions(SearchInstitutionRolePermissionDto searchDto, Pageable pageable) {
        SpecificationBuilder<InstitutionRolePermission, SearchInstitutionRolePermissionDto> builder = new SpecificationBuilder<>();
        Specification<InstitutionRolePermission> spec = builder.build(searchDto);

        Page<InstitutionRolePermission> pageResult = institutionRolePermissionRepository.findAll(spec, pageable);
        List<SimpleResInstitutionRolePermissionDto> dtoList = responseMapper.toSimpleResDtoList(pageResult.getContent());
        return new PaginatedResult<>(dtoList, pageResult.getTotalElements(), pageable.getPageNumber(), pageable.getPageSize());
    }

    @Override
    public DetailedResInstitutionRolePermissionDto getInstitutionRolePermissionByUuidOrNull(UUID uuid) {
        InstitutionRolePermission entity = findByUuidOrNull(uuid);
        return responseMapper.toDetailedResDto(entity);
    }

    @Override
    public DetailedResInstitutionRolePermissionDto getInstitutionRolePermissionByUuidOrThrow(UUID uuid) {
        InstitutionRolePermission entity = findByUuidOrThrow(uuid);
        return responseMapper.toDetailedResDto(entity);
    }

    @Override
    @AutoAssignInstitution
    public DetailedResInstitutionRolePermissionDto createInstitutionRolePermission(UUID institutionUuid, CreateInstitutionRolePermissionDto request) {
        Permission permission = permissionService.findByUuidOrThrow(request.getPermissionUuid());
        InstitutionRole role = institutionRoleService.findByUuidOrThrow(request.getInstitutionRoleUuid());

        InstitutionRolePermission entity = new InstitutionRolePermission(role, permission);

        if (request.getInstitution() != null) {
            entity.setInstitution(request.getInstitution());
        } else {
            Institution institution = institutionService.findByUuidOrThrow(institutionUuid);
            entity.setInstitution(institution);
        }

        InstitutionRolePermission saved = institutionRolePermissionRepository.save(entity);
        return responseMapper.toDetailedResDto(saved);
    }

    @Override
    public List<DetailedResInstitutionRolePermissionDto> createBulkInstitutionRolePermission(UUID institutionUuid, List<CreateInstitutionRolePermissionDto> createInstitutionRolePermissionDtos) {
        List<DetailedResInstitutionRolePermissionDto> savedList = new ArrayList<>();
        for (CreateInstitutionRolePermissionDto dto : createInstitutionRolePermissionDtos) {
            DetailedResInstitutionRolePermissionDto result =
                    createInstitutionRolePermission(institutionUuid, dto);

            savedList.add(result);
        }

        return savedList;
    }

    @Override
    public void deleteInstitutionRolePermission(UUID uuid) {
        InstitutionRolePermission entity = findByUuidOrThrow(uuid);
        institutionRolePermissionRepository.delete(entity);
    }

    @Override
    public void deleteBulkInstitutionRolePermission(DeleteInstitutionRolePermissionDto request) {
        List<UUID> uuids = request.getUuids();
        List<InstitutionRolePermission> rolePermissions = institutionRolePermissionRepository.findAllByUuidIn(uuids);
        institutionRolePermissionRepository.deleteAll(rolePermissions);
    }
}