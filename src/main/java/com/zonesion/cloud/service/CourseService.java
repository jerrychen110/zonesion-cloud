package com.zonesion.cloud.service;

import com.zonesion.cloud.config.Constants;
import com.zonesion.cloud.domain.Chapter;
import com.zonesion.cloud.domain.Course;
import com.zonesion.cloud.domain.CourseLesson;
import com.zonesion.cloud.domain.CourseMember;
import com.zonesion.cloud.domain.CourseReview;
import com.zonesion.cloud.repository.CourseFavoriteRepository;
import com.zonesion.cloud.repository.CourseLessonLearnRepository;
import com.zonesion.cloud.repository.CourseMemberRepository;
import com.zonesion.cloud.repository.CourseRepository;
import com.zonesion.cloud.repository.UserRepository;
import com.zonesion.cloud.security.SecurityUtils;
import com.zonesion.cloud.service.dto.CourseLessonAttachmentDTO;
import com.zonesion.cloud.service.dto.CourseLessonNoteDTO;
import com.zonesion.cloud.service.dto.CourseReviewDTO;
import com.zonesion.cloud.service.dto.ext.CourseReviewExtDTO;
import com.zonesion.cloud.web.rest.dto.in.CourseReviewInDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zonesion.cloud.web.rest.dto.CourseLessonInfoDTO;
import com.zonesion.cloud.web.rest.dto.LessonInfoDTO;
import com.zonesion.cloud.web.rest.dto.MyCourseDTO;
import com.zonesion.cloud.web.rest.dto.UnitInfoDTO;
import com.zonesion.cloud.web.rest.dto.in.ChapterInDTO;
import com.zonesion.cloud.web.rest.dto.in.CourseLessonInDTO;
import com.zonesion.cloud.web.rest.dto.ChapterInfoDTO;
import com.zonesion.cloud.web.rest.dto.CourseBaseInfoDTO;

/**
 * Service Implementation for managing Course.
 */
@Service
@Transactional
public class CourseService {

    private final Logger log = LoggerFactory.getLogger(CourseService.class);

    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }
    
    @Inject
    private JdbcTemplate jdbcTemplate;
    
    @Inject
    private UserRepository userRepository;
    
    @Inject
    private CourseLessonLearnRepository courseLessonLearnRepository;
    
    @Inject
    private CourseFavoriteRepository courseFavoriteRepository;
    
    @Inject
    private CourseLessonAttachmentService courseLessonAttachmentService;
    
    @Inject
    private CourseReviewService courseReviewService;
    
    @Inject
    private CourseLessonNoteService courseLessonNoteService;
    
    @Inject
    private ChapterService chapterService;
    
    @Inject
    private CourseLessonService courseLessonService;
    
    @Inject
    private UserService userService;
    
    @Inject
    private CourseMemberRepository courseMemberRepository;

    /**
     * Save a course.
     *
     * @param course the entity to save
     * @return the persisted entity
     */
    public Course save(Course course) {
        log.debug("Request to save Course : {}", course);
        return courseRepository.save(course);
    }

    /**
     *  Get all the courses.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<Course> findAll(Pageable pageable) {
        log.debug("Request to get all Courses");
        return courseRepository.findAll(pageable);
    }

    /**
     *  Get one course by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true)
    public Course findOne(Long id) {
        log.debug("Request to get Course : {}", id);
        return courseRepository.findOne(id);
    }

    /**
     *  Delete the  course by id.
     *
     *  @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Course : {}", id);
        courseRepository.delete(id);
    }
    
    public List<Course> findByCourseId(Long courseId) {
		return courseRepository.findByCourseId(courseId);
	}

	public List<Course> findByUserId(Long userId) {
		return courseRepository.findByUserId(userId);
	}
	
	/**
	 * 根据课程id查询课程的课时信息
	 * @param id
	 * @return
	 */
	@Transactional(readOnly = true)
    public CourseLessonInfoDTO findCourseLessonInfoDTO(Long id) {
		long currentUserId=0;
		String currentLogin = SecurityUtils.getCurrentUserLogin();
		if(StringUtils.isNotBlank(currentLogin)&&!"anonymousUser".equals(currentLogin)) {
			currentUserId = userRepository.findOneByLogin(currentLogin).get().getId();
		}
        log.debug("Request to get Course : {}", id);
        CourseLessonInfoDTO courseInfoDTO = new CourseLessonInfoDTO();
        courseInfoDTO.setCourseId(id);
        Course course = courseRepository.findOne(id);
        if(course.getChapters()!=null&&!course.getChapters().isEmpty()) {
        	List<ChapterInfoDTO> chapters = new ArrayList<>();
        	List<UnitInfoDTO> units = new ArrayList<>();
        	for(Chapter obj:course.getChapters()) {
        		if(StringUtils.isNoneBlank(obj.getChapterType())) {
        			if("0".equals(obj.getChapterType())) {
        				ChapterInfoDTO chapterDTO = new ChapterInfoDTO();
        				chapterDTO.setId(obj.getId());
        				chapterDTO.setChapterType(obj.getChapterType());
        				chapterDTO.setTitle(obj.getTitle());
        				chapterDTO.setNumber(obj.getNumber());
        				chapterDTO.setSeq(obj.getSeq());
        				chapters.add(chapterDTO);
        			}else {
        				UnitInfoDTO unitDTO = new UnitInfoDTO();
        				unitDTO.setId(obj.getId());
        				unitDTO.setParentId(obj.getParentId());
        				unitDTO.setChapterType(obj.getChapterType());
        				unitDTO.setTitle(obj.getTitle());
        				unitDTO.setNumber(obj.getNumber());
        				unitDTO.setSeq(obj.getSeq());
        				String lessonSql = null;
        				if(currentUserId!=0) {
        					StringBuilder sb = new StringBuilder();
        					sb.append("select tcl.id,tcl.title,tcl.summary,tcl.course_lesson_type,tcl.content,tcl.number,tcl.seq,")
        					.append("tcl.credit,tcl.learned_num,tcl.viewed_num,tcl.media_name,tcl.media_source,tcl.media_uri,tcl.media_size,")
        					.append("tcl.media_length,tcll.id,tcll.user_id,tcll.is_complete,")
        					.append("case when tcll.is_complete is null then '0' when tcll.is_complete='0' then '1' else '2' end as status ")
        					.append("from t_course_lesson tcl left join t_course_lesson_learn tcll on tcl.id=tcll.course_lesson_id ")
        					.append("and tcll.user_id=").append(currentUserId).append(" where tcl.chapter_id=").append(obj.getId()).append(" order by seq");
        					lessonSql = sb.toString();
        				}else {
        					lessonSql = "select id,title,summary,course_lesson_type,content,number,seq,credit,learned_num,viewed_num,media_name,media_source,media_uri,media_size,media_length,0 as user_id,0 as status from t_course_lesson where chapter_id="+obj.getId()+" order by seq";
        				}
        				@SuppressWarnings({ "unchecked", "rawtypes" })
						List<LessonInfoDTO> lessons = jdbcTemplate.query(lessonSql,
        					new RowMapper(){
	                            @Override
	                            public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
	                            	LessonInfoDTO lessonDTO  = new LessonInfoDTO();
	                            	lessonDTO.setId(rs.getLong("id"));
	                            	lessonDTO.setTitle(rs.getString("title"));
	                            	lessonDTO.setSummary(rs.getString("summary")!=null?rs.getString("summary"):"");
	                            	lessonDTO.setCourseLessonType(rs.getString("course_lesson_type")!=null?rs.getString("course_lesson_type"):"");
	                            	lessonDTO.setNumber(rs.getInt("number"));
	                            	lessonDTO.setSeq(rs.getInt("seq"));
	                            	lessonDTO.setContent(rs.getString("content")!=null?rs.getString("content"):"");
	                            	lessonDTO.setCredit(rs.getInt("credit")!=0?rs.getInt("credit"):0);
	                            	lessonDTO.setMediaName(rs.getString("media_name")!=null?rs.getString("media_name"):"");
	                            	lessonDTO.setMediaSource(rs.getString("media_source")!=null?rs.getString("media_source"):"");
	                            	lessonDTO.setMediaUri(rs.getString("media_uri")!=null?rs.getString("media_uri"):"");
	                            	lessonDTO.setMediaSize(rs.getInt("media_size")!=0?rs.getInt("media_size"):0);
	                            	lessonDTO.setMediaLength(rs.getInt("media_length")!=0?rs.getInt("media_length"):0);
	                            	lessonDTO.setLearnedNum(rs.getInt("learned_num")!=0?rs.getInt("learned_num"):0);
	                            	lessonDTO.setViewedNum(rs.getInt("viewed_num")!=0?rs.getInt("viewed_num"):0);
	                            	lessonDTO.setLearnedStatus(rs.getString("status")!=null?rs.getString("status"):"");
	                                return lessonDTO;
	                            }
        					});
        				unitDTO.setLessons(lessons);
        				units.add(unitDTO);
        			}
        		}
        	}
        	
        	//进行最终对象的封装
        	if(chapters.size()>0&&units.size()>0) {
        		for(ChapterInfoDTO chapter:chapters) {
        			List<UnitInfoDTO> thisUnits = new ArrayList<>();
        			for(UnitInfoDTO unit:units) {
        				if(chapter.id==unit.parentId) {
        					thisUnits.add(unit);
        				}
        			}
        			Collections.sort(thisUnits, new Comparator<UnitInfoDTO>(){
        				 public int compare(UnitInfoDTO o1, UnitInfoDTO o2) {
        					 if(o1.getNumber() > o2.getNumber()){
    		                    return 1;
    		                }
    		                if(o1.getNumber() == o2.getNumber()){
    		                    return 0;
    		                }
    		                return -1;
        				 }
        			});
        			chapter.units=thisUnits;
        		}
        		Collections.sort(chapters, new Comparator<ChapterInfoDTO>(){
      				 public int compare(ChapterInfoDTO o1, ChapterInfoDTO o2) {
      					 if(o1.getNumber() > o2.getNumber()){
   		                    return 1;
   		                }
   		                if(o1.getNumber() == o2.getNumber()){
   		                    return 0;
   		                }
   		                return -1;
   	   				 }
   	   			});
        	}
        	courseInfoDTO.setChapters(chapters);
        }
        return courseInfoDTO;
    }
	
	/**
	 * 根据课程id查询课程基本信息（课程信息、学员情况）
	 * @param id
	 * @return
	 */
	@Transactional(readOnly = true)
	public CourseBaseInfoDTO findCourseBaseInfoDTO(long id) {
		long currentUserId=0;
		String currentLogin = SecurityUtils.getCurrentUserLogin();
		if(StringUtils.isNotBlank(currentLogin)&&!"anonymousUser".equals(currentLogin)) {
			currentUserId = userRepository.findOneByLogin(currentLogin).get().getId();
		}
		CourseBaseInfoDTO courseBaseInfoDTO = new CourseBaseInfoDTO();
		Course course = courseRepository.findOne(id);
		courseBaseInfoDTO.setId(id);
		courseBaseInfoDTO.setTitle(course.getTitle());
		courseBaseInfoDTO.setSubTitle(course.getSubTitle());
		courseBaseInfoDTO.setStatus(course.getStatus());
		courseBaseInfoDTO.setCourseType(course.getCourseType());
		courseBaseInfoDTO.setCourseSource(course.getCourseSource());
		courseBaseInfoDTO.setLessonNum(course.getLessonNum());
		courseBaseInfoDTO.setCredit(course.getCredit());
		courseBaseInfoDTO.setCoverPicture(course.getCoverPicture());
		courseBaseInfoDTO.setIntroduction(course.getIntroduction());
		courseBaseInfoDTO.setGoals(course.getGoals());
		courseBaseInfoDTO.setTags(course.getTags());
		courseBaseInfoDTO.setLearnedNum(courseMemberRepository.getLearnedNumByCourseId(id));
		String learnedStatus = "0";
		String isCollected = "0";
		if(currentUserId>0) {
			if(courseBaseInfoDTO.getLearnedNum()>0) {
				learnedStatus = "1";
				courseBaseInfoDTO.setLearnedUsers(userRepository.findAllLearnedUserByCourseId(id));
			}
			int collected = courseFavoriteRepository.getFavoriteNumByCourseIdAndUserId(id, currentUserId);
			if(collected>0) {
				isCollected = "1";
			}
		}
		courseBaseInfoDTO.setLearnedStatus(learnedStatus);
		courseBaseInfoDTO.setIsCollected(isCollected);
		return courseBaseInfoDTO;
	}
	
	/**
	 * 查询课程的附件资料
	 * @param courseId
	 * @param pageable
	 * @return
	 */
	@Transactional(readOnly = true)
	public Page<CourseLessonAttachmentDTO> getCourseAttachementsByCourseId(long courseId, Pageable pageable) {
		return courseLessonAttachmentService.findAllByTargetTypeAndTargetId(Constants.ATTACHEMENT_TYPE_COURSE, courseId, pageable);
	}
	
	/**
	 * 查询课程的评论记录
	 * @param id
	 * @param pageable
	 * @return
	 */
	@Transactional(readOnly = true)
	public Page<CourseReviewExtDTO> getCourseReviewsByCourseId(long id, Pageable pageable) {
		return courseReviewService.getCourseReviewsByCourseId(id, pageable);
	}
	
	public CourseReviewDTO saveCourseReview(long id, CourseReviewInDTO courseReviewInDTO) {
		CourseReview courseReview = new CourseReview();
		courseReview.setTitle(courseReviewInDTO.getTitle());
		courseReview.setContent(courseReviewInDTO.getContent());
		courseReview.setUserId(courseReviewInDTO.getUserId());
		courseReview.setRating(courseReviewInDTO.getRating());
		courseReview.setPrivacy(courseReviewInDTO.getPrivacy());
		courseReview.setCourse(courseRepository.findOne(id));
		return new CourseReviewDTO(courseReviewService.save(courseReview));
	}
	
	@Transactional(readOnly = true)
	public Page<CourseLessonNoteDTO> getCourseNotesByCourseId(Long id, Pageable pageable, Long courseLessonId){
		return courseLessonNoteService.getCourseNotesByCourseId(id, pageable, courseLessonId);
	}
	
	public Chapter saveChapter(Long id, ChapterInDTO chapterInDTO) {
		Chapter chapter = new Chapter();
		if(chapterInDTO.getId()!=null) {
			chapter.setId(chapterInDTO.getId());
			chapter.setNumber(chapterInDTO.getNumber());
			chapter.setSeq(chapterInDTO.getSeq());
		}else {
			chapter.setNumber(chapterService.getMaxNumberByCourseId(id, chapterInDTO.getChapterType())+1);
			chapter.setSeq(chapterService.getMaxSeqByCourseId(id, chapterInDTO.getChapterType())+1);
		}
		chapter.setChapterType(chapterInDTO.getChapterType());
		chapter.setTitle(chapterInDTO.getTitle());
		chapter.setParentId(chapterInDTO.getParentId());
		chapter.setCourse(courseRepository.findOne(id));
		chapter.setUserId(chapterInDTO.getUserId());
		return chapterService.save(chapter);
	}
	
	public CourseLesson saveLesson(Long id, CourseLessonInDTO courseLessonInDTO) {
		CourseLesson newCourseLesson = new CourseLesson();
		if(courseLessonInDTO.getId()!=null) {
			newCourseLesson.setId(courseLessonInDTO.getId());
			newCourseLesson.setNumber(courseLessonInDTO.getNumber());
			newCourseLesson.setSeq(courseLessonInDTO.getSeq());
		}else {
			int maxNumber = courseLessonService.getLessonMaxNumberByCourseId(id);
			newCourseLesson.setNumber(maxNumber+1);
			newCourseLesson.setSeq(maxNumber+1);
		}
		newCourseLesson.setTitle(courseLessonInDTO.getTitle());
		newCourseLesson.setSummary(courseLessonInDTO.getSummary());
		newCourseLesson.setUserId(courseLessonInDTO.getUserId());
		newCourseLesson.setCourseId(id);
		newCourseLesson.setChapter(chapterService.findOne(courseLessonInDTO.getChapterId()));
		newCourseLesson.setCredit(courseLessonInDTO.getCredit());
		newCourseLesson.setCourseLessonType(courseLessonInDTO.getCourseLessonType());
		newCourseLesson.setContent(courseLessonInDTO.getContent());
		newCourseLesson.setMediaName(courseLessonInDTO.getMediaName());
		newCourseLesson.setMediaUri(courseLessonInDTO.getMediaUri());
		newCourseLesson.setMediaSource(courseLessonInDTO.getMediaSource());
		newCourseLesson.setMediaSize(courseLessonInDTO.getMediaSize());
		newCourseLesson.setMediaLength(courseLessonInDTO.getMediaLength());
		newCourseLesson.setLearnedNum(0);
		newCourseLesson.setViewedNum(0);
		return courseLessonService.save(newCourseLesson);
	}
	
	public CourseMember joinCourse(CourseMember courseMember) {
		CourseMember findCourseMember = courseMemberRepository.findOneByCourseIdAndUserId(courseMember.getCourseId(), courseMember.getUserId());
		if(findCourseMember==null) {
			findCourseMember = courseMemberRepository.save(courseMember);
		}
		return findCourseMember;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Page<MyCourseDTO> getMyLearningCourses(Pageable pageable) {
		long currentUserId = userService.getCurrentUserId();
		StringBuilder sb = new StringBuilder();
		sb.append("select distinct tc.id,tc.title,tc.sub_title,tc.lesson_num,tc.cover_picture,tc.introduction,")
		.append("tc.goals,tc.tags,tclln.learned_num,tu.id user_id,tu.login,tu.avatar")
		.append(" from t_course tc")
		.append(" left join t_course_lesson_learn tcll ON tc.id = tcll.course_id")
		.append(" left join t_user tu ON tcll.user_id = tu.id")
		.append(" left join (select course_id,count(distinct user_id) learned_num from t_course_lesson_learn group by course_id order by course_id) tclln on tc.id=tclln.course_id")
		.append(" where tu.id=").append(currentUserId).append(" order by tc.created_date desc");
		
		List<MyCourseDTO> myCourses = jdbcTemplate.query(sb.toString(), new RowMapper(){
            @Override
            public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
            	MyCourseDTO myCourseDTO  = new MyCourseDTO();
            	myCourseDTO.setId(rs.getLong("id"));
            	myCourseDTO.setTitle(rs.getString("title")!=null?rs.getString("title"):"");
            	myCourseDTO.setSubTitle(rs.getString("sub_title")!=null?rs.getString("sub_title"):"");
            	myCourseDTO.setLessonNum(rs.getInt("lesson_num")!=0?rs.getInt("lesson_num"):0);
            	myCourseDTO.setCoverPicture(rs.getString("cover_picture")!=null?rs.getString("cover_picture"):"");
            	myCourseDTO.setIntroduction(rs.getString("introduction")!=null?rs.getString("introduction"):"");
            	myCourseDTO.setGoals(rs.getString("goals")!=null?rs.getString("goals"):"");
            	myCourseDTO.setTags(rs.getString("tags")!=null?rs.getString("tags"):"");
            	myCourseDTO.setLearnedNum(rs.getInt("learned_num")!=0?rs.getInt("learned_num"):0);
            	myCourseDTO.setUserId(rs.getLong("user_id"));
            	myCourseDTO.setLogin(rs.getString("login")!=null?rs.getString("login"):"");
            	myCourseDTO.setAvatar(rs.getString("avatar")!=null?rs.getString("avatar"):"");
                return myCourseDTO;
            }
		});
		Page<MyCourseDTO> result = new PageImpl<>(myCourses, pageable, myCourses.size());
    	return result;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Page<MyCourseDTO> getMyFavoriteCourses(Pageable pageable) {
		long currentUserId = userService.getCurrentUserId();
		StringBuilder sb = new StringBuilder();
		sb.append("select distinct tc.id,tc.title,tc.sub_title,tc.lesson_num,tc.cover_picture,tc.introduction,")
		.append("tc.goals,tc.tags,tclln.learned_num,tu.id user_id,tu.login,tu.avatar")
		.append(" from t_course tc")
		.append(" left join t_course_favorite tcf ON tc.id = tcf.course_id")
		.append(" left join t_user tu ON tcf.user_id = tu.id")
		.append(" left join (select course_id,count(distinct user_id) learned_num from t_course_lesson_learn group by course_id order by course_id) tclln on tc.id=tclln.course_id")
		.append(" where tu.id=").append(currentUserId).append(" order by tc.created_date desc");
		
		List<MyCourseDTO> myCourses = jdbcTemplate.query(sb.toString(), new RowMapper(){
            @Override
            public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
            	MyCourseDTO myCourseDTO  = new MyCourseDTO();
            	myCourseDTO.setId(rs.getLong("id"));
            	myCourseDTO.setTitle(rs.getString("title")!=null?rs.getString("title"):"");
            	myCourseDTO.setSubTitle(rs.getString("sub_title")!=null?rs.getString("sub_title"):"");
            	myCourseDTO.setLessonNum(rs.getInt("lesson_num")!=0?rs.getInt("lesson_num"):0);
            	myCourseDTO.setCoverPicture(rs.getString("cover_picture")!=null?rs.getString("cover_picture"):"");
            	myCourseDTO.setIntroduction(rs.getString("introduction")!=null?rs.getString("introduction"):"");
            	myCourseDTO.setGoals(rs.getString("goals")!=null?rs.getString("goals"):"");
            	myCourseDTO.setTags(rs.getString("tags")!=null?rs.getString("tags"):"");
            	myCourseDTO.setLearnedNum(rs.getInt("learned_num")!=0?rs.getInt("learned_num"):0);
            	myCourseDTO.setUserId(rs.getLong("user_id"));
            	myCourseDTO.setLogin(rs.getString("login")!=null?rs.getString("login"):"");
            	myCourseDTO.setAvatar(rs.getString("avatar")!=null?rs.getString("avatar"):"");
                return myCourseDTO;
            }
		});
		Page<MyCourseDTO> result = new PageImpl<>(myCourses, pageable, myCourses.size());
    	return result;
	}
}
