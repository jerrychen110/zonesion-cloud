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
import com.zonesion.cloud.service.dto.HomeDTO;

/**   
 * @Title: CourseJDBCRepository.java 
 * @Package com.zonesion.cloud.repository 
 * @Description: TODO 
 * @author: cc  
 * @date: 2017年8月21日 上午8:31:47 
 */
@Repository
public class HomeDTORepository {
	@Autowired
	
    private JdbcTemplate jdbcTemplate;
	@Transactional
    public List<HomeDTO> findNewestCourse() {
        return jdbcTemplate.query("SELECT c.*, count(DISTINCT(cll.user_id)) count_user_id, count(DISTINCT(cr.id)) course_review_id FROM t_course c LEFT JOIN t_chapter ch ON ch.course_id = c.id LEFT JOIN t_course_lesson cl ON cl.chapter_id = ch.id LEFT JOIN t_course_lesson_learn cll ON cll.course_id = c.id LEFT JOIN t_course_review cr ON cr.course_id = c.id GROUP BY c.id, cll.course_id, cr.user_id ORDER BY last_modified_date DESC", new Object[]{}, new HomeRowMapper());
    }
	
	@Transactional
    public List<HomeDTO> findHotCourse() {
        return jdbcTemplate.query("SELECT c.*, count(DISTINCT(cll.user_id)) count_user_id, count(DISTINCT(cr.id)) course_review_id FROM t_course c LEFT JOIN t_chapter ch ON ch.course_id = c.id LEFT JOIN t_course_lesson cl ON cl.chapter_id = ch.id LEFT JOIN t_course_lesson_learn cll ON cll.course_id = c.id LEFT JOIN t_course_review cr ON cr.course_id = c.id GROUP BY c.id, cll.course_id, cr.user_id ORDER BY count_user_id DESC", new Object[]{}, new HomeRowMapper());
    }
	
	@Transactional
    public List<HomeDTO> findRecommendedCourse() {
        return jdbcTemplate.query("SELECT c.*, count(DISTINCT(cll.user_id)) count_user_id, count(DISTINCT(cr.id)) course_review_id FROM t_course c LEFT JOIN t_chapter ch ON ch.course_id = c.id LEFT JOIN t_course_lesson cl ON cl.chapter_id = ch.id LEFT JOIN t_course_lesson_learn cll ON cll.course_id = c.id LEFT JOIN t_course_review cr ON cr.course_id = c.id WHERE c.recommended='是' GROUP BY c.id, cll.course_id, cr.user_id ORDER BY recommended_sort DESC", new Object[]{}, new HomeRowMapper());
    }

}

class HomeRowMapper implements RowMapper<HomeDTO> {

	@Override
	public HomeDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		HomeDTO homeDTO = new HomeDTO();
		homeDTO.setId(rs.getLong("id"));
		homeDTO.setUserId(rs.getLong("user_id"));
		homeDTO.setTitle(rs.getString("title"));
		homeDTO.setSubTitle(rs.getString("sub_title"));
		homeDTO.setStatus(rs.getString("status"));
		homeDTO.setCourseType(rs.getString("course_type"));
		homeDTO.setLessonNum(rs.getInt("lesson_num"));
		homeDTO.setCredit(rs.getString("credit"));
		homeDTO.setCoverPicture(rs.getString("cover_picture"));
		homeDTO.setIntroduction(rs.getString("introduction"));
		homeDTO.setGoals(rs.getString("goals"));
		homeDTO.setRecommended(rs.getString("recommended"));
		homeDTO.setRecommendedSort(rs.getString("recommended_sort"));
		homeDTO.setCreatedBy(rs.getString("created_by"));
		if(rs.getTimestamp("created_date")==null){
			homeDTO.setCreatedDate(null);
		}else{
			homeDTO.setCreatedDate(rs.getTimestamp("created_date").toInstant());
		}
		
		homeDTO.setLastModifiedBy(rs.getString("last_modified_by"));
		if(rs.getTimestamp("last_modified_date") == null){
			homeDTO.setLastModifiedDate(null);
		}else{
			homeDTO.setLastModifiedDate(rs.getTimestamp("last_modified_date").toInstant());
		}
		homeDTO.setCountUserId(rs.getInt("count_user_id"));	
		homeDTO.setCourseReviewId(rs.getInt("course_review_id"));
		/*homeDTO.setCourseFavoriteId(rs.getLong("chapter_favorite_id"));
		homeDTO.setCourseFavoriteUserId(rs.getLong("chapter_favorite_userid"));
		homeDTO.setCourseReviewTitle(rs.getString("chapter_review_title"));
		homeDTO.setCourseReviewContent(rs.getString("chapter_review_content"));
		homeDTO.setCourseReviewRating(rs.getInt("chapter_review_rating"));
		homeDTO.setCourseReviewPrivacy(rs.getString("chapter_review_privacy"));		
		*/
		return homeDTO;
	}


    
    
}
