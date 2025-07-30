package io.stevengoh.portfolio.school_management_app.modules.user_institution_roles;

import io.stevengoh.portfolio.school_management_app.common.annotations.AutoAssignInstitution;
import io.stevengoh.portfolio.school_management_app.common.dtos.PaginatedResult;
import io.stevengoh.portfolio.school_management_app.common.specifications.SpecificationBuilder;
import io.stevengoh.portfolio.school_management_app.modules.institution_roles.InstitutionRoleService;
import io.stevengoh.portfolio.school_management_app.modules.institution_roles.entities.InstitutionRole;
import io.stevengoh.portfolio.school_management_app.modules.institutions.InstitutionService;
import io.stevengoh.portfolio.school_management_app.modules.institutions.entities.Institution;
import io.stevengoh.portfolio.school_management_app.modules.roles.RoleService;
import io.stevengoh.portfolio.school_management_app.modules.roles.entities.Role;
import io.stevengoh.portfolio.school_management_app.modules.user_institution_roles.UserInstitutionRoleRepository;
import io.stevengoh.portfolio.school_management_app.modules.user_institution_roles.UserInstitutionRoleService;
import io.stevengoh.portfolio.school_management_app.modules.user_institution_roles.dtos.request.CreateUserInstitutionRoleDto;
import io.stevengoh.portfolio.school_management_app.modules.user_institution_roles.dtos.request.DeleteUserInstitutionRoleDto;
import io.stevengoh.portfolio.school_management_app.modules.user_institution_roles.dtos.request.SearchUserInstitutionRoleDto;
import io.stevengoh.portfolio.school_management_app.modules.user_institution_roles.dtos.response.DetailedResUserInstitutionRoleDto;
import io.stevengoh.portfolio.school_management_app.modules.user_institution_roles.dtos.response.SimpleResUserInstitutionRoleDto;
import io.stevengoh.portfolio.school_management_app.modules.user_institution_roles.entities.UserInstitutionRole;
import io.stevengoh.portfolio.school_management_app.modules.user_institution_roles.mappers.UserInstitutionRoleResponseMapper;
import io.stevengoh.portfolio.school_management_app.modules.users.UserService;
import io.stevengoh.portfolio.school_management_app.modules.users.entities.User;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserInstitutionRoleServiceImpl implements UserInstitutionRoleService {
    private final UserService userService;
    private final InstitutionRoleService roleService;
    private final InstitutionService institutionService;

    private final UserInstitutionRoleRepository userRoleRepository;
    private final UserInstitutionRoleResponseMapper responseMapper;

    @Override
    public UserInstitutionRole findByUuidOrNull(UUID uuid) {
        return userRoleRepository.findByUuid(uuid)
                .orElse(null);
    }

    @Override
    public UserInstitutionRole findByUuidOrThrow(UUID uuid) {
        return userRoleRepository.findByUuid(uuid)
                .orElseThrow(() -> new EntityNotFoundException("User role not found"));
    }

    @Override
    public PaginatedResult<SimpleResUserInstitutionRoleDto> getUserInstitutionRoles(SearchUserInstitutionRoleDto searchDto, Pageable pageable) {
        SpecificationBuilder<UserInstitutionRole, SearchUserInstitutionRoleDto> builder = new SpecificationBuilder<>();
        Specification<UserInstitutionRole> spec = builder.build(searchDto);

        Page<UserInstitutionRole> pageResult = userRoleRepository.findAll(spec, pageable);
        List<SimpleResUserInstitutionRoleDto> dtoList = responseMapper.toDtoList(pageResult.getContent());
        return new PaginatedResult<>(dtoList, pageResult.getTotalElements(), pageable.getPageNumber(), pageable.getPageSize());
    }

    @Override
    public DetailedResUserInstitutionRoleDto getUserInstitutionRoleByUuidOrNull(UUID uuid) {
        UserInstitutionRole userRole = findByUuidOrNull(uuid);
        return responseMapper.toDetailedDto(userRole);
    }

    @Override
    public DetailedResUserInstitutionRoleDto getUserInstitutionRoleByUuidOrThrow(UUID uuid) {
        UserInstitutionRole userRole = findByUuidOrThrow(uuid);
        return responseMapper.toDetailedDto(userRole);
    }

    @Override
    @AutoAssignInstitution
    public DetailedResUserInstitutionRoleDto createUserInstitutionRole(UUID uuid, CreateUserInstitutionRoleDto createUserInstitutionRoleDto) {
        User user = userService.findByUuidOrThrow(createUserInstitutionRoleDto.getUserUuid());
        InstitutionRole role = roleService.findByUuidOrThrow(createUserInstitutionRoleDto.getInstitutionRoleUuid());

        UserInstitutionRole userRole = new UserInstitutionRole(user, role);

        if (createUserInstitutionRoleDto.getInstitution() != null) {
            userRole.setInstitution(createUserInstitutionRoleDto.getInstitution());
        } else {
            Institution institution = institutionService.findByUuidOrThrow(uuid);
            userRole.setInstitution(institution);
        }
        UserInstitutionRole createdUserInstitutionRole = userRoleRepository.save(userRole);
        return responseMapper.toDetailedDto(createdUserInstitutionRole);
    }

    @Override
    public List<DetailedResUserInstitutionRoleDto> createBulkUserInstitutionRole(UUID uuid, List<CreateUserInstitutionRoleDto> createUserInstitutionRoleDtos) {
        List<DetailedResUserInstitutionRoleDto> createdUsers = new ArrayList<>();

        for (CreateUserInstitutionRoleDto createUserInstitutionRoleDto : createUserInstitutionRoleDtos) {
            DetailedResUserInstitutionRoleDto createdUser = this.createUserInstitutionRole(uuid, createUserInstitutionRoleDto);
            createdUsers.add(createdUser);
        }

        return createdUsers;
    }

    @Override
    public void deleteUserInstitutionRole(UUID uuid) {
        UserInstitutionRole userRole = findByUuidOrThrow(uuid);
        userRoleRepository.delete(userRole);
    }

    @Override
    public void deleteBulkUserInstitutionRole(DeleteUserInstitutionRoleDto request) {
        for (UUID uuid : request.getUserInstitutionRoleUuids()) {
            deleteUserInstitutionRole(uuid);
        }
    }
}