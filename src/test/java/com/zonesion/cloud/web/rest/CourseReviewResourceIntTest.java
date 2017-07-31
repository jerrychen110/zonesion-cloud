package com.zonesion.cloud.web.rest;

import com.zonesion.cloud.ZonesionCloudApplicationApp;

import com.zonesion.cloud.domain.CourseReview;
import com.zonesion.cloud.domain.Course;
import com.zonesion.cloud.repository.CourseReviewRepository;
import com.zonesion.cloud.service.CourseReviewService;
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
 * Test class for the CourseReviewResource REST controller.
 *
 * @see CourseReviewResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ZonesionCloudApplicationApp.class)
public class CourseReviewResourceIntTest {

    private static final Long DEFAULT_USER_ID = 1L;
    private static final Long UPDATED_USER_ID = 2L;

    private static final String DEFAULT_TITLE = "AAAAAAAAAA";
    private static final String UPDATED_TITLE = "BBBBBBBBBB";

    private static final String DEFAULT_CONTENT = "AAAAAAAAAA";
    private static final String UPDATED_CONTENT = "BBBBBBBBBB";

    private static final Integer DEFAULT_RATING = 1;
    private static final Integer UPDATED_RATING = 2;

    private static final String DEFAULT_PRIVACY = "A";
    private static final String UPDATED_PRIVACY = "B";

    @Autowired
    private CourseReviewRepository courseReviewRepository;

    @Autowired
    private CourseReviewService courseReviewService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restCourseReviewMockMvc;

    private CourseReview courseReview;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        CourseReviewResource courseReviewResource = new CourseReviewResource(courseReviewService);
        this.restCourseReviewMockMvc = MockMvcBuilders.standaloneSetup(courseReviewResource)
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
    public static CourseReview createEntity(EntityManager em) {
        CourseReview courseReview = new CourseReview()
            .userId(DEFAULT_USER_ID)
            .title(DEFAULT_TITLE)
            .content(DEFAULT_CONTENT)
            .rating(DEFAULT_RATING)
            .privacy(DEFAULT_PRIVACY);
        // Add required entity
        Course course = CourseResourceIntTest.createEntity(em);
        em.persist(course);
        em.flush();
        courseReview.setCourse(course);
        return courseReview;
    }

    @Before
    public void initTest() {
        courseReview = createEntity(em);
    }

    @Test
    @Transactional
    public void createCourseReview() throws Exception {
        int databaseSizeBeforeCreate = courseReviewRepository.findAll().size();

        // Create the CourseReview
        restCourseReviewMockMvc.perform(post("/api/course-reviews")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(courseReview)))
            .andExpect(status().isCreated());

        // Validate the CourseReview in the database
        List<CourseReview> courseReviewList = courseReviewRepository.findAll();
        assertThat(courseReviewList).hasSize(databaseSizeBeforeCreate + 1);
        CourseReview testCourseReview = courseReviewList.get(courseReviewList.size() - 1);
        assertThat(testCourseReview.getUserId()).isEqualTo(DEFAULT_USER_ID);
        assertThat(testCourseReview.getTitle()).isEqualTo(DEFAULT_TITLE);
        assertThat(testCourseReview.getContent()).isEqualTo(DEFAULT_CONTENT);
        assertThat(testCourseReview.getRating()).isEqualTo(DEFAULT_RATING);
        assertThat(testCourseReview.getPrivacy()).isEqualTo(DEFAULT_PRIVACY);
    }

    @Test
    @Transactional
    public void createCourseReviewWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = courseReviewRepository.findAll().size();

        // Create the CourseReview with an existing ID
        courseReview.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCourseReviewMockMvc.perform(post("/api/course-reviews")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(courseReview)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<CourseReview> courseReviewList = courseReviewRepository.findAll();
        assertThat(courseReviewList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkUserIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = courseReviewRepository.findAll().size();
        // set the field null
        courseReview.setUserId(null);

        // Create the CourseReview, which fails.

        restCourseReviewMockMvc.perform(post("/api/course-reviews")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(courseReview)))
            .andExpect(status().isBadRequest());

        List<CourseReview> courseReviewList = courseReviewRepository.findAll();
        assertThat(courseReviewList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTitleIsRequired() throws Exception {
        int databaseSizeBeforeTest = courseReviewRepository.findAll().size();
        // set the field null
        courseReview.setTitle(null);

        // Create the CourseReview, which fails.

        restCourseReviewMockMvc.perform(post("/api/course-reviews")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(courseReview)))
            .andExpect(status().isBadRequest());

        List<CourseReview> courseReviewList = courseReviewRepository.findAll();
        assertThat(courseReviewList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkContentIsRequired() throws Exception {
        int databaseSizeBeforeTest = courseReviewRepository.findAll().size();
        // set the field null
        courseReview.setContent(null);

        // Create the CourseReview, which fails.

        restCourseReviewMockMvc.perform(post("/api/course-reviews")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(courseReview)))
            .andExpect(status().isBadRequest());

        List<CourseReview> courseReviewList = courseReviewRepository.findAll();
        assertThat(courseReviewList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkRatingIsRequired() throws Exception {
        int databaseSizeBeforeTest = courseReviewRepository.findAll().size();
        // set the field null
        courseReview.setRating(null);

        // Create the CourseReview, which fails.

        restCourseReviewMockMvc.perform(post("/api/course-reviews")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(courseReview)))
            .andExpect(status().isBadRequest());

        List<CourseReview> courseReviewList = courseReviewRepository.findAll();
        assertThat(courseReviewList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPrivacyIsRequired() throws Exception {
        int databaseSizeBeforeTest = courseReviewRepository.findAll().size();
        // set the field null
        courseReview.setPrivacy(null);

        // Create the CourseReview, which fails.

        restCourseReviewMockMvc.perform(post("/api/course-reviews")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(courseReview)))
            .andExpect(status().isBadRequest());

        List<CourseReview> courseReviewList = courseReviewRepository.findAll();
        assertThat(courseReviewList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllCourseReviews() throws Exception {
        // Initialize the database
        courseReviewRepository.saveAndFlush(courseReview);

        // Get all the courseReviewList
        restCourseReviewMockMvc.perform(get("/api/course-reviews?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(courseReview.getId().intValue())))
            .andExpect(jsonPath("$.[*].userId").value(hasItem(DEFAULT_USER_ID.intValue())))
            .andExpect(jsonPath("$.[*].title").value(hasItem(DEFAULT_TITLE.toString())))
            .andExpect(jsonPath("$.[*].content").value(hasItem(DEFAULT_CONTENT.toString())))
            .andExpect(jsonPath("$.[*].rating").value(hasItem(DEFAULT_RATING)))
            .andExpect(jsonPath("$.[*].privacy").value(hasItem(DEFAULT_PRIVACY.toString())));
    }

    @Test
    @Transactional
    public void getCourseReview() throws Exception {
        // Initialize the database
        courseReviewRepository.saveAndFlush(courseReview);

        // Get the courseReview
        restCourseReviewMockMvc.perform(get("/api/course-reviews/{id}", courseReview.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(courseReview.getId().intValue()))
            .andExpect(jsonPath("$.userId").value(DEFAULT_USER_ID.intValue()))
            .andExpect(jsonPath("$.title").value(DEFAULT_TITLE.toString()))
            .andExpect(jsonPath("$.content").value(DEFAULT_CONTENT.toString()))
            .andExpect(jsonPath("$.rating").value(DEFAULT_RATING))
            .andExpect(jsonPath("$.privacy").value(DEFAULT_PRIVACY.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingCourseReview() throws Exception {
        // Get the courseReview
        restCourseReviewMockMvc.perform(get("/api/course-reviews/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCourseReview() throws Exception {
        // Initialize the database
        courseReviewService.save(courseReview);

        int databaseSizeBeforeUpdate = courseReviewRepository.findAll().size();

        // Update the courseReview
        CourseReview updatedCourseReview = courseReviewRepository.findOne(courseReview.getId());
        updatedCourseReview
            .userId(UPDATED_USER_ID)
            .title(UPDATED_TITLE)
            .content(UPDATED_CONTENT)
            .rating(UPDATED_RATING)
            .privacy(UPDATED_PRIVACY);

        restCourseReviewMockMvc.perform(put("/api/course-reviews")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedCourseReview)))
            .andExpect(status().isOk());

        // Validate the CourseReview in the database
        List<CourseReview> courseReviewList = courseReviewRepository.findAll();
        assertThat(courseReviewList).hasSize(databaseSizeBeforeUpdate);
        CourseReview testCourseReview = courseReviewList.get(courseReviewList.size() - 1);
        assertThat(testCourseReview.getUserId()).isEqualTo(UPDATED_USER_ID);
        assertThat(testCourseReview.getTitle()).isEqualTo(UPDATED_TITLE);
        assertThat(testCourseReview.getContent()).isEqualTo(UPDATED_CONTENT);
        assertThat(testCourseReview.getRating()).isEqualTo(UPDATED_RATING);
        assertThat(testCourseReview.getPrivacy()).isEqualTo(UPDATED_PRIVACY);
    }

    @Test
    @Transactional
    public void updateNonExistingCourseReview() throws Exception {
        int databaseSizeBeforeUpdate = courseReviewRepository.findAll().size();

        // Create the CourseReview

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restCourseReviewMockMvc.perform(put("/api/course-reviews")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(courseReview)))
            .andExpect(status().isCreated());

        // Validate the CourseReview in the database
        List<CourseReview> courseReviewList = courseReviewRepository.findAll();
        assertThat(courseReviewList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteCourseReview() throws Exception {
        // Initialize the database
        courseReviewService.save(courseReview);

        int databaseSizeBeforeDelete = courseReviewRepository.findAll().size();

        // Get the courseReview
        restCourseReviewMockMvc.perform(delete("/api/course-reviews/{id}", courseReview.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<CourseReview> courseReviewList = courseReviewRepository.findAll();
        assertThat(courseReviewList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CourseReview.class);
        CourseReview courseReview1 = new CourseReview();
        courseReview1.setId(1L);
        CourseReview courseReview2 = new CourseReview();
        courseReview2.setId(courseReview1.getId());
        assertThat(courseReview1).isEqualTo(courseReview2);
        courseReview2.setId(2L);
        assertThat(courseReview1).isNotEqualTo(courseReview2);
        courseReview1.setId(null);
        assertThat(courseReview1).isNotEqualTo(courseReview2);
    }
}
