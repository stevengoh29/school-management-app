package io.stevengoh.portfolio.school_management_app.modules.institution_academic_levels;

import io.stevengoh.portfolio.school_management_app.common.dtos.BaseResponse;
import io.stevengoh.portfolio.school_management_app.common.dtos.PaginatedResult;
import io.stevengoh.portfolio.school_management_app.modules.institution_academic_levels.dtos.request.CreateAcademicLevelDto;
import io.stevengoh.portfolio.school_management_app.modules.institution_academic_levels.dtos.request.SearchAcademicLevelDto;
import io.stevengoh.portfolio.school_management_app.modules.institution_academic_levels.dtos.request.UpdateAcademicLevelDto;
import io.stevengoh.portfolio.school_management_app.modules.institution_academic_levels.dtos.response.DetailedResAcademicLevelDto;
import io.stevengoh.portfolio.school_management_app.modules.institution_academic_levels.dtos.response.SimpleResAcademicLevelDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("institutions/{institutionUuid}/academic-levels")
@RequiredArgsConstructor
public class AcademicLevelController {
    private final AcademicLevelService academicLevelService;

    @GetMapping
    public ResponseEntity<BaseResponse<PaginatedResult<SimpleResAcademicLevelDto>>> getAcademicLevels(
            @PathVariable String institutionUuid,
            @ModelAttribute SearchAcademicLevelDto searchAcademicLevelDto,
            @PageableDefault(size = 20, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        PaginatedResult<SimpleResAcademicLevelDto> response = academicLevelService.getAcademicLevels(searchAcademicLevelDto, pageable);
        return ResponseEntity.ok(new BaseResponse<>(response));
    }

    @GetMapping("{uuid}")
    public ResponseEntity<BaseResponse<DetailedResAcademicLevelDto>> getAcademicLevelByUuid(
            @PathVariable("uuid") UUID uuid
    ) {
        DetailedResAcademicLevelDto response = academicLevelService.getAcademicLevelByUuidOrThrow(uuid);
        return ResponseEntity.ok(new BaseResponse<>(response));
    }

    @PostMapping
    public ResponseEntity<BaseResponse<DetailedResAcademicLevelDto>> createAcademicLevel(
            @PathVariable("institutionUuid") UUID institutionUuid,
            @RequestBody CreateAcademicLevelDto createAcademicLevelDto
    ) {
        DetailedResAcademicLevelDto response = academicLevelService.createAcademicLevel(institutionUuid, createAcademicLevelDto);
        return ResponseEntity.ok(new BaseResponse<>(response));
    }

    @PutMapping("{uuid}")
    public ResponseEntity<BaseResponse<DetailedResAcademicLevelDto>> updateAcademicLevelByUuid(
            @PathVariable("uuid") UUID uuid,
            @RequestBody UpdateAcademicLevelDto updateAcademicLevelDto
    ) {
        DetailedResAcademicLevelDto response = academicLevelService.updateAcademicLevel(uuid, updateAcademicLevelDto);
        return ResponseEntity.ok(new BaseResponse<>(response));
    }

    @DeleteMapping("{uuid}")
    public ResponseEntity<BaseResponse<String>> deleteAcademicLevelByUuid(
            @PathVariable("uuid") UUID uuid
    ) {
        academicLevelService.deleteAcademicLevel(uuid);
        return ResponseEntity.ok(new BaseResponse<>("AcademicLevel deleted."));
    }
}