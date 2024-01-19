package com.platform.igrejapentecostalreformadaapi.repositories.register;

import com.platform.igrejapentecostalreformadaapi.entities.register.Baptism;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface BaptismRepository extends JpaRepository<Baptism, Long> {

    @Query(nativeQuery = true, value = """
                    SELECT * from baptism b
                    WHERE b.user_id = :id
            """)
    Optional<Baptism> findByUserId(@Param("id") Long id);
}
