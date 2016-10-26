package com.baseframework.web.security.access;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.baseframework.biz.security.access.AuthenticateService;
import com.baseframework.biz.security.access.FunctionService;
import com.baseframework.biz.security.access.ModuleService;
import com.baseframework.biz.security.access.RoleService;
import com.baseframework.domain.security.access.Authenticate;
import com.baseframework.domain.security.access.Function;
import com.baseframework.domain.security.access.Module;
import com.baseframework.domain.security.access.Role;
import com.baseframework.domain.vo.JSONDetails;

@Controller
@Scope("prototype")
@RequestMapping(value = "/security/access")
public class AuthenticateController implements JSONDetails {

	private AuthenticateForm authenticateForm = null;

	@Autowired
	private AuthenticateService authenticateService = null;

	@Autowired
	private ModuleService moduleService = null;

	@Autowired
	private RoleService roleService = null;

	@Autowired
	private FunctionService functionService = null;

	public ModuleService getModuleService() {
		return moduleService;
	}

	public void setModuleService(ModuleService moduleService) {
		this.moduleService = moduleService;
	}

	@RequestMapping(value = "/authenticate", method = RequestMethod.GET)
	public ModelAndView onLoad() {

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
		f.setSelectedModule(firstModule);

		setAuthenticateForm(f);

		ModelAndView model = new ModelAndView("/jsp/base/security/authenticate.jsp");
		model.addObject("authenticateForm", f);
		return model;
	}

	@RequestMapping(value = "/authenticate/{moduleId}", method = RequestMethod.GET)
	public ModelAndView selectAuthenticate(@PathVariable Integer moduleId) {
		AuthenticateForm f = new AuthenticateForm();
		f.setSelectedModule(new Module(moduleId));
		setAuthenticateForm(f);
		return onLoad();
	}

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ModelAndView saveAuthenticates(@ModelAttribute("authenticateForm") AuthenticateForm authenticateForm) {
		getAuthenticateService().insertAuthenticate(authenticateForm.getGrantList());
		return new ModelAndView("redirect:/mvc/security/access/authenticate");
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
