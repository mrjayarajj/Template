package com.baseframework.web.security.access;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.baseframework.biz.security.access.ModuleService;
import com.baseframework.domain.security.access.Module;
import com.baseframework.domain.vo.JSONDetails;

public class ModuleAction implements JSONDetails {
	
	public static final Logger Log = LoggerFactory.getLogger(ModuleAction.class);

	private ModuleForm moduleForm = null;

	private ModuleService moduleService = null;

	public String onLoad() {
		List<Module> moduleList = getModuleService().selectAllModule();
		ModuleForm f = getModuleForm() == null ? new ModuleForm() : getModuleForm();
		f.setModuleList(moduleList);
		setModuleForm(f);
		return "success";
	}

	public String addModule() {
		Module m = getModuleForm().getModule();
		getModuleService().insertModule(m);
		return "redirect_onLoad";
	}

	public String selectModule() {
		Module m = getModuleForm().getModule();
		Module loadedModule = getModuleService().selectModule(m.getModuleId());
		getModuleForm().setModule(loadedModule);
		getModuleForm().setAction("update");
		return "onLoad";
	}

	public String updateModule() {
		Module m = getModuleForm().getModule();
		getModuleService().updateModule(m);
		return "redirect_onLoad";
	}

	public String deleteModules() {
		List<Integer> moduleList = getModuleForm().getSelectedModuleList();
		
		List<Integer> selectedModuleList_ = new ArrayList<Integer>();
		for(Object o : moduleList){
			try{
				Integer i = Integer.parseInt(o.toString());
				selectedModuleList_.add(i);
			}catch(NumberFormatException e){
				//ignore this
			}
		}
		
		getModuleService().deleteModule(selectedModuleList_);
		return "redirect_onLoad";
	}

	public ModuleForm getModuleForm() {
		return moduleForm;
	}

	public void setModuleForm(ModuleForm moduleForm) {
		this.moduleForm = moduleForm;
	}

	public ModuleService getModuleService() {
		return moduleService;
	}

	public void setModuleService(ModuleService moduleService) {
		this.moduleService = moduleService;
	}

	public Object getJavaValue() {
		return getModuleForm();
	}

}
