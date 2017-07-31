package com.zonesion.cloud.repository;

import com.zonesion.cloud.domain.CourseFavorite;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the CourseFavorite entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CourseFavoriteRepository extends JpaRepository<CourseFavorite,Long> {
    
}
