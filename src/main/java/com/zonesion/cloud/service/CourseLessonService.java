package com.zonesion.cloud.service;

import com.zonesion.cloud.config.Constants;
import com.zonesion.cloud.domain.Chapter;
import com.zonesion.cloud.domain.CourseLesson;
import com.zonesion.cloud.domain.CourseLessonNote;
import com.zonesion.cloud.repository.CourseLessonNoteRepository;
import com.zonesion.cloud.repository.CourseLessonRepository;
import com.zonesion.cloud.service.dto.CourseLessonAttachmentDTO;
import com.zonesion.cloud.web.rest.dto.in.CourseLessonNoteInDTO;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
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
    
    /*public List<CourseLesson> findAllCourseLesson(Long id){
		List<CourseLesson> courseLessonList = courseLessonRepository.findAll(new Specification<CourseLesson>() {

			@Override
			public Predicate toPredicate(Root<CourseLesson> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				// TODO Auto-generated method stub
				Join<CourseLesson, Chapter> courseLessonJoin = root.join("courseLessons", JoinType.LEFT);
                return cb.equal(courseLessonJoin.get("chapter_id"), id);
			}
			
		});
		return courseLessonList;
		
	}*/
	
	@Transactional(readOnly = true)
	public Page<CourseLessonAttachmentDTO> getLessonAttachementsByLessonId(long lessonId, Pageable pageable) {
		return courseLessonAttachmentService.findAllByTargetTypeAndTargetId(Constants.ATTACHEMENT_TYPE_LESSON, lessonId, pageable);
	}
	
	public CourseLessonNote saveCourseLessonNote(Long id, CourseLessonNoteInDTO courseLessonNoteInDTO) {
		CourseLessonNote courseLessonNote = new CourseLessonNote();
		courseLessonNote.setCourseId(courseLessonNoteInDTO.getCourseId());
		courseLessonNote.setCourseLesson(courseLessonRepository.findOne(id));
		courseLessonNote.setUserId(courseLessonNoteInDTO.getUserId());
		courseLessonNote.setContent(courseLessonNoteInDTO.getContent());
		courseLessonNote.setLength(courseLessonNoteInDTO.getLength());
		courseLessonNote.setLikeNum(0);
		courseLessonNote.setIsPrivate(courseLessonNoteInDTO.getIsPrivate());
		
		return courseLessonNoteRepository.save(courseLessonNote);
	}
}
