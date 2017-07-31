package com.zonesion.cloud.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A CourseLessonNoteLike.
 */
@Entity
@Table(name = "t_course_lesson_note_like")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class CourseLessonNoteLike implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "user_id", nullable = false)
    private Long userId;

    @NotNull
    @Column(name = "created_time", nullable = false)
    private Instant createdTime;

    @ManyToOne
    private CourseLessonNote courseLessonNote;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public CourseLessonNoteLike userId(Long userId) {
        this.userId = userId;
        return this;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Instant getCreatedTime() {
        return createdTime;
    }

    public CourseLessonNoteLike createdTime(Instant createdTime) {
        this.createdTime = createdTime;
        return this;
    }

    public void setCreatedTime(Instant createdTime) {
        this.createdTime = createdTime;
    }

    public CourseLessonNote getCourseLessonNote() {
        return courseLessonNote;
    }

    public CourseLessonNoteLike courseLessonNote(CourseLessonNote CourseLessonNote) {
        this.courseLessonNote = CourseLessonNote;
        return this;
    }

    public void setCourseLessonNote(CourseLessonNote CourseLessonNote) {
        this.courseLessonNote = CourseLessonNote;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CourseLessonNoteLike courseLessonNoteLike = (CourseLessonNoteLike) o;
        if (courseLessonNoteLike.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), courseLessonNoteLike.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CourseLessonNoteLike{" +
            "id=" + getId() +
            ", userId='" + getUserId() + "'" +
            ", createdTime='" + getCreatedTime() + "'" +
            "}";
    }
}
