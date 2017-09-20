package com.zonesion.cloud.web.rest.dto;

public class MyCourseDTO {
	
	private Long id;
	
	private String title;
	
	private String subTitle;
	
	private Integer lessonNum;
	
	private String coverPicture;
	
	private String introduction;
	
	private String goals;
	
	private String tags;
	
	private Integer learnedNum;
	
	private Long userId;
	
	private String login;
	
	private String avatar;
	
	public MyCourseDTO() {
		
	}

	public MyCourseDTO(Long id, String title, String subTitle, Integer lessonNum, String coverPicture,
			String introduction, String goals, String tags, Integer learnedNum, Long userId, String login,
			String avatar) {
		super();
		this.id = id;
		this.title = title;
		this.subTitle = subTitle;
		this.lessonNum = lessonNum;
		this.coverPicture = coverPicture;
		this.introduction = introduction;
		this.goals = goals;
		this.tags = tags;
		this.learnedNum = learnedNum;
		this.userId = userId;
		this.login = login;
		this.avatar = avatar;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public Integer getLessonNum() {
		return lessonNum;
	}

	public void setLessonNum(Integer lessonNum) {
		this.lessonNum = lessonNum;
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

	public Integer getLearnedNum() {
		return learnedNum;
	}

	public void setLearnedNum(Integer learnedNum) {
		this.learnedNum = learnedNum;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

}
