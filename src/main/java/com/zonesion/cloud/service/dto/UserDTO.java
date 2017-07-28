package com.zonesion.cloud.service.dto;

import com.zonesion.cloud.config.Constants;

import com.zonesion.cloud.domain.Authority;
import com.zonesion.cloud.domain.User;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.*;
import java.time.Instant;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * A DTO representing a user, with his authorities.
 */
public class UserDTO {

    private Long id;

    @NotBlank
    @Pattern(regexp = Constants.LOGIN_REGEX)
    @Size(min = 1, max = 50)
    private String login;

    @Size(max = 50)
    private String name;

    @Size(max = 256)
    private String avatar;
    
    @Email
    @Size(min = 5, max = 100)
    private String email;
    
    @Size(max = 11)
    private String mobile;
    
    @Size(max = 1)
    private String sex;
    
    @Size(max = 12)
    private String staffNo;
    
    @Size(max = 50)
    private String major;
    
    @Size(max = 50)
    private String school;

    private boolean activated = false;

    private String createdBy;

    private Instant createdDate;

    private String lastModifiedBy;

    private Instant lastModifiedDate;

    private Set<String> authorities;

    public UserDTO() {
        // Empty constructor needed for Jackson.
    }

	public UserDTO(User user) {
		this(user.getId(), user.getLogin(), user.getName(), user.getAvatar(), user.getEmail(), user.getMobile(),
				user.getSex(), user.getStaffNo(), user.getMajor(), user.getSchool(), user.getActivated(),
				user.getCreatedBy(), user.getCreatedDate(), user.getLastModifiedBy(), user.getLastModifiedDate(),
				user.getAuthorities().stream().map(Authority::getName).collect(Collectors.toSet()));
	}

	public UserDTO(Long id, String login, String name, String avatar, String email, String mobile, String sex,
			String staffNo, String major, String school, boolean activated, String createdBy, Instant createdDate,
			String lastModifiedBy, Instant lastModifiedDate, Set<String> authorities) {

        this.id = id;
        this.login = login;
        this.name = name;
        this.avatar = avatar;
        this.email = email;
        this.mobile = mobile;
        this.sex = sex;
        this.staffNo = staffNo;
        this.major = major;
        this.school = school;
        this.activated = activated;
        this.createdBy = createdBy;
        this.createdDate = createdDate;
        this.lastModifiedBy = lastModifiedBy;
        this.lastModifiedDate = lastModifiedDate;
        this.authorities = authorities;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public String getAvatar() {
		return avatar;
	}

	public String getMobile() {
		return mobile;
	}

	public String getSex() {
		return sex;
	}

	public String getStaffNo() {
		return staffNo;
	}

	public String getMajor() {
		return major;
	}

	public String getSchool() {
		return school;
	}

	public String getEmail() {
        return email;
    }

    public boolean isActivated() {
        return activated;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public Instant getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Instant lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public Set<String> getAuthorities() {
        return authorities;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
            "login='" + login + '\'' +
            ", name='" + name + '\'' +
            ", avatar='" + avatar + '\'' +
            ", email='" + email + '\'' +
            ", mobile='" + mobile + '\'' +
            ", sex='" + sex + '\'' +
            ", staffNo='" + staffNo + '\'' +
            ", major='" + major + '\'' +
            ", school='" + school + '\'' +
            ", activated=" + activated +
            ", createdBy=" + createdBy +
            ", createdDate=" + createdDate +
            ", lastModifiedBy='" + lastModifiedBy + '\'' +
            ", lastModifiedDate=" + lastModifiedDate +
            ", authorities=" + authorities +
            "}";
    }
}
