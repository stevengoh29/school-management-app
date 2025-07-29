package io.stevengoh.portfolio.school_management_app.modules.role_permissions;

import io.stevengoh.portfolio.school_management_app.common.dtos.BaseResponse;
import io.stevengoh.portfolio.school_management_app.common.dtos.PaginatedResult;
import io.stevengoh.portfolio.school_management_app.modules.role_permissions.dtos.request.CreateRolePermissionDto;
import io.stevengoh.portfolio.school_management_app.modules.role_permissions.dtos.request.DeleteBulkRolePermissionDto;
import io.stevengoh.portfolio.school_management_app.modules.role_permissions.dtos.request.SearchRolePermissionDto;
import io.stevengoh.portfolio.school_management_app.modules.role_permissions.dtos.response.DetailedResRolePermissionDto;
import io.stevengoh.portfolio.school_management_app.modules.role_permissions.dtos.response.SimpleResRolePermissionDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("role-permissions")
@RequiredArgsConstructor
public class RolePermissionController {
    private final RolePermissionService rolePermissionService;

    @GetMapping
    public ResponseEntity<BaseResponse<PaginatedResult<SimpleResRolePermissionDto>>> getRolePermissions(
            @ModelAttribute SearchRolePermissionDto searchRolePermissionDto,
            @PageableDefault(size = 20, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        PaginatedResult<SimpleResRolePermissionDto> result = rolePermissionService.getRolePermissions(searchRolePermissionDto, pageable);
        return ResponseEntity.ok(new BaseResponse<>(result));
    }

    @GetMapping("{uuid}")
    public ResponseEntity<BaseResponse<DetailedResRolePermissionDto>> getRolePermissionByUuid(
            @PathVariable("uuid") UUID uuid
    ) {
        DetailedResRolePermissionDto result = rolePermissionService.getRolePermissionByUuidOrThrow(uuid);
        return ResponseEntity.ok(new BaseResponse<>(result));
    }

    @PostMapping
    public ResponseEntity<BaseResponse<DetailedResRolePermissionDto>> createRolePermission(
            @RequestBody CreateRolePermissionDto request
    ) {
        DetailedResRolePermissionDto result = rolePermissionService.createRolePermission(request);
        return ResponseEntity.ok(new BaseResponse<>(result));
    }

    @PostMapping("bulk-insert")
    public ResponseEntity<BaseResponse<List<DetailedResRolePermissionDto>>> createBulkRolePermissions(
            @RequestBody List<CreateRolePermissionDto> request
    ) {
        List<DetailedResRolePermissionDto> result = rolePermissionService.createBulkRolePermissions(request);
        return ResponseEntity.ok(new BaseResponse<>(result));
    }

    @DeleteMapping("{uuid}")
    public ResponseEntity<BaseResponse<String>> deleteRolePermission(
            @PathVariable("uuid") UUID uuid
    ) {
        rolePermissionService.deleteRolePermission(uuid);
        return ResponseEntity.ok(new BaseResponse<>("Role permission deleted"));
    }

    @DeleteMapping("bulk-delete")
    public ResponseEntity<BaseResponse<String>> deleteBulkRolePermissions(
            @RequestBody DeleteBulkRolePermissionDto request
    ) {
        rolePermissionService.deleteBulkRolePermissions(request);
        return ResponseEntity.ok(new BaseResponse<>("Role permissions deleted."));
    }
}