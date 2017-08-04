package com.zonesion.cloud.service.dto;


import javax.validation.constraints.*;

import com.zonesion.cloud.domain.Course;

import java.io.Serializable;
import java.time.Instant;

/**
 * A DTO for the Course entity.
 */
public class CourseDTO implements Serializable {

    /**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

    @NotNull
    private Long userId;

    @NotNull
    @Size(max = 1024)
    private String title;

    @Size(max = 1024)
    private String subTitle;

    @NotNull
    @Size(max = 1)
    private String status;

    @NotNull
    @Size(max = 1)
    private String courseType;

    @NotNull
    @Size(max = 10)
    private Integer lessonNum;

    @NotNull
    @Size(max = 10)
    private String credit;

    @NotNull
    @Size(max = 255)
    private String coverPicture;

    private String introduction;

    private String goals;

    @NotNull
    @Size(max = 1)
    private String recommended;

    @NotNull
    @Size(max = 1)
    private String recommendedSort;
    
    private String createdBy;

    private Instant createdDate;

    private String lastModifiedBy;

    private Instant lastModifiedDate;

    public CourseDTO() {
		// TODO Auto-generated constructor stub
	}
    public CourseDTO(Course course){
    	this(course.getId(), course.getUserId(), course.getTitle(),course.getSubTitle(), 
    		 course.getStatus(), course.getCourseType(), course.getLessonNum(),
    		 course.getCredit(), course.getCoverPicture(), course.getIntroduction(),course.getGoals(),
    		 course.getRecommended(), course.getRecommendedSort());
    }
	public CourseDTO(Long id, Long userId, String title, String subTitle, String status, String courseType,
			Integer lessonNum, String credit, String coverPicture, String introduction, String goals,
			String recommended, String recommendedSort) {
		super();
		this.id = id;
		this.userId = userId;
		this.title = title;
		this.subTitle = subTitle;
		this.status = status;
		this.courseType = courseType;
		this.lessonNum = lessonNum;
		this.credit = credit;
		this.coverPicture = coverPicture;
		this.introduction = introduction;
		this.goals = goals;
		this.recommended = recommended;
		this.recommendedSort = recommendedSort;
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

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCourseType() {
        return courseType;
    }

    public void setCourseType(String courseType) {
        this.courseType = courseType;
    }

    public Integer getLessonNum() {
        return lessonNum;
    }

    public void setLessonNum(Integer lessonNum) {
        this.lessonNum = lessonNum;
    }

    public String getCredit() {
        return credit;
    }

    public void setCredit(String credit) {
        this.credit = credit;
    }

    public String getCoverPicture() {
        return coverPicture;
    }

    public void setCoverPicture(String coverPicture) {
        this.coverPicture = coverPicture;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getGoals() {
        return goals;
    }

    public void setGoals(String goals) {
        this.goals = goals;
    }

    public String getRecommended() {
        return recommended;
    }

    public void setRecommended(String recommended) {
        this.recommended = recommended;
    }

    public String getRecommendedSort() {
        return recommendedSort;
    }

    public void setRecommendedSort(String recommendedSort) {
        this.recommendedSort = recommendedSort;
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
		return "CourseDTO [id=" + id + ", userId=" + userId + ", title=" + title + ", subTitle=" + subTitle
				+ ", status=" + status + ", courseType=" + courseType + ", lessonNum=" + lessonNum + ", credit="
				+ credit + ", coverPicture=" + coverPicture + ", introduction=" + introduction + ", goals=" + goals
				+ ", recommended=" + recommended + ", recommendedSort=" + recommendedSort + ", createdBy=" + createdBy
				+ ", createdDate=" + createdDate + ", lastModifiedBy=" + lastModifiedBy + ", lastModifiedDate="
				+ lastModifiedDate + "]";
	}
}
