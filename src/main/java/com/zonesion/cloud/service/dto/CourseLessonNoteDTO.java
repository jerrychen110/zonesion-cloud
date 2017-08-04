package com.zonesion.cloud.service.dto;


import javax.validation.constraints.*;

import com.zonesion.cloud.domain.CourseLessonNote;

import java.io.Serializable;
import java.time.Instant;

/**
 * A DTO for the CourseLessonNote entity.
 */
public class CourseLessonNoteDTO implements Serializable {

    /**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

    @NotNull
    private Long courseId;

    @NotNull
    private Long userId;

    @NotNull
    private String content;

    @NotNull
    private Integer length;

    @NotNull
    private Integer likeNum;

    @NotNull
    @Size(max = 1)
    private String isPrivate;
    
    private String createdBy;

    private Instant createdDate;

    private String lastModifiedBy;

    private Instant lastModifiedDate;
    
   
    public CourseLessonNoteDTO(){
    	
    }
    
    public CourseLessonNoteDTO(CourseLessonNote courseLessonNote) {
    	this(courseLessonNote.getId(), courseLessonNote.getCourseId(), courseLessonNote.getUserId(),
    		 courseLessonNote.getContent(), courseLessonNote.getLength(), courseLessonNote.getLikeNum(),
    		 courseLessonNote.getIsPrivate(), courseLessonNote.getCreatedBy(), courseLessonNote.getCreatedDate(),
    		 courseLessonNote.getLastModifiedBy(), courseLessonNote.getLastModifiedDate());
	}
    

	public CourseLessonNoteDTO(Long id, Long courseId, Long userId, String content, Integer length, Integer likeNum,
			String isPrivate, String createdBy, Instant createdDate, String lastModifiedBy, Instant lastModifiedDate) {
		super();
		this.id = id;
		this.courseId = courseId;
		this.userId = userId;
		this.content = content;
		this.length = length;
		this.likeNum = likeNum;
		this.isPrivate = isPrivate;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.lastModifiedBy = lastModifiedBy;
		this.lastModifiedDate = lastModifiedDate;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Instant getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Instant createdDate) {
		this.createdDate = createdDate;
	}

	public String getLastModifiedBy() {
		return lastModifiedBy;
	}

	public void setLastModifiedBy(String lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}

	public Instant getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(Instant lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
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

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public Integer getLikeNum() {
        return likeNum;
    }

    public void setLikeNum(Integer likeNum) {
        this.likeNum = likeNum;
    }

    public String getIsPrivate() {
        return isPrivate;
    }

    public void setIsPrivate(String isPrivate) {
        this.isPrivate = isPrivate;
    }

    @Override
	public String toString() {
		return "CourseLessonNoteDTO [id=" + id + ", courseId=" + courseId + ", userId=" + userId + ", content="
				+ content + ", length=" + length + ", likeNum=" + likeNum + ", isPrivate=" + isPrivate + ", createdBy="
				+ createdBy + ", createdDate=" + createdDate + ", lastModifiedBy=" + lastModifiedBy
				+ ", lastModifiedDate=" + lastModifiedDate + "]";
	}
}
