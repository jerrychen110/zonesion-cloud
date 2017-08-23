package com.zonesion.cloud.repository;

import com.zonesion.cloud.domain.Course;
import org.springframework.stereotype.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.PagingAndSortingRepository;


/**
 * Spring Data JPA repository for the Course entity.
 */
@Repository
public interface CourseRepository extends PagingAndSortingRepository<Course, Long>, JpaSpecificationExecutor<Course> {
    
	//根据课程id查询课程表
	@Query(value="SELECT c.*, ch.*, cl.* FROM t_course c LEFT JOIN t_chapter ch ON c.id = ch.course_id LEFT JOIN t_course_lesson cl ON cl.chapter_id = ch.id WHERE c.id = ?1",nativeQuery = true)
	List<Course> findByCourseId(Long courseId);
	
	//根据用户id查询课程表
	@Query(value="SELECT c.* FROM t_course c LEFT JOIN t_user u ON c.user_id = u.id WHERE c.user_id = ?1",nativeQuery = true)
	List<Course> findByUserId(Long userId);
	
	
	
}
