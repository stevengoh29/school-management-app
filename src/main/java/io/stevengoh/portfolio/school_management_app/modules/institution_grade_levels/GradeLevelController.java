package io.stevengoh.portfolio.school_management_app.modules.institution_grade_levels;

import io.stevengoh.portfolio.school_management_app.common.dtos.BaseResponse;
import io.stevengoh.portfolio.school_management_app.common.dtos.PaginatedResult;
import io.stevengoh.portfolio.school_management_app.modules.institution_grade_levels.dtos.request.CreateGradeLevelDto;
import io.stevengoh.portfolio.school_management_app.modules.institution_grade_levels.dtos.request.SearchGradeLevelDto;
import io.stevengoh.portfolio.school_management_app.modules.institution_grade_levels.dtos.request.UpdateGradeLevelDto;
import io.stevengoh.portfolio.school_management_app.modules.institution_grade_levels.dtos.response.DetailedResGradeLevelDto;
import io.stevengoh.portfolio.school_management_app.modules.institution_grade_levels.dtos.response.SimpleResGradeLevelDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("institutions/{institutionUuid}/grade-levels")
@RequiredArgsConstructor
public class GradeLevelController {
    private final GradeLevelService gradeLevelService;

    @GetMapping
    public ResponseEntity<BaseResponse<PaginatedResult<SimpleResGradeLevelDto>>> getGradeLevels(
            @PathVariable String institutionUuid,
            @ModelAttribute SearchGradeLevelDto searchGradeLevelDto,
            @PageableDefault(size = 20, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        PaginatedResult<SimpleResGradeLevelDto> response = gradeLevelService.getGradeLevels(searchGradeLevelDto, pageable);
        return ResponseEntity.ok(new BaseResponse<>(response));
    }

    @GetMapping("{uuid}")
    public ResponseEntity<BaseResponse<DetailedResGradeLevelDto>> getGradeLevelByUuid(
            @PathVariable("uuid") UUID uuid
    ) {
        DetailedResGradeLevelDto response = gradeLevelService.getGradeLevelByUuidOrThrow(uuid);
        return ResponseEntity.ok(new BaseResponse<>(response));
    }

    @PostMapping
    public ResponseEntity<BaseResponse<DetailedResGradeLevelDto>> createGradeLevel(
            @PathVariable("institutionUuid") UUID institutionUuid,
            @RequestBody CreateGradeLevelDto createGradeLevelDto
    ) {
        DetailedResGradeLevelDto response = gradeLevelService.createGradeLevel(institutionUuid, createGradeLevelDto);
        return ResponseEntity.ok(new BaseResponse<>(response));
    }

    @PutMapping("{uuid}")
    public ResponseEntity<BaseResponse<DetailedResGradeLevelDto>> updateGradeLevelByUuid(
            @PathVariable("uuid") UUID uuid,
            @RequestBody UpdateGradeLevelDto updateGradeLevelDto
    ) {
        DetailedResGradeLevelDto response = gradeLevelService.updateGradeLevel(uuid, updateGradeLevelDto);
        return ResponseEntity.ok(new BaseResponse<>(response));
    }

    @DeleteMapping("{uuid}")
    public ResponseEntity<BaseResponse<String>> deleteGradeLevelByUuid(
            @PathVariable("uuid") UUID uuid
    ) {
        gradeLevelService.deleteGradeLevel(uuid);
        return ResponseEntity.ok(new BaseResponse<>("Grade Level deleted."));
    }
}