package io.stevengoh.portfolio.school_management_app.modules.institution_master_academic_classes.mappers;

import io.stevengoh.portfolio.school_management_app.modules.institution_master_academic_classes.dtos.response.DetailedResMasterAcademicClassDto;
import io.stevengoh.portfolio.school_management_app.modules.institution_master_academic_classes.dtos.response.SimpleResMasterAcademicClassDto;
import io.stevengoh.portfolio.school_management_app.modules.institution_master_academic_classes.entities.MasterAcademicClass;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MasterAcademicClassResponseMapper {
    SimpleResMasterAcademicClassDto toSimpleResDto(MasterAcademicClass entity);

    List<SimpleResMasterAcademicClassDto> toSimpleResDtoList(List<MasterAcademicClass> entity);

    DetailedResMasterAcademicClassDto toDetailedResDto(MasterAcademicClass entity);

    List<DetailedResMasterAcademicClassDto> toDetailedResDtoList(List<MasterAcademicClass> entity);
}