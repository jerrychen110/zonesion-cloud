package com.zonesion.cloud.web.rest;

import com.zonesion.cloud.ZonesionCloudApplicationApp;

import com.zonesion.cloud.domain.CourseLessonNoteLike;
import com.zonesion.cloud.domain.CourseLessonNote;
import com.zonesion.cloud.repository.CourseLessonNoteLikeRepository;
import com.zonesion.cloud.service.CourseLessonNoteLikeService;
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
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the CourseLessonNoteLikeResource REST controller.
 *
 * @see CourseLessonNoteLikeResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ZonesionCloudApplicationApp.class)
public class CourseLessonNoteLikeResourceIntTest {

    private static final Long DEFAULT_USER_ID = 1L;
    private static final Long UPDATED_USER_ID = 2L;

    private static final Instant DEFAULT_CREATED_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATED_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private CourseLessonNoteLikeRepository courseLessonNoteLikeRepository;

    @Autowired
    private CourseLessonNoteLikeService courseLessonNoteLikeService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restCourseLessonNoteLikeMockMvc;

    private CourseLessonNoteLike courseLessonNoteLike;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        CourseLessonNoteLikeResource courseLessonNoteLikeResource = new CourseLessonNoteLikeResource(courseLessonNoteLikeService);
        this.restCourseLessonNoteLikeMockMvc = MockMvcBuilders.standaloneSetup(courseLessonNoteLikeResource)
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
    public static CourseLessonNoteLike createEntity(EntityManager em) {
        CourseLessonNoteLike courseLessonNoteLike = new CourseLessonNoteLike()
            .userId(DEFAULT_USER_ID)
            .createdTime(DEFAULT_CREATED_TIME);
        // Add required entity
        CourseLessonNote courseLessonNote = CourseLessonNoteResourceIntTest.createEntity(em);
        em.persist(courseLessonNote);
        em.flush();
        courseLessonNoteLike.setCourseLessonNote(courseLessonNote);
        return courseLessonNoteLike;
    }

    @Before
    public void initTest() {
        courseLessonNoteLike = createEntity(em);
    }

    @Test
    @Transactional
    public void createCourseLessonNoteLike() throws Exception {
        int databaseSizeBeforeCreate = courseLessonNoteLikeRepository.findAll().size();

        // Create the CourseLessonNoteLike
        restCourseLessonNoteLikeMockMvc.perform(post("/api/course-lesson-note-likes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(courseLessonNoteLike)))
            .andExpect(status().isCreated());

        // Validate the CourseLessonNoteLike in the database
        List<CourseLessonNoteLike> courseLessonNoteLikeList = courseLessonNoteLikeRepository.findAll();
        assertThat(courseLessonNoteLikeList).hasSize(databaseSizeBeforeCreate + 1);
        CourseLessonNoteLike testCourseLessonNoteLike = courseLessonNoteLikeList.get(courseLessonNoteLikeList.size() - 1);
        assertThat(testCourseLessonNoteLike.getUserId()).isEqualTo(DEFAULT_USER_ID);
        assertThat(testCourseLessonNoteLike.getCreatedTime()).isEqualTo(DEFAULT_CREATED_TIME);
    }

    @Test
    @Transactional
    public void createCourseLessonNoteLikeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = courseLessonNoteLikeRepository.findAll().size();

        // Create the CourseLessonNoteLike with an existing ID
        courseLessonNoteLike.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCourseLessonNoteLikeMockMvc.perform(post("/api/course-lesson-note-likes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(courseLessonNoteLike)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<CourseLessonNoteLike> courseLessonNoteLikeList = courseLessonNoteLikeRepository.findAll();
        assertThat(courseLessonNoteLikeList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkUserIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = courseLessonNoteLikeRepository.findAll().size();
        // set the field null
        courseLessonNoteLike.setUserId(null);

        // Create the CourseLessonNoteLike, which fails.

        restCourseLessonNoteLikeMockMvc.perform(post("/api/course-lesson-note-likes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(courseLessonNoteLike)))
            .andExpect(status().isBadRequest());

        List<CourseLessonNoteLike> courseLessonNoteLikeList = courseLessonNoteLikeRepository.findAll();
        assertThat(courseLessonNoteLikeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCreatedTimeIsRequired() throws Exception {
        int databaseSizeBeforeTest = courseLessonNoteLikeRepository.findAll().size();
        // set the field null
        courseLessonNoteLike.setCreatedTime(null);

        // Create the CourseLessonNoteLike, which fails.

        restCourseLessonNoteLikeMockMvc.perform(post("/api/course-lesson-note-likes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(courseLessonNoteLike)))
            .andExpect(status().isBadRequest());

        List<CourseLessonNoteLike> courseLessonNoteLikeList = courseLessonNoteLikeRepository.findAll();
        assertThat(courseLessonNoteLikeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllCourseLessonNoteLikes() throws Exception {
        // Initialize the database
        courseLessonNoteLikeRepository.saveAndFlush(courseLessonNoteLike);

        // Get all the courseLessonNoteLikeList
        restCourseLessonNoteLikeMockMvc.perform(get("/api/course-lesson-note-likes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(courseLessonNoteLike.getId().intValue())))
            .andExpect(jsonPath("$.[*].userId").value(hasItem(DEFAULT_USER_ID.intValue())))
            .andExpect(jsonPath("$.[*].createdTime").value(hasItem(DEFAULT_CREATED_TIME.toString())));
    }

    @Test
    @Transactional
    public void getCourseLessonNoteLike() throws Exception {
        // Initialize the database
        courseLessonNoteLikeRepository.saveAndFlush(courseLessonNoteLike);

        // Get the courseLessonNoteLike
        restCourseLessonNoteLikeMockMvc.perform(get("/api/course-lesson-note-likes/{id}", courseLessonNoteLike.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(courseLessonNoteLike.getId().intValue()))
            .andExpect(jsonPath("$.userId").value(DEFAULT_USER_ID.intValue()))
            .andExpect(jsonPath("$.createdTime").value(DEFAULT_CREATED_TIME.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingCourseLessonNoteLike() throws Exception {
        // Get the courseLessonNoteLike
        restCourseLessonNoteLikeMockMvc.perform(get("/api/course-lesson-note-likes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCourseLessonNoteLike() throws Exception {
        // Initialize the database
        courseLessonNoteLikeService.save(courseLessonNoteLike);

        int databaseSizeBeforeUpdate = courseLessonNoteLikeRepository.findAll().size();

        // Update the courseLessonNoteLike
        CourseLessonNoteLike updatedCourseLessonNoteLike = courseLessonNoteLikeRepository.findOne(courseLessonNoteLike.getId());
        updatedCourseLessonNoteLike
            .userId(UPDATED_USER_ID)
            .createdTime(UPDATED_CREATED_TIME);

        restCourseLessonNoteLikeMockMvc.perform(put("/api/course-lesson-note-likes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedCourseLessonNoteLike)))
            .andExpect(status().isOk());

        // Validate the CourseLessonNoteLike in the database
        List<CourseLessonNoteLike> courseLessonNoteLikeList = courseLessonNoteLikeRepository.findAll();
        assertThat(courseLessonNoteLikeList).hasSize(databaseSizeBeforeUpdate);
        CourseLessonNoteLike testCourseLessonNoteLike = courseLessonNoteLikeList.get(courseLessonNoteLikeList.size() - 1);
        assertThat(testCourseLessonNoteLike.getUserId()).isEqualTo(UPDATED_USER_ID);
        assertThat(testCourseLessonNoteLike.getCreatedTime()).isEqualTo(UPDATED_CREATED_TIME);
    }

    @Test
    @Transactional
    public void updateNonExistingCourseLessonNoteLike() throws Exception {
        int databaseSizeBeforeUpdate = courseLessonNoteLikeRepository.findAll().size();

        // Create the CourseLessonNoteLike

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restCourseLessonNoteLikeMockMvc.perform(put("/api/course-lesson-note-likes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(courseLessonNoteLike)))
            .andExpect(status().isCreated());

        // Validate the CourseLessonNoteLike in the database
        List<CourseLessonNoteLike> courseLessonNoteLikeList = courseLessonNoteLikeRepository.findAll();
        assertThat(courseLessonNoteLikeList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteCourseLessonNoteLike() throws Exception {
        // Initialize the database
        courseLessonNoteLikeService.save(courseLessonNoteLike);

        int databaseSizeBeforeDelete = courseLessonNoteLikeRepository.findAll().size();

        // Get the courseLessonNoteLike
        restCourseLessonNoteLikeMockMvc.perform(delete("/api/course-lesson-note-likes/{id}", courseLessonNoteLike.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<CourseLessonNoteLike> courseLessonNoteLikeList = courseLessonNoteLikeRepository.findAll();
        assertThat(courseLessonNoteLikeList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CourseLessonNoteLike.class);
        CourseLessonNoteLike courseLessonNoteLike1 = new CourseLessonNoteLike();
        courseLessonNoteLike1.setId(1L);
        CourseLessonNoteLike courseLessonNoteLike2 = new CourseLessonNoteLike();
        courseLessonNoteLike2.setId(courseLessonNoteLike1.getId());
        assertThat(courseLessonNoteLike1).isEqualTo(courseLessonNoteLike2);
        courseLessonNoteLike2.setId(2L);
        assertThat(courseLessonNoteLike1).isNotEqualTo(courseLessonNoteLike2);
        courseLessonNoteLike1.setId(null);
        assertThat(courseLessonNoteLike1).isNotEqualTo(courseLessonNoteLike2);
    }
}
