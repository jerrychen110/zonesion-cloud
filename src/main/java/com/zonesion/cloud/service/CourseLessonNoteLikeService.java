package com.zonesion.cloud.service;

import com.zonesion.cloud.domain.CourseLessonNoteLike;
import com.zonesion.cloud.repository.CourseLessonNoteLikeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing CourseLessonNoteLike.
 */
@Service
@Transactional
public class CourseLessonNoteLikeService {

    private final Logger log = LoggerFactory.getLogger(CourseLessonNoteLikeService.class);

    private final CourseLessonNoteLikeRepository courseLessonNoteLikeRepository;

    public CourseLessonNoteLikeService(CourseLessonNoteLikeRepository courseLessonNoteLikeRepository) {
        this.courseLessonNoteLikeRepository = courseLessonNoteLikeRepository;
    }

    /**
     * Save a courseLessonNoteLike.
     *
     * @param courseLessonNoteLike the entity to save
     * @return the persisted entity
     */
    public CourseLessonNoteLike save(CourseLessonNoteLike courseLessonNoteLike) {
        log.debug("Request to save CourseLessonNoteLike : {}", courseLessonNoteLike);
        return courseLessonNoteLikeRepository.save(courseLessonNoteLike);
    }

    /**
     *  Get all the courseLessonNoteLikes.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<CourseLessonNoteLike> findAll(Pageable pageable) {
        log.debug("Request to get all CourseLessonNoteLikes");
        return courseLessonNoteLikeRepository.findAll(pageable);
    }

    /**
     *  Get one courseLessonNoteLike by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true)
    public CourseLessonNoteLike findOne(Long id) {
        log.debug("Request to get CourseLessonNoteLike : {}", id);
        return courseLessonNoteLikeRepository.findOne(id);
    }

    /**
     *  Delete the  courseLessonNoteLike by id.
     *
     *  @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete CourseLessonNoteLike : {}", id);
        courseLessonNoteLikeRepository.delete(id);
    }
}
