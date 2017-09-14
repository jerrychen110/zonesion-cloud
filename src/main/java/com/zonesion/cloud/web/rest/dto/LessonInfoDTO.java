package com.zonesion.cloud.web.rest.dto;

public class LessonInfoDTO {
	
	public long id;
	
	public int number;
	
	public int seq;
	
	public String title;
	
	public String summary;
	
	public String courseLessonType;
	
	public String mediaName;
	
	public int mediaSize;
	
	public int mediaLength;
	
	public String mediaSource;
	
	public String mediaUri;
	
	public String content;
	
	public int credit;
	
	public int learnedNum;
	
	public int viewedNum;
	
	public long learnedUserId;
	
	public String learnedStatus;
	
	public LessonInfoDTO() {
		
	}

	public LessonInfoDTO(long id, int number, int seq, String title, String summary, String courseLessonType,
			String mediaName, int mediaSize, int mediaLength, String mediaSource, String mediaUri, String content,
			int credit, int learnedNum, int viewedNum, long learnedUserId, String learnedStatus) {
		super();
		this.id = id;
		this.number = number;
		this.seq = seq;
		this.title = title;
		this.summary = summary;
		this.courseLessonType = courseLessonType;
		this.mediaName = mediaName;
		this.mediaSize = mediaSize;
		this.mediaLength = mediaLength;
		this.mediaSource = mediaSource;
		this.mediaUri = mediaUri;
		this.content = content;
		this.credit = credit;
		this.learnedNum = learnedNum;
		this.viewedNum = viewedNum;
		this.learnedUserId = learnedUserId;
		this.learnedStatus = learnedStatus;
	}



	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
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

	public String getMediaName() {
		return mediaName;
	}

	public void setMediaName(String mediaName) {
		this.mediaName = mediaName;
	}

	public int getMediaSize() {
		return mediaSize;
	}

	public void setMediaSize(int mediaSize) {
		this.mediaSize = mediaSize;
	}

	public int getMediaLength() {
		return mediaLength;
	}

	public void setMediaLength(int mediaLength) {
		this.mediaLength = mediaLength;
	}

	public String getMediaSource() {
		return mediaSource;
	}

	public void setMediaSource(String mediaSource) {
		this.mediaSource = mediaSource;
	}

	public String getMediaUri() {
		return mediaUri;
	}

	public void setMediaUri(String mediaUri) {
		this.mediaUri = mediaUri;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getCredit() {
		return credit;
	}

	public void setCredit(int credit) {
		this.credit = credit;
	}

	public int getLearnedNum() {
		return learnedNum;
	}

	public void setLearnedNum(int learnedNum) {
		this.learnedNum = learnedNum;
	}

	public int getViewedNum() {
		return viewedNum;
	}

	public void setViewedNum(int viewedNum) {
		this.viewedNum = viewedNum;
	}

	public long getLearnedUserId() {
		return learnedUserId;
	}

	public void setLearnedUserId(long learnedUserId) {
		this.learnedUserId = learnedUserId;
	}

	public String getLearnedStatus() {
		return learnedStatus;
	}

	public void setLearnedStatus(String learnedStatus) {
		this.learnedStatus = learnedStatus;
	}
	
}
