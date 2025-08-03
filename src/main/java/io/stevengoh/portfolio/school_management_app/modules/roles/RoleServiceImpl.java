package io.stevengoh.portfolio.school_management_app.modules.roles;

import io.stevengoh.portfolio.school_management_app.common.dtos.PaginatedResult;
import io.stevengoh.portfolio.school_management_app.common.specifications.SpecificationBuilder;
import io.stevengoh.portfolio.school_management_app.core.auth.entities.CustomUserDetails;
import io.stevengoh.portfolio.school_management_app.modules.roles.dtos.request.CreateRoleDto;
import io.stevengoh.portfolio.school_management_app.modules.roles.dtos.request.SearchRoleDto;
import io.stevengoh.portfolio.school_management_app.modules.roles.dtos.request.UpdateRoleDto;
import io.stevengoh.portfolio.school_management_app.modules.roles.dtos.response.DetailedResRoleDto;
import io.stevengoh.portfolio.school_management_app.modules.roles.dtos.response.SimpleResRoleDto;
import io.stevengoh.portfolio.school_management_app.modules.roles.entities.Role;
import io.stevengoh.portfolio.school_management_app.modules.roles.enums.RoleStatus;
import io.stevengoh.portfolio.school_management_app.modules.roles.mappers.RoleRequestMapper;
import io.stevengoh.portfolio.school_management_app.modules.roles.mappers.RoleResponseMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
    private final RoleRequestMapper requestMapper;
    private final RoleResponseMapper responseMapper;

    @Override
    public Role findByUuidOrNull(UUID uuid) {
        return roleRepository.findByUuid(uuid)
                .orElse(null);
    }

    @Override
    public Role findByUuidOrThrow(UUID uuid) {
        return roleRepository.findByUuid(uuid)
                .orElseThrow(() -> new EntityNotFoundException("Role not found"));
    }

    @Override
    public PaginatedResult<SimpleResRoleDto> getRoles(SearchRoleDto searchDto, Pageable pageable) {
        SpecificationBuilder<Role, SearchRoleDto> builder = new SpecificationBuilder<>();
        Specification<Role> spec = builder.build(searchDto);

        Page<Role> pageResult = roleRepository.findAll(spec, pageable);
        List<SimpleResRoleDto> dtoList = responseMapper.toDtoList(pageResult.getContent());

        return new PaginatedResult<>(dtoList, pageResult.getTotalElements(), pageable.getPageNumber(), pageable.getPageSize());
    }

    @Override
    public DetailedResRoleDto getRoleByUuidOrNull(UUID uuid) {
        Role role = findByUuidOrNull(uuid);
        return responseMapper.toDetailedDto(role);
    }

    @Override
    public DetailedResRoleDto getRoleByUuidOrThrow(UUID uuid) {
        Role role = findByUuidOrThrow(uuid);
        return responseMapper.toDetailedDto(role);
    }

    @Override
    public DetailedResRoleDto createRole(CreateRoleDto createRoleDto) {
        Role toCreateRole = requestMapper.createEntityToDto(createRoleDto);
        Role createdRole = roleRepository.save(toCreateRole);
        return responseMapper.toDetailedDto(createdRole);
    }

    @Override
    public DetailedResRoleDto updateRole(UUID uuid, UpdateRoleDto updateRoleDto) {
        Role role = findByUuidOrThrow(uuid);
        requestMapper.updateEntityToDto(updateRoleDto, role);

        Role updatedRole = roleRepository.save(role);
        return responseMapper.toDetailedDto(updatedRole);
    }

    @Override
    public void deleteRole(UUID uuid) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();

        log.warn("Deleting roles by {}", customUserDetails.getUsername());

        Role role = findByUuidOrThrow(uuid);
        role.setStatus(RoleStatus.INACTIVE);
        role.setDeletedAt(Instant.now());
        role.setDeletedBy(customUserDetails.getUsername());

        roleRepository.save(role);
    }
}