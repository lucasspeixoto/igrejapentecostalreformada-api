package com.platform.igrejapentecostalreformadaapi.unittests.mapper.mocks;

import com.platform.igrejapentecostalreformadaapi.data.vo.UserVO;
import com.platform.igrejapentecostalreformadaapi.entities.User;

import java.util.ArrayList;
import java.util.List;

public class MockUser {

    public User mockEntity() {
        return mockEntity(0);
    }

    public UserVO mockVO() {
        return mockVO(0);
    }

    public List<User> mockEntityList() {
        List<User> users = new ArrayList<User>();

        for (int i = 0; i < 14; i++) {
            users.add(mockEntity(i));
        }
        return users;
    }

    public List<UserVO> mockVOList() {
        List<UserVO> users = new ArrayList<>();
        for (int i = 0; i < 14; i++) {
            users.add(mockVO(i));
        }
        return users;
    }

    public User mockEntity(Integer number) {
        User user = new User();
        user.setId(number.longValue());
        user.setName("Lucas Peixoto");
        user.setEmail("lspeixotodev@gmail.com");
        user.setUsername("lspeixotodev");

        return user;
    }

    public UserVO mockVO(Integer number) {
        UserVO user = new UserVO();
        user.setId(number.longValue());
        user.setName("Lucas Peixoto");
        user.setEmail("lspeixotodev@gmail.com");
        user.setUsername("lspeixotodev");

        return user;
    }

}

