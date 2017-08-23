package com.zonesion.cloud.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zonesion.cloud.domain.Course;
import com.zonesion.cloud.repository.HomeDTORepository;
import com.zonesion.cloud.service.dto.CourseDTO;
import com.zonesion.cloud.service.dto.HomeDTO;

/**   
 * @Title: CourseJDBCService.java 
 * @Package com.zonesion.cloud.service 
 * @Description: TODO 
 * @author: cc  
 * @date: 2017年8月21日 上午8:49:50 
 */
@Service
@Transactional
public class HomeDTOService {
	
	private final Logger log = LoggerFactory.getLogger(HomeDTOService.class);

    private final HomeDTORepository homeDTORepository;

    public HomeDTOService(HomeDTORepository homeDTORepository) {
        this.homeDTORepository = homeDTORepository;
    }
    
    public List<HomeDTO> findNewestCourse() {
        log.debug("Request to get all Courses");
        return homeDTORepository.findNewestCourse();
    }
    
    public List<HomeDTO> findHotCourse() {
        log.debug("Request to get all Courses");
        return homeDTORepository.findHotCourse();
    }
    
    public List<HomeDTO> findRecommendedCourse() {
        log.debug("Request to get all Courses");
        return homeDTORepository.findRecommendedCourse();
    }
}
