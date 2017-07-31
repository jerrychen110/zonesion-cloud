package com.zonesion.cloud.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.zonesion.cloud.domain.Major;
import com.zonesion.cloud.service.MajorService;
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
 * REST controller for managing Major.
 */
@RestController
@RequestMapping("/api")
public class MajorResource {

    private final Logger log = LoggerFactory.getLogger(MajorResource.class);

    private static final String ENTITY_NAME = "major";

    private final MajorService majorService;

    public MajorResource(MajorService majorService) {
        this.majorService = majorService;
    }

    /**
     * POST  /majors : Create a new major.
     *
     * @param major the major to create
     * @return the ResponseEntity with status 201 (Created) and with body the new major, or with status 400 (Bad Request) if the major has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/majors")
    @Timed
    public ResponseEntity<Major> createMajor(@Valid @RequestBody Major major) throws URISyntaxException {
        log.debug("REST request to save Major : {}", major);
        if (major.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new major cannot already have an ID")).body(null);
        }
        Major result = majorService.save(major);
        return ResponseEntity.created(new URI("/api/majors/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /majors : Updates an existing major.
     *
     * @param major the major to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated major,
     * or with status 400 (Bad Request) if the major is not valid,
     * or with status 500 (Internal Server Error) if the major couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/majors")
    @Timed
    public ResponseEntity<Major> updateMajor(@Valid @RequestBody Major major) throws URISyntaxException {
        log.debug("REST request to update Major : {}", major);
        if (major.getId() == null) {
            return createMajor(major);
        }
        Major result = majorService.save(major);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, major.getId().toString()))
            .body(result);
    }

    /**
     * GET  /majors : get all the majors.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of majors in body
     */
    @GetMapping("/majors")
    @Timed
    public ResponseEntity<List<Major>> getAllMajors(@ApiParam Pageable pageable) {
        log.debug("REST request to get a page of Majors");
        Page<Major> page = majorService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/majors");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /majors/:id : get the "id" major.
     *
     * @param id the id of the major to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the major, or with status 404 (Not Found)
     */
    @GetMapping("/majors/{id}")
    @Timed
    public ResponseEntity<Major> getMajor(@PathVariable Long id) {
        log.debug("REST request to get Major : {}", id);
        Major major = majorService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(major));
    }

    /**
     * DELETE  /majors/:id : delete the "id" major.
     *
     * @param id the id of the major to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/majors/{id}")
    @Timed
    public ResponseEntity<Void> deleteMajor(@PathVariable Long id) {
        log.debug("REST request to delete Major : {}", id);
        majorService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
