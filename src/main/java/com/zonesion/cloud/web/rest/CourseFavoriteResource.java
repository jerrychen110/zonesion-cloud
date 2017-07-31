package com.zonesion.cloud.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.zonesion.cloud.domain.CourseFavorite;
import com.zonesion.cloud.service.CourseFavoriteService;
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
 * REST controller for managing CourseFavorite.
 */
@RestController
@RequestMapping("/api")
public class CourseFavoriteResource {

    private final Logger log = LoggerFactory.getLogger(CourseFavoriteResource.class);

    private static final String ENTITY_NAME = "courseFavorite";

    private final CourseFavoriteService courseFavoriteService;

    public CourseFavoriteResource(CourseFavoriteService courseFavoriteService) {
        this.courseFavoriteService = courseFavoriteService;
    }

    /**
     * POST  /course-favorites : Create a new courseFavorite.
     *
     * @param courseFavorite the courseFavorite to create
     * @return the ResponseEntity with status 201 (Created) and with body the new courseFavorite, or with status 400 (Bad Request) if the courseFavorite has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/course-favorites")
    @Timed
    public ResponseEntity<CourseFavorite> createCourseFavorite(@Valid @RequestBody CourseFavorite courseFavorite) throws URISyntaxException {
        log.debug("REST request to save CourseFavorite : {}", courseFavorite);
        if (courseFavorite.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new courseFavorite cannot already have an ID")).body(null);
        }
        CourseFavorite result = courseFavoriteService.save(courseFavorite);
        return ResponseEntity.created(new URI("/api/course-favorites/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /course-favorites : Updates an existing courseFavorite.
     *
     * @param courseFavorite the courseFavorite to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated courseFavorite,
     * or with status 400 (Bad Request) if the courseFavorite is not valid,
     * or with status 500 (Internal Server Error) if the courseFavorite couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/course-favorites")
    @Timed
    public ResponseEntity<CourseFavorite> updateCourseFavorite(@Valid @RequestBody CourseFavorite courseFavorite) throws URISyntaxException {
        log.debug("REST request to update CourseFavorite : {}", courseFavorite);
        if (courseFavorite.getId() == null) {
            return createCourseFavorite(courseFavorite);
        }
        CourseFavorite result = courseFavoriteService.save(courseFavorite);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, courseFavorite.getId().toString()))
            .body(result);
    }

    /**
     * GET  /course-favorites : get all the courseFavorites.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of courseFavorites in body
     */
    @GetMapping("/course-favorites")
    @Timed
    public ResponseEntity<List<CourseFavorite>> getAllCourseFavorites(@ApiParam Pageable pageable) {
        log.debug("REST request to get a page of CourseFavorites");
        Page<CourseFavorite> page = courseFavoriteService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/course-favorites");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /course-favorites/:id : get the "id" courseFavorite.
     *
     * @param id the id of the courseFavorite to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the courseFavorite, or with status 404 (Not Found)
     */
    @GetMapping("/course-favorites/{id}")
    @Timed
    public ResponseEntity<CourseFavorite> getCourseFavorite(@PathVariable Long id) {
        log.debug("REST request to get CourseFavorite : {}", id);
        CourseFavorite courseFavorite = courseFavoriteService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(courseFavorite));
    }

    /**
     * DELETE  /course-favorites/:id : delete the "id" courseFavorite.
     *
     * @param id the id of the courseFavorite to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/course-favorites/{id}")
    @Timed
    public ResponseEntity<Void> deleteCourseFavorite(@PathVariable Long id) {
        log.debug("REST request to delete CourseFavorite : {}", id);
        courseFavoriteService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
