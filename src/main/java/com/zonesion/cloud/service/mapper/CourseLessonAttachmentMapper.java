package com.zonesion.cloud.service.mapper;

import com.zonesion.cloud.domain.*;
import com.zonesion.cloud.service.dto.CourseLessonAttachmentDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity CourseLessonAttachment and its DTO CourseLessonAttachmentDTO.
 */
@Mapper(componentModel = "spring", uses = {CourseLessonMapper.class, })
public interface CourseLessonAttachmentMapper extends EntityMapper <CourseLessonAttachmentDTO, CourseLessonAttachment> {

    @Mapping(source = "courseLesson.id", target = "courseLessonId")
    @Mapping(source = "courseLesson.CourseLessonAttachment", target = "courseLessonCourseLessonAttachment")
    CourseLessonAttachmentDTO toDto(CourseLessonAttachment courseLessonAttachment); 

    @Mapping(source = "courseLessonId", target = "courseLesson")
    CourseLessonAttachment toEntity(CourseLessonAttachmentDTO courseLessonAttachmentDTO); 
    default CourseLessonAttachment fromId(Long id) {
        if (id == null) {
            return null;
        }
        CourseLessonAttachment courseLessonAttachment = new CourseLessonAttachment();
        courseLessonAttachment.setId(id);
        return courseLessonAttachment;
    }
}
