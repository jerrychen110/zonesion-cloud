package com.zonesion.cloud.web.rest.dto;

import java.util.List;

public class CourseLessonInfoDTO {
	
	public Long courseId;
	
	public List<ChapterInfoDTO> chapters;
	
	public CourseLessonInfoDTO() {
		
	}

	public CourseLessonInfoDTO(Long courseId, List<ChapterInfoDTO> chapters) {
		super();
		this.courseId = courseId;
		this.chapters = chapters;
	}

	public Long getCourseId() {
		return courseId;
	}

	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}

	public List<ChapterInfoDTO> getChapters() {
		return chapters;
	}

	public void setChapters(List<ChapterInfoDTO> chapters) {
		this.chapters = chapters;
	}
	
}
