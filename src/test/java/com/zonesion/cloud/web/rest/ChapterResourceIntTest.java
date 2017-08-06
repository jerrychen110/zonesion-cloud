package com.zonesion.cloud.web.rest;

import com.zonesion.cloud.ZonesionCloudApplicationApp;

import com.zonesion.cloud.domain.Chapter;
import com.zonesion.cloud.domain.CourseLesson;
import com.zonesion.cloud.domain.Course;
import com.zonesion.cloud.repository.ChapterRepository;
import com.zonesion.cloud.service.ChapterService;
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
 * Test class for the ChapterResource REST controller.
 *
 * @see ChapterResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ZonesionCloudApplicationApp.class)
public class ChapterResourceIntTest {

    private static final Long DEFAULT_USER_ID = 1L;
    private static final Long UPDATED_USER_ID = 2L;

    private static final String DEFAULT_CHAPTER_TYPE = "A";
    private static final String UPDATED_CHAPTER_TYPE = "B";

    private static final Integer DEFAULT_NUMBER = 1;
    private static final Integer UPDATED_NUMBER = 2;

    private static final Integer DEFAULT_SEQ = 1;
    private static final Integer UPDATED_SEQ = 2;

    private static final String DEFAULT_TITLE = "AAAAAAAAAA";
    private static final String UPDATED_TITLE = "BBBBBBBBBB";

    @Autowired
    private ChapterRepository chapterRepository;

    @Autowired
    private ChapterService chapterService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restChapterMockMvc;

    private Chapter chapter;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        ChapterResource chapterResource = new ChapterResource(chapterService);
        this.restChapterMockMvc = MockMvcBuilders.standaloneSetup(chapterResource)
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
    public static Chapter createEntity(EntityManager em) {
        Chapter chapter = new Chapter()
            .userId(DEFAULT_USER_ID)
            .chapterType(DEFAULT_CHAPTER_TYPE)
            .number(DEFAULT_NUMBER)
            .seq(DEFAULT_SEQ)
            .title(DEFAULT_TITLE);
        // Add required entity
        CourseLesson courseLesson = CourseLessonResourceIntTest.createEntity(em);
        em.persist(courseLesson);
        em.flush();
        chapter.getCourseLessons().add(courseLesson);
        // Add required entity
        Course course = CourseResourceIntTest.createEntity(em);
        em.persist(course);
        em.flush();
        chapter.setCourseId(course);
        return chapter;
    }

    @Before
    public void initTest() {
        chapter = createEntity(em);
    }

    @Test
    @Transactional
    public void createChapter() throws Exception {
        int databaseSizeBeforeCreate = chapterRepository.findAll().size();

        // Create the Chapter
        restChapterMockMvc.perform(post("/api/chapters")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(chapter)))
            .andExpect(status().isCreated());

        // Validate the Chapter in the database
        List<Chapter> chapterList = chapterRepository.findAll();
        assertThat(chapterList).hasSize(databaseSizeBeforeCreate + 1);
        Chapter testChapter = chapterList.get(chapterList.size() - 1);
        assertThat(testChapter.getUserId()).isEqualTo(DEFAULT_USER_ID);
        assertThat(testChapter.getChapterType()).isEqualTo(DEFAULT_CHAPTER_TYPE);
        assertThat(testChapter.getNumber()).isEqualTo(DEFAULT_NUMBER);
        assertThat(testChapter.getSeq()).isEqualTo(DEFAULT_SEQ);
        assertThat(testChapter.getTitle()).isEqualTo(DEFAULT_TITLE);
    }

    @Test
    @Transactional
    public void createChapterWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = chapterRepository.findAll().size();

        // Create the Chapter with an existing ID
        chapter.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restChapterMockMvc.perform(post("/api/chapters")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(chapter)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Chapter> chapterList = chapterRepository.findAll();
        assertThat(chapterList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkUserIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = chapterRepository.findAll().size();
        // set the field null
        chapter.setUserId(null);

        // Create the Chapter, which fails.

        restChapterMockMvc.perform(post("/api/chapters")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(chapter)))
            .andExpect(status().isBadRequest());

        List<Chapter> chapterList = chapterRepository.findAll();
        assertThat(chapterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkChapterTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = chapterRepository.findAll().size();
        // set the field null
        chapter.setChapterType(null);

        // Create the Chapter, which fails.

        restChapterMockMvc.perform(post("/api/chapters")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(chapter)))
            .andExpect(status().isBadRequest());

        List<Chapter> chapterList = chapterRepository.findAll();
        assertThat(chapterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNumberIsRequired() throws Exception {
        int databaseSizeBeforeTest = chapterRepository.findAll().size();
        // set the field null
        chapter.setNumber(null);

        // Create the Chapter, which fails.

        restChapterMockMvc.perform(post("/api/chapters")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(chapter)))
            .andExpect(status().isBadRequest());

        List<Chapter> chapterList = chapterRepository.findAll();
        assertThat(chapterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSeqIsRequired() throws Exception {
        int databaseSizeBeforeTest = chapterRepository.findAll().size();
        // set the field null
        chapter.setSeq(null);

        // Create the Chapter, which fails.

        restChapterMockMvc.perform(post("/api/chapters")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(chapter)))
            .andExpect(status().isBadRequest());

        List<Chapter> chapterList = chapterRepository.findAll();
        assertThat(chapterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTitleIsRequired() throws Exception {
        int databaseSizeBeforeTest = chapterRepository.findAll().size();
        // set the field null
        chapter.setTitle(null);

        // Create the Chapter, which fails.

        restChapterMockMvc.perform(post("/api/chapters")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(chapter)))
            .andExpect(status().isBadRequest());

        List<Chapter> chapterList = chapterRepository.findAll();
        assertThat(chapterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllChapters() throws Exception {
        // Initialize the database
        chapterRepository.saveAndFlush(chapter);

        // Get all the chapterList
        restChapterMockMvc.perform(get("/api/chapters?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(chapter.getId().intValue())))
            .andExpect(jsonPath("$.[*].userId").value(hasItem(DEFAULT_USER_ID.intValue())))
            .andExpect(jsonPath("$.[*].chapterType").value(hasItem(DEFAULT_CHAPTER_TYPE.toString())))
            .andExpect(jsonPath("$.[*].number").value(hasItem(DEFAULT_NUMBER)))
            .andExpect(jsonPath("$.[*].seq").value(hasItem(DEFAULT_SEQ)))
            .andExpect(jsonPath("$.[*].title").value(hasItem(DEFAULT_TITLE.toString())));
    }

    @Test
    @Transactional
    public void getChapter() throws Exception {
        // Initialize the database
        chapterRepository.saveAndFlush(chapter);

        // Get the chapter
        restChapterMockMvc.perform(get("/api/chapters/{id}", chapter.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(chapter.getId().intValue()))
            .andExpect(jsonPath("$.userId").value(DEFAULT_USER_ID.intValue()))
            .andExpect(jsonPath("$.chapterType").value(DEFAULT_CHAPTER_TYPE.toString()))
            .andExpect(jsonPath("$.number").value(DEFAULT_NUMBER))
            .andExpect(jsonPath("$.seq").value(DEFAULT_SEQ))
            .andExpect(jsonPath("$.title").value(DEFAULT_TITLE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingChapter() throws Exception {
        // Get the chapter
        restChapterMockMvc.perform(get("/api/chapters/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateChapter() throws Exception {
        // Initialize the database
        chapterService.save(chapter);

        int databaseSizeBeforeUpdate = chapterRepository.findAll().size();

        // Update the chapter
        Chapter updatedChapter = chapterRepository.findOne(chapter.getId());
        updatedChapter
            .userId(UPDATED_USER_ID)
            .chapterType(UPDATED_CHAPTER_TYPE)
            .number(UPDATED_NUMBER)
            .seq(UPDATED_SEQ)
            .title(UPDATED_TITLE);

        restChapterMockMvc.perform(put("/api/chapters")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedChapter)))
            .andExpect(status().isOk());

        // Validate the Chapter in the database
        List<Chapter> chapterList = chapterRepository.findAll();
        assertThat(chapterList).hasSize(databaseSizeBeforeUpdate);
        Chapter testChapter = chapterList.get(chapterList.size() - 1);
        assertThat(testChapter.getUserId()).isEqualTo(UPDATED_USER_ID);
        assertThat(testChapter.getChapterType()).isEqualTo(UPDATED_CHAPTER_TYPE);
        assertThat(testChapter.getNumber()).isEqualTo(UPDATED_NUMBER);
        assertThat(testChapter.getSeq()).isEqualTo(UPDATED_SEQ);
        assertThat(testChapter.getTitle()).isEqualTo(UPDATED_TITLE);
    }

    @Test
    @Transactional
    public void updateNonExistingChapter() throws Exception {
        int databaseSizeBeforeUpdate = chapterRepository.findAll().size();

        // Create the Chapter

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restChapterMockMvc.perform(put("/api/chapters")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(chapter)))
            .andExpect(status().isCreated());

        // Validate the Chapter in the database
        List<Chapter> chapterList = chapterRepository.findAll();
        assertThat(chapterList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteChapter() throws Exception {
        // Initialize the database
        chapterService.save(chapter);

        int databaseSizeBeforeDelete = chapterRepository.findAll().size();

        // Get the chapter
        restChapterMockMvc.perform(delete("/api/chapters/{id}", chapter.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Chapter> chapterList = chapterRepository.findAll();
        assertThat(chapterList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Chapter.class);
        Chapter chapter1 = new Chapter();
        chapter1.setId(1L);
        Chapter chapter2 = new Chapter();
        chapter2.setId(chapter1.getId());
        assertThat(chapter1).isEqualTo(chapter2);
        chapter2.setId(2L);
        assertThat(chapter1).isNotEqualTo(chapter2);
        chapter1.setId(null);
        assertThat(chapter1).isNotEqualTo(chapter2);
    }
}
