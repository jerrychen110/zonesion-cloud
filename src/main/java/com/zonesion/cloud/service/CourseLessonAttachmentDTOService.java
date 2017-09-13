package com.zonesion.cloud.service;

import com.zonesion.cloud.repository.CourseLessonAttachmentDTORepository;
import com.zonesion.cloud.repository.CourseLessonNoteDTORepository;
import com.zonesion.cloud.service.dto.CourseLessonAttachmentDTO;
import com.zonesion.cloud.service.dto.CourseLessonNoteDTO;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing CourseLessonNote.
 */
@Service
@Transactional
public class CourseLessonAttachmentDTOService {

    private final Logger log = LoggerFactory.getLogger(CourseLessonAttachmentDTOService.class);

    private final CourseLessonAttachmentDTORepository courseLessonAttachmentDTORepository;

    public CourseLessonAttachmentDTOService(CourseLessonAttachmentDTORepository courseLessonAttachmentDTORepository) {
        this.courseLessonAttachmentDTORepository = courseLessonAttachmentDTORepository;
    }

    public List<CourseLessonAttachmentDTO> findCourseLessonAttachment(Long courseId) {
        log.debug("Request to get all Courses attachments ");
        return courseLessonAttachmentDTORepository.findCourseLessonAttachment(courseId);
    }
}
