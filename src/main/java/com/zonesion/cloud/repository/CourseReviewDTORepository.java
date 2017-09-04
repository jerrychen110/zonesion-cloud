package com.zonesion.cloud.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.zonesion.cloud.service.dto.CourseLessonNoteDTO;
import com.zonesion.cloud.service.dto.CourseReviewDTO;



/**
 * Spring Data JPA repository for the CourseLessonNote entity.
 */
@Repository
public class CourseReviewDTORepository {
    
	@Autowired
	
    private JdbcTemplate jdbcTemplate;
	
	@Transactional
    public List<CourseReviewDTO> findCourseReview(Long id) {
        return jdbcTemplate.query("SELECT u.avatar, cr.* FROM t_course c LEFT JOIN t_course_review cr ON cr.course_id = c.id LEFT JOIN t_user u ON u.id = c.user_id WHERE c.id = ?", new Object[]{id}, new CourseReviewRowMapper());
    }

}

class CourseReviewRowMapper implements RowMapper<CourseReviewDTO> {

	@Override
	public CourseReviewDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		CourseReviewDTO courseReviewDTO = new CourseReviewDTO();
		courseReviewDTO.setId(rs.getLong("id"));
		courseReviewDTO.setUserId(rs.getLong("user_id"));
		courseReviewDTO.setTitle(rs.getString("title"));
		courseReviewDTO.setContent(rs.getString("content"));
		courseReviewDTO.setRating(rs.getInt("rating"));
		courseReviewDTO.setPrivacy(rs.getString("privacy"));
		courseReviewDTO.setCourseId(rs.getLong("course_id"));
		courseReviewDTO.setCreatedBy(rs.getString("created_by"));
		courseReviewDTO.setAvatar(rs.getString("avatar"));
		if(rs.getTimestamp("created_date")==null){
			courseReviewDTO.setCreatedDate(null);
		}else{
			courseReviewDTO.setCreatedDate(rs.getTimestamp("created_date").toInstant());		
		}
		
		courseReviewDTO.setLastModifiedBy(rs.getString("last_modified_by"));
		
		if(rs.getTime("last_modified_date")==null){
			courseReviewDTO.setLastModifiedDate(null);
		}else{
			courseReviewDTO.setLastModifiedDate(rs.getTimestamp("last_modified_date").toInstant());
		}
		
		return courseReviewDTO;
	}
	
}

