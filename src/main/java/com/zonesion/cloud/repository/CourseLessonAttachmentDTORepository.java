package com.zonesion.cloud.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.zonesion.cloud.service.dto.CourseLessonAttachmentDTO;

/**   
 * @Title: CourseLessonAttachmentDTORepository.java 
 * @Package com.zonesion.cloud.repository 
 * @Description: TODO 
 * @author: cc  
 * @date: 2017年9月8日 上午11:01:51 
 */
@Repository
public class CourseLessonAttachmentDTORepository {
	
	@Autowired
	
    private JdbcTemplate jdbcTemplate;
	
	@Transactional
    public List<CourseLessonAttachmentDTO> findCourseLessonAttachment(Long id) {
        return jdbcTemplate.query("SELECT cla.*, f.id files_id, f.uri files_uri, f.name files_name, f.mime files_mime, f.size files_size, f.status files_status, f.created_by files_created_by, f.created_date files_created_date, f.last_modified_by files_last_modified_by, f.last_modified_date files_last_modified_date FROM t_course_lesson_attachment cla LEFT JOIN t_file f ON f.id = cla.file_id LEFT JOIN t_course c ON c.id = cla.course_id WHERE c.id = ?", new Object[]{id}, new CourseLessonAttachmentRowMapper());
    }

}

class CourseLessonAttachmentRowMapper implements RowMapper<CourseLessonAttachmentDTO> {

	@Override
	public CourseLessonAttachmentDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		CourseLessonAttachmentDTO courseLessonAttachmentDTO = new CourseLessonAttachmentDTO();
		courseLessonAttachmentDTO.setId(rs.getLong("id"));
		courseLessonAttachmentDTO.setCourseId(rs.getLong("course_id"));
		courseLessonAttachmentDTO.setUserId(rs.getLong("user_id"));
		courseLessonAttachmentDTO.setTitle(rs.getString("title"));
		courseLessonAttachmentDTO.setDescription(rs.getString("description"));
		courseLessonAttachmentDTO.setLink(rs.getString("link"));
		courseLessonAttachmentDTO.setFileId(rs.getLong("file_id"));
		courseLessonAttachmentDTO.setFileUri(rs.getString("file_uri"));
		courseLessonAttachmentDTO.setFileMime(rs.getString("file_mime"));
		courseLessonAttachmentDTO.setFileSize(rs.getInt("file_size"));
		courseLessonAttachmentDTO.setCourseLessonId(rs.getLong("course_lesson_id"));
		courseLessonAttachmentDTO.setCreatedBy(rs.getString("created_by"));
		if(rs.getTimestamp("created_date")==null){
			courseLessonAttachmentDTO.setCreatedDate(null);
		}else{
			courseLessonAttachmentDTO.setCreatedDate(rs.getTimestamp("created_date").toInstant());		
		}
		
		courseLessonAttachmentDTO.setLastModifiedBy(rs.getString("last_modified_by"));
		
		if(rs.getTime("last_modified_date")==null){
			courseLessonAttachmentDTO.setLastModifiedDate(null);
		}else{
			courseLessonAttachmentDTO.setLastModifiedDate(rs.getTimestamp("last_modified_date").toInstant());
		}
		courseLessonAttachmentDTO.setFilesId(rs.getLong("files_id"));
		courseLessonAttachmentDTO.setFilesUri(rs.getString("files_uri"));
		courseLessonAttachmentDTO.setFilesName(rs.getString("files_name"));
		courseLessonAttachmentDTO.setFilesMime(rs.getString("files_mime"));
		courseLessonAttachmentDTO.setFilesSize(rs.getInt("files_size"));
		courseLessonAttachmentDTO.setFilesStatus(rs.getString("files_status"));
		
		courseLessonAttachmentDTO.setFilesCreatedBy(rs.getString("files_created_by"));
		if(rs.getTimestamp("files_created_date")==null){
			courseLessonAttachmentDTO.setFilesCreatedDate(null);
		}else{
			courseLessonAttachmentDTO.setFilesCreatedDate(rs.getTimestamp("files_created_date").toInstant());		
		}
		
		courseLessonAttachmentDTO.setFilesLastModifiedBy(rs.getString("files_last_modified_by"));
		
		if(rs.getTime("files_last_modified_date")==null){
			courseLessonAttachmentDTO.setFilesLastModifiedDate(null);
		}else{
			courseLessonAttachmentDTO.setFilesLastModifiedDate(rs.getTimestamp("files_last_modified_date").toInstant());
		}
		
		return courseLessonAttachmentDTO;
	}
	
}
