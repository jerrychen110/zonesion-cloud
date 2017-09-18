package com.zonesion.cloud.service;

import com.zonesion.cloud.domain.CourseReview;
import com.zonesion.cloud.repository.CourseReviewRepository;
import com.zonesion.cloud.service.dto.ext.CourseReviewExtDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing CourseReview.
 */
@Service
@Transactional
public class CourseReviewService {

    private final Logger log = LoggerFactory.getLogger(CourseReviewService.class);

    private final CourseReviewRepository courseReviewRepository;

    public CourseReviewService(CourseReviewRepository courseReviewRepository) {
        this.courseReviewRepository = courseReviewRepository;
    }
    
    @Inject
    private JdbcTemplate jdbcTemplate;

    /**
     * Save a courseReview.
     *
     * @param courseReview the entity to save
     * @return the persisted entity
     */
    public CourseReview save(CourseReview courseReview) {
        log.debug("Request to save CourseReview : {}", courseReview);
        return courseReviewRepository.save(courseReview);
    }

    /**
     *  Get all the courseReviews.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<CourseReview> findAll(Pageable pageable) {
        log.debug("Request to get all CourseReviews");
        return courseReviewRepository.findAll(pageable);
    }

    /**
     *  Get one courseReview by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true)
    public CourseReview findOne(Long id) {
        log.debug("Request to get CourseReview : {}", id);
        return courseReviewRepository.findOne(id);
    }

    /**
     *  Delete the  courseReview by id.
     *
     *  @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete CourseReview : {}", id);
        courseReviewRepository.delete(id);
    }
    
    @SuppressWarnings("unchecked")
	public Page<CourseReviewExtDTO> getCourseReviewsByCourseId(long id, Pageable pageable) {
    	StringBuilder sb = new StringBuilder();
    	sb.append("select tcr.*,tu.avatar from t_course_review tcr left join t_user tu on tcr.user_id=tu.id")
    	.append(" where tcr.course_id=").append(id).append(" order by tcr.created_date desc");
    	@SuppressWarnings("rawtypes")
		List<CourseReviewExtDTO> courseReviews = jdbcTemplate.query(sb.toString(), new RowMapper(){
            @Override
            public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
            	CourseReviewExtDTO courseReviewExtDTO  = new CourseReviewExtDTO();
            	courseReviewExtDTO.setId(rs.getLong("id"));
            	courseReviewExtDTO.setUserId(rs.getLong("user_id"));
            	courseReviewExtDTO.setCourseId(rs.getLong("course_id"));
            	courseReviewExtDTO.setAvatar(rs.getString("avatar")!=null?rs.getString("avatar"):"");
            	courseReviewExtDTO.setTitle(rs.getString("title"));
            	courseReviewExtDTO.setContent(rs.getString("content")!=null?rs.getString("content"):"");
            	courseReviewExtDTO.setRating(rs.getInt("rating")!=0?rs.getInt("rating"):0);
            	courseReviewExtDTO.setPrivacy(rs.getString("privacy")!=null?rs.getString("privacy"):"");
            	courseReviewExtDTO.setCreatedBy(rs.getString("created_by")!=null?rs.getString("created_by"):"");
            	courseReviewExtDTO.setCreatedDate(rs.getTimestamp("created_date")!=null?rs.getTimestamp("created_date").toInstant():null);
            	courseReviewExtDTO.setLastModifiedBy(rs.getString("last_modified_by")!=null?rs.getString("last_modified_by"):"");
            	courseReviewExtDTO.setLastModifiedDate(rs.getTimestamp("last_modified_date")!=null?rs.getTimestamp("last_modified_date").toInstant():null);
                return courseReviewExtDTO;
            }
		});
    	Page<CourseReviewExtDTO> result = new PageImpl<>(courseReviews, pageable, courseReviews.size());
    	return result;
    }
}
