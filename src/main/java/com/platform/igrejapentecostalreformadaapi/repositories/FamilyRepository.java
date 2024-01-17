package com.platform.igrejapentecostalreformadaapi.repositories;

import com.platform.igrejapentecostalreformadaapi.entities.Family;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface FamilyRepository extends JpaRepository<Family, Long> {

    @Query(nativeQuery = true, value = """
                    SELECT * from family f
                    WHERE f.user_id = :id
            """)
    Optional<Family> findByUserId(@Param("id") Long id);
}
