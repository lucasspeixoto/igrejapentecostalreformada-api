package com.platform.igrejapentecostalreformadaapi.repositories;

import com.platform.igrejapentecostalreformadaapi.entities.UserProcess;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("UserProcess repository tests")
class UserProcessRepositoryTest {

    public UserProcess userProcess;

    @Autowired
    private UserProcessRepository repository;

    @BeforeEach
    public void configs() throws ParseException {

        this.userProcess = new UserProcess(
                true,
                true,
                true,
                true,
                true,
                true,
                true,
                2L
        );
    }


    @DisplayName("Given User Process Object when find by id then return UserProcess Object")
    @Test
    void testGivenUserProcessObject_WhenFindById_ThenReturnSavedUserProcess() {

        UserProcess savedUserProcess = this.repository.save(this.userProcess);
        UserProcess findUserProcess = this.repository.findById(savedUserProcess.getId()).get();

        assertNotNull(findUserProcess);
        assertEquals(savedUserProcess.getId(), findUserProcess.getId());

        assertEquals(2L, findUserProcess.getUserId());
        assertTrue(findUserProcess.isHasAddress());
        assertTrue(findUserProcess.isHasBaptism());
        assertTrue(findUserProcess.isHasContact());
        assertTrue(findUserProcess.isHasDocument());
        assertTrue(findUserProcess.isHasFamily());
        assertTrue(findUserProcess.isHasEducation());
        assertTrue(findUserProcess.isHasMember());
    }
}