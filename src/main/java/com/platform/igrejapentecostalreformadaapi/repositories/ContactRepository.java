package com.platform.igrejapentecostalreformadaapi.repositories;
import com.platform.igrejapentecostalreformadaapi.entities.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ContactRepository extends JpaRepository<Contact, Long> {

    @Query(nativeQuery = true, value = """
                    SELECT * from contacts c
                    WHERE c.user_id = :id
            """)
    Optional<Contact> findByUserId(@Param("id") Long id);
}
