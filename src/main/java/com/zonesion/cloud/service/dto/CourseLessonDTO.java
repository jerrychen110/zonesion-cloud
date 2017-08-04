package com.zonesion.cloud.service.dto;


import javax.validation.constraints.*;

import com.zonesion.cloud.domain.CourseLesson;

import java.io.Serializable;
import java.time.Instant;

/**
 * A DTO for the CourseLesson entity.
 */
public class CourseLessonDTO implements Serializable {

    /**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = 1L;

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
    
    private String createdBy;

    private Instant createdDate;

    private String lastModifiedBy;

    private Instant lastModifiedDate;
    
    public CourseLessonDTO(){
    	
    }
    
    public CourseLessonDTO(CourseLesson courseLesson) {
		this(courseLesson.getId(), courseLesson.getUserId(), courseLesson.getNumber(), courseLesson.getSeq(),
			 courseLesson.getTitle(), courseLesson.getSummary(), courseLesson.getCourseLessonType(),
			 courseLesson.getContent(), courseLesson.getCredit(), courseLesson.getMediaId(),courseLesson.getMediaSource(),
			 courseLesson.getMediaName(), courseLesson.getMediaUri(), courseLesson.getLearnedNum(), courseLesson.getViewedNum(),
			 courseLesson.getCreatedBy(), courseLesson.getCreatedDate(), courseLesson.getLastModifiedBy(), courseLesson.getLastModifiedDate());
	}
    
	public CourseLessonDTO(Long id, Long userId, Integer number, Integer seq, String title, String summary,
			String courseLessonType, String content, Integer credit, Integer mediaId, String mediaSource,
			String mediaName, String mediaUri, Integer learnedNum, Integer viewedNum, String createdBy,
			Instant createdDate, String lastModifiedBy, Instant lastModifiedDate) {
		super();
		this.id = id;
		this.userId = userId;
		this.number = number;
		this.seq = seq;
		this.title = title;
		this.summary = summary;
		this.courseLessonType = courseLessonType;
		this.content = content;
		this.credit = credit;
		this.mediaId = mediaId;
		this.mediaSource = mediaSource;
		this.mediaName = mediaName;
		this.mediaUri = mediaUri;
		this.learnedNum = learnedNum;
		this.viewedNum = viewedNum;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.lastModifiedBy = lastModifiedBy;
		this.lastModifiedDate = lastModifiedDate;
	}

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

    @Override
	public String toString() {
		return "CourseLessonDTO [id=" + id + ", userId=" + userId + ", number=" + number + ", seq=" + seq + ", title="
				+ title + ", summary=" + summary + ", courseLessonType=" + courseLessonType + ", content=" + content
				+ ", credit=" + credit + ", mediaId=" + mediaId + ", mediaSource=" + mediaSource + ", mediaName="
				+ mediaName + ", mediaUri=" + mediaUri + ", learnedNum=" + learnedNum + ", viewedNum=" + viewedNum
				+ ", createdBy=" + createdBy + ", createdDate=" + createdDate + ", lastModifiedBy=" + lastModifiedBy
				+ ", lastModifiedDate=" + lastModifiedDate + "]";
	}
}
