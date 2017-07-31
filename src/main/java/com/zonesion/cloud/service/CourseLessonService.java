package com.zonesion.cloud.service;

import com.zonesion.cloud.domain.CourseLesson;
import com.zonesion.cloud.repository.CourseLessonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
}
