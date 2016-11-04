package com.baseframework.domain.security.access;

import java.util.Set;

import javax.xml.bind.annotation.XmlTransient;

import org.springframework.security.core.GrantedAuthority;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Role implements java.io.Serializable, GrantedAuthority {

	private Integer roleId;
	private String roleName;
	private Set functions = null;

	public Role() {
	}

	public Role(Integer roleId) {
		this.roleId = roleId;
	}

	public Role(String roleName) {
		this.roleName = roleName;
	}

	public Integer getRoleId() {
		return this.roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return this.roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	@XmlTransient
	public Set<Function> getFunctions() {
		return this.functions;
	}

	@JsonIgnore
	public Set<GrantedAuthority> getGrantedAuthoritys() {
		return this.functions;
	}

	public void setFunctions(Set<Function> functions) {
		this.functions = functions;
	}

	@JsonIgnore
	public String getAuthority() {
		return getRoleName();
	}

	public String toString() {
		return this.roleId + "-" + this.roleName;
	}
}
