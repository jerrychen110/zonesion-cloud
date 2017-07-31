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
 * A CourseLesson.
 */
@Entity
@Table(name = "t_course_lesson")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class CourseLesson extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "user_id", nullable = false)
    private Long userId;

    @NotNull
    @Column(name = "number", nullable = false)
    private Integer number;

    @NotNull
    @Column(name = "seq", nullable = false)
    private Integer seq;

    @NotNull
    @Size(max = 255)
    @Column(name = "title", length = 255, nullable = false)
    private String title;

    @Column(name = "summary")
    private String summary;

    @NotNull
    @Size(max = 1)
    @Column(name = "course_lesson_type", length = 1, nullable = false)
    private String courseLessonType;

    @Column(name = "content")
    private String content;

    @NotNull
    @Column(name = "credit", nullable = false)
    private Integer credit;

    @NotNull
    @Column(name = "media_id", nullable = false)
    private Integer mediaId;

    @Size(max = 1)
    @Column(name = "media_source", length = 1)
    private String mediaSource;

    @Size(max = 255)
    @Column(name = "media_name", length = 255)
    private String mediaName;

    @Column(name = "media_uri")
    private String mediaUri;

    @NotNull
    @Column(name = "learned_num", nullable = false)
    private Integer learnedNum;

    @NotNull
    @Column(name = "viewed_num", nullable = false)
    private Integer viewedNum;

    @OneToMany
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<CourseLessonAttachment> courseLessonAttachments = new HashSet<>();

    @OneToMany
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<CourseLessonLearn> courseLessonLearns = new HashSet<>();

    @OneToMany
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<CourseLessonNote> courseLessonNotes = new HashSet<>();

    @ManyToOne
    private Chapter chapter;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public CourseLesson userId(Long userId) {
        this.userId = userId;
        return this;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getNumber() {
        return number;
    }

    public CourseLesson number(Integer number) {
        this.number = number;
        return this;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getSeq() {
        return seq;
    }

    public CourseLesson seq(Integer seq) {
        this.seq = seq;
        return this;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public String getTitle() {
        return title;
    }

    public CourseLesson title(String title) {
        this.title = title;
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public CourseLesson summary(String summary) {
        this.summary = summary;
        return this;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getCourseLessonType() {
        return courseLessonType;
    }

    public CourseLesson courseLessonType(String courseLessonType) {
        this.courseLessonType = courseLessonType;
        return this;
    }

    public void setCourseLessonType(String courseLessonType) {
        this.courseLessonType = courseLessonType;
    }

    public String getContent() {
        return content;
    }

    public CourseLesson content(String content) {
        this.content = content;
        return this;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getCredit() {
        return credit;
    }

    public CourseLesson credit(Integer credit) {
        this.credit = credit;
        return this;
    }

    public void setCredit(Integer credit) {
        this.credit = credit;
    }

    public Integer getMediaId() {
        return mediaId;
    }

    public CourseLesson mediaId(Integer mediaId) {
        this.mediaId = mediaId;
        return this;
    }

    public void setMediaId(Integer mediaId) {
        this.mediaId = mediaId;
    }

    public String getMediaSource() {
        return mediaSource;
    }

    public CourseLesson mediaSource(String mediaSource) {
        this.mediaSource = mediaSource;
        return this;
    }

    public void setMediaSource(String mediaSource) {
        this.mediaSource = mediaSource;
    }

    public String getMediaName() {
        return mediaName;
    }

    public CourseLesson mediaName(String mediaName) {
        this.mediaName = mediaName;
        return this;
    }

    public void setMediaName(String mediaName) {
        this.mediaName = mediaName;
    }

    public String getMediaUri() {
        return mediaUri;
    }

    public CourseLesson mediaUri(String mediaUri) {
        this.mediaUri = mediaUri;
        return this;
    }

    public void setMediaUri(String mediaUri) {
        this.mediaUri = mediaUri;
    }

    public Integer getLearnedNum() {
        return learnedNum;
    }

    public CourseLesson learnedNum(Integer learnedNum) {
        this.learnedNum = learnedNum;
        return this;
    }

    public void setLearnedNum(Integer learnedNum) {
        this.learnedNum = learnedNum;
    }

    public Integer getViewedNum() {
        return viewedNum;
    }

    public CourseLesson viewedNum(Integer viewedNum) {
        this.viewedNum = viewedNum;
        return this;
    }

    public void setViewedNum(Integer viewedNum) {
        this.viewedNum = viewedNum;
    }

    public Set<CourseLessonAttachment> getCourseLessonAttachments() {
        return courseLessonAttachments;
    }

    public CourseLesson courseLessonAttachments(Set<CourseLessonAttachment> CourseLessonAttachments) {
        this.courseLessonAttachments = CourseLessonAttachments;
        return this;
    }

    public CourseLesson addCourseLessonAttachment(CourseLessonAttachment CourseLessonAttachment) {
        this.courseLessonAttachments.add(CourseLessonAttachment);
        CourseLessonAttachment.setCourseLesson(this);
        return this;
    }

    public CourseLesson removeCourseLessonAttachment(CourseLessonAttachment CourseLessonAttachment) {
        this.courseLessonAttachments.remove(CourseLessonAttachment);
        CourseLessonAttachment.setCourseLesson(null);
        return this;
    }

    public void setCourseLessonAttachments(Set<CourseLessonAttachment> CourseLessonAttachments) {
        this.courseLessonAttachments = CourseLessonAttachments;
    }

    public Set<CourseLessonLearn> getCourseLessonLearns() {
        return courseLessonLearns;
    }

    public CourseLesson courseLessonLearns(Set<CourseLessonLearn> CourseLessonLearns) {
        this.courseLessonLearns = CourseLessonLearns;
        return this;
    }

    public CourseLesson addCourseLessonLearn(CourseLessonLearn CourseLessonLearn) {
        this.courseLessonLearns.add(CourseLessonLearn);
        CourseLessonLearn.setCourseLesson(this);
        return this;
    }

    public CourseLesson removeCourseLessonLearn(CourseLessonLearn CourseLessonLearn) {
        this.courseLessonLearns.remove(CourseLessonLearn);
        CourseLessonLearn.setCourseLesson(null);
        return this;
    }

    public void setCourseLessonLearns(Set<CourseLessonLearn> CourseLessonLearns) {
        this.courseLessonLearns = CourseLessonLearns;
    }

    public Set<CourseLessonNote> getCourseLessonNotes() {
        return courseLessonNotes;
    }

    public CourseLesson courseLessonNotes(Set<CourseLessonNote> CourseLessonNotes) {
        this.courseLessonNotes = CourseLessonNotes;
        return this;
    }

    public CourseLesson addCourseLessonNote(CourseLessonNote CourseLessonNote) {
        this.courseLessonNotes.add(CourseLessonNote);
        CourseLessonNote.setCourseLesson(this);
        return this;
    }

    public CourseLesson removeCourseLessonNote(CourseLessonNote CourseLessonNote) {
        this.courseLessonNotes.remove(CourseLessonNote);
        CourseLessonNote.setCourseLesson(null);
        return this;
    }

    public void setCourseLessonNotes(Set<CourseLessonNote> CourseLessonNotes) {
        this.courseLessonNotes = CourseLessonNotes;
    }

    public Chapter getChapter() {
        return chapter;
    }

    public CourseLesson chapter(Chapter Chapter) {
        this.chapter = Chapter;
        return this;
    }

    public void setChapter(Chapter Chapter) {
        this.chapter = Chapter;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CourseLesson courseLesson = (CourseLesson) o;
        if (courseLesson.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), courseLesson.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CourseLesson{" +
            "id=" + getId() +
            ", userId='" + getUserId() + "'" +
            ", number='" + getNumber() + "'" +
            ", seq='" + getSeq() + "'" +
            ", title='" + getTitle() + "'" +
            ", summary='" + getSummary() + "'" +
            ", courseLessonType='" + getCourseLessonType() + "'" +
            ", content='" + getContent() + "'" +
            ", credit='" + getCredit() + "'" +
            ", mediaId='" + getMediaId() + "'" +
            ", mediaSource='" + getMediaSource() + "'" +
            ", mediaName='" + getMediaName() + "'" +
            ", mediaUri='" + getMediaUri() + "'" +
            ", learnedNum='" + getLearnedNum() + "'" +
            ", viewedNum='" + getViewedNum() + "'" +
            "}";
    }
}
