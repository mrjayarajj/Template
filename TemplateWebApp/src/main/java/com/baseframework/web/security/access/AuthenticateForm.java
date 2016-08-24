package com.baseframework.web.security.access;

import java.util.List;
import java.util.Set;

import com.baseframework.domain.security.access.Authenticate;
import com.baseframework.domain.security.access.Function;
import com.baseframework.domain.security.access.Module;
import com.baseframework.domain.security.access.Role;

public class AuthenticateForm {

	private List<Module> modules = null;

	private List<Function> functions = null;

	private List<Authenticate> authenticatedList = null;

	private List<Authenticate> grantList = null;
	
	private Module selectedModule = null;

	public List<Authenticate> getGrantList() {
		return grantList;
	}

	public void setGrantList(List<Authenticate> grantList) {
		this.grantList = grantList;
	}

	public void setAuthenticatedList(List<Authenticate> authenticatedList) {
		this.authenticatedList = authenticatedList;
	}

	public List<Function> getFunctions() {
		return functions;
	}

	public void setFunctions(List<Function> functions) {
		this.functions = functions;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	private List<Role> roles = null;

	public List<Module> getModules() {
		return modules;
	}

	public void setModules(List<Module> modules) {
		this.modules = modules;
	}
	
	public boolean check(int functionId, int roleId) {

		for (Authenticate a : this.authenticatedList) {
			if (a.isGrant(functionId, roleId)) {
				return true;
			}
		}

		return false;
	}

	public Module getSelectedModule() {
		return selectedModule;
	}

	public void setSelectedModule(Module selectedModule) {
		this.selectedModule = selectedModule;
	}

	public List<Authenticate> getAuthenticatedList() {
		return authenticatedList;
	}	
}
