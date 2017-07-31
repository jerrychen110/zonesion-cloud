package com.zonesion.cloud.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A File.
 */
@Entity
@Table(name = "t_file")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class File extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "user_id", nullable = false)
    private Long userId;

    @NotNull
    @Size(max = 255)
    @Column(name = "uri", length = 255, nullable = false)
    private String uri;

    @NotNull
    @Size(max = 1024)
    @Column(name = "name", length = 1024, nullable = false)
    private String name;

    @NotNull
    @Size(max = 255)
    @Column(name = "mime", length = 255, nullable = false)
    private String mime;

    @NotNull
    @Column(name = "size", nullable = false)
    private Integer size;

    @NotNull
    @Size(max = 1)
    @Column(name = "status", length = 1, nullable = false)
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

    public File userId(Long userId) {
        this.userId = userId;
        return this;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUri() {
        return uri;
    }

    public File uri(String uri) {
        this.uri = uri;
        return this;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getName() {
        return name;
    }

    public File name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMime() {
        return mime;
    }

    public File mime(String mime) {
        this.mime = mime;
        return this;
    }

    public void setMime(String mime) {
        this.mime = mime;
    }

    public Integer getSize() {
        return size;
    }

    public File size(Integer size) {
        this.size = size;
        return this;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public String getStatus() {
        return status;
    }

    public File status(String status) {
        this.status = status;
        return this;
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
        File file = (File) o;
        if (file.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), file.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "File{" +
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
