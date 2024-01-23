package com.platform.igrejapentecostalreformadaapi.mappers.support;

import com.platform.igrejapentecostalreformadaapi.data.vo.support.InterestTypeVO;
import com.platform.igrejapentecostalreformadaapi.entities.support.InterestType;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface InterestTypeMapper {

    InterestTypeMapper INSTANCE = Mappers.getMapper(InterestTypeMapper.class);

    InterestTypeVO convertEntityToVO(InterestType entity);

    List<InterestTypeVO> convertEntitiesToVOs(List<InterestType> entities);

    InterestType convertVOToEntity(InterestTypeVO entityVO);

    List<InterestType> convertVOsToEntities(List<InterestTypeVO> entityVOs);
}
