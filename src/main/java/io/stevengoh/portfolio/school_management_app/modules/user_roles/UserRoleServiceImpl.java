package io.stevengoh.portfolio.school_management_app.modules.user_roles;

import io.stevengoh.portfolio.school_management_app.common.dtos.PaginatedResult;
import io.stevengoh.portfolio.school_management_app.common.specifications.SpecificationBuilder;
import io.stevengoh.portfolio.school_management_app.modules.institutions.InstitutionService;
import io.stevengoh.portfolio.school_management_app.modules.institutions.entities.Institution;
import io.stevengoh.portfolio.school_management_app.modules.roles.RoleService;
import io.stevengoh.portfolio.school_management_app.modules.roles.entities.Role;
import io.stevengoh.portfolio.school_management_app.modules.user_roles.dtos.request.CreateUserRoleDto;
import io.stevengoh.portfolio.school_management_app.modules.user_roles.dtos.request.DeleteUserRoleDto;
import io.stevengoh.portfolio.school_management_app.modules.user_roles.dtos.request.SearchUserRoleDto;
import io.stevengoh.portfolio.school_management_app.modules.user_roles.dtos.response.DetailedResUserRoleDto;
import io.stevengoh.portfolio.school_management_app.modules.user_roles.dtos.response.SimpleResUserRoleDto;
import io.stevengoh.portfolio.school_management_app.modules.user_roles.entities.UserRole;
import io.stevengoh.portfolio.school_management_app.modules.user_roles.mappers.UserRoleRequestMapper;
import io.stevengoh.portfolio.school_management_app.modules.user_roles.mappers.UserRoleResponseMapper;
import io.stevengoh.portfolio.school_management_app.modules.users.UserService;
import io.stevengoh.portfolio.school_management_app.modules.users.entities.User;
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
public class UserRoleServiceImpl implements UserRoleService {
    private final UserService userService;
    private final RoleService roleService;
    private final InstitutionService institutionService;

    private final UserRoleRepository userRoleRepository;
    private final UserRoleResponseMapper responseMapper;

    @Override
    public UserRole findByUuidOrNull(UUID uuid) {
        return userRoleRepository.findByUuid(uuid)
                .orElse(null);
    }

    @Override
    public UserRole findByUuidOrThrow(UUID uuid) {
        return userRoleRepository.findByUuid(uuid)
                .orElseThrow(() -> new EntityNotFoundException("User role not found"));
    }

    @Override
    public PaginatedResult<SimpleResUserRoleDto> getUserRoles(SearchUserRoleDto searchDto, Pageable pageable) {
        SpecificationBuilder<UserRole, SearchUserRoleDto> builder = new SpecificationBuilder<>();
        Specification<UserRole> spec = builder.build(searchDto);

        Page<UserRole> pageResult = userRoleRepository.findAll(spec, pageable);
        List<SimpleResUserRoleDto> dtoList = responseMapper.toDtoList(pageResult.getContent());
        return new PaginatedResult<>(dtoList, pageResult.getTotalElements(), pageable.getPageNumber(), pageable.getPageSize());
    }

    @Override
    public DetailedResUserRoleDto getUserRoleByUuidOrNull(UUID uuid) {
        UserRole userRole = findByUuidOrNull(uuid);
        return responseMapper.toDetailedDto(userRole);
    }

    @Override
    public DetailedResUserRoleDto getUserRoleByUuidOrThrow(UUID uuid) {
        UserRole userRole = findByUuidOrThrow(uuid);
        return responseMapper.toDetailedDto(userRole);
    }

    @Override
    public DetailedResUserRoleDto createUserRole(CreateUserRoleDto createUserRoleDto) {
        User user = userService.findByUuidOrThrow(createUserRoleDto.getUserUuid());
        Role role = roleService.findByUuidOrThrow(createUserRoleDto.getRoleUuid());

        UserRole userRole = new UserRole(user, role);

        if (createUserRoleDto.getInstitutionUuid() != null) {
            Institution institution = institutionService.findByUuidOrThrow(createUserRoleDto.getInstitutionUuid());
            userRole.setInstitution(institution);
        }

        UserRole createdUserRole = userRoleRepository.save(userRole);
        return responseMapper.toDetailedDto(createdUserRole);
    }

    @Override
    public List<DetailedResUserRoleDto> createBulkUserRole(List<CreateUserRoleDto> createUserRoleDtos) {
        List<DetailedResUserRoleDto> createdUsers = new ArrayList<>();

        for (CreateUserRoleDto createUserRoleDto : createUserRoleDtos) {
            DetailedResUserRoleDto createdUser = this.createUserRole(createUserRoleDto);
            createdUsers.add(createdUser);
        }

        return createdUsers;
    }

    @Override
    public void deleteUserRole(UUID uuid) {
        UserRole userRole = findByUuidOrThrow(uuid);
        userRoleRepository.delete(userRole);
    }

    @Override
    public void deleteBulkUserRole(DeleteUserRoleDto request) {
        for (UUID uuid : request.getUserRoleUuids()) {
            deleteUserRole(uuid);
        }
    }
}