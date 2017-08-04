package com.zonesion.cloud.service.mapper;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.zonesion.cloud.domain.CourseReview;
import com.zonesion.cloud.service.dto.CourseReviewDTO;

/**   
 * @Title: CourseFavoriteMapper.java 
 * @Package com.zonesion.cloud.service.mapper 
 * @Description: TODO 
 * @author: cc  
 * @date: 2017年8月4日 下午2:53:25 
 */
public class CourseReviewMapper {
	
	public CourseReviewDTO courseReviewDTO(CourseReview courseReview) {
        return new CourseReviewDTO(courseReview);
    }

    public List<CourseReviewDTO> courseReviewsToCourseReviewDTOs(List<CourseReview> courseReviews) {
        return courseReviews.stream()
            .filter(Objects::nonNull)
            .map(this::courseReviewDTO)
            .collect(Collectors.toList());
    }
    
    public CourseReview courseReviewDTOToCourseReview(CourseReviewDTO courseReviewDTO){
    	if (courseReviewDTO == null){
    		return null;
    	} else {
    		CourseReview courseReview = new CourseReview();
    		courseReview.setId(courseReviewDTO.getId());
    		courseReview.setUserId(courseReviewDTO.getUserId());

    		return courseReview;
    	}
    }
    
    public List<CourseReview> courseReviewDTOToCourseReviews(List<CourseReviewDTO> courseReviewDTOs) {
        return courseReviewDTOs.stream()
            .filter(Objects::nonNull)
            .map(this::courseReviewDTOToCourseReview)
            .collect(Collectors.toList());
    } 
    
    public CourseReview courseReviewFromId(Long id) {
    	if (id == null) {
    		return null;
    	}
    	CourseReview courseReview = new CourseReview();
    	courseReview.setId(id);
    	return courseReview;
    }
}
