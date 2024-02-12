package com.platform.igrejapentecostalreformadaapi.services.register;

import com.platform.igrejapentecostalreformadaapi.data.vo.register.ContactVO;
import com.platform.igrejapentecostalreformadaapi.entities.User;
import com.platform.igrejapentecostalreformadaapi.entities.register.Contact;
import com.platform.igrejapentecostalreformadaapi.exceptions.ResourceAlreadyExistsException;
import com.platform.igrejapentecostalreformadaapi.exceptions.ResourceNotFoundException;
import com.platform.igrejapentecostalreformadaapi.mappers.UserMapper;
import com.platform.igrejapentecostalreformadaapi.mappers.register.ContactMapper;
import com.platform.igrejapentecostalreformadaapi.repositories.UserRepository;
import com.platform.igrejapentecostalreformadaapi.repositories.register.ContactRepository;
import com.platform.igrejapentecostalreformadaapi.services.register.impl.ContactServiceImpl;
import com.platform.igrejapentecostalreformadaapi.unittests.mapper.mocks.MockUser;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;

import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.BDDMockito.*;

import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("Contact Service (Unit Testing)")
class ContactServiceImplTest {

    @Mock
    private ContactRepository contactRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private ContactServiceImpl contactService;

    MockUser userInput;

    @Spy
    private ContactMapper contactMapper = Mappers.getMapper(ContactMapper.class);

    @Spy
    private UserMapper userMapper = Mappers.getMapper(UserMapper.class);

    private Contact contact;

    private User user;

    @BeforeEach
    public void setUp() throws ParseException {
        this.setUser();
        this.setContact();
    }


    @DisplayName("JUnit test for create a contact method (Success case)")
    @Order(1)
    @Test
    public void givenUserAndEmptyContactObjects_WhenCreateContact_ThenReturnContactObject() throws Exception {
        //given - precondition or setup
        long userId = user.getId();

        given(userRepository.findById(userId))
                .willReturn(Optional.of(user));

        given(contactRepository.findByUserId(userId))
                .willReturn(Optional.empty());

        ContactVO contactVo = contactMapper.convertEntityToVO(contact);

        given(contactRepository.save(contact))
                .willReturn(contact);

        //when - action or the behaviour we`re testing
        ContactVO savedContactVo = contactService.create(contactVo, userId);

        //then - verify the output
        assertThat(savedContactVo).isNotNull();
        assertThat(savedContactVo.getSex()).isEqualTo("Masculino");
        assertThat(savedContactVo.getTelephone()).isEqualTo("19982621117");
        assertThat(savedContactVo.getCellphone()).isEqualTo("19982621117");
    }

    @DisplayName("JUnit test for create a contact method (Failed case With Empty User)")
    @Order(2)
    @Test
    public void givenEmptyUserObject_WhenCreateContactWithEmptyUser_ThenThrowsResourceNotFoundException() throws Exception {
        //given - precondition or setup
        long userId = user.getId();

        given(userRepository.findById(userId))
                .willReturn(Optional.empty());

        //when - action or the behaviour we`re testing
        ContactVO contactVo = contactMapper.convertEntityToVO(contact);

        org.junit.jupiter.api.Assertions.assertThrows(
                ResourceNotFoundException.class,
                () -> contactService.create(contactVo, userId)
        );

        //then - verify the output
        verify(contactRepository, never()).save(any(Contact.class));
    }

    @DisplayName("JUnit test for create a contact method (Failed case With Existing Contact)")
    @Order(3)
    @Test
    public void givenUserAndContactObject_WhenCreateContactWithExistingContact_ThenThrowsResourceAlreadyExistsException() throws Exception {
        //given - precondition or setup
        long userId = user.getId();

        given(userRepository.findById(userId))
                .willReturn(Optional.of(user));

        given(contactRepository.findByUserId(userId))
                .willReturn(Optional.of(contact));

        //when - action or the behaviour we`re testing
        ContactVO contactVo = contactMapper.convertEntityToVO(contact);

        org.junit.jupiter.api.Assertions.assertThrows(
                ResourceAlreadyExistsException.class,
                () -> contactService.create(contactVo, userId)
        );

        //then - verify the output
        verify(contactRepository, never()).save(any(Contact.class));
    }

    @DisplayName("JUnit test for find all contacts (Empty case)")
    @Order(4)
    @Test
    public void givenEmptyContactsList_WhenFindAll_ThenReturnEmptyContactsList() throws Exception {
        //given - precondition or setup
        given(contactRepository.findAll())
                .willReturn(Collections.emptyList());

        //when - action or the behaviour we`re testing
        List<ContactVO> contactsVo = contactService.findAll();

        List<Contact> contacts = contactMapper.convertVOsToEntities(contactsVo);

        //then - verify the output
        assertThat(contacts).isEmpty();
    }

    @DisplayName("JUnit test for find all contacts (Empty case)")
    @Order(5)
    @Test
    public void givenContactsList_WhenFindAll_ThenReturnContactsList() throws Exception {
        Contact newContact =  new Contact(
                "Feminino",
                "19981448980",
                "19981448980",
                new Date(),
                null
        );
        //given - precondition or setup
        given(contactRepository.findAll())
                .willReturn(List.of(contact, newContact));

        //when - action or the behaviour we`re testing
        List<ContactVO> contactsVo = contactService.findAll();

        List<Contact> contacts = contactMapper.convertVOsToEntities(contactsVo);

        //then - verify the output
        assertThat(contacts.size()).isEqualTo(2);
    }

    @DisplayName("JUnit test for find contact by id method (Success case)")
    @Order(6)
    @Test
    public void givenContactId_WhenFindById_ThenReturnContactObject() throws Exception {
        //given - precondition or setup
        given(contactRepository.findById(contact.getId()))
                .willReturn(Optional.of(contact));

        //when - action or the behaviour we`re testing
        ContactVO savedContactVo = contactService.findById(contact.getId());

        //then - verify the output
        assertThat(savedContactVo).isNotNull();
        assertThat(savedContactVo.getSex()).isEqualTo("Masculino");
        assertThat(savedContactVo.getTelephone()).isEqualTo("19982621117");
        assertThat(savedContactVo.getCellphone()).isEqualTo("19982621117");
    }

    @DisplayName("JUnit test for find contact by id method (Failed case)")
    @Order(7)
    @Test
    public void givenContactId_WhenFindById_ThenThrowAnException() throws Exception {
        //given - precondition or setup
        given(contactRepository.findById(anyLong()))
                .willReturn(Optional.empty());

        //then - verify the output
        org.junit.jupiter.api.Assertions.assertThrows(
                ResourceNotFoundException.class,
                //when - action or the behaviour we`re testing
                () -> contactService.findById(anyLong())
        );
    }

    @DisplayName("JUnit test for find contact by user id method (Success case)")
    @Order(8)
    @Test
    public void givenContactUserId_WhenFindById_ThenReturnContactObject() throws Exception {
        //given - precondition or setup
        given(contactRepository.findByUserId(user.getId()))
                .willReturn(Optional.of(contact));

        //when - action or the behaviour we`re testing
        ContactVO savedContactVo = contactService.findByUserId(user.getId());

        //then - verify the output
        assertThat(savedContactVo).isNotNull();
        assertThat(savedContactVo.getSex()).isEqualTo("Masculino");
        assertThat(savedContactVo.getTelephone()).isEqualTo("19982621117");
        assertThat(savedContactVo.getCellphone()).isEqualTo("19982621117");
    }

    @DisplayName("JUnit test for find contact by user id method (Failed case)")
    @Order(9)
    @Test
    public void givenContactUserId_WhenFindById_ThenThrowAnException() throws Exception {
        //given - precondition or setup
        given(contactRepository.findByUserId(anyLong()))
                .willReturn(Optional.empty());

        //then - verify the output
        org.junit.jupiter.api.Assertions.assertThrows(
                ResourceNotFoundException.class,
                //when - action or the behaviour we`re testing
                () -> contactService.findByUserId(anyLong())
        );
    }

    @DisplayName("JUnit test for update contact method (Success case)")
    @Order(10)
    @Test
    public void givenContactObject_WhenUpdate_ThenReturnUpdatedContactObject() throws Exception {
        //given - precondition or setup
        given(contactRepository.findById(contact.getId()))
                .willReturn(Optional.of(contact));

        contact.setSex("Feminino");

        given(contactRepository.save(contact))
                .willReturn(contact);

        //when - action or the behaviour we`re testing
        contactService.update(contactMapper.convertEntityToVO(contact));

        ContactVO updatedContact = contactService.findById(contact.getId());

        //then - verify the output
        assertThat(updatedContact).isNotNull();
        assertThat(updatedContact.getSex()).isEqualTo("Feminino");
    }

    @DisplayName("JUnit test for update contact method (Failed case)")
    @Order(11)
    @Test
    public void givenContactObject_WhenUpdate_ThenThrowsAnException() {
        //given - precondition or setup
        given(contactRepository.findById(contact.getId()))
                .willReturn(Optional.empty());

        //when - action or the behaviour we`re testing
        org.junit.jupiter.api.Assertions.assertThrows(
                ResourceNotFoundException.class,
                () -> contactService.update(contactMapper.convertEntityToVO(contact))
        );

        //then - verify the output
        verify(contactRepository, never()).save(any(Contact.class));
    }

    private void setUser() {
        user = new User();
        user.setId(1L);
        user.setName("Lucas Peixoto");
        user.setEmail("lspeixotodev@gmail.com");
        user.setUsername("lspeixotodev");
    }

    private void setContact() throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

        Date birthday = simpleDateFormat.parse("30/10/1991");

        contact = new Contact(
                "Masculino",
                "19982621117",
                "19982621117",
                birthday,
                user
        );
    }

}