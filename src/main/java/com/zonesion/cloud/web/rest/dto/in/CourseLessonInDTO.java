package com.zonesion.cloud.web.rest.dto.in;

import javax.validation.constraints.NotNull;

public class CourseLessonInDTO {

	private Long id;
	
	@NotNull
    private Long userId;
	
	@NotNull
    private Long courseId;
	
	@NotNull
    private Long chapterId;
	
	@NotNull
    private String courseLessonType;
	
	@NotNull
    private String title;
	
	private String summary;
	
	private String content;
	
	@NotNull
    private Integer credit;

	private String mediaName;
	
    private Integer mediaSize;
    
    private Integer mediaLength;

    private String mediaSource;

    private String mediaUri;
    
    public CourseLessonInDTO() {
    	
    }

	public CourseLessonInDTO(Long id, Long userId, Long courseId, Long chapterId, String courseLessonType, String title,
			String summary, String content, Integer credit, String mediaName, Integer mediaSize, Integer mediaLength,
			String mediaSource, String mediaUri) {
		super();
		this.id = id;
		this.userId = userId;
		this.courseId = courseId;
		this.chapterId = chapterId;
		this.courseLessonType = courseLessonType;
		this.title = title;
		this.summary = summary;
		this.content = content;
		this.credit = credit;
		this.mediaName = mediaName;
		this.mediaSize = mediaSize;
		this.mediaLength = mediaLength;
		this.mediaSource = mediaSource;
		this.mediaUri = mediaUri;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Long getChapterId() {
		return chapterId;
	}

	public void setChapterId(Long chapterId) {
		this.chapterId = chapterId;
	}

	public String getCourseLessonType() {
		return courseLessonType;
	}

	public void setCourseLessonType(String courseLessonType) {
		this.courseLessonType = courseLessonType;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getCredit() {
		return credit;
	}

	public void setCredit(Integer credit) {
		this.credit = credit;
	}

	public String getMediaName() {
		return mediaName;
	}

	public void setMediaName(String mediaName) {
		this.mediaName = mediaName;
	}

	public Integer getMediaSize() {
		return mediaSize;
	}

	public void setMediaSize(Integer mediaSize) {
		this.mediaSize = mediaSize;
	}

	public Integer getMediaLength() {
		return mediaLength;
	}

	public void setMediaLength(Integer mediaLength) {
		this.mediaLength = mediaLength;
	}

	public String getMediaSource() {
		return mediaSource;
	}

	public void setMediaSource(String mediaSource) {
		this.mediaSource = mediaSource;
	}

	public String getMediaUri() {
		return mediaUri;
	}

	public void setMediaUri(String mediaUri) {
		this.mediaUri = mediaUri;
	}
    
}
