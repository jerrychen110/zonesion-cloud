package com.zonesion.cloud.service;

import com.zonesion.cloud.domain.Major;
import com.zonesion.cloud.repository.MajorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing Major.
 */
@Service
@Transactional
public class MajorService {

    private final Logger log = LoggerFactory.getLogger(MajorService.class);

    private final MajorRepository majorRepository;

    public MajorService(MajorRepository majorRepository) {
        this.majorRepository = majorRepository;
    }

    /**
     * Save a major.
     *
     * @param major the entity to save
     * @return the persisted entity
     */
    public Major save(Major major) {
        log.debug("Request to save Major : {}", major);
        return majorRepository.save(major);
    }

    /**
     *  Get all the majors.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<Major> findAll(Pageable pageable) {
        log.debug("Request to get all Majors");
        return majorRepository.findAll(pageable);
    }

    /**
     *  Get one major by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true)
    public Major findOne(Long id) {
        log.debug("Request to get Major : {}", id);
        return majorRepository.findOne(id);
    }

    /**
     *  Delete the  major by id.
     *
     *  @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Major : {}", id);
        majorRepository.delete(id);
    }
}
