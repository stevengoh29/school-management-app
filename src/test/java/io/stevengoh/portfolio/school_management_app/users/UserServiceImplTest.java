package io.stevengoh.portfolio.school_management_app.users;

import io.stevengoh.portfolio.school_management_app.modules.users.UserRepository;
import io.stevengoh.portfolio.school_management_app.modules.users.UserServiceImpl;
import io.stevengoh.portfolio.school_management_app.modules.users.dtos.request.CreateUserDto;
import io.stevengoh.portfolio.school_management_app.modules.users.dtos.request.UpdateUserDto;
import io.stevengoh.portfolio.school_management_app.modules.users.dtos.response.DetailedResUserDto;
import io.stevengoh.portfolio.school_management_app.modules.users.entities.User;
import io.stevengoh.portfolio.school_management_app.modules.users.enums.UserStatus;
import io.stevengoh.portfolio.school_management_app.modules.users.mappers.UserRequestMapper;
import io.stevengoh.portfolio.school_management_app.modules.users.mappers.UserResponseMapper;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserRequestMapper requestMapper;

    @Mock
    private UserResponseMapper responseMapper;

    @InjectMocks
    private UserServiceImpl userService;


    private final UUID uuid = UUID.randomUUID();

    @Test
    void testFindByUuidOrNull_found() {
        User user = new User();
        when(userRepository.findByUuid(uuid)).thenReturn(Optional.of(user));

        assertThat(userService.findByUuidOrNull(uuid)).isEqualTo(user);
    }

    @Test
    void testFindByUuidOrNull_notFound() {
        when(userRepository.findByUuid(uuid)).thenReturn(Optional.empty());
        assertThat(userService.findByUuidOrNull(uuid)).isNull();
    }

    @Test
    void testFindByUuidOrThrow_found() {
        User user = new User();
        when(userRepository.findByUuid(uuid)).thenReturn(Optional.of(user));
        assertThat(userService.findByUuidOrThrow(uuid)).isEqualTo(user);
    }

    @Test
    void testFindByUuidOrThrow_notFound() {
        when(userRepository.findByUuid(uuid)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> userService.findByUuidOrThrow(uuid));
    }

    @Test
    void testCreateUser() {
        CreateUserDto dto = new CreateUserDto();
        User user = new User();
        User saved = new User();
        DetailedResUserDto response = new DetailedResUserDto();

        when(requestMapper.createEntityToDto(dto)).thenReturn(user);
        when(userRepository.save(user)).thenReturn(saved);
        when(responseMapper.toDetailedDto(saved)).thenReturn(response);

        assertThat(userService.createUser(dto)).isEqualTo(response);
    }

    @Test
    void testUpdateUser() {
        UpdateUserDto dto = new UpdateUserDto();
        User user = new User();
        User saved = new User();
        DetailedResUserDto response = new DetailedResUserDto();

        when(userRepository.findByUuid(uuid)).thenReturn(Optional.of(user));
        doNothing().when(requestMapper).updateEntityToDto(dto, user);
        when(userRepository.save(user)).thenReturn(saved);
        when(responseMapper.toDetailedDto(saved)).thenReturn(response);

        assertThat(userService.updateUser(uuid, dto)).isEqualTo(response);
    }

    @Test
    void testDeleteUser() {
        User user = new User();
        when(userRepository.findByUuid(uuid)).thenReturn(Optional.of(user));

        userService.deleteUser(uuid);

        assertThat(user.getStatus()).isEqualTo(UserStatus.INACTIVE);
        assertThat(user.getDeletedAt()).isNotNull();
        verify(userRepository).delete(user);
    }
}