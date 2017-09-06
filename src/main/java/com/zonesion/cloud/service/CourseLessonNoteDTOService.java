package com.zonesion.cloud.service;

import com.zonesion.cloud.repository.CourseLessonNoteDTORepository;
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
public class CourseLessonNoteDTOService {

    private final Logger log = LoggerFactory.getLogger(CourseLessonNoteDTOService.class);

    private final CourseLessonNoteDTORepository courseLessonNoteDTORepository;

    public CourseLessonNoteDTOService(CourseLessonNoteDTORepository courseLessonNoteDTORepository) {
        this.courseLessonNoteDTORepository = courseLessonNoteDTORepository;
    }

    public List<CourseLessonNoteDTO> findCourseLessonNote(Long courseId) {
        log.debug("Request to get all Courses notes ");
        return courseLessonNoteDTORepository.findCourseLessonNote(courseId);
    }
}
