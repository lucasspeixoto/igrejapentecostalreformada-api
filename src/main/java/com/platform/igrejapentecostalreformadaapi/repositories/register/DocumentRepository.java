package com.platform.igrejapentecostalreformadaapi.repositories.register;

import com.platform.igrejapentecostalreformadaapi.entities.register.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface DocumentRepository extends JpaRepository<Document, Long> {

    @Query(nativeQuery = true, value = """
                    SELECT * from documents d
                    WHERE d.user_id = :id
            """)
    Optional<Document> findByUserId(@Param("id") Long id);
}
