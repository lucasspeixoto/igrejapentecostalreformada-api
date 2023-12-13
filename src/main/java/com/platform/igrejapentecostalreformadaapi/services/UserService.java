package com.platform.igrejapentecostalreformadaapi.services;

import com.platform.igrejapentecostalreformadaapi.data.vo.UserDetailVO;
import com.platform.igrejapentecostalreformadaapi.data.vo.UserVO;
import com.platform.igrejapentecostalreformadaapi.entities.User;
import com.platform.igrejapentecostalreformadaapi.exceptions.ResourceNotFoundException;
import com.platform.igrejapentecostalreformadaapi.mapper.UserMapper;
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
    private UserMapper userMapper;

    @Autowired
    private ModelMapper modelMapper;

    public UserVO findByUsernameOrEmail(String username, String email) {
        User user = this.repository.findByUsernameOrEmail(username, email).orElseThrow(
                () -> new ResourceNotFoundException("User", "email", 1L)
        );

        return this.userMapper.convertEntityToVO(user);

    }

    public List<UserVO> findAll() {
        List<User> users = this.repository.findAll();

        return this.userMapper.convertEntitiesToVOs(users);
    }

}
