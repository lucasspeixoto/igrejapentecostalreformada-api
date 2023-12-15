package com.platform.igrejapentecostalreformadaapi.services;

import com.platform.igrejapentecostalreformadaapi.data.vo.ContactVO;
import com.platform.igrejapentecostalreformadaapi.entities.Contact;
import com.platform.igrejapentecostalreformadaapi.repositories.ContactRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ContactServiceTest {

    @Mock
    private ContactRepository repository;

    /*
    @InjectMocks: Quando o service precisar de uma
    instância do serviço ele vai mocar
     */
    @InjectMocks
    private ContactService service;

    public ContactVO contact;

    public static SimpleDateFormat simpleDateFormat;

    public Date birthday;

    @BeforeAll
    public static void setup() {
        simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
    }

    @BeforeEach
    public void configs() throws ParseException {
        birthday = simpleDateFormat.parse("30/10/1991");
        this.contact = new ContactVO(birthday,  "19982621117", "19982621117","Masculino", 3L);
    }

    @DisplayName("JUnit test for Give ContactVO object when save the return saved ContactVO Object")
    @Test
    public void testGivenContactObject_WhenSave_ShouldReturnContactVOObject() {
        /* https://www.baeldung.com/mockito-unnecessary-stubbing-exception */

        lenient().when(repository.findByUserId(anyLong())).thenReturn(Optional.empty());
    }
}