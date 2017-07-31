package com.zonesion.cloud.service;

import com.zonesion.cloud.domain.CourseReview;
import com.zonesion.cloud.repository.CourseReviewRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing CourseReview.
 */
@Service
@Transactional
public class CourseReviewService {

    private final Logger log = LoggerFactory.getLogger(CourseReviewService.class);

    private final CourseReviewRepository courseReviewRepository;

    public CourseReviewService(CourseReviewRepository courseReviewRepository) {
        this.courseReviewRepository = courseReviewRepository;
    }

    /**
     * Save a courseReview.
     *
     * @param courseReview the entity to save
     * @return the persisted entity
     */
    public CourseReview save(CourseReview courseReview) {
        log.debug("Request to save CourseReview : {}", courseReview);
        return courseReviewRepository.save(courseReview);
    }

    /**
     *  Get all the courseReviews.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<CourseReview> findAll(Pageable pageable) {
        log.debug("Request to get all CourseReviews");
        return courseReviewRepository.findAll(pageable);
    }

    /**
     *  Get one courseReview by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true)
    public CourseReview findOne(Long id) {
        log.debug("Request to get CourseReview : {}", id);
        return courseReviewRepository.findOne(id);
    }

    /**
     *  Delete the  courseReview by id.
     *
     *  @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete CourseReview : {}", id);
        courseReviewRepository.delete(id);
    }
}
