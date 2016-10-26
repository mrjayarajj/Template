package com.baseframework.web.security.access;

import java.util.List;

import com.baseframework.biz.security.access.AuthenticateService;
import com.baseframework.biz.security.access.FunctionService;
import com.baseframework.biz.security.access.ModuleService;
import com.baseframework.biz.security.access.RoleService;
import com.baseframework.domain.security.access.Authenticate;
import com.baseframework.domain.security.access.Function;
import com.baseframework.domain.security.access.Module;
import com.baseframework.domain.security.access.Role;
import com.baseframework.domain.vo.JSONDetails;

public class AuthenticateAction implements JSONDetails {

	private AuthenticateForm authenticateForm = null;

	private AuthenticateService authenticateService = null;

	private ModuleService moduleService = null;

	private RoleService roleService = null;

	private FunctionService functionService = null;

	public ModuleService getModuleService() {
		return moduleService;
	}

	public void setModuleService(ModuleService moduleService) {
		this.moduleService = moduleService;
	}

	public String onLoad() {

		List<Module> modules = getModuleService().selectAllModule();
		List<Role> roles = getRoleService().selectAllRole();

		AuthenticateForm f = getAuthenticateForm() == null ? new AuthenticateForm() : getAuthenticateForm();
		Module firstModule = f.getSelectedModule() == null ? modules.get(0) : f.getSelectedModule();
		
		List<Function> functions = getFunctionService().selectFunctions(firstModule);
		List<Authenticate> authenticatedList = getAuthenticateService().selectAuthenticatedList();

		f.setModules(modules);
		f.setAuthenticatedList(authenticatedList);
		f.setFunctions(functions);
		f.setRoles(roles);

		setAuthenticateForm(f);

		return "success";
	}

	public String selectAuthenticate() {
		return "onLoad";
	}

	public String saveAuthenticates() {
		AuthenticateForm f = getAuthenticateForm();
		getAuthenticateService().insertAuthenticate(f.getGrantList());

		return "redirect_onLoad";
	}

	public AuthenticateForm getAuthenticateForm() {
		return authenticateForm;
	}

	public void setAuthenticateForm(AuthenticateForm authenticateForm) {
		this.authenticateForm = authenticateForm;
	}

	public AuthenticateService getAuthenticateService() {
		return authenticateService;
	}

	public void setAuthenticateService(AuthenticateService authenticateService) {
		this.authenticateService = authenticateService;
	}

	public RoleService getRoleService() {
		return roleService;
	}

	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}

	public FunctionService getFunctionService() {
		return functionService;
	}

	public void setFunctionService(FunctionService functionService) {
		this.functionService = functionService;
	}

	public Object getJavaValue() {
		return getAuthenticateForm();
	}
}
