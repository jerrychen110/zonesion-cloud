package com.zonesion.cloud.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.zonesion.cloud.domain.Chapter;
import com.zonesion.cloud.domain.Course;
import com.zonesion.cloud.domain.CourseLesson;
import com.zonesion.cloud.domain.CourseMember;
import com.zonesion.cloud.repository.CourseRepository;
import com.zonesion.cloud.service.CourseService;
import com.zonesion.cloud.service.FileManageMentService;
import com.zonesion.cloud.service.dto.CourseLessonAttachmentDTO;
import com.zonesion.cloud.service.dto.CourseLessonNoteDTO;
import com.zonesion.cloud.service.dto.CourseReviewDTO;
import com.zonesion.cloud.service.dto.ext.CourseReviewExtDTO;
import com.zonesion.cloud.service.util.JcropSize;
import com.zonesion.cloud.service.util.ServiceConstants;
import com.zonesion.cloud.service.util.FileUtil;
import com.zonesion.cloud.web.rest.dto.CourseBaseInfoDTO;
import com.zonesion.cloud.web.rest.dto.CourseLessonInfoDTO;
import com.zonesion.cloud.web.rest.dto.MyCourseDTO;
import com.zonesion.cloud.web.rest.dto.in.ChapterInDTO;
import com.zonesion.cloud.web.rest.dto.in.CourseLessonInDTO;
import com.zonesion.cloud.web.rest.dto.in.CourseReviewInDTO;
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
    
    @Inject
	private CourseRepository courseRepository;

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
    public ResponseEntity<List<Course>> getAllCourses(@ApiParam Pageable pageable, String courseType, String courseSource) {
        log.debug("REST request to get a page of Courses");
        Page<Course> page = null;
        if(StringUtils.isBlank(courseType)&&StringUtils.isBlank(courseSource)) {
        	page = courseRepository.findAll(pageable);
        }else {
        	if(StringUtils.isBlank(courseType)&&StringUtils.isNotBlank(courseSource)) {
        		page = courseRepository.findAllByCourseSource(courseSource, pageable);
        	}else if(StringUtils.isNotBlank(courseType)&&StringUtils.isBlank(courseSource)) {
        		page = courseRepository.findAllByCourseType(courseType, pageable);
        	}else {
        		page = courseRepository.findAllByCourseSourceAndCourseType(courseSource, courseType, pageable);
        	}
        }
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
	public ResponseEntity<?> saveCourseCoverPicture(@PathVariable Long id, MultipartHttpServletRequest request)
			throws NumberFormatException, IOException {
		log.debug("Saving course cover picture : {}", id);
		String coverPicture = null;
		String[] crops = StringUtils.split(request.getParameter("cropSelection"), ",");// [x,y,x2,y2,w,h]
		String resizeToWidth = request.getParameter("resizeToWidth");
		String resizeToHeight = request.getParameter("resizeToHeight");
		Iterator<String> itr = request.getFileNames();
		if (itr.hasNext()) {
			MultipartFile mpf = request.getFile(itr.next());
			coverPicture = fileManageMentService.saveJcropPicture(mpf, FileUtil.LOCAL_UPLOAD_COURSE_COVER_PICTURE_FOLDER+"/"+id, new JcropSize(Integer.parseInt(crops[0]),
					Integer.parseInt(crops[1]), Integer.parseInt(crops[4]), Integer.parseInt(crops[5]),
					Integer.parseInt(resizeToWidth), Integer.parseInt(resizeToHeight)));
		}
		
		Course course = courseService.findOne(id);
		course.setCoverPicture(coverPicture);
		courseService.save(course);
		Map<String, String> ret = new HashMap<>();
		ret.put("coverPicture", coverPicture);
		return new ResponseEntity<>(ret, HttpStatus.OK);
    }
    
    /**
     * 查询课程基本信息
     * @param id
     * @return
     */
    @RequestMapping(value = "/courses/{id}/course-base", method = RequestMethod.GET)
    @Timed
    public ResponseEntity<CourseBaseInfoDTO> getCourseBaseById(@PathVariable Long id){
    	log.debug("query course base info : {}", id);
    	return new ResponseEntity<>(courseService.findCourseBaseInfoDTO(id), HttpStatus.OK);
    }
    
    /**
     * 查询课时信息
     * @param id
     * @return
     */
    @RequestMapping(value = "/courses/{id}/course-lessons", method = RequestMethod.GET)
    @Timed
    public ResponseEntity<CourseLessonInfoDTO> getCourseLessonInfoById(@PathVariable Long id){
    	log.debug("query course lesson info : {}", id);
    	return new ResponseEntity<>(courseService.findCourseLessonInfoDTO(id), HttpStatus.OK);
    }
    
    /**
     * 查询课程相关的附件资料
     * @param id
     * @param pageable
     * @return
     */
    @RequestMapping(value = "/courses/{id}/course-attachments", method = RequestMethod.GET)
    @Timed
    public ResponseEntity<List<CourseLessonAttachmentDTO>> getCourseAttachementsById(@PathVariable Long id, @ApiParam Pageable pageable){
    	log.debug("query course base info : {}", id);
    	Page<CourseLessonAttachmentDTO> page = courseService.getCourseAttachementsByCourseId(id, pageable);
    	HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/courses/{id}/course-attachments");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }
    
    /**
     * 查询课程相关的评论信息
     * @param id
     * @param pageable
     * @return
     */
    @RequestMapping(value = "/courses/{id}/course-reviews", method = RequestMethod.GET)
    @Timed
    public ResponseEntity<List<CourseReviewExtDTO>> getCourseReviewsById(@PathVariable Long id, @ApiParam Pageable pageable){
    	log.debug("query course base info : {}", id);
    	Page<CourseReviewExtDTO> page = courseService.getCourseReviewsByCourseId(id, pageable);
    	HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/courses/{id}/course-reviews");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }
    
    /**
     * 发表课程评论
     * @param id
     * @param courseReviewInDTO
     * @return
     * @throws URISyntaxException
     */
    @RequestMapping(value = "/courses/{id}/course-reviews", method = RequestMethod.POST)
    @Timed
    public ResponseEntity<CourseReviewDTO> saveCourseReview(@PathVariable Long id, CourseReviewInDTO courseReviewInDTO) throws URISyntaxException {
    	CourseReviewDTO result = courseService.saveCourseReview(id, courseReviewInDTO);
		return ResponseEntity.ok()
	            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, result.getId().toString()))
	            .body(result);
    }
    
    /**
     * 查询课程相关的笔记信息
     * @param id
     * @param pageable
     * @param courseLessonId
     * @return
     */
    @RequestMapping(value = "/courses/{id}/course-notes", method = RequestMethod.GET)
    @Timed
    public ResponseEntity<List<CourseLessonNoteDTO>> getCourseNotesById(@PathVariable Long id, @ApiParam Pageable pageable, @RequestParam(required=false) Long courseLessonId, @RequestParam(required=false) Long userId){
    	log.debug("query course base info : {}", id);
    	Page<CourseLessonNoteDTO> page = courseService.getCourseNotesByCourseId(id, pageable, courseLessonId, userId);
    	HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/courses/{id}/course-notes");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }
    
    /**
     * 添加章节
     *
     * @param chapter the chapter to create
     * @return the ResponseEntity with status 201 (Created) and with body the new chapter, or with status 400 (Bad Request) if the chapter has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/courses/{id}/chapters")
    @Timed
    public ResponseEntity<Chapter> createChapter(@PathVariable Long id, @Valid @RequestBody ChapterInDTO chapterInDTO) throws URISyntaxException {
        log.debug("REST request to create Chapter : {}", chapterInDTO);
        if (chapterInDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new chapter cannot already have an ID")).body(null);
        }
        Chapter result = courseService.saveChapter(id, chapterInDTO);
        return ResponseEntity.created(new URI("/api/courses/"+id+"/chapters/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }
    
    /**
     * 修改章节
     *
     * @param chapter the chapter to create
     * @return the ResponseEntity with status 201 (Created) and with body the new chapter, or with status 400 (Bad Request) if the chapter has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/courses/{id}/chapters")
    @Timed
    public ResponseEntity<Chapter> updateChapter(@PathVariable Long id, @Valid @RequestBody ChapterInDTO chapterInDTO) throws URISyntaxException {
        log.debug("REST request to update Chapter : {}", chapterInDTO);
        if (chapterInDTO.getId() == null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "id not exists", "ID不能为空！")).body(null);
        }
        Chapter result = courseService.saveChapter(id, chapterInDTO);
        return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, result.getId().toString()))
                .body(result);
    }
    
    /**
     * 新增课时
     * @param id
     * @param courseLessonInDTO
     * @return
     * @throws URISyntaxException
     */
    @PostMapping("/courses/{id}/lessons")
    @Timed
    public ResponseEntity<CourseLesson> createLesson(@PathVariable Long id, @Valid @RequestBody CourseLessonInDTO courseLessonInDTO) throws URISyntaxException {
    	log.debug("REST request to create Lesson : {}", courseLessonInDTO);
        if (courseLessonInDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new lesson cannot already have an ID")).body(null);
        }
        CourseLesson result = courseService.saveLesson(id, courseLessonInDTO);
    	return ResponseEntity.created(new URI("/api/courses/"+id+"/lessons/" + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
                .body(result);
    }
    
    /**
     * 修改课时
     * @param id
     * @param courseLessonInDTO
     * @return
     * @throws URISyntaxException
     */
    @PutMapping("/courses/{id}/lessons")
    @Timed
    public ResponseEntity<CourseLesson> updateLesson(@PathVariable Long id, @Valid @RequestBody CourseLessonInDTO courseLessonInDTO) throws URISyntaxException {
    	log.debug("REST request to update Lesson : {}", courseLessonInDTO);
        if (courseLessonInDTO.getId() == null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "id not exists", "ID不能为空！")).body(null);
        }
        CourseLesson result = courseService.saveLesson(id, courseLessonInDTO);
    	return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, result.getId().toString()))
                .body(result);
    }
    
    /**
     * 课程发布
     * @param id
     * @return
     * @throws URISyntaxException
     */
    @PutMapping("/courses/{id}/publish")
    @Timed
    public ResponseEntity<Course> publishCourse(@PathVariable Long id) throws URISyntaxException {
        log.debug("REST request to publish Course : {}", id);
        Course thisCourse = courseService.findOne(id);
        if (thisCourse == null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "course not exists", "该课程不存在！")).body(null);
        }
        thisCourse.setStatus(ServiceConstants.COURSE_STATUS_PUBLISHED);
        Course result = courseService.save(thisCourse);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }
    
    /**
     * 加入学习
     * @param id
     * @param courseMember
     * @return
     * @throws URISyntaxException
     */
    @PostMapping("/courses/{id}/join")
    @Timed
    public ResponseEntity<CourseMember> joinCourse(@PathVariable Long id, @Valid @RequestBody CourseMember courseMember) throws URISyntaxException {
    	log.debug("REST request to join course : {}", id);
        if (courseService.findOne(id) == null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "course not exists", "该课程不存在")).body(null);
        }
        CourseMember result = courseService.joinCourse(courseMember);
    	return ResponseEntity.created(new URI("/api/courses/"+id+"/join/" + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
                .body(result);
    }
    
    /**
     * 继续学习-获取课程最新学习的课时
     * @param id
     * @param userId
     * @return
     */
    @RequestMapping(value = "/courses/{id}/latest-learn-lesson", method = RequestMethod.GET)
    @Timed
    public ResponseEntity<?> getLatestLearnLesson(@PathVariable Long id, @RequestParam(required=true) Long userId) {
    	log.debug("REST request to get latest lesson : {}", id);
        if (courseService.findOne(id) == null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "course not exists", "该课程不存在")).body(null);
        }
        Map<String, Object> returnMap = new HashMap<String, Object>();
        returnMap.put("lessonId", courseService.getLatestLearnLesson(id, userId));
    	return new ResponseEntity<>(returnMap, HttpStatus.OK);
    }
    
    /**
     * 我正在学习的课程
     * @param pageable
     * @return
     */
    @RequestMapping(value = "/courses/my/learning-courses", method = RequestMethod.GET)
    @Timed
    public ResponseEntity<List<MyCourseDTO>> getMyLearningCourses(@ApiParam Pageable pageable){
    	log.debug("query my learning course");
    	Page<MyCourseDTO> page = courseService.getMyLearningCourses(pageable);
    	HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/courses/my/learning-courses");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }
    
    /**
     * 我收藏的课程
     * @param pageable
     * @return
     */
    @RequestMapping(value = "/courses/my/favorite-courses", method = RequestMethod.GET)
    @Timed
    public ResponseEntity<List<MyCourseDTO>> getMyFavoriteCourses(@ApiParam Pageable pageable){
    	log.debug("query my favorite course");
    	Page<MyCourseDTO> page = courseService.getMyFavoriteCourses(pageable);
    	HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/courses/my/favorite-courses");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }
    
}
