package com.zonesion.cloud.repository;

import com.zonesion.cloud.domain.Course;
import org.springframework.stereotype.Repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
	
	//根据课程类型查询课程列表
	Page<Course> findAllByCourseType(String courseType, Pageable pageable);
	
	//根据课程来源查询课程列表
	Page<Course> findAllByCourseSource(String courseSource, Pageable pageable);
	
	//根据课程来源及课程类型查询课程列表
	Page<Course> findAllByCourseSourceAndCourseType(String courseSource, String courseType, Pageable pageable);
	
	List<Course> findAll();
	
	void saveAndFlush(Course course);
	
}
