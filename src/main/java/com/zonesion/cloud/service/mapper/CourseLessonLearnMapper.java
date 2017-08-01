package com.zonesion.cloud.service.mapper;

import com.zonesion.cloud.domain.*;
import com.zonesion.cloud.service.dto.CourseLessonLearnDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity CourseLessonLearn and its DTO CourseLessonLearnDTO.
 */
@Mapper(componentModel = "spring", uses = {CourseLessonMapper.class, })
public interface CourseLessonLearnMapper extends EntityMapper <CourseLessonLearnDTO, CourseLessonLearn> {

    @Mapping(source = "courseLesson.id", target = "courseLessonId")
    @Mapping(source = "courseLesson.CourseLessonLearn", target = "courseLessonCourseLessonLearn")
    CourseLessonLearnDTO toDto(CourseLessonLearn courseLessonLearn); 

    @Mapping(source = "courseLessonId", target = "courseLesson")
    CourseLessonLearn toEntity(CourseLessonLearnDTO courseLessonLearnDTO); 
    default CourseLessonLearn fromId(Long id) {
        if (id == null) {
            return null;
        }
        CourseLessonLearn courseLessonLearn = new CourseLessonLearn();
        courseLessonLearn.setId(id);
        return courseLessonLearn;
    }
}
