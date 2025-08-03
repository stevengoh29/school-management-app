package io.stevengoh.portfolio.school_management_app.modules.institution_academic_terms;

import io.stevengoh.portfolio.school_management_app.common.dtos.BaseResponse;
import io.stevengoh.portfolio.school_management_app.common.dtos.PaginatedResult;
import io.stevengoh.portfolio.school_management_app.modules.institution_academic_terms.dtos.request.CreateAcademicTermDto;
import io.stevengoh.portfolio.school_management_app.modules.institution_academic_terms.dtos.request.SearchAcademicTermDto;
import io.stevengoh.portfolio.school_management_app.modules.institution_academic_terms.dtos.request.UpdateAcademicTermDto;
import io.stevengoh.portfolio.school_management_app.modules.institution_academic_terms.dtos.response.DetailedResAcademicTermDto;
import io.stevengoh.portfolio.school_management_app.modules.institution_academic_terms.dtos.response.SimpleResAcademicTermDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("institutions/{institutionUuid}/academic-terms")
@RequiredArgsConstructor
public class AcademicTermController {
    private final AcademicTermService academicTermService;

    @GetMapping
    public ResponseEntity<BaseResponse<PaginatedResult<SimpleResAcademicTermDto>>> getTerms(
            @PathVariable String institutionUuid,
            @ModelAttribute SearchAcademicTermDto searchAcademicTermDto,
            @PageableDefault(size = 20, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        PaginatedResult<SimpleResAcademicTermDto> response = academicTermService.getTerms(searchAcademicTermDto, pageable);
        return ResponseEntity.ok(new BaseResponse<>(response));
    }

    @GetMapping("{uuid}")
    public ResponseEntity<BaseResponse<DetailedResAcademicTermDto>> getTermByUuid(
            @PathVariable("uuid") UUID uuid
    ) {
        DetailedResAcademicTermDto response = academicTermService.getTermByUuidOrThrow(uuid);
        return ResponseEntity.ok(new BaseResponse<>(response));
    }

    @PostMapping
    public ResponseEntity<BaseResponse<DetailedResAcademicTermDto>> createTerm(
            @PathVariable("institutionUuid") UUID institutionUuid,
            @RequestBody CreateAcademicTermDto createAcademicTermDto
    ) {
        DetailedResAcademicTermDto response = academicTermService.createTerm(institutionUuid, createAcademicTermDto);
        return ResponseEntity.ok(new BaseResponse<>(response));
    }

    @PutMapping("{uuid}")
    public ResponseEntity<BaseResponse<DetailedResAcademicTermDto>> updateTermByUuid(
            @PathVariable("uuid") UUID uuid,
            @RequestBody UpdateAcademicTermDto updateAcademicTermDto
    ) {
        DetailedResAcademicTermDto response = academicTermService.updateTerm(uuid, updateAcademicTermDto);
        return ResponseEntity.ok(new BaseResponse<>(response));
    }

    @DeleteMapping("{uuid}")
    public ResponseEntity<BaseResponse<String>> deleteTermByUuid(
            @PathVariable("uuid") UUID uuid
    ) {
        academicTermService.deleteTerm(uuid);
        return ResponseEntity.ok(new BaseResponse<>("Term deleted."));
    }
}