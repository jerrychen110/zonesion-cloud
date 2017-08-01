package com.zonesion.cloud.service.dto;


import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the CourseReview entity.
 */
public class CourseReviewDTO implements Serializable {

    private Long id;

    @NotNull
    private Long userId;

    @NotNull
    @Size(max = 255)
    private String title;

    @NotNull
    private String content;

    @NotNull
    private Integer rating;

    @NotNull
    @Size(max = 1)
    private String privacy;

    private Long courseId;

    private String courseCourseReview;

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getPrivacy() {
        return privacy;
    }

    public void setPrivacy(String privacy) {
        this.privacy = privacy;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long CourseId) {
        this.courseId = CourseId;
    }

    public String getCourseCourseReview() {
        return courseCourseReview;
    }

    public void setCourseCourseReview(String CourseCourseReview) {
        this.courseCourseReview = CourseCourseReview;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CourseReviewDTO courseReviewDTO = (CourseReviewDTO) o;
        if(courseReviewDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), courseReviewDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CourseReviewDTO{" +
            "id=" + getId() +
            ", userId='" + getUserId() + "'" +
            ", title='" + getTitle() + "'" +
            ", content='" + getContent() + "'" +
            ", rating='" + getRating() + "'" +
            ", privacy='" + getPrivacy() + "'" +
            "}";
    }
}
