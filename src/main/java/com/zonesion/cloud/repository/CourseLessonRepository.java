package com.zonesion.cloud.repository;

import com.zonesion.cloud.domain.CourseLesson;
import org.springframework.stereotype.Repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the CourseLesson entity.
 */
@Repository
public interface CourseLessonRepository extends JpaRepository<CourseLesson,Long>, JpaSpecificationExecutor<CourseLesson> {
    
	@Query(value="SELECT cl.*, ch.*, c.* FROM t_course_lesson cl INNER JOIN t_chapter ch ON cl.chapter_id = ch.id INNER JOIN t_course c ON c.id = ch.course_id WHERE c.id = ?1",nativeQuery = true)
	List<CourseLesson> findAllCourseLesson(Long id);
}
