package com.zonesion.cloud.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.zonesion.cloud.domain.Course;
import com.zonesion.cloud.service.CourseService;
import com.zonesion.cloud.service.FileManageMentService;
import com.zonesion.cloud.service.util.AvatarSize;
import com.zonesion.cloud.service.util.FileUtil;
import com.zonesion.cloud.web.rest.util.HeaderUtil;
import com.zonesion.cloud.web.rest.util.PaginationUtil;
import io.swagger.annotations.ApiParam;
import io.github.jhipster.web.util.ResponseUtil;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.inject.Inject;
import javax.validation.Valid;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * REST controller for managing Course.
 */
@RestController
@RequestMapping("/api")
public class CourseResource {

    private final Logger log = LoggerFactory.getLogger(CourseResource.class);

    private static final String ENTITY_NAME = "course";

    private final CourseService courseService;

    public CourseResource(CourseService courseService) {
        this.courseService = courseService;
    }
    
    @Inject
	private FileManageMentService fileManageMentService;

    /**
     * POST  /courses : Create a new course.
     *
     * @param course the course to create
     * @return the ResponseEntity with status 201 (Created) and with body the new course, or with status 400 (Bad Request) if the course has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/courses")
    @Timed
    public ResponseEntity<Course> createCourse(@Valid @RequestBody Course course) throws URISyntaxException {
        log.debug("REST request to save Course : {}", course);
        if (course.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new course cannot already have an ID")).body(null);
        }
        Course result = courseService.save(course);
        return ResponseEntity.created(new URI("/api/courses/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /courses : Updates an existing course.
     *
     * @param course the course to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated course,
     * or with status 400 (Bad Request) if the course is not valid,
     * or with status 500 (Internal Server Error) if the course couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/courses")
    @Timed
    public ResponseEntity<Course> updateCourse(@Valid @RequestBody Course course) throws URISyntaxException {
        log.debug("REST request to update Course : {}", course);
        if (course.getId() == null) {
            return createCourse(course);
        }
        Course result = courseService.save(course);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, course.getId().toString()))
            .body(result);
    }

    /**
     * GET  /courses : get all the courses.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of courses in body
     */
    @GetMapping("/courses")
    @Timed
    public ResponseEntity<List<Course>> getAllCourses(@ApiParam Pageable pageable) {
        log.debug("REST request to get a page of Courses");
        Page<Course> page = courseService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/courses");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /courses/:id : get the "id" course.
     *
     * @param id the id of the course to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the course, or with status 404 (Not Found)
     */
    @GetMapping("/courses/{id}")
    @Timed
    public ResponseEntity<Course> getCourse(@PathVariable Long id) {
        log.debug("REST request to get Course : {}", id);
        Course course = courseService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(course));
    }

    /**
     * DELETE  /courses/:id : delete the "id" course.
     *
     * @param id the id of the course to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/courses/{id}")
    @Timed
    public ResponseEntity<Void> deleteCourse(@PathVariable Long id) {
        log.debug("REST request to delete Course : {}", id);
        courseService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
    
    @GetMapping("/courses/newest")
    @Timed
    public ResponseEntity<List<Course>> getNewestCourses(@ApiParam Pageable pageable) {
        log.debug("REST request to get a page of Courses");
        Page<Course> page = courseService.findNewestCourseByUserId(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/courses/newest");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }
    
    @GetMapping("/courses/hot")
    @Timed
    public ResponseEntity<List<Course>> getHotCourses(@ApiParam Pageable pageable) {
        log.debug("REST request to get a page of Courses");
        Page<Course> page = courseService.findHotCourseByUserId(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/courses/hot");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }
    
    @GetMapping("/courses/recommended")
    @Timed
    public ResponseEntity<List<Course>> getRecommendedCourses(@ApiParam Pageable pageable) {
        log.debug("REST request to get a page of Courses");
        Page<Course> page = courseService.findRecommendedCourseByUserId(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/courses/recommended");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }
    
    /*@GetMapping("/courses/info/{id}")
    @Timed
    public ResponseEntity<List<Course>> getAllCourse(@PathVariable Long id) {
        log.debug("REST request to get Course : {}", id);
        List<Course> course = courseService.findAllCourse(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(course));
    }*/
    
    /**
     * 上传课程封面
     * @param id
     * @param request
     * @return
     * @throws NumberFormatException
     * @throws IOException
     */
    @RequestMapping(value = "/courses/{id}/cover-picture", method = RequestMethod.POST)
    @Transactional
	public ResponseEntity<?> saveTeamLogo(@PathVariable Long id, MultipartHttpServletRequest request)
			throws NumberFormatException, IOException {
		log.debug("Saving course cover picture : {}", id);
		String coverPicture = null;
		String[] crops = StringUtils.split(request.getParameter("cropSelection"), ",");// [x,y,x2,y2,w,h]
		String resizeTo = request.getParameter("resizeTo");
		Iterator<String> itr = request.getFileNames();
		if (itr.hasNext()) {
			MultipartFile mpf = request.getFile(itr.next());
			coverPicture = fileManageMentService.saveAvatar(mpf, FileUtil.LOCAL_UPLOAD_COURSE_COVER_PICTURE_FOLDER, new AvatarSize(Integer.parseInt(crops[0]),
					Integer.parseInt(crops[1]), Integer.parseInt(crops[4]), Integer.parseInt(crops[5]),
					Integer.parseInt(resizeTo), Integer.parseInt(resizeTo)));
		}
		
		Course course = courseService.findOne(id);
		course.setCoverPicture(coverPicture);
		courseService.save(course);
		Map<String, String> ret = new HashMap<>();
		ret.put("coverPicture", coverPicture);
		return new ResponseEntity<>(ret, HttpStatus.OK);
    }
}
