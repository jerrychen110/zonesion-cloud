package com.zonesion.cloud.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.zonesion.cloud.domain.CourseLessonLearn;
import com.zonesion.cloud.service.CourseLessonLearnService;
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
 * REST controller for managing CourseLessonLearn.
 */
@RestController
@RequestMapping("/api")
public class CourseLessonLearnResource {

    private final Logger log = LoggerFactory.getLogger(CourseLessonLearnResource.class);

    private static final String ENTITY_NAME = "courseLessonLearn";

    private final CourseLessonLearnService courseLessonLearnService;

    public CourseLessonLearnResource(CourseLessonLearnService courseLessonLearnService) {
        this.courseLessonLearnService = courseLessonLearnService;
    }

    /**
     * POST  /course-lesson-learns : Create a new courseLessonLearn.
     *
     * @param courseLessonLearn the courseLessonLearn to create
     * @return the ResponseEntity with status 201 (Created) and with body the new courseLessonLearn, or with status 400 (Bad Request) if the courseLessonLearn has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/course-lesson-learns")
    @Timed
    public ResponseEntity<CourseLessonLearn> createCourseLessonLearn(@Valid @RequestBody CourseLessonLearn courseLessonLearn) throws URISyntaxException {
        log.debug("REST request to save CourseLessonLearn : {}", courseLessonLearn);
        if (courseLessonLearn.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new courseLessonLearn cannot already have an ID")).body(null);
        }
        CourseLessonLearn result = courseLessonLearnService.save(courseLessonLearn);
        return ResponseEntity.created(new URI("/api/course-lesson-learns/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /course-lesson-learns : Updates an existing courseLessonLearn.
     *
     * @param courseLessonLearn the courseLessonLearn to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated courseLessonLearn,
     * or with status 400 (Bad Request) if the courseLessonLearn is not valid,
     * or with status 500 (Internal Server Error) if the courseLessonLearn couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/course-lesson-learns")
    @Timed
    public ResponseEntity<CourseLessonLearn> updateCourseLessonLearn(@Valid @RequestBody CourseLessonLearn courseLessonLearn) throws URISyntaxException {
        log.debug("REST request to update CourseLessonLearn : {}", courseLessonLearn);
        if (courseLessonLearn.getId() == null) {
            return createCourseLessonLearn(courseLessonLearn);
        }
        CourseLessonLearn result = courseLessonLearnService.save(courseLessonLearn);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, courseLessonLearn.getId().toString()))
            .body(result);
    }

    /**
     * GET  /course-lesson-learns : get all the courseLessonLearns.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of courseLessonLearns in body
     */
    @GetMapping("/course-lesson-learns")
    @Timed
    public ResponseEntity<List<CourseLessonLearn>> getAllCourseLessonLearns(@ApiParam Pageable pageable) {
        log.debug("REST request to get a page of CourseLessonLearns");
        Page<CourseLessonLearn> page = courseLessonLearnService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/course-lesson-learns");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /course-lesson-learns/:id : get the "id" courseLessonLearn.
     *
     * @param id the id of the courseLessonLearn to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the courseLessonLearn, or with status 404 (Not Found)
     */
    @GetMapping("/course-lesson-learns/{id}")
    @Timed
    public ResponseEntity<CourseLessonLearn> getCourseLessonLearn(@PathVariable Long id) {
        log.debug("REST request to get CourseLessonLearn : {}", id);
        CourseLessonLearn courseLessonLearn = courseLessonLearnService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(courseLessonLearn));
    }

    /**
     * DELETE  /course-lesson-learns/:id : delete the "id" courseLessonLearn.
     *
     * @param id the id of the courseLessonLearn to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/course-lesson-learns/{id}")
    @Timed
    public ResponseEntity<Void> deleteCourseLessonLearn(@PathVariable Long id) {
        log.debug("REST request to delete CourseLessonLearn : {}", id);
        courseLessonLearnService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
