package com.zonesion.cloud.service.mapper;

import com.zonesion.cloud.domain.*;
import com.zonesion.cloud.service.dto.CourseDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Course and its DTO CourseDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface CourseMapper extends EntityMapper <CourseDTO, Course> {
    
    @Mapping(target = "courseFavorites", ignore = true)
    @Mapping(target = "chapter_FKS", ignore = true)
    @Mapping(target = "courseReviews", ignore = true)
    Course toEntity(CourseDTO courseDTO); 
    default Course fromId(Long id) {
        if (id == null) {
            return null;
        }
        Course course = new Course();
        course.setId(id);
        return course;
    }
}
