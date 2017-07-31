package com.zonesion.cloud.web.rest;

import com.zonesion.cloud.ZonesionCloudApplicationApp;

import com.zonesion.cloud.domain.CourseLessonLearn;
import com.zonesion.cloud.domain.CourseLesson;
import com.zonesion.cloud.repository.CourseLessonLearnRepository;
import com.zonesion.cloud.service.CourseLessonLearnService;
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
 * Test class for the CourseLessonLearnResource REST controller.
 *
 * @see CourseLessonLearnResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ZonesionCloudApplicationApp.class)
public class CourseLessonLearnResourceIntTest {

    private static final Long DEFAULT_COURSE_ID = 1L;
    private static final Long UPDATED_COURSE_ID = 2L;

    private static final Long DEFAULT_USER_ID = 1L;
    private static final Long UPDATED_USER_ID = 2L;

    private static final Long DEFAULT_DURATION_ID = 1L;
    private static final Long UPDATED_DURATION_ID = 2L;

    private static final String DEFAULT_IS_COMPLETE = "A";
    private static final String UPDATED_IS_COMPLETE = "B";

    @Autowired
    private CourseLessonLearnRepository courseLessonLearnRepository;

    @Autowired
    private CourseLessonLearnService courseLessonLearnService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restCourseLessonLearnMockMvc;

    private CourseLessonLearn courseLessonLearn;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        CourseLessonLearnResource courseLessonLearnResource = new CourseLessonLearnResource(courseLessonLearnService);
        this.restCourseLessonLearnMockMvc = MockMvcBuilders.standaloneSetup(courseLessonLearnResource)
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
    public static CourseLessonLearn createEntity(EntityManager em) {
        CourseLessonLearn courseLessonLearn = new CourseLessonLearn()
            .courseId(DEFAULT_COURSE_ID)
            .userId(DEFAULT_USER_ID)
            .durationId(DEFAULT_DURATION_ID)
            .isComplete(DEFAULT_IS_COMPLETE);
        // Add required entity
        CourseLesson courseLesson = CourseLessonResourceIntTest.createEntity(em);
        em.persist(courseLesson);
        em.flush();
        courseLessonLearn.setCourseLesson(courseLesson);
        return courseLessonLearn;
    }

    @Before
    public void initTest() {
        courseLessonLearn = createEntity(em);
    }

    @Test
    @Transactional
    public void createCourseLessonLearn() throws Exception {
        int databaseSizeBeforeCreate = courseLessonLearnRepository.findAll().size();

        // Create the CourseLessonLearn
        restCourseLessonLearnMockMvc.perform(post("/api/course-lesson-learns")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(courseLessonLearn)))
            .andExpect(status().isCreated());

        // Validate the CourseLessonLearn in the database
        List<CourseLessonLearn> courseLessonLearnList = courseLessonLearnRepository.findAll();
        assertThat(courseLessonLearnList).hasSize(databaseSizeBeforeCreate + 1);
        CourseLessonLearn testCourseLessonLearn = courseLessonLearnList.get(courseLessonLearnList.size() - 1);
        assertThat(testCourseLessonLearn.getCourseId()).isEqualTo(DEFAULT_COURSE_ID);
        assertThat(testCourseLessonLearn.getUserId()).isEqualTo(DEFAULT_USER_ID);
        assertThat(testCourseLessonLearn.getDurationId()).isEqualTo(DEFAULT_DURATION_ID);
        assertThat(testCourseLessonLearn.getIsComplete()).isEqualTo(DEFAULT_IS_COMPLETE);
    }

    @Test
    @Transactional
    public void createCourseLessonLearnWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = courseLessonLearnRepository.findAll().size();

        // Create the CourseLessonLearn with an existing ID
        courseLessonLearn.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCourseLessonLearnMockMvc.perform(post("/api/course-lesson-learns")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(courseLessonLearn)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<CourseLessonLearn> courseLessonLearnList = courseLessonLearnRepository.findAll();
        assertThat(courseLessonLearnList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkCourseIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = courseLessonLearnRepository.findAll().size();
        // set the field null
        courseLessonLearn.setCourseId(null);

        // Create the CourseLessonLearn, which fails.

        restCourseLessonLearnMockMvc.perform(post("/api/course-lesson-learns")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(courseLessonLearn)))
            .andExpect(status().isBadRequest());

        List<CourseLessonLearn> courseLessonLearnList = courseLessonLearnRepository.findAll();
        assertThat(courseLessonLearnList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkUserIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = courseLessonLearnRepository.findAll().size();
        // set the field null
        courseLessonLearn.setUserId(null);

        // Create the CourseLessonLearn, which fails.

        restCourseLessonLearnMockMvc.perform(post("/api/course-lesson-learns")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(courseLessonLearn)))
            .andExpect(status().isBadRequest());

        List<CourseLessonLearn> courseLessonLearnList = courseLessonLearnRepository.findAll();
        assertThat(courseLessonLearnList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDurationIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = courseLessonLearnRepository.findAll().size();
        // set the field null
        courseLessonLearn.setDurationId(null);

        // Create the CourseLessonLearn, which fails.

        restCourseLessonLearnMockMvc.perform(post("/api/course-lesson-learns")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(courseLessonLearn)))
            .andExpect(status().isBadRequest());

        List<CourseLessonLearn> courseLessonLearnList = courseLessonLearnRepository.findAll();
        assertThat(courseLessonLearnList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIsCompleteIsRequired() throws Exception {
        int databaseSizeBeforeTest = courseLessonLearnRepository.findAll().size();
        // set the field null
        courseLessonLearn.setIsComplete(null);

        // Create the CourseLessonLearn, which fails.

        restCourseLessonLearnMockMvc.perform(post("/api/course-lesson-learns")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(courseLessonLearn)))
            .andExpect(status().isBadRequest());

        List<CourseLessonLearn> courseLessonLearnList = courseLessonLearnRepository.findAll();
        assertThat(courseLessonLearnList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllCourseLessonLearns() throws Exception {
        // Initialize the database
        courseLessonLearnRepository.saveAndFlush(courseLessonLearn);

        // Get all the courseLessonLearnList
        restCourseLessonLearnMockMvc.perform(get("/api/course-lesson-learns?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(courseLessonLearn.getId().intValue())))
            .andExpect(jsonPath("$.[*].courseId").value(hasItem(DEFAULT_COURSE_ID.intValue())))
            .andExpect(jsonPath("$.[*].userId").value(hasItem(DEFAULT_USER_ID.intValue())))
            .andExpect(jsonPath("$.[*].durationId").value(hasItem(DEFAULT_DURATION_ID.intValue())))
            .andExpect(jsonPath("$.[*].isComplete").value(hasItem(DEFAULT_IS_COMPLETE.toString())));
    }

    @Test
    @Transactional
    public void getCourseLessonLearn() throws Exception {
        // Initialize the database
        courseLessonLearnRepository.saveAndFlush(courseLessonLearn);

        // Get the courseLessonLearn
        restCourseLessonLearnMockMvc.perform(get("/api/course-lesson-learns/{id}", courseLessonLearn.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(courseLessonLearn.getId().intValue()))
            .andExpect(jsonPath("$.courseId").value(DEFAULT_COURSE_ID.intValue()))
            .andExpect(jsonPath("$.userId").value(DEFAULT_USER_ID.intValue()))
            .andExpect(jsonPath("$.durationId").value(DEFAULT_DURATION_ID.intValue()))
            .andExpect(jsonPath("$.isComplete").value(DEFAULT_IS_COMPLETE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingCourseLessonLearn() throws Exception {
        // Get the courseLessonLearn
        restCourseLessonLearnMockMvc.perform(get("/api/course-lesson-learns/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCourseLessonLearn() throws Exception {
        // Initialize the database
        courseLessonLearnService.save(courseLessonLearn);

        int databaseSizeBeforeUpdate = courseLessonLearnRepository.findAll().size();

        // Update the courseLessonLearn
        CourseLessonLearn updatedCourseLessonLearn = courseLessonLearnRepository.findOne(courseLessonLearn.getId());
        updatedCourseLessonLearn
            .courseId(UPDATED_COURSE_ID)
            .userId(UPDATED_USER_ID)
            .durationId(UPDATED_DURATION_ID)
            .isComplete(UPDATED_IS_COMPLETE);

        restCourseLessonLearnMockMvc.perform(put("/api/course-lesson-learns")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedCourseLessonLearn)))
            .andExpect(status().isOk());

        // Validate the CourseLessonLearn in the database
        List<CourseLessonLearn> courseLessonLearnList = courseLessonLearnRepository.findAll();
        assertThat(courseLessonLearnList).hasSize(databaseSizeBeforeUpdate);
        CourseLessonLearn testCourseLessonLearn = courseLessonLearnList.get(courseLessonLearnList.size() - 1);
        assertThat(testCourseLessonLearn.getCourseId()).isEqualTo(UPDATED_COURSE_ID);
        assertThat(testCourseLessonLearn.getUserId()).isEqualTo(UPDATED_USER_ID);
        assertThat(testCourseLessonLearn.getDurationId()).isEqualTo(UPDATED_DURATION_ID);
        assertThat(testCourseLessonLearn.getIsComplete()).isEqualTo(UPDATED_IS_COMPLETE);
    }

    @Test
    @Transactional
    public void updateNonExistingCourseLessonLearn() throws Exception {
        int databaseSizeBeforeUpdate = courseLessonLearnRepository.findAll().size();

        // Create the CourseLessonLearn

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restCourseLessonLearnMockMvc.perform(put("/api/course-lesson-learns")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(courseLessonLearn)))
            .andExpect(status().isCreated());

        // Validate the CourseLessonLearn in the database
        List<CourseLessonLearn> courseLessonLearnList = courseLessonLearnRepository.findAll();
        assertThat(courseLessonLearnList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteCourseLessonLearn() throws Exception {
        // Initialize the database
        courseLessonLearnService.save(courseLessonLearn);

        int databaseSizeBeforeDelete = courseLessonLearnRepository.findAll().size();

        // Get the courseLessonLearn
        restCourseLessonLearnMockMvc.perform(delete("/api/course-lesson-learns/{id}", courseLessonLearn.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<CourseLessonLearn> courseLessonLearnList = courseLessonLearnRepository.findAll();
        assertThat(courseLessonLearnList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CourseLessonLearn.class);
        CourseLessonLearn courseLessonLearn1 = new CourseLessonLearn();
        courseLessonLearn1.setId(1L);
        CourseLessonLearn courseLessonLearn2 = new CourseLessonLearn();
        courseLessonLearn2.setId(courseLessonLearn1.getId());
        assertThat(courseLessonLearn1).isEqualTo(courseLessonLearn2);
        courseLessonLearn2.setId(2L);
        assertThat(courseLessonLearn1).isNotEqualTo(courseLessonLearn2);
        courseLessonLearn1.setId(null);
        assertThat(courseLessonLearn1).isNotEqualTo(courseLessonLearn2);
    }
}
