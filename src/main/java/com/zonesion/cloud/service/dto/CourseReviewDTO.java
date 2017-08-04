package com.zonesion.cloud.service.dto;


import javax.validation.constraints.*;

import com.zonesion.cloud.domain.CourseReview;

import java.io.Serializable;
import java.time.Instant;

/**
 * A DTO for the CourseReview entity.
 */
public class CourseReviewDTO implements Serializable {

    /**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

    @NotNull
    private Long userId;

    @NotNull
    @Size(max = 255)
    private String title;

    @NotNull
    private String content;

    @NotNull
    private Integer rating;

    @NotNull
    @Size(max = 1)
    private String privacy;
    
    private String createdBy;

    private Instant createdDate;

    private String lastModifiedBy;

    private Instant lastModifiedDate;

    public CourseReviewDTO(){
    	
    }
    
    public CourseReviewDTO(CourseReview courseReview) {
		this(courseReview.getId(), courseReview.getUserId(), courseReview.getTitle(), courseReview.getContent(),
			 courseReview.getRating(), courseReview.getPrivacy(), courseReview.getCreatedBy(), courseReview.getCreatedDate(),
			 courseReview.getLastModifiedBy(),courseReview.getLastModifiedDate());
	}
    
    

	public CourseReviewDTO(Long id, Long userId, String title, String content, Integer rating, String privacy,
			String createdBy, Instant createdDate, String lastModifiedBy, Instant lastModifiedDate) {
		super();
		this.id = id;
		this.userId = userId;
		this.title = title;
		this.content = content;
		this.rating = rating;
		this.privacy = privacy;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getPrivacy() {
        return privacy;
    }

    public void setPrivacy(String privacy) {
        this.privacy = privacy;
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

    @Override
	public String toString() {
		return "CourseReviewDTO [id=" + id + ", userId=" + userId + ", title=" + title + ", content=" + content
				+ ", rating=" + rating + ", privacy=" + privacy + ", createdBy=" + createdBy + ", createdDate="
				+ createdDate + ", lastModifiedBy=" + lastModifiedBy + ", lastModifiedDate=" + lastModifiedDate + "]";
	}
}
