package com.zonesion.cloud.repository;

import com.zonesion.cloud.domain.CourseReview;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the CourseReview entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CourseReviewRepository extends JpaRepository<CourseReview,Long> {
    
}
