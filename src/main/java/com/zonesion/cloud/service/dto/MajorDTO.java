package com.zonesion.cloud.service.dto;


import javax.validation.constraints.*;

import com.zonesion.cloud.domain.Major;

import java.io.Serializable;

/**
 * A DTO for the Major entity.
 */
public class MajorDTO implements Serializable {

    /**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

    @NotNull
    @Size(max = 255)
    private String major;

    public MajorDTO(Major major) {
		this(major.getId(), major.getMajor());
	}
    
    public MajorDTO(Long id, String major){
    	this.id = id;
    	this.major = major;
    }

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }


    @Override
	public String toString() {
		return "MajorDTO [id=" + id + ", major=" + major + "]";
	}
}
