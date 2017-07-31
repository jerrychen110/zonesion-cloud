package com.zonesion.cloud.web.rest;

import com.zonesion.cloud.ZonesionCloudApplicationApp;

import com.zonesion.cloud.domain.CourseLessonNote;
import com.zonesion.cloud.domain.CourseLesson;
import com.zonesion.cloud.domain.CourseLessonNoteLike;
import com.zonesion.cloud.repository.CourseLessonNoteRepository;
import com.zonesion.cloud.service.CourseLessonNoteService;
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
 * Test class for the CourseLessonNoteResource REST controller.
 *
 * @see CourseLessonNoteResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ZonesionCloudApplicationApp.class)
public class CourseLessonNoteResourceIntTest {

    private static final Long DEFAULT_COURSE_ID = 1L;
    private static final Long UPDATED_COURSE_ID = 2L;

    private static final Long DEFAULT_USER_ID = 1L;
    private static final Long UPDATED_USER_ID = 2L;

    private static final String DEFAULT_CONTENT = "AAAAAAAAAA";
    private static final String UPDATED_CONTENT = "BBBBBBBBBB";

    private static final Integer DEFAULT_LENGTH = 1;
    private static final Integer UPDATED_LENGTH = 2;

    private static final Integer DEFAULT_LIKE_NUM = 1;
    private static final Integer UPDATED_LIKE_NUM = 2;

    private static final String DEFAULT_IS_PRIVATE = "A";
    private static final String UPDATED_IS_PRIVATE = "B";

    @Autowired
    private CourseLessonNoteRepository courseLessonNoteRepository;

    @Autowired
    private CourseLessonNoteService courseLessonNoteService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restCourseLessonNoteMockMvc;

    private CourseLessonNote courseLessonNote;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        CourseLessonNoteResource courseLessonNoteResource = new CourseLessonNoteResource(courseLessonNoteService);
        this.restCourseLessonNoteMockMvc = MockMvcBuilders.standaloneSetup(courseLessonNoteResource)
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
    public static CourseLessonNote createEntity(EntityManager em) {
        CourseLessonNote courseLessonNote = new CourseLessonNote()
            .courseId(DEFAULT_COURSE_ID)
            .userId(DEFAULT_USER_ID)
            .content(DEFAULT_CONTENT)
            .length(DEFAULT_LENGTH)
            .likeNum(DEFAULT_LIKE_NUM)
            .isPrivate(DEFAULT_IS_PRIVATE);
        // Add required entity
        CourseLesson courseLesson = CourseLessonResourceIntTest.createEntity(em);
        em.persist(courseLesson);
        em.flush();
        courseLessonNote.setCourseLesson(courseLesson);
        // Add required entity
        CourseLessonNoteLike courseLessonNoteLike = CourseLessonNoteLikeResourceIntTest.createEntity(em);
        em.persist(courseLessonNoteLike);
        em.flush();
        courseLessonNote.getCourseLessonNoteLikes().add(courseLessonNoteLike);
        return courseLessonNote;
    }

    @Before
    public void initTest() {
        courseLessonNote = createEntity(em);
    }

    @Test
    @Transactional
    public void createCourseLessonNote() throws Exception {
        int databaseSizeBeforeCreate = courseLessonNoteRepository.findAll().size();

        // Create the CourseLessonNote
        restCourseLessonNoteMockMvc.perform(post("/api/course-lesson-notes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(courseLessonNote)))
            .andExpect(status().isCreated());

        // Validate the CourseLessonNote in the database
        List<CourseLessonNote> courseLessonNoteList = courseLessonNoteRepository.findAll();
        assertThat(courseLessonNoteList).hasSize(databaseSizeBeforeCreate + 1);
        CourseLessonNote testCourseLessonNote = courseLessonNoteList.get(courseLessonNoteList.size() - 1);
        assertThat(testCourseLessonNote.getCourseId()).isEqualTo(DEFAULT_COURSE_ID);
        assertThat(testCourseLessonNote.getUserId()).isEqualTo(DEFAULT_USER_ID);
        assertThat(testCourseLessonNote.getContent()).isEqualTo(DEFAULT_CONTENT);
        assertThat(testCourseLessonNote.getLength()).isEqualTo(DEFAULT_LENGTH);
        assertThat(testCourseLessonNote.getLikeNum()).isEqualTo(DEFAULT_LIKE_NUM);
        assertThat(testCourseLessonNote.getIsPrivate()).isEqualTo(DEFAULT_IS_PRIVATE);
    }

    @Test
    @Transactional
    public void createCourseLessonNoteWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = courseLessonNoteRepository.findAll().size();

        // Create the CourseLessonNote with an existing ID
        courseLessonNote.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCourseLessonNoteMockMvc.perform(post("/api/course-lesson-notes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(courseLessonNote)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<CourseLessonNote> courseLessonNoteList = courseLessonNoteRepository.findAll();
        assertThat(courseLessonNoteList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkCourseIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = courseLessonNoteRepository.findAll().size();
        // set the field null
        courseLessonNote.setCourseId(null);

        // Create the CourseLessonNote, which fails.

        restCourseLessonNoteMockMvc.perform(post("/api/course-lesson-notes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(courseLessonNote)))
            .andExpect(status().isBadRequest());

        List<CourseLessonNote> courseLessonNoteList = courseLessonNoteRepository.findAll();
        assertThat(courseLessonNoteList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkUserIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = courseLessonNoteRepository.findAll().size();
        // set the field null
        courseLessonNote.setUserId(null);

        // Create the CourseLessonNote, which fails.

        restCourseLessonNoteMockMvc.perform(post("/api/course-lesson-notes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(courseLessonNote)))
            .andExpect(status().isBadRequest());

        List<CourseLessonNote> courseLessonNoteList = courseLessonNoteRepository.findAll();
        assertThat(courseLessonNoteList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkContentIsRequired() throws Exception {
        int databaseSizeBeforeTest = courseLessonNoteRepository.findAll().size();
        // set the field null
        courseLessonNote.setContent(null);

        // Create the CourseLessonNote, which fails.

        restCourseLessonNoteMockMvc.perform(post("/api/course-lesson-notes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(courseLessonNote)))
            .andExpect(status().isBadRequest());

        List<CourseLessonNote> courseLessonNoteList = courseLessonNoteRepository.findAll();
        assertThat(courseLessonNoteList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLengthIsRequired() throws Exception {
        int databaseSizeBeforeTest = courseLessonNoteRepository.findAll().size();
        // set the field null
        courseLessonNote.setLength(null);

        // Create the CourseLessonNote, which fails.

        restCourseLessonNoteMockMvc.perform(post("/api/course-lesson-notes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(courseLessonNote)))
            .andExpect(status().isBadRequest());

        List<CourseLessonNote> courseLessonNoteList = courseLessonNoteRepository.findAll();
        assertThat(courseLessonNoteList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLikeNumIsRequired() throws Exception {
        int databaseSizeBeforeTest = courseLessonNoteRepository.findAll().size();
        // set the field null
        courseLessonNote.setLikeNum(null);

        // Create the CourseLessonNote, which fails.

        restCourseLessonNoteMockMvc.perform(post("/api/course-lesson-notes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(courseLessonNote)))
            .andExpect(status().isBadRequest());

        List<CourseLessonNote> courseLessonNoteList = courseLessonNoteRepository.findAll();
        assertThat(courseLessonNoteList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIsPrivateIsRequired() throws Exception {
        int databaseSizeBeforeTest = courseLessonNoteRepository.findAll().size();
        // set the field null
        courseLessonNote.setIsPrivate(null);

        // Create the CourseLessonNote, which fails.

        restCourseLessonNoteMockMvc.perform(post("/api/course-lesson-notes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(courseLessonNote)))
            .andExpect(status().isBadRequest());

        List<CourseLessonNote> courseLessonNoteList = courseLessonNoteRepository.findAll();
        assertThat(courseLessonNoteList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllCourseLessonNotes() throws Exception {
        // Initialize the database
        courseLessonNoteRepository.saveAndFlush(courseLessonNote);

        // Get all the courseLessonNoteList
        restCourseLessonNoteMockMvc.perform(get("/api/course-lesson-notes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(courseLessonNote.getId().intValue())))
            .andExpect(jsonPath("$.[*].courseId").value(hasItem(DEFAULT_COURSE_ID.intValue())))
            .andExpect(jsonPath("$.[*].userId").value(hasItem(DEFAULT_USER_ID.intValue())))
            .andExpect(jsonPath("$.[*].content").value(hasItem(DEFAULT_CONTENT.toString())))
            .andExpect(jsonPath("$.[*].length").value(hasItem(DEFAULT_LENGTH)))
            .andExpect(jsonPath("$.[*].likeNum").value(hasItem(DEFAULT_LIKE_NUM)))
            .andExpect(jsonPath("$.[*].isPrivate").value(hasItem(DEFAULT_IS_PRIVATE.toString())));
    }

    @Test
    @Transactional
    public void getCourseLessonNote() throws Exception {
        // Initialize the database
        courseLessonNoteRepository.saveAndFlush(courseLessonNote);

        // Get the courseLessonNote
        restCourseLessonNoteMockMvc.perform(get("/api/course-lesson-notes/{id}", courseLessonNote.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(courseLessonNote.getId().intValue()))
            .andExpect(jsonPath("$.courseId").value(DEFAULT_COURSE_ID.intValue()))
            .andExpect(jsonPath("$.userId").value(DEFAULT_USER_ID.intValue()))
            .andExpect(jsonPath("$.content").value(DEFAULT_CONTENT.toString()))
            .andExpect(jsonPath("$.length").value(DEFAULT_LENGTH))
            .andExpect(jsonPath("$.likeNum").value(DEFAULT_LIKE_NUM))
            .andExpect(jsonPath("$.isPrivate").value(DEFAULT_IS_PRIVATE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingCourseLessonNote() throws Exception {
        // Get the courseLessonNote
        restCourseLessonNoteMockMvc.perform(get("/api/course-lesson-notes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCourseLessonNote() throws Exception {
        // Initialize the database
        courseLessonNoteService.save(courseLessonNote);

        int databaseSizeBeforeUpdate = courseLessonNoteRepository.findAll().size();

        // Update the courseLessonNote
        CourseLessonNote updatedCourseLessonNote = courseLessonNoteRepository.findOne(courseLessonNote.getId());
        updatedCourseLessonNote
            .courseId(UPDATED_COURSE_ID)
            .userId(UPDATED_USER_ID)
            .content(UPDATED_CONTENT)
            .length(UPDATED_LENGTH)
            .likeNum(UPDATED_LIKE_NUM)
            .isPrivate(UPDATED_IS_PRIVATE);

        restCourseLessonNoteMockMvc.perform(put("/api/course-lesson-notes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedCourseLessonNote)))
            .andExpect(status().isOk());

        // Validate the CourseLessonNote in the database
        List<CourseLessonNote> courseLessonNoteList = courseLessonNoteRepository.findAll();
        assertThat(courseLessonNoteList).hasSize(databaseSizeBeforeUpdate);
        CourseLessonNote testCourseLessonNote = courseLessonNoteList.get(courseLessonNoteList.size() - 1);
        assertThat(testCourseLessonNote.getCourseId()).isEqualTo(UPDATED_COURSE_ID);
        assertThat(testCourseLessonNote.getUserId()).isEqualTo(UPDATED_USER_ID);
        assertThat(testCourseLessonNote.getContent()).isEqualTo(UPDATED_CONTENT);
        assertThat(testCourseLessonNote.getLength()).isEqualTo(UPDATED_LENGTH);
        assertThat(testCourseLessonNote.getLikeNum()).isEqualTo(UPDATED_LIKE_NUM);
        assertThat(testCourseLessonNote.getIsPrivate()).isEqualTo(UPDATED_IS_PRIVATE);
    }

    @Test
    @Transactional
    public void updateNonExistingCourseLessonNote() throws Exception {
        int databaseSizeBeforeUpdate = courseLessonNoteRepository.findAll().size();

        // Create the CourseLessonNote

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restCourseLessonNoteMockMvc.perform(put("/api/course-lesson-notes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(courseLessonNote)))
            .andExpect(status().isCreated());

        // Validate the CourseLessonNote in the database
        List<CourseLessonNote> courseLessonNoteList = courseLessonNoteRepository.findAll();
        assertThat(courseLessonNoteList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteCourseLessonNote() throws Exception {
        // Initialize the database
        courseLessonNoteService.save(courseLessonNote);

        int databaseSizeBeforeDelete = courseLessonNoteRepository.findAll().size();

        // Get the courseLessonNote
        restCourseLessonNoteMockMvc.perform(delete("/api/course-lesson-notes/{id}", courseLessonNote.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<CourseLessonNote> courseLessonNoteList = courseLessonNoteRepository.findAll();
        assertThat(courseLessonNoteList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CourseLessonNote.class);
        CourseLessonNote courseLessonNote1 = new CourseLessonNote();
        courseLessonNote1.setId(1L);
        CourseLessonNote courseLessonNote2 = new CourseLessonNote();
        courseLessonNote2.setId(courseLessonNote1.getId());
        assertThat(courseLessonNote1).isEqualTo(courseLessonNote2);
        courseLessonNote2.setId(2L);
        assertThat(courseLessonNote1).isNotEqualTo(courseLessonNote2);
        courseLessonNote1.setId(null);
        assertThat(courseLessonNote1).isNotEqualTo(courseLessonNote2);
    }
}
