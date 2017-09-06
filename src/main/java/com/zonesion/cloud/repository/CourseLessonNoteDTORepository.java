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
    public List<CourseLessonNoteDTO> findCourseLessonNote(Long id) {
        return jdbcTemplate.query("SELECT c.*, ch.*, cl.*, cln.*, clnl.id course_lesson_note_like_id, clnl.user_id course_lesson_note_like_user_id, clnl.created_time course_lesson_note_like_create_time, clnl.course_lesson_note_id, u.avatar FROM t_course c LEFT JOIN t_chapter ch ON ch.course_id = c.id LEFT JOIN t_course_lesson cl ON cl.chapter_id = ch.id LEFT JOIN t_course_lesson_note cln ON cln.course_lesson_id = cl.id LEFT JOIN t_course_lesson_note_like clnl ON clnl.course_lesson_note_id = cln.id LEFT JOIN t_user u ON u.id = c.user_id WHERE c.id = ?", new Object[]{id}, new CourseLessonNoteRowMapper());
    }

}

class CourseLessonNoteRowMapper implements RowMapper<CourseLessonNoteDTO> {

	@Override
	public CourseLessonNoteDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
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
		courseLessonNoteDTO.setCourseLessonNoteLikeId(rs.getLong("course_lesson_note_like_id"));
		courseLessonNoteDTO.setCourseLessonNoteLikeUserId(rs.getLong("course_lesson_note_like_user_id"));
		
		if(rs.getTime("course_lesson_note_like_create_time")==null){
			courseLessonNoteDTO.setCourseLessonNoteLikeCreateTime(null);
		}else{
			courseLessonNoteDTO.setCourseLessonNoteLikeCreateTime(rs.getTimestamp("course_lesson_note_like_create_time").toInstant());
		}

	
		courseLessonNoteDTO.setCourseLessonNoteId(rs.getLong("course_lesson_note_id"));
		courseLessonNoteDTO.setAvatar(rs.getString("avatar"));
		return courseLessonNoteDTO;
	}
	
}

