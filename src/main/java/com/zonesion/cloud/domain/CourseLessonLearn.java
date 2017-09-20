package com.zonesion.cloud.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A CourseLessonLearn.
 */
@Entity
@Table(name = "t_course_lesson_learn")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class CourseLessonLearn extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "course_id", nullable = false)
    private Long courseId;

    @NotNull
    @Column(name = "user_id", nullable = false)
    private Long userId;

    @NotNull
    @Column(name = "duration_id", nullable = false)
    private Long durationId;

    @NotNull
    @Size(max = 1)
    @Column(name = "is_complete", length = 1, nullable = false)
    private String isComplete;

    @ManyToOne(optional = false)
    private CourseLesson courseLesson;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCourseId() {
        return courseId;
    }

    public CourseLessonLearn courseId(Long courseId) {
        this.courseId = courseId;
        return this;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Long getUserId() {
        return userId;
    }

    public CourseLessonLearn userId(Long userId) {
        this.userId = userId;
        return this;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getDurationId() {
        return durationId;
    }

    public CourseLessonLearn durationId(Long durationId) {
        this.durationId = durationId;
        return this;
    }

    public void setDurationId(Long durationId) {
        this.durationId = durationId;
    }

    public String getIsComplete() {
        return isComplete;
    }

    public CourseLessonLearn isComplete(String isComplete) {
        this.isComplete = isComplete;
        return this;
    }

    public void setIsComplete(String isComplete) {
        this.isComplete = isComplete;
    }

    public CourseLesson getCourseLesson() {
        return courseLesson;
    }

    public CourseLessonLearn courseLesson(CourseLesson CourseLesson) {
        this.courseLesson = CourseLesson;
        return this;
    }

    public void setCourseLesson(CourseLesson CourseLesson) {
        this.courseLesson = CourseLesson;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CourseLessonLearn courseLessonLearn = (CourseLessonLearn) o;
        if (courseLessonLearn.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), courseLessonLearn.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CourseLessonLearn{" +
            "id=" + getId() +
            ", courseId='" + getCourseId() + "'" +
            ", userId='" + getUserId() + "'" +
            ", durationId='" + getDurationId() + "'" +
            ", isComplete='" + getIsComplete() + "'" +
            "}";
    }
}
