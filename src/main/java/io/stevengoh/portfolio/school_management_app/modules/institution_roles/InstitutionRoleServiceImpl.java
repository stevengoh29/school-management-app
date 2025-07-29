package io.stevengoh.portfolio.school_management_app.modules.institution_roles;

import io.stevengoh.portfolio.school_management_app.common.annotations.AutoAssignInstitution;
import io.stevengoh.portfolio.school_management_app.common.dtos.PaginatedResult;
import io.stevengoh.portfolio.school_management_app.common.specifications.SpecificationBuilder;
import io.stevengoh.portfolio.school_management_app.common.utils.AuthUtils;
import io.stevengoh.portfolio.school_management_app.core.auth.entities.CustomUserDetails;
import io.stevengoh.portfolio.school_management_app.modules.institution_roles.dtos.request.CreateInstitutionRoleDto;
import io.stevengoh.portfolio.school_management_app.modules.institution_roles.dtos.request.SearchInstitutionRoleDto;
import io.stevengoh.portfolio.school_management_app.modules.institution_roles.dtos.request.UpdateInstitutionRoleDto;
import io.stevengoh.portfolio.school_management_app.modules.institution_roles.dtos.response.DetailedResInstitutionRoleDto;
import io.stevengoh.portfolio.school_management_app.modules.institution_roles.dtos.response.SimpleResInstitutionRoleDto;
import io.stevengoh.portfolio.school_management_app.modules.institution_roles.entities.InstitutionRole;
import io.stevengoh.portfolio.school_management_app.modules.institution_roles.mappers.InstitutionRoleRequestMapper;
import io.stevengoh.portfolio.school_management_app.modules.institution_roles.mappers.InstitutionRoleResponseMapper;
import io.stevengoh.portfolio.school_management_app.modules.institutions.InstitutionService;
import io.stevengoh.portfolio.school_management_app.modules.institutions.entities.Institution;
import io.stevengoh.portfolio.school_management_app.modules.roles.RoleService;
import io.stevengoh.portfolio.school_management_app.modules.roles.entities.Role;
import io.stevengoh.portfolio.school_management_app.modules.roles.enums.RoleStatus;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class InstitutionRoleServiceImpl implements InstitutionRoleService {
    private final InstitutionService institutionService;
    private final RoleService roleService;

    private final InstitutionRoleRepository institutionRoleRepository;

    private final InstitutionRoleRequestMapper requestMapper;
    private final InstitutionRoleResponseMapper responseMapper;

    @Override
    public InstitutionRole findByUuidOrNull(UUID uuid) {
        return institutionRoleRepository.findByUuid(uuid)
                .orElse(null);
    }

    @Override
    public InstitutionRole findByUuidOrThrow(UUID uuid) {
        return institutionRoleRepository.findByUuid(uuid)
                .orElseThrow(() -> new EntityNotFoundException("Institution role not found"));
    }

    @Override
    public PaginatedResult<SimpleResInstitutionRoleDto> getInstitutionRoles(SearchInstitutionRoleDto searchDto, Pageable pageable) {
        SpecificationBuilder<InstitutionRole, SearchInstitutionRoleDto> builder = new SpecificationBuilder<>();
        Specification<InstitutionRole> spec = builder.build(searchDto);

        Page<InstitutionRole> pageResult = institutionRoleRepository.findAll(spec, pageable);
        List<SimpleResInstitutionRoleDto> dtoList = responseMapper.toDtoList(pageResult.getContent());

        return new PaginatedResult<>(dtoList, pageResult.getTotalElements(), pageable.getPageNumber(), pageable.getPageSize());
    }

    @Override
    public DetailedResInstitutionRoleDto getInstitutionRoleByUuidOrNull(UUID uuid) {
        InstitutionRole institutionRole = this.findByUuidOrNull(uuid);
        return responseMapper.toDetailedDto(institutionRole);
    }

    @Override
    public DetailedResInstitutionRoleDto getInstitutionRoleByUuidOrThrow(UUID uuid) {
        InstitutionRole institutionRole = this.findByUuidOrThrow(uuid);
        return responseMapper.toDetailedDto(institutionRole);
    }

    @Override
    @AutoAssignInstitution
    public DetailedResInstitutionRoleDto createInstitutionRole(UUID institutionUuid, CreateInstitutionRoleDto createInstitutionRoleDto) {
        System.out.println(createInstitutionRoleDto.getInstitution().getName());
        InstitutionRole institutionRole = requestMapper.createEntityToDto(createInstitutionRoleDto);

        if(createInstitutionRoleDto.getRoleUuid() != null) {
            Role role = roleService.findByUuidOrThrow(createInstitutionRoleDto.getRoleUuid());
            institutionRole.setBaseRole(role);
        }

        // Fallback: If institution is still null after auto-assign
        if (createInstitutionRoleDto.getInstitution() == null && institutionUuid != null) {
            Institution fallback = institutionService.findByUuidOrThrow(institutionUuid);
            createInstitutionRoleDto.setInstitution(fallback);
        }

        InstitutionRole savedInstitutionRole = institutionRoleRepository.save(institutionRole);
        return responseMapper.toDetailedDto(savedInstitutionRole);
    }

    @Override
    public DetailedResInstitutionRoleDto updateInstitutionRole(UUID uuid, UpdateInstitutionRoleDto updateInstitutionRoleDto) {
        InstitutionRole institutionRole = this.findByUuidOrThrow(uuid);
        requestMapper.updateEntityToDto(updateInstitutionRoleDto, institutionRole);

        InstitutionRole savedInstitutionRole = institutionRoleRepository.save(institutionRole);
        return responseMapper.toDetailedDto(savedInstitutionRole);
    }

    @Override
    public void deleteInstitutionRole(UUID uuid) {
        String username = AuthUtils.getUsername();
        log.warn("Deleting roles by {}", username);

        InstitutionRole role = findByUuidOrThrow(uuid);
        role.setStatus(RoleStatus.INACTIVE);
        role.setDeletedAt(LocalDateTime.now());
        role.setDeletedBy(username);

        institutionRoleRepository.save(role);
    }
}