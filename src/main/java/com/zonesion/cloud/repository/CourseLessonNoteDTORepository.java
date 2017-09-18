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



/**
 * Spring Data JPA repository for the CourseLessonNote entity.
 */
@Repository
public class CourseLessonNoteDTORepository {
    
	@Autowired
    private JdbcTemplate jdbcTemplate;
	
	@Transactional
    public List<CourseLessonNoteDTO> findCourseNote(Long id) {
        return jdbcTemplate.query("select tcln.*,tu.avatar,tu.login from t_course_lesson_note tcln left join t_user tu on tcln.user_id=tu.id where tcln.course_id= ? order by tcln.created_date desc", new Object[]{id}, new CourseLessonNoteRowMapper());
    }
	
	@Transactional
    public List<CourseLessonNoteDTO> findCourseLessonNote(Long id, Long courseLessonId) {
        return jdbcTemplate.query("select tcln.*,tu.avatar,tu.login from t_course_lesson_note tcln left join t_user tu on tcln.user_id=tu.id where tcln.course_id= ? and tcln.course_lesson_id=? order by tcln.created_date desc", new Object[]{id, courseLessonId}, new CourseLessonNoteRowMapper());
    }

}

class CourseLessonNoteRowMapper implements RowMapper<CourseLessonNoteDTO> {

	@Override
	public CourseLessonNoteDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		CourseLessonNoteDTO courseLessonNoteDTO = new CourseLessonNoteDTO();
		courseLessonNoteDTO.setId(rs.getLong("id"));
		courseLessonNoteDTO.setCourseId(rs.getLong("course_id"));
		courseLessonNoteDTO.setUserId(rs.getLong("user_id"));
		courseLessonNoteDTO.setContent(rs.getString("content"));
		courseLessonNoteDTO.setLength(rs.getInt("length"));
		courseLessonNoteDTO.setLikeNum(rs.getInt("like_num"));
		courseLessonNoteDTO.setIsPrivate(rs.getString("is_private"));
		courseLessonNoteDTO.setCourseLessonId(rs.getLong("course_lesson_id"));
		courseLessonNoteDTO.setCreatedBy(rs.getString("created_by"));
		if(rs.getTimestamp("created_date")==null){
			courseLessonNoteDTO.setCreatedDate(null);
		}else{
			courseLessonNoteDTO.setCreatedDate(rs.getTimestamp("created_date").toInstant());		
		}
		
		courseLessonNoteDTO.setLastModifiedBy(rs.getString("last_modified_by"));
		
		if(rs.getTime("last_modified_date")==null){
			courseLessonNoteDTO.setLastModifiedDate(null);
		}else{
			courseLessonNoteDTO.setLastModifiedDate(rs.getTimestamp("last_modified_date").toInstant());
		}
		courseLessonNoteDTO.setAvatar(rs.getString("avatar"));
		courseLessonNoteDTO.setLogin(rs.getString("login"));
		return courseLessonNoteDTO;
	}
	
}

