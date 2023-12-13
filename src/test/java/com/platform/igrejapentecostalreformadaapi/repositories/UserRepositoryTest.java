package com.platform.igrejapentecostalreformadaapi.repositories;

import com.platform.igrejapentecostalreformadaapi.entities.User;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("User repository tests")
class UserRepositoryTest {

    public User user;

    @Autowired
    public UserRepository repository;

    @BeforeEach
    public void configs() {
        this.user = new User("Lucas Sacramoni Peixoto", "lspeixotodev", "lspeixotodev@gmail.com", "1234");
    }

    @DisplayName("Given User List when find all then return User List")
    @Test
    @Order(1)
    void testGivenUserList_WhenFindAll_ThenReturnUserList()  {

        User secondUser = new User("Liana Fernandes", "lianacgf", "lianacgf@gmail.com", "1234");

        User savedSecondUser = repository.save(secondUser);

        List<User> users = repository.findAll();

        assertNotNull(users);
        assertEquals("Liana Fernandes", savedSecondUser.getName());
        assertEquals("lianacgf", savedSecondUser.getUsername());
        assertEquals("lianacgf@gmail.com", savedSecondUser.getEmail());
        assertTrue(savedSecondUser.getId() > 0);

    }

    @DisplayName("Given User Object when find by username or email then return user Object")
    @Test
    @Order(2)
    void testGivenUserObject_WhenFindByUsernameOrEmail_ThenReturnFindUser() {

        Optional<User> user = this.repository.findByUsernameOrEmail("lspeixotodev", "lspeixotodev@gmail.com");

        User findedUser = user.get();

        assertNotNull(findedUser);
        assertEquals("Lucas Sacramoni Peixoto", findedUser.getName());
    }
}