package com.zonesion.cloud.service.mapper;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.zonesion.cloud.domain.CourseLessonNote;
import com.zonesion.cloud.service.dto.CourseLessonNoteDTO;

/**   
 * @Title: CourseFavoriteMapper.java 
 * @Package com.zonesion.cloud.service.mapper 
 * @Description: TODO 
 * @author: cc  
 * @date: 2017年8月4日 下午2:53:25 
 */
@Service
public class CourseLessonNoteMapper {
	
	public CourseLessonNoteDTO courseLessonNoteDTO(CourseLessonNote courseLessonNote) {
        return new CourseLessonNoteDTO(courseLessonNote);
    }

    public List<CourseLessonNoteDTO> CourseLessonNotesToCourseLessonNoteDTOs(List<CourseLessonNote> courseLessonNotes) {
        return courseLessonNotes.stream()
            .filter(Objects::nonNull)
            .map(this::courseLessonNoteDTO)
            .collect(Collectors.toList());
    }
    
    public CourseLessonNote courseLessonNoteDTOToCourseLessonNote(CourseLessonNoteDTO courseLessonNoteDTO){
    	if (courseLessonNoteDTO == null){
    		return null;
    	} else {
    		CourseLessonNote courseLessonNote = new CourseLessonNote();
    		courseLessonNote.setId(courseLessonNoteDTO.getId());
    		courseLessonNote.setCourseId(courseLessonNoteDTO.getCourseId());
    		courseLessonNote.setUserId(courseLessonNoteDTO.getUserId());
    		courseLessonNote.setContent(courseLessonNoteDTO.getContent());
    		courseLessonNote.setLength(courseLessonNoteDTO.getLength());
    		courseLessonNote.setLikeNum(courseLessonNoteDTO.getLikeNum());
    		courseLessonNote.setIsPrivate(courseLessonNoteDTO.getIsPrivate());   		
    		return courseLessonNote;
    	}
    }
    
    public List<CourseLessonNote> courseLessonNoteDTOToCourseLessonNotes(List<CourseLessonNoteDTO> courseLessonNoteDTOs) {
        return courseLessonNoteDTOs.stream()
            .filter(Objects::nonNull)
            .map(this::courseLessonNoteDTOToCourseLessonNote)
            .collect(Collectors.toList());
    } 
    
    public CourseLessonNote courseLessonNoteFromId(Long id) {
    	if (id == null) {
    		return null;
    	}
    	CourseLessonNote courseLessonNote = new CourseLessonNote();
    	courseLessonNote.setId(id);
    	return courseLessonNote;
    }
}
