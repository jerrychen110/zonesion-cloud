package com.zonesion.cloud.service.dto;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.*;

import com.zonesion.cloud.domain.AbstractAuditingEntity;
import com.zonesion.cloud.domain.CourseLessonAttachment;

import java.io.Serializable;
import java.time.Instant;

/**
 * A DTO for the CourseLessonAttachment entity.
 */
@Entity
public class CourseLessonAttachmentDTO extends AbstractAuditingEntity implements Serializable {

    /**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)	
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
       
    private Long filesId;
    private String filesUri;
    private String filesName;
    private String filesMime;
    private Integer filesSize;
    private String filesStatus;
    private String filesCreatedBy;
    private Instant filesCreatedDate;
    private String filesLastModifiedBy;
    private Instant filesLastModifiedDate; 

	private Long courseLessonId;

    private String courseLessonCourseLessonAttachment;
    
    public CourseLessonAttachmentDTO(){
    	
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

    public Long getFilesId() {
		return filesId;
	}

	public void setFilesId(Long filesId) {
		this.filesId = filesId;
	}

	public String getFilesUri() {
		return filesUri;
	}

	public void setFilesUri(String filesUri) {
		this.filesUri = filesUri;
	}

	public String getFilesName() {
		return filesName;
	}

	public void setFilesName(String filesName) {
		this.filesName = filesName;
	}

	public String getFilesMime() {
		return filesMime;
	}

	public void setFilesMime(String filesMime) {
		this.filesMime = filesMime;
	}

	public Integer getFilesSize() {
		return filesSize;
	}

	public void setFilesSize(Integer filesSize) {
		this.filesSize = filesSize;
	}

	public String getFilesStatus() {
		return filesStatus;
	}

	public void setFilesStatus(String filesStatus) {
		this.filesStatus = filesStatus;
	}

	public String getFilesCreatedBy() {
		return filesCreatedBy;
	}

	public void setFilesCreatedBy(String filesCreatedBy) {
		this.filesCreatedBy = filesCreatedBy;
	}

	public Instant getFilesCreatedDate() {
		return filesCreatedDate;
	}

	public void setFilesCreatedDate(Instant filesCreatedDate) {
		this.filesCreatedDate = filesCreatedDate;
	}

	public String getFilesLastModifiedBy() {
		return filesLastModifiedBy;
	}

	public void setFilesLastModifiedBy(String filesLastModifiedBy) {
		this.filesLastModifiedBy = filesLastModifiedBy;
	}

	public Instant getFilesLastModifiedDate() {
		return filesLastModifiedDate;
	}

	public void setFilesLastModifiedDate(Instant filesLastModifiedDate) {
		this.filesLastModifiedDate = filesLastModifiedDate;
	}

	@Override
	public String toString() {
		return "CourseLessonAttachmentDTO [id=" + id + ", courseId=" + courseId + ", userId=" + userId + ", title="
				+ title + ", description=" + description + ", link=" + link + ", fileId=" + fileId + ", fileUri="
				+ fileUri + ", fileMime=" + fileMime + ", fileSize=" + fileSize + ", filesId=" + filesId + ", filesUri="
				+ filesUri + ", filesName=" + filesName + ", filesMime=" + filesMime + ", filesSize=" + filesSize
				+ ", filesStatus=" + filesStatus + ", filesCreatedBy=" + filesCreatedBy + ", filesCreatedDate="
				+ filesCreatedDate + ", filesLastModifiedBy=" + filesLastModifiedBy + ", filesLastModifiedDate="
				+ filesLastModifiedDate + ", courseLessonId=" + courseLessonId + ", courseLessonCourseLessonAttachment="
				+ courseLessonCourseLessonAttachment + "]";
	}
}
