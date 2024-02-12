package com.platform.igrejapentecostalreformadaapi.repositories.register;

import com.platform.igrejapentecostalreformadaapi.entities.register.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ContactRepository extends JpaRepository<Contact, Long> {


    /**
     * Find a contact by its user id.
     * Is Tested: true
     * @param id the user id
     * @return the contact
     * @author Lucas Peixoto
     */
    @Query(nativeQuery = true, value = "SELECT * from contacts c WHERE c.user_id = :id")
    Optional<Contact> findByUserId(@Param("id") Long id);
}
