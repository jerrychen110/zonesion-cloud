package com.zonesion.cloud.service.dto;


import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the CourseLessonLearn entity.
 */
public class CourseLessonLearnDTO implements Serializable {

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

    private Long courseLessonId;

    private String courseLessonCourseLessonLearn;

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

    public Long getCourseLessonId() {
        return courseLessonId;
    }

    public void setCourseLessonId(Long CourseLessonId) {
        this.courseLessonId = CourseLessonId;
    }

    public String getCourseLessonCourseLessonLearn() {
        return courseLessonCourseLessonLearn;
    }

    public void setCourseLessonCourseLessonLearn(String CourseLessonCourseLessonLearn) {
        this.courseLessonCourseLessonLearn = CourseLessonCourseLessonLearn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CourseLessonLearnDTO courseLessonLearnDTO = (CourseLessonLearnDTO) o;
        if(courseLessonLearnDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), courseLessonLearnDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CourseLessonLearnDTO{" +
            "id=" + getId() +
            ", courseId='" + getCourseId() + "'" +
            ", userId='" + getUserId() + "'" +
            ", durationId='" + getDurationId() + "'" +
            ", isComplete='" + getIsComplete() + "'" +
            "}";
    }
}
