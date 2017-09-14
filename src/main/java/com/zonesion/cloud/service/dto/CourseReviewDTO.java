package com.zonesion.cloud.service.dto;


import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.*;

import com.zonesion.cloud.domain.AbstractAuditingEntity;
import com.zonesion.cloud.domain.CourseReview;

import java.io.Serializable;
import java.time.Instant;

/**
 * A DTO for the CourseReview entity.
 */
public class CourseReviewDTO extends AbstractAuditingEntity implements Serializable {

    /**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
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
    
    private Long courseId;
    
/*    private String createdBy;

    private Instant createdDate;

    private String lastModifiedBy;

    private Instant lastModifiedDate;*/
    //private String avatar;
    
    public CourseReviewDTO(){
    	
    }
    
    
	public CourseReviewDTO(Long id, Long userId, String title, String content, Integer rating, String privacy,
			Long courseId) {
		super();
		this.id = id;
		this.userId = userId;
		this.title = title;
		this.content = content;
		this.rating = rating;
		this.privacy = privacy;
		this.courseId = courseId;
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
    
    public Long getCourseId() {
		return courseId;
	}

	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}

	@Override
	public String toString() {
		return "CourseReviewDTO [id=" + id + ", userId=" + userId + ", title=" + title + ", content=" + content
				+ ", rating=" + rating + ", privacy=" + privacy + ", courseId=" + courseId + "]";
	}
}
