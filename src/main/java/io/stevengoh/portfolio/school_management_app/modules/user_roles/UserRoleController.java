package io.stevengoh.portfolio.school_management_app.modules.user_roles;

import io.stevengoh.portfolio.school_management_app.common.dtos.BaseResponse;
import io.stevengoh.portfolio.school_management_app.common.dtos.PaginatedResult;
import io.stevengoh.portfolio.school_management_app.modules.user_roles.dtos.request.CreateUserRoleDto;
import io.stevengoh.portfolio.school_management_app.modules.user_roles.dtos.request.DeleteUserRoleDto;
import io.stevengoh.portfolio.school_management_app.modules.user_roles.dtos.request.SearchUserRoleDto;
import io.stevengoh.portfolio.school_management_app.modules.user_roles.dtos.response.DetailedResUserRoleDto;
import io.stevengoh.portfolio.school_management_app.modules.user_roles.dtos.response.SimpleResUserRoleDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("user-roles")
@RequiredArgsConstructor
public class UserRoleController {
    private final UserRoleService userRoleService;

    @GetMapping
    public ResponseEntity<BaseResponse<PaginatedResult<SimpleResUserRoleDto>>> getUserRoles(
            @ModelAttribute SearchUserRoleDto searchUserRoleDto,
            @PageableDefault(size = 20, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        PaginatedResult<SimpleResUserRoleDto> response = userRoleService.getUserRoles(searchUserRoleDto, pageable);
        return ResponseEntity.ok(new BaseResponse<>(response));
    }

    @GetMapping("{uuid}")
    public ResponseEntity<BaseResponse<DetailedResUserRoleDto>> getUserRole(
            @PathVariable("uuid") UUID uuid
    ) {
        DetailedResUserRoleDto response = userRoleService.getUserRoleByUuidOrThrow(uuid);
        return ResponseEntity.ok(new BaseResponse<>(response));
    }

    @PostMapping
    public ResponseEntity<BaseResponse<DetailedResUserRoleDto>> createUserRole(
            @RequestBody CreateUserRoleDto createUserRoleDto
    ) {
        DetailedResUserRoleDto response = userRoleService.createUserRole(createUserRoleDto);
        return ResponseEntity.ok(new BaseResponse<>(response));
    }

    @PostMapping("bulk-insert")
    public ResponseEntity<BaseResponse<List<DetailedResUserRoleDto>>> bulkInsertUserRoles(
            @RequestBody List<CreateUserRoleDto> createUserRoleDtos
    ) {
        List<DetailedResUserRoleDto> response = userRoleService.createBulkUserRole(createUserRoleDtos);
        return ResponseEntity.ok(new BaseResponse<>(response));
    }

    @DeleteMapping("{uuid}")
    public ResponseEntity<BaseResponse<String>> deleteUserRole(@PathVariable("uuid") UUID uuid) {
        userRoleService.deleteUserRole(uuid);
        return ResponseEntity.ok(new BaseResponse<>("User role deleted."));
    }

    @DeleteMapping("bulk-delete")
    public ResponseEntity<BaseResponse<String>> deleteUserRoles(
            @RequestBody DeleteUserRoleDto deleteUserRoleDto
    ) {
        userRoleService.deleteBulkUserRole(deleteUserRoleDto);
        return ResponseEntity.ok(new BaseResponse<>("User roles deleted."));
    }
}