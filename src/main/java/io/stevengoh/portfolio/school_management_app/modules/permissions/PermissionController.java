package io.stevengoh.portfolio.school_management_app.modules.permissions;

import io.stevengoh.portfolio.school_management_app.common.dtos.BaseResponse;
import io.stevengoh.portfolio.school_management_app.common.dtos.PaginatedResult;
import io.stevengoh.portfolio.school_management_app.modules.permissions.dtos.request.CreatePermissionDto;
import io.stevengoh.portfolio.school_management_app.modules.permissions.dtos.request.SearchPermissionDto;
import io.stevengoh.portfolio.school_management_app.modules.permissions.dtos.request.UpdatePermissionDto;
import io.stevengoh.portfolio.school_management_app.modules.permissions.dtos.response.DetailedResPermissionDto;
import io.stevengoh.portfolio.school_management_app.modules.permissions.dtos.response.SimpleResPermissionDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("permissions")
@RequiredArgsConstructor
public class PermissionController {
    private final PermissionService permissionService;

    @GetMapping
    public ResponseEntity<BaseResponse<PaginatedResult<SimpleResPermissionDto>>> getPermissions(
            @ModelAttribute SearchPermissionDto searchPermissionDto,
            @PageableDefault(size = 20, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        PaginatedResult<SimpleResPermissionDto> response = permissionService.getPermissions(searchPermissionDto, pageable);
        return ResponseEntity.ok(new BaseResponse<>(response));
    }

    @GetMapping("{uuid}")
    public ResponseEntity<BaseResponse<DetailedResPermissionDto>> getPermission(
            @PathVariable UUID uuid
    ) {
        DetailedResPermissionDto response = permissionService.getPermissionByUuidOrThrow(uuid);
        return ResponseEntity.ok(new BaseResponse<>(response));
    }

    @PostMapping
    public ResponseEntity<BaseResponse<DetailedResPermissionDto>> createPermission(
            @Valid @RequestBody CreatePermissionDto createPermissionDto
    ) {
        DetailedResPermissionDto response = permissionService.createPermission(createPermissionDto);
        return ResponseEntity.ok(new BaseResponse<>(response));
    }

    @PutMapping("{uuid}")
    public ResponseEntity<BaseResponse<DetailedResPermissionDto>> updatePermissionByUuid(
            @Valid @RequestBody UpdatePermissionDto updatePermissionDto,
            @PathVariable("uuid") UUID uuid
    ) {
        DetailedResPermissionDto response = permissionService.updatePermission(uuid, updatePermissionDto);
        return ResponseEntity.ok(new BaseResponse<>(response));
    }

    @DeleteMapping("{uuid}")
    public ResponseEntity<BaseResponse<String>> deletePermissionByUuid(
            @PathVariable("uuid") UUID uuid
    ) {
        permissionService.deletePermission(uuid);
        return ResponseEntity.ok(new BaseResponse<>("Permission deleted."));
    }
}