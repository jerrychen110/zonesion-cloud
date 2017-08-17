package com.zonesion.cloud.repository;

import com.zonesion.cloud.domain.Course;
import com.zonesion.cloud.domain.CourseReview;

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
	@Query(value="SELECT c.* FROM t_course c WHERE c.id = ?1",nativeQuery = true)
	List<Course> findByCourseId(Long courseId);
	
	//根据用户id查询课程表
	@Query(value="SELECT c.* FROM t_course c LEFT JOIN t_user u ON c.user_id = u.id WHERE c.user_id = ?1",nativeQuery = true)
	List<Course> findByUserId(Long userId);
	
	//查询最新课程
	@Query(value="SELECT c.*, count(cf.course_id) course_favoriteid, count(cr.id) course_reviewid FROM t_course c LEFT JOIN t_course_favorite cf ON cf.course_id = c.id LEFT JOIN t_course_review cr ON cr.course_id = c.id GROUP BY c.id, cf.course_id, cr.user_id ORDER BY last_modified_date DESC \n#pageable\n",
			countQuery = "SELECT count(*) FROM t_course",nativeQuery = true)
	Page<Course> findNewestCourseByUserId(Pageable pageable);
	
	//查询最热课程
	@Query(value="SELECT c.*, count(cf.course_id) course_favoriteid, count(cr.id) course_reviewid FROM t_course c LEFT JOIN t_course_favorite cf ON cf.course_id = c.id LEFT JOIN t_course_review cr ON cr.course_id = c.id GROUP BY c.id, cf.course_id, cr.user_id ORDER BY course_favoriteid DESC \n#pageable\n",
			countQuery = "SELECT count(*) FROM t_course",nativeQuery = true)
	Page<Course> findHotCourseByUserId(Pageable pageable);
	
	
	//查询推荐课程
	@Query(value="SELECT c.*, count(cf.course_id) course_favoriteid, count(cr.id) course_reviewid FROM t_course c LEFT JOIN t_course_favorite cf ON cf.course_id = c.id LEFT JOIN t_course_review cr ON cr.course_id = c.id WHERE c.recommended='是' GROUP BY c.id, cf.id, cr.id ORDER BY recommended_sort DESC \n#pageable\n",
			countQuery = "SELECT count(*) FROM t_course",nativeQuery = true)
	Page<Course> findRecommendedCourseByUserId(Pageable pageable);
	
	
	
	//查询课程留言数
	@Query(value="SELECT count(cr.id) FROM t_course c LEFT JOIN t_course_review cr on cr.course_id = c.id LEFT JOIN t_user u ON c.user_id = u.id WHERE c.id = ?1",nativeQuery = true)
	
	List<CourseReview> countCourseReview(Long courseId);
	
	
	//查询课程浏览人数
	@Query(value="SELECT count(cf.course_id) FROM t_course c LEFT JOIN t_course_favorite cf on cf.course_id = c.id LEFT JOIN t_user u ON c.user_id = u.id WHERE c.id = ?1",nativeQuery = true)
	
	List<Course> countCourseComment(Long courseId);
	
	//@Query(value="SELECT c.*, cl.*, ch.* FROM t_course c INNER JOIN t_chapter ch ON ch.course_id = c.id INNER JOIN t_course_lesson cl ON cl.chapter_id = ch.id WHERE c.id = ?1",nativeQuery = true)
	//Page<Course> findCourseInfoById(Specification<Course> spec, Pageable pageable);

	//Page<Course> findAllCourse(Specification<Course> specification);
	
}
