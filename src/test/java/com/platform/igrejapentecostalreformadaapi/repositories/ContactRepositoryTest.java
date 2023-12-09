package com.platform.igrejapentecostalreformadaapi.repositories;
import com.platform.igrejapentecostalreformadaapi.entities.Contact;
import com.platform.igrejapentecostalreformadaapi.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

/**
 * O @DataJpaTest sobe um bando de dados em memória
 * para realização dos testes, neste caso o h2-database.
 * O @DataJpaTest não carrega outros beans do Spring
 * como @Components, @Controller ou @Service

 * Por padrão ele procura por classes @Entity e configura
 * os repositórios do Spring Data JPA anotados com @Repository

 * Por padrão os testes anotados com @DataJpaTest são transacionais,
 * ou seja, são revertidos ao final de cada teste
 */

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ContactRepositoryTest  {

    public Contact contact;

    @Autowired
    private ContactRepository repository;

    public static SimpleDateFormat simpleDateFormat;

    @BeforeAll
    public static void setup() {

        simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
    }

    @BeforeEach
    public void configs() throws ParseException {
        Date birthday = simpleDateFormat.parse("30/10/1991");
        this.contact = new Contact("Masculino",  "114982621117", "114982621117",birthday, 3L);
    }

    @DisplayName("Given Contact Object when find by id then return Contact Object")
    @Test
    void testGivenContactObject_WhenSave_ThenReturnSavedContact() throws ParseException {

        Contact savedContact = this.repository.save(this.contact);

        assertNotNull(savedContact);
        assertTrue(savedContact.getId() > 0);
    }

    @DisplayName("Given Contact Object when find by id then return Contact Object")
    @Test
    void testGivenContactObject_WhenFindById_ThenReturnSavedContact() {

        Contact savedContact = this.repository.save(this.contact);
        Contact findContact = this.repository.findById(savedContact.getId()).get();

        assertNotNull(findContact);
        assertEquals(savedContact.getId(), findContact.getId());
    }
}