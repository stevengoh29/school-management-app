package io.stevengoh.portfolio.school_management_app.modules.institutions.mappers;

import io.stevengoh.portfolio.school_management_app.modules.institutions.dtos.response.DetailedResInstitutionDto;
import io.stevengoh.portfolio.school_management_app.modules.institutions.dtos.response.SimpleResInstitutionDto;
import io.stevengoh.portfolio.school_management_app.modules.institutions.entities.Institution;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface InstitutionResponseMapper {
    List<SimpleResInstitutionDto> toDtoList(List<Institution> institutions);
    SimpleResInstitutionDto toDto(Institution institution);

    List<DetailedResInstitutionDto> toDetailedDtoList(List<Institution> institutions);
    DetailedResInstitutionDto toDetailedDto(Institution institution);
}
