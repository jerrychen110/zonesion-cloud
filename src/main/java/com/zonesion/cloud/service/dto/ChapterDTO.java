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
    private Course courseId;
    
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
    
    private String createdBy;

    private Instant createdDate;

    private String lastModifiedBy;

    private Instant lastModifiedDate;
    
    public ChapterDTO(){
    	
    }
    public ChapterDTO(Chapter chapter) {
		this(chapter.getId(), chapter.getCourseId(), chapter.getUserId(), chapter.getChapterType(),
			 chapter.getNumber(), chapter.getSeq(), chapter.getTitle(), chapter.getCreatedBy(),
			 chapter.getCreatedDate(), chapter.getLastModifiedBy(), chapter.getLastModifiedDate());
	}
    
	public ChapterDTO(Long id, Course courseId, Long userId, String chapterType, Integer number, Integer seq,
			String title, String createdBy, Instant createdDate,String lastModifiedBy, Instant lastModifiedDate ) {
		super();
		this.id = id;
		this.courseId = courseId;
		this.userId = userId;
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
    
	public Course getCourseId() {
		return courseId;
	}

	public void setCourseId(Course courseId) {
		this.courseId = courseId;
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
		return "ChapterDTO [id=" + id + ", courseId=" + courseId + ", userId=" + userId + ", chapterType=" + chapterType
				+ ", number=" + number + ", seq=" + seq + ", title=" + title + ", createdBy=" + createdBy
				+ ", createdDate=" + createdDate + ", lastModifiedBy=" + lastModifiedBy + ", lastModifiedDate="
				+ lastModifiedDate + "]";
	}
}
