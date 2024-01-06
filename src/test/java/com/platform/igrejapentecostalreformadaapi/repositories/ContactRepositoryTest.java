package com.platform.igrejapentecostalreformadaapi.repositories;

import com.platform.igrejapentecostalreformadaapi.entities.userForms.Contact;

import static org.junit.jupiter.api.Assertions.*;

import com.platform.igrejapentecostalreformadaapi.entities.User;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * O @DataJpaTest sobe um bando de dados em memória
 * para realização dos testes, neste caso o h2-database.
 * O @DataJpaTest não carrega outros beans do Spring
 * como @Components, @Controller ou @Service
 * <p>
 * Por padrão ele procura por classes @Entity e configura
 * os repositórios do Spring Data JPA anotados com @Repository
 * <p>
 * Por padrão os testes anotados com @DataJpaTest são transacionais,
 * ou seja, são revertidos ao final de cada teste
 */

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("Contact repository tests")
class ContactRepositoryTest {

    @Autowired
    private ContactRepository repository;

    public Contact contact;

    public static User user;

    public static SimpleDateFormat simpleDateFormat;

    @BeforeAll
    public static void setup() {
        simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
    }

    @BeforeEach
    public void config() throws ParseException {
        Date birthday = simpleDateFormat.parse("30/10/1991");

        this.contact = new Contact("Masculino", "19982621117", "19982621117", birthday, null);
    }

    @DisplayName("Given Contact List when find all then return Contact List")
    @Test
    @Order(1)
    void testGivenContactList_WhenFindAll_ThenReturnContactList() throws ParseException {
        Date firstContactBirthday = simpleDateFormat.parse("30/10/1991");
        Contact firstContact = new Contact("Masculino", "199982621117", "199982621117", firstContactBirthday, this.user);

        Date secondContactBirthday = simpleDateFormat.parse("10/02/1994");
        Contact secondContact = new Contact("Feminino", "19981448980", "19981448980", secondContactBirthday, this.user);

        repository.save(firstContact);
        repository.save(secondContact);

        List<Contact> contacts = repository.findAll();

        assertNotNull(contacts);

    }

    @DisplayName("Given Contact Object when find by id then return Contact Object")
    @Test
    @Order(2)
    void testGivenContactObject_WhenSave_ThenReturnSavedContact() throws ParseException {

        Contact savedContact = this.repository.save(this.contact);

        assertNotNull(savedContact);
        assertTrue(savedContact.getId() > 0);
    }

    @DisplayName("Given Contact Object when find by id then return Contact Object")
    @Test
    @Order(3)
    void testGivenContactObject_WhenFindById_ThenReturnSavedContact() {

        Contact savedContact = this.repository.save(this.contact);
        Contact findContact = this.repository.findById(savedContact.getId()).get();

        assertNotNull(findContact);
        assertEquals(savedContact.getId(), findContact.getId());
    }

    @DisplayName("Given Contact Object when find by user id then return Contact Object")
    @Test
    @Order(4)
    void testGivenContactObject_WhenFindByUserId_ThenReturnUserContact() {

        this.repository.save(this.contact);
        Contact findContactByUserId = this.repository.findByUserId(1L).get();

        assertNotNull(findContactByUserId);
        assertTrue(findContactByUserId.getId() > 0);
        assertEquals(1L, findContactByUserId.getUserId());
        assertEquals("Masculino", findContactByUserId.getSex());
        assertEquals("19982621117", findContactByUserId.getCellphone());
        assertEquals("19982621117", findContactByUserId.getTelephone());
    }
}