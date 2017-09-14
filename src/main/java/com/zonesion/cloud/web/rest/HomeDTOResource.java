package com.zonesion.cloud.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.zonesion.cloud.service.HomeDTOService;
import com.zonesion.cloud.service.dto.HomeDTO;
import com.zonesion.cloud.web.rest.util.Page;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Course.
 */
@RestController
@RequestMapping("/api")
public class HomeDTOResource {

    private final Logger log = LoggerFactory.getLogger(HomeDTOResource.class);

    private static final String COURSE_RECOMMENDED = "recommended";
    private static final String COURSE_NEWEST = "newest";
    private static final String COURSE_HOT = "hot";

    @Inject
    private HomeDTOService homeDTOService;
    
    /**
     * 首页课程按分类展示
     * @param courseQueryType
     * @return
     */
    @GetMapping("/home/course-list")
    @Timed
    public ResponseEntity<List<HomeDTO>> getHomeCourseList(@RequestParam String courseQueryType) {
        log.debug("REST request to get a page of Courses");
        List<HomeDTO> course = new ArrayList<>();
        if(COURSE_RECOMMENDED.equals(courseQueryType)) {
        	course = homeDTOService.findRecommendedCourse();
        }else if(COURSE_NEWEST.equals(courseQueryType)) {
        	course = homeDTOService.findNewestCourse();
        }
        else if(COURSE_HOT.equals(courseQueryType)) {
        	course = homeDTOService.findNewestCourse();
        }else {
        	course = homeDTOService.findRecommendedCourse();
        }
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(course));
    }
    
    /**
     * 首页更多
     * @param pageable
     * @param filters
     * @param query
     * @return
     */
    @GetMapping("/home/explore")
    @Timed
    public ResponseEntity<Page<HomeDTO>> getPageSearchCourse(Pageable pageable, @RequestParam(required=false) String filter, @RequestParam(required=false) String query) {
        log.debug("REST request to get a page of Courses");
        Page<HomeDTO> course = homeDTOService.findPageAndSearchCourse(pageable, filter, query);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(course));
    }
    
}
