package com.platform.igrejapentecostalreformadaapi.mapper;

import com.platform.igrejapentecostalreformadaapi.data.vo.CivilStatusVO;
import com.platform.igrejapentecostalreformadaapi.data.vo.ContactVO;
import com.platform.igrejapentecostalreformadaapi.entities.CivilStatus;
import com.platform.igrejapentecostalreformadaapi.entities.Contact;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CivilStatusMapper {

    CivilStatusMapper INSTANCE = Mappers.getMapper(CivilStatusMapper.class);

    CivilStatusVO convertEntityToVO(CivilStatus entity);

    List<CivilStatusVO> convertEntitiesToVOs(List<CivilStatus> entities);

    CivilStatus convertVOToEntity(CivilStatusVO entityVO);

    List<Contact> convertVOsToEntities(List<ContactVO> entityVOs);
}
