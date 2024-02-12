package com.platform.igrejapentecostalreformadaapi.mappers.indicators;

import com.platform.igrejapentecostalreformadaapi.data.vo.indicators.UserProcessVO;
import com.platform.igrejapentecostalreformadaapi.entities.indicators.UserProcess;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserProcessMapper {

    UserProcessMapper INSTANCE = Mappers.getMapper(UserProcessMapper.class);

    UserProcessVO convertEntityToVO(UserProcess process);

    List<UserProcessVO> convertEntitiesToVOs(List<UserProcess> processes);

    UserProcess convertVOToEntity(UserProcessVO process);

    List<UserProcess> convertVOsToEntities(List<UserProcessVO> processesVos);
}
