package com.livingoncodes.spring.web.domain;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.livingoncodes.spring.web.dao.FormValidationGroup;
import com.livingoncodes.spring.web.dao.PersistenceValidationGroup;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@Entity
@Table(name="user_profile")
public class UserProfile {
	
	@Id @GeneratedValue
	private Long id;

	@DateTimeFormat(iso=ISO.DATE)
	@Column(name="birthdate")
	private Date birthDate;
	
	private int sex;
	
	@Size(max=30, groups={PersistenceValidationGroup.class, FormValidationGroup.class})
	@Column(name="mobileno")
	private String mobileNo;
	
	@Size(max=12, groups={PersistenceValidationGroup.class, FormValidationGroup.class})
	@Column(name="bloodgroup")
	private String bloodGroup;
	
	@Size(max=100, groups={PersistenceValidationGroup.class, FormValidationGroup.class})
	private String address;
	
	@Size(max=150, groups={PersistenceValidationGroup.class, FormValidationGroup.class})
	private String emergency;
	
	@Size(max=100, groups={PersistenceValidationGroup.class, FormValidationGroup.class})
	@Column(name="facebookprofile")
	private String facebookProfile;
	
	private String temppass;
	
	private String secret;
	
	@OneToOne(mappedBy="userProfile", cascade=CascadeType.ALL)
	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	
	
	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}

	public String getTemppass() {
		return temppass;
	}

	public void setTemppass(String temppass) {
		this.temppass = temppass;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getBloodGroup() {
		return bloodGroup;
	}

	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmergency() {
		return emergency;
	}

	public void setEmergency(String emergency) {
		this.emergency = emergency;
	}

	public String getFacebookProfile() {
		return facebookProfile;
	}

	public void setFacebookProfile(String facebookProfile) {
		this.facebookProfile = facebookProfile;
	}

	public UserProfile(Long id, Date birthDate, int sex, String mobileNo,
			String bloodGroup, String address, String emergency,
			String facebookProfile) {
		this.id = id;
		this.birthDate = birthDate;
		this.sex = sex;
		this.mobileNo = mobileNo;
		this.bloodGroup = bloodGroup;
		this.address = address;
		this.emergency = emergency;
		this.facebookProfile = facebookProfile;
	}

	public UserProfile() {
	}

	@Override
	public String toString() {
		return "UserProfile{" +
				"birthDate=" + birthDate +
				", sex=" + sex +
				", mobileNo='" + mobileNo + '\'' +
				", bloodGroup='" + bloodGroup + '\'' +
				", address='" + address + '\'' +
				", emergency='" + emergency + '\'' +
				", facebookProfile='" + facebookProfile + '\'' +
				", temppass='" + temppass + '\'' +
				", secret='" + secret + '\'' +
				", user=" + user +
				'}';
	}
}
