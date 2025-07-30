package io.stevengoh.portfolio.school_management_app.modules.institution_role_permissions;

import io.stevengoh.portfolio.school_management_app.common.dtos.BaseResponse;
import io.stevengoh.portfolio.school_management_app.common.dtos.PaginatedResult;
import io.stevengoh.portfolio.school_management_app.modules.institution_role_permissions.dtos.request.CreateInstitutionRolePermissionDto;
import io.stevengoh.portfolio.school_management_app.modules.institution_role_permissions.dtos.request.DeleteInstitutionRolePermissionDto;
import io.stevengoh.portfolio.school_management_app.modules.institution_role_permissions.dtos.request.SearchInstitutionRolePermissionDto;
import io.stevengoh.portfolio.school_management_app.modules.institution_role_permissions.dtos.response.DetailedResInstitutionRolePermissionDto;
import io.stevengoh.portfolio.school_management_app.modules.institution_role_permissions.dtos.response.SimpleResInstitutionRolePermissionDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("institution/{institutionUuid}/role-permissions")
@RequiredArgsConstructor
public class InstitutionRolePermissionController {
    private final InstitutionRolePermissionService institutionRolePermissionService;

    @GetMapping
    public ResponseEntity<BaseResponse<PaginatedResult<SimpleResInstitutionRolePermissionDto>>> getInstitutionRolePermissions(
            @ModelAttribute SearchInstitutionRolePermissionDto searchInstitutionRolePermissionDto,
            @PageableDefault(size = 20, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        PaginatedResult<SimpleResInstitutionRolePermissionDto> result = institutionRolePermissionService.getInstitutionRolePermissions(searchInstitutionRolePermissionDto, pageable);
        return ResponseEntity.ok(new BaseResponse<>(result));
    }

    @GetMapping("{uuid}")
    public ResponseEntity<BaseResponse<DetailedResInstitutionRolePermissionDto>> getInstitutionRolePermissionByUuid(
            @PathVariable("uuid") UUID uuid
    ) {
        DetailedResInstitutionRolePermissionDto result = institutionRolePermissionService.getInstitutionRolePermissionByUuidOrThrow(uuid);
        return ResponseEntity.ok(new BaseResponse<>(result));
    }

    @PostMapping
    public ResponseEntity<BaseResponse<DetailedResInstitutionRolePermissionDto>> createInstitutionRolePermission(
            @PathVariable("institutionUuid") UUID uuid,
            @RequestBody CreateInstitutionRolePermissionDto request
    ) {
        DetailedResInstitutionRolePermissionDto result = institutionRolePermissionService.createInstitutionRolePermission(uuid, request);
        return ResponseEntity.ok(new BaseResponse<>(result));
    }

    @PostMapping("bulk-insert")
    public ResponseEntity<BaseResponse<List<DetailedResInstitutionRolePermissionDto>>> createBulkInstitutionRolePermissions(
            @PathVariable("institutionUuid") UUID uuid,
            @RequestBody List<CreateInstitutionRolePermissionDto> request
    ) {
        List<DetailedResInstitutionRolePermissionDto> result = institutionRolePermissionService.createBulkInstitutionRolePermission(uuid, request);
        return ResponseEntity.ok(new BaseResponse<>(result));
    }

    @DeleteMapping("{uuid}")
    public ResponseEntity<BaseResponse<String>> deleteInstitutionRolePermission(
            @PathVariable("uuid") UUID uuid
    ) {
        institutionRolePermissionService.deleteInstitutionRolePermission(uuid);
        return ResponseEntity.ok(new BaseResponse<>("Role permission deleted"));
    }

    @DeleteMapping("bulk-delete")
    public ResponseEntity<BaseResponse<String>> deleteBulkInstitutionRolePermissions(
            @RequestBody DeleteInstitutionRolePermissionDto request
    ) {
        institutionRolePermissionService.deleteBulkInstitutionRolePermission(request);
        return ResponseEntity.ok(new BaseResponse<>("Role permissions deleted."));
    }
}