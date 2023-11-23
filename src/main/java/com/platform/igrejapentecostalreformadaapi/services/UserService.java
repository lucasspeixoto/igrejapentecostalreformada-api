package com.platform.igrejapentecostalreformadaapi.services;


import com.platform.igrejapentecostalreformadaapi.data.vo.UserVO;
import com.platform.igrejapentecostalreformadaapi.entities.User;
import com.platform.igrejapentecostalreformadaapi.exceptions.ResourceNotFoundException;
import com.platform.igrejapentecostalreformadaapi.mapper.PlatformMapper;
import com.platform.igrejapentecostalreformadaapi.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class UserService {

    private final Logger logger = Logger.getLogger(UserService.class.getName());

    @Autowired
    private UserRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    public UserVO findByUsernameOrEmail(String usernameOrEmail) {
        User user = this.repository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail).orElseThrow(
                () -> new ResourceNotFoundException("User", "email", 1L)
        );

        return this.convertEntityToDTO(user);
    }

    //! Mapper methods ---------------------------------------------------------------------------
    private UserVO convertEntityToDTO(User entity) {
        return PlatformMapper.parseObject(entity, UserVO.class, modelMapper);
    }

    private User convertDTOToEntity(UserVO postDTO) {
        return PlatformMapper.parseObject(postDTO, User.class, modelMapper);
    }

    private List<UserVO> convertEntitiesToDTOs(List<User> entities) {
        return PlatformMapper.parseListObjects(entities, UserVO.class, modelMapper);
    }

    private List<User> convertDTOsToEntities(List<UserVO> states) {
        return PlatformMapper.parseListObjects(states, User.class, modelMapper);
    }
    //! --------------------------------------------------------------------------- Mapper methods

}
