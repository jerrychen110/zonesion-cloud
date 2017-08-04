package com.zonesion.cloud.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Chapter.
 */
@Entity
@Table(name = "t_chapter")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Chapter extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull
    @Column(name = "user_id", nullable = false)
    private Long userId;

    @NotNull
    @Size(max = 1)
    @Column(name = "chapter_type", length = 1, nullable = false)
    private String chapterType;

    @NotNull
    @Column(name = "number", length = 1, nullable = false)
    private Integer number;

    @NotNull
    @Column(name = "seq", length = 1, nullable = false)
    private Integer seq;

    @NotNull
    @Size(max = 255)
    @Column(name = "title", length = 255, nullable = false)
    private String title;

    @OneToMany
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<CourseLesson> courseLessons = new HashSet<>();

    @ManyToOne
    private Course courseId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
	public Course getCourseId() {
		return courseId;
	}

	public void setCourseId(Course courseId) {
		this.courseId = courseId;
	}

    public Long getUserId() {
        return userId;
    }

    public Chapter userId(Long userId) {
        this.userId = userId;
        return this;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getChapterType() {
        return chapterType;
    }

    public Chapter chapterType(String chapterType) {
        this.chapterType = chapterType;
        return this;
    }

    public void setChapterType(String chapterType) {
        this.chapterType = chapterType;
    }

    public Integer getNumber() {
        return number;
    }

    public Chapter number(Integer number) {
        this.number = number;
        return this;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getSeq() {
        return seq;
    }

    public Chapter seq(Integer seq) {
        this.seq = seq;
        return this;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public String getTitle() {
        return title;
    }

    public Chapter title(String title) {
        this.title = title;
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<CourseLesson> getCourseLessons() {
        return courseLessons;
    }

    public Chapter courseLessons(Set<CourseLesson> CourseLessons) {
        this.courseLessons = CourseLessons;
        return this;
    }

    public Chapter addCourseLesson(CourseLesson CourseLesson) {
        this.courseLessons.add(CourseLesson);
        CourseLesson.setChapter(this);
        return this;
    }

    public Chapter removeCourseLesson(CourseLesson CourseLesson) {
        this.courseLessons.remove(CourseLesson);
        CourseLesson.setChapter(null);
        return this;
    }

    public void setCourseLessons(Set<CourseLesson> CourseLessons) {
        this.courseLessons = CourseLessons;
    }



    @Override
    public String toString() {
        return "Chapter{" +
            "id=" + getId() +
            ", userId='" + getUserId() + "'" +
            ", chapterType='" + getChapterType() + "'" +
            ", number='" + getNumber() + "'" +
            ", seq='" + getSeq() + "'" +
            ", title='" + getTitle() + "'" +
            "}";
    }


}
