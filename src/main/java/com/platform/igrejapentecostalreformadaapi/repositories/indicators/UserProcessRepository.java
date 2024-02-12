package com.platform.igrejapentecostalreformadaapi.repositories.indicators;

import com.platform.igrejapentecostalreformadaapi.entities.indicators.UserProcess;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserProcessRepository extends JpaRepository<UserProcess, Long> {

    @Query(nativeQuery = true, value = """
                    SELECT * from user_process up
                    WHERE up.user_id = :id
            """)
    Optional<UserProcess> findByUserId(@Param("id") Long id);

}
