package com.zonesion.cloud.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.mysql.jdbc.Statement;
import com.zonesion.cloud.service.dto.CourseLessonNoteDTO;
import com.zonesion.cloud.service.dto.CourseReviewDTO;
import com.zonesion.cloud.service.dto.ext.CourseReviewExtDTO;



/**
 * Spring Data JPA repository for the CourseLessonNote entity.
 */
@Repository
public class CourseReviewDTORepository {
    
	@Autowired
	
    private JdbcTemplate jdbcTemplate;
	
	@Transactional
    public List<CourseReviewExtDTO> findCourseReview(Long id) {
        return jdbcTemplate.query("SELECT u.avatar, cr.* FROM t_course c LEFT JOIN t_course_review cr ON cr.course_id = c.id LEFT JOIN t_user u ON u.id = c.user_id WHERE c.id = ?", new Object[]{id}, new CourseReviewRowMapper());
    }
	
	public CourseReviewDTO saveCourseReview(final CourseReviewDTO courseReviewDTO) {
        final String sql = "INSERT INTO t_course_review(user_id, title, content, rating, privacy, course_id, created_by, created_date, last_modified_by, last_modified_date) VALUES(?,?,?,?,?,?,?,?,?,?)";

        KeyHolder holder = new GeneratedKeyHolder();

        jdbcTemplate.update(new PreparedStatementCreator() {

			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				// TODO Auto-generated method stub
				PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setLong(1, courseReviewDTO.getUserId());
                ps.setString(2, courseReviewDTO.getTitle());
                ps.setString(3, courseReviewDTO.getContent());
                ps.setInt(4, courseReviewDTO.getRating());
                ps.setString(5, courseReviewDTO.getPrivacy());
                ps.setLong(6, courseReviewDTO.getCourseId());
                ps.setString(7, courseReviewDTO.getCreatedBy());
                ps.setTimestamp(8, Timestamp.from(courseReviewDTO.getCreatedDate()));
                ps.setString(9, courseReviewDTO.getLastModifiedBy());
                ps.setTimestamp(10, Timestamp.from(courseReviewDTO.getLastModifiedDate()));
                return ps;
			}
        }, holder);

        long newUserId = holder.getKey().longValue();
        courseReviewDTO.setId(newUserId);
        return courseReviewDTO;
    }
	
	public void deleteCourseReview(final Long id) {
        final String sql = "DELETE FROM t_course_review WHERE id=?";
        jdbcTemplate.update(sql,
                new Object[]{id},
                new int[]{java.sql.Types.BIGINT});
    }

    public void updateCourseReview(final CourseReviewDTO courseReviewDTO) {
    	Timestamp createdDate = Timestamp.from(courseReviewDTO.getCreatedDate());
    	Timestamp lastModifiedDate = Timestamp.from(courseReviewDTO.getLastModifiedDate());
        jdbcTemplate.update(
                "UPDATE t_course_review SET user_id=?, title=?, content=?, rating=?, privacy=?, course_id=?, created_by=?, created_date=?, last_modified_by=?, last_modified_date=? WHERE id=?",
                new Object[]{courseReviewDTO.getUserId(), courseReviewDTO.getTitle(), courseReviewDTO.getContent(), courseReviewDTO.getRating(), courseReviewDTO.getPrivacy(), courseReviewDTO.getCourseId(), courseReviewDTO.getCreatedBy(), createdDate, courseReviewDTO.getLastModifiedBy(), lastModifiedDate, courseReviewDTO.getId()});
    }

}

class CourseReviewRowMapper implements RowMapper<CourseReviewExtDTO> {

	@Override
	public CourseReviewExtDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		CourseReviewExtDTO courseReviewDTO = new CourseReviewExtDTO();
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

