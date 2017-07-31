package com.zonesion.cloud.web.rest;

import com.zonesion.cloud.ZonesionCloudApplicationApp;

import com.zonesion.cloud.domain.CourseLessonAttachment;
import com.zonesion.cloud.domain.CourseLesson;
import com.zonesion.cloud.repository.CourseLessonAttachmentRepository;
import com.zonesion.cloud.service.CourseLessonAttachmentService;
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
 * Test class for the CourseLessonAttachmentResource REST controller.
 *
 * @see CourseLessonAttachmentResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ZonesionCloudApplicationApp.class)
public class CourseLessonAttachmentResourceIntTest {

    private static final Long DEFAULT_COURSE_ID = 1L;
    private static final Long UPDATED_COURSE_ID = 2L;

    private static final Long DEFAULT_USER_ID = 1L;
    private static final Long UPDATED_USER_ID = 2L;

    private static final String DEFAULT_TITLE = "AAAAAAAAAA";
    private static final String UPDATED_TITLE = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_LINK = "AAAAAAAAAA";
    private static final String UPDATED_LINK = "BBBBBBBBBB";

    private static final Long DEFAULT_FILE_ID = 1L;
    private static final Long UPDATED_FILE_ID = 2L;

    private static final String DEFAULT_FILE_URI = "AAAAAAAAAA";
    private static final String UPDATED_FILE_URI = "BBBBBBBBBB";

    private static final String DEFAULT_FILE_MIME = "AAAAAAAAAA";
    private static final String UPDATED_FILE_MIME = "BBBBBBBBBB";

    private static final Integer DEFAULT_FILE_SIZE = 1;
    private static final Integer UPDATED_FILE_SIZE = 2;

    @Autowired
    private CourseLessonAttachmentRepository courseLessonAttachmentRepository;

    @Autowired
    private CourseLessonAttachmentService courseLessonAttachmentService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restCourseLessonAttachmentMockMvc;

    private CourseLessonAttachment courseLessonAttachment;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        CourseLessonAttachmentResource courseLessonAttachmentResource = new CourseLessonAttachmentResource(courseLessonAttachmentService);
        this.restCourseLessonAttachmentMockMvc = MockMvcBuilders.standaloneSetup(courseLessonAttachmentResource)
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
    public static CourseLessonAttachment createEntity(EntityManager em) {
        CourseLessonAttachment courseLessonAttachment = new CourseLessonAttachment()
            .courseId(DEFAULT_COURSE_ID)
            .userId(DEFAULT_USER_ID)
            .title(DEFAULT_TITLE)
            .description(DEFAULT_DESCRIPTION)
            .link(DEFAULT_LINK)
            .fileId(DEFAULT_FILE_ID)
            .fileUri(DEFAULT_FILE_URI)
            .fileMime(DEFAULT_FILE_MIME)
            .fileSize(DEFAULT_FILE_SIZE);
        // Add required entity
        CourseLesson courseLesson = CourseLessonResourceIntTest.createEntity(em);
        em.persist(courseLesson);
        em.flush();
        courseLessonAttachment.setCourseLesson(courseLesson);
        return courseLessonAttachment;
    }

    @Before
    public void initTest() {
        courseLessonAttachment = createEntity(em);
    }

    @Test
    @Transactional
    public void createCourseLessonAttachment() throws Exception {
        int databaseSizeBeforeCreate = courseLessonAttachmentRepository.findAll().size();

        // Create the CourseLessonAttachment
        restCourseLessonAttachmentMockMvc.perform(post("/api/course-lesson-attachments")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(courseLessonAttachment)))
            .andExpect(status().isCreated());

        // Validate the CourseLessonAttachment in the database
        List<CourseLessonAttachment> courseLessonAttachmentList = courseLessonAttachmentRepository.findAll();
        assertThat(courseLessonAttachmentList).hasSize(databaseSizeBeforeCreate + 1);
        CourseLessonAttachment testCourseLessonAttachment = courseLessonAttachmentList.get(courseLessonAttachmentList.size() - 1);
        assertThat(testCourseLessonAttachment.getCourseId()).isEqualTo(DEFAULT_COURSE_ID);
        assertThat(testCourseLessonAttachment.getUserId()).isEqualTo(DEFAULT_USER_ID);
        assertThat(testCourseLessonAttachment.getTitle()).isEqualTo(DEFAULT_TITLE);
        assertThat(testCourseLessonAttachment.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testCourseLessonAttachment.getLink()).isEqualTo(DEFAULT_LINK);
        assertThat(testCourseLessonAttachment.getFileId()).isEqualTo(DEFAULT_FILE_ID);
        assertThat(testCourseLessonAttachment.getFileUri()).isEqualTo(DEFAULT_FILE_URI);
        assertThat(testCourseLessonAttachment.getFileMime()).isEqualTo(DEFAULT_FILE_MIME);
        assertThat(testCourseLessonAttachment.getFileSize()).isEqualTo(DEFAULT_FILE_SIZE);
    }

    @Test
    @Transactional
    public void createCourseLessonAttachmentWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = courseLessonAttachmentRepository.findAll().size();

        // Create the CourseLessonAttachment with an existing ID
        courseLessonAttachment.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCourseLessonAttachmentMockMvc.perform(post("/api/course-lesson-attachments")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(courseLessonAttachment)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<CourseLessonAttachment> courseLessonAttachmentList = courseLessonAttachmentRepository.findAll();
        assertThat(courseLessonAttachmentList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkCourseIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = courseLessonAttachmentRepository.findAll().size();
        // set the field null
        courseLessonAttachment.setCourseId(null);

        // Create the CourseLessonAttachment, which fails.

        restCourseLessonAttachmentMockMvc.perform(post("/api/course-lesson-attachments")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(courseLessonAttachment)))
            .andExpect(status().isBadRequest());

        List<CourseLessonAttachment> courseLessonAttachmentList = courseLessonAttachmentRepository.findAll();
        assertThat(courseLessonAttachmentList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkUserIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = courseLessonAttachmentRepository.findAll().size();
        // set the field null
        courseLessonAttachment.setUserId(null);

        // Create the CourseLessonAttachment, which fails.

        restCourseLessonAttachmentMockMvc.perform(post("/api/course-lesson-attachments")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(courseLessonAttachment)))
            .andExpect(status().isBadRequest());

        List<CourseLessonAttachment> courseLessonAttachmentList = courseLessonAttachmentRepository.findAll();
        assertThat(courseLessonAttachmentList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTitleIsRequired() throws Exception {
        int databaseSizeBeforeTest = courseLessonAttachmentRepository.findAll().size();
        // set the field null
        courseLessonAttachment.setTitle(null);

        // Create the CourseLessonAttachment, which fails.

        restCourseLessonAttachmentMockMvc.perform(post("/api/course-lesson-attachments")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(courseLessonAttachment)))
            .andExpect(status().isBadRequest());

        List<CourseLessonAttachment> courseLessonAttachmentList = courseLessonAttachmentRepository.findAll();
        assertThat(courseLessonAttachmentList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFileSizeIsRequired() throws Exception {
        int databaseSizeBeforeTest = courseLessonAttachmentRepository.findAll().size();
        // set the field null
        courseLessonAttachment.setFileSize(null);

        // Create the CourseLessonAttachment, which fails.

        restCourseLessonAttachmentMockMvc.perform(post("/api/course-lesson-attachments")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(courseLessonAttachment)))
            .andExpect(status().isBadRequest());

        List<CourseLessonAttachment> courseLessonAttachmentList = courseLessonAttachmentRepository.findAll();
        assertThat(courseLessonAttachmentList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllCourseLessonAttachments() throws Exception {
        // Initialize the database
        courseLessonAttachmentRepository.saveAndFlush(courseLessonAttachment);

        // Get all the courseLessonAttachmentList
        restCourseLessonAttachmentMockMvc.perform(get("/api/course-lesson-attachments?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(courseLessonAttachment.getId().intValue())))
            .andExpect(jsonPath("$.[*].courseId").value(hasItem(DEFAULT_COURSE_ID.intValue())))
            .andExpect(jsonPath("$.[*].userId").value(hasItem(DEFAULT_USER_ID.intValue())))
            .andExpect(jsonPath("$.[*].title").value(hasItem(DEFAULT_TITLE.toString())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].link").value(hasItem(DEFAULT_LINK.toString())))
            .andExpect(jsonPath("$.[*].fileId").value(hasItem(DEFAULT_FILE_ID.intValue())))
            .andExpect(jsonPath("$.[*].fileUri").value(hasItem(DEFAULT_FILE_URI.toString())))
            .andExpect(jsonPath("$.[*].fileMime").value(hasItem(DEFAULT_FILE_MIME.toString())))
            .andExpect(jsonPath("$.[*].fileSize").value(hasItem(DEFAULT_FILE_SIZE)));
    }

    @Test
    @Transactional
    public void getCourseLessonAttachment() throws Exception {
        // Initialize the database
        courseLessonAttachmentRepository.saveAndFlush(courseLessonAttachment);

        // Get the courseLessonAttachment
        restCourseLessonAttachmentMockMvc.perform(get("/api/course-lesson-attachments/{id}", courseLessonAttachment.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(courseLessonAttachment.getId().intValue()))
            .andExpect(jsonPath("$.courseId").value(DEFAULT_COURSE_ID.intValue()))
            .andExpect(jsonPath("$.userId").value(DEFAULT_USER_ID.intValue()))
            .andExpect(jsonPath("$.title").value(DEFAULT_TITLE.toString()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.link").value(DEFAULT_LINK.toString()))
            .andExpect(jsonPath("$.fileId").value(DEFAULT_FILE_ID.intValue()))
            .andExpect(jsonPath("$.fileUri").value(DEFAULT_FILE_URI.toString()))
            .andExpect(jsonPath("$.fileMime").value(DEFAULT_FILE_MIME.toString()))
            .andExpect(jsonPath("$.fileSize").value(DEFAULT_FILE_SIZE));
    }

    @Test
    @Transactional
    public void getNonExistingCourseLessonAttachment() throws Exception {
        // Get the courseLessonAttachment
        restCourseLessonAttachmentMockMvc.perform(get("/api/course-lesson-attachments/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCourseLessonAttachment() throws Exception {
        // Initialize the database
        courseLessonAttachmentService.save(courseLessonAttachment);

        int databaseSizeBeforeUpdate = courseLessonAttachmentRepository.findAll().size();

        // Update the courseLessonAttachment
        CourseLessonAttachment updatedCourseLessonAttachment = courseLessonAttachmentRepository.findOne(courseLessonAttachment.getId());
        updatedCourseLessonAttachment
            .courseId(UPDATED_COURSE_ID)
            .userId(UPDATED_USER_ID)
            .title(UPDATED_TITLE)
            .description(UPDATED_DESCRIPTION)
            .link(UPDATED_LINK)
            .fileId(UPDATED_FILE_ID)
            .fileUri(UPDATED_FILE_URI)
            .fileMime(UPDATED_FILE_MIME)
            .fileSize(UPDATED_FILE_SIZE);

        restCourseLessonAttachmentMockMvc.perform(put("/api/course-lesson-attachments")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedCourseLessonAttachment)))
            .andExpect(status().isOk());

        // Validate the CourseLessonAttachment in the database
        List<CourseLessonAttachment> courseLessonAttachmentList = courseLessonAttachmentRepository.findAll();
        assertThat(courseLessonAttachmentList).hasSize(databaseSizeBeforeUpdate);
        CourseLessonAttachment testCourseLessonAttachment = courseLessonAttachmentList.get(courseLessonAttachmentList.size() - 1);
        assertThat(testCourseLessonAttachment.getCourseId()).isEqualTo(UPDATED_COURSE_ID);
        assertThat(testCourseLessonAttachment.getUserId()).isEqualTo(UPDATED_USER_ID);
        assertThat(testCourseLessonAttachment.getTitle()).isEqualTo(UPDATED_TITLE);
        assertThat(testCourseLessonAttachment.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testCourseLessonAttachment.getLink()).isEqualTo(UPDATED_LINK);
        assertThat(testCourseLessonAttachment.getFileId()).isEqualTo(UPDATED_FILE_ID);
        assertThat(testCourseLessonAttachment.getFileUri()).isEqualTo(UPDATED_FILE_URI);
        assertThat(testCourseLessonAttachment.getFileMime()).isEqualTo(UPDATED_FILE_MIME);
        assertThat(testCourseLessonAttachment.getFileSize()).isEqualTo(UPDATED_FILE_SIZE);
    }

    @Test
    @Transactional
    public void updateNonExistingCourseLessonAttachment() throws Exception {
        int databaseSizeBeforeUpdate = courseLessonAttachmentRepository.findAll().size();

        // Create the CourseLessonAttachment

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restCourseLessonAttachmentMockMvc.perform(put("/api/course-lesson-attachments")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(courseLessonAttachment)))
            .andExpect(status().isCreated());

        // Validate the CourseLessonAttachment in the database
        List<CourseLessonAttachment> courseLessonAttachmentList = courseLessonAttachmentRepository.findAll();
        assertThat(courseLessonAttachmentList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteCourseLessonAttachment() throws Exception {
        // Initialize the database
        courseLessonAttachmentService.save(courseLessonAttachment);

        int databaseSizeBeforeDelete = courseLessonAttachmentRepository.findAll().size();

        // Get the courseLessonAttachment
        restCourseLessonAttachmentMockMvc.perform(delete("/api/course-lesson-attachments/{id}", courseLessonAttachment.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<CourseLessonAttachment> courseLessonAttachmentList = courseLessonAttachmentRepository.findAll();
        assertThat(courseLessonAttachmentList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CourseLessonAttachment.class);
        CourseLessonAttachment courseLessonAttachment1 = new CourseLessonAttachment();
        courseLessonAttachment1.setId(1L);
        CourseLessonAttachment courseLessonAttachment2 = new CourseLessonAttachment();
        courseLessonAttachment2.setId(courseLessonAttachment1.getId());
        assertThat(courseLessonAttachment1).isEqualTo(courseLessonAttachment2);
        courseLessonAttachment2.setId(2L);
        assertThat(courseLessonAttachment1).isNotEqualTo(courseLessonAttachment2);
        courseLessonAttachment1.setId(null);
        assertThat(courseLessonAttachment1).isNotEqualTo(courseLessonAttachment2);
    }
}
