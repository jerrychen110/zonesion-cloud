package com.zonesion.cloud.service;

import com.zonesion.cloud.domain.CourseLessonAttachment;
import com.zonesion.cloud.repository.CourseLessonAttachmentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing CourseLessonAttachment.
 */
@Service
@Transactional
public class CourseLessonAttachmentService {

    private final Logger log = LoggerFactory.getLogger(CourseLessonAttachmentService.class);

    private final CourseLessonAttachmentRepository courseLessonAttachmentRepository;

    public CourseLessonAttachmentService(CourseLessonAttachmentRepository courseLessonAttachmentRepository) {
        this.courseLessonAttachmentRepository = courseLessonAttachmentRepository;
    }

    /**
     * Save a courseLessonAttachment.
     *
     * @param courseLessonAttachment the entity to save
     * @return the persisted entity
     */
    public CourseLessonAttachment save(CourseLessonAttachment courseLessonAttachment) {
        log.debug("Request to save CourseLessonAttachment : {}", courseLessonAttachment);
        return courseLessonAttachmentRepository.save(courseLessonAttachment);
    }

    /**
     *  Get all the courseLessonAttachments.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<CourseLessonAttachment> findAll(Pageable pageable) {
        log.debug("Request to get all CourseLessonAttachments");
        return courseLessonAttachmentRepository.findAll(pageable);
    }

    /**
     *  Get one courseLessonAttachment by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true)
    public CourseLessonAttachment findOne(Long id) {
        log.debug("Request to get CourseLessonAttachment : {}", id);
        return courseLessonAttachmentRepository.findOne(id);
    }

    /**
     *  Delete the  courseLessonAttachment by id.
     *
     *  @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete CourseLessonAttachment : {}", id);
        courseLessonAttachmentRepository.delete(id);
    }
}
