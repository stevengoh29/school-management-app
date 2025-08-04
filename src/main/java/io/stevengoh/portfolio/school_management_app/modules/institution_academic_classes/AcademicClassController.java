package io.stevengoh.portfolio.school_management_app.modules.institution_academic_classes;

import io.stevengoh.portfolio.school_management_app.common.dtos.BaseResponse;
import io.stevengoh.portfolio.school_management_app.common.dtos.PaginatedResult;
import io.stevengoh.portfolio.school_management_app.modules.institution_academic_classes.dtos.request.CreateAcademicClassDto;
import io.stevengoh.portfolio.school_management_app.modules.institution_academic_classes.dtos.request.SearchAcademicClassDto;
import io.stevengoh.portfolio.school_management_app.modules.institution_academic_classes.dtos.request.UpdateAcademicClassDto;
import io.stevengoh.portfolio.school_management_app.modules.institution_academic_classes.dtos.response.DetailedResAcademicClassDto;
import io.stevengoh.portfolio.school_management_app.modules.institution_academic_classes.dtos.response.SimpleResAcademicClassDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("institutions/{institutionUuid}/academic-terms/{termUuid}/classes")
@RequiredArgsConstructor
public class AcademicClassController {
    private final AcademicClassService masterAcademicClassService;

    @GetMapping
    public ResponseEntity<BaseResponse<PaginatedResult<SimpleResAcademicClassDto>>> getAcademicClasses(
            @PathVariable String institutionUuid,
            @ModelAttribute SearchAcademicClassDto searchAcademicClassDto,
            @PageableDefault(size = 20, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        PaginatedResult<SimpleResAcademicClassDto> response = masterAcademicClassService.getAcademicClasses(searchAcademicClassDto, pageable);
        return ResponseEntity.ok(new BaseResponse<>(response));
    }

    @GetMapping("{uuid}")
    public ResponseEntity<BaseResponse<DetailedResAcademicClassDto>> getAcademicClassByUuid(
            @PathVariable("uuid") UUID uuid
    ) {
        DetailedResAcademicClassDto response = masterAcademicClassService.getAcademicClassByUuidOrThrow(uuid);
        return ResponseEntity.ok(new BaseResponse<>(response));
    }

    @PostMapping
    public ResponseEntity<BaseResponse<DetailedResAcademicClassDto>> createAcademicClass(
            @PathVariable("institutionUuid") UUID institutionUuid,
            @RequestBody CreateAcademicClassDto createAcademicClassDto
    ) {
        DetailedResAcademicClassDto response = masterAcademicClassService.createAcademicClass(institutionUuid, createAcademicClassDto);
        return ResponseEntity.ok(new BaseResponse<>(response));
    }

    @PutMapping("{uuid}")
    public ResponseEntity<BaseResponse<DetailedResAcademicClassDto>> updateAcademicClassByUuid(
            @PathVariable("uuid") UUID uuid,
            @RequestBody UpdateAcademicClassDto updateAcademicClassDto
    ) {
        DetailedResAcademicClassDto response = masterAcademicClassService.updateAcademicClass(uuid, updateAcademicClassDto);
        return ResponseEntity.ok(new BaseResponse<>(response));
    }

    @DeleteMapping("{uuid}")
    public ResponseEntity<BaseResponse<String>> deleteAcademicClassByUuid(
            @PathVariable("uuid") UUID uuid
    ) {
        masterAcademicClassService.deleteAcademicClass(uuid);
        return ResponseEntity.ok(new BaseResponse<>("Academic Class deleted."));
    }
}