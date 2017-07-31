package com.zonesion.cloud.repository;

import com.zonesion.cloud.domain.CourseLessonLearn;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the CourseLessonLearn entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CourseLessonLearnRepository extends JpaRepository<CourseLessonLearn,Long> {
    
}
