package com.zonesion.cloud.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.zonesion.cloud.domain.CourseLessonAttachment;
import com.zonesion.cloud.service.CourseLessonAttachmentService;
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
 * REST controller for managing CourseLessonAttachment.
 */
@RestController
@RequestMapping("/api")
public class CourseLessonAttachmentResource {

    private final Logger log = LoggerFactory.getLogger(CourseLessonAttachmentResource.class);

    private static final String ENTITY_NAME = "courseLessonAttachment";

    private final CourseLessonAttachmentService courseLessonAttachmentService;

    public CourseLessonAttachmentResource(CourseLessonAttachmentService courseLessonAttachmentService) {
        this.courseLessonAttachmentService = courseLessonAttachmentService;
    }

    /**
     * POST  /course-lesson-attachments : Create a new courseLessonAttachment.
     *
     * @param courseLessonAttachment the courseLessonAttachment to create
     * @return the ResponseEntity with status 201 (Created) and with body the new courseLessonAttachment, or with status 400 (Bad Request) if the courseLessonAttachment has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/course-lesson-attachments")
    @Timed
    public ResponseEntity<CourseLessonAttachment> createCourseLessonAttachment(@Valid @RequestBody CourseLessonAttachment courseLessonAttachment) throws URISyntaxException {
        log.debug("REST request to save CourseLessonAttachment : {}", courseLessonAttachment);
        if (courseLessonAttachment.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new courseLessonAttachment cannot already have an ID")).body(null);
        }
        CourseLessonAttachment result = courseLessonAttachmentService.save(courseLessonAttachment);
        return ResponseEntity.created(new URI("/api/course-lesson-attachments/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /course-lesson-attachments : Updates an existing courseLessonAttachment.
     *
     * @param courseLessonAttachment the courseLessonAttachment to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated courseLessonAttachment,
     * or with status 400 (Bad Request) if the courseLessonAttachment is not valid,
     * or with status 500 (Internal Server Error) if the courseLessonAttachment couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/course-lesson-attachments")
    @Timed
    public ResponseEntity<CourseLessonAttachment> updateCourseLessonAttachment(@Valid @RequestBody CourseLessonAttachment courseLessonAttachment) throws URISyntaxException {
        log.debug("REST request to update CourseLessonAttachment : {}", courseLessonAttachment);
        if (courseLessonAttachment.getId() == null) {
            return createCourseLessonAttachment(courseLessonAttachment);
        }
        CourseLessonAttachment result = courseLessonAttachmentService.save(courseLessonAttachment);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, courseLessonAttachment.getId().toString()))
            .body(result);
    }

    /**
     * GET  /course-lesson-attachments : get all the courseLessonAttachments.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of courseLessonAttachments in body
     */
    @GetMapping("/course-lesson-attachments")
    @Timed
    public ResponseEntity<List<CourseLessonAttachment>> getAllCourseLessonAttachments(@ApiParam Pageable pageable) {
        log.debug("REST request to get a page of CourseLessonAttachments");
        Page<CourseLessonAttachment> page = courseLessonAttachmentService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/course-lesson-attachments");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /course-lesson-attachments/:id : get the "id" courseLessonAttachment.
     *
     * @param id the id of the courseLessonAttachment to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the courseLessonAttachment, or with status 404 (Not Found)
     */
    @GetMapping("/course-lesson-attachments/{id}")
    @Timed
    public ResponseEntity<CourseLessonAttachment> getCourseLessonAttachment(@PathVariable Long id) {
        log.debug("REST request to get CourseLessonAttachment : {}", id);
        CourseLessonAttachment courseLessonAttachment = courseLessonAttachmentService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(courseLessonAttachment));
    }

    /**
     * DELETE  /course-lesson-attachments/:id : delete the "id" courseLessonAttachment.
     *
     * @param id the id of the courseLessonAttachment to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/course-lesson-attachments/{id}")
    @Timed
    public ResponseEntity<Void> deleteCourseLessonAttachment(@PathVariable Long id) {
        log.debug("REST request to delete CourseLessonAttachment : {}", id);
        courseLessonAttachmentService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
