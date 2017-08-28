package com.zonesion.cloud.service.dto;

import com.zonesion.cloud.config.Constants;

import com.zonesion.cloud.domain.Authority;
import com.zonesion.cloud.domain.User;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.*;
import java.time.Instant;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * A DTO representing a user, with his authorities.
 */
public class UserInfoDTO {

    private Long id;

    @NotBlank
    @Pattern(regexp = Constants.LOGIN_REGEX)
    @Size(min = 1, max = 50)
    private String login;

    @Size(max = 50)
    private String name;

    @Size(max = 256)
    private String avatar;
    
    @Email
    @Size(min = 5, max = 100)
    private String email;
    
    @Size(max = 11)
    private String mobile;
    
    @Size(max = 1)
    private String sex;
    
    @Size(max = 12)
    private String staffNo;
    
    @Size(max = 50)
    private String major;
    
    @Size(max = 50)
    private String school;

    private boolean activated = false;

    private String createdBy;

    private Instant createdDate;

    private String lastModifiedBy;

    private Instant lastModifiedDate;

    private Long courseId;
    private String courseTitle;
    private String courseSubTitle;
    private String courseStatus;
    private String courseType;
    private Integer courseLessonNum;
    private String courseCredit;
    private String courseCoverPicture;
    private String courseIntroduction;
    private String courseGoals;
    private String courseRecommended;
    private String courseRecommendedSort;
    
    private String courseCreatedBy;

    private Instant courseCreatedDate;

    private String courseLastModifiedBy;

    private Instant courseLastModifiedDate;
    
    private Long courseFavoriteId;
    private Long courseFavoriteCourseId;
    private Integer countUserId;
    private Integer countCourseReviewId;

    public UserInfoDTO() {
        // Empty constructor needed for Jackson.
    }

	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getLogin() {
		return login;
	}


	public void setLogin(String login) {
		this.login = login;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getAvatar() {
		return avatar;
	}


	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getMobile() {
		return mobile;
	}


	public void setMobile(String mobile) {
		this.mobile = mobile;
	}


	public String getSex() {
		return sex;
	}


	public void setSex(String sex) {
		this.sex = sex;
	}


	public String getStaffNo() {
		return staffNo;
	}


	public void setStaffNo(String staffNo) {
		this.staffNo = staffNo;
	}


	public String getMajor() {
		return major;
	}


	public void setMajor(String major) {
		this.major = major;
	}


	public String getSchool() {
		return school;
	}


	public void setSchool(String school) {
		this.school = school;
	}


	public boolean isActivated() {
		return activated;
	}


	public void setActivated(boolean activated) {
		this.activated = activated;
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


	public Long getCourseId() {
		return courseId;
	}


	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}


	public String getCourseTitle() {
		return courseTitle;
	}
	

	public String getCourseSubTitle() {
		return courseSubTitle;
	}

	public void setCourseSubTitle(String courseSubTitle) {
		this.courseSubTitle = courseSubTitle;
	}

	public void setCourseTitle(String courseTitle) {
		this.courseTitle = courseTitle;
	}


	public String getCourseStatus() {
		return courseStatus;
	}


	public void setCourseStatus(String courseStatus) {
		this.courseStatus = courseStatus;
	}


	public String getCourseType() {
		return courseType;
	}


	public void setCourseType(String courseType) {
		this.courseType = courseType;
	}


	public Integer getCourseLessonNum() {
		return courseLessonNum;
	}


	public void setCourseLessonNum(Integer courseLessonNum) {
		this.courseLessonNum = courseLessonNum;
	}


	public String getCourseCredit() {
		return courseCredit;
	}


	public void setCourseCredit(String courseCredit) {
		this.courseCredit = courseCredit;
	}


	public String getCourseCoverPicture() {
		return courseCoverPicture;
	}


	public void setCourseCoverPicture(String courseCoverPicture) {
		this.courseCoverPicture = courseCoverPicture;
	}


	public String getCourseIntroduction() {
		return courseIntroduction;
	}


	public void setCourseIntroduction(String courseIntroduction) {
		this.courseIntroduction = courseIntroduction;
	}


	public String getCourseGoals() {
		return courseGoals;
	}


	public void setCourseGoals(String courseGoals) {
		this.courseGoals = courseGoals;
	}


	public String getCourseRecommended() {
		return courseRecommended;
	}


	public void setCourseRecommended(String courseRecommended) {
		this.courseRecommended = courseRecommended;
	}


	public String getCourseRecommendedSort() {
		return courseRecommendedSort;
	}


	public void setCourseRecommendedSort(String courseRecommendedSort) {
		this.courseRecommendedSort = courseRecommendedSort;
	}


	public String getCourseCreatedBy() {
		return courseCreatedBy;
	}


	public void setCourseCreatedBy(String courseCreatedBy) {
		this.courseCreatedBy = courseCreatedBy;
	}


	public Instant getCourseCreatedDate() {
		return courseCreatedDate;
	}


	public void setCourseCreatedDate(Instant courseCreatedDate) {
		this.courseCreatedDate = courseCreatedDate;
	}


	public String getCourseLastModifiedBy() {
		return courseLastModifiedBy;
	}


	public void setCourseLastModifiedBy(String courseLastModifiedBy) {
		this.courseLastModifiedBy = courseLastModifiedBy;
	}


	public Instant getCourseLastModifiedDate() {
		return courseLastModifiedDate;
	}


	public void setCourseLastModifiedDate(Instant courseLastModifiedDate) {
		this.courseLastModifiedDate = courseLastModifiedDate;
	}


	public Long getCourseFavoriteId() {
		return courseFavoriteId;
	}


	public void setCourseFavoriteId(Long courseFavoriteId) {
		this.courseFavoriteId = courseFavoriteId;
	}


	public Long getCourseFavoriteCourseId() {
		return courseFavoriteCourseId;
	}


	public void setCourseFavoriteCourseId(Long courseFavoriteCourseId) {
		this.courseFavoriteCourseId = courseFavoriteCourseId;
	}


	public Integer getCountUserId() {
		return countUserId;
	}

	public void setCountUserId(Integer countUserId) {
		this.countUserId = countUserId;
	}

	public Integer getCountCourseReviewId() {
		return countCourseReviewId;
	}

	public void setCountCourseReviewId(Integer countCourseReviewId) {
		this.countCourseReviewId = countCourseReviewId;
	}

	@Override
	public String toString() {
		return "UserInfoDTO [id=" + id + ", login=" + login + ", name=" + name + ", avatar=" + avatar + ", email="
				+ email + ", mobile=" + mobile + ", sex=" + sex + ", staffNo=" + staffNo + ", major=" + major
				+ ", school=" + school + ", activated=" + activated + ", createdBy=" + createdBy + ", createdDate="
				+ createdDate + ", lastModifiedBy=" + lastModifiedBy + ", lastModifiedDate=" + lastModifiedDate
				+ ", courseId=" + courseId + ", courseTitle=" + courseTitle + ", courseSubTitle=" + courseSubTitle
				+ ", courseStatus=" + courseStatus + ", courseType=" + courseType + ", courseLessonNum="
				+ courseLessonNum + ", courseCredit=" + courseCredit + ", courseCoverPicture=" + courseCoverPicture
				+ ", courseIntroduction=" + courseIntroduction + ", courseGoals=" + courseGoals + ", courseRecommended="
				+ courseRecommended + ", courseRecommendedSort=" + courseRecommendedSort + ", courseCreatedBy="
				+ courseCreatedBy + ", courseCreatedDate=" + courseCreatedDate + ", courseLastModifiedBy="
				+ courseLastModifiedBy + ", courseLastModifiedDate=" + courseLastModifiedDate + ", courseFavoriteId="
				+ courseFavoriteId + ", courseFavoriteCourseId=" + courseFavoriteCourseId + ", countUserId="
				+ countUserId + ", countCourseReviewId=" + countCourseReviewId + "]";
	}
	
}
