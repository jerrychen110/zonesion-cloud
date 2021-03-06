package com.zonesion.cloud.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.zonesion.cloud.domain.Course;
import com.zonesion.cloud.service.CourseDTOService;
import com.zonesion.cloud.service.CourseService;
import com.zonesion.cloud.service.dto.CourseDTO;
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
 * REST controller for managing Course.
 */
@RestController
@RequestMapping("/api")
public class CourseDTOResource {

    private final Logger log = LoggerFactory.getLogger(CourseDTOResource.class);

    private static final String ENTITY_NAME = "course";

    private final CourseDTOService courseJDBCService;

    public CourseDTOResource(CourseDTOService courseJDBCService) {
        this.courseJDBCService = courseJDBCService;
    }

    @GetMapping("/coursesdto/{id}")
    @Timed
    public ResponseEntity<List<CourseDTO>> getCoursesChapters(@PathVariable Long id) {
        log.debug("REST request to get a page of Courses");
        List<CourseDTO> course = courseJDBCService.findCourseChapters(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(course));
    }
}
