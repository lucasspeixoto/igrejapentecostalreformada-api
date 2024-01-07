package com.platform.igrejapentecostalreformadaapi.mapper;

import com.platform.igrejapentecostalreformadaapi.data.vo.UserVO;
import com.platform.igrejapentecostalreformadaapi.entities.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserVO convertEntityToVO(User user);

    List<UserVO> convertEntitiesToVOs(List<User> users);

    User convertVOToEntity(UserVO user);

    List<User> convertVOsToEntities(List<UserVO> usersVos);
}
