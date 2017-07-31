package com.zonesion.cloud.repository;

import com.zonesion.cloud.domain.CourseLesson;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the CourseLesson entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CourseLessonRepository extends JpaRepository<CourseLesson,Long> {
    
}
