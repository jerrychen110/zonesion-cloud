package com.zonesion.cloud.service.mapper;

import com.zonesion.cloud.domain.*;
import com.zonesion.cloud.service.dto.CourseLessonDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity CourseLesson and its DTO CourseLessonDTO.
 */
@Mapper(componentModel = "spring", uses = {ChapterMapper.class, })
public interface CourseLessonMapper extends EntityMapper <CourseLessonDTO, CourseLesson> {

    @Mapping(source = "courseLesson_FK.id", target = "courseLesson_FKId")
    @Mapping(source = "courseLesson_FK.CourseLesson", target = "courseLesson_FKCourseLesson")
    CourseLessonDTO toDto(CourseLesson courseLesson); 
    @Mapping(target = "courseLessonAttachments", ignore = true)
    @Mapping(target = "courseLessonLearns", ignore = true)
    @Mapping(target = "courseLessonNotes", ignore = true)

    @Mapping(source = "courseLesson_FKId", target = "courseLesson_FK")
    CourseLesson toEntity(CourseLessonDTO courseLessonDTO); 
    default CourseLesson fromId(Long id) {
        if (id == null) {
            return null;
        }
        CourseLesson courseLesson = new CourseLesson();
        courseLesson.setId(id);
        return courseLesson;
    }
}
