package com.zonesion.cloud.repository;

import com.zonesion.cloud.domain.Major;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Major entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MajorRepository extends JpaRepository<Major,Long> {
    
}
