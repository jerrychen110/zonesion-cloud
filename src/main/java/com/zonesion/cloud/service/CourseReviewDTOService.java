package com.zonesion.cloud.service;

import com.zonesion.cloud.repository.CourseReviewDTORepository;
import com.zonesion.cloud.service.dto.CourseReviewDTO;
import com.zonesion.cloud.service.dto.ext.CourseReviewExtDTO;

import java.time.Instant;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing CourseLessonNote.
 */
@Service
@Transactional
public class CourseReviewDTOService {

    private final Logger log = LoggerFactory.getLogger(CourseReviewDTOService.class);

    private final CourseReviewDTORepository courseReviewDTORepository;

    public CourseReviewDTOService(CourseReviewDTORepository courseReviewDTORepository) {
        this.courseReviewDTORepository = courseReviewDTORepository;
    }

    public List<CourseReviewExtDTO> findCourseReview(Long courseId) {
        log.debug("Request to get all Courses reviews ");
        return courseReviewDTORepository.findCourseReview(courseId);
    }
    
    public CourseReviewDTO saveCourseReview(CourseReviewDTO courseReviewDTO){
    	log.debug("Request to save all Courses reviews ");
    	return courseReviewDTORepository.saveCourseReview(courseReviewDTO);  	
    }
    
    public void updateCourseReview(CourseReviewDTO courseReviewDTO) {
        log.debug("Request to update all Courses reviews ");
        courseReviewDTORepository.updateCourseReview(courseReviewDTO);
    }
    
    public void deleteCourseReview(Long id) {
        log.debug("Request to delete Courses reviews ");
        courseReviewDTORepository.deleteCourseReview(id);
    }
}
