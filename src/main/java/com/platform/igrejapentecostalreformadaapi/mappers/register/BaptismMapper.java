package com.platform.igrejapentecostalreformadaapi.mappers.register;

import com.platform.igrejapentecostalreformadaapi.data.vo.register.BaptismVO;
import com.platform.igrejapentecostalreformadaapi.entities.register.Baptism;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BaptismMapper {

    BaptismMapper INSTANCE = Mappers.getMapper(BaptismMapper.class);

    BaptismVO convertEntityToVO(Baptism entity);

    List<BaptismVO> convertEntitiesToVOs(List<Baptism> entities);

    Baptism convertVOToEntity(BaptismVO entityVo);

    List<Baptism> convertVOsToEntities(List<BaptismVO> entitiesVos);
}
