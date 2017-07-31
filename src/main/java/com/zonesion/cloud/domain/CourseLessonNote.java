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
 * A CourseLessonNote.
 */
@Entity
@Table(name = "t_course_lesson_note")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class CourseLessonNote extends AbstractAuditingEntity implements Serializable {

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
    @Column(name = "content", nullable = false)
    private String content;

    @NotNull
    @Column(name = "length", nullable = false)
    private Integer length;

    @NotNull
    @Column(name = "like_num", nullable = false)
    private Integer likeNum;

    @NotNull
    @Size(max = 1)
    @Column(name = "is_private", length = 1, nullable = false)
    private String isPrivate;

    @ManyToOne
    private CourseLesson courseLesson;

    @OneToMany(mappedBy = "courseLessonNote", cascade=CascadeType.ALL, fetch=FetchType.EAGER)
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<CourseLessonNoteLike> courseLessonNoteLikes = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCourseId() {
        return courseId;
    }

    public CourseLessonNote courseId(Long courseId) {
        this.courseId = courseId;
        return this;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Long getUserId() {
        return userId;
    }

    public CourseLessonNote userId(Long userId) {
        this.userId = userId;
        return this;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public CourseLessonNote content(String content) {
        this.content = content;
        return this;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getLength() {
        return length;
    }

    public CourseLessonNote length(Integer length) {
        this.length = length;
        return this;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public Integer getLikeNum() {
        return likeNum;
    }

    public CourseLessonNote likeNum(Integer likeNum) {
        this.likeNum = likeNum;
        return this;
    }

    public void setLikeNum(Integer likeNum) {
        this.likeNum = likeNum;
    }

    public String getIsPrivate() {
        return isPrivate;
    }

    public CourseLessonNote isPrivate(String isPrivate) {
        this.isPrivate = isPrivate;
        return this;
    }

    public void setIsPrivate(String isPrivate) {
        this.isPrivate = isPrivate;
    }

    public CourseLesson getCourseLesson() {
        return courseLesson;
    }

    public CourseLessonNote courseLesson(CourseLesson CourseLesson) {
        this.courseLesson = CourseLesson;
        return this;
    }

    public void setCourseLesson(CourseLesson CourseLesson) {
        this.courseLesson = CourseLesson;
    }

    public Set<CourseLessonNoteLike> getCourseLessonNoteLikes() {
        return courseLessonNoteLikes;
    }

    public CourseLessonNote courseLessonNoteLikes(Set<CourseLessonNoteLike> CourseLessonNoteLikes) {
        this.courseLessonNoteLikes = CourseLessonNoteLikes;
        return this;
    }

    public CourseLessonNote addCourseLessonNoteLike(CourseLessonNoteLike CourseLessonNoteLike) {
        this.courseLessonNoteLikes.add(CourseLessonNoteLike);
        CourseLessonNoteLike.setCourseLessonNote(this);
        return this;
    }

    public CourseLessonNote removeCourseLessonNoteLike(CourseLessonNoteLike CourseLessonNoteLike) {
        this.courseLessonNoteLikes.remove(CourseLessonNoteLike);
        CourseLessonNoteLike.setCourseLessonNote(null);
        return this;
    }

    public void setCourseLessonNoteLikes(Set<CourseLessonNoteLike> CourseLessonNoteLikes) {
        this.courseLessonNoteLikes = CourseLessonNoteLikes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CourseLessonNote courseLessonNote = (CourseLessonNote) o;
        if (courseLessonNote.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), courseLessonNote.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CourseLessonNote{" +
            "id=" + getId() +
            ", courseId='" + getCourseId() + "'" +
            ", userId='" + getUserId() + "'" +
            ", content='" + getContent() + "'" +
            ", length='" + getLength() + "'" +
            ", likeNum='" + getLikeNum() + "'" +
            ", isPrivate='" + getIsPrivate() + "'" +
            "}";
    }
}
