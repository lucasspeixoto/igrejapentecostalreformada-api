package com.platform.igrejapentecostalreformadaapi.mappers.register;

import com.platform.igrejapentecostalreformadaapi.data.vo.register.EducationVO;
import com.platform.igrejapentecostalreformadaapi.entities.register.Education;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EducationMapper {

    EducationMapper INSTANCE = Mappers.getMapper(EducationMapper.class);

    EducationVO convertEntityToVO(Education entity);

    List<EducationVO> convertEntitiesToVOs(List<Education> entities);

    Education convertVOToEntity(EducationVO entityVo);

    List<Education> convertVOsToEntities(List<EducationVO> entitiesVos);
}
