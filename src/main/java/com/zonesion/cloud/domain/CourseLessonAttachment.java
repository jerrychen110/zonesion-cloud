package com.zonesion.cloud.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A CourseLessonAttachment.
 */
@Entity
@Table(name = "t_course_lesson_attachment")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class CourseLessonAttachment extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "target_type", length = 50, nullable = false)
    private String targetType;
    
    @NotNull
    @Column(name = "target_id", nullable = false)
    private Long targetId;

    @NotNull
    @Column(name = "user_id", nullable = false)
    private Long userId;

    @NotNull
    @Size(max = 255)
    @Column(name = "title", length = 255, nullable = false)
    private String title;

    @Column(name = "description")
    private String description;

    @Size(max = 1024)
    @Column(name = "link", length = 1024)
    private String link;

    @NotNull
    @Column(name = "file_type")
    private Integer fileType;
    
    @NotNull
    @Column(name = "file_length")
    private Integer fileLength;

    @Size(max = 1024)
    @Column(name = "file_uri", length = 1024)
    private String fileUri;

    @Size(max = 255)
    @Column(name = "file_mime", length = 255)
    private String fileMime;

    @NotNull
    @Column(name = "file_size", nullable = false)
    private Integer fileSize;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTargetId() {
        return targetId;
    }

    public CourseLessonAttachment targetId(Long targetId) {
        this.targetId = targetId;
        return this;
    }

    public void setTargetId(Long targetId) {
        this.targetId = targetId;
    }
    
    public String getTargetType() {
        return targetType;
    }

    public CourseLessonAttachment targetType(String targetType) {
        this.targetType = targetType;
        return this;
    }

    public void setTargetType(String targetType) {
        this.targetType = targetType;
    }

    public Long getUserId() {
        return userId;
    }

    public CourseLessonAttachment userId(Long userId) {
        this.userId = userId;
        return this;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public CourseLessonAttachment title(String title) {
        this.title = title;
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public CourseLessonAttachment description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLink() {
        return link;
    }

    public CourseLessonAttachment link(String link) {
        this.link = link;
        return this;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Integer getFileType() {
        return fileType;
    }

    public CourseLessonAttachment fileType(Integer fileType) {
        this.fileType = fileType;
        return this;
    }

    public void setFileType(Integer fileType) {
        this.fileType = fileType;
    }

    public String getFileUri() {
        return fileUri;
    }

    public CourseLessonAttachment fileUri(String fileUri) {
        this.fileUri = fileUri;
        return this;
    }

    public void setFileUri(String fileUri) {
        this.fileUri = fileUri;
    }

    public String getFileMime() {
        return fileMime;
    }

    public CourseLessonAttachment fileMime(String fileMime) {
        this.fileMime = fileMime;
        return this;
    }

    public void setFileMime(String fileMime) {
        this.fileMime = fileMime;
    }

    public Integer getFileSize() {
        return fileSize;
    }

    public CourseLessonAttachment fileSize(Integer fileSize) {
        this.fileSize = fileSize;
        return this;
    }

    public void setFileSize(Integer fileSize) {
        this.fileSize = fileSize;
    }
    
    public Integer getFileLength() {
        return fileLength;
    }

    public CourseLessonAttachment fileLength(Integer fileLength) {
        this.fileSize = fileLength;
        return this;
    }

    public void setFileLength(Integer fileLength) {
        this.fileLength = fileLength;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CourseLessonAttachment courseLessonAttachment = (CourseLessonAttachment) o;
        if (courseLessonAttachment.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), courseLessonAttachment.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CourseLessonAttachment{" +
            "id=" + getId() +
            ", targetType='" + getTargetType() + "'" +
            ", targetId='" + getTargetId() + "'" +
            ", userId='" + getUserId() + "'" +
            ", title='" + getTitle() + "'" +
            ", description='" + getDescription() + "'" +
            ", link='" + getLink() + "'" +
            ", fileType='" + getFileType() + "'" +
            ", fileLength='" + getFileLength() + "'" +
            ", fileUri='" + getFileUri() + "'" +
            ", fileMime='" + getFileMime() + "'" +
            ", fileSize='" + getFileSize() + "'" +
            "}";
    }
}
