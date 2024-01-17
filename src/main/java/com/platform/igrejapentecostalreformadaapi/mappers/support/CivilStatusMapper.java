package com.platform.igrejapentecostalreformadaapi.mappers.support;

import com.platform.igrejapentecostalreformadaapi.data.vo.support.CivilStatusVO;
import com.platform.igrejapentecostalreformadaapi.entities.support.CivilStatus;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CivilStatusMapper {

    CivilStatusMapper INSTANCE = Mappers.getMapper(CivilStatusMapper.class);

    CivilStatusVO convertEntityToVO(CivilStatus entity);

    List<CivilStatusVO> convertEntitiesToVOs(List<CivilStatus> entities);

    CivilStatus convertVOToEntity(CivilStatusVO entityVO);

    List<CivilStatus> convertVOsToEntities(List<CivilStatusVO> entityVOs);
}
