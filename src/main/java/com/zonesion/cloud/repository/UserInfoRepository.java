package com.zonesion.cloud.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.zonesion.cloud.service.dto.UserInfoDTO;

/**   
 * @Title: UserInfoRepository.java 
 * @Package com.zonesion.cloud.repository 
 * @Description: TODO 
 * @author: cc  
 * @date: 2017年8月23日 上午10:22:08 
 */
@Repository
public class UserInfoRepository {
	@Autowired
	
    private JdbcTemplate jdbcTemplate;

    public List<UserInfoDTO> findUserLearned(Long id) {
        return jdbcTemplate.query("SELECT u.*, c.id course_id, c.title course_title, c.sub_title course_sub_title, c.status course_status, c.course_type, c.lesson_num course_lesson_num, c.credit course_credit, c.cover_picture course_cover_picture, c.introduction course_introduction, c.goals course_goals, c.recommended course_recommended, c.recommended_sort course_recommended_sort, c.created_by course_created_by, c.created_date course_created_date, c.last_modified_by course_last_modified_by, c.last_modified_date course_last_modified_date, cf.id course_favorite_id, cf.course_id course_favorite_course_id, (cll.user_id) count_user_id, count(DISTINCT(cr.id)) count_course_review_id FROM t_course_lesson cll LEFT JOIN t_chapter ch ON ch.id = cll.chapter_id LEFT JOIN t_course c ON c.id = ch.course_id LEFT JOIN t_user u ON u.id = c.user_id LEFT JOIN t_course_review cr ON cr.course_id = c.id LEFT JOIN t_course_favorite cf ON cf.course_id = c.id WHERE c.id = ? GROUP BY c.id, cll.user_id, cf.id", new Object[]{id}, new UserInfoMapper());
    }
	
	public List<UserInfoDTO> findUserFavorited(Long id){
		return jdbcTemplate.query("SELECT u.*, c.id course_id, c.title course_title, c.sub_title course_sub_title, c.status course_status, c.course_type, c.lesson_num course_lesson_num, c.credit course_credit, c.cover_picture course_cover_picture, c.introduction course_introduction, c.goals course_goals, c.recommended course_recommended, c.recommended_sort course_recommended_sort, c.created_by course_created_by, c.created_date course_created_date, c.last_modified_by course_last_modified_by, c.last_modified_date course_last_modified_date, cf.id course_favorite_id, cf.course_id course_favorite_course_id, count(DISTINCT(cll.user_id)) count_user_id, count(DISTINCT(cr.id)) count_course_review_id FROM t_user u LEFT JOIN t_course_favorite cf ON cf.user_id = u.id LEFT JOIN t_course c ON c.id = cf.course_id LEFT JOIN t_chapter ch ON ch.course_id = c.id LEFT JOIN t_course_lesson cl ON cl.chapter_id = ch.id LEFT JOIN t_course_lesson_learn cll ON cll.course_id = c.id LEFT JOIN t_course_review cr ON cr.course_id = c.id WHERE c.id = ? GROUP BY cf.id;", new Object[]{id}, new UserInfoMapper());
	}
}

class UserInfoMapper implements RowMapper<UserInfoDTO> {

	@Override
	public UserInfoDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		UserInfoDTO userInfoDTO = new UserInfoDTO();
		userInfoDTO.setId(rs.getLong("id"));
		userInfoDTO.setLogin(rs.getString("login"));
		userInfoDTO.setName(rs.getString("name"));
		userInfoDTO.setAvatar(rs.getString("avatar"));
		userInfoDTO.setEmail(rs.getString("email"));
		userInfoDTO.setMobile(rs.getString("mobile"));
		userInfoDTO.setSex(rs.getString("sex"));
		userInfoDTO.setStaffNo(rs.getString("staff_no"));
		userInfoDTO.setMajor(rs.getString("major"));
		userInfoDTO.setSchool(rs.getString("school"));
		userInfoDTO.setCreatedBy(rs.getString("created_by"));

		if(rs.getTimestamp("created_date")==null){
			userInfoDTO.setCreatedDate(null);
		}else{
			userInfoDTO.setCreatedDate(rs.getTimestamp("created_date").toInstant());		
		}
		userInfoDTO.setLastModifiedBy(rs.getString("last_modified_by"));

		if(rs.getTimestamp("last_modified_date")==null){
			userInfoDTO.setLastModifiedDate(null);
		}else{
			userInfoDTO.setLastModifiedDate(rs.getTimestamp("last_modified_date").toInstant());		
		}
		userInfoDTO.setCourseId(rs.getLong("course_id"));
		userInfoDTO.setCourseTitle(rs.getString("course_title"));
		userInfoDTO.setCourseSubTitle(rs.getString("course_sub_title"));
		userInfoDTO.setCourseStatus(rs.getString("course_status"));
		userInfoDTO.setCourseType(rs.getString("course_type"));
		userInfoDTO.setCourseLessonNum(rs.getInt("course_lesson_num"));
		userInfoDTO.setCourseCredit(rs.getString("course_credit"));
		userInfoDTO.setCourseCoverPicture(rs.getString("course_cover_picture"));
		userInfoDTO.setCourseIntroduction(rs.getString("course_introduction"));
		userInfoDTO.setCourseGoals(rs.getString("course_goals"));
		userInfoDTO.setCourseRecommended(rs.getString("course_recommended"));
		userInfoDTO.setCourseRecommendedSort(rs.getString("course_recommended_sort"));
		userInfoDTO.setCourseCreatedBy(rs.getString("course_created_by"));

		if(rs.getTimestamp("created_date")==null){
			userInfoDTO.setCourseCreatedDate(null);
		}else{
			userInfoDTO.setCourseCreatedDate(rs.getTimestamp("created_date").toInstant());		
		}
		userInfoDTO.setCourseLastModifiedBy(rs.getString("course_last_modified_by"));

		if(rs.getTimestamp("course_last_modified_date")==null){
			userInfoDTO.setCourseLastModifiedDate(null);
		}else{
			userInfoDTO.setCourseLastModifiedDate(rs.getTimestamp("course_last_modified_date").toInstant());		
		}
		
		userInfoDTO.setCourseFavoriteId(rs.getLong("course_favorite_id"));
		userInfoDTO.setCourseFavoriteCourseId(rs.getLong("course_favorite_course_id"));
		userInfoDTO.setCountUserId(rs.getInt("count_user_id"));
		userInfoDTO.setCountCourseReviewId(rs.getInt("count_course_review_id"));
		return userInfoDTO;		
		
	}
	
}