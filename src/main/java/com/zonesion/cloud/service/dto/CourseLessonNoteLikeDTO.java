package com.zonesion.cloud.service.dto;


import java.time.Instant;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the CourseLessonNoteLike entity.
 */
public class CourseLessonNoteLikeDTO implements Serializable {

    private Long id;

    @NotNull
    private Long userId;

    @NotNull
    private Instant createdTime;

    private Long courseLessonNoteId;

    private String courseLessonNoteCourseLessonNoteLike;

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

    public Long getCourseLessonNoteId() {
        return courseLessonNoteId;
    }

    public void setCourseLessonNoteId(Long CourseLessonNoteId) {
        this.courseLessonNoteId = CourseLessonNoteId;
    }

    public String getCourseLessonNoteCourseLessonNoteLike() {
        return courseLessonNoteCourseLessonNoteLike;
    }

    public void setCourseLessonNoteCourseLessonNoteLike(String CourseLessonNoteCourseLessonNoteLike) {
        this.courseLessonNoteCourseLessonNoteLike = CourseLessonNoteCourseLessonNoteLike;
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
        return "CourseLessonNoteLikeDTO{" +
            "id=" + getId() +
            ", userId='" + getUserId() + "'" +
            ", createdTime='" + getCreatedTime() + "'" +
            "}";
    }
}
