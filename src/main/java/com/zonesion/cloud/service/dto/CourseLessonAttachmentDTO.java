package com.zonesion.cloud.service.dto;


import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the CourseLessonAttachment entity.
 */
public class CourseLessonAttachmentDTO implements Serializable {

    private Long id;

    @NotNull
    private Long courseId;

    @NotNull
    private Long userId;

    @NotNull
    @Size(max = 255)
    private String title;

    private String description;

    @Size(max = 1024)
    private String link;

    private Long fileId;

    @Size(max = 1024)
    private String fileUri;

    @Size(max = 255)
    private String fileMime;

    @NotNull
    private Integer fileSize;

    private Long courseLessonId;

    private String courseLessonCourseLessonAttachment;

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Long getFileId() {
        return fileId;
    }

    public void setFileId(Long fileId) {
        this.fileId = fileId;
    }

    public String getFileUri() {
        return fileUri;
    }

    public void setFileUri(String fileUri) {
        this.fileUri = fileUri;
    }

    public String getFileMime() {
        return fileMime;
    }

    public void setFileMime(String fileMime) {
        this.fileMime = fileMime;
    }

    public Integer getFileSize() {
        return fileSize;
    }

    public void setFileSize(Integer fileSize) {
        this.fileSize = fileSize;
    }

    public Long getCourseLessonId() {
        return courseLessonId;
    }

    public void setCourseLessonId(Long CourseLessonId) {
        this.courseLessonId = CourseLessonId;
    }

    public String getCourseLessonCourseLessonAttachment() {
        return courseLessonCourseLessonAttachment;
    }

    public void setCourseLessonCourseLessonAttachment(String CourseLessonCourseLessonAttachment) {
        this.courseLessonCourseLessonAttachment = CourseLessonCourseLessonAttachment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CourseLessonAttachmentDTO courseLessonAttachmentDTO = (CourseLessonAttachmentDTO) o;
        if(courseLessonAttachmentDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), courseLessonAttachmentDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CourseLessonAttachmentDTO{" +
            "id=" + getId() +
            ", courseId='" + getCourseId() + "'" +
            ", userId='" + getUserId() + "'" +
            ", title='" + getTitle() + "'" +
            ", description='" + getDescription() + "'" +
            ", link='" + getLink() + "'" +
            ", fileId='" + getFileId() + "'" +
            ", fileUri='" + getFileUri() + "'" +
            ", fileMime='" + getFileMime() + "'" +
            ", fileSize='" + getFileSize() + "'" +
            "}";
    }
}
