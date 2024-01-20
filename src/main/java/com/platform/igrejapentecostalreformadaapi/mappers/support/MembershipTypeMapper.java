package com.platform.igrejapentecostalreformadaapi.mappers.support;

import com.platform.igrejapentecostalreformadaapi.data.vo.support.MembershipTypeVO;
import com.platform.igrejapentecostalreformadaapi.entities.support.MembershipType;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MembershipTypeMapper {

    MembershipTypeMapper INSTANCE = Mappers.getMapper(MembershipTypeMapper.class);

    MembershipTypeVO convertEntityToVO(MembershipType entity);

    List<MembershipTypeVO> convertEntitiesToVOs(List<MembershipType> entities);

    MembershipType convertVOToEntity(MembershipTypeVO entityVO);

    List<MembershipType> convertVOsToEntities(List<MembershipTypeVO> entityVOs);
}
