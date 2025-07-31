package io.stevengoh.portfolio.school_management_app.modules.institution_academic_terms.mappers;

import io.stevengoh.portfolio.school_management_app.modules.institution_academic_terms.dtos.response.DetailedResAcademicTermDto;
import io.stevengoh.portfolio.school_management_app.modules.institution_academic_terms.dtos.response.SimpleResAcademicTermDto;
import io.stevengoh.portfolio.school_management_app.modules.institution_academic_terms.entities.AcademicTerm;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AcademicTermResponseMapper {
    SimpleResAcademicTermDto toSimpleResDto(AcademicTerm academicTerm);

    List<SimpleResAcademicTermDto> toSimpleResDtoList(List<AcademicTerm> academicTerm);

    DetailedResAcademicTermDto toDetailedResDto(AcademicTerm academicTerm);

    List<DetailedResAcademicTermDto> toDetailedResDtoList(List<AcademicTerm> academicTerm);
}