package io.stevengoh.portfolio.school_management_app.modules.institution_academic_terms;

import io.stevengoh.portfolio.school_management_app.common.dtos.PaginatedResult;
import io.stevengoh.portfolio.school_management_app.common.specifications.SpecificationBuilder;
import io.stevengoh.portfolio.school_management_app.modules.institution_academic_terms.dtos.request.CreateAcademicTermDto;
import io.stevengoh.portfolio.school_management_app.modules.institution_academic_terms.dtos.request.SearchAcademicTermDto;
import io.stevengoh.portfolio.school_management_app.modules.institution_academic_terms.dtos.request.UpdateAcademicTermDto;
import io.stevengoh.portfolio.school_management_app.modules.institution_academic_terms.dtos.response.DetailedResAcademicTermDto;
import io.stevengoh.portfolio.school_management_app.modules.institution_academic_terms.dtos.response.SimpleResAcademicTermDto;
import io.stevengoh.portfolio.school_management_app.modules.institution_academic_terms.entities.AcademicTerm;
import io.stevengoh.portfolio.school_management_app.modules.institution_academic_terms.mappers.AcademicTermRequestMapper;
import io.stevengoh.portfolio.school_management_app.modules.institution_academic_terms.mappers.AcademicTermResponseMapper;
import io.stevengoh.portfolio.school_management_app.modules.institution_academic_terms.dtos.request.SearchAcademicTermDto;
import io.stevengoh.portfolio.school_management_app.modules.institution_academic_terms.dtos.response.SimpleResAcademicTermDto;
import io.stevengoh.portfolio.school_management_app.modules.institution_academic_terms.entities.AcademicTerm;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AcademicTermServiceImpl implements AcademicTermService{
    private final AcademicTermRepository academicTermRepository;
    private final AcademicTermRequestMapper requestMapper;
    private final AcademicTermResponseMapper responseMapper;

    @Override
    public AcademicTerm findByUuidOrNull(UUID uuid) {
        return academicTermRepository.findByUuid(uuid)
                .orElse(null);
    }

    @Override
    public AcademicTerm findByUuidOrThrow(UUID uuid) {
        return academicTermRepository.findByUuid(uuid)
                .orElseThrow(() -> new EntityNotFoundException("Academic term not found."));
    }

    @Override
    public PaginatedResult<SimpleResAcademicTermDto> getTerms(SearchAcademicTermDto searchAcademicTermDto, Pageable pageable) {
        SpecificationBuilder<AcademicTerm, SearchAcademicTermDto> builder = new SpecificationBuilder<>();
        Specification<AcademicTerm> spec = builder.build(searchAcademicTermDto);

        Page<AcademicTerm> pageResult = academicTermRepository.findAll(spec, pageable);
        List<SimpleResAcademicTermDto> dtoList = responseMapper.toSimpleResDtoList(pageResult.getContent());

        return new PaginatedResult<>(dtoList, pageResult.getTotalElements(), pageable.getPageNumber(), pageable.getPageSize());
    }

    @Override
    public DetailedResAcademicTermDto getTermByUuidOrNull(UUID uuid) {
        AcademicTerm academicTerm = findByUuidOrNull(uuid);
        return responseMapper.toDetailedResDto(academicTerm);
    }

    @Override
    public DetailedResAcademicTermDto getTermByUuidOrThrow(UUID uuid) {
        AcademicTerm academicTerm = findByUuidOrThrow(uuid);
        return responseMapper.toDetailedResDto(academicTerm);
    }

    @Override
    public DetailedResAcademicTermDto createTerm(UUID institutionUuid, CreateAcademicTermDto request) {
        return null;
    }

    @Override
    public DetailedResAcademicTermDto updateTerm(UUID termUuid, UpdateAcademicTermDto request) {
        return null;
    }

    @Override
    public void resetTermIsCurrentFlagByInstitutionUuid(UUID institutionUuid) {

    }

    @Override
    public void deleteTerm(UUID uuid) {

    }
}
