package io.stevengoh.portfolio.school_management_app.modules.roles;

import io.stevengoh.portfolio.school_management_app.common.dtos.BaseResponse;
import io.stevengoh.portfolio.school_management_app.common.dtos.PaginatedResult;
import io.stevengoh.portfolio.school_management_app.modules.roles.dtos.request.CreateRoleDto;
import io.stevengoh.portfolio.school_management_app.modules.roles.dtos.request.SearchRoleDto;
import io.stevengoh.portfolio.school_management_app.modules.roles.dtos.request.UpdateRoleDto;
import io.stevengoh.portfolio.school_management_app.modules.roles.dtos.response.DetailedResRoleDto;
import io.stevengoh.portfolio.school_management_app.modules.roles.dtos.response.SimpleResRoleDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("roles")
@RequiredArgsConstructor
public class RoleController {
    private final RoleService roleService;

    @GetMapping
    public ResponseEntity<BaseResponse<PaginatedResult<SimpleResRoleDto>>> getRoles(
            @ModelAttribute SearchRoleDto searchRoleDto,
            @PageableDefault(size = 20, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        PaginatedResult<SimpleResRoleDto> response = roleService.getRoles(searchRoleDto, pageable);
        return ResponseEntity.ok(new BaseResponse<>(response));
    }

    @GetMapping("{uuid}")
    public ResponseEntity<BaseResponse<DetailedResRoleDto>> getRole(
            @PathVariable UUID uuid
    ) {
        DetailedResRoleDto response = roleService.getRoleByUuidOrThrow(uuid);
        return ResponseEntity.ok(new BaseResponse<>(response));
    }

    @PostMapping
    public ResponseEntity<BaseResponse<DetailedResRoleDto>> createRole(
            @Valid @RequestBody CreateRoleDto createRoleDto
    ) {
        DetailedResRoleDto response = roleService.createRole(createRoleDto);
        return ResponseEntity.ok(new BaseResponse<>(response));
    }

    @PutMapping("{uuid}")
    public ResponseEntity<BaseResponse<DetailedResRoleDto>> updateRoleByUuid(
            @Valid @RequestBody UpdateRoleDto updateRoleDto,
            @PathVariable("uuid") UUID uuid
    ) {
        DetailedResRoleDto response = roleService.updateRole(uuid, updateRoleDto);
        return ResponseEntity.ok(new BaseResponse<>(response));
    }

    @DeleteMapping("{uuid}")
    public ResponseEntity<BaseResponse<String>> deleteRoleByUuid(
            @PathVariable("uuid") UUID uuid
    ) {
        roleService.deleteRole(uuid);
        return ResponseEntity.ok(new BaseResponse<>("Role deleted."));
    }
}