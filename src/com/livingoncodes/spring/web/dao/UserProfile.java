package com.livingoncodes.spring.web.dao;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name="user_profile")
public class UserProfile {
	
	@Id
	@GeneratedValue
	private int id; 

	@Column(name="birthdate")
	private Date birthDate;
	
	private int sex;
	
	@NotBlank(groups={PersistenceValidationGroup.class, FormValidationGroup.class})
	@Size(min=5, max=20, groups={PersistenceValidationGroup.class, FormValidationGroup.class})
	@Pattern(regexp="^\\w{4,}$", groups={PersistenceValidationGroup.class, FormValidationGroup.class})
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
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

	public UserProfile(int id, Date birthDate, int sex, String mobileNo,
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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result
				+ ((birthDate == null) ? 0 : birthDate.hashCode());
		result = prime * result
				+ ((bloodGroup == null) ? 0 : bloodGroup.hashCode());
		result = prime * result
				+ ((emergency == null) ? 0 : emergency.hashCode());
		result = prime * result
				+ ((facebookProfile == null) ? 0 : facebookProfile.hashCode());
		result = prime * result
				+ ((mobileNo == null) ? 0 : mobileNo.hashCode());
		result = prime * result + sex;
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
		UserProfile other = (UserProfile) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (birthDate == null) {
			if (other.birthDate != null)
				return false;
		} else if (!birthDate.equals(other.birthDate))
			return false;
		if (bloodGroup == null) {
			if (other.bloodGroup != null)
				return false;
		} else if (!bloodGroup.equals(other.bloodGroup))
			return false;
		if (emergency == null) {
			if (other.emergency != null)
				return false;
		} else if (!emergency.equals(other.emergency))
			return false;
		if (facebookProfile == null) {
			if (other.facebookProfile != null)
				return false;
		} else if (!facebookProfile.equals(other.facebookProfile))
			return false;
		if (mobileNo == null) {
			if (other.mobileNo != null)
				return false;
		} else if (!mobileNo.equals(other.mobileNo))
			return false;
		if (sex != other.sex)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "UserProfile [id=" + id + ", birthDate=" + birthDate + ", sex="
				+ sex + ", mobileNo=" + mobileNo + ", bloodGroup=" + bloodGroup
				+ ", address=" + address + ", emergency=" + emergency
				+ ", facebookProfile=" + facebookProfile + "]";
	}

	
	
	
}
