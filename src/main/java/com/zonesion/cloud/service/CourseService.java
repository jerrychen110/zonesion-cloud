package com.zonesion.cloud.service;

import com.zonesion.cloud.domain.Chapter;
import com.zonesion.cloud.domain.Course;
import com.zonesion.cloud.repository.CourseRepository;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing Course.
 */
@Service
@Transactional
public class CourseService {

    private final Logger log = LoggerFactory.getLogger(CourseService.class);

    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    /**
     * Save a course.
     *
     * @param course the entity to save
     * @return the persisted entity
     */
    public Course save(Course course) {
        log.debug("Request to save Course : {}", course);
        return courseRepository.save(course);
    }

    /**
     *  Get all the courses.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<Course> findAll(Pageable pageable) {
        log.debug("Request to get all Courses");
        return courseRepository.findAll(pageable);
    }

    /**
     *  Get one course by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true)
    public Course findOne(Long id) {
        log.debug("Request to get Course : {}", id);
        return courseRepository.findOne(id);
    }

    /**
     *  Delete the  course by id.
     *
     *  @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Course : {}", id);
        courseRepository.delete(id);
    }
    
    public List<Course> findByCourseId(Long courseId) {
		return courseRepository.findByCourseId(courseId);
	}

	public List<Course> findByUserId(Long userId) {
		return courseRepository.findByUserId(userId);
	}

	public Page<Course> findNewestCourseByUserId(Pageable pageable) {
		return courseRepository.findNewestCourseByUserId(pageable);
	}

	public Page<Course> findHotCourseByUserId(Pageable pageable) {
		return courseRepository.findHotCourseByUserId(pageable);
	}

	public Page<Course> findRecommendedCourseByUserId(Pageable pageable) {
		return courseRepository.findRecommendedCourseByUserId(pageable);
	}
	
    /*public List<Course> findAllCourse(Long id){
		List<Course> courseList = courseRepository.findAll(new Specification<Course>() {

			@Override
			public Predicate toPredicate(Root<Course> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				// TODO Auto-generated method stub
				Join<Course, Chapter> chapterJoin = root.join("chapters", JoinType.LEFT);
                return cb.equal(chapterJoin.get("courseId"), id);
			}
			
		});
		return courseList;
		
	}*/
}
