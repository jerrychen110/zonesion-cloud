package com.zonesion.cloud.service;

import com.zonesion.cloud.domain.Chapter;
import com.zonesion.cloud.repository.ChapterRepository;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing Chapter.
 */
@Service
@Transactional
public class ChapterService {

    private final Logger log = LoggerFactory.getLogger(ChapterService.class);

    private final ChapterRepository chapterRepository;

    public ChapterService(ChapterRepository chapterRepository) {
        this.chapterRepository = chapterRepository;
    }
    
    @Inject
    private JdbcTemplate jdbcTemplate;

    /**
     * Save a chapter.
     *
     * @param chapter the entity to save
     * @return the persisted entity
     */
    public Chapter save(Chapter chapter) {
        log.debug("Request to save Chapter : {}", chapter);
        return chapterRepository.save(chapter);
    }

    /**
     *  Get all the chapters.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<Chapter> findAll(Pageable pageable) {
        log.debug("Request to get all Chapters");
        return chapterRepository.findAll(pageable);
    }

    /**
     *  Get one chapter by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true)
    public Chapter findOne(Long id) {
        log.debug("Request to get Chapter : {}", id);
        return chapterRepository.findOne(id);
    }

    /**
     *  Delete the  chapter by id.
     *
     *  @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Chapter : {}", id);
        chapterRepository.delete(id);
    }
    
    public int getMaxNumberByCourseId(Long id, String chapterType) {
    	StringBuilder sb = new StringBuilder();
    	sb.append("select max(number) from t_chapter where course_id=")
    	.append(id).append(" and chapter_type='").append(chapterType).append("'");
    	return jdbcTemplate.queryForObject(sb.toString(), Integer.class);
    }
    
    public int getMaxSeqByCourseId(Long id, String chapterType) {
    	StringBuilder sb = new StringBuilder();
    	sb.append("select max(seq) from t_chapter where course_id=")
    	.append(id).append(" and chapter_type='").append(chapterType).append("'");
    	return jdbcTemplate.queryForObject(sb.toString(), Integer.class);
    }
}
