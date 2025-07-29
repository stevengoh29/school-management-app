package io.stevengoh.portfolio.school_management_app.modules.institutions;

import io.stevengoh.portfolio.school_management_app.common.dtos.PaginatedResult;
import io.stevengoh.portfolio.school_management_app.common.specifications.SpecificationBuilder;
import io.stevengoh.portfolio.school_management_app.modules.institutions.dtos.request.CreateInstitutionDto;
import io.stevengoh.portfolio.school_management_app.modules.institutions.dtos.request.SearchInstitutionDto;
import io.stevengoh.portfolio.school_management_app.modules.institutions.dtos.request.UpdateInstitutionDto;
import io.stevengoh.portfolio.school_management_app.modules.institutions.dtos.response.DetailedResInstitutionDto;
import io.stevengoh.portfolio.school_management_app.modules.institutions.dtos.response.SimpleResInstitutionDto;
import io.stevengoh.portfolio.school_management_app.modules.institutions.entities.Institution;
import io.stevengoh.portfolio.school_management_app.modules.institutions.enums.InstitutionStatus;
import io.stevengoh.portfolio.school_management_app.modules.institutions.mappers.InstitutionRequestMapper;
import io.stevengoh.portfolio.school_management_app.modules.institutions.mappers.InstitutionResponseMapper;
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
public class InstitutionServiceImpl implements InstitutionService {
    private final InstitutionRepository institutionRepository;
    private final InstitutionRequestMapper requestMapper;
    private final InstitutionResponseMapper responseMapper;

    @Override
    public Institution findByUuidOrNull(UUID uuid) {
        return institutionRepository.findByUuid(uuid)
                .orElse(null);
    }

    @Override
    public Institution findByUuidOrThrow(UUID uuid) {
        return institutionRepository.findByUuid(uuid)
                .orElseThrow(() -> new EntityNotFoundException("Institution not found"));
    }

    @Override
    public PaginatedResult<SimpleResInstitutionDto> getInstitutions(SearchInstitutionDto searchInstitutionDto, Pageable pageable) {
        SpecificationBuilder<Institution, SearchInstitutionDto> builder = new SpecificationBuilder<>();
        Specification<Institution> spec = builder.build(searchInstitutionDto);

        Page<Institution> pageResult = institutionRepository.findAll(spec, pageable);
        List<SimpleResInstitutionDto> dtoList = responseMapper.toDtoList(pageResult.getContent());

        return new PaginatedResult<>(dtoList, pageResult.getTotalElements(), pageable.getPageNumber(), pageable.getPageSize());
    }

    @Override
    public DetailedResInstitutionDto getInstitutionByUuidOrNull(UUID uuid) {
        Institution institution = institutionRepository.findByUuidAndStatus(uuid, InstitutionStatus.ACTIVE)
                .orElse(null);

        return institution == null ? null : responseMapper.toDetailedDto(institution);
    }

    @Override
    public DetailedResInstitutionDto getInstitutionByUuidOrThrow(UUID uuid) {
        Institution institution = institutionRepository.findByUuidAndStatus(uuid, InstitutionStatus.ACTIVE)
                .orElseThrow(() -> new EntityNotFoundException("Institution not found"));

        return responseMapper.toDetailedDto(institution);
    }

    @Override
    public DetailedResInstitutionDto createInstitution(CreateInstitutionDto createInstitutionDto) {
        Institution newInstitution = requestMapper.createEntityToDto(createInstitutionDto);
        Institution savedInstitution = institutionRepository.save(newInstitution);

        return responseMapper.toDetailedDto(savedInstitution);
    }

    @Override
    public DetailedResInstitutionDto updateInstitution(UUID uuid, UpdateInstitutionDto updateInstitutionDto) {
        Institution existingInstitution = this.findByUuidOrThrow(uuid);
        requestMapper.updateEntityToDto(updateInstitutionDto, existingInstitution);

        Institution savedInstitution = institutionRepository.save(existingInstitution);
        return responseMapper.toDetailedDto(savedInstitution);
    }

    @Override
    public void deleteInstitution(UUID uuid) {
        Institution existingInstitution = this.findByUuidOrThrow(uuid);
        existingInstitution.setStatus(InstitutionStatus.DELETED);

        institutionRepository.save(existingInstitution);
    }
}
