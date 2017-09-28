package com.zonesion.cloud.repository;

import com.zonesion.cloud.domain.CourseFavorite;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the CourseFavorite entity.
 */
@Repository
public interface CourseFavoriteRepository extends JpaRepository<CourseFavorite,Long> {
    
	@Query(value="select count(distinct user_id) from t_course_favorite where course_id = ?1",nativeQuery = true)
	int getFavoriteNumByCourseId(long courseId);
	
	@Query(value="select count(user_id) from t_course_favorite where course_id = ?1 and user_id= ?2",nativeQuery = true)
	int getFavoriteNumByCourseIdAndUserId(long courseId, long userId);
	
	CourseFavorite findOneByCourse_idAndUserId(Long courseId, Long userId);
	
}
