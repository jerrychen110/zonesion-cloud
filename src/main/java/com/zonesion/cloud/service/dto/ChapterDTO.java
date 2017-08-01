package com.zonesion.cloud.service.dto;


import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the Chapter entity.
 */
public class ChapterDTO implements Serializable {

    private Long id;

    @NotNull
    private Long userId;

    @NotNull
    @Size(max = 1)
    private String chapterType;

    @NotNull
    @Size(max = 1)
    private Integer number;

    @NotNull
    @Size(max = 1)
    private Integer seq;

    @NotNull
    @Size(max = 255)
    private String title;

    private Long courseId;

    private String courseChapter;

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

    public String getChapterType() {
        return chapterType;
    }

    public void setChapterType(String chapterType) {
        this.chapterType = chapterType;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long CourseId) {
        this.courseId = CourseId;
    }

    public String getCourseChapter() {
        return courseChapter;
    }

    public void setCourseChapter(String CourseChapter) {
        this.courseChapter = CourseChapter;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ChapterDTO chapterDTO = (ChapterDTO) o;
        if(chapterDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), chapterDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ChapterDTO{" +
            "id=" + getId() +
            ", userId='" + getUserId() + "'" +
            ", chapterType='" + getChapterType() + "'" +
            ", number='" + getNumber() + "'" +
            ", seq='" + getSeq() + "'" +
            ", title='" + getTitle() + "'" +
            "}";
    }
}
