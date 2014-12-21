package com.livingoncodes.spring.web.dao;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import com.livingoncodes.spring.web.validation.ValidEmail;

@Entity
@Table(name="user")
public class User {
	
	@Id
	@GeneratedValue
	private int id; 

	@NotBlank(groups={PersistenceValidationGroup.class, FormValidationGroup.class})
	@Size(min=3, max=60, groups={PersistenceValidationGroup.class, FormValidationGroup.class})
	@Pattern(regexp="^\\w{3,}$", groups={PersistenceValidationGroup.class, FormValidationGroup.class})
	@Column(name="username")
	private String username;
	
	@NotBlank(groups={PersistenceValidationGroup.class, FormValidationGroup.class})
	@Pattern(regexp="^\\S+$", groups={PersistenceValidationGroup.class, FormValidationGroup.class})
	@Size(min=5, max=15, groups={PersistenceValidationGroup.class, FormValidationGroup.class})
	private String password;
	
	@ValidEmail(groups={PersistenceValidationGroup.class, FormValidationGroup.class})
	@Column(name="email", unique=true)
	private String email;
	
	private boolean enabled = false;
	private String authority;
	
	@Size(max=60, groups={PersistenceValidationGroup.class, FormValidationGroup.class})
	private String fullname;

	@OneToOne(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name="user_profile_id", referencedColumnName="id")
	private UserProfile userProfile;
	
	
	public UserProfile getUserProfile() {
		return userProfile;
	}

	public void setUserProfile(UserProfile userProfile) {
		this.userProfile = userProfile;
	}

	public User() {
	}

	public User(String username, String fullname, String password, String email,
			boolean enabled, String authority) {
		this.username = username;
		this.fullname = fullname;
		this.password = password;
		this.email = email;
		this.enabled = enabled;
		this.authority = authority;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}
	
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((authority == null) ? 0 : authority.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + (enabled ? 1231 : 1237);
		result = prime * result
				+ ((fullname == null) ? 0 : fullname.hashCode());
		result = prime * result
				+ ((password == null) ? 0 : password.hashCode());
		result = prime * result
				+ ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (authority == null) {
			if (other.authority != null)
				return false;
		} else if (!authority.equals(other.authority))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (enabled != other.enabled)
			return false;
		if (fullname == null) {
			if (other.fullname != null)
				return false;
		} else if (!fullname.equals(other.fullname))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password="
				+ password + ", email=" + email + ", enabled=" + enabled
				+ ", authority=" + authority + ", fullname=" + fullname
				+ ", userProfile=" + userProfile + "]";
	}



}
