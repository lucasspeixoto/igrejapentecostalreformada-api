package com.platform.igrejapentecostalreformadaapi.repositories;
import com.platform.igrejapentecostalreformadaapi.entities.UserProcess;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserProcessRepository extends JpaRepository<UserProcess, Long> {

    @Query(nativeQuery = true, value = """
                    SELECT * from user_process up
                    WHERE up.user_id = :id
            """)
    Optional<UserProcess> findByUserId(@Param("id") Long id);

    /* @Modifying é necessário, pois é uma modificação customizada,
    * ou seja, não é o SpringData que está realizando essa manipulação
    * no nosso banco
    */
    @Modifying
    @Query("UPDATE UserProcess up SET up.hasContact = true WHERE up.userId = :userId")
    void setHasContact(@Param("userId") Long userId);
}
