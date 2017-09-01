package com.zonesion.cloud.service.dto;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zonesion.cloud.domain.Chapter;
import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

/**
 * A DTO for the Course entity.
 */
@Entity
public class CourseDTO implements Serializable {

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
    @Size(max = 1)
    private String courseSource;

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
    private Long chapterId;  
    private String chapterType;
    private Integer chapterNumber;
    private Integer chapterSeq;
    private String chapterTitle;
    private String chapterCreatedBy;

    private Instant chapterCreatedDate;

    private String chapterLastModifiedBy;

    private Instant chapterLastModifiedDate;
    
    private Long courseLessonId;
    private Integer courseLessonNumber;
    private Integer courseLessonSeq;
    private String courseLessonTitle;
    private String courseLessonSummary;
    private String courseLessonType;
    private String courseLessonContent;
    private Integer courseLessonCredit;
    private Integer courseLessonMediaId;
    private String courseLessonMediaSource;
    private String courseLessonMediaName;
    private String courseLessonMediaURI;
    private Integer courseLessonLearnedNum;
    private Integer courseLessonViewedNum;
    private String courseLessonCreatedBy;

    private Instant courseLessonCreatedDate;

    private String courseLessonLastModifiedBy;

    private Instant courseLessonLastModifiedDate;
    
    private String userName;
    private String userAvatar;
    private String userEmail;
    private String userMobile;
    private String userSex;
    private String staffNo;
    private String userMajor;
    private String userSchool;
    
    public CourseDTO() {
		// TODO Auto-generated constructor stub
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
    
    public String getCourseSource() {
        return courseSource;
    }

    public void setCourseSource(String courseSource) {
        this.courseSource = courseSource;
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
	
	public Long getChapterId() {
		return chapterId;
	}
	public void setChapterId(Long chapterId) {
		this.chapterId = chapterId;
	}

	public String getChapterType() {
		return chapterType;
	}

	public void setChapterType(String chapterType) {
		this.chapterType = chapterType;
	}

	public Integer getChapterNumber() {
		return chapterNumber;
	}

	public void setChapterNumber(Integer chapterNumber) {
		this.chapterNumber = chapterNumber;
	}

	public Integer getChapterSeq() {
		return chapterSeq;
	}

	public void setChapterSeq(Integer chapterSeq) {
		this.chapterSeq = chapterSeq;
	}

	public String getChapterTitle() {
		return chapterTitle;
	}

	public void setChapterTitle(String chapterTitle) {
		this.chapterTitle = chapterTitle;
	}

	public String getChapterCreatedBy() {
		return chapterCreatedBy;
	}

	public void setChapterCreatedBy(String chapterCreatedBy) {
		this.chapterCreatedBy = chapterCreatedBy;
	}

	public Instant getChapterCreatedDate() {
		return chapterCreatedDate;
	}

	public void setChapterCreatedDate(Instant chapterCreatedDate) {
		this.chapterCreatedDate = chapterCreatedDate;
	}

	public String getChapterLastModifiedBy() {
		return chapterLastModifiedBy;
	}

	public void setChapterLastModifiedBy(String chapterLastModifiedBy) {
		this.chapterLastModifiedBy = chapterLastModifiedBy;
	}

	public Instant getChapterLastModifiedDate() {
		return chapterLastModifiedDate;
	}

	public void setChapterLastModifiedDate(Instant chapterLastModifiedDate) {
		this.chapterLastModifiedDate = chapterLastModifiedDate;
	}

	public Long getCourseLessonId() {
		return courseLessonId;
	}

	public void setCourseLessonId(Long courseLessonId) {
		this.courseLessonId = courseLessonId;
	}

	public Integer getCourseLessonNumber() {
		return courseLessonNumber;
	}

	public void setCourseLessonNumber(Integer courseLessonNumber) {
		this.courseLessonNumber = courseLessonNumber;
	}

	public Integer getCourseLessonSeq() {
		return courseLessonSeq;
	}

	public void setCourseLessonSeq(Integer courseLessonSeq) {
		this.courseLessonSeq = courseLessonSeq;
	}

	public String getCourseLessonTitle() {
		return courseLessonTitle;
	}

	public void setCourseLessonTitle(String courseLessonTitle) {
		this.courseLessonTitle = courseLessonTitle;
	}

	public String getCourseLessonSummary() {
		return courseLessonSummary;
	}

	public void setCourseLessonSummary(String courseLessonSummary) {
		this.courseLessonSummary = courseLessonSummary;
	}

	public String getCourseLessonType() {
		return courseLessonType;
	}

	public void setCourseLessonType(String courseLessonType) {
		this.courseLessonType = courseLessonType;
	}

	public String getCourseLessonContent() {
		return courseLessonContent;
	}

	public void setCourseLessonContent(String courseLessonContent) {
		this.courseLessonContent = courseLessonContent;
	}

	public Integer getCourseLessonCredit() {
		return courseLessonCredit;
	}

	public void setCourseLessonCredit(Integer courseLessonCredit) {
		this.courseLessonCredit = courseLessonCredit;
	}

	public Integer getCourseLessonMediaId() {
		return courseLessonMediaId;
	}

	public void setCourseLessonMediaId(Integer courseLessonMediaId) {
		this.courseLessonMediaId = courseLessonMediaId;
	}

	public String getCourseLessonMediaSource() {
		return courseLessonMediaSource;
	}

	public void setCourseLessonMediaSource(String courseLessonMediaSource) {
		this.courseLessonMediaSource = courseLessonMediaSource;
	}

	public String getCourseLessonMediaName() {
		return courseLessonMediaName;
	}

	public void setCourseLessonMediaName(String courseLessonMediaName) {
		this.courseLessonMediaName = courseLessonMediaName;
	}

	public String getCourseLessonMediaURI() {
		return courseLessonMediaURI;
	}

	public void setCourseLessonMediaURI(String courseLessonMediaURI) {
		this.courseLessonMediaURI = courseLessonMediaURI;
	}

	public Integer getCourseLessonLearnedNum() {
		return courseLessonLearnedNum;
	}

	public void setCourseLessonLearnedNum(Integer courseLessonLearnedNum) {
		this.courseLessonLearnedNum = courseLessonLearnedNum;
	}

	public Integer getCourseLessonViewedNum() {
		return courseLessonViewedNum;
	}

	public void setCourseLessonViewedNum(Integer courseLessonViewedNum) {
		this.courseLessonViewedNum = courseLessonViewedNum;
	}

	public String getCourseLessonCreatedBy() {
		return courseLessonCreatedBy;
	}

	public void setCourseLessonCreatedBy(String courseLessonCreatedBy) {
		this.courseLessonCreatedBy = courseLessonCreatedBy;
	}

	public Instant getCourseLessonCreatedDate() {
		return courseLessonCreatedDate;
	}

	public void setCourseLessonCreatedDate(Instant courseLessonCreatedDate) {
		this.courseLessonCreatedDate = courseLessonCreatedDate;
	}

	public String getCourseLessonLastModifiedBy() {
		return courseLessonLastModifiedBy;
	}

	public void setCourseLessonLastModifiedBy(String courseLessonLastModifiedBy) {
		this.courseLessonLastModifiedBy = courseLessonLastModifiedBy;
	}

	public Instant getCourseLessonLastModifiedDate() {
		return courseLessonLastModifiedDate;
	}

	public void setCourseLessonLastModifiedDate(Instant courseLessonLastModifiedDate) {
		this.courseLessonLastModifiedDate = courseLessonLastModifiedDate;
	}

	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getUserEmail() {
		return userEmail;
	}


	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}


	public String getUserMobile() {
		return userMobile;
	}


	public void setUserMobile(String userMobile) {
		this.userMobile = userMobile;
	}


	public String getUserSex() {
		return userSex;
	}


	public void setUserSex(String userSex) {
		this.userSex = userSex;
	}


	public String getStaffNo() {
		return staffNo;
	}


	public void setStaffNo(String staffNo) {
		this.staffNo = staffNo;
	}


	public String getUserMajor() {
		return userMajor;
	}


	public void setUserMajor(String userMajor) {
		this.userMajor = userMajor;
	}


	public String getUserSchool() {
		return userSchool;
	}


	public void setUserSchool(String userSchool) {
		this.userSchool = userSchool;
	}


	public String getUserAvatar() {
		return userAvatar;
	}


	public void setUserAvatar(String userAvatar) {
		this.userAvatar = userAvatar;
	}


	@Override
	public String toString() {
		return "CourseDTO [id=" + id + ", userId=" + userId + ", title=" + title + ", subTitle=" + subTitle
				+ ", status=" + status + ", courseType=" + courseType + ", courseSource=" + courseSource + ", lessonNum=" + lessonNum + ", credit="
				+ credit + ", coverPicture=" + coverPicture + ", introduction=" + introduction + ", goals=" + goals
				+ ", recommended=" + recommended + ", recommendedSort=" + recommendedSort + ", createdBy=" + createdBy
				+ ", createdDate=" + createdDate + ", lastModifiedBy=" + lastModifiedBy + ", lastModifiedDate="
				+ lastModifiedDate + ", chapterId=" + chapterId + ", chapterType=" + chapterType + ", chapterNumber="
				+ chapterNumber + ", chapterSeq=" + chapterSeq + ", chapterTitle=" + chapterTitle
				+ ", chapterCreatedBy=" + chapterCreatedBy + ", chapterCreatedDate=" + chapterCreatedDate
				+ ", chapterLastModifiedBy=" + chapterLastModifiedBy + ", chapterLastModifiedDate="
				+ chapterLastModifiedDate + ", courseLessonId=" + courseLessonId + ", courseLessonNumber="
				+ courseLessonNumber + ", courseLessonSeq=" + courseLessonSeq + ", courseLessonTitle="
				+ courseLessonTitle + ", courseLessonSummary=" + courseLessonSummary + ", courseLessonType="
				+ courseLessonType + ", courseLessonContent=" + courseLessonContent + ", courseLessonCredit="
				+ courseLessonCredit + ", courseLessonMediaId=" + courseLessonMediaId + ", courseLessonMediaSource="
				+ courseLessonMediaSource + ", courseLessonMediaName=" + courseLessonMediaName
				+ ", courseLessonMediaURI=" + courseLessonMediaURI + ", courseLessonLearnedNum="
				+ courseLessonLearnedNum + ", courseLessonViewedNum=" + courseLessonViewedNum
				+ ", courseLessonCreatedBy=" + courseLessonCreatedBy + ", courseLessonCreatedDate="
				+ courseLessonCreatedDate + ", courseLessonLastModifiedBy=" + courseLessonLastModifiedBy
				+ ", courseLessonLastModifiedDate=" + courseLessonLastModifiedDate + ", userName=" + userName
				+ ", userAvatar=" + userAvatar + ", userEmail=" + userEmail + ", userMobile=" + userMobile
				+ ", userSex=" + userSex + ", staffNo=" + staffNo + ", userMajor=" + userMajor + ", userSchool="
				+ userSchool + "]";
	}
}
