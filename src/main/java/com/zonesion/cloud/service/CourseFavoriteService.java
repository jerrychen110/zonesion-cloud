package com.zonesion.cloud.service;

import com.zonesion.cloud.domain.CourseFavorite;
import com.zonesion.cloud.repository.CourseFavoriteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing CourseFavorite.
 */
@Service
@Transactional
public class CourseFavoriteService {

    private final Logger log = LoggerFactory.getLogger(CourseFavoriteService.class);

    private final CourseFavoriteRepository courseFavoriteRepository;

    public CourseFavoriteService(CourseFavoriteRepository courseFavoriteRepository) {
        this.courseFavoriteRepository = courseFavoriteRepository;
    }

    /**
     * Save a courseFavorite.
     *
     * @param courseFavorite the entity to save
     * @return the persisted entity
     */
    public CourseFavorite save(CourseFavorite courseFavorite) {
        log.debug("Request to save CourseFavorite : {}", courseFavorite);
        return courseFavoriteRepository.save(courseFavorite);
    }

    /**
     *  Get all the courseFavorites.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<CourseFavorite> findAll(Pageable pageable) {
        log.debug("Request to get all CourseFavorites");
        return courseFavoriteRepository.findAll(pageable);
    }

    /**
     *  Get one courseFavorite by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true)
    public CourseFavorite findOne(Long id) {
        log.debug("Request to get CourseFavorite : {}", id);
        return courseFavoriteRepository.findOne(id);
    }

    /**
     *  Delete the  courseFavorite by id.
     *
     *  @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete CourseFavorite : {}", id);
        courseFavoriteRepository.delete(id);
    }
}
