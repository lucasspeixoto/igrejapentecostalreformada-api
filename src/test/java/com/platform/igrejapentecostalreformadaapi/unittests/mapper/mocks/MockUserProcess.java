package com.platform.igrejapentecostalreformadaapi.unittests.mapper.mocks;

import com.platform.igrejapentecostalreformadaapi.data.vo.UserProcessVO;
import com.platform.igrejapentecostalreformadaapi.entities.UserProcess;

import java.util.ArrayList;
import java.util.List;

public class MockUserProcess {

    public UserProcess mockEntity() {
        return mockEntity(0);
    }

    public UserProcessVO mockVO() {
        return mockVO(0);
    }

    public List<UserProcess> mockEntityList() {
        List<UserProcess> users = new ArrayList<>();

        for (int i = 0; i < 14; i++) {
            users.add(mockEntity(i));
        }
        return users;
    }

    public List<UserProcessVO> mockVOList() {
        List<UserProcessVO> users = new ArrayList<>();
        for (int i = 0; i < 14; i++) {
            users.add(mockVO(i));
        }
        return users;
    }

    public UserProcess mockEntity(Integer number) {
        UserProcess user = new UserProcess();
        user.setId(number.longValue());
        user.setUserId(number.longValue());

        user.setHasBaptism(false);
        user.setHasContact(false);
        user.setHasDocument(false);
        user.setHasEducation(false);
        user.setHasFamily(false);
        user.setHasMember(false);

        return user;
    }

    public UserProcessVO mockVO(Integer number) {
        UserProcessVO user = new UserProcessVO();
        user.setId(number.longValue());
        user.setUserId(number.longValue());

        user.setHasBaptism(false);
        user.setHasContact(false);
        user.setHasDocument(false);
        user.setHasEducation(false);
        user.setHasFamily(false);
        user.setHasMember(false);

        return user;
    }

}

