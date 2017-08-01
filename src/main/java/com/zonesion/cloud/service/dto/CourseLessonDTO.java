package com.zonesion.cloud.service.dto;


import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the CourseLesson entity.
 */
public class CourseLessonDTO implements Serializable {

    private Long id;

    @NotNull
    private Long userId;

    @NotNull
    private Integer number;

    @NotNull
    private Integer seq;

    @NotNull
    @Size(max = 255)
    private String title;

    private String summary;

    @NotNull
    @Size(max = 1)
    private String courseLessonType;

    private String content;

    @NotNull
    private Integer credit;

    @NotNull
    private Integer mediaId;

    @Size(max = 1)
    private String mediaSource;

    @Size(max = 255)
    private String mediaName;

    private String mediaUri;

    @NotNull
    private Integer learnedNum;

    @NotNull
    private Integer viewedNum;

    private Long courseLesson_FKId;

    private String courseLesson_FKCourseLesson;

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

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getCourseLessonType() {
        return courseLessonType;
    }

    public void setCourseLessonType(String courseLessonType) {
        this.courseLessonType = courseLessonType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getCredit() {
        return credit;
    }

    public void setCredit(Integer credit) {
        this.credit = credit;
    }

    public Integer getMediaId() {
        return mediaId;
    }

    public void setMediaId(Integer mediaId) {
        this.mediaId = mediaId;
    }

    public String getMediaSource() {
        return mediaSource;
    }

    public void setMediaSource(String mediaSource) {
        this.mediaSource = mediaSource;
    }

    public String getMediaName() {
        return mediaName;
    }

    public void setMediaName(String mediaName) {
        this.mediaName = mediaName;
    }

    public String getMediaUri() {
        return mediaUri;
    }

    public void setMediaUri(String mediaUri) {
        this.mediaUri = mediaUri;
    }

    public Integer getLearnedNum() {
        return learnedNum;
    }

    public void setLearnedNum(Integer learnedNum) {
        this.learnedNum = learnedNum;
    }

    public Integer getViewedNum() {
        return viewedNum;
    }

    public void setViewedNum(Integer viewedNum) {
        this.viewedNum = viewedNum;
    }

    public Long getCourseLesson_FKId() {
        return courseLesson_FKId;
    }

    public void setCourseLesson_FKId(Long ChapterId) {
        this.courseLesson_FKId = ChapterId;
    }

    public String getCourseLesson_FKCourseLesson() {
        return courseLesson_FKCourseLesson;
    }

    public void setCourseLesson_FKCourseLesson(String ChapterCourseLesson) {
        this.courseLesson_FKCourseLesson = ChapterCourseLesson;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CourseLessonDTO courseLessonDTO = (CourseLessonDTO) o;
        if(courseLessonDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), courseLessonDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CourseLessonDTO{" +
            "id=" + getId() +
            ", userId='" + getUserId() + "'" +
            ", number='" + getNumber() + "'" +
            ", seq='" + getSeq() + "'" +
            ", title='" + getTitle() + "'" +
            ", summary='" + getSummary() + "'" +
            ", courseLessonType='" + getCourseLessonType() + "'" +
            ", content='" + getContent() + "'" +
            ", credit='" + getCredit() + "'" +
            ", mediaId='" + getMediaId() + "'" +
            ", mediaSource='" + getMediaSource() + "'" +
            ", mediaName='" + getMediaName() + "'" +
            ", mediaUri='" + getMediaUri() + "'" +
            ", learnedNum='" + getLearnedNum() + "'" +
            ", viewedNum='" + getViewedNum() + "'" +
            "}";
    }
}
