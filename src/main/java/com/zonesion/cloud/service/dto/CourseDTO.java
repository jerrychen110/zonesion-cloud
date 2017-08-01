package com.zonesion.cloud.service.dto;


import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the Course entity.
 */
public class CourseDTO implements Serializable {

    private Long id;

    @NotNull
    private Long userId;

    @NotNull
    @Size(max = 1024)
    private String title;

    @Size(max = 1024)
    private String subTitle;

    @NotNull
    @Size(max = 1)
    private String status;

    @NotNull
    @Size(max = 1)
    private String courseType;

    @NotNull
    @Size(max = 10)
    private Integer lessonNum;

    @NotNull
    @Size(max = 10)
    private String credit;

    @NotNull
    @Size(max = 255)
    private String coverPicture;

    private String introduction;

    private String goals;

    @NotNull
    @Size(max = 1)
    private String recommended;

    @NotNull
    @Size(max = 1)
    private String recommendedSort;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCourseType() {
        return courseType;
    }

    public void setCourseType(String courseType) {
        this.courseType = courseType;
    }

    public Integer getLessonNum() {
        return lessonNum;
    }

    public void setLessonNum(Integer lessonNum) {
        this.lessonNum = lessonNum;
    }

    public String getCredit() {
        return credit;
    }

    public void setCredit(String credit) {
        this.credit = credit;
    }

    public String getCoverPicture() {
        return coverPicture;
    }

    public void setCoverPicture(String coverPicture) {
        this.coverPicture = coverPicture;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getGoals() {
        return goals;
    }

    public void setGoals(String goals) {
        this.goals = goals;
    }

    public String getRecommended() {
        return recommended;
    }

    public void setRecommended(String recommended) {
        this.recommended = recommended;
    }

    public String getRecommendedSort() {
        return recommendedSort;
    }

    public void setRecommendedSort(String recommendedSort) {
        this.recommendedSort = recommendedSort;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CourseDTO courseDTO = (CourseDTO) o;
        if(courseDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), courseDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CourseDTO{" +
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
