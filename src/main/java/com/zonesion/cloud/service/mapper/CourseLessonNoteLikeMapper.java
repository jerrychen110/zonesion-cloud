package com.zonesion.cloud.service.mapper;

import com.zonesion.cloud.domain.*;
import com.zonesion.cloud.service.dto.CourseLessonNoteLikeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity CourseLessonNoteLike and its DTO CourseLessonNoteLikeDTO.
 */
@Mapper(componentModel = "spring", uses = {CourseLessonNoteMapper.class, })
public interface CourseLessonNoteLikeMapper extends EntityMapper <CourseLessonNoteLikeDTO, CourseLessonNoteLike> {

    @Mapping(source = "courseLessonNote.id", target = "courseLessonNoteId")
    @Mapping(source = "courseLessonNote.CourseLessonNoteLike", target = "courseLessonNoteCourseLessonNoteLike")
    CourseLessonNoteLikeDTO toDto(CourseLessonNoteLike courseLessonNoteLike); 

    @Mapping(source = "courseLessonNoteId", target = "courseLessonNote")
    CourseLessonNoteLike toEntity(CourseLessonNoteLikeDTO courseLessonNoteLikeDTO); 
    default CourseLessonNoteLike fromId(Long id) {
        if (id == null) {
            return null;
        }
        CourseLessonNoteLike courseLessonNoteLike = new CourseLessonNoteLike();
        courseLessonNoteLike.setId(id);
        return courseLessonNoteLike;
    }
}
