package com.zonesion.cloud.service.dto;


import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the CourseLessonNote entity.
 */
public class CourseLessonNoteDTO implements Serializable {

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

    private String courseLessonCourseLessonNote;

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

    public void setCourseLessonId(Long CourseLessonId) {
        this.courseLessonId = CourseLessonId;
    }

    public String getCourseLessonCourseLessonNote() {
        return courseLessonCourseLessonNote;
    }

    public void setCourseLessonCourseLessonNote(String CourseLessonCourseLessonNote) {
        this.courseLessonCourseLessonNote = CourseLessonCourseLessonNote;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CourseLessonNoteDTO courseLessonNoteDTO = (CourseLessonNoteDTO) o;
        if(courseLessonNoteDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), courseLessonNoteDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CourseLessonNoteDTO{" +
            "id=" + getId() +
            ", courseId='" + getCourseId() + "'" +
            ", userId='" + getUserId() + "'" +
            ", content='" + getContent() + "'" +
            ", length='" + getLength() + "'" +
            ", likeNum='" + getLikeNum() + "'" +
            ", isPrivate='" + getIsPrivate() + "'" +
            "}";
    }
}
