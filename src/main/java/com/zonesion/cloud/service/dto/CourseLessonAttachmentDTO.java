package com.zonesion.cloud.service.dto;


import javax.validation.constraints.*;

import com.zonesion.cloud.domain.CourseLessonAttachment;

import java.io.Serializable;
import java.time.Instant;

/**
 * A DTO for the CourseLessonAttachment entity.
 */
public class CourseLessonAttachmentDTO implements Serializable {

    /**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = 1L;

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
    
    private String createdBy;

    private Instant createdDate;

    private String lastModifiedBy;

    private Instant lastModifiedDate;

    public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Instant getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Instant createdDate) {
		this.createdDate = createdDate;
	}

	public String getLastModifiedBy() {
		return lastModifiedBy;
	}

	public void setLastModifiedBy(String lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}

	public Instant getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(Instant lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	private Long courseLessonId;

    private String courseLessonCourseLessonAttachment;
    
    public CourseLessonAttachmentDTO(){
    	
    }
    
    public CourseLessonAttachmentDTO(CourseLessonAttachment courseLessonAttachment) {
    	this(courseLessonAttachment.getId(), courseLessonAttachment.getCourseId(),courseLessonAttachment.getUserId(), courseLessonAttachment.getTitle(),
    		 courseLessonAttachment.getDescription(), courseLessonAttachment.getLink(), courseLessonAttachment.getFileId(),
    		 courseLessonAttachment.getFileUri(), courseLessonAttachment.getFileMime(),courseLessonAttachment.getFileSize(), 
    		 courseLessonAttachment.getCreatedBy(),courseLessonAttachment.getCreatedDate(),
    		 courseLessonAttachment.getLastModifiedBy(),courseLessonAttachment.getLastModifiedDate());
	}
    
    

	public CourseLessonAttachmentDTO(Long id, Long courseId, Long userId, String title, String description, String link,
			Long fileId, String fileUri, String fileMime, Integer fileSize, String createdBy, Instant createdDate,
			String lastModifiedBy, Instant lastModifiedDate) {
		super();
		this.id = id;
		this.courseId = courseId;
		this.userId = userId;
		this.title = title;
		this.description = description;
		this.link = link;
		this.fileId = fileId;
		this.fileUri = fileUri;
		this.fileMime = fileMime;
		this.fileSize = fileSize;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.lastModifiedBy = lastModifiedBy;
		this.lastModifiedDate = lastModifiedDate;
	}

	public CourseLessonAttachmentDTO(Long id, Long courseId, Long userId, String title, String description, String link,
			Long fileId, String fileUri, String fileMime, Integer fileSize) {
		super();
		this.id = id;
		this.courseId = courseId;
		this.userId = userId;
		this.title = title;
		this.description = description;
		this.link = link;
		this.fileId = fileId;
		this.fileUri = fileUri;
		this.fileMime = fileMime;
		this.fileSize = fileSize;
	}

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
	public String toString() {
		return "CourseLessonAttachmentDTO [id=" + id + ", courseId=" + courseId + ", userId=" + userId + ", title="
				+ title + ", description=" + description + ", link=" + link + ", fileId=" + fileId + ", fileUri="
				+ fileUri + ", fileMime=" + fileMime + ", fileSize=" + fileSize + ", createdBy=" + createdBy
				+ ", createdDate=" + createdDate + ", lastModifiedBy=" + lastModifiedBy + ", lastModifiedDate="
				+ lastModifiedDate + "]";
	}
}
