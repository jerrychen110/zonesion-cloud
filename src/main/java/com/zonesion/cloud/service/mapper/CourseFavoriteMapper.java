package com.zonesion.cloud.service.mapper;

import com.zonesion.cloud.domain.*;
import com.zonesion.cloud.service.dto.CourseFavoriteDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity CourseFavorite and its DTO CourseFavoriteDTO.
 */
@Mapper(componentModel = "spring", uses = {CourseMapper.class, })
public interface CourseFavoriteMapper extends EntityMapper <CourseFavoriteDTO, CourseFavorite> {

    @Mapping(source = "course.id", target = "courseId")
    @Mapping(source = "course.CourseFavorite", target = "courseCourseFavorite")
    CourseFavoriteDTO toDto(CourseFavorite courseFavorite); 

    @Mapping(source = "courseId", target = "course")
    CourseFavorite toEntity(CourseFavoriteDTO courseFavoriteDTO); 
    default CourseFavorite fromId(Long id) {
        if (id == null) {
            return null;
        }
        CourseFavorite courseFavorite = new CourseFavorite();
        courseFavorite.setId(id);
        return courseFavorite;
    }
}
