package com.zonesion.cloud.web.rest.dto;

import java.util.List;

public class UnitInfoDTO {
	
	public long id;
	
	public long parentId;
	
	public String chapterType;
	
	public String title;
	
	public int number;
	
	public int seq;
	
	public List<LessonInfoDTO> lessons;
	
	public UnitInfoDTO() {
		
	}

	public UnitInfoDTO(long id, long parentId, String chapterType, String title, int number, int seq,
			List<LessonInfoDTO> lessons) {
		super();
		this.id = id;
		this.parentId = parentId;
		this.chapterType = chapterType;
		this.title = title;
		this.number = number;
		this.seq = seq;
		this.lessons = lessons;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getParentId() {
		return parentId;
	}

	public void setParentId(long parentId) {
		this.parentId = parentId;
	}

	public String getChapterType() {
		return chapterType;
	}

	public void setChapterType(String chapterType) {
		this.chapterType = chapterType;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public List<LessonInfoDTO> getLessons() {
		return lessons;
	}

	public void setLessons(List<LessonInfoDTO> lessons) {
		this.lessons = lessons;
	}
	
}
