package com.zonesion.cloud.service.mapper;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.zonesion.cloud.domain.CourseLessonAttachment;
import com.zonesion.cloud.service.dto.CourseLessonAttachmentDTO;

/**   
 * @Title: CourseFavoriteMapper.java 
 * @Package com.zonesion.cloud.service.mapper 
 * @Description: TODO 
 * @author: cc  
 * @date: 2017年8月4日 下午2:53:25 
 */
@Service
public class CourseLessonAttachmentMapper {
	public CourseLessonAttachmentDTO courseLessonAttachmentToCourseLessonAttachmentDTO(CourseLessonAttachment courseLessonAttachment) {
        return new CourseLessonAttachmentDTO(courseLessonAttachment);
    }

    public List<CourseLessonAttachmentDTO> courseLessonAttachmentsToCourseLessonAttachmentDTOs(List<CourseLessonAttachment> courseLessonAttachments) {
        return courseLessonAttachments.stream()
            .filter(Objects::nonNull)
            .map(this::courseLessonAttachmentToCourseLessonAttachmentDTO)
            .collect(Collectors.toList());
    }
    
    public CourseLessonAttachment courseLessonAttachmentDTOToCourseLessonAttachment(CourseLessonAttachmentDTO courseLessonAttachmentDTO){
    	if (courseLessonAttachmentDTO == null){
    		return null;
    	} else {
    		CourseLessonAttachment courseLessonAttachment = new CourseLessonAttachment();
    		courseLessonAttachment.setId(courseLessonAttachmentDTO.getId());
    		courseLessonAttachment.setCourseId(courseLessonAttachmentDTO.getId());
    		courseLessonAttachment.setUserId(courseLessonAttachmentDTO.getUserId());
    		courseLessonAttachment.setTitle(courseLessonAttachmentDTO.getTitle());
    		courseLessonAttachment.setDescription(courseLessonAttachmentDTO.getDescription());
    		courseLessonAttachment.setLink(courseLessonAttachmentDTO.getLink());
    		courseLessonAttachment.setFileId(courseLessonAttachmentDTO.getFileId());
    		courseLessonAttachment.setFileUri(courseLessonAttachmentDTO.getFileUri());
    		courseLessonAttachment.setFileMime(courseLessonAttachmentDTO.getFileMime());
    		courseLessonAttachment.setFileSize(courseLessonAttachmentDTO.getFileSize());
    		return courseLessonAttachment;
    	}
    }
    
    public List<CourseLessonAttachment> courseLessonAttachmentDTOsToCourseLessonAttachments(List<CourseLessonAttachmentDTO> courseLessonAttachmentDTOs) {
        return courseLessonAttachmentDTOs.stream()
            .filter(Objects::nonNull)
            .map(this::courseLessonAttachmentDTOToCourseLessonAttachment)
            .collect(Collectors.toList());
    }
    
    public CourseLessonAttachment courseLessonAttachmentFromId(Long id) {
    	if (id == null){
    		return null;
    	}
    	CourseLessonAttachment courseLessonAttachment = new CourseLessonAttachment();
    	courseLessonAttachment.setId(id);
    	return courseLessonAttachment;
    }
}
