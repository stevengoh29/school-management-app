package io.stevengoh.portfolio.school_management_app.modules.users;

import io.stevengoh.portfolio.school_management_app.common.dtos.BaseResponse;
import io.stevengoh.portfolio.school_management_app.common.dtos.PaginatedResult;
import io.stevengoh.portfolio.school_management_app.modules.users.dtos.request.CreateUserDto;
import io.stevengoh.portfolio.school_management_app.modules.users.dtos.request.SearchUserDto;
import io.stevengoh.portfolio.school_management_app.modules.users.dtos.request.UpdateUserDto;
import io.stevengoh.portfolio.school_management_app.modules.users.dtos.response.DetailedResUserDto;
import io.stevengoh.portfolio.school_management_app.modules.users.dtos.response.SimpleResUserDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping
    public ResponseEntity<BaseResponse<PaginatedResult<SimpleResUserDto>>> getUsers(
            @ModelAttribute SearchUserDto searchUserDto,
            @PageableDefault(size = 20, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        PaginatedResult<SimpleResUserDto> response = userService.getUsers(searchUserDto, pageable);
        return ResponseEntity.ok(new BaseResponse<>(response));
    }

    @GetMapping("{uuid}")
    public ResponseEntity<BaseResponse<DetailedResUserDto>> getUser(
            @PathVariable UUID uuid
    ) {
        DetailedResUserDto response = userService.getUserByUuidOrThrow(uuid);
        return ResponseEntity.ok(new BaseResponse<>(response));
    }

    @PostMapping
    public ResponseEntity<BaseResponse<DetailedResUserDto>> createUser(
            @Valid @RequestBody CreateUserDto createUserDto
    ) {
        DetailedResUserDto response = userService.createUser(createUserDto);
        return ResponseEntity.ok(new BaseResponse<>(response));
    }

    @PutMapping("{uuid}")
    public ResponseEntity<BaseResponse<DetailedResUserDto>> updateUserByUuid(
            @Valid @RequestBody UpdateUserDto updateUserDto,
            @PathVariable("uuid") UUID uuid
    ) {
        DetailedResUserDto response = userService.updateUser(uuid, updateUserDto);
        return ResponseEntity.ok(new BaseResponse<>(response));
    }

    @DeleteMapping("{uuid}")
    public ResponseEntity<BaseResponse<String>> deleteUserByUuid(
            @PathVariable("uuid") UUID uuid
    ) {
        userService.deleteUser(uuid);
        return ResponseEntity.ok(new BaseResponse<>("User deleted."));
    }
}