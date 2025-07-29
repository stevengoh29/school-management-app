package io.stevengoh.portfolio.school_management_app.institutions;

import io.stevengoh.portfolio.school_management_app.common.dtos.PaginatedResult;
import io.stevengoh.portfolio.school_management_app.modules.institutions.InstitutionRepository;
import io.stevengoh.portfolio.school_management_app.modules.institutions.InstitutionServiceImpl;
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
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class InstitutionServiceImplTest {
    @Mock
    private InstitutionRepository institutionRepository;

    @Mock
    private InstitutionRequestMapper requestMapper;

    @Mock
    private InstitutionResponseMapper responseMapper;

    @InjectMocks
    private InstitutionServiceImpl institutionService;

    @Test
    void shouldReturnPaginatedInstitutionList() {
        // Arrange
        SearchInstitutionDto searchDto = new SearchInstitutionDto();
        Pageable pageable = PageRequest.of(0, 10);
        Institution entity = new Institution();
        List<Institution> entities = List.of(entity);

        Page<Institution> page = new PageImpl<>(entities, pageable, 1);

        when(institutionRepository.findAll(any(Specification.class), eq(pageable))).thenReturn(page);
        when(responseMapper.toDtoList(any())).thenReturn(List.of(new SimpleResInstitutionDto()));

        // Act
        PaginatedResult<SimpleResInstitutionDto> result = institutionService.getInstitutions(searchDto, pageable);

        // Assert
        Assertions.assertEquals(1, result.getContent().size());
        verify(institutionRepository).findAll((Specification<Institution>) any(Specification.class), eq(pageable));
        verify(responseMapper).toDtoList(any());
    }

    @Test
    void shouldThrowWhenInstitutionNotFound() {
        UUID uuid = UUID.randomUUID();
        when(institutionRepository.findByUuid(uuid)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> institutionService.findByUuidOrThrow(uuid));
    }

    @Test
    void shouldReturnInstitutionWhenExistsByUuidAndActive() {
        UUID uuid = UUID.randomUUID();
        Institution mockInstitution = new Institution();
        when(institutionRepository.findByUuidAndStatus(uuid, InstitutionStatus.ACTIVE))
                .thenReturn(Optional.of(mockInstitution));

        DetailedResInstitutionDto dto = new DetailedResInstitutionDto();
        when(responseMapper.toDetailedDto(mockInstitution)).thenReturn(dto);

        DetailedResInstitutionDto result = institutionService.getInstitutionByUuidOrNull(uuid);

        assertNotNull(result);
        verify(institutionRepository).findByUuidAndStatus(uuid, InstitutionStatus.ACTIVE);
        verify(responseMapper).toDetailedDto(mockInstitution);
    }

    @Test
    void shouldReturnNullWhenInstitutionNotFoundByUuidAndActive() {
        UUID uuid = UUID.randomUUID();
        when(institutionRepository.findByUuidAndStatus(uuid, InstitutionStatus.ACTIVE))
                .thenReturn(Optional.empty());

        DetailedResInstitutionDto result = institutionService.getInstitutionByUuidOrNull(uuid);

        assertNull(result);
    }

    @Test
    void shouldCreateInstitution() {
        CreateInstitutionDto createDto = new CreateInstitutionDto();
        Institution entity = new Institution();
        Institution savedEntity = new Institution();

        when(requestMapper.createEntityToDto(createDto)).thenReturn(entity);
        when(institutionRepository.save(entity)).thenReturn(savedEntity);
        when(responseMapper.toDetailedDto(savedEntity)).thenReturn(new DetailedResInstitutionDto());

        DetailedResInstitutionDto result = institutionService.createInstitution(createDto);

        assertNotNull(result);
        verify(requestMapper).createEntityToDto(createDto);
        verify(institutionRepository).save(entity);
        verify(responseMapper).toDetailedDto(savedEntity);
    }

    @Test
    void shouldUpdateInstitution() {
        UUID uuid = UUID.randomUUID();
        UpdateInstitutionDto updateDto = new UpdateInstitutionDto();
        Institution existing = new Institution();

        when(institutionRepository.findByUuid(uuid)).thenReturn(Optional.of(existing));
        doNothing().when(requestMapper).updateEntityToDto(updateDto, existing);
        when(institutionRepository.save(existing)).thenReturn(existing);
        when(responseMapper.toDetailedDto(existing)).thenReturn(new DetailedResInstitutionDto());

        DetailedResInstitutionDto result = institutionService.updateInstitution(uuid, updateDto);

        assertNotNull(result);
        verify(requestMapper).updateEntityToDto(updateDto, existing);
        verify(institutionRepository).save(existing);
    }
}
