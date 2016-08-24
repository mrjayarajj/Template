package com.baseframework.web.security.access;

import java.util.List;

import com.baseframework.domain.security.access.Module;

public class ModuleForm {

	private String action = "add";

	private Module module = null;

	private List<Module> moduleList = null;

	private List<Integer> selectedModuleList = null;

	public Module getModule() {
		return module;
	}

	public void setModule(Module module) {
		this.module = module;
	}

	public List<Module> getModuleList() {
		return moduleList;
	}

	public void setModuleList(List<Module> moduleList) {
		this.moduleList = moduleList;
	}

	public List<Integer> getSelectedModuleList() {
		return selectedModuleList;
	}

	public void setSelectedModuleList(List<Integer> selectedModuleList) {
		this.selectedModuleList = selectedModuleList;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

}
