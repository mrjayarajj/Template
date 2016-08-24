package com.baseframework.web.security.access;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.baseframework.biz.security.access.RoleService;
import com.baseframework.domain.security.access.Role;
import com.baseframework.domain.vo.JSONDetails;

public class RoleAction implements JSONDetails {
	
	private static final Logger LOG = LoggerFactory.getLogger(RoleAction.class);

	private RoleForm RoleForm = null;

	private RoleService roleService = null;

	public String onLoad() {	
		LOG.info("onload of Role Action");
		List<Role> userRole = getRoleService().selectAllRole();
		RoleForm f = getRoleForm() == null ? new RoleForm() : getRoleForm();
		f.setRoleList(userRole);
		setRoleForm(f);		
		return "success";
	}

	public String addRole() {
		Role r = getRoleForm().getRole();
		getRoleService().insertRole(r);
		return "redirect_onLoad";
	}

	public String selectRole() {
		Role r = getRoleForm().getRole();
		Role loadedRole = getRoleService().selectRole(r.getRoleId());
		getRoleForm().setRole(loadedRole);
		getRoleForm().setAction("update");
		return "onLoad";
	}

	public String updateRole() {
		Role m = getRoleForm().getRole();
		getRoleService().updateRole(m);
		return "redirect_onLoad";
	}

	public String deleteRoles() {
		List<Integer> roleList = getRoleForm().getSelectedRoleList();
		
		List<Integer> selectedRoleList_ = new ArrayList<Integer>();
		for(Object o : roleList){
			try{
				Integer i = Integer.parseInt(o.toString());
				selectedRoleList_.add(i);
			}catch(NumberFormatException e){
				//ignore this
			}
		}
		
		getRoleService().deleteRole(selectedRoleList_);
		return "redirect_onLoad";
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
