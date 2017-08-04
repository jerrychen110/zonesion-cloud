package com.zonesion.cloud.service.mapper;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.zonesion.cloud.domain.CourseLessonLearn;
import com.zonesion.cloud.service.dto.CourseLessonLearnDTO;

/**   
 * @Title: CourseFavoriteMapper.java 
 * @Package com.zonesion.cloud.service.mapper 
 * @Description: TODO 
 * @author: cc  
 * @date: 2017年8月4日 下午2:53:25 
 */
public class CourseLessonLearnMapper {
	
	public CourseLessonLearnDTO courseLessonLearnDTO(CourseLessonLearn courseLessonLearn) {
        return new CourseLessonLearnDTO(courseLessonLearn);
    }

    public List<CourseLessonLearnDTO> courseLessonLearnsToCourseLessonLearnDTOs(List<CourseLessonLearn> courseLessonLearns) {
        return courseLessonLearns.stream()
            .filter(Objects::nonNull)
            .map(this::courseLessonLearnDTO)
            .collect(Collectors.toList());
    }
    
    public CourseLessonLearn courseLessonLearnDTOToCourseLessonLearn(CourseLessonLearnDTO courseLessonLearnDTO){
    	if (courseLessonLearnDTO == null){
    		return null;
    	} else {
    		CourseLessonLearn courseLessonLearn = new CourseLessonLearn();
    		courseLessonLearn.setId(courseLessonLearnDTO.getId());
    		courseLessonLearn.setCourseId(courseLessonLearnDTO.getCourseId());
    		courseLessonLearn.setUserId(courseLessonLearnDTO.getUserId());
    		courseLessonLearn.setDurationId(courseLessonLearnDTO.getDurationId());
    		courseLessonLearn.setIsComplete(courseLessonLearnDTO.getIsComplete());
    		return courseLessonLearn;
    	}
    }
    
    public List<CourseLessonLearn> courseLessonLearnDTOToCourseLessonLearns(List<CourseLessonLearnDTO> courseLessonLearnDTOs) {
        return courseLessonLearnDTOs.stream()
            .filter(Objects::nonNull)
            .map(this::courseLessonLearnDTOToCourseLessonLearn)
            .collect(Collectors.toList());
    } 
    
    public CourseLessonLearn courseLessonLearnFromId(Long id) {
    	if (id == null) {
    		return null;
    	}
    	CourseLessonLearn courseLessonLearn = new CourseLessonLearn();
    	courseLessonLearn.setId(id);
    	return courseLessonLearn;
    }
}
