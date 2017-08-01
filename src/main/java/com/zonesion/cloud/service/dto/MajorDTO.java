package com.zonesion.cloud.service.dto;


import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Major entity.
 */
public class MajorDTO implements Serializable {

    private Long id;

    @NotNull
    @Size(max = 255)
    private String major;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MajorDTO majorDTO = (MajorDTO) o;
        if(majorDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), majorDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MajorDTO{" +
            "id=" + getId() +
            ", major='" + getMajor() + "'" +
            "}";
    }
}
