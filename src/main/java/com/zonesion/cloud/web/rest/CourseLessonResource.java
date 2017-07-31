package com.zonesion.cloud.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.zonesion.cloud.domain.CourseLesson;
import com.zonesion.cloud.service.CourseLessonService;
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
 * REST controller for managing CourseLesson.
 */
@RestController
@RequestMapping("/api")
public class CourseLessonResource {

    private final Logger log = LoggerFactory.getLogger(CourseLessonResource.class);

    private static final String ENTITY_NAME = "courseLesson";

    private final CourseLessonService courseLessonService;

    public CourseLessonResource(CourseLessonService courseLessonService) {
        this.courseLessonService = courseLessonService;
    }

    /**
     * POST  /course-lessons : Create a new courseLesson.
     *
     * @param courseLesson the courseLesson to create
     * @return the ResponseEntity with status 201 (Created) and with body the new courseLesson, or with status 400 (Bad Request) if the courseLesson has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/course-lessons")
    @Timed
    public ResponseEntity<CourseLesson> createCourseLesson(@Valid @RequestBody CourseLesson courseLesson) throws URISyntaxException {
        log.debug("REST request to save CourseLesson : {}", courseLesson);
        if (courseLesson.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new courseLesson cannot already have an ID")).body(null);
        }
        CourseLesson result = courseLessonService.save(courseLesson);
        return ResponseEntity.created(new URI("/api/course-lessons/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /course-lessons : Updates an existing courseLesson.
     *
     * @param courseLesson the courseLesson to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated courseLesson,
     * or with status 400 (Bad Request) if the courseLesson is not valid,
     * or with status 500 (Internal Server Error) if the courseLesson couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/course-lessons")
    @Timed
    public ResponseEntity<CourseLesson> updateCourseLesson(@Valid @RequestBody CourseLesson courseLesson) throws URISyntaxException {
        log.debug("REST request to update CourseLesson : {}", courseLesson);
        if (courseLesson.getId() == null) {
            return createCourseLesson(courseLesson);
        }
        CourseLesson result = courseLessonService.save(courseLesson);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, courseLesson.getId().toString()))
            .body(result);
    }

    /**
     * GET  /course-lessons : get all the courseLessons.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of courseLessons in body
     */
    @GetMapping("/course-lessons")
    @Timed
    public ResponseEntity<List<CourseLesson>> getAllCourseLessons(@ApiParam Pageable pageable) {
        log.debug("REST request to get a page of CourseLessons");
        Page<CourseLesson> page = courseLessonService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/course-lessons");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /course-lessons/:id : get the "id" courseLesson.
     *
     * @param id the id of the courseLesson to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the courseLesson, or with status 404 (Not Found)
     */
    @GetMapping("/course-lessons/{id}")
    @Timed
    public ResponseEntity<CourseLesson> getCourseLesson(@PathVariable Long id) {
        log.debug("REST request to get CourseLesson : {}", id);
        CourseLesson courseLesson = courseLessonService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(courseLesson));
    }

    /**
     * DELETE  /course-lessons/:id : delete the "id" courseLesson.
     *
     * @param id the id of the courseLesson to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/course-lessons/{id}")
    @Timed
    public ResponseEntity<Void> deleteCourseLesson(@PathVariable Long id) {
        log.debug("REST request to delete CourseLesson : {}", id);
        courseLessonService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
