package io.stevengoh.portfolio.school_management_app.modules.role_permissions;

import io.stevengoh.portfolio.school_management_app.common.dtos.PaginatedResult;
import io.stevengoh.portfolio.school_management_app.common.exceptions.MissingBodyRequestException;
import io.stevengoh.portfolio.school_management_app.common.specifications.SpecificationBuilder;
import io.stevengoh.portfolio.school_management_app.modules.permissions.PermissionService;
import io.stevengoh.portfolio.school_management_app.modules.permissions.entities.Permission;
import io.stevengoh.portfolio.school_management_app.modules.role_permissions.dtos.request.CreateRolePermissionDto;
import io.stevengoh.portfolio.school_management_app.modules.role_permissions.dtos.request.DeleteBulkRolePermissionDto;
import io.stevengoh.portfolio.school_management_app.modules.role_permissions.dtos.request.SearchRolePermissionDto;
import io.stevengoh.portfolio.school_management_app.modules.role_permissions.dtos.response.DetailedResRolePermissionDto;
import io.stevengoh.portfolio.school_management_app.modules.role_permissions.dtos.response.SimpleResRolePermissionDto;
import io.stevengoh.portfolio.school_management_app.modules.role_permissions.entities.RolePermission;
import io.stevengoh.portfolio.school_management_app.modules.role_permissions.mappers.RolePermissionRequestMapper;
import io.stevengoh.portfolio.school_management_app.modules.role_permissions.mappers.RolePermissionResponseMapper;
import io.stevengoh.portfolio.school_management_app.modules.roles.RoleService;
import io.stevengoh.portfolio.school_management_app.modules.roles.entities.Role;
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
public class RolePermissionServiceImpl implements RolePermissionService {
    private final RolePermissionRepository rolePermissionRepository;

    private final RoleService roleService;
    private final PermissionService permissionService;

    private final RolePermissionRequestMapper requestMapper;
    private final RolePermissionResponseMapper responseMapper;

    @Override
    public RolePermission findByUuidOrNull(UUID uuid) {
        return rolePermissionRepository.findByUuid(uuid)
                .orElse(null);
    }

    @Override
    public RolePermission findByUuidOrThrow(UUID uuid) {
        return rolePermissionRepository.findByUuid(uuid)
                .orElseThrow(() -> new EntityNotFoundException("Role permission not found"));
    }

    @Override
    public PaginatedResult<SimpleResRolePermissionDto> getRolePermissions(SearchRolePermissionDto searchRolePermissionDto, Pageable pageable) {
        SpecificationBuilder<RolePermission, SearchRolePermissionDto> builder = new SpecificationBuilder<>();
        Specification<RolePermission> spec = builder.build(searchRolePermissionDto);

        Page<RolePermission> pageResult = rolePermissionRepository.findAll(spec, pageable);
        List<SimpleResRolePermissionDto> dtoList = responseMapper.toSimpleResDtoList(pageResult.getContent());

        return new PaginatedResult<>(dtoList, pageResult.getTotalElements(), pageable.getPageNumber(), pageable.getPageSize());
    }

    @Override
    public DetailedResRolePermissionDto getRolePermissionByUuidOrNull(UUID uuid) {
        RolePermission rolePermission = this.findByUuidOrNull(uuid);
        return responseMapper.toDetailedResDto(rolePermission);
    }

    @Override
    public DetailedResRolePermissionDto getRolePermissionByUuidOrThrow(UUID uuid) {
        RolePermission rolePermission = this.findByUuidOrThrow(uuid);
        return responseMapper.toDetailedResDto(rolePermission);
    }

    @Override
    public DetailedResRolePermissionDto createRolePermission(CreateRolePermissionDto request) {
        if (request.getPermissionUuid() == null || request.getRoleUuid() == null) {
            throw new MissingBodyRequestException("Request body is missing required payload.");
        }

        Permission permission = permissionService.findByUuidOrThrow(request.getPermissionUuid());
        Role role = roleService.findByUuidOrThrow(request.getRoleUuid());

        RolePermission rolePermission = new RolePermission(role, permission);
        RolePermission saved = rolePermissionRepository.save(rolePermission);

        return responseMapper.toDetailedResDto(saved);
    }

    @Override
    public List<DetailedResRolePermissionDto> createBulkRolePermissions(List<CreateRolePermissionDto> request) {
        List<RolePermission> toSave = new ArrayList<>();

        for (CreateRolePermissionDto dto : request) {
            if (dto.getPermissionUuid() == null || dto.getRoleUuid() == null) {
                throw new MissingBodyRequestException("Request body is missing required payload.");
            }

            Permission permission = permissionService.findByUuidOrThrow(dto.getPermissionUuid());
            Role role = roleService.findByUuidOrThrow(dto.getRoleUuid());

            RolePermission rolePermission = new RolePermission(role, permission);
            toSave.add(rolePermission);
        }

        List<RolePermission> saved = rolePermissionRepository.saveAll(toSave);
        return responseMapper.toDetailedResDtoList(saved);
    }

    @Override
    public void deleteRolePermission(UUID uuid) {
        RolePermission rolePermission = this.findByUuidOrThrow(uuid);
        rolePermissionRepository.delete(rolePermission);
    }

    @Override
    public void deleteBulkRolePermissions(DeleteBulkRolePermissionDto deleteBulkRolePermissionDto) {
        List<UUID> uuids = deleteBulkRolePermissionDto.getRolePermissionUuids();
        List<RolePermission> rolePermissions = rolePermissionRepository.findAllByUuidIn(uuids);
        rolePermissionRepository.deleteAll(rolePermissions);
    }
}