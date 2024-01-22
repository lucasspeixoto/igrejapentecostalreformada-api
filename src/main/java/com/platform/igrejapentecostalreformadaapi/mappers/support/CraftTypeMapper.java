package com.platform.igrejapentecostalreformadaapi.mappers.support;

import com.platform.igrejapentecostalreformadaapi.data.vo.support.CraftTypeVO;
import com.platform.igrejapentecostalreformadaapi.entities.support.CraftType;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CraftTypeMapper {

    CraftTypeMapper INSTANCE = Mappers.getMapper(CraftTypeMapper.class);

    CraftTypeVO convertEntityToVO(CraftType entity);

    List<CraftTypeVO> convertEntitiesToVOs(List<CraftType> entities);

    CraftType convertVOToEntity(CraftTypeVO entityVO);

    List<CraftType> convertVOsToEntities(List<CraftTypeVO> entityVOs);
}
