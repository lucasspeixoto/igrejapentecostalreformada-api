package com.platform.igrejapentecostalreformadaapi.services;

import com.platform.igrejapentecostalreformadaapi.data.vo.UserProcessVO;
import com.platform.igrejapentecostalreformadaapi.entities.UserProcess;
import com.platform.igrejapentecostalreformadaapi.mapper.UserMapper;
import com.platform.igrejapentecostalreformadaapi.mapper.UserProcessMapper;
import com.platform.igrejapentecostalreformadaapi.repositories.UserProcessRepository;
import com.platform.igrejapentecostalreformadaapi.repositories.UserRepository;
import com.platform.igrejapentecostalreformadaapi.unittests.mapper.mocks.MockUser;
import com.platform.igrejapentecostalreformadaapi.unittests.mapper.mocks.MockUserProcess;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class UserProcessServiceTest {

    MockUserProcess userProcessInput;

    MockUser userInput;

    @Mock
    UserRepository userRepository;

    @Mock
    UserProcessRepository userProcessRepository;

    @InjectMocks
    private UserService userService;

    @InjectMocks
    private UserProcessService userProcessService;

    @Spy
    private UserMapper userMapper = Mappers.getMapper(UserMapper.class);

    @Spy
    private UserProcessMapper userProcessMapper = Mappers.getMapper(UserProcessMapper.class);

    @BeforeEach
    void setUpMocks() throws Exception {
        userInput = new MockUser();
        userProcessInput = new MockUserProcess();
        MockitoAnnotations.openMocks(this);
    }

    @DisplayName("Given UserProcess Object when find by id then return UserProcess Object")
    @Test
    void testGivenUserProcess_WhenFindById_ThenReturnUserProcessObject() {
        UserProcess entity = userProcessInput.mockEntity(1);

        entity.setId(1L);

        when(userProcessRepository.findById(1L)).thenReturn(Optional.of(entity));

        var result = userProcessService.findById(1L);

        assertNotNull(result);
        assertFalse(result.getHasBaptism());
        assertFalse(result.getHasContact());
        assertFalse(result.getHasDocument());
        assertFalse(result.getHasEducation());
        assertFalse(result.getHasFamily());
        assertFalse(result.getHasMember());
    }

    @DisplayName("Given User Process List when find All then return User List")
    @Test
    void testGivenUserProcessObject_WhenFindAll_ThenReturnUsersProcessList() {

        List<UserProcess> userProcessMockList = userProcessInput.mockEntityList();

        given(userProcessRepository.findAll()).willReturn(userProcessMockList);

        List<UserProcess> usersProcesses = userProcessService.findAll();

        assertNotNull(usersProcesses);
        assertEquals(14, usersProcesses.size());

    }

    @DisplayName("Given Empty User Process List when find All then return Empty User List")
    @Test
    void testGivenEmptyUserProcessList_WhenFindAllUserProcesses_ThenReturnEmptyUsersProcessList() {

        given(userProcessRepository.findAll()).willReturn(Collections.emptyList());

        List<UserProcess> usersProcesses = userProcessService.findAll();

        assertTrue(usersProcesses.isEmpty());
        assertEquals(0, usersProcesses.size());

    }

    @DisplayName("Given User Process Object when find by user id then return User Object")
    @Test
    void testGivenUserId_WhenFindByUserId_ThenReturnUsersProcessObject() {

        Long userId = 1L;

        UserProcess entity = userProcessInput.mockEntity(1);

        entity.setId(1L);

        when(userProcessRepository.findByUserId(userId)).thenReturn(Optional.of(entity));

        UserProcessVO result = userProcessService.findByUserId(userId);

        assertNotNull(result);
        assertFalse(result.getHasBaptism());
        assertFalse(result.getHasContact());
        assertFalse(result.getHasDocument());
        assertFalse(result.getHasEducation());
        assertFalse(result.getHasFamily());
        assertFalse(result.getHasMember());

    }
}