package com.zonesion.cloud.service.mapper;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.zonesion.cloud.domain.Major;
import com.zonesion.cloud.service.dto.MajorDTO;

/**   
 * @Title: CourseFavoriteMapper.java 
 * @Package com.zonesion.cloud.service.mapper 
 * @Description: TODO 
 * @author: cc  
 * @date: 2017年8月4日 下午2:53:25 
 */
@Service
public class MajorMapper {
	
	public MajorDTO majorToMajorDTO(Major major){
		return new MajorDTO(major);
	}
	
	public List<MajorDTO> majorToMajorDTOs(List<Major> majors){
		return majors.stream()
	            .filter(Objects::nonNull)
	            .map(this::majorToMajorDTO)
	            .collect(Collectors.toList());
	}
	
	public Major majorDTOToMajor(MajorDTO majorDTO){
		if (majorDTO == null){
			return null;
		} else {
			Major major = new Major();
			major.setId(majorDTO.getId());
			major.setMajor(majorDTO.getMajor());
			return major;
		}
	}
	
	public List<Major> majorDTOsToMajor(List<MajorDTO> majorDTOs){
		return majorDTOs.stream()
	            .filter(Objects::nonNull)
	            .map(this::majorDTOToMajor)
	            .collect(Collectors.toList());
	}
	
	public Major majorFromId(Long id){
		if (id == null){
			return null;
		}
		Major major = new Major();
		major.setId(id);
		return major;
	}
}
