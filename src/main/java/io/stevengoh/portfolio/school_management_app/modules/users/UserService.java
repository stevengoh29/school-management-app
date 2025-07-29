package io.stevengoh.portfolio.school_management_app.modules.users;

import io.stevengoh.portfolio.school_management_app.common.dtos.PaginatedResult;
import io.stevengoh.portfolio.school_management_app.modules.users.dtos.request.CreateUserDto;
import io.stevengoh.portfolio.school_management_app.modules.users.dtos.request.SearchUserDto;
import io.stevengoh.portfolio.school_management_app.modules.users.dtos.request.UpdateUserDto;
import io.stevengoh.portfolio.school_management_app.modules.users.dtos.response.DetailedResUserDto;
import io.stevengoh.portfolio.school_management_app.modules.users.dtos.response.SimpleResUserDto;
import io.stevengoh.portfolio.school_management_app.modules.users.entities.User;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface UserService {
    // Reusable service method
    User findByUuidOrNull(UUID uuid);

    User findByUuidOrThrow(UUID uuid);

    PaginatedResult<SimpleResUserDto> getUsers(SearchUserDto searchDto, Pageable pageable);

    DetailedResUserDto getUserByUuidOrNull(UUID uuid);

    DetailedResUserDto getUserByUuidOrThrow(UUID uuid);

    DetailedResUserDto createUser(CreateUserDto createUserDto);

    DetailedResUserDto updateUser(UUID uuid, UpdateUserDto updateUserDto);

    void deleteUser(UUID uuid);
}