package com.platform.igrejapentecostalreformadaapi.mapper;
import com.platform.igrejapentecostalreformadaapi.data.vo.UserProcessVO;
import com.platform.igrejapentecostalreformadaapi.entities.UserProcess;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserProcessMapper {

    UserProcessVO convertEntityToVO(UserProcess process);

    List<UserProcessVO> convertEntitiesToVOs(List<UserProcess> processes);

    UserProcess convertVOToEntity(UserProcessVO process);

    List<UserProcess> convertVOsToEntities(List<UserProcessVO> processesVos);
}
