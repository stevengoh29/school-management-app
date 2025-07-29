package io.stevengoh.portfolio.school_management_app.modules.user_institution_roles;

import io.stevengoh.portfolio.school_management_app.common.dtos.BaseResponse;
import io.stevengoh.portfolio.school_management_app.common.dtos.PaginatedResult;
import io.stevengoh.portfolio.school_management_app.modules.user_institution_roles.dtos.request.CreateUserInstitutionRoleDto;
import io.stevengoh.portfolio.school_management_app.modules.user_institution_roles.dtos.request.DeleteUserInstitutionRoleDto;
import io.stevengoh.portfolio.school_management_app.modules.user_institution_roles.dtos.request.SearchUserInstitutionRoleDto;
import io.stevengoh.portfolio.school_management_app.modules.user_institution_roles.dtos.response.DetailedResUserInstitutionRoleDto;
import io.stevengoh.portfolio.school_management_app.modules.user_institution_roles.dtos.response.SimpleResUserInstitutionRoleDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("institutions/:uuid/user-roles")
@RequiredArgsConstructor
public class UserInstitutionRoleController {
    private final UserInstitutionRoleService userRoleService;

    @GetMapping
    public ResponseEntity<BaseResponse<PaginatedResult<SimpleResUserInstitutionRoleDto>>> getUserInstitutionRoles(
            @ModelAttribute SearchUserInstitutionRoleDto searchUserInstitutionRoleDto,
            @PageableDefault(size = 20, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        PaginatedResult<SimpleResUserInstitutionRoleDto> response = userRoleService.getUserInstitutionRoles(searchUserInstitutionRoleDto, pageable);
        return ResponseEntity.ok(new BaseResponse<>(response));
    }

    @GetMapping("{uuid}")
    public ResponseEntity<BaseResponse<DetailedResUserInstitutionRoleDto>> getUserInstitutionRole(
            @PathVariable("uuid") UUID uuid
    ) {
        DetailedResUserInstitutionRoleDto response = userRoleService.getUserInstitutionRoleByUuidOrThrow(uuid);
        return ResponseEntity.ok(new BaseResponse<>(response));
    }

    @PostMapping
    public ResponseEntity<BaseResponse<DetailedResUserInstitutionRoleDto>> createUserInstitutionRole(
            @RequestBody CreateUserInstitutionRoleDto createUserInstitutionRoleDto
    ) {
        DetailedResUserInstitutionRoleDto response = userRoleService.createUserInstitutionRole(createUserInstitutionRoleDto);
        return ResponseEntity.ok(new BaseResponse<>(response));
    }

    @PostMapping("bulk-insert")
    public ResponseEntity<BaseResponse<List<DetailedResUserInstitutionRoleDto>>> bulkInsertUserInstitutionRoles(
            @RequestBody List<CreateUserInstitutionRoleDto> createUserInstitutionRoleDtos
    ) {
        List<DetailedResUserInstitutionRoleDto> response = userRoleService.createBulkUserInstitutionRole(createUserInstitutionRoleDtos);
        return ResponseEntity.ok(new BaseResponse<>(response));
    }

    @DeleteMapping("{uuid}")
    public ResponseEntity<BaseResponse<String>> deleteUserInstitutionRole(@PathVariable("uuid") UUID uuid) {
        userRoleService.deleteUserInstitutionRole(uuid);
        return ResponseEntity.ok(new BaseResponse<>("User role deleted."));
    }

    @DeleteMapping("bulk-delete")
    public ResponseEntity<BaseResponse<String>> deleteUserInstitutionRoles(
            @RequestBody DeleteUserInstitutionRoleDto deleteUserInstitutionRoleDto
    ) {
        userRoleService.deleteBulkUserInstitutionRole(deleteUserInstitutionRoleDto);
        return ResponseEntity.ok(new BaseResponse<>("User roles deleted."));
    }
}
