package com.zonesion.cloud.service.mapper;

import com.zonesion.cloud.domain.*;
import com.zonesion.cloud.service.dto.CourseReviewDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity CourseReview and its DTO CourseReviewDTO.
 */
@Mapper(componentModel = "spring", uses = {CourseMapper.class, })
public interface CourseReviewMapper extends EntityMapper <CourseReviewDTO, CourseReview> {

    @Mapping(source = "course.id", target = "courseId")
    @Mapping(source = "course.CourseReview", target = "courseCourseReview")
    CourseReviewDTO toDto(CourseReview courseReview); 

    @Mapping(source = "courseId", target = "course")
    CourseReview toEntity(CourseReviewDTO courseReviewDTO); 
    default CourseReview fromId(Long id) {
        if (id == null) {
            return null;
        }
        CourseReview courseReview = new CourseReview();
        courseReview.setId(id);
        return courseReview;
    }
}
