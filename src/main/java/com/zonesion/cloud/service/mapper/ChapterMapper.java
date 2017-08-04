package com.zonesion.cloud.service.mapper;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.zonesion.cloud.domain.Chapter;
import com.zonesion.cloud.service.dto.ChapterDTO;

/**   
 * @Title: ChapterMapper.java 
 * @Package com.zonesion.cloud.service.mapper 
 * @Description: TODO 
 * @author: cc  
 * @date: 2017年8月4日 上午11:48:14 
 */
public class ChapterMapper {
	
	public ChapterDTO chapterToChapterDTO(Chapter chapter){
		return new ChapterDTO(chapter);
	}
	
	public List<ChapterDTO> chapterToChapterDTOs(List<Chapter> chapters) {
        return chapters.stream()
            .filter(Objects::nonNull)
            .map(this::chapterToChapterDTO)
            .collect(Collectors.toList());
    }
	
	public Chapter chapterDTOToChapter(ChapterDTO chapterDTO){
		if (chapterDTO == null){
			return null;
		} else{
			Chapter chapter =new Chapter();
			chapter.setId(chapterDTO.getId());
			chapter.setCourseId(chapterDTO.getCourseId());
			chapter.setUserId(chapterDTO.getUserId());
			chapter.setChapterType(chapterDTO.getChapterType());
			chapter.setNumber(chapterDTO.getNumber());
			chapter.setSeq(chapterDTO.getSeq());
			chapter.setTitle(chapterDTO.getTitle());
			return chapter;
		}
	}
	
	public List<Chapter> chapterDTOsToChapters(List<ChapterDTO> chapterDTOs) {
        return chapterDTOs.stream()
            .filter(Objects::nonNull)
            .map(this::chapterDTOToChapter)
            .collect(Collectors.toList());
    }
	
	public Chapter chapterFromId(Long id){
		if (id == null){
			return null;
		}
		Chapter chapter = new Chapter();
		chapter.setId(id);
		return chapter;
	}
}
