package com.zonesion.cloud.service.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.*;

import com.zonesion.cloud.domain.AbstractAuditingEntity;

import java.io.Serializable;

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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	private String targetType;

	@NotNull
	private Long targetId;

	@NotNull
	private Long userId;

	@NotNull
	@Size(max = 255)
	private String title;

	private String description;

	@Size(max = 1024)
	private String link;

	@NotNull
	private Integer fileType;

	@NotNull
	private Integer fileLength;

	@Size(max = 1024)
	private String fileUri;

	@Size(max = 255)
	private String fileMime;

	@NotNull
	private Integer fileSize;

	public CourseLessonAttachmentDTO() {

	}

	public CourseLessonAttachmentDTO(Long id, String targetType, Long targetId, Long userId, String title, String description, String link,
			Integer fileType, Integer fileLength, String fileUri, String fileMime, Integer fileSize) {
		super();
		this.id = id;
		this.targetType = targetType;
		this.targetId = targetId;
		this.userId = userId;
		this.title = title;
		this.description = description;
		this.link = link;
		this.fileType = fileType;
		this.fileLength = fileLength;
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

	public String getTargetType() {
		return targetType;
	}

	public void setTargetType(String targetType) {
		this.targetType = targetType;
	}

	public Long getTargetId() {
		return targetId;
	}

	public void setTargetId(Long targetId) {
		this.targetId = targetId;
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

	public Integer getFileType() {
		return fileType;
	}

	public void setFileType(Integer fileType) {
		this.fileType = fileType;
	}

	public Integer getFileLength() {
		return fileLength;
	}

	public void setFileLength(Integer fileLength) {
		this.fileLength = fileLength;
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

	@Override
	public String toString() {
		return "CourseLessonAttachmentDTO ["
				+ "id=" + id
				+ ", targetType=" + targetType
				+  ", targetId=" + targetId
				+ ", userId=" + userId
				+ ", title=" + title
				+ ", description=" + description
				+ ", link=" + link
				+ ", fileType=" + fileType
				+  ", fileLength=" + fileLength
				+  ", fileUri=" + fileUri
				+  ", fileMime=" + fileMime
				+  ", fileSize=" + fileSize
				+ "]";
	}
}
