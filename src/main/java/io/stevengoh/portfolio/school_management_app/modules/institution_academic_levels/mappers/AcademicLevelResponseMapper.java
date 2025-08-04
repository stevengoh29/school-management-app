package io.stevengoh.portfolio.school_management_app.modules.institution_academic_levels.mappers;

import io.stevengoh.portfolio.school_management_app.modules.institution_academic_levels.dtos.response.DetailedResAcademicLevelDto;
import io.stevengoh.portfolio.school_management_app.modules.institution_academic_levels.dtos.response.SimpleResAcademicLevelDto;
import io.stevengoh.portfolio.school_management_app.modules.institution_academic_levels.entities.AcademicLevel;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AcademicLevelResponseMapper {
    SimpleResAcademicLevelDto toSimpleResDto(AcademicLevel dto);
    List<SimpleResAcademicLevelDto> toSimpleResDtoList(List<AcademicLevel> dto);

    DetailedResAcademicLevelDto toDetailedResDto(AcademicLevel dto);
    List<DetailedResAcademicLevelDto> toDetailedResDtoList(List<AcademicLevel> dto);
}