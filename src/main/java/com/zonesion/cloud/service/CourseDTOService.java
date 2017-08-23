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
import com.zonesion.cloud.repository.CourseDTORepository;
import com.zonesion.cloud.service.dto.CourseDTO;

/**   
 * @Title: CourseJDBCService.java 
 * @Package com.zonesion.cloud.service 
 * @Description: TODO 
 * @author: cc  
 * @date: 2017年8月21日 上午8:49:50 
 */
@Service
@Transactional
public class CourseDTOService {
	
	private final Logger log = LoggerFactory.getLogger(CourseDTOService.class);

    private final CourseDTORepository courseJDBCRepository;

    public CourseDTOService(CourseDTORepository courseJDBCRepository) {
        this.courseJDBCRepository = courseJDBCRepository;
    }
    
    public List<CourseDTO> findCourseChapters(Long courseId) {
        log.debug("Request to get all Courses");
        return courseJDBCRepository.findCourseChapters(courseId);
    }
}
