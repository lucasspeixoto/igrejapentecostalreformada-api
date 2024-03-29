package com.platform.igrejapentecostalreformadaapi.mappers.register;

import com.platform.igrejapentecostalreformadaapi.data.vo.register.AddressVO;
import com.platform.igrejapentecostalreformadaapi.entities.register.Address;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;


@Mapper(componentModel = "spring")
public interface AddressMapper {

    AddressMapper INSTANCE = Mappers.getMapper(AddressMapper.class);

    @Mapping(source = "number", target = "number")
    AddressVO convertEntityToVO(Address entity);

    @Mapping(source = "number", target = "number")
    List<AddressVO> convertEntitiesToVOs(List<Address> entities);

    @Mapping(source = "number", target = "number")
    Address convertVOToEntity(AddressVO vo);

    @Mapping(source = "number", target = "number")
    List<Address> convertVOsToEntities(List<AddressVO> vos);
}
