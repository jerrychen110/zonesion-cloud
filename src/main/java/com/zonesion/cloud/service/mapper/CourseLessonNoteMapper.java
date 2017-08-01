package com.zonesion.cloud.service.mapper;

import com.zonesion.cloud.domain.*;
import com.zonesion.cloud.service.dto.CourseLessonNoteDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity CourseLessonNote and its DTO CourseLessonNoteDTO.
 */
@Mapper(componentModel = "spring", uses = {CourseLessonMapper.class, })
public interface CourseLessonNoteMapper extends EntityMapper <CourseLessonNoteDTO, CourseLessonNote> {

    @Mapping(source = "courseLesson.id", target = "courseLessonId")
    @Mapping(source = "courseLesson.CourseLessonNote", target = "courseLessonCourseLessonNote")
    CourseLessonNoteDTO toDto(CourseLessonNote courseLessonNote); 

    @Mapping(source = "courseLessonId", target = "courseLesson")
    @Mapping(target = "courseLessonNoteLikes", ignore = true)
    CourseLessonNote toEntity(CourseLessonNoteDTO courseLessonNoteDTO); 
    default CourseLessonNote fromId(Long id) {
        if (id == null) {
            return null;
        }
        CourseLessonNote courseLessonNote = new CourseLessonNote();
        courseLessonNote.setId(id);
        return courseLessonNote;
    }
}
