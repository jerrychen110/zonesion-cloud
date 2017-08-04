package com.zonesion.cloud.service.mapper;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.zonesion.cloud.domain.CourseLessonNoteLike;
import com.zonesion.cloud.service.dto.CourseLessonNoteLikeDTO;

/**   
 * @Title: CourseFavoriteMapper.java 
 * @Package com.zonesion.cloud.service.mapper 
 * @Description: TODO 
 * @author: cc  
 * @date: 2017年8月4日 下午2:53:25 
 */
@Service
public class CourseLessonNoteLikeMapper {
	
	public CourseLessonNoteLikeDTO courseLessonNoteLikeDTO(CourseLessonNoteLike courseLessonNoteLike) {
        return new CourseLessonNoteLikeDTO(courseLessonNoteLike);
    }

    public List<CourseLessonNoteLikeDTO> CourseLessonNoteLikesToCourseLessonNoteLikeDTOs(List<CourseLessonNoteLike> courseLessonNoteLikes) {
        return courseLessonNoteLikes.stream()
            .filter(Objects::nonNull)
            .map(this::courseLessonNoteLikeDTO)
            .collect(Collectors.toList());
    }
    
    public CourseLessonNoteLike courseLessonNoteLikeDTOToCourseLessonNoteLike(CourseLessonNoteLikeDTO courseLessonNoteLikeDTO){
    	if (courseLessonNoteLikeDTO == null){
    		return null;
    	} else {
    		CourseLessonNoteLike courseLessonNoteLike = new CourseLessonNoteLike();
    		courseLessonNoteLike.setId(courseLessonNoteLikeDTO.getId());
    		courseLessonNoteLike.setUserId(courseLessonNoteLikeDTO.getUserId());
    		courseLessonNoteLike.setCreatedTime(courseLessonNoteLikeDTO.getCreatedTime());
    		return courseLessonNoteLike;
    	}
    }
    
    public List<CourseLessonNoteLike> courseLessonNoteLikeDTOToCourseLessonNoteLikes(List<CourseLessonNoteLikeDTO> courseLessonNoteLikeDTOs) {
        return courseLessonNoteLikeDTOs.stream()
            .filter(Objects::nonNull)
            .map(this::courseLessonNoteLikeDTOToCourseLessonNoteLike)
            .collect(Collectors.toList());
    } 
    
    public CourseLessonNoteLike courseLessonNoteLikeFromId(Long id) {
    	if (id == null) {
    		return null;
    	}
    	CourseLessonNoteLike courseLessonNoteLike = new CourseLessonNoteLike();
    	courseLessonNoteLike.setId(id);
    	return courseLessonNoteLike;
    }
}
