package com.platform.igrejapentecostalreformadaapi.repositories;
import com.platform.igrejapentecostalreformadaapi.entities.Contact;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.*;

import org.springframework.beans.factory.annotation.Autowired;


class ContactRepositoryTest  {

    private static Contact contact;

    @Autowired
    EntityManager entityManager;

    @Autowired
    public ContactRepository repository;

    @BeforeAll
    public static void setup() {
        contact = new Contact();
    }




}