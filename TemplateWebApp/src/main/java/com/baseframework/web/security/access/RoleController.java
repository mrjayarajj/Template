package com.baseframework.web.security.access;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.baseframework.biz.security.access.RoleService;
import com.baseframework.domain.security.access.Role;
import com.baseframework.domain.vo.JSONDetails;
import com.baseframework.web.security.core.userdetails.UserForm;

@Controller
@Scope("prototype")
@RequestMapping(value = "/security/access")
public class RoleController implements JSONDetails {

	private static final Logger LOG = LoggerFactory.getLogger(RoleController.class);

	private RoleForm RoleForm = null;

	@Autowired
	private RoleService roleService = null;

	@RequestMapping(value = "/roles", method = RequestMethod.GET)
	public ModelAndView onLoad() {
		LOG.info("onload of Role Action");
		List<Role> userRole = getRoleService().selectAllRole();
		RoleForm f = getRoleForm() == null ? new RoleForm() : getRoleForm();
		f.setRoleList(userRole);
		setRoleForm(f);

		ModelAndView model = new ModelAndView("/jsp/base/security/role.jsp");
		model.addObject("roleForm", f);
		return model;
	}

	@RequestMapping(value = "/roles", method = RequestMethod.POST)
	public ModelAndView addRole(@ModelAttribute("roleForm") RoleForm roleForm) {
		Role r = roleForm.getRole();
		getRoleService().insertRole(r);
		return new ModelAndView("redirect:/mvc/security/access/roles");
	}

	@RequestMapping(value = "/role/{roleId}", method = RequestMethod.GET)
	public ModelAndView selectRole(@PathVariable Integer roleId) {
		Role loadedRole = getRoleService().selectRole(roleId);
		setRoleForm(new RoleForm());
		getRoleForm().setRole(loadedRole);
		getRoleForm().setAction("update");
		return onLoad();
	}

	@RequestMapping(value = "/role/{roleId}", method = RequestMethod.POST)
	public ModelAndView updateRole(@ModelAttribute("roleForm") RoleForm roleForm) {
		Role m = roleForm.getRole();
		getRoleService().updateRole(m);
		return new ModelAndView("redirect:/mvc/security/access/roles");
	}

	@RequestMapping(value = "/roles/delete", method = RequestMethod.POST)
	public ModelAndView deleteRoles(@ModelAttribute("roleForm") RoleForm roleForm) {
		List<Integer> roleList =roleForm.getSelectedRoleList();

		List<Integer> selectedRoleList_ = new ArrayList<Integer>();
		for (Object o : roleList) {
			try {
				Integer i = Integer.parseInt(o.toString());
				selectedRoleList_.add(i);
			} catch (NumberFormatException e) {
				// ignore this
			}
		}

		getRoleService().deleteRole(selectedRoleList_);
		return new ModelAndView("redirect:/mvc/security/access/roles");
	}

	public RoleForm getRoleForm() {
		return RoleForm;
	}

	public void setRoleForm(RoleForm RoleForm) {
		this.RoleForm = RoleForm;
	}

	public RoleService getRoleService() {
		return roleService;
	}

	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}

	public Object getJavaValue() {
		return getRoleForm();
	}

}
