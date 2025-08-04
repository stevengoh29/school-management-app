package io.stevengoh.portfolio.school_management_app.modules.institution_academic_classes.mappers;

import io.stevengoh.portfolio.school_management_app.modules.institution_academic_classes.dtos.response.DetailedResAcademicClassDto;
import io.stevengoh.portfolio.school_management_app.modules.institution_academic_classes.dtos.response.SimpleResAcademicClassDto;
import io.stevengoh.portfolio.school_management_app.modules.institution_academic_classes.entities.AcademicClass;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AcademicClassResponseMapper {
    SimpleResAcademicClassDto toSimpleResDto(AcademicClass entity);

    List<SimpleResAcademicClassDto> toSimpleResDtoList(List<AcademicClass> entity);

    DetailedResAcademicClassDto toDetailedResDto(AcademicClass entity);

    List<DetailedResAcademicClassDto> toDetailedResDtoList(List<AcademicClass> entity);
}