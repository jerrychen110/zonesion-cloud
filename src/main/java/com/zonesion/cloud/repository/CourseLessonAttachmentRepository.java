package com.zonesion.cloud.repository;

import com.zonesion.cloud.domain.CourseLessonAttachment;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the CourseLessonAttachment entity.
 */
@Repository
public interface CourseLessonAttachmentRepository extends JpaRepository<CourseLessonAttachment,Long> {
    
	Page<CourseLessonAttachment> findAllByTargetTypeAndTargetIdOrderByCreatedDateDesc(String targetType, long targetId, Pageable pageable);
}
