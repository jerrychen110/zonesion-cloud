package com.zonesion.cloud.repository;

import com.zonesion.cloud.domain.CourseLessonLearn;

import org.springframework.stereotype.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.*;

/**
 * Spring Data JPA repository for the CourseLessonLearn entity.
 */
@Repository
public interface CourseLessonLearnRepository extends JpaRepository<CourseLessonLearn,Long> {
    
	@Query(value="select count(distinct user_id) from t_course_lesson_learn where course_id = ?1",nativeQuery = true)
	int getLearnedNumByCourseId(long courseId);
	
	@Query(value="select count(user_id) from t_course_lesson_learn where course_id = ?1 and user_id = ?2",nativeQuery = true)
	int getLearnedNumByCourseIdAndUserId(long courseId, long userId);
	
	List<CourseLessonLearn> findAllByCourseIdAndUserIdOrderByCreatedDateDesc(long courseId, long userId);
	
    CourseLessonLearn findOneByUserIdAndCourseLesson_id(Long userId, Long lessonId);

}
