package com.platform.igrejapentecostalreformadaapi.services;

import com.platform.igrejapentecostalreformadaapi.entities.User;
import com.platform.igrejapentecostalreformadaapi.mapper.UserMapper;
import com.platform.igrejapentecostalreformadaapi.repositories.UserRepository;
import com.platform.igrejapentecostalreformadaapi.unittests.mapper.mocks.MockUser;
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

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    MockUser input;

    @Mock
    UserRepository repository;

    @InjectMocks
    private UserService service;

    @Spy
    private UserMapper userMapper = Mappers.getMapper(UserMapper.class);

    @BeforeEach
    void setUpMocks() throws Exception {
        input = new MockUser();
        MockitoAnnotations.openMocks(this);
    }

    @DisplayName("Given User is when find by id then return User Object")
    @Test
    void testGivenUserId_WhenFindById_ThenReturnUser() {
        User entity = input.mockEntity(1);

        entity.setId(1L);

        when(repository.findById(1L)).thenReturn(Optional.of(entity));

        var result = service.findById(1L);

        assertNotNull(result);
        assertEquals("Lucas Peixoto", result.getName());
        assertEquals("lspeixotodev@gmail.com", result.getEmail());
        assertEquals("lspeixotodev", result.getUsername());

    }

    @DisplayName("Given User username or email when find by username or email then return User Object")
    @Test
    void testGivenUserEmail_WhenFindByUsernameOrEmail_ThenReturnUser() {
        User entity = input.mockEntity(1);

        String userName = "lspeixotodev";
        String email = "lspeixotodev@gmail.com";

        entity.setUsername(userName);
        entity.setEmail(email);

        when(repository.findByUsernameOrEmail(userName, email)).thenReturn(Optional.of(entity));

        var result = service.findByUsernameOrEmail(userName, email);

        assertNotNull(result);
        assertEquals("Lucas Peixoto", result.getName());
        assertEquals("lspeixotodev@gmail.com", result.getEmail());
        assertEquals("lspeixotodev", result.getUsername());

    }

    @DisplayName("Given User List when find All then return User List")
    @Test
    void testGivenUserObject_WhenFindByUsernameOrEmail_ThenReturnUser() {

        List<User> personsMockList = input.mockEntityList();

        when(repository.findAll()).thenReturn(personsMockList);

        var users = service.findAll();

        assertNotNull(users);
        assertEquals(14, users.size());

    }
}