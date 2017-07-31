package com.zonesion.cloud.repository;

import com.zonesion.cloud.domain.CourseLessonNote;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the CourseLessonNote entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CourseLessonNoteRepository extends JpaRepository<CourseLessonNote,Long> {
    
}
