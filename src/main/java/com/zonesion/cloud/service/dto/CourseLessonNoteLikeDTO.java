package com.zonesion.cloud.service.dto;


import java.time.Instant;
import javax.validation.constraints.*;

import com.zonesion.cloud.domain.CourseLessonNote;
import com.zonesion.cloud.domain.CourseLessonNoteLike;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the CourseLessonNoteLike entity.
 */
public class CourseLessonNoteLikeDTO implements Serializable {

    /**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

    @NotNull
    private Long userId;

    @NotNull
    private Instant createdTime;

    private CourseLessonNote courseLessonNote;
    
    public CourseLessonNoteLikeDTO(){
    	
    }
    
    public CourseLessonNoteLikeDTO(CourseLessonNoteLike courseLessonNoteLike) {
    	this(courseLessonNoteLike.getId(), courseLessonNoteLike.getUserId(), courseLessonNoteLike.getCreatedTime(), courseLessonNoteLike.getCourseLessonNote());
	}
    

	public CourseLessonNoteLikeDTO(Long id, Long userId, Instant createdTime, CourseLessonNote courseLessonNote) {
		super();
		this.id = id;
		this.userId = userId;
		this.createdTime = createdTime;
		this.courseLessonNote = courseLessonNote;
	}

	public CourseLessonNote getCourseLessonNote() {
		return courseLessonNote;
	}

	public void setCourseLessonNote(CourseLessonNote courseLessonNote) {
		this.courseLessonNote = courseLessonNote;
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

    public Instant getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Instant createdTime) {
        this.createdTime = createdTime;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CourseLessonNoteLikeDTO courseLessonNoteLikeDTO = (CourseLessonNoteLikeDTO) o;
        if(courseLessonNoteLikeDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), courseLessonNoteLikeDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
	public String toString() {
		return "CourseLessonNoteLikeDTO [id=" + id + ", userId=" + userId + ", createdTime=" + createdTime
				+ ", courseLessonNote=" + courseLessonNote + "]";
	}
}
