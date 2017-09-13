package com.zonesion.cloud.service.dto;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;

/**
 * A DTO for the CourseLessonNote entity.
 */
@Entity
public class CourseLessonNoteDTO implements Serializable {

    /**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
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
    
    private Long courseLessonId;
    
    private String createdBy;

    private Instant createdDate;

    private String lastModifiedBy;

    private Instant lastModifiedDate;
    
    private Long courseLessonNoteLikeId;
    private Long courseLessonNoteLikeUserId;
    
    private Instant courseLessonNoteLikeCreateTime;
    private Long courseLessonNoteId;
    
    private String avatar;
    private String title;
    
    public CourseLessonNoteDTO(){
    	
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

    public Long getCourseLessonId() {
		return courseLessonId;
	}

	public void setCourseLessonId(Long courseLessonId) {
		this.courseLessonId = courseLessonId;
	}
	

	public Long getCourseLessonNoteLikeId() {
		return courseLessonNoteLikeId;
	}

	public void setCourseLessonNoteLikeId(Long courseLessonNoteLikeId) {
		this.courseLessonNoteLikeId = courseLessonNoteLikeId;
	}

	public Long getCourseLessonNoteLikeUserId() {
		return courseLessonNoteLikeUserId;
	}

	public void setCourseLessonNoteLikeUserId(Long courseLessonNoteLikeUserId) {
		this.courseLessonNoteLikeUserId = courseLessonNoteLikeUserId;
	}

	public Instant getCourseLessonNoteLikeCreateTime() {
		return courseLessonNoteLikeCreateTime;
	}

	public void setCourseLessonNoteLikeCreateTime(Instant courseLessonNoteLikeCreateTime) {
		this.courseLessonNoteLikeCreateTime = courseLessonNoteLikeCreateTime;
	}

	public Long getCourseLessonNoteId() {
		return courseLessonNoteId;
	}

	public void setCourseLessonNoteId(Long courseLessonNoteId) {
		this.courseLessonNoteId = courseLessonNoteId;
	}
	

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		return "CourseLessonNoteDTO [id=" + id + ", courseId=" + courseId + ", userId=" + userId + ", content="
				+ content + ", length=" + length + ", likeNum=" + likeNum + ", isPrivate=" + isPrivate
				+ ", courseLessonId=" + courseLessonId + ", createdBy=" + createdBy + ", createdDate=" + createdDate
				+ ", lastModifiedBy=" + lastModifiedBy + ", lastModifiedDate=" + lastModifiedDate
				+ ", courseLessonNoteLikeId=" + courseLessonNoteLikeId + ", courseLessonNoteLikeUserId="
				+ courseLessonNoteLikeUserId + ", courseLessonNoteLikeCreateTime=" + courseLessonNoteLikeCreateTime
				+ ", courseLessonNoteId=" + courseLessonNoteId + "]";
	}
}
