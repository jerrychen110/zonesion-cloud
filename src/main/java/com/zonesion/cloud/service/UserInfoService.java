package com.zonesion.cloud.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zonesion.cloud.domain.Course;
import com.zonesion.cloud.repository.CourseDTORepository;
import com.zonesion.cloud.repository.UserInfoRepository;
import com.zonesion.cloud.repository.CourseDTORepository;
import com.zonesion.cloud.service.dto.CourseDTO;
import com.zonesion.cloud.service.dto.UserInfoDTO;

/**   
 * @Title: CourseJDBCService.java 
 * @Package com.zonesion.cloud.service 
 * @Description: TODO 
 * @author: cc  
 * @date: 2017年8月21日 上午8:49:50 
 */
@Service
@Transactional
public class UserInfoService {
	
	private final Logger log = LoggerFactory.getLogger(UserInfoService.class);

    private final UserInfoRepository userInfoRepository;

    public UserInfoService(UserInfoRepository userInfoRepository) {
        this.userInfoRepository = userInfoRepository;
    }
    
    public List<UserInfoDTO> findUserLearned(Long id) {
        log.debug("Request to get all user information");
        return userInfoRepository.findUserLearned(id);
    }
    
    public List<UserInfoDTO> findUserFavorited(Long id){
    	log.debug("Request to get user favorited");
        return userInfoRepository.findUserFavorited(id);
    }
    
}
