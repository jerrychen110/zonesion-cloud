package com.zonesion.cloud.service.dto;


import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the CourseFavorite entity.
 */
public class CourseFavoriteDTO implements Serializable {

    private Long id;

    @NotNull
    private Long userId;

    private Long courseId;

    private String courseCourseFavorite;

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

    public void setCourseId(Long CourseId) {
        this.courseId = CourseId;
    }

    public String getCourseCourseFavorite() {
        return courseCourseFavorite;
    }

    public void setCourseCourseFavorite(String CourseCourseFavorite) {
        this.courseCourseFavorite = CourseCourseFavorite;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CourseFavoriteDTO courseFavoriteDTO = (CourseFavoriteDTO) o;
        if(courseFavoriteDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), courseFavoriteDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CourseFavoriteDTO{" +
            "id=" + getId() +
            ", userId='" + getUserId() + "'" +
            "}";
    }
}
