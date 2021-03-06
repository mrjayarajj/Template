package com.baseframework.domain.security.access;


public class Authenticate implements java.io.Serializable {

	private int roleId;
	private int functionId;
	private boolean grant;

	public boolean isGrant() {
		return grant;
	}

	public void setGrant(boolean grant) {
		this.grant = grant;
	}

	public int getRoleId() {
		return this.roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public int getFunctionId() {
		return this.functionId;
	}

	public void setFunctionId(int functionId) {
		this.functionId = functionId;
	}	

	public boolean isGrant(int functionId, int roleId) {
		if (this.functionId == functionId && this.roleId == roleId) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof Authenticate))
			return false;
		Authenticate castOther = (Authenticate) other;

		return (this.getRoleId() == castOther.getRoleId()) && (this.getFunctionId() == castOther.getFunctionId());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getRoleId();
		result = 37 * result + this.getFunctionId();
		return result;
	}	
	
	public String toString() {		
		return "["+getRoleId()+","+getFunctionId()+"]="+isGrant();
	}

}
