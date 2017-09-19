package com.zonesion.cloud.web.rest.dto.in;

import javax.validation.constraints.NotNull;

public class ChapterInDTO {
	
	private Long id;
	
	@NotNull
	private String chapterType;
	
	@NotNull
	private String title;
	
	@NotNull
    private Long userId;
	
	@NotNull
	private Long courseId;
	
	@NotNull
	private Long parentId;

	public ChapterInDTO() {
		
	}

	public ChapterInDTO(Long id, String chapterType, String title, Long userId, Long courseId, Long parentId) {
		super();
		this.id = id;
		this.chapterType = chapterType;
		this.title = title;
		this.userId = userId;
		this.courseId = courseId;
		this.parentId = parentId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getChapterType() {
		return chapterType;
	}

	public void setChapterType(String chapterType) {
		this.chapterType = chapterType;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getCourseId() {
		return courseId;
	}

	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	
}
