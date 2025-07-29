package io.stevengoh.portfolio.school_management_app.modules.institution_roles;

import io.stevengoh.portfolio.school_management_app.common.dtos.BaseResponse;
import io.stevengoh.portfolio.school_management_app.common.dtos.PaginatedResult;
import io.stevengoh.portfolio.school_management_app.modules.institution_roles.dtos.request.CreateInstitutionRoleDto;
import io.stevengoh.portfolio.school_management_app.modules.institution_roles.dtos.request.SearchInstitutionRoleDto;
import io.stevengoh.portfolio.school_management_app.modules.institution_roles.dtos.request.UpdateInstitutionRoleDto;
import io.stevengoh.portfolio.school_management_app.modules.institution_roles.dtos.response.DetailedResInstitutionRoleDto;
import io.stevengoh.portfolio.school_management_app.modules.institution_roles.dtos.response.SimpleResInstitutionRoleDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("institutions/{institutionUuid}/roles")
@RequiredArgsConstructor
public class InstitutionRoleController {
    private final InstitutionRoleService institutionRoleService;

    @GetMapping
    public ResponseEntity<BaseResponse<PaginatedResult<SimpleResInstitutionRoleDto>>> getInstitutionRoles(
            @PathVariable("institutionUuid") UUID institutionUuid,
            @ModelAttribute SearchInstitutionRoleDto searchInstitutionRoleDto,
            @PageableDefault(size = 20, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        PaginatedResult<SimpleResInstitutionRoleDto> response = institutionRoleService.getInstitutionRoles(searchInstitutionRoleDto, pageable);
        return ResponseEntity.ok(new BaseResponse<>(response));
    }

    @GetMapping("{uuid}")
    public ResponseEntity<BaseResponse<DetailedResInstitutionRoleDto>> getInstitutionRole(
            @PathVariable("uuid") UUID uuid,
            @PathVariable String institutionUuid
    ) {
        DetailedResInstitutionRoleDto response = institutionRoleService.getInstitutionRoleByUuidOrThrow(uuid);
        return ResponseEntity.ok(new BaseResponse<>(response));
    }

    @PostMapping
    public ResponseEntity<BaseResponse<DetailedResInstitutionRoleDto>> createInstitutionRole(
            @PathVariable("institutionUuid") UUID institutionUuid,
            @RequestBody CreateInstitutionRoleDto request
    ) {
        DetailedResInstitutionRoleDto response = institutionRoleService.createInstitutionRole(institutionUuid, request);
        return ResponseEntity.ok(new BaseResponse<>(response));
    }

    @PutMapping("{uuid}")
    public ResponseEntity<BaseResponse<DetailedResInstitutionRoleDto>> updateInstitutionRole(
            @PathVariable("institutionUuid") UUID institutionUuid,
            @PathVariable("uuid") UUID uuid,
            @RequestBody UpdateInstitutionRoleDto request
    ) {
        DetailedResInstitutionRoleDto response = institutionRoleService.updateInstitutionRole(uuid, request);
        return ResponseEntity.ok(new BaseResponse<>(response));
    }

    @DeleteMapping("{uuid}")
    public ResponseEntity<BaseResponse<String>> deleteInstitutionRole(
            @PathVariable("institutionUuid") UUID institutionUuid,
            @PathVariable("uuid") UUID uuid
    ) {
        institutionRoleService.deleteInstitutionRole(uuid);
        return ResponseEntity.ok(new BaseResponse<>("Institution Role deleted."));
    }
}