package com.zonesion.cloud.service.mapper;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.zonesion.cloud.domain.File;
import com.zonesion.cloud.service.dto.FileDTO;

/**   
 * @Title: CourseFavoriteMapper.java 
 * @Package com.zonesion.cloud.service.mapper 
 * @Description: TODO 
 * @author: cc  
 * @date: 2017年8月4日 下午2:53:25 
 */
@Service
public class FileMapper {
	public FileDTO fileToFileDTO(File file) {
        return new FileDTO(file);
    }

    public List<FileDTO> filesToFileDTOs(List<File> files) {
        return files.stream()
            .filter(Objects::nonNull)
            .map(this::fileToFileDTO)
            .collect(Collectors.toList());
    }
    
    public File fileDTOToFile(FileDTO fileDTO){
    	if (fileDTO == null){
    		return null;
    	} else {
    		File file = new File();
    		file.setId(fileDTO.getId());
    		file.setUserId(file.getUserId());
    		file.setUri(file.getUri());
    		file.setName(file.getName());
    		file.setMime(file.getMime());
    		file.setSize(file.getSize());
    		file.setStatus(file.getStatus());
    		return file;
    	}
    }
    
    public List<File> fileDTOsToFiles(List<FileDTO> fileDTOs) {
        return fileDTOs.stream()
            .filter(Objects::nonNull)
            .map(this::fileDTOToFile)
            .collect(Collectors.toList());
    }
    
    public File fileFromId(Long id){
    	if (id == null){
    		return null;
    	}
    	File file = new File();
    	file.setId(id);
    	return file;
    }
}
