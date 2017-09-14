package com.zonesion.cloud.web.rest.dto;

import java.util.List;

import com.zonesion.cloud.domain.User;

public class CourseBaseInfoDTO {
	
	public long id;
	
	public String title;
	
	public String subTitle;
	
	public String status;
	
	public String courseType;
	
	public String courseSource;
	
	public int lessonNum;
	
	public String credit;
	
	public String coverPicture;
	
	public String introduction;
	
	public String goals;
	
	public String tags;
	
	public int learnedNum;
	
	public String isCollected;
	
	public String learnedStatus;
	
	public List<User> learnedUsers;
	
	public CourseBaseInfoDTO() {
		
	}

	public CourseBaseInfoDTO(long id, String title, String subTitle, String status, String courseType,
			String courseSource, int lessonNum, String credit, String coverPicture, String introduction, String goals,
			String tags, int learnedNum, String isCollected, String learnedStatus, List<User> learnedUsers) {
		super();
		this.id = id;
		this.title = title;
		this.subTitle = subTitle;
		this.status = status;
		this.courseType = courseType;
		this.courseSource = courseSource;
		this.lessonNum = lessonNum;
		this.credit = credit;
		this.coverPicture = coverPicture;
		this.introduction = introduction;
		this.goals = goals;
		this.tags = tags;
		this.learnedNum = learnedNum;
		this.isCollected = isCollected;
		this.learnedStatus = learnedStatus;
		this.learnedUsers = learnedUsers;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public int getLessonNum() {
		return lessonNum;
	}

	public void setLessonNum(int lessonNum) {
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

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public int getLearnedNum() {
		return learnedNum;
	}

	public void setLearnedNum(int learnedNum) {
		this.learnedNum = learnedNum;
	}

	public String getIsCollected() {
		return isCollected;
	}

	public void setIsCollected(String isCollected) {
		this.isCollected = isCollected;
	}

	public String getLearnedStatus() {
		return learnedStatus;
	}

	public void setLearnedStatus(String learnedStatus) {
		this.learnedStatus = learnedStatus;
	}

	public List<User> getLearnedUsers() {
		return learnedUsers;
	}

	public void setLearnedUsers(List<User> learnedUsers) {
		this.learnedUsers = learnedUsers;
	}
	
}
