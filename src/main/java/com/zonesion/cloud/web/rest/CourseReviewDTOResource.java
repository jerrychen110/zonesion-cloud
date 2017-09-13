package com.zonesion.cloud.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.zonesion.cloud.service.CourseReviewDTOService;
import com.zonesion.cloud.service.dto.CourseReviewDTO;
import com.zonesion.cloud.service.dto.ext.CourseReviewExtDTO;
import com.zonesion.cloud.web.rest.util.HeaderUtil;

import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Course.
 */
@RestController
@RequestMapping("/api")
public class CourseReviewDTOResource {

    private final Logger log = LoggerFactory.getLogger(CourseReviewDTOResource.class);

    private static final String ENTITY_NAME = "courseReview";

    private final CourseReviewDTOService courseReviewDTOService;

    public CourseReviewDTOResource(CourseReviewDTOService courseReviewDTOService) {
        this.courseReviewDTOService = courseReviewDTOService;
    }

    @GetMapping("/coursesReview/{id}")
    @Timed
    public ResponseEntity<List<CourseReviewExtDTO>> getCourseReview(@PathVariable Long id) {
        log.debug("REST request to get a page of Courses review");
        List<CourseReviewExtDTO> courseReviewExtDTO = courseReviewDTOService.findCourseReview(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(courseReviewExtDTO));
    }
    
    @PostMapping("/coursesReview/")
    @Timed
    public CourseReviewDTO saveCourseReview(CourseReviewDTO courseReviewDTO) {
        log.debug("REST request to get a page of Courses review");
        return courseReviewDTOService.saveCourseReview(courseReviewDTO);
        
    }
    
    @PutMapping("/coursesReview/")
    @Timed
    public ResponseEntity<Void> updateCourseReview(CourseReviewDTO courseReviewDTO) {
        log.debug("REST request to update a page of Courses review");
        courseReviewDTOService.updateCourseReview(courseReviewDTO);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, courseReviewDTO.getId().toString())).build();
    } 
    
    @DeleteMapping("/coursesReview/{id}")
    @Timed
    public ResponseEntity<Void> deleteCourseReview(@PathVariable Long id) {
        log.debug("REST request to delete Course : {}", id);
        courseReviewDTOService.deleteCourseReview(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
    
}
