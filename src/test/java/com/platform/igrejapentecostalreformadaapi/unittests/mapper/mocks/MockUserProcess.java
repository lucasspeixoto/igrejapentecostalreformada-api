package com.platform.igrejapentecostalreformadaapi.unittests.mapper.mocks;

import com.platform.igrejapentecostalreformadaapi.data.vo.indicators.UserProcessVO;
import com.platform.igrejapentecostalreformadaapi.entities.User;
import com.platform.igrejapentecostalreformadaapi.entities.indicators.UserProcess;

import java.util.ArrayList;
import java.util.List;

public class MockUserProcess {

    MockUser userInput;

    public UserProcess mockEntity() {
        return mockEntity(0);
    }

    public UserProcessVO mockVO() {
        return mockVO(0);
    }

    public List<UserProcess> mockEntityList() {
        List<UserProcess> userProcess = new ArrayList<>();

        for (int i = 0; i < 14; i++) {
            userProcess.add(mockEntity(i));
        }
        return userProcess;
    }

    public List<UserProcessVO> mockVOList() {
        List<UserProcessVO> userProcess = new ArrayList<>();
        for (int i = 0; i < 14; i++) {
            userProcess.add(mockVO(i));
        }
        return userProcess;
    }

    public UserProcess mockEntity(Integer number) {

        UserProcess userProcess = new UserProcess();

        userProcess.setId(number.longValue());
        userProcess.setHasBaptism(false);
        userProcess.setHasContact(false);
        userProcess.setHasDocument(false);
        userProcess.setHasEducation(false);
        userProcess.setHasFamily(false);
        userProcess.setHasMember(false);

        return userProcess;
    }

    public UserProcessVO mockVO(Integer number) {
        UserProcessVO userProcess = new UserProcessVO();
        User user = new User(1L, "Lucas Peixoto", "lspeixotodev", "lspeixotodev@gmail.com", "1234");

        userProcess.setUser(user);
        userProcess.setId(number.longValue());
        userProcess.setHasBaptism(false);
        userProcess.setHasContact(false);
        userProcess.setHasDocument(false);
        userProcess.setHasEducation(false);
        userProcess.setHasFamily(false);
        userProcess.setHasMember(false);

        return userProcess;
    }

}

