package com.zonesion.cloud.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Course.
 */
@Entity
@Table(name = "t_course")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Course extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "user_id", nullable = false)
    private Long userId;

    @NotNull
    @Size(max = 1024)
    @Column(name = "title", length = 1024, nullable = false)
    private String title;

    @Size(max = 1024)
    @Column(name = "sub_title", length = 1024)
    private String subTitle;

    @NotNull
    @Size(max = 1)
    @Column(name = "status", length = 1, nullable = false)
    private String status;

    @NotNull
    @Size(max = 1)
    @Column(name = "course_type", length = 1, nullable = false)
    private String courseType;

    @NotNull
    @Column(name = "lesson_num", length = 10, nullable = false)
    private Integer lessonNum;

    @NotNull
    @Size(max = 10)
    @Column(name = "credit", length = 10, nullable = false)
    private String credit;

    @NotNull
    @Size(max = 255)
    @Column(name = "cover_picture", length = 255, nullable = false)
    private String coverPicture;

    @Column(name = "introduction")
    private String introduction;

    @Column(name = "goals")
    private String goals;

    @NotNull
    @Size(max = 1)
    @Column(name = "recommended", length = 1, nullable = false)
    private String recommended;

    @NotNull
    @Size(max = 1)
    @Column(name = "recommended_sort", length = 1, nullable = false)
    private String recommendedSort;

    @OneToMany
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<CourseFavorite> courseFavorites = new HashSet<>();

    @OneToMany
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Chapter> chapters = new HashSet<>();

    @OneToMany
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<CourseReview> courseReviews = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public Course userId(Long userId) {
        this.userId = userId;
        return this;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public Course title(String title) {
        this.title = title;
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public Course subTitle(String subTitle) {
        this.subTitle = subTitle;
        return this;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getStatus() {
        return status;
    }

    public Course status(String status) {
        this.status = status;
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCourseType() {
        return courseType;
    }

    public Course courseType(String courseType) {
        this.courseType = courseType;
        return this;
    }

    public void setCourseType(String courseType) {
        this.courseType = courseType;
    }

    public Integer getLessonNum() {
        return lessonNum;
    }

    public Course lessonNum(Integer lessonNum) {
        this.lessonNum = lessonNum;
        return this;
    }

    public void setLessonNum(Integer lessonNum) {
        this.lessonNum = lessonNum;
    }

    public String getCredit() {
        return credit;
    }

    public Course credit(String credit) {
        this.credit = credit;
        return this;
    }

    public void setCredit(String credit) {
        this.credit = credit;
    }

    public String getCoverPicture() {
        return coverPicture;
    }

    public Course coverPicture(String coverPicture) {
        this.coverPicture = coverPicture;
        return this;
    }

    public void setCoverPicture(String coverPicture) {
        this.coverPicture = coverPicture;
    }

    public String getIntroduction() {
        return introduction;
    }

    public Course introduction(String introduction) {
        this.introduction = introduction;
        return this;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getGoals() {
        return goals;
    }

    public Course goals(String goals) {
        this.goals = goals;
        return this;
    }

    public void setGoals(String goals) {
        this.goals = goals;
    }

    public String getRecommended() {
        return recommended;
    }

    public Course recommended(String recommended) {
        this.recommended = recommended;
        return this;
    }

    public void setRecommended(String recommended) {
        this.recommended = recommended;
    }

    public String getRecommendedSort() {
        return recommendedSort;
    }

    public Course recommendedSort(String recommendedSort) {
        this.recommendedSort = recommendedSort;
        return this;
    }

    public void setRecommendedSort(String recommendedSort) {
        this.recommendedSort = recommendedSort;
    }

    public Set<CourseFavorite> getCourseFavorites() {
        return courseFavorites;
    }

    public Course courseFavorites(Set<CourseFavorite> CourseFavorites) {
        this.courseFavorites = CourseFavorites;
        return this;
    }

    public Course addCourseFavorite(CourseFavorite CourseFavorite) {
        this.courseFavorites.add(CourseFavorite);
//        CourseFavorite.setCourseFavorite(this);
        return this;
    }

    public Course removeCourseFavorite(CourseFavorite CourseFavorite) {
        this.courseFavorites.remove(CourseFavorite);
//        CourseFavorite.setCourseFavorite(null);
        return this;
    }

    public void setCourseFavorites(Set<CourseFavorite> CourseFavorites) {
        this.courseFavorites = CourseFavorites;
    }

    public Set<Chapter> getChapters() {
        return chapters;
    }

    public Course chapters(Set<Chapter> Chapters) {
        this.chapters = Chapters;
        return this;
    }

    public Course addChapter(Chapter Chapter) {
        this.chapters.add(Chapter);
//        Chapter.setChapter(this);
        return this;
    }

    public Course removeChapter(Chapter Chapter) {
        this.chapters.remove(Chapter);
//        Chapter.setChapter(null);
        return this;
    }

    public void setChapters(Set<Chapter> Chapters) {
        this.chapters = Chapters;
    }

    public Set<CourseReview> getCourseReviews() {
        return courseReviews;
    }

    public Course courseReviews(Set<CourseReview> CourseReviews) {
        this.courseReviews = CourseReviews;
        return this;
    }

    public Course addCourseReview(CourseReview CourseReview) {
        this.courseReviews.add(CourseReview);
//        CourseReview.setCourseReview(this);
        return this;
    }

    public Course removeCourseReview(CourseReview CourseReview) {
        this.courseReviews.remove(CourseReview);
//        CourseReview.setCourseReview(null);
        return this;
    }

    public void setCourseReviews(Set<CourseReview> CourseReviews) {
        this.courseReviews = CourseReviews;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Course course = (Course) o;
        if (course.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), course.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Course{" +
            "id=" + getId() +
            ", userId='" + getUserId() + "'" +
            ", title='" + getTitle() + "'" +
            ", subTitle='" + getSubTitle() + "'" +
            ", status='" + getStatus() + "'" +
            ", courseType='" + getCourseType() + "'" +
            ", lessonNum='" + getLessonNum() + "'" +
            ", credit='" + getCredit() + "'" +
            ", coverPicture='" + getCoverPicture() + "'" +
            ", introduction='" + getIntroduction() + "'" +
            ", goals='" + getGoals() + "'" +
            ", recommended='" + getRecommended() + "'" +
            ", recommendedSort='" + getRecommendedSort() + "'" +
            "}";
    }
}
