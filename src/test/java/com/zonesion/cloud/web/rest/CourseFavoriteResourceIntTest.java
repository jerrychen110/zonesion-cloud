package com.zonesion.cloud.web.rest;

import com.zonesion.cloud.ZonesionCloudApplicationApp;

import com.zonesion.cloud.domain.CourseFavorite;
import com.zonesion.cloud.domain.Course;
import com.zonesion.cloud.repository.CourseFavoriteRepository;
import com.zonesion.cloud.service.CourseFavoriteService;
import com.zonesion.cloud.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the CourseFavoriteResource REST controller.
 *
 * @see CourseFavoriteResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ZonesionCloudApplicationApp.class)
public class CourseFavoriteResourceIntTest {

    private static final Long DEFAULT_USER_ID = 1L;
    private static final Long UPDATED_USER_ID = 2L;

    @Autowired
    private CourseFavoriteRepository courseFavoriteRepository;

    @Autowired
    private CourseFavoriteService courseFavoriteService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restCourseFavoriteMockMvc;

    private CourseFavorite courseFavorite;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        CourseFavoriteResource courseFavoriteResource = new CourseFavoriteResource(courseFavoriteService);
        this.restCourseFavoriteMockMvc = MockMvcBuilders.standaloneSetup(courseFavoriteResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CourseFavorite createEntity(EntityManager em) {
        CourseFavorite courseFavorite = new CourseFavorite()
            .userId(DEFAULT_USER_ID);
        // Add required entity
        Course course = CourseResourceIntTest.createEntity(em);
        em.persist(course);
        em.flush();
        courseFavorite.setCourse(course);
        return courseFavorite;
    }

    @Before
    public void initTest() {
        courseFavorite = createEntity(em);
    }

    @Test
    @Transactional
    public void createCourseFavorite() throws Exception {
        int databaseSizeBeforeCreate = courseFavoriteRepository.findAll().size();

        // Create the CourseFavorite
        restCourseFavoriteMockMvc.perform(post("/api/course-favorites")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(courseFavorite)))
            .andExpect(status().isCreated());

        // Validate the CourseFavorite in the database
        List<CourseFavorite> courseFavoriteList = courseFavoriteRepository.findAll();
        assertThat(courseFavoriteList).hasSize(databaseSizeBeforeCreate + 1);
        CourseFavorite testCourseFavorite = courseFavoriteList.get(courseFavoriteList.size() - 1);
        assertThat(testCourseFavorite.getUserId()).isEqualTo(DEFAULT_USER_ID);
    }

    @Test
    @Transactional
    public void createCourseFavoriteWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = courseFavoriteRepository.findAll().size();

        // Create the CourseFavorite with an existing ID
        courseFavorite.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCourseFavoriteMockMvc.perform(post("/api/course-favorites")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(courseFavorite)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<CourseFavorite> courseFavoriteList = courseFavoriteRepository.findAll();
        assertThat(courseFavoriteList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkUserIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = courseFavoriteRepository.findAll().size();
        // set the field null
        courseFavorite.setUserId(null);

        // Create the CourseFavorite, which fails.

        restCourseFavoriteMockMvc.perform(post("/api/course-favorites")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(courseFavorite)))
            .andExpect(status().isBadRequest());

        List<CourseFavorite> courseFavoriteList = courseFavoriteRepository.findAll();
        assertThat(courseFavoriteList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllCourseFavorites() throws Exception {
        // Initialize the database
        courseFavoriteRepository.saveAndFlush(courseFavorite);

        // Get all the courseFavoriteList
        restCourseFavoriteMockMvc.perform(get("/api/course-favorites?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(courseFavorite.getId().intValue())))
            .andExpect(jsonPath("$.[*].userId").value(hasItem(DEFAULT_USER_ID.intValue())));
    }

    @Test
    @Transactional
    public void getCourseFavorite() throws Exception {
        // Initialize the database
        courseFavoriteRepository.saveAndFlush(courseFavorite);

        // Get the courseFavorite
        restCourseFavoriteMockMvc.perform(get("/api/course-favorites/{id}", courseFavorite.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(courseFavorite.getId().intValue()))
            .andExpect(jsonPath("$.userId").value(DEFAULT_USER_ID.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingCourseFavorite() throws Exception {
        // Get the courseFavorite
        restCourseFavoriteMockMvc.perform(get("/api/course-favorites/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCourseFavorite() throws Exception {
        // Initialize the database
        courseFavoriteService.save(courseFavorite);

        int databaseSizeBeforeUpdate = courseFavoriteRepository.findAll().size();

        // Update the courseFavorite
        CourseFavorite updatedCourseFavorite = courseFavoriteRepository.findOne(courseFavorite.getId());
        updatedCourseFavorite
            .userId(UPDATED_USER_ID);

        restCourseFavoriteMockMvc.perform(put("/api/course-favorites")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedCourseFavorite)))
            .andExpect(status().isOk());

        // Validate the CourseFavorite in the database
        List<CourseFavorite> courseFavoriteList = courseFavoriteRepository.findAll();
        assertThat(courseFavoriteList).hasSize(databaseSizeBeforeUpdate);
        CourseFavorite testCourseFavorite = courseFavoriteList.get(courseFavoriteList.size() - 1);
        assertThat(testCourseFavorite.getUserId()).isEqualTo(UPDATED_USER_ID);
    }

    @Test
    @Transactional
    public void updateNonExistingCourseFavorite() throws Exception {
        int databaseSizeBeforeUpdate = courseFavoriteRepository.findAll().size();

        // Create the CourseFavorite

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restCourseFavoriteMockMvc.perform(put("/api/course-favorites")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(courseFavorite)))
            .andExpect(status().isCreated());

        // Validate the CourseFavorite in the database
        List<CourseFavorite> courseFavoriteList = courseFavoriteRepository.findAll();
        assertThat(courseFavoriteList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteCourseFavorite() throws Exception {
        // Initialize the database
        courseFavoriteService.save(courseFavorite);

        int databaseSizeBeforeDelete = courseFavoriteRepository.findAll().size();

        // Get the courseFavorite
        restCourseFavoriteMockMvc.perform(delete("/api/course-favorites/{id}", courseFavorite.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<CourseFavorite> courseFavoriteList = courseFavoriteRepository.findAll();
        assertThat(courseFavoriteList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CourseFavorite.class);
        CourseFavorite courseFavorite1 = new CourseFavorite();
        courseFavorite1.setId(1L);
        CourseFavorite courseFavorite2 = new CourseFavorite();
        courseFavorite2.setId(courseFavorite1.getId());
        assertThat(courseFavorite1).isEqualTo(courseFavorite2);
        courseFavorite2.setId(2L);
        assertThat(courseFavorite1).isNotEqualTo(courseFavorite2);
        courseFavorite1.setId(null);
        assertThat(courseFavorite1).isNotEqualTo(courseFavorite2);
    }
}
