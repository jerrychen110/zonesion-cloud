package com.zonesion.cloud.service.dto;


import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the File entity.
 */
public class FileDTO implements Serializable {

    private Long id;

    @NotNull
    private Long userId;

    @NotNull
    @Size(max = 255)
    private String uri;

    @NotNull
    @Size(max = 1024)
    private String name;

    @NotNull
    @Size(max = 255)
    private String mime;

    @NotNull
    private Integer size;

    @NotNull
    @Size(max = 1)
    private String status;

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

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMime() {
        return mime;
    }

    public void setMime(String mime) {
        this.mime = mime;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        FileDTO fileDTO = (FileDTO) o;
        if(fileDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), fileDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "FileDTO{" +
            "id=" + getId() +
            ", userId='" + getUserId() + "'" +
            ", uri='" + getUri() + "'" +
            ", name='" + getName() + "'" +
            ", mime='" + getMime() + "'" +
            ", size='" + getSize() + "'" +
            ", status='" + getStatus() + "'" +
            "}";
    }
}
