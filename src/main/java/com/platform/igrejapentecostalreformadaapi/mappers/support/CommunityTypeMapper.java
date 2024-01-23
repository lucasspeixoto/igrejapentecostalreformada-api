package com.platform.igrejapentecostalreformadaapi.mappers.support;

import com.platform.igrejapentecostalreformadaapi.data.vo.support.CommunityTypeVO;
import com.platform.igrejapentecostalreformadaapi.entities.support.CommunityType;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CommunityTypeMapper {

    CommunityTypeMapper INSTANCE = Mappers.getMapper(CommunityTypeMapper.class);

    CommunityTypeVO convertEntityToVO(CommunityType entity);

    List<CommunityTypeVO> convertEntitiesToVOs(List<CommunityType> entities);

    CommunityType convertVOToEntity(CommunityTypeVO entityVO);

    List<CommunityType> convertVOsToEntities(List<CommunityTypeVO> entityVOs);
}
