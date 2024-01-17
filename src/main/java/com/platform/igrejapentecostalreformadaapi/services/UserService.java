package com.platform.igrejapentecostalreformadaapi.services;

import com.platform.igrejapentecostalreformadaapi.data.vo.UserVO;
import com.platform.igrejapentecostalreformadaapi.entities.User;
import com.platform.igrejapentecostalreformadaapi.exceptions.ResourceNotFoundException;
import com.platform.igrejapentecostalreformadaapi.mappers.UserMapper;
import com.platform.igrejapentecostalreformadaapi.repositories.UserRepository;
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

    public UserVO findById(Long id) {

        User user = this.repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("User", "id", id)
        );

        return this.userMapper.convertEntityToVO(user);
    }

}
