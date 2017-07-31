package com.zonesion.cloud.repository;

import com.zonesion.cloud.domain.CourseLessonAttachment;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the CourseLessonAttachment entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CourseLessonAttachmentRepository extends JpaRepository<CourseLessonAttachment,Long> {
    
}
