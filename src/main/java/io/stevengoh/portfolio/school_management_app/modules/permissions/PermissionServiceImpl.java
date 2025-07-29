package io.stevengoh.portfolio.school_management_app.modules.permissions;

import io.stevengoh.portfolio.school_management_app.common.dtos.PaginatedResult;
import io.stevengoh.portfolio.school_management_app.common.specifications.SpecificationBuilder;
import io.stevengoh.portfolio.school_management_app.modules.permissions.dtos.request.CreatePermissionDto;
import io.stevengoh.portfolio.school_management_app.modules.permissions.dtos.request.SearchPermissionDto;
import io.stevengoh.portfolio.school_management_app.modules.permissions.dtos.request.UpdatePermissionDto;
import io.stevengoh.portfolio.school_management_app.modules.permissions.dtos.response.DetailedResPermissionDto;
import io.stevengoh.portfolio.school_management_app.modules.permissions.dtos.response.SimpleResPermissionDto;
import io.stevengoh.portfolio.school_management_app.modules.permissions.entities.Permission;
import io.stevengoh.portfolio.school_management_app.modules.permissions.mappers.PermissionRequestMapper;
import io.stevengoh.portfolio.school_management_app.modules.permissions.mappers.PermissionResponseMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PermissionServiceImpl implements PermissionService {
    private final PermissionRepository permissionRepository;
    private final PermissionRequestMapper requestMapper;
    private final PermissionResponseMapper responseMapper;

    @Override
    public Permission findByUuidOrNull(UUID uuid) {
        return permissionRepository.findByUuid(uuid)
                .orElse(null);
    }

    @Override
    public Permission findByUuidOrThrow(UUID uuid) {
        return permissionRepository.findByUuid(uuid)
                .orElseThrow(() -> new EntityNotFoundException("Permission not found"));
    }

    @Override
    public PaginatedResult<SimpleResPermissionDto> getPermissions(SearchPermissionDto searchDto, Pageable pageable) {
        SpecificationBuilder<Permission, SearchPermissionDto> builder = new SpecificationBuilder<>();
        Specification<Permission> spec = builder.build(searchDto);

        Page<Permission> pageResult = permissionRepository.findAll(spec, pageable);
        List<SimpleResPermissionDto> dtoList = responseMapper.toSimpleResDtoList(pageResult.getContent());

        return new PaginatedResult<>(dtoList, pageResult.getTotalElements(), pageable.getPageNumber(), pageable.getPageSize());
    }

    @Override
    public DetailedResPermissionDto getPermissionByUuidOrNull(UUID uuid) {
        Permission permission = findByUuidOrNull(uuid);
        return responseMapper.toDetailedResDto(permission);
    }

    @Override
    public DetailedResPermissionDto getPermissionByUuidOrThrow(UUID uuid) {
        Permission permission = findByUuidOrThrow(uuid);
        return responseMapper.toDetailedResDto(permission);
    }

    @Override
    public DetailedResPermissionDto createPermission(CreatePermissionDto createPermissionDto) {
        Permission permission = requestMapper.createDtoToEntity(createPermissionDto);
        Permission saved = permissionRepository.save(permission);
        return responseMapper.toDetailedResDto(saved);
    }

    @Override
    public DetailedResPermissionDto updatePermission(UUID uuid, UpdatePermissionDto updatePermissionDto) {
        Permission permission = findByUuidOrThrow(uuid);
        requestMapper.updateDtoToEntity(updatePermissionDto, permission);

        Permission saved = permissionRepository.save(permission);
        return responseMapper.toDetailedResDto(saved);
    }

    @Override
    public void deletePermission(UUID uuid) {
        Permission permission = findByUuidOrThrow(uuid);
        permissionRepository.delete(permission);
    }
}