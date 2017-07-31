package com.zonesion.cloud.repository;

import com.zonesion.cloud.domain.CourseLessonNoteLike;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the CourseLessonNoteLike entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CourseLessonNoteLikeRepository extends JpaRepository<CourseLessonNoteLike,Long> {
    
}
