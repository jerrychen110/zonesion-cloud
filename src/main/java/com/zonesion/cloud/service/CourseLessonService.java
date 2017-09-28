package com.zonesion.cloud.service;

import com.zonesion.cloud.config.Constants;
import com.zonesion.cloud.domain.CourseLesson;
import com.zonesion.cloud.domain.CourseLessonLearn;
import com.zonesion.cloud.domain.CourseLessonNote;
import com.zonesion.cloud.repository.CourseLessonLearnRepository;
import com.zonesion.cloud.repository.CourseLessonNoteRepository;
import com.zonesion.cloud.repository.CourseLessonRepository;
import com.zonesion.cloud.service.dto.CourseLessonAttachmentDTO;
import com.zonesion.cloud.web.rest.dto.in.CourseLessonNoteInDTO;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing CourseLesson.
 */
@Service
@Transactional
public class CourseLessonService {

    private final Logger log = LoggerFactory.getLogger(CourseLessonService.class);

    private final CourseLessonRepository courseLessonRepository;

    public CourseLessonService(CourseLessonRepository courseLessonRepository) {
        this.courseLessonRepository = courseLessonRepository;
    }
    
    @Inject
    private CourseLessonAttachmentService courseLessonAttachmentService;
    
    @Inject
    private CourseLessonNoteRepository courseLessonNoteRepository;
    
    @Inject
    private CourseLessonLearnRepository courseLessonLearnRepository;
    
    @Inject
    private JdbcTemplate jdbcTemplate;

    /**
     * Save a courseLesson.
     *
     * @param courseLesson the entity to save
     * @return the persisted entity
     */
    public CourseLesson save(CourseLesson courseLesson) {
        log.debug("Request to save CourseLesson : {}", courseLesson);
        return courseLessonRepository.save(courseLesson);
    }

    /**
     *  Get all the courseLessons.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<CourseLesson> findAll(Pageable pageable) {
        log.debug("Request to get all CourseLessons");
        return courseLessonRepository.findAll(pageable);
    }

    /**
     *  Get one courseLesson by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true)
    public CourseLesson findOne(Long id) {
        log.debug("Request to get CourseLesson : {}", id);
        return courseLessonRepository.findOne(id);
    }

    /**
     *  Delete the  courseLesson by id.
     *
     *  @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete CourseLesson : {}", id);
        courseLessonRepository.delete(id);
    }


	public List<CourseLesson> findAllCourseLesson(Long id) {
		// TODO Auto-generated method stub
		return courseLessonRepository.findAllCourseLesson(id);
	}
    
    /**
     * 获取课时附件列表
     * @param id
     * @param pageable
     * @return
     */
	@Transactional(readOnly = true)
	public Page<CourseLessonAttachmentDTO> getLessonAttachementsByLessonId(long lessonId, Pageable pageable) {
		return courseLessonAttachmentService.findAllByTargetTypeAndTargetId(Constants.ATTACHEMENT_TYPE_LESSON, lessonId, pageable);
	}
	
    /**
     * 保存课时笔记
     * @param id
     * @param courseLessonNoteInDTO
     * @return
     */
	public CourseLessonNote saveCourseLessonNote(Long id, CourseLessonNoteInDTO courseLessonNoteInDTO) {
		CourseLessonNote courseLessonNote = new CourseLessonNote();
		courseLessonNote.setCourseId(courseLessonNoteInDTO.getCourseId());
		courseLessonNote.setCourseLesson(courseLessonRepository.findOne(id));
		courseLessonNote.setUserId(courseLessonNoteInDTO.getUserId());
		courseLessonNote.setContent(courseLessonNoteInDTO.getContent());
		courseLessonNote.setLength(courseLessonNoteInDTO.getLength()!=null?courseLessonNoteInDTO.getLength():0);
		courseLessonNote.setLikeNum(0);
		courseLessonNote.setIsPrivate(courseLessonNoteInDTO.getIsPrivate()!=null?courseLessonNoteInDTO.getIsPrivate():"0");
		
		return courseLessonNoteRepository.save(courseLessonNote);
	}
	
    /**
     * 获取课时的最大number
     * @param courseId
     * @return
     */
	public int getLessonMaxNumberByCourseId(Long courseId) {
		StringBuilder sb = new StringBuilder();
    	sb.append("select max(number) from t_course_lesson where course_id=").append(courseId);
    	return jdbcTemplate.queryForObject(sb.toString(), Integer.class)!=null?jdbcTemplate.queryForObject(sb.toString(), Integer.class):0;
	}
	
    /**
     * 完成课时学习
     * @param lessonId
     * @param courseId
     * @param userId
     * @return
     */
    public CourseLessonLearn doLessonLearned(Long lessonId, Long courseId, Long userId) {
        CourseLessonLearn returnCourseLessonLearn = null;
        CourseLessonLearn findCourseLessonLearn = courseLessonLearnRepository.findOneByUserIdAndCourseLesson_id(userId, lessonId);
        if(findCourseLessonLearn!=null) {
            findCourseLessonLearn.setIsComplete("1");
            returnCourseLessonLearn = courseLessonLearnRepository.save(findCourseLessonLearn);
        }else {
            CourseLessonLearn courseLessonLearn = new CourseLessonLearn();
            courseLessonLearn.setCourseId(courseId);
            courseLessonLearn.setUserId(userId);
            courseLessonLearn.setCourseLesson(courseLessonRepository.findOne(lessonId));
            courseLessonLearn.setIsComplete("1");
            courseLessonLearn.setDuration(Long.valueOf(0));
            returnCourseLessonLearn = courseLessonLearnRepository.save(courseLessonLearn);
        }
        return returnCourseLessonLearn;
    }
    
    /**
     * 插入学习记录
     * @param lessonId
     * @param courseId
     * @param userId
     * @return
     */
    public CourseLessonLearn insertLessonLearn(Long lessonId, Long courseId, Long userId) {
    	CourseLessonLearn returnCourseLessonLearn = null;
        CourseLessonLearn findCourseLessonLearn = courseLessonLearnRepository.findOneByUserIdAndCourseLesson_id(userId, lessonId);
        if(findCourseLessonLearn==null) {
        	CourseLessonLearn courseLessonLearn = new CourseLessonLearn();
            courseLessonLearn.setCourseId(courseId);
            courseLessonLearn.setUserId(userId);
            courseLessonLearn.setCourseLesson(courseLessonRepository.findOne(lessonId));
            courseLessonLearn.setIsComplete("0");
            courseLessonLearn.setDuration(Long.valueOf(0));
            returnCourseLessonLearn = courseLessonLearnRepository.save(courseLessonLearn);
        }else {
        	returnCourseLessonLearn = findCourseLessonLearn;
        }
        return returnCourseLessonLearn;
    }

}
