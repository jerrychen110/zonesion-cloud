package com.zonesion.cloud.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.zonesion.cloud.service.dto.CourseDTO;

/**   
 * @Title: CourseJDBCRepository.java 
 * @Package com.zonesion.cloud.repository 
 * @Description: TODO 
 * @author: cc  
 * @date: 2017年8月21日 上午8:31:47 
 */
@Repository
public class CourseDTORepository {
	@Autowired
	
    private JdbcTemplate jdbcTemplate;
	@Transactional
    public List<CourseDTO> findCourseChapters(Long id) {
        return jdbcTemplate.query("SELECT c.*, ch.id chapter_id, ch.chapter_type, ch.number chapter_num, ch.seq chapter_seq, ch.title chapter_title, ch.created_by chapter_created_by, ch.created_date chapter_created_date, ch.last_modified_by chapter_last_modified_by, ch.last_modified_date chapter_last_modified_date, cl.id course_lesson_id, cl.number course_lesson_num, cl.seq course_lesson_seq, cl.title course_lesson_title, cl.summary course_lesson_summary, cl.course_lesson_type, cl.content course_lesson_content, cl.credit course_lesson_credit, cl.media_id course_lesson_media_id, cl.media_source course_lesson_media_source, cl.media_name course_lesson_media_name, cl.media_uri course_lesson_media_uri, cl.learned_num course_lesson_learned_num, cl.viewed_num course_lesson_viewed_num, cl.created_by course_lesson_created_by, cl.created_date course_lesson_created_date, cl.last_modified_by course_lesson_last_modified_by, cl.last_modified_date course_lesson_last_modified_date, u.name user_name, u.avatar user_avatar, u.email user_email, u.mobile user_mobile, u.sex user_sex, u.staff_no, u.major user_major, u.school user_school FROM t_course c LEFT JOIN t_chapter ch ON c.id = ch.course_id LEFT JOIN t_course_lesson cl ON cl.chapter_id = ch.id LEFT JOIN t_user u ON u.id = c.user_id WHERE c.id = ?", new Object[]{id}, new UserRowMapper());
    }

}

class UserRowMapper implements RowMapper<CourseDTO> {

	@Override
	public CourseDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		CourseDTO courseDTO = new CourseDTO();
		courseDTO.setId(rs.getLong("id"));
		courseDTO.setUserId(rs.getLong("user_id"));
		courseDTO.setTitle(rs.getString("title"));
		courseDTO.setSubTitle(rs.getString("sub_title"));
		courseDTO.setStatus(rs.getString("status"));
		courseDTO.setCourseType(rs.getString("course_type"));
		courseDTO.setLessonNum(rs.getInt("lesson_num"));
		courseDTO.setCredit(rs.getString("credit"));
		courseDTO.setCoverPicture(rs.getString("cover_picture"));
		courseDTO.setIntroduction(rs.getString("introduction"));
		courseDTO.setGoals(rs.getString("goals"));
		courseDTO.setRecommended(rs.getString("recommended"));
		courseDTO.setRecommendedSort(rs.getString("recommended_sort"));
		courseDTO.setCreatedBy(rs.getString("created_by"));
		if(rs.getTimestamp("created_date")==null){
			courseDTO.setCreatedDate(null);
		}else{
			courseDTO.setCreatedDate(rs.getTimestamp("created_date").toInstant());
		}
		
		courseDTO.setLastModifiedBy(rs.getString("last_modified_by"));
		if(rs.getTimestamp("last_modified_date") == null){
			courseDTO.setLastModifiedDate(null);
		}else{
			courseDTO.setLastModifiedDate(rs.getTimestamp("last_modified_date").toInstant());
		}
		
		
		courseDTO.setChapterId(rs.getLong("chapter_id"));
		courseDTO.setChapterType(rs.getString("chapter_type"));
		courseDTO.setChapterNumber(rs.getInt("chapter_num"));
		courseDTO.setChapterSeq(rs.getInt("chapter_seq"));
		courseDTO.setChapterTitle(rs.getString("chapter_title"));
		courseDTO.setChapterCreatedBy(rs.getString("chapter_created_by"));
		if(rs.getTimestamp("chapter_created_date") == null){
			courseDTO.setChapterCreatedDate(null);
		}else{
			courseDTO.setChapterCreatedDate(rs.getTimestamp("chapter_created_date").toInstant());
		}
		
		courseDTO.setChapterLastModifiedBy(rs.getString("chapter_last_modified_by"));
		if(rs.getTimestamp("chapter_last_modified_date") == null){
			courseDTO.setChapterLastModifiedDate(null);
		}else{
			courseDTO.setChapterLastModifiedDate(rs.getTimestamp("chapter_last_modified_date").toInstant());
		}
			
		courseDTO.setCourseLessonId(rs.getLong("course_lesson_id"));
		courseDTO.setCourseLessonNumber(rs.getInt("course_lesson_num"));
		courseDTO.setCourseLessonSeq(rs.getInt("course_lesson_seq"));
		courseDTO.setCourseLessonTitle(rs.getString("course_lesson_title"));
		courseDTO.setCourseLessonSummary(rs.getString("course_lesson_summary"));
		courseDTO.setCourseLessonType(rs.getString("course_lesson_type"));
		courseDTO.setCourseLessonContent(rs.getString("course_lesson_content"));
		courseDTO.setCourseLessonCredit(rs.getInt("course_lesson_credit"));
		courseDTO.setCourseLessonMediaId(rs.getInt("course_lesson_media_id"));
		courseDTO.setCourseLessonMediaSource(rs.getString("course_lesson_media_source"));
		courseDTO.setCourseLessonMediaName(rs.getString("course_lesson_media_name"));
		courseDTO.setCourseLessonMediaURI(rs.getString("course_lesson_media_uri"));
		courseDTO.setCourseLessonLearnedNum(rs.getInt("course_lesson_learned_num"));
		courseDTO.setCourseLessonViewedNum(rs.getInt("course_lesson_viewed_num"));
		courseDTO.setCourseLessonCreatedBy(rs.getString("course_lesson_created_by"));
		if(rs.getTimestamp("course_lesson_created_date")==null){
			courseDTO.setCourseLessonCreatedDate(null);
		}else{
			courseDTO.setCourseLessonCreatedDate(rs.getTimestamp("course_lesson_created_date").toInstant());		
		}
		
		courseDTO.setCourseLessonLastModifiedBy(rs.getString("course_lesson_last_modified_by"));
		
		if(rs.getTime("course_lesson_last_modified_date")==null){
			courseDTO.setCourseLessonLastModifiedDate(null);
		}else{
			courseDTO.setCourseLessonLastModifiedDate(rs.getTime("course_lesson_last_modified_date").toInstant());
		}
		
		courseDTO.setUserName(rs.getString("user_name"));
		courseDTO.setUserAvatar(rs.getString("user_avatar"));
		courseDTO.setUserEmail(rs.getString("user_email"));
		courseDTO.setUserMobile(rs.getString("user_mobile"));
		courseDTO.setUserSex(rs.getString("user_sex"));
		courseDTO.setStaffNo(rs.getString("staff_no"));
		courseDTO.setUserMajor(rs.getString("user_major"));
		courseDTO.setUserSchool(rs.getString("user_school"));
		
		return courseDTO;
	}


    
    
}
