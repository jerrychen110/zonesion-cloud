package com.zonesion.cloud.web.rest;

import com.zonesion.cloud.ZonesionCloudApplicationApp;

import com.zonesion.cloud.domain.CourseLesson;
import com.zonesion.cloud.domain.CourseLessonAttachment;
import com.zonesion.cloud.domain.CourseLessonLearn;
import com.zonesion.cloud.domain.CourseLessonNote;
import com.zonesion.cloud.domain.Chapter;
import com.zonesion.cloud.repository.CourseLessonRepository;
import com.zonesion.cloud.service.CourseLessonService;
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
 * Test class for the CourseLessonResource REST controller.
 *
 * @see CourseLessonResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ZonesionCloudApplicationApp.class)
public class CourseLessonResourceIntTest {

    private static final Long DEFAULT_USER_ID = 1L;
    private static final Long UPDATED_USER_ID = 2L;

    private static final Integer DEFAULT_NUMBER = 1;
    private static final Integer UPDATED_NUMBER = 2;

    private static final Integer DEFAULT_SEQ = 1;
    private static final Integer UPDATED_SEQ = 2;

    private static final String DEFAULT_TITLE = "AAAAAAAAAA";
    private static final String UPDATED_TITLE = "BBBBBBBBBB";

    private static final String DEFAULT_SUMMARY = "AAAAAAAAAA";
    private static final String UPDATED_SUMMARY = "BBBBBBBBBB";

    private static final String DEFAULT_COURSE_LESSON_TYPE = "A";
    private static final String UPDATED_COURSE_LESSON_TYPE = "B";

    private static final String DEFAULT_CONTENT = "AAAAAAAAAA";
    private static final String UPDATED_CONTENT = "BBBBBBBBBB";

    private static final Integer DEFAULT_CREDIT = 1;
    private static final Integer UPDATED_CREDIT = 2;

    private static final Integer DEFAULT_MEDIA_ID = 1;
    private static final Integer UPDATED_MEDIA_ID = 2;

    private static final String DEFAULT_MEDIA_SOURCE = "A";
    private static final String UPDATED_MEDIA_SOURCE = "B";

    private static final String DEFAULT_MEDIA_NAME = "AAAAAAAAAA";
    private static final String UPDATED_MEDIA_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_MEDIA_URI = "AAAAAAAAAA";
    private static final String UPDATED_MEDIA_URI = "BBBBBBBBBB";

    private static final Integer DEFAULT_LEARNED_NUM = 1;
    private static final Integer UPDATED_LEARNED_NUM = 2;

    private static final Integer DEFAULT_VIEWED_NUM = 1;
    private static final Integer UPDATED_VIEWED_NUM = 2;

    @Autowired
    private CourseLessonRepository courseLessonRepository;

    @Autowired
    private CourseLessonService courseLessonService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restCourseLessonMockMvc;

    private CourseLesson courseLesson;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        CourseLessonResource courseLessonResource = new CourseLessonResource(courseLessonService);
        this.restCourseLessonMockMvc = MockMvcBuilders.standaloneSetup(courseLessonResource)
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
    public static CourseLesson createEntity(EntityManager em) {
        CourseLesson courseLesson = new CourseLesson()
            .userId(DEFAULT_USER_ID)
            .number(DEFAULT_NUMBER)
            .seq(DEFAULT_SEQ)
            .title(DEFAULT_TITLE)
            .summary(DEFAULT_SUMMARY)
            .courseLessonType(DEFAULT_COURSE_LESSON_TYPE)
            .content(DEFAULT_CONTENT)
            .credit(DEFAULT_CREDIT)
            .mediaId(DEFAULT_MEDIA_ID)
            .mediaSource(DEFAULT_MEDIA_SOURCE)
            .mediaName(DEFAULT_MEDIA_NAME)
            .mediaUri(DEFAULT_MEDIA_URI)
            .learnedNum(DEFAULT_LEARNED_NUM)
            .viewedNum(DEFAULT_VIEWED_NUM);
        // Add required entity
        CourseLessonAttachment courseLessonAttachment = CourseLessonAttachmentResourceIntTest.createEntity(em);
        em.persist(courseLessonAttachment);
        em.flush();
        courseLesson.getCourseLessonAttachments().add(courseLessonAttachment);
        // Add required entity
        CourseLessonLearn courseLessonLearn = CourseLessonLearnResourceIntTest.createEntity(em);
        em.persist(courseLessonLearn);
        em.flush();
        courseLesson.getCourseLessonLearns().add(courseLessonLearn);
        // Add required entity
        CourseLessonNote courseLessonNote = CourseLessonNoteResourceIntTest.createEntity(em);
        em.persist(courseLessonNote);
        em.flush();
        courseLesson.getCourseLessonNotes().add(courseLessonNote);
        // Add required entity
        Chapter chapter = ChapterResourceIntTest.createEntity(em);
        em.persist(chapter);
        em.flush();
        courseLesson.setChapter(chapter);
        return courseLesson;
    }

    @Before
    public void initTest() {
        courseLesson = createEntity(em);
    }

    @Test
    @Transactional
    public void createCourseLesson() throws Exception {
        int databaseSizeBeforeCreate = courseLessonRepository.findAll().size();

        // Create the CourseLesson
        restCourseLessonMockMvc.perform(post("/api/course-lessons")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(courseLesson)))
            .andExpect(status().isCreated());

        // Validate the CourseLesson in the database
        List<CourseLesson> courseLessonList = courseLessonRepository.findAll();
        assertThat(courseLessonList).hasSize(databaseSizeBeforeCreate + 1);
        CourseLesson testCourseLesson = courseLessonList.get(courseLessonList.size() - 1);
        assertThat(testCourseLesson.getUserId()).isEqualTo(DEFAULT_USER_ID);
        assertThat(testCourseLesson.getNumber()).isEqualTo(DEFAULT_NUMBER);
        assertThat(testCourseLesson.getSeq()).isEqualTo(DEFAULT_SEQ);
        assertThat(testCourseLesson.getTitle()).isEqualTo(DEFAULT_TITLE);
        assertThat(testCourseLesson.getSummary()).isEqualTo(DEFAULT_SUMMARY);
        assertThat(testCourseLesson.getCourseLessonType()).isEqualTo(DEFAULT_COURSE_LESSON_TYPE);
        assertThat(testCourseLesson.getContent()).isEqualTo(DEFAULT_CONTENT);
        assertThat(testCourseLesson.getCredit()).isEqualTo(DEFAULT_CREDIT);
        assertThat(testCourseLesson.getMediaId()).isEqualTo(DEFAULT_MEDIA_ID);
        assertThat(testCourseLesson.getMediaSource()).isEqualTo(DEFAULT_MEDIA_SOURCE);
        assertThat(testCourseLesson.getMediaName()).isEqualTo(DEFAULT_MEDIA_NAME);
        assertThat(testCourseLesson.getMediaUri()).isEqualTo(DEFAULT_MEDIA_URI);
        assertThat(testCourseLesson.getLearnedNum()).isEqualTo(DEFAULT_LEARNED_NUM);
        assertThat(testCourseLesson.getViewedNum()).isEqualTo(DEFAULT_VIEWED_NUM);
    }

    @Test
    @Transactional
    public void createCourseLessonWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = courseLessonRepository.findAll().size();

        // Create the CourseLesson with an existing ID
        courseLesson.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCourseLessonMockMvc.perform(post("/api/course-lessons")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(courseLesson)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<CourseLesson> courseLessonList = courseLessonRepository.findAll();
        assertThat(courseLessonList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkUserIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = courseLessonRepository.findAll().size();
        // set the field null
        courseLesson.setUserId(null);

        // Create the CourseLesson, which fails.

        restCourseLessonMockMvc.perform(post("/api/course-lessons")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(courseLesson)))
            .andExpect(status().isBadRequest());

        List<CourseLesson> courseLessonList = courseLessonRepository.findAll();
        assertThat(courseLessonList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNumberIsRequired() throws Exception {
        int databaseSizeBeforeTest = courseLessonRepository.findAll().size();
        // set the field null
        courseLesson.setNumber(null);

        // Create the CourseLesson, which fails.

        restCourseLessonMockMvc.perform(post("/api/course-lessons")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(courseLesson)))
            .andExpect(status().isBadRequest());

        List<CourseLesson> courseLessonList = courseLessonRepository.findAll();
        assertThat(courseLessonList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSeqIsRequired() throws Exception {
        int databaseSizeBeforeTest = courseLessonRepository.findAll().size();
        // set the field null
        courseLesson.setSeq(null);

        // Create the CourseLesson, which fails.

        restCourseLessonMockMvc.perform(post("/api/course-lessons")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(courseLesson)))
            .andExpect(status().isBadRequest());

        List<CourseLesson> courseLessonList = courseLessonRepository.findAll();
        assertThat(courseLessonList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTitleIsRequired() throws Exception {
        int databaseSizeBeforeTest = courseLessonRepository.findAll().size();
        // set the field null
        courseLesson.setTitle(null);

        // Create the CourseLesson, which fails.

        restCourseLessonMockMvc.perform(post("/api/course-lessons")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(courseLesson)))
            .andExpect(status().isBadRequest());

        List<CourseLesson> courseLessonList = courseLessonRepository.findAll();
        assertThat(courseLessonList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCourseLessonTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = courseLessonRepository.findAll().size();
        // set the field null
        courseLesson.setCourseLessonType(null);

        // Create the CourseLesson, which fails.

        restCourseLessonMockMvc.perform(post("/api/course-lessons")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(courseLesson)))
            .andExpect(status().isBadRequest());

        List<CourseLesson> courseLessonList = courseLessonRepository.findAll();
        assertThat(courseLessonList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCreditIsRequired() throws Exception {
        int databaseSizeBeforeTest = courseLessonRepository.findAll().size();
        // set the field null
        courseLesson.setCredit(null);

        // Create the CourseLesson, which fails.

        restCourseLessonMockMvc.perform(post("/api/course-lessons")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(courseLesson)))
            .andExpect(status().isBadRequest());

        List<CourseLesson> courseLessonList = courseLessonRepository.findAll();
        assertThat(courseLessonList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkMediaIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = courseLessonRepository.findAll().size();
        // set the field null
        courseLesson.setMediaId(null);

        // Create the CourseLesson, which fails.

        restCourseLessonMockMvc.perform(post("/api/course-lessons")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(courseLesson)))
            .andExpect(status().isBadRequest());

        List<CourseLesson> courseLessonList = courseLessonRepository.findAll();
        assertThat(courseLessonList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLearnedNumIsRequired() throws Exception {
        int databaseSizeBeforeTest = courseLessonRepository.findAll().size();
        // set the field null
        courseLesson.setLearnedNum(null);

        // Create the CourseLesson, which fails.

        restCourseLessonMockMvc.perform(post("/api/course-lessons")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(courseLesson)))
            .andExpect(status().isBadRequest());

        List<CourseLesson> courseLessonList = courseLessonRepository.findAll();
        assertThat(courseLessonList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkViewedNumIsRequired() throws Exception {
        int databaseSizeBeforeTest = courseLessonRepository.findAll().size();
        // set the field null
        courseLesson.setViewedNum(null);

        // Create the CourseLesson, which fails.

        restCourseLessonMockMvc.perform(post("/api/course-lessons")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(courseLesson)))
            .andExpect(status().isBadRequest());

        List<CourseLesson> courseLessonList = courseLessonRepository.findAll();
        assertThat(courseLessonList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllCourseLessons() throws Exception {
        // Initialize the database
        courseLessonRepository.saveAndFlush(courseLesson);

        // Get all the courseLessonList
        restCourseLessonMockMvc.perform(get("/api/course-lessons?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(courseLesson.getId().intValue())))
            .andExpect(jsonPath("$.[*].userId").value(hasItem(DEFAULT_USER_ID.intValue())))
            .andExpect(jsonPath("$.[*].number").value(hasItem(DEFAULT_NUMBER)))
            .andExpect(jsonPath("$.[*].seq").value(hasItem(DEFAULT_SEQ)))
            .andExpect(jsonPath("$.[*].title").value(hasItem(DEFAULT_TITLE.toString())))
            .andExpect(jsonPath("$.[*].summary").value(hasItem(DEFAULT_SUMMARY.toString())))
            .andExpect(jsonPath("$.[*].courseLessonType").value(hasItem(DEFAULT_COURSE_LESSON_TYPE.toString())))
            .andExpect(jsonPath("$.[*].content").value(hasItem(DEFAULT_CONTENT.toString())))
            .andExpect(jsonPath("$.[*].credit").value(hasItem(DEFAULT_CREDIT)))
            .andExpect(jsonPath("$.[*].mediaId").value(hasItem(DEFAULT_MEDIA_ID)))
            .andExpect(jsonPath("$.[*].mediaSource").value(hasItem(DEFAULT_MEDIA_SOURCE.toString())))
            .andExpect(jsonPath("$.[*].mediaName").value(hasItem(DEFAULT_MEDIA_NAME.toString())))
            .andExpect(jsonPath("$.[*].mediaUri").value(hasItem(DEFAULT_MEDIA_URI.toString())))
            .andExpect(jsonPath("$.[*].learnedNum").value(hasItem(DEFAULT_LEARNED_NUM)))
            .andExpect(jsonPath("$.[*].viewedNum").value(hasItem(DEFAULT_VIEWED_NUM)));
    }

    @Test
    @Transactional
    public void getCourseLesson() throws Exception {
        // Initialize the database
        courseLessonRepository.saveAndFlush(courseLesson);

        // Get the courseLesson
        restCourseLessonMockMvc.perform(get("/api/course-lessons/{id}", courseLesson.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(courseLesson.getId().intValue()))
            .andExpect(jsonPath("$.userId").value(DEFAULT_USER_ID.intValue()))
            .andExpect(jsonPath("$.number").value(DEFAULT_NUMBER))
            .andExpect(jsonPath("$.seq").value(DEFAULT_SEQ))
            .andExpect(jsonPath("$.title").value(DEFAULT_TITLE.toString()))
            .andExpect(jsonPath("$.summary").value(DEFAULT_SUMMARY.toString()))
            .andExpect(jsonPath("$.courseLessonType").value(DEFAULT_COURSE_LESSON_TYPE.toString()))
            .andExpect(jsonPath("$.content").value(DEFAULT_CONTENT.toString()))
            .andExpect(jsonPath("$.credit").value(DEFAULT_CREDIT))
            .andExpect(jsonPath("$.mediaId").value(DEFAULT_MEDIA_ID))
            .andExpect(jsonPath("$.mediaSource").value(DEFAULT_MEDIA_SOURCE.toString()))
            .andExpect(jsonPath("$.mediaName").value(DEFAULT_MEDIA_NAME.toString()))
            .andExpect(jsonPath("$.mediaUri").value(DEFAULT_MEDIA_URI.toString()))
            .andExpect(jsonPath("$.learnedNum").value(DEFAULT_LEARNED_NUM))
            .andExpect(jsonPath("$.viewedNum").value(DEFAULT_VIEWED_NUM));
    }

    @Test
    @Transactional
    public void getNonExistingCourseLesson() throws Exception {
        // Get the courseLesson
        restCourseLessonMockMvc.perform(get("/api/course-lessons/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCourseLesson() throws Exception {
        // Initialize the database
        courseLessonService.save(courseLesson);

        int databaseSizeBeforeUpdate = courseLessonRepository.findAll().size();

        // Update the courseLesson
        CourseLesson updatedCourseLesson = courseLessonRepository.findOne(courseLesson.getId());
        updatedCourseLesson
            .userId(UPDATED_USER_ID)
            .number(UPDATED_NUMBER)
            .seq(UPDATED_SEQ)
            .title(UPDATED_TITLE)
            .summary(UPDATED_SUMMARY)
            .courseLessonType(UPDATED_COURSE_LESSON_TYPE)
            .content(UPDATED_CONTENT)
            .credit(UPDATED_CREDIT)
            .mediaId(UPDATED_MEDIA_ID)
            .mediaSource(UPDATED_MEDIA_SOURCE)
            .mediaName(UPDATED_MEDIA_NAME)
            .mediaUri(UPDATED_MEDIA_URI)
            .learnedNum(UPDATED_LEARNED_NUM)
            .viewedNum(UPDATED_VIEWED_NUM);

        restCourseLessonMockMvc.perform(put("/api/course-lessons")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedCourseLesson)))
            .andExpect(status().isOk());

        // Validate the CourseLesson in the database
        List<CourseLesson> courseLessonList = courseLessonRepository.findAll();
        assertThat(courseLessonList).hasSize(databaseSizeBeforeUpdate);
        CourseLesson testCourseLesson = courseLessonList.get(courseLessonList.size() - 1);
        assertThat(testCourseLesson.getUserId()).isEqualTo(UPDATED_USER_ID);
        assertThat(testCourseLesson.getNumber()).isEqualTo(UPDATED_NUMBER);
        assertThat(testCourseLesson.getSeq()).isEqualTo(UPDATED_SEQ);
        assertThat(testCourseLesson.getTitle()).isEqualTo(UPDATED_TITLE);
        assertThat(testCourseLesson.getSummary()).isEqualTo(UPDATED_SUMMARY);
        assertThat(testCourseLesson.getCourseLessonType()).isEqualTo(UPDATED_COURSE_LESSON_TYPE);
        assertThat(testCourseLesson.getContent()).isEqualTo(UPDATED_CONTENT);
        assertThat(testCourseLesson.getCredit()).isEqualTo(UPDATED_CREDIT);
        assertThat(testCourseLesson.getMediaId()).isEqualTo(UPDATED_MEDIA_ID);
        assertThat(testCourseLesson.getMediaSource()).isEqualTo(UPDATED_MEDIA_SOURCE);
        assertThat(testCourseLesson.getMediaName()).isEqualTo(UPDATED_MEDIA_NAME);
        assertThat(testCourseLesson.getMediaUri()).isEqualTo(UPDATED_MEDIA_URI);
        assertThat(testCourseLesson.getLearnedNum()).isEqualTo(UPDATED_LEARNED_NUM);
        assertThat(testCourseLesson.getViewedNum()).isEqualTo(UPDATED_VIEWED_NUM);
    }

    @Test
    @Transactional
    public void updateNonExistingCourseLesson() throws Exception {
        int databaseSizeBeforeUpdate = courseLessonRepository.findAll().size();

        // Create the CourseLesson

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restCourseLessonMockMvc.perform(put("/api/course-lessons")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(courseLesson)))
            .andExpect(status().isCreated());

        // Validate the CourseLesson in the database
        List<CourseLesson> courseLessonList = courseLessonRepository.findAll();
        assertThat(courseLessonList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteCourseLesson() throws Exception {
        // Initialize the database
        courseLessonService.save(courseLesson);

        int databaseSizeBeforeDelete = courseLessonRepository.findAll().size();

        // Get the courseLesson
        restCourseLessonMockMvc.perform(delete("/api/course-lessons/{id}", courseLesson.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<CourseLesson> courseLessonList = courseLessonRepository.findAll();
        assertThat(courseLessonList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CourseLesson.class);
        CourseLesson courseLesson1 = new CourseLesson();
        courseLesson1.setId(1L);
        CourseLesson courseLesson2 = new CourseLesson();
        courseLesson2.setId(courseLesson1.getId());
        assertThat(courseLesson1).isEqualTo(courseLesson2);
        courseLesson2.setId(2L);
        assertThat(courseLesson1).isNotEqualTo(courseLesson2);
        courseLesson1.setId(null);
        assertThat(courseLesson1).isNotEqualTo(courseLesson2);
    }
}
