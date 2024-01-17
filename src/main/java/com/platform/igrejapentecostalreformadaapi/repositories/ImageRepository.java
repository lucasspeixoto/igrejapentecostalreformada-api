package com.platform.igrejapentecostalreformadaapi.repositories;

import com.platform.igrejapentecostalreformadaapi.entities.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ImageRepository extends JpaRepository<Image, Long>{

    @Query(nativeQuery = true, value = """
                    SELECT * from images i
                    WHERE i.user_id = :id
            """)
    Optional<Image> findByUserId(@Param("id") Long id);

}