package com.platform.igrejapentecostalreformadaapi.repositories.support;

import com.platform.igrejapentecostalreformadaapi.entities.support.MembershipType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MembershipTypeRepository extends JpaRepository<MembershipType, Long> {
}
