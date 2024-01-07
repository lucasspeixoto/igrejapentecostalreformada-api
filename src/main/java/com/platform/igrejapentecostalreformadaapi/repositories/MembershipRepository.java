package com.platform.igrejapentecostalreformadaapi.repositories;
import com.platform.igrejapentecostalreformadaapi.entities.Membership;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MembershipRepository extends JpaRepository<Membership, Long> {

    @Query(nativeQuery = true, value = """
                    SELECT * from membership
                    WHERE membership.user_id = :id
            """)
    Optional<Membership> findByUserId(@Param("id") Long id);
}
