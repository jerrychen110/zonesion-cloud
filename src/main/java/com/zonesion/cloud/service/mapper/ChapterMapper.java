package com.zonesion.cloud.service.mapper;

import com.zonesion.cloud.domain.*;
import com.zonesion.cloud.service.dto.ChapterDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Chapter and its DTO ChapterDTO.
 */
@Mapper(componentModel = "spring", uses = {CourseMapper.class, })
public interface ChapterMapper extends EntityMapper <ChapterDTO, Chapter> {

    @Mapping(source = "course.id", target = "courseId")
    @Mapping(source = "course.Chapter", target = "courseChapter")
    ChapterDTO toDto(Chapter chapter); 
    @Mapping(target = "courseLessons", ignore = true)

    @Mapping(source = "courseId", target = "course")
    Chapter toEntity(ChapterDTO chapterDTO); 
    default Chapter fromId(Long id) {
        if (id == null) {
            return null;
        }
        Chapter chapter = new Chapter();
        chapter.setId(id);
        return chapter;
    }
}
