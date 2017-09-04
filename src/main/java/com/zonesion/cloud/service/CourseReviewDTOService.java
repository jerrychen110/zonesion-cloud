package com.zonesion.cloud.service;

import com.zonesion.cloud.repository.CourseReviewDTORepository;
import com.zonesion.cloud.service.dto.CourseReviewDTO;

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

    public List<CourseReviewDTO> findCourseReview(Long courseId) {
        log.debug("Request to get all Courses notes ");
        return courseReviewDTORepository.findCourseReview(courseId);
    }
}
