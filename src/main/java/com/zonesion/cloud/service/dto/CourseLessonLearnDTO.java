package com.zonesion.cloud.service.dto;


import javax.validation.constraints.*;

import com.zonesion.cloud.domain.CourseLessonLearn;

import java.io.Serializable;
import java.time.Instant;

/**
 * A DTO for the CourseLessonLearn entity.
 */
public class CourseLessonLearnDTO implements Serializable {

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
    private Long durationId;

    @NotNull
    @Size(max = 1)
    private String isComplete;
    
    private String createdBy;

    private Instant createdDate;

    private String lastModifiedBy;

    private Instant lastModifiedDate;

    public CourseLessonLearnDTO(){
    	
    }
    
    public CourseLessonLearnDTO(CourseLessonLearn courseLessonLearn) {
		this(courseLessonLearn.getId(), courseLessonLearn.getCourseId(), courseLessonLearn.getUserId(),
			 courseLessonLearn.getDurationId(), courseLessonLearn.getIsComplete(), courseLessonLearn.getCreatedBy(),
			 courseLessonLearn.getCreatedDate(), courseLessonLearn.getLastModifiedBy(), courseLessonLearn.getLastModifiedDate());
	}
    

	public CourseLessonLearnDTO(Long id, Long courseId, Long userId, Long durationId, String isComplete,
			String createdBy, Instant createdDate, String lastModifiedBy, Instant lastModifiedDate) {
		super();
		this.id = id;
		this.courseId = courseId;
		this.userId = userId;
		this.durationId = durationId;
		this.isComplete = isComplete;
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

    public Long getDurationId() {
        return durationId;
    }

    public void setDurationId(Long durationId) {
        this.durationId = durationId;
    }

    public String getIsComplete() {
        return isComplete;
    }

    public void setIsComplete(String isComplete) {
        this.isComplete = isComplete;
    }

    @Override
	public String toString() {
		return "CourseLessonLearnDTO [id=" + id + ", courseId=" + courseId + ", userId=" + userId + ", durationId="
				+ durationId + ", isComplete=" + isComplete + ", createdBy=" + createdBy + ", createdDate="
				+ createdDate + ", lastModifiedBy=" + lastModifiedBy + ", lastModifiedDate=" + lastModifiedDate + "]";
	}
}
