package com.zonesion.cloud.service.dto.in;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CourseReviewInDTO {
	
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
    
    public CourseReviewInDTO() {
    	
    }

	public CourseReviewInDTO(Long id, Long userId, String title, String content, Integer rating, String privacy) {
		super();
		this.id = id;
		this.userId = userId;
		this.title = title;
		this.content = content;
		this.rating = rating;
		this.privacy = privacy;
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
    
}
