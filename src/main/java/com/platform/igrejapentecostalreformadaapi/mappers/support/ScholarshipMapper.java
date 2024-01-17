package com.platform.igrejapentecostalreformadaapi.mappers.support;

import com.platform.igrejapentecostalreformadaapi.data.vo.support.ScholarshipVO;
import com.platform.igrejapentecostalreformadaapi.entities.support.Scholarship;

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
