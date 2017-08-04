package com.zonesion.cloud.service.mapper;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.zonesion.cloud.domain.CourseLesson;
import com.zonesion.cloud.service.dto.CourseLessonDTO;

/**   
 * @Title: CourseFavoriteMapper.java 
 * @Package com.zonesion.cloud.service.mapper 
 * @Description: TODO 
 * @author: cc  
 * @date: 2017年8月4日 下午2:53:25 
 */
@Service
public class CourseLessonMapper {
	
	public CourseLessonDTO courseLessonDTO(CourseLesson courseLesson) {
        return new CourseLessonDTO(courseLesson);
    }

    public List<CourseLessonDTO> courseLessonsToCourseLessonDTOs(List<CourseLesson> courseLessons) {
        return courseLessons.stream()
            .filter(Objects::nonNull)
            .map(this::courseLessonDTO)
            .collect(Collectors.toList());
    }
    
    public CourseLesson courseLessonDTOToCourseLesson(CourseLessonDTO courseLessonDTO){
    	if (courseLessonDTO == null){
    		return null;
    	} else {
    		CourseLesson courseLesson = new CourseLesson();
    		courseLesson.setId(courseLessonDTO.getId());
    		courseLesson.setUserId(courseLessonDTO.getUserId());
    		courseLesson.setNumber(courseLessonDTO.getNumber());
    		courseLesson.setSeq(courseLessonDTO.getSeq());
    		courseLesson.setTitle(courseLessonDTO.getTitle());
    		courseLesson.setSummary(courseLessonDTO.getSummary());
    		courseLesson.setCourseLessonType(courseLessonDTO.getCourseLessonType());
    		courseLesson.setContent(courseLessonDTO.getContent());
    		courseLesson.setCredit(courseLessonDTO.getCredit());
    		courseLesson.setMediaId(courseLessonDTO.getMediaId());
    		courseLesson.setMediaSource(courseLessonDTO.getMediaSource());
    		courseLesson.setMediaName(courseLessonDTO.getMediaName());
    		courseLesson.setMediaUri(courseLessonDTO.getMediaUri());
    		courseLesson.setLearnedNum(courseLessonDTO.getLearnedNum());
    		courseLesson.setViewedNum(courseLessonDTO.getViewedNum());
    		return courseLesson;
    	}
    }
    
    public List<CourseLesson> courseLessonDTOToCourseLessons(List<CourseLessonDTO> courseLessonDTOs) {
        return courseLessonDTOs.stream()
            .filter(Objects::nonNull)
            .map(this::courseLessonDTOToCourseLesson)
            .collect(Collectors.toList());
    } 
    
    public CourseLesson CourseLessonFromId(Long id) {
    	if (id == null) {
    		return null;
    	}
    	CourseLesson CourseLesson = new CourseLesson();
    	CourseLesson.setId(id);
    	return CourseLesson;
    }
}
