package com.platform.igrejapentecostalreformadaapi.repositories;

import com.platform.igrejapentecostalreformadaapi.entities.register.Contact;

import static org.junit.jupiter.api.Assertions.*;

import com.platform.igrejapentecostalreformadaapi.entities.User;
import com.platform.igrejapentecostalreformadaapi.repositories.register.ContactRepository;
import integrationtests.testcontainers.AbstractIntegrationTest;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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
@DirtiesContext
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("Contact repository (Integration Tests)")
class ContactRepositoryTest extends AbstractIntegrationTest {

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private UserRepository userRepository;

    public Contact contact;

    public User user;

    public static SimpleDateFormat simpleDateFormat;

    @BeforeAll
    public static void setup() {
        simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
    }


    @BeforeEach
    public void config() throws ParseException {
        this.setUser();
    }

    @DisplayName("JUnit Test for Given a Contact Object when save then return Saved Contact Object")
    @Test
    @Order(1)
    void testGivenContactObject_WhenSave_ThenReturnSavedContact() throws ParseException {

        Contact savedContact = this.contactRepository.save(this.contact);

        assertNotNull(savedContact);
        assertTrue(savedContact.getId() > 0);
        assertEquals("Masculino", savedContact.getSex());
        assertEquals("19982621117", savedContact.getCellphone());
        assertEquals("19982621117", savedContact.getTelephone());

    }

    @DisplayName("JUnit Test for Given a List of Contacts Objects when find all then return Saved Contacts List")
    @Test
    @Order(2)
    void testGivenContactList_WhenFindAll_ThenReturnContactList() throws ParseException {
        Date firstContactBirthday = simpleDateFormat.parse("30/10/1991");
        Contact firstContact = new Contact("Masculino", "199982621117", "199982621117", firstContactBirthday, this.user);

        contactRepository.save(firstContact);

        List<Contact> contacts = contactRepository.findAll();

        assertNotNull(contacts);
        assertEquals(1, contacts.size());

    }


    @DisplayName("JUnit test for get a contact by Id")
    @Test
    @Order(3)
    void testGivenContactObject_WhenFindById_ThenReturnSavedContact() {

        Contact savedContact = this.contactRepository.save(this.contact);

        Contact findContact = this.contactRepository.findById(savedContact.getId()).get();

        assertNotNull(findContact);
        assertEquals(savedContact.getId(), findContact.getId());
        assertEquals(savedContact.getSex(), findContact.getSex());
        assertEquals(savedContact.getCellphone(), findContact.getCellphone());
        assertEquals(savedContact.getTelephone(), findContact.getTelephone());
    }

    @DisplayName("Given Contact Object when find by user id then return Contact Object")
    @Test
    @Order(4)
    void testGivenContactObject_WhenFindByUserId_ThenReturnUserContact() {

        this.contactRepository.save(this.contact);

        Optional<Contact> findOptionalContact = this.contactRepository.findByUserId(this.contact.getId());

        if(findOptionalContact.isPresent()) {
            Contact findContactByUserId = findOptionalContact.get();
            assertNotNull(findContactByUserId);
            assertTrue(findContactByUserId.getId() > 0);
            assertEquals(1L, findContactByUserId.getUserId());
            assertEquals("Masculino", findContactByUserId.getSex());
            assertEquals("19982621117", findContactByUserId.getCellphone());
            assertEquals("19982621117", findContactByUserId.getTelephone());
        }

    }

    private void setUser() throws ParseException {
        Optional<User> optionalUser = userRepository.findById(1L);

        if(optionalUser.isPresent()) {
            user = optionalUser.get();
            this.setContact();
        } else {
            user = null;
        }

    }

    private void setContact() throws ParseException {
        this.contact = new Contact(
                "Masculino",
                "19982621117",
                "19982621117",
                simpleDateFormat.parse("30/10/1991"),
                user
        );
    }

}