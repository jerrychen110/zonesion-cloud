package com.zonesion.cloud.service;

import com.zonesion.cloud.domain.CourseLessonLearn;
import com.zonesion.cloud.repository.CourseLessonLearnRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing CourseLessonLearn.
 */
@Service
@Transactional
public class CourseLessonLearnService {

    private final Logger log = LoggerFactory.getLogger(CourseLessonLearnService.class);

    private final CourseLessonLearnRepository courseLessonLearnRepository;

    public CourseLessonLearnService(CourseLessonLearnRepository courseLessonLearnRepository) {
        this.courseLessonLearnRepository = courseLessonLearnRepository;
    }

    /**
     * Save a courseLessonLearn.
     *
     * @param courseLessonLearn the entity to save
     * @return the persisted entity
     */
    public CourseLessonLearn save(CourseLessonLearn courseLessonLearn) {
        log.debug("Request to save CourseLessonLearn : {}", courseLessonLearn);
        return courseLessonLearnRepository.save(courseLessonLearn);
    }

    /**
     *  Get all the courseLessonLearns.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<CourseLessonLearn> findAll(Pageable pageable) {
        log.debug("Request to get all CourseLessonLearns");
        return courseLessonLearnRepository.findAll(pageable);
    }

    /**
     *  Get one courseLessonLearn by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true)
    public CourseLessonLearn findOne(Long id) {
        log.debug("Request to get CourseLessonLearn : {}", id);
        return courseLessonLearnRepository.findOne(id);
    }

    /**
     *  Delete the  courseLessonLearn by id.
     *
     *  @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete CourseLessonLearn : {}", id);
        courseLessonLearnRepository.delete(id);
    }
}
