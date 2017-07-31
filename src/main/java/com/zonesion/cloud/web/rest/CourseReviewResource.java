package com.zonesion.cloud.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.zonesion.cloud.domain.CourseReview;
import com.zonesion.cloud.service.CourseReviewService;
import com.zonesion.cloud.web.rest.util.HeaderUtil;
import com.zonesion.cloud.web.rest.util.PaginationUtil;
import io.swagger.annotations.ApiParam;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing CourseReview.
 */
@RestController
@RequestMapping("/api")
public class CourseReviewResource {

    private final Logger log = LoggerFactory.getLogger(CourseReviewResource.class);

    private static final String ENTITY_NAME = "courseReview";

    private final CourseReviewService courseReviewService;

    public CourseReviewResource(CourseReviewService courseReviewService) {
        this.courseReviewService = courseReviewService;
    }

    /**
     * POST  /course-reviews : Create a new courseReview.
     *
     * @param courseReview the courseReview to create
     * @return the ResponseEntity with status 201 (Created) and with body the new courseReview, or with status 400 (Bad Request) if the courseReview has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/course-reviews")
    @Timed
    public ResponseEntity<CourseReview> createCourseReview(@Valid @RequestBody CourseReview courseReview) throws URISyntaxException {
        log.debug("REST request to save CourseReview : {}", courseReview);
        if (courseReview.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new courseReview cannot already have an ID")).body(null);
        }
        CourseReview result = courseReviewService.save(courseReview);
        return ResponseEntity.created(new URI("/api/course-reviews/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /course-reviews : Updates an existing courseReview.
     *
     * @param courseReview the courseReview to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated courseReview,
     * or with status 400 (Bad Request) if the courseReview is not valid,
     * or with status 500 (Internal Server Error) if the courseReview couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/course-reviews")
    @Timed
    public ResponseEntity<CourseReview> updateCourseReview(@Valid @RequestBody CourseReview courseReview) throws URISyntaxException {
        log.debug("REST request to update CourseReview : {}", courseReview);
        if (courseReview.getId() == null) {
            return createCourseReview(courseReview);
        }
        CourseReview result = courseReviewService.save(courseReview);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, courseReview.getId().toString()))
            .body(result);
    }

    /**
     * GET  /course-reviews : get all the courseReviews.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of courseReviews in body
     */
    @GetMapping("/course-reviews")
    @Timed
    public ResponseEntity<List<CourseReview>> getAllCourseReviews(@ApiParam Pageable pageable) {
        log.debug("REST request to get a page of CourseReviews");
        Page<CourseReview> page = courseReviewService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/course-reviews");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /course-reviews/:id : get the "id" courseReview.
     *
     * @param id the id of the courseReview to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the courseReview, or with status 404 (Not Found)
     */
    @GetMapping("/course-reviews/{id}")
    @Timed
    public ResponseEntity<CourseReview> getCourseReview(@PathVariable Long id) {
        log.debug("REST request to get CourseReview : {}", id);
        CourseReview courseReview = courseReviewService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(courseReview));
    }

    /**
     * DELETE  /course-reviews/:id : delete the "id" courseReview.
     *
     * @param id the id of the courseReview to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/course-reviews/{id}")
    @Timed
    public ResponseEntity<Void> deleteCourseReview(@PathVariable Long id) {
        log.debug("REST request to delete CourseReview : {}", id);
        courseReviewService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
