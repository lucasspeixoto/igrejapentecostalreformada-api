package com.platform.igrejapentecostalreformadaapi.mapper;

import com.platform.igrejapentecostalreformadaapi.data.vo.ScholarshipVO;
import com.platform.igrejapentecostalreformadaapi.entities.Scholarship;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ScholarshipMapper {

    ScholarshipMapper INSTANCE = Mappers.getMapper(ScholarshipMapper.class);

    ScholarshipVO convertEntityToVO(Scholarship entity);

    List<ScholarshipVO> convertEntitiesToVOs(List<Scholarship> entities);

    Scholarship convertVOToEntity(ScholarshipVO entityVO);

    List<Scholarship> convertVOsToEntities(List<ScholarshipVO> entityVOs);
}
