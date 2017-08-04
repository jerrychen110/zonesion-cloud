package com.zonesion.cloud.service.dto;


import javax.validation.constraints.*;

import com.zonesion.cloud.domain.File;

import java.io.Serializable;
import java.time.Instant;

/**
 * A DTO for the File entity.
 */
public class FileDTO implements Serializable {

    /**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

    @NotNull
    private Long userId;

    @NotNull
    @Size(max = 255)
    private String uri;

    @NotNull
    @Size(max = 1024)
    private String name;

    @NotNull
    @Size(max = 255)
    private String mime;

    @NotNull
    private Integer size;

    @NotNull
    @Size(max = 1)
    private String status;
    
    private String createdBy;

    private Instant createdDate;

    private String lastModifiedBy;

    private Instant lastModifiedDate;
    
    public FileDTO(){
    	
    }
    public FileDTO(File file) {
		this(file.getId(), file.getUserId(), file.getUri(), file.getName(),
			 file.getMime(), file.getSize(), file.getStatus(), file.getCreatedBy(),
			 file.getCreatedDate(), file.getLastModifiedBy(), file.getLastModifiedDate());
	}
    
    
	public FileDTO(Long id, Long userId, String uri, String name, String mime, Integer size, String status,
			String createdBy, Instant createdDate, String lastModifiedBy, Instant lastModifiedDate) {
		super();
		this.id = id;
		this.userId = userId;
		this.uri = uri;
		this.name = name;
		this.mime = mime;
		this.size = size;
		this.status = status;
		this.createdBy = createdBy;
        this.createdDate = createdDate;
        this.lastModifiedBy = lastModifiedBy;
        this.lastModifiedDate = lastModifiedDate;
	}
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

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMime() {
        return mime;
    }

    public void setMime(String mime) {
        this.mime = mime;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    public String getCreatedBy() {
        return createdBy;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public Instant getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Instant lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }
    

    @Override
	public String toString() {
		return "FileDTO [id=" + id + ", userId=" + userId + ", uri=" + uri + ", name=" + name + ", mime=" + mime
				+ ", size=" + size + ", status=" + status + ", createdBy=" + createdBy + ", createdDate=" + createdDate
				+ ", lastModifiedBy=" + lastModifiedBy + ", lastModifiedDate=" + lastModifiedDate + "]";
	}
}
