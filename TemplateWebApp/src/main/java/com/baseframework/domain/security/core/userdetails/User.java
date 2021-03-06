package com.baseframework.domain.security.core.userdetails;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/*
import cache.ehcache.CacheDetails;
*/
import com.baseframework.domain.security.access.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;

@XmlRootElement
public class User implements java.io.Serializable, UserDetails, /* CacheDetails, */ Cloneable {

	private Integer userId;
	private Role role;
	private String userName;
	/**
	 * do not serialize password during thread dump or have it as char[] because
	 * char[] are stored in
	 */
	private transient String userPassword;

	private char gender;
	private boolean status;
	private boolean selected;

	private boolean locked;

	private Date expireDate;

	public User() {
	}

	public User(Integer userId, String userName, String userPassword) {
		this.userId = userId;
		this.userName = userName;
		this.userPassword = userPassword;
		this.status = true;
	}

	public User(Integer userId, String userName, String userPassword, Role role) {
		this.userId = userId;
		this.userName = userName;
		this.userPassword = userPassword;
		this.status = true;
		this.role = role;
	}

	public Integer getUserId() {
		return this.userId;
	}

	@JsonIgnore
	public Serializable getCacheKey() {
		return "" + this.userId;
	}

	@JsonIgnore
	public Serializable getCacheValue() {
		return this;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Role getRole() {
		return this.role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@JsonIgnore
	public String getUserPassword() {
		return this.userPassword;
	}

	@JsonIgnore
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public char getGender() {
		return this.gender;
	}

	public void setGender(char gender) {
		this.gender = gender;
	}

	public boolean isStatus() {
		return this.status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	@JsonIgnore
	public Collection getAuthorities() {
		Set<GrantedAuthority> GrantedAuthoritys = getRole().getGrantedAuthoritys();
		if (GrantedAuthoritys != null) {
			GrantedAuthoritys.add((GrantedAuthority) getRole());
			return GrantedAuthoritys;
		}
		return null;
	}

	@JsonIgnore
	public String getPassword() {
		return getUserPassword();
	}

	@JsonIgnore
	public String getUsername() {
		return getUserName();
	}

	public boolean isAccountNonExpired() {
		if (expireDate == null)
			return true;
		else
			return new Date().compareTo(expireDate) <= 0;
	}

	public boolean isAccountNonLocked() {
		return !isLocked();
	}

	public boolean isCredentialsNonExpired() {
		if (expireDate == null)
			return true;
		else
			return new Date().compareTo(expireDate) <= 0;
	}

	public boolean isEnabled() {
		return isStatus();
	}

	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}

	public Date getExpireDate() {
		return expireDate;
	}

	public String getExpireDateAsString() {
		if (expireDate != null) {
			return new SimpleDateFormat("MM/dd/yyyy").format(expireDate);
		} else {
			return "";
		}
	}

	public void setExpireDateAsString(String expireDate) throws ParseException {
		if (expireDate != null && !expireDate.isEmpty()) {
			this.expireDate = new SimpleDateFormat("MM/dd/yyyy").parse(expireDate);
		} else {
			this.expireDate = null;
		}
	}

	public boolean isLocked() {
		return locked;
	}

	public void setLocked(boolean locked) {
		this.locked = locked;
	}

	public static List<Integer> getSelectedUserId(List<User> userList) {
		List<Integer> selectedUserList = new ArrayList<Integer>();
		for (User user : userList) {
			if (user.isSelected()) {
				selectedUserList.add(user.getUserId());
			}
		}
		return selectedUserList;
	}

	public boolean hasUserProperty(String propertyName, Object desPropertyValue) {

		try {
			Object srcpropertyValue = BeanUtils.getSimpleProperty(this, propertyName);
			return srcpropertyValue.equals(desPropertyValue);
		} catch (IllegalAccessException e) {
			new RuntimeException(e);
		} catch (InvocationTargetException e) {
			new RuntimeException(e);
		} catch (NoSuchMethodException e) {
			new RuntimeException(e);
		}

		throw new RuntimeException(new NoSuchFieldException());

	}

	public String toString() {
		return this.userId + "-" + this.userName;
	}

	public boolean equals(Object obj) {
		return new Integer(this.userId).equals(new Integer(((User) obj).getUserId()));
	}

	@JsonIgnore
	public User clone() {
		try {
			return (User) super.clone();
		} catch (CloneNotSupportedException e) {
			throw new RuntimeException(e);
		}
	}

	private String token;

	public void setToken(String myAcInfoToken) {
		this.token = token;
	}
}
