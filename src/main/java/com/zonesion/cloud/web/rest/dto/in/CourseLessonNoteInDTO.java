package com.zonesion.cloud.web.rest.dto.in;

import javax.validation.constraints.NotNull;

public class CourseLessonNoteInDTO {
	
	private Long id;
	
	@NotNull
	private Long courseId;
	
	@NotNull
	private Long courseLessonId;
	
	@NotNull
	private Long userId;
	
	@NotNull
	private String content;
	
	private int length;
	
	private String isPrivate;
	
	public CourseLessonNoteInDTO() {
		
	}

	public CourseLessonNoteInDTO(Long id, Long courseId, Long courseLessonId, Long userId, String content, int length,
			String isPrivate) {
		super();
		this.id = id;
		this.courseId = courseId;
		this.courseLessonId = courseLessonId;
		this.userId = userId;
		this.content = content;
		this.length = length;
		this.isPrivate = isPrivate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCourseId() {
		return courseId;
	}

	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}

	public Long getCourseLessonId() {
		return courseLessonId;
	}

	public void setCourseLessonId(Long courseLessonId) {
		this.courseLessonId = courseLessonId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public String getIsPrivate() {
		return isPrivate;
	}

	public void setIsPrivate(String isPrivate) {
		this.isPrivate = isPrivate;
	}
	
}
