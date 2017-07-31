package com.zonesion.cloud.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.zonesion.cloud.domain.CourseLessonNote;
import com.zonesion.cloud.service.CourseLessonNoteService;
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
 * REST controller for managing CourseLessonNote.
 */
@RestController
@RequestMapping("/api")
public class CourseLessonNoteResource {

    private final Logger log = LoggerFactory.getLogger(CourseLessonNoteResource.class);

    private static final String ENTITY_NAME = "courseLessonNote";

    private final CourseLessonNoteService courseLessonNoteService;

    public CourseLessonNoteResource(CourseLessonNoteService courseLessonNoteService) {
        this.courseLessonNoteService = courseLessonNoteService;
    }

    /**
     * POST  /course-lesson-notes : Create a new courseLessonNote.
     *
     * @param courseLessonNote the courseLessonNote to create
     * @return the ResponseEntity with status 201 (Created) and with body the new courseLessonNote, or with status 400 (Bad Request) if the courseLessonNote has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/course-lesson-notes")
    @Timed
    public ResponseEntity<CourseLessonNote> createCourseLessonNote(@Valid @RequestBody CourseLessonNote courseLessonNote) throws URISyntaxException {
        log.debug("REST request to save CourseLessonNote : {}", courseLessonNote);
        if (courseLessonNote.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new courseLessonNote cannot already have an ID")).body(null);
        }
        CourseLessonNote result = courseLessonNoteService.save(courseLessonNote);
        return ResponseEntity.created(new URI("/api/course-lesson-notes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /course-lesson-notes : Updates an existing courseLessonNote.
     *
     * @param courseLessonNote the courseLessonNote to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated courseLessonNote,
     * or with status 400 (Bad Request) if the courseLessonNote is not valid,
     * or with status 500 (Internal Server Error) if the courseLessonNote couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/course-lesson-notes")
    @Timed
    public ResponseEntity<CourseLessonNote> updateCourseLessonNote(@Valid @RequestBody CourseLessonNote courseLessonNote) throws URISyntaxException {
        log.debug("REST request to update CourseLessonNote : {}", courseLessonNote);
        if (courseLessonNote.getId() == null) {
            return createCourseLessonNote(courseLessonNote);
        }
        CourseLessonNote result = courseLessonNoteService.save(courseLessonNote);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, courseLessonNote.getId().toString()))
            .body(result);
    }

    /**
     * GET  /course-lesson-notes : get all the courseLessonNotes.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of courseLessonNotes in body
     */
    @GetMapping("/course-lesson-notes")
    @Timed
    public ResponseEntity<List<CourseLessonNote>> getAllCourseLessonNotes(@ApiParam Pageable pageable) {
        log.debug("REST request to get a page of CourseLessonNotes");
        Page<CourseLessonNote> page = courseLessonNoteService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/course-lesson-notes");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /course-lesson-notes/:id : get the "id" courseLessonNote.
     *
     * @param id the id of the courseLessonNote to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the courseLessonNote, or with status 404 (Not Found)
     */
    @GetMapping("/course-lesson-notes/{id}")
    @Timed
    public ResponseEntity<CourseLessonNote> getCourseLessonNote(@PathVariable Long id) {
        log.debug("REST request to get CourseLessonNote : {}", id);
        CourseLessonNote courseLessonNote = courseLessonNoteService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(courseLessonNote));
    }

    /**
     * DELETE  /course-lesson-notes/:id : delete the "id" courseLessonNote.
     *
     * @param id the id of the courseLessonNote to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/course-lesson-notes/{id}")
    @Timed
    public ResponseEntity<Void> deleteCourseLessonNote(@PathVariable Long id) {
        log.debug("REST request to delete CourseLessonNote : {}", id);
        courseLessonNoteService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
