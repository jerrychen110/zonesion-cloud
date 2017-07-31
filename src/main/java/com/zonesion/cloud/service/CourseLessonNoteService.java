package com.zonesion.cloud.service;

import com.zonesion.cloud.domain.CourseLessonNote;
import com.zonesion.cloud.repository.CourseLessonNoteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing CourseLessonNote.
 */
@Service
@Transactional
public class CourseLessonNoteService {

    private final Logger log = LoggerFactory.getLogger(CourseLessonNoteService.class);

    private final CourseLessonNoteRepository courseLessonNoteRepository;

    public CourseLessonNoteService(CourseLessonNoteRepository courseLessonNoteRepository) {
        this.courseLessonNoteRepository = courseLessonNoteRepository;
    }

    /**
     * Save a courseLessonNote.
     *
     * @param courseLessonNote the entity to save
     * @return the persisted entity
     */
    public CourseLessonNote save(CourseLessonNote courseLessonNote) {
        log.debug("Request to save CourseLessonNote : {}", courseLessonNote);
        return courseLessonNoteRepository.save(courseLessonNote);
    }

    /**
     *  Get all the courseLessonNotes.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<CourseLessonNote> findAll(Pageable pageable) {
        log.debug("Request to get all CourseLessonNotes");
        return courseLessonNoteRepository.findAll(pageable);
    }

    /**
     *  Get one courseLessonNote by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true)
    public CourseLessonNote findOne(Long id) {
        log.debug("Request to get CourseLessonNote : {}", id);
        return courseLessonNoteRepository.findOne(id);
    }

    /**
     *  Delete the  courseLessonNote by id.
     *
     *  @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete CourseLessonNote : {}", id);
        courseLessonNoteRepository.delete(id);
    }
}
