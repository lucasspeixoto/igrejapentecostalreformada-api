package com.platform.igrejapentecostalreformadaapi.repositories.register;

import com.platform.igrejapentecostalreformadaapi.entities.register.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface AddressRepository extends JpaRepository<Address, Long> {

    @Query(nativeQuery = true, value = """
                    SELECT * from address a
                    WHERE a.user_id = :id
            """)
    Optional<Address> findByUserId(@Param("id") Long id);
}
