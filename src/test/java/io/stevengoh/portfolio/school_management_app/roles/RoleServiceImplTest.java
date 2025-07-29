package io.stevengoh.portfolio.school_management_app.roles;

import io.stevengoh.portfolio.school_management_app.core.auth.entities.CustomUserDetails;
import io.stevengoh.portfolio.school_management_app.modules.roles.RoleRepository;
import io.stevengoh.portfolio.school_management_app.modules.roles.RoleServiceImpl;
import io.stevengoh.portfolio.school_management_app.modules.roles.dtos.request.CreateRoleDto;
import io.stevengoh.portfolio.school_management_app.modules.roles.dtos.request.UpdateRoleDto;
import io.stevengoh.portfolio.school_management_app.modules.roles.dtos.response.DetailedResRoleDto;
import io.stevengoh.portfolio.school_management_app.modules.roles.entities.Role;
import io.stevengoh.portfolio.school_management_app.modules.roles.enums.RoleStatus;
import io.stevengoh.portfolio.school_management_app.modules.roles.mappers.RoleRequestMapper;
import io.stevengoh.portfolio.school_management_app.modules.roles.mappers.RoleResponseMapper;
import io.stevengoh.portfolio.school_management_app.modules.users.entities.User;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RoleServiceImplTest {
    @Mock
    private RoleRepository roleRepository;

    @Mock
    private RoleRequestMapper requestMapper;

    @Mock
    private RoleResponseMapper responseMapper;

    @InjectMocks
    private RoleServiceImpl roleService;

    private final UUID uuid = UUID.randomUUID();

    @Test
    void testFindByUuidOrNull_found() {
        Role role = new Role();
        when(roleRepository.findByUuid(uuid)).thenReturn(Optional.of(role));

        assertThat(roleService.findByUuidOrNull(uuid)).isEqualTo(role);
    }

    @Test
    void testFindByUuidOrNull_notFound() {
        when(roleRepository.findByUuid(uuid)).thenReturn(Optional.empty());
        assertThat(roleService.findByUuidOrNull(uuid)).isNull();
    }

    @Test
    void testFindByUuidOrThrow_found() {
        Role role = new Role();
        when(roleRepository.findByUuid(uuid)).thenReturn(Optional.of(role));
        assertThat(roleService.findByUuidOrThrow(uuid)).isEqualTo(role);
    }

    @Test
    void testFindByUuidOrThrow_notFound() {
        when(roleRepository.findByUuid(uuid)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> roleService.findByUuidOrThrow(uuid));
    }

    @Test
    void testCreateRole() {
        CreateRoleDto dto = new CreateRoleDto();
        Role role = new Role();
        Role saved = new Role();
        DetailedResRoleDto response = new DetailedResRoleDto();

        when(requestMapper.createEntityToDto(dto)).thenReturn(role);
        when(roleRepository.save(role)).thenReturn(saved);
        when(responseMapper.toDetailedDto(saved)).thenReturn(response);

        assertThat(roleService.createRole(dto)).isEqualTo(response);
    }

    @Test
    void testUpdateRole() {
        UpdateRoleDto dto = new UpdateRoleDto();
        Role role = new Role();
        Role saved = new Role();
        DetailedResRoleDto response = new DetailedResRoleDto();

        when(roleRepository.findByUuid(uuid)).thenReturn(Optional.of(role));
        doNothing().when(requestMapper).updateEntityToDto(dto, role);
        when(roleRepository.save(role)).thenReturn(saved);
        when(responseMapper.toDetailedDto(saved)).thenReturn(response);

        assertThat(roleService.updateRole(uuid, dto)).isEqualTo(response);
    }

    @Test
    void testDeleteRole() {
        Role role = new Role();
        when(roleRepository.findByUuid(uuid)).thenReturn(Optional.of(role));

        // Arrange - create dummy authentication
        UUID userUuid = UUID.randomUUID();

        User user = new User();
        user.setUuid(userUuid);
        user.setUsername("steven");
        user.setPassword("123");

        CustomUserDetails userDetails = new CustomUserDetails(user);

        UsernamePasswordAuthenticationToken auth =
                new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(auth);

        roleService.deleteRole(uuid);

        assertThat(role.getStatus()).isEqualTo(RoleStatus.INACTIVE);
        assertThat(role.getDeletedAt()).isNotNull();
        verify(roleRepository).save(role);
    }
}