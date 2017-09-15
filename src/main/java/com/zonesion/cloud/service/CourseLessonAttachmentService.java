package com.zonesion.cloud.service;

import com.zonesion.cloud.domain.CourseLessonAttachment;
import com.zonesion.cloud.repository.CourseLessonAttachmentRepository;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing CourseLessonAttachment.
 */
@Service
@Transactional
public class CourseLessonAttachmentService {

    private final Logger log = LoggerFactory.getLogger(CourseLessonAttachmentService.class);

    private final CourseLessonAttachmentRepository courseLessonAttachmentRepository;

    public CourseLessonAttachmentService(CourseLessonAttachmentRepository courseLessonAttachmentRepository) {
        this.courseLessonAttachmentRepository = courseLessonAttachmentRepository;
    }

    /**
     * Save a courseLessonAttachment.
     *
     * @param courseLessonAttachment the entity to save
     * @return the persisted entity
     */
    public CourseLessonAttachment save(CourseLessonAttachment courseLessonAttachment) {
        log.debug("Request to save CourseLessonAttachment : {}", courseLessonAttachment);
        return courseLessonAttachmentRepository.save(courseLessonAttachment);
    }

    /**
     *  Get all the courseLessonAttachments.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<CourseLessonAttachment> findAll(Pageable pageable) {
        log.debug("Request to get all CourseLessonAttachments");
        return courseLessonAttachmentRepository.findAll(pageable);
    }

    /**
     *  Get one courseLessonAttachment by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true)
    public CourseLessonAttachment findOne(Long id) {
        log.debug("Request to get CourseLessonAttachment : {}", id);
        return courseLessonAttachmentRepository.findOne(id);
    }

    /**
     *  Delete the  courseLessonAttachment by id.
     *
     *  @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete CourseLessonAttachment : {}", id);
        courseLessonAttachmentRepository.delete(id);
    }
    
    /**
     * 根据targetType和targetId查询附件列表
     * @param targetType(course、lesson)
     * @param targetId
     * @param pageable
     * @return
     */
    @Transactional(readOnly = true)
    public Page<CourseLessonAttachment> findAllByTargetTypeAndTargetId(String targetType, long targetId, Pageable pageable) {
        log.debug("Request to get all CourseLessonAttachments");
        return courseLessonAttachmentRepository.findAllByTargetTypeAndTargetIdOrderByCreatedDateDesc(targetType, targetId, pageable);
    }
    
    /**
     * 下载附件资料
     * @param id
     * @param request
     * @param response
     * @throws IOException
     */
    public void downloadAttchement(long id, HttpServletRequest request, HttpServletResponse response) throws IOException {
    	CourseLessonAttachment courseLessonAttachment = courseLessonAttachmentRepository.findOne(id);
    	//设置文件MIME类型
        response.setContentType(request.getServletContext().getMimeType(courseLessonAttachment.getTitle()));
        //设置Content-Disposition
        response.setHeader("Content-Disposition", "attachment;filename="+courseLessonAttachment.getTitle());
        //读取目标文件，通过response将目标文件写到客户端
        //获取目标文件的绝对路径
        String fullFileName = request.getServletContext().getRealPath(courseLessonAttachment.getFileUri());
        //读取文件
        InputStream in = new FileInputStream(fullFileName);
        OutputStream out = response.getOutputStream();
        //写文件
        int b;
        while((b=in.read())!= -1)
        {  
            out.write(b);
        }
        in.close();
        out.close();
    }
}
