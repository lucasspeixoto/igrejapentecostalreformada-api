package com.platform.igrejapentecostalreformadaapi.mappers.register;

import com.platform.igrejapentecostalreformadaapi.data.vo.register.FamilyVO;
import com.platform.igrejapentecostalreformadaapi.entities.register.Family;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FamilyMapper {

    FamilyMapper INSTANCE = Mappers.getMapper(FamilyMapper.class);

    FamilyVO convertEntityToVO(Family entity);

    List<FamilyVO> convertEntitiesToVOs(List<Family> entities);

    Family convertVOToEntity(FamilyVO entityVo);

    List<Family> convertVOsToEntities(List<FamilyVO> entitiesVos);
}
