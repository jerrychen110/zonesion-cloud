package com.zonesion.cloud.service.dto;


import javax.validation.constraints.*;

import com.zonesion.cloud.domain.Chapter;
import com.zonesion.cloud.domain.Course;

import java.io.Serializable;
import java.time.Instant;

/**
 * A DTO for the Chapter entity.
 */
public class ChapterDTO implements Serializable {

    /**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = 1L;

	private Long id;
    
    @NotNull
    private Course course;
    
    @NotNull
    private Long userId;
    
    @NotNull
    private Long parentId;

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
    
    private String createdBy;

    private Instant createdDate;

    private String lastModifiedBy;

    private Instant lastModifiedDate;
    
    public ChapterDTO(){
    	
    }
    public ChapterDTO(Chapter chapter) {
		this(chapter.getId(), chapter.getCourse(), chapter.getUserId(), chapter.getParentId(), chapter.getChapterType(),
			 chapter.getNumber(), chapter.getSeq(), chapter.getTitle(), chapter.getCreatedBy(),
			 chapter.getCreatedDate(), chapter.getLastModifiedBy(), chapter.getLastModifiedDate());
	}
    
	public ChapterDTO(Long id, Course course, Long userId, Long parentId, String chapterType, Integer number, Integer seq,
			String title, String createdBy, Instant createdDate,String lastModifiedBy, Instant lastModifiedDate ) {
		super();
		this.id = id;
		this.course = course;
		this.userId = userId;
		this.parentId = parentId;
		this.chapterType = chapterType;
		this.number = number;
		this.seq = seq;
		this.title = title;
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
    
	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}
    
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
    
    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
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
		return "ChapterDTO [id=" + id + ", course=" + course + ", userId=" + userId + ", parentId=" + parentId + ", chapterType=" + chapterType
				+ ", number=" + number + ", seq=" + seq + ", title=" + title + ", createdBy=" + createdBy
				+ ", createdDate=" + createdDate + ", lastModifiedBy=" + lastModifiedBy + ", lastModifiedDate="
				+ lastModifiedDate + "]";
	}
}
