package io.stevengoh.portfolio.school_management_app.modules.institution_grade_levels.mappers;

import io.stevengoh.portfolio.school_management_app.modules.institution_grade_levels.dtos.response.DetailedResGradeLevelDto;
import io.stevengoh.portfolio.school_management_app.modules.institution_grade_levels.dtos.response.SimpleResGradeLevelDto;
import io.stevengoh.portfolio.school_management_app.modules.institution_grade_levels.entities.GradeLevel;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface GradeLevelResponseMapper {
    SimpleResGradeLevelDto toSimpleResDto(GradeLevel gradeLevel);

    List<SimpleResGradeLevelDto> toSimpleResDtoList(List<GradeLevel> gradeLevel);

    DetailedResGradeLevelDto toDetailedResDto(GradeLevel gradeLevel);

    List<DetailedResGradeLevelDto> toDetailedResDtoList(List<GradeLevel> gradeLevel);
}