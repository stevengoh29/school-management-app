package io.stevengoh.portfolio.school_management_app.modules.institutions;

import io.stevengoh.portfolio.school_management_app.common.dtos.BaseResponse;
import io.stevengoh.portfolio.school_management_app.common.dtos.PaginatedResult;
import io.stevengoh.portfolio.school_management_app.modules.institutions.dtos.request.CreateInstitutionDto;
import io.stevengoh.portfolio.school_management_app.modules.institutions.dtos.request.SearchInstitutionDto;
import io.stevengoh.portfolio.school_management_app.modules.institutions.dtos.request.UpdateInstitutionDto;
import io.stevengoh.portfolio.school_management_app.modules.institutions.dtos.response.DetailedResInstitutionDto;
import io.stevengoh.portfolio.school_management_app.modules.institutions.dtos.response.SimpleResInstitutionDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("institutions")
@RequiredArgsConstructor
public class InstitutionController {
    private final InstitutionService institutionService;

    @GetMapping
    public ResponseEntity<BaseResponse<PaginatedResult<SimpleResInstitutionDto>>> getInstitutions(
            @ModelAttribute SearchInstitutionDto searchInstitutionDto,
            @PageableDefault(size = 20, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        PaginatedResult<SimpleResInstitutionDto> response = institutionService.getInstitutions(searchInstitutionDto, pageable);
        return ResponseEntity.ok(new BaseResponse<>(response));
    }

    @GetMapping("{uuid}")
    public ResponseEntity<BaseResponse<DetailedResInstitutionDto>> getInstitution(
            @PathVariable UUID uuid
    ) {
        DetailedResInstitutionDto response = institutionService.getInstitutionByUuidOrThrow(uuid);
        return ResponseEntity.ok(new BaseResponse<>(response));
    }

    @PostMapping
    public ResponseEntity<BaseResponse<DetailedResInstitutionDto>> createInstitution(
            @Valid @RequestBody CreateInstitutionDto createInstitutionDto
    ) {
        DetailedResInstitutionDto response = institutionService.createInstitution(createInstitutionDto);
        return ResponseEntity.ok(new BaseResponse<>(response));
    }

    @PutMapping("{uuid}")
    public ResponseEntity<BaseResponse<DetailedResInstitutionDto>> updateInstitutionByUuid(
            @Valid @RequestBody UpdateInstitutionDto updateInstitutionDto,
            @PathVariable("uuid") UUID uuid
    ) {
        DetailedResInstitutionDto response = institutionService.updateInstitution(uuid, updateInstitutionDto);
        return ResponseEntity.ok(new BaseResponse<>(response));
    }

    @DeleteMapping("{uuid}")
    public ResponseEntity<BaseResponse<String>> deleteInstitutionByUuid(
            @PathVariable("uuid") UUID uuid
    ) {
        institutionService.deleteInstitution(uuid);
        return ResponseEntity.ok(new BaseResponse<>("Institution deleted."));
    }
}
