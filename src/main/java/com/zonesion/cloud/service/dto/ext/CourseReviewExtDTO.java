package com.zonesion.cloud.service.dto.ext;


import com.zonesion.cloud.service.dto.CourseReviewDTO;

import java.io.Serializable;

/**
 * A DTO for the CourseReview entity.
 */
public class CourseReviewExtDTO extends CourseReviewDTO implements Serializable {

    /**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = 1L;

    private String avatar;
    
    public CourseReviewExtDTO(){
    	
    }
    
    public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
}
