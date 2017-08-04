package com.zonesion.cloud.service.dto;


import javax.validation.constraints.*;

import com.zonesion.cloud.domain.Course;
import com.zonesion.cloud.domain.CourseFavorite;

import java.io.Serializable;

/**
 * A DTO for the CourseFavorite entity.
 */
public class CourseFavoriteDTO implements Serializable {

    /**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

    @NotNull
    private Long userId;

    private Course course;
    
    public CourseFavoriteDTO(){
    	
    }
    
    public CourseFavoriteDTO(CourseFavorite courseFavorite) {
		this(courseFavorite.getId(), courseFavorite.getUserId(), courseFavorite.getCourse());
	}
    
    

	public CourseFavoriteDTO(Long id, Long userId, Course course) {
		super();
		this.id = id;
		this.userId = userId;
		this.course = course;
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

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }


    @Override
    public String toString() {
        return "CourseFavoriteDTO{" +
            "id=" + getId() +
            ", userId='" + getUserId() + "'" +
            ", course='" + getCourse() + "'" +
            "}";
    }
}
