package com.platform.igrejapentecostalreformadaapi.repositories;

import com.platform.igrejapentecostalreformadaapi.entities.UserProcess;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("UserProcess repository tests")
class UserProcessRepositoryTest {

    @Autowired
    private UserProcessRepository repository;

    public UserProcess userProcess;

    public UserProcess savedUserProcess;

    @BeforeEach
    public void setup() {
       this.userProcess = new UserProcess(
                false,
                true,
                true,
                true,
                true,
                true,
                true,
                3L
        );

        this.savedUserProcess = this.repository.save(this.userProcess);
    }

    @DisplayName("Given User Process Object when find by id then return UserProcess Object")
    @Test
    @Order(1)
    void testGivenUserProcessObject_WhenFindById_ThenReturnSavedUserProcess() {

        UserProcess findUserProcessById = this.repository.findById(savedUserProcess.getId()).get();

        assertNotNull(findUserProcessById);
        assertEquals(savedUserProcess.getId(), findUserProcessById.getId());
        assertEquals(3L, findUserProcessById.getUserId());
        assertFalse(findUserProcessById.isHasContact());
        assertTrue(findUserProcessById.isHasAddress());
        assertTrue(findUserProcessById.isHasBaptism());
        assertTrue(findUserProcessById.isHasDocument());
        assertTrue(findUserProcessById.isHasFamily());
        assertTrue(findUserProcessById.isHasEducation());
        assertTrue(findUserProcessById.isHasMember());
    }

    @DisplayName("Given User Process Object with hasContact true when call setHasContact then return UserProcess Object Updated")
    @Test
    @Order(2)
    void testGivenUserProcessObject_WhenCallSetHasContact_ThenReturnUpdatedUserProcess() {

        UserProcess findUserProcess = this.repository.findById(savedUserProcess.getId()).get();

        this.repository.setHasContact(3L);

        assertFalse(findUserProcess.isHasContact());

    }

    @DisplayName("Given User Process Object when find by user id then return UserProcess Object")
    @Test
    @Order(3)
    void testGivenUserProcessObject_WhenFindByUserId_ThenReturnUserProcess() {

        UserProcess findUserProcessByUserId = this.repository.findByUserId(3L).get();

        assertNotNull(findUserProcessByUserId);
        assertEquals(savedUserProcess.getId(), findUserProcessByUserId.getId());
        assertEquals(3L, findUserProcessByUserId.getUserId());
        assertFalse(findUserProcessByUserId.isHasContact());
        assertTrue(findUserProcessByUserId.isHasAddress());
        assertTrue(findUserProcessByUserId.isHasBaptism());
        assertTrue(findUserProcessByUserId.isHasDocument());
        assertTrue(findUserProcessByUserId.isHasFamily());
        assertTrue(findUserProcessByUserId.isHasEducation());
        assertTrue(findUserProcessByUserId.isHasMember());
    }
}