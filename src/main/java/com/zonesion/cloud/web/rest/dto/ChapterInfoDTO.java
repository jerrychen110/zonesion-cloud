package com.zonesion.cloud.web.rest.dto;

import java.util.List;

public class ChapterInfoDTO {
	
	public long id;
	
	public String chapterType;
	
	public String title;
	
	public int number;
	
	public int seq;
	
	public List<UnitInfoDTO> units;
	
	public ChapterInfoDTO() {
		
	}

	public ChapterInfoDTO(long id, String chapterType, String title, int number, int seq,
			List<UnitInfoDTO> units) {
		super();
		this.id = id;
		this.chapterType = chapterType;
		this.title = title;
		this.number = number;
		this.seq = seq;
		this.units = units;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public List<UnitInfoDTO> getUnits() {
		return units;
	}

	public void setUnits(List<UnitInfoDTO> units) {
		this.units = units;
	}
	
}
