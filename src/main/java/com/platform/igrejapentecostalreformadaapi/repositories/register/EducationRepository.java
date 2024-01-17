package com.platform.igrejapentecostalreformadaapi.repositories.register;

import com.platform.igrejapentecostalreformadaapi.entities.register.Education;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface EducationRepository extends JpaRepository<Education, Long> {

    @Query(nativeQuery = true, value = """
                    SELECT * from education e
                    WHERE e.user_id = :id
            """)
    Optional<Education> findByUserId(@Param("id") Long id);
}
