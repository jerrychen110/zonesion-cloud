package com.zonesion.cloud.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.zonesion.cloud.service.CourseReviewDTOService;
import com.zonesion.cloud.service.dto.CourseReviewDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<List<CourseReviewDTO>> getCourseReview(@PathVariable Long id) {
        log.debug("REST request to get a page of Courses review");
        List<CourseReviewDTO> courseReviewDTO = courseReviewDTOService.findCourseReview(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(courseReviewDTO));
    }
}
