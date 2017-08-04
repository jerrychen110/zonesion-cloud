package com.zonesion.cloud.service.mapper;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.zonesion.cloud.domain.CourseFavorite;
import com.zonesion.cloud.service.dto.CourseFavoriteDTO;

/**   
 * @Title: CourseFavoriteMapper.java 
 * @Package com.zonesion.cloud.service.mapper 
 * @Description: TODO 
 * @author: cc  
 * @date: 2017年8月4日 下午2:53:25 
 */
@Service
public class CourseFavoriteMapper {
	
	public CourseFavoriteDTO courseFavoriteToCourseFavoriteDTO(CourseFavorite courseFavorite){
		return new CourseFavoriteDTO(courseFavorite);
	}
	
	public List<CourseFavoriteDTO> courseFavoritesToCourseFavoriteDTOs(List<CourseFavorite> courseFavorites){
		return courseFavorites.stream()
	            .filter(Objects::nonNull)
	            .map(this::courseFavoriteToCourseFavoriteDTO)
	            .collect(Collectors.toList());
	}
	
	public CourseFavorite courseFavoriteDTOTocourseFavorite(CourseFavoriteDTO courseFavoriteDTO){
		if (courseFavoriteDTO == null){
			return null;
		} else {
			CourseFavorite courseFavorite = new CourseFavorite();
			courseFavorite.setId(courseFavoriteDTO.getId());
			courseFavorite.setUserId(courseFavoriteDTO.getUserId());
			courseFavorite.setCourse(courseFavoriteDTO.getCourse());
			return courseFavorite;
		}
	}
	
	public List<CourseFavorite> courseFavoriteDTOsToCourseFavorites(List<CourseFavoriteDTO> courseFavoriteDTOs){
		return courseFavoriteDTOs.stream()
	            .filter(Objects::nonNull)
	            .map(this::courseFavoriteDTOTocourseFavorite)
	            .collect(Collectors.toList());
	}
	
	public CourseFavorite courseFavoriteFromId(Long id){
		if (id == null) {
			return null;
		}
		CourseFavorite courseFavorite = new CourseFavorite();
		courseFavorite.setId(id);
		return courseFavorite;
	}
}
