package io.stevengoh.portfolio.school_management_app.modules.institution_master_academic_classes;

import io.stevengoh.portfolio.school_management_app.common.dtos.BaseResponse;
import io.stevengoh.portfolio.school_management_app.common.dtos.PaginatedResult;
import io.stevengoh.portfolio.school_management_app.modules.institution_master_academic_classes.dtos.request.CreateMasterAcademicClassDto;
import io.stevengoh.portfolio.school_management_app.modules.institution_master_academic_classes.dtos.request.SearchMasterAcademicClassDto;
import io.stevengoh.portfolio.school_management_app.modules.institution_master_academic_classes.dtos.request.UpdateMasterAcademicClassDto;
import io.stevengoh.portfolio.school_management_app.modules.institution_master_academic_classes.dtos.response.DetailedResMasterAcademicClassDto;
import io.stevengoh.portfolio.school_management_app.modules.institution_master_academic_classes.dtos.response.SimpleResMasterAcademicClassDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("institutions/{institutionUuid}/master-academic-classes")
@RequiredArgsConstructor
public class MasterAcademicClassController {
    private final MasterAcademicClassService masterAcademicClassService;

    @GetMapping
    public ResponseEntity<BaseResponse<PaginatedResult<SimpleResMasterAcademicClassDto>>> getMasterAcademicClasses(
            @PathVariable String institutionUuid,
            @ModelAttribute SearchMasterAcademicClassDto searchMasterAcademicClassDto,
            @PageableDefault(size = 20, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        PaginatedResult<SimpleResMasterAcademicClassDto> response = masterAcademicClassService.getMasterAcademicClasses(searchMasterAcademicClassDto, pageable);
        return ResponseEntity.ok(new BaseResponse<>(response));
    }

    @GetMapping("{uuid}")
    public ResponseEntity<BaseResponse<DetailedResMasterAcademicClassDto>> getMasterAcademicClassByUuid(
            @PathVariable("uuid") UUID uuid
    ) {
        DetailedResMasterAcademicClassDto response = masterAcademicClassService.getMasterAcademicClassByUuidOrThrow(uuid);
        return ResponseEntity.ok(new BaseResponse<>(response));
    }

    @PostMapping
    public ResponseEntity<BaseResponse<DetailedResMasterAcademicClassDto>> createMasterAcademicClass(
            @PathVariable("institutionUuid") UUID institutionUuid,
            @RequestBody CreateMasterAcademicClassDto createMasterAcademicClassDto
    ) {
        DetailedResMasterAcademicClassDto response = masterAcademicClassService.createMasterAcademicClass(institutionUuid, createMasterAcademicClassDto);
        return ResponseEntity.ok(new BaseResponse<>(response));
    }

    @PutMapping("{uuid}")
    public ResponseEntity<BaseResponse<DetailedResMasterAcademicClassDto>> updateMasterAcademicClassByUuid(
            @PathVariable("uuid") UUID uuid,
            @RequestBody UpdateMasterAcademicClassDto updateMasterAcademicClassDto
    ) {
        DetailedResMasterAcademicClassDto response = masterAcademicClassService.updateMasterAcademicClass(uuid, updateMasterAcademicClassDto);
        return ResponseEntity.ok(new BaseResponse<>(response));
    }

    @DeleteMapping("{uuid}")
    public ResponseEntity<BaseResponse<String>> deleteMasterAcademicClassByUuid(
            @PathVariable("uuid") UUID uuid
    ) {
        masterAcademicClassService.deleteMasterAcademicClass(uuid);
        return ResponseEntity.ok(new BaseResponse<>("MasterAcademicClass deleted."));
    }
}
