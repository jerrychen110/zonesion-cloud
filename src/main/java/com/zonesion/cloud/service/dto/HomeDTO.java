package com.zonesion.cloud.service.dto;

import java.io.Serializable;
import java.time.Instant;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**   
 * @Title: HomeDTO.java 
 * @Package com.zonesion.cloud.service.dto 
 * @Description: TODO 
 * @author: cc  
 * @date: 2017年8月21日 上午11:27:35 
 */
@Entity
public class HomeDTO implements Serializable {
	
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
    
    private Integer countUserId;
    
    private Integer courseReviewId;
    
    private String userName;
    private String userAvatar;
    private String userEmail;
    private String userMobile;
    private String userSex;
    private String staffNo;
    private String userMajor;
    private String userSchool;
    
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

	
	public Integer getCountUserId() {
		return countUserId;
	}
	public void setCountUserId(Integer countUserId) {
		this.countUserId = countUserId;
	}
	public Integer getCourseReviewId() {
		return courseReviewId;
	}
	public void setCourseReviewId(Integer courseReviewId) {
		this.courseReviewId = courseReviewId;
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserAvatar() {
		return userAvatar;
	}
	public void setUserAvatar(String userAvatar) {
		this.userAvatar = userAvatar;
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
	@Override
	public String toString() {
		return "HomeDTO [id=" + id + ", userId=" + userId + ", title=" + title + ", subTitle=" + subTitle + ", status="
				+ status + ", courseType=" + courseType + ", lessonNum=" + lessonNum + ", credit=" + credit
				+ ", coverPicture=" + coverPicture + ", introduction=" + introduction + ", goals=" + goals
				+ ", recommended=" + recommended + ", recommendedSort=" + recommendedSort + ", createdBy=" + createdBy
				+ ", createdDate=" + createdDate + ", lastModifiedBy=" + lastModifiedBy + ", lastModifiedDate="
				+ lastModifiedDate + ", countUserId=" + countUserId + ", courseReviewId=" + courseReviewId
				+ ", userName=" + userName + ", userAvatar=" + userAvatar + ", userEmail=" + userEmail + ", userMobile="
				+ userMobile + ", userSex=" + userSex + ", staffNo=" + staffNo + ", userMajor=" + userMajor
				+ ", userSchool=" + userSchool + "]";
	}
}
