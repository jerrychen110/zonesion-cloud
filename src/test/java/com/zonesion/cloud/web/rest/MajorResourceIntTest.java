package com.zonesion.cloud.web.rest;

import com.zonesion.cloud.ZonesionCloudApplicationApp;

import com.zonesion.cloud.domain.Major;
import com.zonesion.cloud.repository.MajorRepository;
import com.zonesion.cloud.service.MajorService;
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
 * Test class for the MajorResource REST controller.
 *
 * @see MajorResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ZonesionCloudApplicationApp.class)
public class MajorResourceIntTest {

    private static final String DEFAULT_MAJOR = "AAAAAAAAAA";
    private static final String UPDATED_MAJOR = "BBBBBBBBBB";

    @Autowired
    private MajorRepository majorRepository;

    @Autowired
    private MajorService majorService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restMajorMockMvc;

    private Major major;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        MajorResource majorResource = new MajorResource(majorService);
        this.restMajorMockMvc = MockMvcBuilders.standaloneSetup(majorResource)
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
    public static Major createEntity(EntityManager em) {
        Major major = new Major()
            .major(DEFAULT_MAJOR);
        return major;
    }

    @Before
    public void initTest() {
        major = createEntity(em);
    }

    @Test
    @Transactional
    public void createMajor() throws Exception {
        int databaseSizeBeforeCreate = majorRepository.findAll().size();

        // Create the Major
        restMajorMockMvc.perform(post("/api/majors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(major)))
            .andExpect(status().isCreated());

        // Validate the Major in the database
        List<Major> majorList = majorRepository.findAll();
        assertThat(majorList).hasSize(databaseSizeBeforeCreate + 1);
        Major testMajor = majorList.get(majorList.size() - 1);
        assertThat(testMajor.getMajor()).isEqualTo(DEFAULT_MAJOR);
    }

    @Test
    @Transactional
    public void createMajorWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = majorRepository.findAll().size();

        // Create the Major with an existing ID
        major.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMajorMockMvc.perform(post("/api/majors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(major)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Major> majorList = majorRepository.findAll();
        assertThat(majorList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkMajorIsRequired() throws Exception {
        int databaseSizeBeforeTest = majorRepository.findAll().size();
        // set the field null
        major.setMajor(null);

        // Create the Major, which fails.

        restMajorMockMvc.perform(post("/api/majors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(major)))
            .andExpect(status().isBadRequest());

        List<Major> majorList = majorRepository.findAll();
        assertThat(majorList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMajors() throws Exception {
        // Initialize the database
        majorRepository.saveAndFlush(major);

        // Get all the majorList
        restMajorMockMvc.perform(get("/api/majors?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(major.getId().intValue())))
            .andExpect(jsonPath("$.[*].major").value(hasItem(DEFAULT_MAJOR.toString())));
    }

    @Test
    @Transactional
    public void getMajor() throws Exception {
        // Initialize the database
        majorRepository.saveAndFlush(major);

        // Get the major
        restMajorMockMvc.perform(get("/api/majors/{id}", major.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(major.getId().intValue()))
            .andExpect(jsonPath("$.major").value(DEFAULT_MAJOR.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingMajor() throws Exception {
        // Get the major
        restMajorMockMvc.perform(get("/api/majors/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMajor() throws Exception {
        // Initialize the database
        majorService.save(major);

        int databaseSizeBeforeUpdate = majorRepository.findAll().size();

        // Update the major
        Major updatedMajor = majorRepository.findOne(major.getId());
        updatedMajor
            .major(UPDATED_MAJOR);

        restMajorMockMvc.perform(put("/api/majors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedMajor)))
            .andExpect(status().isOk());

        // Validate the Major in the database
        List<Major> majorList = majorRepository.findAll();
        assertThat(majorList).hasSize(databaseSizeBeforeUpdate);
        Major testMajor = majorList.get(majorList.size() - 1);
        assertThat(testMajor.getMajor()).isEqualTo(UPDATED_MAJOR);
    }

    @Test
    @Transactional
    public void updateNonExistingMajor() throws Exception {
        int databaseSizeBeforeUpdate = majorRepository.findAll().size();

        // Create the Major

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restMajorMockMvc.perform(put("/api/majors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(major)))
            .andExpect(status().isCreated());

        // Validate the Major in the database
        List<Major> majorList = majorRepository.findAll();
        assertThat(majorList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteMajor() throws Exception {
        // Initialize the database
        majorService.save(major);

        int databaseSizeBeforeDelete = majorRepository.findAll().size();

        // Get the major
        restMajorMockMvc.perform(delete("/api/majors/{id}", major.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Major> majorList = majorRepository.findAll();
        assertThat(majorList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Major.class);
        Major major1 = new Major();
        major1.setId(1L);
        Major major2 = new Major();
        major2.setId(major1.getId());
        assertThat(major1).isEqualTo(major2);
        major2.setId(2L);
        assertThat(major1).isNotEqualTo(major2);
        major1.setId(null);
        assertThat(major1).isNotEqualTo(major2);
    }
}
