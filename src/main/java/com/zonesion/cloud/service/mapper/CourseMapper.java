package com.zonesion.cloud.service.mapper;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.zonesion.cloud.domain.Course;
import com.zonesion.cloud.service.dto.CourseDTO;

/**   
 * @Title: CouseMapper.java 
 * @Package com.zonesion.cloud.service.mapper 
 * @Description: TODO 
 * @author: cc  
 * @date: 2017年8月3日 下午4:55:12 
 */
@Service
public class CourseMapper {
	
	public CourseDTO courseToCourseDTO(Course course){
		return new CourseDTO(course);
	}
	
	public List<CourseDTO> courseToCourseDTOs(List<Course> courses){
		return courses.stream()
				.filter(Objects::nonNull)
				.map(this::courseToCourseDTO)
				.collect(Collectors.toList());
	}
	
	public Course courseDTOToCourse(CourseDTO courseDTO){
		if (courseDTO == null){
			return null;
		} else {
			Course course = new Course();
			course.setId(course.getId());
			course.setUserId(course.getUserId());
			course.setTitle(course.getTitle());
			course.setSubTitle(course.getSubTitle());
			course.setStatus(course.getStatus());
			course.setCourseType(course.getCourseType());
			course.setLessonNum(course.getLessonNum());
			course.setCredit(course.getCredit());
			course.setCoverPicture(course.getCoverPicture());
			course.setIntroduction(course.getIntroduction());
			course.setGoals(course.getGoals());
			course.setRecommended(course.getRecommended());
			course.setRecommendedSort(course.getRecommendedSort());
			return course;
		}
		
	}
	
	public List<Course> courseDTOsToCourses(List<CourseDTO> courseDTOs){
		return courseDTOs.stream()
	            .filter(Objects::nonNull)
	            .map(this::courseDTOToCourse)
	            .collect(Collectors.toList());
	}
	
	public Course courseFromId(Long id){
		if (id == null){
			return null;
		}
		Course course = new Course();
		course.setId(id);
		return course;
	}
	
	
	
	
}
