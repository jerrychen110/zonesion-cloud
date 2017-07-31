package com.zonesion.cloud.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.zonesion.cloud.domain.CourseLessonNoteLike;
import com.zonesion.cloud.service.CourseLessonNoteLikeService;
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
 * REST controller for managing CourseLessonNoteLike.
 */
@RestController
@RequestMapping("/api")
public class CourseLessonNoteLikeResource {

    private final Logger log = LoggerFactory.getLogger(CourseLessonNoteLikeResource.class);

    private static final String ENTITY_NAME = "courseLessonNoteLike";

    private final CourseLessonNoteLikeService courseLessonNoteLikeService;

    public CourseLessonNoteLikeResource(CourseLessonNoteLikeService courseLessonNoteLikeService) {
        this.courseLessonNoteLikeService = courseLessonNoteLikeService;
    }

    /**
     * POST  /course-lesson-note-likes : Create a new courseLessonNoteLike.
     *
     * @param courseLessonNoteLike the courseLessonNoteLike to create
     * @return the ResponseEntity with status 201 (Created) and with body the new courseLessonNoteLike, or with status 400 (Bad Request) if the courseLessonNoteLike has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/course-lesson-note-likes")
    @Timed
    public ResponseEntity<CourseLessonNoteLike> createCourseLessonNoteLike(@Valid @RequestBody CourseLessonNoteLike courseLessonNoteLike) throws URISyntaxException {
        log.debug("REST request to save CourseLessonNoteLike : {}", courseLessonNoteLike);
        if (courseLessonNoteLike.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new courseLessonNoteLike cannot already have an ID")).body(null);
        }
        CourseLessonNoteLike result = courseLessonNoteLikeService.save(courseLessonNoteLike);
        return ResponseEntity.created(new URI("/api/course-lesson-note-likes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /course-lesson-note-likes : Updates an existing courseLessonNoteLike.
     *
     * @param courseLessonNoteLike the courseLessonNoteLike to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated courseLessonNoteLike,
     * or with status 400 (Bad Request) if the courseLessonNoteLike is not valid,
     * or with status 500 (Internal Server Error) if the courseLessonNoteLike couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/course-lesson-note-likes")
    @Timed
    public ResponseEntity<CourseLessonNoteLike> updateCourseLessonNoteLike(@Valid @RequestBody CourseLessonNoteLike courseLessonNoteLike) throws URISyntaxException {
        log.debug("REST request to update CourseLessonNoteLike : {}", courseLessonNoteLike);
        if (courseLessonNoteLike.getId() == null) {
            return createCourseLessonNoteLike(courseLessonNoteLike);
        }
        CourseLessonNoteLike result = courseLessonNoteLikeService.save(courseLessonNoteLike);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, courseLessonNoteLike.getId().toString()))
            .body(result);
    }

    /**
     * GET  /course-lesson-note-likes : get all the courseLessonNoteLikes.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of courseLessonNoteLikes in body
     */
    @GetMapping("/course-lesson-note-likes")
    @Timed
    public ResponseEntity<List<CourseLessonNoteLike>> getAllCourseLessonNoteLikes(@ApiParam Pageable pageable) {
        log.debug("REST request to get a page of CourseLessonNoteLikes");
        Page<CourseLessonNoteLike> page = courseLessonNoteLikeService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/course-lesson-note-likes");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /course-lesson-note-likes/:id : get the "id" courseLessonNoteLike.
     *
     * @param id the id of the courseLessonNoteLike to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the courseLessonNoteLike, or with status 404 (Not Found)
     */
    @GetMapping("/course-lesson-note-likes/{id}")
    @Timed
    public ResponseEntity<CourseLessonNoteLike> getCourseLessonNoteLike(@PathVariable Long id) {
        log.debug("REST request to get CourseLessonNoteLike : {}", id);
        CourseLessonNoteLike courseLessonNoteLike = courseLessonNoteLikeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(courseLessonNoteLike));
    }

    /**
     * DELETE  /course-lesson-note-likes/:id : delete the "id" courseLessonNoteLike.
     *
     * @param id the id of the courseLessonNoteLike to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/course-lesson-note-likes/{id}")
    @Timed
    public ResponseEntity<Void> deleteCourseLessonNoteLike(@PathVariable Long id) {
        log.debug("REST request to delete CourseLessonNoteLike : {}", id);
        courseLessonNoteLikeService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
